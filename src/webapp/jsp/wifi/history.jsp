<%@ page import="java.util.List" %>
<%@ page import="com.ljh.dto.HistoryDTO" %>
<%@ page import="com.ljh.mvc.history.HistoryRepository" %>
<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf" %>

<%
    HistoryRepository historyRepository = new HistoryRepository();
    List<HistoryDTO> historyList = historyRepository.selectAll();
%>

<div class="overflow-x-auto">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <% for (HistoryDTO historyDTO : historyList) { %>
        <tr class="hover">
            <td class="text-center"><%=historyDTO.getID()%></td>
            <td><%=historyDTO.getLAT()%></td>
            <td><%=historyDTO.getLNT()%></td>
            <td><%=historyDTO.getSEARCH_DATETIME()%></td>
            <td class="text-center">
                <a onclick="if (!confirm('정말 삭제하시겠습니까?')) return false;" class="btn btn-secondary" href="/wifi/history/delete/<%=historyDTO.getID()%>?_method=DELETE">
                    삭제
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="../common/foot.jspf" %>