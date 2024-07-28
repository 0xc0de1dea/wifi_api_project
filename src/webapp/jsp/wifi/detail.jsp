<%@ page import="com.ljh.Rq" %>
<%@ page import="com.ljh.dto.RowDTO" %>
<%@ page import="com.ljh.mvc.wifi.WifiRepository" %>
<%@ page import="com.ljh.dto.BookMarkGroupDTO" %>
<%@ page import="com.ljh.mvc.bookmarkgroup.BookMarkGroupRepository" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf" %>

<%
    BookMarkGroupRepository bookmarkGroupRepository = new BookMarkGroupRepository();
    RowDTO rowDTO = (RowDTO)request.getAttribute("row");
    List<BookMarkGroupDTO> bookMarkGroupList = bookmarkGroupRepository.selectAll();
%>

<form method="POST" action="http://localhost:8081/wifi/bookmark/add">
    <select name="bookmark_group_name" class="select select-bordered w-full max-w-xs">
        <option value="--북마크 그룹 이름 선택">북마크 그룹 이름 선택</option>

        <% for (BookMarkGroupDTO bookMarkGroupDTO : bookMarkGroupList){ %>
        <option value="<%=bookMarkGroupDTO.getID()%>"><%=bookMarkGroupDTO.getNAME()%></option>
        <% } %>
    </select>
    <input type="hidden" name="wifi" value="<%=rowDTO.getID()%>">
    <input class="btn btn-active btn-accent" type="submit" value="북마크 추가하기">
</form>

<div class="overflow-x-auto">
    <table class="table">
        <tr class="hover">
            <th>거리(KM)</th>
            <td><%=rowDTO.getDIST()%></td>
        </tr>
        <tr class="hover">
            <th>관리번호</th>
            <td><%=rowDTO.getX_SWIFI_MGR_NO()%></td>
        </tr>
        <tr class="hover">
            <th>자치구</th>
            <td><%=rowDTO.getX_SWIFI_WRDOFC()%></td>
        </tr>
        <tr class="hover">
            <th>와이파이명</th>
            <td><%=rowDTO.getX_SWIFI_MAIN_NM()%></td>
        </tr>
        <tr class="hover">
            <th>도로명주소</th>
            <td><%=rowDTO.getX_SWIFI_ADRES1()%></td>
        </tr>
        <tr class="hover">
            <th>상세주소</th>
            <td><%=rowDTO.getX_SWIFI_ADRES2()%></td>
        </tr>
        <tr class="hover">
            <th>설치위치(층)</th>
            <td><%=rowDTO.getX_SWIFI_INSTL_FLOOR()%></td>
        </tr>
        <tr class="hover">
            <th>설치기관</th>
            <td><%=rowDTO.getX_SWIFI_INSTL_MBY()%></td>
        </tr>
        <tr class="hover">
            <th>설치유형</th>
            <td><%=rowDTO.getX_SWIFI_INSTL_TY()%></td>
        </tr>
        <tr class="hover">
            <th>서비스구분</th>
            <td><%=rowDTO.getX_SWIFI_SVC_SE()%></td>
        </tr>
        <tr class="hover">
            <th>망종류</th>
            <td><%=rowDTO.getX_SWIFI_CMCWR()%></td>
        </tr>
        <tr class="hover">
            <th>설치년도</th>
            <td><%=rowDTO.getX_SWIFI_CNSTC_YEAR()%></td>
        </tr>
        <tr class="hover">
            <th>실내외구분</th>
            <td><%=rowDTO.getX_SWIFI_INOUT_DOOR()%></td>
        </tr>
        <tr class="hover">
            <th>WIFI접속환경</th>
            <td><%=rowDTO.getX_SWIFI_REMARS3()%></td>
        </tr>
        <tr class="hover">
            <th>X좌표</th>
            <td><%=rowDTO.getLAT()%></td>
        </tr>
        <tr class="hover">
            <th>Y좌표</th>
            <td><%=rowDTO.getLNT()%></td>
        </tr>
        <tr class="hover">
            <th>작업일자</th>
            <td><%=rowDTO.getWORK_DTTM()%></td>
        </tr>
    </table>
</div>

<%@ include file="../common/foot.jspf" %>