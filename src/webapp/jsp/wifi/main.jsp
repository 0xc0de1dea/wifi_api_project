<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ljh.mvc.wifi.WifiController" %>
<%@ page import="com.ljh.dto.RowDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ljh.mvc.wifi.WifiRepository" %>
<%@ page import="com.ljh.Rq" %>

<%@ include file="../common/head.jspf" %>

<%
    Rq rq = new Rq(request, response);
    String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
    String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
%>

<script>
    function findMyLocation(){
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition(
                function (pos){
                    let lat = pos.coords.latitude;
                    let lnt = pos.coords.longitude;

                    document.getElementById('lat').value = lat;
                    document.getElementById('lnt').value = lnt;
                }
            )
        }
    }

    function searchNearWifi(){
        let lat = document.getElementById("lat").value;
        let lnt = document.getElementById("lnt").value;
        location.assign("http://localhost:8081/wifi/main?lat=" + lat + "&lnt=" + lnt);
    }
</script>

<div class="mt-[10px]">
    <span>LAT:</span>
    <input type="text" id="lat" value="<%=lat%>" placeholder="위도를 입력하세요" class="input input-bordered w-full max-w-xs" />
    <span>, LNT</span>
    <input type="text" id="lnt" value="<%=lnt%>" placeholder="경도를 입력하세요" class="input input-bordered w-full max-w-xs" />
    <input class="btn btn-primary" type="button" onclick="findMyLocation()" value="내 위치 가져오기"/>
    <input class="btn btn-secondary" type="button" onclick="searchNearWifi()" value="근처 WIFI 정보 보기"/>
</div>
</br>

<div class="overflow-x-auto">
    <table class="table">
        <!-- head -->
        <thead>
        <tr>
            <th></th>
            <th>거리(KM)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
            <%
                String initLoc = "0.0";

                if (lat.equals(initLoc) || lnt.equals(initLoc)){ %>
            <td class="text-center text-3xl" colspan="17">위치 정보를 입력하신 후에 조회해 주세요.</td>
            <% } else { %>
                <%
                    WifiRepository wifiRepository = new WifiRepository();
                    List<RowDTO> rows = wifiRepository.getNearWifiLimit20(lat, lnt);
                    int idx = 0;

                for (RowDTO row : rows){
                %>
                    <tr class="hover">
                        <td></td>
                        <td><%=row.getDIST()%></td>
                        <td><%=row.getX_SWIFI_MGR_NO()%></td>
                        <td><%=row.getX_SWIFI_WRDOFC()%></td>
                        <td class="text-blue-500"><a class="hover:underline hover:text-[red]" href="/wifi/detail/<%=row.getID()%>"><%=row.getX_SWIFI_MAIN_NM()%></a></td>
                        <td><%=row.getX_SWIFI_ADRES1()%></td>
                        <td><%=row.getX_SWIFI_ADRES2()%></td>
                        <td><%=row.getX_SWIFI_INSTL_FLOOR()%></td>
                        <td><%=row.getX_SWIFI_INSTL_MBY()%></td>
                        <td><%=row.getX_SWIFI_INSTL_TY()%></td>
                        <td><%=row.getX_SWIFI_SVC_SE()%></td>
                        <td><%=row.getX_SWIFI_CMCWR()%></td>
                        <td><%=row.getX_SWIFI_CNSTC_YEAR()%></td>
                        <td><%=row.getX_SWIFI_INOUT_DOOR()%></td>
                        <td><%=row.getX_SWIFI_REMARS3()%></td>
                        <td><%=row.getLAT()%></td>
                        <td><%=row.getLNT()%></td>
                        <td><%=row.getWORK_DTTM()%></td>
                    </tr>
                <% } %>
            <% } %>
        </tbody>
    </table>
</div>

<%@ include file="../common/foot.jspf" %>