<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web Study</title>
</head>
<style>
    #img-input {
        display: none;
    }
    .upload-box {
        width: 150px;
        height: 150px;
        border: 3px dashed #454444;
        display: flex;
        justify-content: center;
        align-items: center;
        color: grey;
        font-weight: 700;
        cursor: pointer;
    }
</style>
<body>

<h1>파일 업로드 예제</h1>
<div class="upload-box">CLICK HERE!</div>
  <form action="/upload/file" method="post" enctype="multipart/form-data">
    <%--   form태그의 enctype="multipart/form-data" 속성이 있어야 한다.  --%>

    <input type="file" name="thumbnail" id="img-input" accept="image/*">

    <button type="submit">전송</button>
  </form>

<script>

    document.querySelector('.upload-box').onclick = e => {
        document.getElementById('img-input').click();
    }

</script>
</body>
</html>