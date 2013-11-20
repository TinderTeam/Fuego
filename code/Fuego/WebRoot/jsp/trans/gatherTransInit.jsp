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
				<h1><font  face="微软雅黑">创建资产采购计划</font></h1>
				
				
			</div>
			
			<div id="breadcrumb">
				<a href="<%=request.getContextPath()%>/IndexInit.do" title="返回主页" class="tip-bottom"><i class="icon-home"></i> 主页</a>
				<a href="#" class="tip-bottom">统计事务</a>				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>统计事务</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="<%=request.getContextPath()%>/GatherTrans.do"  name="myForm" method="post" class="form-horizontal">	
						
									<div class="control-group">
										<div class="alert alert-info">
											<button class="close" data-dismiss="alert">×</button>
											<strong>说明</strong>"实物资产采购参考计划"是由系统根据现有实物资产的购置日期及使用期限,自动筛选出到期需采购的资产。并由管理员在此基础上生成采购计划。
										</div>
									
									</div>
 
                         
                                    <div class="control-group">
                                        <label class="control-label">事务类型</label>
                                        <div class="controls">
 											<td style="text-align:center">	
												<select name="transName"  style="width:200px">
												
 													<c:forEach var="i" items= "${transNameList}"> 																								  
														<option id="${i}"/>${i}																							
													</c:forEach>
												</select>
											</td>
										</div>
                                    </div>
								                  
                                    <div class="control-group">
                                        <label class="control-label">事务完成起始时间</label>
                                        <div class="controls">
                                            <input  type="text"  data-date="" data-date-format="yyyy-mm-dd" value="${transFilter.firstEndTime}" onfocus="WdatePicker()" name="firstEndTime" />

                                         </div>
                                    </div>
                                     <div class="control-group">
                                        <label class="control-label">事务完成截止时间</label>
                                        <div class="controls">
                                            <input  type="text"  data-date="" data-date-format="yyyy-mm-dd" value="${transFilter.lastEndTime}" onfocus="WdatePicker()" name="lastEndTime" />
                                        
                                         </div>
                                    </div>
									
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" name="submit" value="confirm">确定</button>
										<button type="submit" class="btn btn-primary" name="submit" value="cancel">取消</button>
										
									</div>
								</form>
							</div>
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
