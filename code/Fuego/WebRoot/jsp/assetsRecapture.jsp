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
    
		
            
		
	<form action="<%=request.getContextPath()%>/AssetsRecapture.do" method="post" class="form-horizontal" />
	

		<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">实物资产回收</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">实物资产回收筛选</a>				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>实物资产回收</h5>
							</div>
							<div class="widget-content">
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<h4 class="alert-heading"><strong>说明</strong></h4>
										“实物资产采购参考计划”是由系统根据现有需要进行回收的加油站及资产类型,自动筛选出到期需回收的资产。
									</div>
										
								</div>			
							</div>
							<div class="widget-content nopadding">
								
									<div class="control-group">
                                        <div class="control-group">
											<label class="control-label">选择需要进行回收的加油站</label>
										
											<div class="controls">
											<select name="gasName" style="width:200px">									
													<c:forEach var="i" items= "${recaptureSearch.gasNameList}"> 	
																																				  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
										    </select>
											</div>
										</div>
									</div>	
								<div class="control-group">
                                      
                                                               
                                    <div class="control-group">
										<label class="control-label">选择实物资产类型</label>
										<div class="controls">
											<select name="assetsType" style="width:120px">	
													<option id="全部" value = "全部"/>	全部												
													<c:forEach var="i" items= "${recaptureSearch.assetsTypeList}"> 				
																																  
														<option id="${i}" value = "${i}"/>${i}																							
													</c:forEach>
										    </select>
										</div>
									</div>
									
									<div class="control-group">
											 <label class="control-label">请输入回收存放地址</label >
											<div class="controls"> 
											   	<input type="text" name="location"> 
											</div>
									</div>
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary">下一步</button>
									
									</div>
								</div>
								
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
		
		
	</form>       
            
	</body>
</html>
