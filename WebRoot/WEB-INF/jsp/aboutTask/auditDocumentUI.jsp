<%@ include file="/WEB-INF/jsp/share/taglib.jsp" %>
<core:forEach items="${documents }" var="document">
		<li><img src="${pageContext.request.contextPath }/${document.documentFullPath }" ></li>
</core:forEach>
