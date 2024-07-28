<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ljh.mvc.wifi.WifiController" %>

<%@ include file="../common/head.jspf" %>

<%
    WifiController wifiController = new WifiController();
    int loadedCnt = wifiController.loadWifiInformation();
%>

<% if (loadedCnt > 0) { %>
    <h1 class="font-bold text-4xl text-center mt-[5px]"><%=loadedCnt%>개의 WIFI 정보를 정상적으로 불러왔습니다.</h1>
<% } else { %>
    <h1 class="font-bold text-4xl text-center mt-[5px]">데이터 저장 실패</h1>
<% } %>

<%@ include file="../common/foot.jspf" %>