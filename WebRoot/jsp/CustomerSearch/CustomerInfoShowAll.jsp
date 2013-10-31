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
		<link rel="stylesheet" href="css/uniform.css" />
		<link rel="stylesheet" href="css/select2.css" />		


		
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
	
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>客户基本资料</h5>
							</div>
							<div class="widget-content">客户维护人:${customer.customerManager}</div>
							<div class="widget-content">编号:${customer.customerIndex}</div>
							<div class="widget-content">客户名称:${customer.customerName}</div>
							<div class="widget-content">客户编号:${customer.customerID}</div>
							
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>证照资料</h5>
							</div>							
							<div class="widget-content">营业执照:${customer.businessLicenseID}</div>
							<div class="widget-content">营业执照有效期限:${customer.businessLicenseTimeLine}</div>
							<div class="widget-content">最近年审日期:${customer.businessLicenseCheckDate}</div>
							<div class="widget-content">税务登记证（地税）:${customer.taxLiceseID_Local}</div>
							<div class="widget-content">税务登记证（国税）:${taxLiceseID_Country}</div>
							<div class="widget-content">组织机构代码证:${customer.ORCID}</div>
							<div class="widget-content">证照有效期限:${customer.ORCIDTimeLine}</div>
							<div class="widget-content">最近年审日期:${customer.ORCIDCheckDate}</div>
							<div class="widget-content">增值税一般纳税人证明:${customer.generalTaxpayerLicense}</div>


							<div class="widget-title">
								<span class="icon">
									<i class="icon-picture"></i>
								</span>
								<h5>证照扫描件</h5>
							</div>
							<div class="widget-content">
								<ul class="thumbnails">
									
									<logic:iterate id="imgpath" name= "imgpath">   
										<li class="span5">						
											<a href="" target="_blank"><img src='${imgpath}' alt="" width="300" height="300"></a>		
										</li>	
									</logic:iterate>																			
																
								</ul>
							</div>

							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>赊销资料</h5>
							</div>
										
							<div class="widget-content">信用额度（万元）:${customer.criditLimit}</div>
							<div class="widget-content">法人代表的身份证:${customer.corporateRepresentativeID}</div>
							<div class="widget-content">经办人的身份证:${customer. operatorID}</div>
							<div class="widget-content">授权委托书:${customer.authorityLetter}</div>
							<div class="widget-content">委托书有效期限:${customer.authorityLetterTimeLine}</div>
							<div class="widget-content">近三年财务数据情况:${customer.financialData}</div>
							<div class="widget-content">赊销申请书:${customer.creditApply}</div>
							<div class="widget-content">客户关于用油机、具的清单及说明:${customer.useInfo}</div>
							<div class="widget-content">对外担保及资产抵押情况的书面说明:${customer.undertakingInfo}</div>
							<div class="widget-content">赊销合同编号:${customer.contractNO}</div>
							
							
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>会员卡资料</h5>
							</div>
							<div class="widget-content">电子提油卡处理业务申请表:${customer.eCardApply}</div>
							<div class="widget-content">会员客户使用提油卡协议:${customer.membershipAgreement}</div>
							<div class="widget-content">会员提油卡交接确认书:${customer.membershipDeliveryReceitp}</div>
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
			 <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/unicorn.js"></script>
	</body>
</html>
