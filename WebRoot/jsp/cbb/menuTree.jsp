<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <ul>
   <c:forEach var="menu" items="${menuTreeList}">
      <li class="${menu.menuCss}" >
       <a href="<%=request.getContextPath()%>/${menu.url}"><i class="${menu.iconCss}"></i>${menu.value}</a>
       <c:if test="${null!=menu.childItemList}"> 
          <c:set var="menuTreeList" value="${menu.childItemList}" scope="request"/>
          <jsp:include page="/jsp/cbb/menuTree.jsp"/>
       </c:if>
       </li>
   </c:forEach>
 </ul>

 
