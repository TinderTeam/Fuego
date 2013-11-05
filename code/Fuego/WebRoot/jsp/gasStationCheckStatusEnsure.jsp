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
				<h1><font  face="微软雅黑">盘点确认</font></h1>

			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">盘点管理</a>
				<a href="#" class="current">盘点情况查看</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>加油站盘点确认</h5>							
							</div>
							
						
							<div class="widget-content">
								
							
									<div class="control-group">
										<span class="label label-info">${statusEnsureInitBo.title}</span>
									</div>	
	
								 
									<form action="<%=request.getContextPath()%>/GasStationCheckStatusEnsure.do?transID=${checkPlan.transInfo.transInfo.transID}" method="post" >
										<c:set var="transInfo" value="${checkPlan.transInfo.transInfo}" scope="request"/>
										<jsp:include page="/jsp/cbb/transaction.jsp"/>
											
										<c:set var="assetsPage" value="${checkPlan.planInfo.assetsPage}" scope="request"/>
										<jsp:include page="/jsp/cbb/assetsList.jsp"/>
										<div class="form-actions">
											  <button class="btn btn-success"  name="submit" value="confirm">确定</button>
											  <button class="btn  btn-inverse" name="submit" value="cancel">取消</button>
											  <a href="#myAlert" data-toggle="modal" class="btn btn-success" style="width:100px">新增盘点资产</a>
									 

							            </div>
								    </form>
								    <form action="<%=request.getContextPath()%>/GasStationCheckStatusEnsureInit.do?transID=${checkPlan.transInfo.transInfo.transID}" method="post" >
								    
								    <div id="myAlert" class="modal hide">
										<div class="modal-header">
											<button data-dismiss="modal" class="close" type="button">×</button>
											<h3>新增盘点资产</h3>
										</div>
										<div class="modal-body">
											<div class="control-group">
												<label class="control-label">*请输入资产名称</label>
												<div class="controls">
													<input type="text" name="assetsInfo.assets.assetsName" value=""/>
												</div>
												<label class="control-label">请输入生产厂家</label>
												<div class="controls">
													<input type="text" name="assetsInfo.assets.manufacture" value=""/>
												</div>
													<label class="control-label">请输入资产规格</label>
												<div class="controls">
													<input type="text" name="assetsInfo.assets.spec" value=""/>
												</div>
											
											</div>										
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-success"  name ="submit" value="addNew">确定</button>
											
											<a data-dismiss="modal" class="btn btn-inverse" href="#">取消</a>
										</div>
								       </div>	
								      </form>
							
						 
						</div>
			</div>
			<div class="container-fluid">
			
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013 Copyright Reserved by Tinder
					</div>
				</div>
			</div>
		</div>
		 </div>
         </div>
	</body>
</html>
