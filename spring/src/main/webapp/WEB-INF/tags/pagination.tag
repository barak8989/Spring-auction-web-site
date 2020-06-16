<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="page" required="true"
	type="org.springframework.data.domain.Page"%>
<%@ attribute name="url" required="true"%>

<%-- Number of page numbers to display at once. --%>
<%@ attribute name="size" required="false"%>

<c:set var="size" value="${empty size ? 10: size}" />

<c:set var="block" value="${empty param.b ? 0: param.b}" />

<c:set var="startPage" value="${block * size + 1}" />
<c:set var="endPage" value="${(block + 1) * size}" />
<c:set var="endPage"
	value="${endPage > page.totalPages ? page.totalPages : endPage}" />

<c:if test="${page.totalPages != 1}">

<ul class="pagination">

	<c:if test="${block != 0}">
	 <a class="page-link" href="${url}?b=${block-1}&p=${(block-1)*size + 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
	</c:if>

	<c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">
	
		<c:choose>
		
			<c:when test="${page.number != pageNumber - 1}">
			<li class="page-item">
				<a class="page-link" href="${url}?p=${pageNumber}&b=${block}"><c:out value="${pageNumber}" /></a>
			</li>
			</c:when>

			 <c:otherwise>
			 <li class="page-item active">
				<a class="page-link"><c:out value="${pageNumber}"/></a>
				</li>
			</c:otherwise> 

		</c:choose>
				
	</c:forEach>
	<c:if test="${endPage != page.totalPages}">	
	<li class="page-item">
	      <a class="page-link" href="?b=${block+1}&p=${(block+1)*size + 1}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	        <span class="sr-only">Next</span>
	      </a>
	</li>
	</c:if>
</ul>
</c:if>