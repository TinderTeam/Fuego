<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 	<jsp:include page="/jsp/cbb/includeCSS.jsp"/>
<body>
 
     	<jsp:include page="/jsp/cbb/header.jsp"/>
    
		
		<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">选择资产</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">选择资产</a>				
			</div>
			
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>实物资产选择</h5>
							</div>
							<div class="widget-content nopadding">
								<form id='selectAssetsFrom' action="<%=request.getContextPath()%>/PurchaseAssetsSelect.do" method="post" class="form-horizontal" >
									 
									<div class="control-group">
										<label class="control-label">资产编号</label>
	                                        <div class="controls">
	                                            <input type="text" name="assetsID"  />
	                                        </div>
	                                    <label class="control-label">资产名称</label>
	                                         <div class="controls">
	                                            <input type="text" name="assetsName"  />
	                                        </div>
	                                    <label class="control-label">规格型号</label>
	                                        <div class="controls">
	                                            <input type="text" name="spec"  />
	                                        </div>
	                                    <label class="control-label">构建日期</label>
	                                        <div class="controls">
	                                            <input type="text" name="purchaseDate"  />
	                                        </div>
	                                    <label class="control-label">责任部门</label>
	                                        <div class="controls">
	                                            <input type="text" name="dept"  />
	                                        </div>
	                                    <label class="control-label">责任人员</label>
	                                        <div class="controls">
	                                            <input type="text" name="duty"  />
	                                        </div>
	                                    <label class="control-label">资产分类</label>
	                                        <div class="controls">
	                                            <input type="text" name="assetsType"  />
	                                        </div>
	                                    <label class="control-label">技术状态</label>    
	                                        <div class="controls">
	                                            <input type="text" name="techState"  />
	                                        </div>
										<div class="form-actions">
											<button type="submit" class="btn btn-primary" name="submit" value="search">查询</button>
										</div>
									</div>
								 </form>
								
						
							
							  </div>
						</div>
						<form action="<%=request.getContextPath()%>/PurchaseAssetsSelect.do" method="post"  >
						
						   <div class="widget-box" style="overflow-x:auto;height:500px;overflow-y:auto;">
							  <div class="widget-title">
							
								<h5>选择资产</h5>
							  </div>
							<table class="table table-bordered">
										<thead>
											<tr>
											<th><input type="checkbox"/>选入</th>
											<th>资产名称</th>
											<th>品牌</th>
											<th>规格/参数</th>
											<th>购置数量</th>
											<th>单位</th>
											<th>预算单价</th>
											<th>金额</th>
											<th>说明</th>
											</tr>
										</thead>
										<tbody>
										
											<c:forEach var="item" items= "${purchaseList}">  
											<tr>
							
												<td style="text-align:center">
													<input type="checkbox" name="assetsIDList"  value="${item.index}"/>
												</td>
													<td style="text-align:center" >${item.assetsBo.assetsName}</td>												
													<td style="text-align:center">${item.assetsBo.manufacture}</td>
													<td style="text-align:center">${item.assetsBo.spec}</td>
													<td style="text-align:center">${item.assetsBo.quantity}</td>
													
													<td style="text-align:center" >${item.assetsBo.unit}</td>
													
													<td style="text-align:center">${item.price}</td>
													
													<td style="text-align:center" >${item.money}</td>
													<td >${item.assetsBo.note}</td>
					
											</tr>
												</c:forEach >
										</tbody>
									
									</table>  

						   </div>
						   <button type="submit" class="btn btn-success" name="submit" value="confirm">确认</button>
						   <button type="submit" class="btn btn-primary" name ="submit" value="cancel" >取消</button>
						</form>
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
