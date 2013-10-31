<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered table-striped with-check">
										<thead>
										  <tr>							
												 <th>事务号</th>
												 <th>事务名</th>
												 <th>发起时间</th>
												 <th>发起人</th>
												 <th>当前处理人</th>
											     <th>当前状态</th>
										  </tr>
										</thead>
										<tbody>
										  <tr>	
											  <td style="text-align:center">${transInfo.transID}</td>
											  <td style="text-align:center">${transInfo.transName}</td>
											  <td style="text-align:center">${transInfo.createTime}</td>
											  <td style="text-align:center">${transInfo.createUser}</td>
											  <td style="text-align:center">${transInfo.handleUser}</td>
											  <td style="text-align:center">${transInfo.state}</td>
							 
										  </tr>
										 </tbody>
									</table>
 
