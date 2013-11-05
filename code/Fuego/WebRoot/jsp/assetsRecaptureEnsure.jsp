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
    
		
            
		
	<form action="<%=request.getContextPath()%>/AssetsRecaptureEnsure.do" method="post" />
	

	<div id="content">
			<div id="content-header">
				<h1><font  face="微软雅黑">实物资产回收</font></h1>
				
			</div>
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i>主页</a>
				<a href="#" class="tip-bottom">实物资产回收确认</a>				
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
						
							
							<div class="widget-title">
							
								<h5>实物资产回收确认</h5>
									
							</div>
							
								
								<c:set var="assetsPage" value="${recapturePlan.assetsPage}" scope="request"/>
	                                <jsp:include page="/jsp/cbb/assetsList.jsp"/>
									
								<div class="form-actions">
								 	 <c:if test="${'create' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="submit">提交</button>								
										<button class="btn  btn-primary" name="submit" value="download">导出</button>
										<a class="btn btn-primary" href="javascript:history.go(-1);">返回</a>
										<button class="btn  btn-inverse" name="submit" value="cancel">取消</button>
									 </c:if>
									 <c:if test="${'approval' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="agree">同意</button>								
										<button class="btn  btn-primary" name="submit" value="refuse">拒绝</button>	
	 								 </c:if>
	 								  <c:if test="${'confirm' == pageDisCtr}"> 
										<button class="btn btn-success"  name="submit" value="confirm">确定</button>								
 	 								 </c:if>
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
	</form>
            
          
	</body>
</html>
