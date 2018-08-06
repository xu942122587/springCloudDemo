package com.ruixun.sincfin.biz.module.file.aliyun.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

/**
 * <p>get aliyun oss utils</p>
 *
 * @author q 2018-06-04
 * @version v1.0
 */
public class AliyunOSSUtils {

    private static AliyunOSSUtils aliyunOSSUtils = new AliyunOSSUtils();

    private OSSClient client;

    private AliyunOSSUtils() {

    }

    public static AliyunOSSUtils getSingle() {
        return aliyunOSSUtils;
    }


    public OSSClient build() {

        OSSClient client = new OSSClient(AliyunOSSConfig.getEndpoint(),
                AliyunOSSConfig.getAccessKeyId(), AliyunOSSConfig.getAccessKeySecret());

        if (!client.doesBucketExist(AliyunOSSConfig.getBucketPrivate())) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(AliyunOSSConfig.getBucketPrivate());
            createBucketRequest.setCannedACL(CannedAccessControlList.Private);
            client.createBucket(createBucketRequest);
        }

        return client;
    }
    /**
     * <p>关闭client链接</p>
     */
    public void clean() {
        if (client != null)
            client.shutdown();
    }


    /**
     * <p> 读取 properties文件 </p>
     *
     * @param filePath
     * @return
     */
    public static Properties getProperties(String filePath) {
        Properties properties = new Properties();
        try {
            InputStream in = AliyunSTSUtils.class.getClassLoader().getResourceAsStream(filePath);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    /**
     * <p> 读取默认 properties文件 </p>
     *
     * @return
     */
    public static Properties getDefProperties() {
        Properties properties = new Properties();
        try {
            InputStream in = AliyunSTSUtils.class.getClassLoader().getResourceAsStream("tmp-application-oss.yml");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * <p>get aliyun oss callback request body data</p>
     *
     * @param is
     * @param contentLen
     * @return
     */
    public static String getCallbackData(ServletInputStream is, int contentLen) {

        if (contentLen > 0) {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];
            try {
                while (readLen != contentLen) {
                    readLengthThisTime = is.read(message, readLen, contentLen - readLen);
                    if (readLengthThisTime == -1) {// Should not happen.
                        break;
                    }
                    readLen += readLengthThisTime;
                }
                return new String(message);
            } catch (IOException e) {
            }
        }
        return "";
    }


    /**
     * <p>RSA 解密校验是否合法aliyun oss request</p>
     *
     * @param request
     * @param ossCallbackBody
     * @return
     */
    public static boolean verifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody) throws NumberFormatException, IOException  {

        boolean ret = false;
        String autorizationInput = new String(request.getHeader("Authorization"));
        String pubKeyInput = request.getHeader("x-oss-pub-key-url");
        byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
        byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
        String pubKeyAddr = new String(pubKey);
        if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
            System.out.println("pub key addr must be oss addrss");
            return false;
        }
        String retString = executeGet(pubKeyAddr);
        retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
        retString = retString.replace("-----END PUBLIC KEY-----", "");
        String queryString = request.getQueryString();
        String uri = request.getRequestURI();
        String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
        String authStr = decodeUri;
        if (queryString != null && !queryString.equals("")) {
            authStr += "?" + queryString;
        }
        authStr += "\n" + ossCallbackBody;
        ret = doCheck(authStr, authorization, retString);
        return ret;
    }

    public static boolean doCheck(String content, byte[] sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            boolean bverify = signature.verify(sign);
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String executeGet(String url) {
        BufferedReader in = null;

        String content = null;
        try {
            // 定义HttpClient
            @SuppressWarnings("resource")
            DefaultHttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();
        } catch (Exception e) {
        } finally {
            if (in != null) {
                try {
                    in.close();// 最后要关闭BufferedReader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
    }

    /**
     * <p>返回给 aliyun oss 成功状态</p>
     *
     * @param request
     * @param response
     * @param results
     * @param status
     * @throws IOException
     */
    public static void response(HttpServletRequest request, HttpServletResponse response, String results, int status) throws IOException {
        String callbackFunName = request.getParameter("callback");
        response.addHeader("Content-Length", String.valueOf(results.length()));
        if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
            response.getWriter().println(results);
        else
            response.getWriter().println(callbackFunName + "( " + results + " )");
        response.setStatus(status);
        response.flushBuffer();
    }
}
