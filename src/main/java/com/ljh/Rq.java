package com.ljh;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset utf-8");
    }

    public int getIntParam(String paramName, int defaultValue){
        String value = req.getParameter(paramName);

        if (value == null) return defaultValue;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getParam(String paramName, String defaultValue){
        String value = req.getParameter(paramName);

        if (value == null) return defaultValue;

        return value;
    }

    public void setAttr(String name, Object value){
        req.setAttribute(name, value);
    }

    public void view(String path){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path + ".jsp");

        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public void print(String str){
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void println(String str){
        print(str + "\n");
    }

    public void replace(String url, String msg){
        if (msg != null && !msg.trim().isEmpty()){
            println("""
                    <script>
                        alert("%s");
                    </script>
                    """.formatted(msg));
        }

        println("""
                <script>
                    location.replace("%s");
                </script>
                """.formatted(url));
    }

    public void historyBack(String msg){
        if (msg != null && !msg.trim().isEmpty()){
            println("""
                    <script>
                        alert("%s");
                    </script>
                    """.formatted(msg));
        }

        println("""
                <script>
                    history.back();
                </script>
                """);
    }

    public String getPath(){
        return req.getRequestURI();
    }

    public String getMethod(){
        return req.getMethod();
    }

    public boolean isLong(String str){
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getActionPath(){
        String path = getPath();

        if (path.contains("?")){
            int idx = path.indexOf('?');
            path = path.substring(0, idx);
        }

        String[] bits = getPath().split("/");
        StringBuilder pathBuilder = new StringBuilder();

        if (isLong(bits[bits.length - 1])){
            for (int i = 1; i < bits.length - 1; i++){
                pathBuilder.append("/%s".formatted(bits[i]));
            }
        } else {
            for (int i = 1; i < bits.length; i++){
                pathBuilder.append("/%s".formatted(bits[i]));
            }
        }

        return pathBuilder.toString();
    }

    public long getLongPathValueByIndex(int idx, int defaultValue){
        String value = getPathValueByIndex(idx, null);

        if (value == null) return defaultValue;

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String getPathValueByIndex(int idx, String defaultValue){
        String[] bits = getPath().split("/");

        try {
            String bit = bits[bits.length - idx];

            if (bit.contains("?")){
                int idx2 = bit.indexOf('?');
                bit = bit.substring(0, idx);
            }

            return bit;
        } catch (ArrayIndexOutOfBoundsException e){
            return defaultValue;
        }
    }

    public String getRouteMethod(){
        String method = getParam("_method", "");

        if (!method.isEmpty()){
            return method.toUpperCase();
        }

        return req.getMethod();
    }
}
