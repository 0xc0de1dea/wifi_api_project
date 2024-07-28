<%@ page import="com.ljh.dto.BookMarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ljh.mvc.bookmarkgroup.BookMarkGroupController" %>
<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf" %>

<a class="btn btn-outline btn-success" href="/wifi/bookmarkgroup/insert">북마크 그룹 이름 추가</a>

<div class="overflow-x-auto">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>북마크이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <%
            BookMarkGroupController bookMarkGroupController = new BookMarkGroupController();
            List<BookMarkGroupDTO> bookmarkGroupList = bookMarkGroupController.selectAll();

            for (BookMarkGroupDTO bookmarkGroupDTO : bookmarkGroupList){ %>
        <tr class="hover">
            <td class="text-center"><%=bookmarkGroupDTO.getID()%></td>
            <td><%=bookmarkGroupDTO.getNAME()%></td>
            <td><%=bookmarkGroupDTO.getORDER_NO()%></td>
            <td><%=bookmarkGroupDTO.getREGISTER_DATETIME()%></td>
            <td>
                <%
                    String editDatetime = bookmarkGroupDTO.getEDIT_DATETIME();

                    if (editDatetime != null){ %>
                        <%=bookmarkGroupDTO.getEDIT_DATETIME()%>
                    <% }
                %>
            </td>
            <td class="text-center">
                <a class="btn btn-primary" href="/wifi/bookmarkgroup/update/<%=bookmarkGroupDTO.getID()%>">수정</a>
                <a class="btn btn-secondary" href="/wifi/bookmarkgroup/delete/<%=bookmarkGroupDTO.getID()%>">삭제</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="../common/foot.jspf" %>