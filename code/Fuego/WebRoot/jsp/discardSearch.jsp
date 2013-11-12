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
		function pageChange(page)
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
						<form action="<%=request.getContextPath()%>/DiscardSearchResult.do"  name="myForm2" method="post">
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
										
										
									<div class="widget-content">
										<div class="control-group">
<<<<<<< HEAD
											 <button id ="subID" type="submit" class="btn btn-success" name="submit" value ="submit">查询</button>													
		 											第<select name="pageNum" style = "width:70px" onchange="return pageChange(this.value)" >
													<option id="${searchForm.pageNum}">${searchForm.pageNum}</option>	
													<c:forEach var="i" items= "${assetsPage.page.pages}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
										 </select>页，共${assetsPage.page.count}条记录							 
=======
											 <button id ="pageChange" type="submit" class="btn btn-success" name="submit" value ="search">查询</button>
		 					 		 
>>>>>>> origin/master
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
