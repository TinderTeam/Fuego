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
				<h1>Common Form Elements</h1>
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="tip-bottom">Form elements</a>
				<a href="#" class="current">Common elements</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th"></i>
								</span>
								<h5>加油站实物资产报备</h5>							
							</div>
							<div class="widget-content">
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<h4 class="alert-heading">Info!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>
										
								</div>
				
							</div>
						
							<div class="widget-content">
							<form action="<%=request.getContextPath()%>/GasAssetsApply.do"  name="myForm" method="post" >
								<h3>实物资产状态变更</h3>
							
								<div class="control-group">
									<span class="label label-info">实物资产状态变更</span>
								</div>	
								
								<table class="table table-bordered table-striped with-check">
									<thead>
										<tr>							
											
											<th>资产编号</th>
											<th>资产名称</th>
											<th>资产来源</th>								
											<th>生产厂家</th>
											<th>规格型号</th>
											<th>计量单位</th>
											<th>数量</th>
											<th>购建日期</th>
											<th>原值</th>
											<th>规定使用年限</th>								
											<th>到期日</th>
											<th>责任部门</th>
											<th>责任人</th>
											<th>资产分类</th>
											<th>存放地点</th>
											<th>技术状态</th>
											<th>说明</th>
										</tr>
									</thead>
									<tbody>
										
										<tr>
										
											
											<td style="text-align:center">6123513256</td>
											<td style="text-align:center">某资产</td>
											<td style="text-align:center">下发</td>
											<td style="text-align:center">某厂商</td>
											<td style="text-align:center">某型号</td>
											<td style="text-align:center">某单位</td>
											<td style="text-align:center">10</td>
											<td style="text-align:center">2013-03-28</td>
											<td style="text-align:center">￥1000.00</td>
											<td style="text-align:center">5</td>
											<td style="text-align:center">2018-03-28</td>
											<td style="text-align:center">业务部</td>
											<td style="text-align:center">南博文</td>
											<td style="text-align:center">低值易耗品</td>
											<td style="text-align:center">东城经管部</td>
											<td style="text-align:center">
											
											<select>
												<option />正常
												<option />损坏
												<option />超期										
											</select>
											</td>
											<td style="text-align:center"><input type="text" name=""/></td>
											
										</tr>
										
							
									
									</tbody>
								</table>
								<div class="form-actions">
											<button type="submit" class="btn btn-success" name="submit" value="submit1">提交申请</button>
											<button type="submit" class="btn btn-primary" name="submit" value="back">返回</button>
								</div>	
							</form> 
							</div>
						</div>
					<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>加油站实物资产申请</h5>
							</div>
						
							<div class="widget-content">
								<h3>实物资产资产申请</h3>
								<div class="control-group">
									<div class="alert alert-info alert-block">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<h4 class="alert-heading">Info!</h4>
										Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
									</div>
										
								</div>			
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/GasAssetsApply.do" method="post" class="form-horizontal" />
								
									<div class="control-group">
                                        <div class="control-group">
											<label class="control-label">选择资产类型</label>
										
											<div class="controls">
												<select name="type" >
													<logic:iterate id="type"  name= "typeList">    											  
														<option id="${type}"/>${type}										
													</logic:iterate>
												</select>
											</div>
										</div>
									</div>	
									<div class="control-group">
                                        <div class="control-group">
											<label class="control-label">选择资产名称</label>
										
											<div class="controls">
												<select name = "assetName">
													<logic:iterate id="type"  name= "assetsList">    											  
														<option id="${type}"/>${type}										
													</logic:iterate>
												</select>
											</div>
										</div>
									</div>	
									<div class="control-group">
                                        <label class="control-label">申请数量</label>
                                        <div class="controls">
                                            <input type="number" name="number"/>
                                        </div>
                                    </div>
									<div class="control-group">
                                        <label class="control-label">填写申请原因</label>
                                        <div class="controls">
                                            <input type="text" name="reason"/>
                                        </div>
                                    </div>
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name="submit" value ="submit2" >申请送审</button>
									</div>
								</div>
								</form>
							</div>
						</div>
				</div>
				
			</div>
			<div class="container-fluid">
			
				<div class="row-fluid">
					<div id="footer" class="span12">
						2012 &copy; Unicorn Admin. Brought to you by <a href="https://wrapbootstrap.com/user/diablo9983">diablo9983</a>
					</div>
				</div>
			</div>
		</div>
		
		
            
            
	</body>
</html>
