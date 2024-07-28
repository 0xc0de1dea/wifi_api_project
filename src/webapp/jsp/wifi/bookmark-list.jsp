<%@ page import="com.ljh.dto.BookMarkDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ljh.mvc.bookmark.BookMarkController" %>
<%@ page import="com.ljh.dto.RowDTO" %>
<%@ page import="com.ljh.dto.BookMarkGroupDTO" %>
<%@ page import="com.ljh.mvc.bookmarkgroup.BookMarkGroupController" %>
<%@ page import="com.ljh.mvc.wifi.WifiController" %>
<%@ page import="com.ljh.mvc.bookmark.BookMarkRepository" %>
<%@ page import="com.ljh.mvc.bookmarkgroup.BookMarkGroupRepository" %>
<%@ page import="com.ljh.mvc.wifi.WifiRepository" %>
<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf" %>

<div class="overflow-x-auto">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>와이파이명</th>
            <th>등록일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            BookMarkRepository bookMarkRepository = new BookMarkRepository();
            BookMarkGroupRepository bookMarkGroupRepository = new BookMarkGroupRepository();
            WifiRepository wifiRepository = new WifiRepository();

            List<BookMarkDTO> bookmarkList = bookMarkRepository.selectAll();

            for (BookMarkDTO bookmarkDTO : bookmarkList){
                BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupRepository.select(bookmarkDTO.getGROUP_ID());
                RowDTO wifiDTO = wifiRepository.select(bookmarkDTO.getWIFI_ID());
        %>

            <tr class="hover">
                <td class="text-center"><%=bookmarkDTO.getID()%></td>
                <td><%=bookMarkGroupDTO.getNAME()%></td>
                <td class="text-blue-500">
                    <a class="hover:underline hover:text-[red]" href="/wifi/detail/<%=wifiDTO.getID()%>"><%=wifiDTO.getX_SWIFI_MAIN_NM()%></a>
                </td>
                <td><%=bookmarkDTO.getREGISTER_DATETIME()%></td>
                <td class="text-center">
                    <a onclick="if (!confirm('정말 삭제하시겠습니까?')) return false;" class="btn btn-secondary" href="/wifi/bookmark/delete/<%=bookmarkDTO.getID()%>?_method=DELETE">
                        삭제
                    </a>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="../common/foot.jspf" %>