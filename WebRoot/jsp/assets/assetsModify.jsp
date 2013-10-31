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
				<h1><font  face="微软雅黑">资产台账修改</font></h1>
			
			</div>
                
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="current">资产台账修改</a>
			
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
											<strong>警告！</strong>利用此项功能修改或编辑资产将无法保存操作记录，一般只用作管理员数据修正。如需正常新增、处置、调拨资产请用相关权限账户登录并通过给定的功能进行操作。 
										</div>									
									</div>
                                                               
									<div class="control-group">
                                        <label class="control-label">资产编号</label >                                   
										<div class="controls">
                                            <input type="text" name="assetsInfo.assets.assetsID" value="${assetsInfo.assets.assetsID}" disabled="true" />
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
                                            <select name="assetsInfo.assets.assetsType" style="width:150px">
											 <c:forEach var="type" items="${typeList}"> 
												<option />${type}	
											 </c:forEach>	
											</select>
                                        </div>                          
                                    </div>
									
									<div class="control-group">
                                        <label class="control-label">资产来源</label >                                   
										<div class="controls">
                                            <select name="assetsInfo.assets.assetsSRC" style="width:150px">
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
			<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Tinder
					</div>
				</div>
			</div>
		</div>
		
		

	</body>
</html>
