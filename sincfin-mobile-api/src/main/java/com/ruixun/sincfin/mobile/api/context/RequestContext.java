package com.ruixun.sincfin.mobile.api.context;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by t on 2017/4/14.
 */
@Component
public class RequestContext {

    private static final Logger logger            = LoggerFactory.getLogger(RequestContext.class);

    private static ThreadLocal<HttpServletRequest>  requestContext    = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responseContext   = new ThreadLocal<HttpServletResponse>();
    private static ThreadLocal<Object>              controllerContext = new ThreadLocal<Object>();

    public static void init(HttpServletRequest request, HttpServletResponse response, Object controller) {

        setRequest(request);
        setResponse(response);
        setController(controller);

    }

    public static HttpSession getSession() {
        if (getRequest() == null) {
            return null;
        }
        return getRequest().getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return requestContext.get();
    }

    public static HttpServletResponse getResponse() {
        return responseContext.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestContext.set(request);
    }

    public static void setResponse(HttpServletResponse response) {
        responseContext.set(response);
    }

    public static void setController(Object controller) {
        controllerContext.set(controller);
    }

    public <T> T getController() {
        return (T) controllerContext.get();
    }

    public static void clear() {
        requestContext.remove();
        responseContext.remove();
        controllerContext.remove();
    }

    public static String getParam(String name) {
        return requestContext.get().getParameter(name);
    }

    public static String getStr(String name) {
        return getStr(name, StringUtils.EMPTY);
    }

    public static String getStr(String name, String defaultValue) {
        String v = getParam(name);
        if (StringUtils.isBlank(v)) {
            return defaultValue;
        }

        return v;
    }

    public static Boolean getBoolean(String name, Boolean defaultValue) {
        String value = getParam(name);
        Boolean bool = BooleanUtils.toBooleanObject(value);
        if (bool == null) {
            return defaultValue;
        }

        return bool;
    }

    public static Boolean getBoolean(String name) {
        return getBoolean(name, null);
    }

    public static Integer getInteger(String name) {
        return getInteger(name, null);
    }

    public static Integer getInteger(String name, Integer defaultValue) {
        String value = getParam(name);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }

        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return defaultValue;
    }

}
