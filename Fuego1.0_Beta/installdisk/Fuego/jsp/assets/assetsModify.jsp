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
				<div class="btn-group">
					<a class="btn btn-large tip-bottom" title="Manage Files"><i class="icon-file"></i></a>
					<a class="btn btn-large tip-bottom" title="Manage Users"><i class="icon-user"></i></a>
					<a class="btn btn-large tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a>
					<a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>
				</div>
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
									<i class="icon-align-justify"></i>									
								</span>
								<h5>修改资产（ID：12312412）</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/AssetsModify.do" method="post" class="form-horizontal" >
									
									<div class="control-group">
										<div class="alert alert-danger">
											<button class="close" data-dismiss="alert">×</button>
											<strong>警告！</strong> 系统初始数据维护。。。。。
										</div>									
									</div>
                                                               
									<div class="control-group">
                                        <label class="control-label">资产编号</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.assetsID" value="${assetsInfo.assets.assetsID}" hidden="true" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">资产名称</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.assetsName" value="${assetsInfo.assets.assetsName}" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">资产分类</label >                                   
										<div class="controls">
                                            <select name="assetsInfo.assets.assetsType">
											 <c:forEach var="type" items="${typeList}"> 
												<option />${type}	
											 </c:forEach>	
											</select>
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">资产来源</label >                                   
										<div class="controls">
                                            <select name="assetsInfo.assets.assetsSRC">
                                             <option >${assetsInfo.assets.assetsSRC}</option>
											 <c:forEach var="src" items="${assetsSrcList}"> 
												<option />${src}	
											 </c:forEach>	
											</select>
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">生产厂家</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.manufacture" value="${assetsInfo.assets.manufacture}" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">规格型号</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.spec" value="${assetsInfo.assets.spec}" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">计量单位</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.unit" value="${assetsInfo.assets.unit}" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">购建日期</label >                                   
										<div class="controls">
                                            <input type="text" data-date="2013-03-04" data-date-format="yyyy-mm-dd" name="assetsInfo.assets.purchaseDate" value="${assetsInfo.assets.purchaseDate}" class="datepicker" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">原值</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.originalValue" value="${assetsInfo.assets.originalValue}" />￥
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">使用年限</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.expectYear" value="${assetsInfo.assets.expectYear}" />
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">使用人</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.duty" value="${assetsInfo.assets.duty}" />
                                        </div>                          
                                    </div>
								
									<div class="control-group">
                                        <label class="control-label">存放地点</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.location" value="${assetsInfo.assets.location}" />
                                        </div>                          
                                    </div>
									
									
									<div class="control-group">
                                        <label class="control-label">技术状态</label >                                   
										<div class="controls">
                                            <select name="assetsInfo.assets.techState">
                                             <option >${assetsInfo.assets.techState}</option>
                                            
											 <c:forEach var="tech" items="${techList}"> 
												<option />${tech}	
											 </c:forEach>	
											</select>
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">其他说明</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.note" value="${assetsInfo.assets.note}" />
                                        </div>                          
                                    </div>
									
									
									
									
									<div class="form-actions">
										<button type="submit" class="btn btn-success"  name ="submit" value="submit">提交</button>
										<button type="submit" class="btn btn-danger"  name ="submit" value="delete">删除</button>
										<button type="submit" class="btn btn-primary"  name ="submit" value="back">返回</button>
										<button type="submit" class="btn btn-primary"  name ="submit" value="cancel">取消</button>
										
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
						2012 &copy; Unicorn Admin. Brought to you by <a href="https://wrapbootstrap.com/user/diablo9983">diablo9983</a>
					</div>
				</div>
			</div>
		</div>
		
		

	</body>
</html>
