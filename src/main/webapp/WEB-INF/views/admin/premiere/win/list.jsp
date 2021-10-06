<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/global/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/JS/admin/premiere/info/premiere.js"></script>
<!-- CSS 적용 -->
<link href="${ pageContext.request.contextPath }/CSS/header.css" rel="stylesheet" type="text/css">
<!-- JQuery 적용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- fontawesome 적용 -->
<script src="https://kit.fontawesome.com/41ddd3d635.js"></script>
</head>
<style>
.article-detail {
    border:2px solid lightgray;
}

.article-detail > table {
    border:none;
    width:calc(100% - 100px);
}

.article-detail > table th, .article-detail > table td {
    border:none;
}

.article-detail > table tr:not(:last-child) {
    border-bottom:1px solid lightgray;
}

.article-detail > table tr:not(.article-body), .article-detail > table tr:not(.article-body) > td{
    height:20px;
    font-size:0.8rem;
}

.article-detail > table tr.article-title > td {
    height:40px;
    font-weight:bold;
    font-size:1.2rem;
}

.article-detail > table td:last-child {
    padding-right:100px;
}

.article-detail > .article-writer {
    width:100px;
    height:102px;
    background-color:lightgray;
    border-bottom:1px solid lightgray;
    text-align:center;
}

.article-detail > .article-writer .writer-icon {
    margin:0 auto;
    background-color:white;
    width:80px;
    height:80px;
    border-radius:50%;
}
</style>
<body>
<h2>list</h2>
<h1 class="con">게시글 상세</h1>
    <section class="article-detail table-common con row">
        <div class="article-writer cell">
                <div class="writer-icon">이미지</div>



</body>
</html>