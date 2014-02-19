<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
     <script type="text/javascript" src="jsp/My97DatePicker/WdatePicker.js"></script>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    
		
            
		
		

		
		<div id="content">

			<div id="content-header">
				<h1><font  face="微软雅黑">创建资产采购计划</font></h1>
				
				
			</div>
			
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">创建资产采购计划</a>				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>实物资产采购参考计划生成</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/PurchasePlan.do"  name="myForm" method="post" class="form-horizontal">	
						
									<div class="control-group">
										<div class="alert alert-info">
											<button class="close" data-dismiss="alert">×</button>
											<strong>说明</strong>"实物资产采购参考计划"是由系统根据现有实物资产的购置日期及使用期限,自动筛选出到期需采购的资产。并由管理员在此基础上生成采购计划。
										</div>
									
									</div>

		
                                    <div class="control-group">
                                        <label class="control-label">部门</label>
                                        <div class="controls">
																							
											<td style="text-align:center">	
												<select name="duty"  style="width:200px">
												
												    <option selected=""  >全部</option>	
													<c:forEach var="i" items= "${deptList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
										</div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">经营管理部</label>
                                        <div class="controls">
																							
											<td style="text-align:center">	
												<select name="manageName"  style="width:200px">
												
												    <option selected=""  >全部</option>	
													<c:forEach var="i" items= "${manageDeptList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
										</div>
                                    </div>
									<div class="control-group">
										<label class="control-label">实物资产类型</label>
										<div class="controls">
																							
											<td style="text-align:center">	
												<select name="typeList"  style="width:200px">
												
												    <option selected=""  >全部</option>	
													<c:forEach var="i" items= "${typeList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">技术状态</label>
										<div class="controls">
											<label><input type="checkbox" name="state1" /> 正常</label>
											<label><input type="checkbox" name="state2" /> 故障</label>
											<label><input type="checkbox" name="state3" /> 闲置</label>
											<label><input type="checkbox" name="state4" /> 待报废</label>
										</div>
									</div>                                                               
                                    <div class="control-group">
                                        <label class="control-label">采购计划截止时间选择</label>
                                        <div class="controls">
                                            <input id="dateInput" type="text" data-date="2013-03-04" data-date-format="yyyy-mm-dd" value="${today}" onfocus="WdatePicker()" name="date"/>
                                        </div>
                                    </div>
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name="submit" value="submit1">生成采购计划参考</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
 
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<div class="widget-box">
								<div class="widget-title">
									<span class="icon">
										<i class="icon-align-justify"></i>									
									</span>
									<h5>导入采购计划</h5>
								</div>
								<form action="<%=request.getContextPath()%>/PurchasePlanCreate.do" name="UpLoadForm" method="post" enctype="multipart/form-data"  >	
								  <div class="widget-content">
									<button  type="submit" class="btn  btn-primary" name ="submit" value="download" >下载模版文件</button>	
									<input type="file" name ="myFile"/>
									<button type="submit" class="btn btn-inverse" name ="submit" value="upload" >通过Excel表格导入</button>
											
								  </div>
								</form>
							</div>	
						</div>	
					</div>	
				</div>				
			<div class="container-fluid">
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
 
	
			</div>
		</div>
 


	</body>
</html>
