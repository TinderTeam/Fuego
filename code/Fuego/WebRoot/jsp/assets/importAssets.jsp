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
	function submitFuction()
	{
		document.getElementById("subID").click();
	}	
	</script>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    
	<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">实物资产验收</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">实物资产管理</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>实物资产验收上载</h5>
										
								</div>
							
							<div class="widget-content">
								<form action="<%=request.getContextPath()%>/ImportAssets.do" name="UpLoadForm" method="post" enctype="multipart/form-data"  >	
									<div class="form-actions">
											<button type="submit" class="btn btn-primary" name ="submit" value="download">Excel数据模版</button>
											<input type="file" name ="recieveAssetsFile"/>										
											<button id = "subID" type="submit" class="btn btn-inverse" name ="submit" value="upload" style="display:none" >通过Excel表格导入</button>	
											
										
											
											<a href="#myAlert" data-toggle="modal" class="btn btn-success" style="width:150px" onclick="submitFuction()">通过Excel表格导入</a>		
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
