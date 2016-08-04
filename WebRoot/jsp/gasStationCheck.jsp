<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
 
		<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">下发实物资产盘点</font></h1>

			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">盘点管理</a>
				<a href="#" class="current">下发实物资产盘点</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					 <div>
 
						
						<form action="<%=request.getContextPath()%>/GasStationCheck.do" method="post" class="form-horizontal" >
						       <button type="submit" class="btn btn-primary" name="submit" value="submit">下发盘点</button>
						       <button type="submit" class="btn btn-primary" name="submit" value="cancel">取消</button>
						       
					    </form>
						
					</div>
				</div>
				
				
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder.
					</div>
				</div>
			</div>
       </div>

	</body>
</html>
