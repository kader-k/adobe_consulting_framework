<%@include file="../global.jsp" %>

<cmp:presenter className="com.myproject.components.SmartListComponent" id="smartlist" />

<wcmmode:setMode mode="disabled">
<c:forEach items="${smartlist.docs}" var="doc">

	<sling:include path="${doc.path}.teaser.html" />

</c:forEach>
</wcmmode:setMode>
