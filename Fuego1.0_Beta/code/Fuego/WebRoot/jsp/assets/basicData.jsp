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
				<h1><font  face="微软雅黑">基础数据导入</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">基础数据导入</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>基础数据上载</h5>
										
								</div>
							
							<div class="widget-content">
								<form action="<%=request.getContextPath()%>/ImportAssets.do" name="UpLoadForm" method="post" enctype="multipart/form-data"  >	

								<div class="form-actions">
										<button type="submit" class="btn btn-primary" name ="submit" value="download">Excel数据模版</button>
										<input type="file" name ="assetsFile"/>
										<button type="submit" class="btn btn-inverse" name ="submit" value="basic_upload" >通过Excel表格导入</button>
 
								</div>	
								</form>
						
							</div>
						</div>
			        </div>
			   </div>
			   
			   <div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
		    </div>
		</div>
	 
					
 
     
	</body>
</html>
