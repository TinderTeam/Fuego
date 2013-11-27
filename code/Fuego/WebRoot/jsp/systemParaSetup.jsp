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
				
                <h1><font  face="微软雅黑">系统参数维护</font></h1>
			</div>
			<div id="breadcrumb">

				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">基础数据维护</a>	
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
								<div class="widget-title">
									<span class="icon">
										<i class="icon-align-justify"></i>									
									</span>
									<h5>加油站用户新增</h5>
								</div>
								<div class="widget-content nopadding">
									
									<form action="<%=request.getContextPath()%>/SystemParaSetup.do" method="post" class="form-horizontal" />																														 
										<div class="control-group">
											 <label class="control-label">油站名称</label >
											<div class="controls"> 
											   	<input type="text" name="gasname"> 
											</div>																                      
										</div>
										
										<div class="control-group">
											<label class="control-label">经管部</label >                                   
											<div class="controls">
										    	<select name="dept1"  style="width:200px">

													<c:forEach var="i" items= "${setupBo.deptList1}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>	

											</div>  
											
										</div>
																	
																		
																			
										<div class="form-actions">
										
											<button type="submit" class="btn btn-primary" name="submit" value="submit1">新增</button>
								
										</div>
									</form>
								</div>
						</div>
				
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>加油站用户维护</h5>
							</div>
							<div class="widget-content nopadding">
								
								<form action="<%=request.getContextPath()%>/SystemParaSetup.do" method="post" class="form-horizontal" />
							
                                         
								
									<div class="control-group">
                                        <label class="control-label">修改加油站账户</label >                                   
										<div class="controls">
                                     		<select name="gasaccount"  style="width:200px">
												 <option selected="" value="${setupBo.currentGas}"/>	${setupBo.currentGas}
													<c:forEach var="i" items= "${setupBo.gasList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
											</select>

											 <button type="submit" class="btn btn-primary" name="submit" value="submit2">查询</button>
											 <button type="submit" class="btn btn-inverse" name="submit" value="submit3">删除</button>
											 
                                        </div>  
										
                                    </div>
									
								
									
									<div class="control-group">
                                        <label class="control-label">经管部</label >                                   
										<div class="controls">
                                     		<select name="dept2"  style="width:200px">
												 <option selected="" value="${setupBo.orignDept}"/>	${setupBo.orignDept}
													<c:forEach var="i" items= "${setupBo.deptList2}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
											</select>										

										    <button type="submit" class="btn btn-success" name="submit" value="submit4">保存</button>
                                        </div>                          
                                    </div>
									
									
								
									
									
								
								</form>
							</div>
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
