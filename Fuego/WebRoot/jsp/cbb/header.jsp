<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>		
<div id="header">
	<h1><a href="">惠州石油资产管理系统</a></h1>		
</div>

		
			
<div id="user-nav" class="navbar navbar-inverse"> 
    <ul class="nav btn-group">
		<li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span class="text">${systemUser.userID}</span></a></li>
		<li class="btn btn-inverse"><a title="" href="<%=request.getContextPath()%>/"><i class="icon icon-share-alt"></i> <span class="text">退出系统</span></a></li>
    </ul>
</div>
 
<div id="sidebar">
	<jsp:include page="/jsp/cbb/menuTree.jsp"/>
</div>

<div id="style-switcher">
	<i class="icon-arrow-left icon-white"></i>
	<span>Style:</span>
	<a href="#grey" style="background-color: #555555;border-color: #aaaaaa;"></a>
	<a href="#blue" style="background-color: #2D2F57;"></a>
	<a href="#red" style="background-color: #673232;"></a>
</div>
 
