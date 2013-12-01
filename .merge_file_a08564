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
    
 
	<form action="<%=request.getContextPath()%>/PurchasePlanCreate.do" name="UpLoadForm" method="post" enctype="multipart/form-data"  >	
		
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
				
					
					
						<div class="widget-box" >
							<div class="widget-title">
							
								<h5>定制采购计划</h5>
							</div>
							<div class="widget-content nopadding" style="overflow-x:auto;height:500px;overflow-y:auto;" >					
								<table class="table table-bordered " >
									<thead>
									<tr>
										<th >序号</th>
										<th >资产名称</th>
										<th >资产型号（生产厂家）</th>
										<th width="120px">规格型号</th>
										<th>部门</th>
										<th >单位</th>
										<th >数量</th>									
										<th >单价</th>	
												
										<th width="25%">备注</th>
									</tr>
								</thead>								
								<tbody>
									
									
									<logic:iterate id="i" name= "refList" >    
											  <tr>
												<td style="text-align:center" ><label style="width:30px" name="assetsCreateBo[${i.index-1}].index" value="${i.index}">${i.index}</label></td>
												
												<td style="text-align:center" >
													<input  style="width:120px" type="hidden" name="assetsCreateBo[${i.index-1}].assetsBo.assetsName"  value="${i.assetsBo.assetsName}" hidden="true"/>${i.assetsBo.assetsName}
													
												</td>
											
												<td style="text-align:center" >
													<input style="width:120px" type="text" name="assetsCreateBo[${i.index-1}].assetsBo.manufacture"  value="${i.assetsBo.manufacture}"/>
												</td>
										     
												<td style="text-align:center">
													<input style="width:100px" type="text" value="${i.assetsBo.spec}" name="assetsCreateBo[${i.index-1}].assetsBo.spec" />
											    <td style="text-align:center">
													<input style="width:100px" type="text" value="${i.assetsBo.duty}" name="assetsCreateBo[${i.index-1}].assetsBo.duty" />
												</td>
													
												</td>
													
												<td style="text-align:center">
													<input style="width:30px" type="text" value="${i.assetsBo.unit}" name="assetsCreateBo[${i.index-1}].assetsBo.unit"/>
												
												</td>	
												
												<td style="text-align:center">
													<input style="width:50px" type="number"  value="${i.assetsBo.quantity}"   name="assetsCreateBo[${i.index-1}].assetsBo.quantity" />
												
												</td>
												
												<td style="text-align:center">
													<input style="width:50px" type="text"  value="${i.price}"   name="assetsCreateBo[${i.index-1}].price" />
												
												</td>
												
											
																				
												<td style="text-align:center"> 
													<input style="width:200px" type="text" value="${i.assetsBo.note} " name="assetsCreateBo[${i.index-1}].assetsBo.note"	/>									
												</td>																								
									
											
											 </tr>
										</logic:iterate>  
										
									
								
								</tbody>
									</table>  
							</div>
						</div>
							<a href="#myAlert" data-toggle="modal" class="btn btn-primary" style="width:60px">新建资产</a>
 							
							
								<div id="myAlert" class="modal hide">
										<div class="modal-header">
											<button data-dismiss="modal" class="close" type="button">×</button>
											<h3>创建新资产</h3>
										</div>
										<div class="modal-body">
											<div class="control-group">
												<label class="control-label">请输入资产名称</label>
												<div class="controls">
													<input type="text" name="newAssetsName"/>
												</div>
											</div>
				
										</div>
										<div class="modal-footer">
												
											<button type="submit" class="btn btn-primary" name ="submit" value="addNew">创建资产</button>
											<a data-dismiss="modal" class="btn" href="#">取消</a>
										</div>
									</div>
							
		
					</div>
				</div>
				
				<div class="widget-box">
					
					

								<div class="widget-content">
								
									<button   type="submit"  class="btn btn-success"   name ="submit" value="submit">提交</button>								
									<button  type="submit" class="btn  btn-primary" name ="submit" value="back">返回</button>	
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
