<%@ page import="com.ljh.mvc.bookmarkgroup.BookMarkGroupController" %>
<%@ page import="com.ljh.dto.BookMarkGroupDTO" %>
<%@ page language="java" contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf" %>

<script>
    function bookmarkGroupSave__submitForm(form){
        form.bookmark_name.value = form.bookmark_name.value.trim();

        if (form.bookmark_name.length == 0){
            alert('북마크 이름을 입력해주세요.');
            form.bookmark_name.focus();
            return;
        }

        form.bookmark_order_no.value = form.bookmark_order_no.value.trim();

        if (form.bookmark_order_no.length == 0){
            alert('순번을 입력해주세요.');
            form.bookmark_order_no.focus();
            return;
        }

        form.submit();
    }
</script>

<p>북마크 그룹 이름을 삭제하시겠습니까?</p>

<%
    BookMarkGroupController bookMarkGroupController = new BookMarkGroupController();
    BookMarkGroupDTO bookmarkGroupDTO = (BookMarkGroupDTO) request.getAttribute("id");

    int id = bookmarkGroupDTO.getID();
    String name = bookmarkGroupDTO.getNAME();
    int order = bookmarkGroupDTO.getORDER_NO();
%>

<form method="POST" onsubmit="bookmarkGroupSave__submitForm(this); return false;">
    <div class="overflow-x-auto">
        <table class="table">
            <tr>
                <th>북마크 이름</th>
                <td><input type="text" name="name" value="<%=name%>"></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="text" name="order" value="<%=order%>"></td>
            </tr>
            <tr>
                <td style="text-align: center" colspan="2">
                    <a class="btn btn-primary" onclick="if (!confirm('정말 삭제하시겠습니까?')) return false;" href="/wifi/bookmarkgroup/delete/<%=bookmarkGroupDTO.getID()%>?_method=DELETE">
                        삭제
                    </a>
                    <a class="btn btn-secondary" href="/wifi/bookmarkgroup/list">돌아가기</a>
                </td>
            </tr>
        </table>
    </div>
</form>

<%@ include file="../common/foot.jspf" %>