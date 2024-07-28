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

<form method="POST" onsubmit="bookmarkGroupSave__submitForm(this); return false;">
    <div class="overflow-x-auto">
        <table class="table">
            <tr>
                <th>북마크 이름</th>
                <td><input type="text" name="bookmark_name" placeholder="북마크 이름을 작성하세요"></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="text" name="bookmark_order_no" placeholder="순서를 작성하세요"></td>
            </tr>
            <tr>
                <td style="text-align: center" colspan="2">
                    <input class="btn btn-primary" type="submit" value="추가">
                    <a class="btn btn-secondary" href="/wifi/bookmarkgroup/list">취소</a>
                </td>
            </tr>
        </table>
    </div>
</form>

<%@ include file="../common/foot.jspf" %>