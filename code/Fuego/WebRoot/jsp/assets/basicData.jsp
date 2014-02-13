<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
	<script type="text/javascript">
	function initSubmitFuction()
	{
		document.getElementById("initSubID").click();
	}	
	
	
	function addSubmitFuction()
	{
		document.getElementById("addSubID").click();
	}
		function 	updateSubmitFuction()
	{
		document.getElementById("updateSubID").click();
	}
	function 	deleteSubmitFuction()
	{
		document.getElementById("deleteSubID").click();
	}

	</script>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    <form action="<%=request.getContextPath()%>/ImportAssets.do" name="UpLoadForm" method="post" enctype="multipart/form-data"  >	
	
	<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">台账数据维护</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">台账数据维护</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>初始化数据上载</h5>
										
								</div>
							
							<div class="widget-content">
								

								<div class="form-actions">
										<button type="submit" class="btn btn-primary" name ="submit" value="initDownload">Excel数据模版</button>
										<input type="file" name ="initAssetsFile"/>
										<button id = "initSubID" type="submit" class="btn btn-inverse" name ="submit" value="basic_upload" style="display:none" >通过Excel表格导入</button>	
										
									
										
										<a href="#myAlert" data-toggle="modal" class="btn btn-success" style="width:150px" onclick="initSubmitFuction()">通过Excel表格导入</a>		
											<div id="myAlert" class="modal hide">
												<div class="modal-header">													
													<h3>正在导入数据...</h3>
												</div>
												<div class="modal-body">
													<div class="span12 center" style="text-align: center;">	
														<img border="0" alt="请等待" src="<%=request.getContextPath()%>/img/loading.gif" style="center"/></a>	
															</div>
												</div>											
											</div>

								</div>	
							
						
							</div>
						</div>
					
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>资产台账追加</h5>
										
								</div>
							
							<div class="widget-content">
							
								<div class="form-actions">
										<button type="submit" class="btn btn-primary" name ="submit" value="addDownload">Excel数据模版</button>
										<input type="file" name ="addAssetsFile"/>
										<button id = "addSubID" type="submit" class="btn btn-inverse" name ="submit" value="add_upload" style="display:none" >通过Excel表格导入</button>	
										
									
										
										<a href="#myAlert" data-toggle="modal" class="btn btn-success" style="width:150px" onclick="addSubmitFuction()">通过Excel表格导入</a>		
											<div id="myAlert" class="modal hide">
												<div class="modal-header">													
													<h3>正在导入数据...</h3>
												</div>
												<div class="modal-body">
													<div class="span12 center" style="text-align: center;">	
														<img border="0" alt="请等待" src="<%=request.getContextPath()%>/img/loading.gif" style="center"/></a>	
															</div>
												</div>											
											</div>

								</div>	
								
							</div>
						</div>
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>资产台账修改</h5>
										
								</div>
							
							<div class="widget-content">
							
								<div class="form-actions">
										<button type="submit" class="btn btn-primary" name ="submit" value="updateDownload">Excel数据模版</button>
										<input type="file" name ="updateAssetsFile"/>
										<button id = "updateSubID" type="submit" class="btn btn-inverse" name ="submit" value="update_upload" style="display:none" >通过Excel表格导入</button>	
										
									
										
										<a href="#myAlert" data-toggle="modal" class="btn btn-success" style="width:150px" onclick="updateSubmitFuction()">通过Excel表格导入</a>		
											<div id="myAlert" class="modal hide">
												<div class="modal-header">													
													<h3>正在导入数据...</h3>
												</div>
												<div class="modal-body">
													<div class="span12 center" style="text-align: center;">	
														<img border="0" alt="请等待" src="<%=request.getContextPath()%>/img/loading.gif" style="center"/></a>	
															</div>
												</div>											
											</div>

								</div>	
								
							</div>
						</div>
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>资产台账删除</h5>
										
								</div>
							
							<div class="widget-content">
							
								<div class="form-actions">
										<button type="submit" class="btn btn-primary" name ="submit" value="deleteDownload">Excel数据模版</button>
										<input type="file" name ="deleteAssetsFile"/>
										<button id = "deleteSubID" type="submit" class="btn btn-inverse" name ="submit" value="delete_upload" style="display:none" >通过Excel表格导入</button>	
										
									
										
										<a href="#myAlert" data-toggle="modal" class="btn btn-success" style="width:150px" onclick="deleteSubmitFuction()">通过Excel表格导入</a>		
											<div id="myAlert" class="modal hide">
												<div class="modal-header">													
													<h3>正在导入数据...</h3>
												</div>
												<div class="modal-body">
													<div class="span12 center" style="text-align: center;">	
														<img border="0" alt="请等待" src="<%=request.getContextPath()%>/img/loading.gif" style="center"/></a>	
															</div>
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
