<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
		
		
		<div id="content">

			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">操作失败</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12 center" style="text-align: center;">					
							
							
							<div class="widget-box">							
								<div class="widget-content">
									${operateException}
								</div>
							</div>
							
								<a href="<%=request.getContextPath()%>/IndexInit.do"><img src="<%=request.getContextPath()%>/img/error.png" alt="test" /></a>
						
					</div>
				</div>
			</div>
			
			
			&nbsp; &nbsp;&nbsp;<a class="btn btn-success" href="<%=request.getContextPath()%>/IndexInit.do">确认</a>
			&nbsp; &nbsp;&nbsp;<a class="btn btn-primary"   href="javascript:history.go(-1);">返回</a>
			
			<div class="row-fluid">
				<div id="footer" class="span12">
					2013  Copyright Reserved by Tinder
				</div>
			</div>
		</div>
       
</body>
</html>
