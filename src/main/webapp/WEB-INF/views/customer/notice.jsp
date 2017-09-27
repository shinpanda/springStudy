<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main>
	<c:forEach items="${list}" begin="0" end="10" var="n">
		<p>${n.title}</p>
	</c:forEach>
</main>