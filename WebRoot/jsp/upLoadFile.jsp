<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
 	
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<title>简单的文件上传</title>
		</head>
	<body>

		<form action="<%=request.getContextPath()%>/UpLoad.do" method="post" enctype="multipart/form-data"  name="UpLoadForm">
			文件标题：<input type="text" name="title" /><br>
			选择文件：<input type="file" name="myFile" /><br>
			<input value="上传" type="submit" />
		</form>
    </body>   
</html> 