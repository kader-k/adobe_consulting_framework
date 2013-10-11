<%@include file="../../global.jsp" %>

<cmp:presenter className="com.myproject.components.SampleComponent" id="sample" />


<h1>${sample.customTitle}</h1>

<c:if test="${myPage.homepage}">
<b>This is the homepage...</b>
</c:if>

${properties.myCustomProperty}

<cq:include path="par" resourceType="foundation/components/parsys" />

<wcmmode:edit not="true">
	Show this not to the author...
</wcmmode:edit>
