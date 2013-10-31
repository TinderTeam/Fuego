<%@ page language="java" contentType="text/html;charset=UTF-8" 
	pageEncoding="UTF-8" isELIgnored="false" %>
	<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
	<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
	<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>东莞石油信息平台</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />	
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/uniform.css" />
		<link rel="stylesheet" href="css/select2.css" />		
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />	
	<meta/></head>
	<body>
		
		<!--公用部分------------------------------>
		
		<div id="header">
			<h1><a href="">东莞石油信息平台</a></h1>		
		</div>

		<div id="sidebar">
			<a href="#" class="visible-phone"><i class="icon icon-home"></i>公告栏</a>
			<ul>
				<li class="active"><a href="<%=request.getContextPath()%>/Index.do"><i class="icon icon-home"></i> <span>公告栏</span></a></li>
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i> <span>客户资料管理</span> <!--数量标签---><span class="label">1</span></a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/CustomerInfoSearchIndex.do">客户资料查询</a></li>
						<!--  隐藏 <li><a href="">客户资料新增</a></li>  --->
					</ul>
				</li>
				<li><a href="<%=request.getContextPath()%>/JSP/SystemSetUp.jsp"><i class="icon icon-pencil"></i> <span>系统数据管理</span></a></li>
				
			</ul>
		</div>
		
		<!--公用部分------------------------------>
		
		
		
	
		<div id="content">
		
			<!--内容头------------------------------>
			
			<div id="content-header">
				<h1>客户资料查询</h1>
		
			</div>
			
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/Index.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>Home</a>
				<a href="#" class="current">客户资料管理</a>
				<a href="<%=request.getContextPath()%>/CustomerInfoSearchIndex.do" class="current">客户资料查询</a>
			</div>
			<!--内容头------------------------------>
			
			
		<form action="<%=request.getContextPath()%>/CustomerInfoSearchByAdmin.do" method="post" class="form-horizontal" >
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="row-fluid">
								<div class="span12">
									<div class="widget-box">
										<div class="widget-title">
											<span class="icon">
												<i class="icon-th-list"></i>
											</span>
											<h5>消息</h5>
										</div>
										<div class="widget-content">
											${basicNotice}
										</div>
									</div>
								</div>
							</div>
						
						
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>客户名称查询</h5>
							</div>
							<div class="widget-content">
								通过客户名称关键字查询客户
							</div>
			
									<div class="control-group">
										<label class="control-label">客户名称关键字</label>
										<div class="controls">
											<input type="text" name="keyword"/>
											<button type="submit" name="submit" value="search" class="btn btn-primary">查询</button>
										</div>
									</div>
							
							<div class="widget-box">
							
							</div>
							
							
								
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>客户经理查询</h5>
							</div>
							<div class="widget-content">
								通过经营部及客户经理查询客户
									 
              						<h5>${ManagerGroup_0.groupName}</h5>
									
										<logic:iterate id="Manager_0" name= "Manager_0">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_0.managerName}>${Manager_0.managerName}</button>
							
										</logic:iterate>
									<h5>${ManagerGroup_1.groupName}</h5>
									
										<logic:iterate id="Manager_1" name= "Manager_1">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_1.managerName}>${Manager_1.managerName}</button>
										</logic:iterate>
										
									<h5>${ManagerGroup_2.groupName}</h5>
									
										<logic:iterate id="Manager_2" name= "Manager_2">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_2.managerName}>${Manager_2.managerName}</button>
										</logic:iterate>
										
									<h5>${ManagerGroup_3.groupName}</h5>
									
										<logic:iterate id="Manager_3" name= "Manager_3">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_3.managerName}>${Manager_3.managerName}</button>
										</logic:iterate>
									<h5>${ManagerGroup_4.groupName}</h5>
									
										<logic:iterate id="Manager_4" name= "Manager_4">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_4.managerName}>${Manager_4.managerName}</button>
										</logic:iterate>
									<h5>${ManagerGroup_5.groupName}</h5>
									
										<logic:iterate id="Manager_5" name= "Manager_5">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_5.managerName}>${Manager_5.managerName}</button>
										</logic:iterate>
									<h5>${ManagerGroup_6.groupName}</h5>
									
										<logic:iterate id="Manager_6" name= "Manager_6">    
											<button class="btn btn-success" type="submit" name="manager_submit" value=${Manager_6.managerName}>${Manager_6.managerName}</button>
										</logic:iterate>
          													
							</div>
							
						</div>
					</div>
			
		
				</div>
				<div class="row-fluid">
					<div id="footer" class="span12">
						2013  Copyright Reserved by Nan Bowen
					</div>
				</div>
			</div>
		
			</form>
		</div>

            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/unicorn.js"></script>
			 <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.uniform.js"></script>
            <script src="js/select2.min.js"></script>
            <script src="js/jquery.dataTables.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.tables.js"></script>
	</body>
</html>
