	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
 	<script type="text/javascript" src="jsp/My97DatePicker/WdatePicker.js"></script>
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
				<h1><font  face="微软雅黑">实物资产处置</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">实物资产处置</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<form action="<%=request.getContextPath()%>/DiscardSearchResult.do"  name="UpLoadForm" method="post" enctype="multipart/form-data" >
						<div class="widget-box">
							
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>加油站实物资产处置</h5>
							</div>
							
							<div class="widget-content">
									<div class="control-group">
										<div class="alert alert-info alert-block">
											<a class="close" data-dismiss="alert" href="#">×</a>
											 <strong>说明</strong>"实物资产处置"是由系统根据用户输入的统计截止时间、状态和资产类型进行待处置资产的查询，然后对查询结果进行筛选操作。
										</div>
												
									</div>			
								</div>
							<div class="widget-content nopadding">
								<div class="form-horizontal" >
									<div class="control-group">
                                        <label class="control-label">统计截止日期</label>
										
                                        <div class="controls">
                                            <input type="text" data-date="2013-4-9" name="endDueDate" data-date-format="yyyy-mm-dd" value="${discardSearchBo.date}" onfocus="WdatePicker()" />
                                        </div>
										
                                    </div>
									
									
									<div class="control-group">
										<div class="control-group">
											<label class="control-label">状态</label>					
											<div class="controls">

												<select name="techState"  style="width:100px">
												
												    <option selected=""  >全部</option>	
													<c:forEach var="i" items= "${discardSearchBo.techStatusList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>												
											</div>
										</div>
									</div>	
									
									<div class="control-group">
										<div class="control-group">
											<label class="control-label">资产类型</label>	
												<div class="controls">

													
																									
												<select name="assetsType"  style="width:200px">
												
												    <option selected=""  >全部</option>	
													<c:forEach var="i" items= "${discardSearchBo.assetsTypeList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
										
												</div>
										</div>
									</div>	
								<div class="control-group">
									<div class="control-group">
									 <label class="control-label">经营管理部</label>	
								    	<div class="controls">
									
                                                 <select name="manageName" style="width: 200px"  >
													<c:forEach var="i" items= "${manageDeptList}"> 																								  
												      <c:choose>
														  <c:when test="${searchForm.manageName == i}">
													           <option selected=""  >${searchForm.manageName}</option>	
														  </c:when>
														  <c:otherwise>  
														       <option id="${i}"/>${i}		
													
														  </c:otherwise>
													  </c:choose>																		
													</c:forEach>
												</select>							
									    </div>
							          </div>
							      </div>	
							      	<div class="control-group">	
										<div class="control-group">
										   	<label class="control-label">加油站</label>	
											<div class="controls">
										    
											   <select name="duty" style="width: 200px"  >
													<c:forEach var="i" items= "${deptList}"> 																								  
												      <c:choose>
														  <c:when test="${searchForm.duty == i}">
													           <option selected=""  >${searchForm.duty}</option>	
														  </c:when>
														  <c:otherwise>  
														       <option id="${i}"/>${i}		
													
														  </c:otherwise>
													  </c:choose>																		
													</c:forEach>
												</select>
										     </div>
										  </div>
										</div>
									<div class="widget-content">
										<div class="control-group">
											 <button  type="submit" class="btn btn-success" name="submit" value ="search">查询</button>
		 					 		 
										</div>
									</div>		
								</div>
							</div>
	
						</div>
						 
						<div class="widget-box">
								<div class="widget-title">
									<span class="icon">
										<i class="icon-align-justify"></i>									
									</span>
									<h5>待处置资产查询结果</h5>
								</div>
								
							
								
									<c:set var="assetsPage" value="${assetsPage}" scope="request"/>
									<jsp:include page="/jsp/cbb/assetsList.jsp"/>
										 
									<div class="form-actions">

										<button type="submit" class="btn btn-success" name="submit" value="submit">提交</button>
										<button type="submit" class="btn btn-primary" name="submit" value="back">返回</button>
													
									</div>							
						
						
						</div>
						<div class="widget-box">
						
								<div class="widget-title">
								
									<h5>实物资产处置上载</h5>
										
								</div>
							
							<div class="widget-content">
							

								<div class="form-actions">
										<button type="submit" class="btn btn-primary" name ="submit" value="download">Excel数据模版</button>
										<input type="file" name ="assetsFile"/>										
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

							</div>
						</div>
				
			</form>
					</div>
				</div>
			
			</div>
			<div class="container-fluid">
			
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder.
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
