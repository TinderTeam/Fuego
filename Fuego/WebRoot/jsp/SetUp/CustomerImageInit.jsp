<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
	<head>
		<title>东莞石油信息平台</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/jquery.gritter.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
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
				<h1>系统数据管理</h1>
		
			</div>
			
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/Index.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>Home</a>
				<a href="#" class="current">系统数据管理</a>
				<a href="<%=request.getContextPath()%>/CustomerInfoSearchIndex.do" class="current">数据标准化管理</a>
			</div>
			<!--内容头------------------------------>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
						
						
						
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>标准化说明</h5>
							</div>
							<div class="widget-content">
								请选择该图片所表示的内容，如果不再下拉菜单里请选择其他，并在文本框中输入名称。
								
							</div>
							
						</div>
					</div>
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon">
								<i class="icon-picture"></i>
							</span>
							<h5>图片预览</h5>
						</div>
						<div class="widget-content">
							<ul class="thumbnails">
								<li class="span8">
									<div class="controls">
										<a href=""><img src=${ImageServer} alt="" ></a>		
										
									</div>
										
								</li>
								<li class="span3">
									<div class="control-group">
										<label class="control-label">选择图片的内容</label>
											<div class="controls">
												<form action="<%=request.getContextPath()%>/CustomerImageSet.do" method="post">
												<label><input type="radio" name="radios" value="组织机构代码证" /> 组织机构代码证</label>
												<label><input type="radio" name="radios" value="营业执照"/> 营业执照</label>
												<label><input type="radio" name="radios" value="税务登记证（国税）"/> 税务登记证（国税）</label>
												<label><input type="radio" name="radios" value="税务登记证（地税）"/> 税务登记证（地税）</label>
												<label><input type="radio" name="radios" value="others"/> 其他内容</label>
												
												<div class="controls">
														<input type="text" name="text"/>
												</div>
												
													<p>
																					
														<button class="btn tip-top" data-original-title="将修改图片名称" type="submit" name="submit" value="save">提交&保存</button>
														<button class="btn" type="submit" name="submit" value="next">下一个</button>													
													</p>
												</form>
											</div>
									</div>
						
								</li>								
							</ul>
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
            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.gritter.min.js"></script>
        <!--   <script src="js/jquery.peity.min.js"></script>   -->
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.interface.js"></script>
	</body>
</html>
