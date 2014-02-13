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
				<h1><font  face="微软雅黑">实物资产调拨</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">创建实物资产调拨单</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>创建调拨单</h5>
							</div>
							<div class="widget-content">
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">X</a>
										<h4 class="alert-heading">说明：</h4>
										选择需要进行实物资产调拨的调出、调入单位，点击确认。
									</div>
										
								</div>			
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/Assign.do" method="post"  enctype="multipart/form-data" class="form-horizontal" >
									                                        
                                    <div class="control-group">
										<label class="control-label">调出部门</label>
										<div class="controls">

											<select name="outDept"  style="width:200px">
												
												    <option selected=""  >${systemUser.deptName}</option>	
													<c:forEach var="i" items= "${deptList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
											</select>									
										</div>
									</div>	
                                         <div class="control-group">
										<label class="control-label">调入部门</label>
										<div class="controls">

											<select name="inDept"  style="width:200px">
												
	
													<c:forEach var="i" items= "${inDeptList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
											</select>												
										</div>
									</div>


									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name = "submit" value = "submit">确认</button>
									</div>
								<div class="control-group">
									<div class="form-actions">
											<button type="submit" class="btn btn-primary" name ="submit" value="download">Excel数据模版</button>
											<input type="file" name ="uploadFile"/>										
											<button type="submit" class="btn btn-inverse" name ="submit" value="upload" >批量调拨导入</button>										
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
