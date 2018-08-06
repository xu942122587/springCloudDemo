package com.ruixun.sincfin.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fe on 2016/11/30.
 */
public class WebHelper {

    public static final Logger logger = LoggerFactory.getLogger(WebHelper.class);

    public static void renderResponse(HttpServletResponse response, String jsonResult) {
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(jsonResult);
        } catch (IOException e) {


        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    public static Map<String,Object> getRequestParam(HttpServletRequest request) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String val = request.getParameter(key);

            paramMap.put(key,val);
        }
        return paramMap;
    }

}