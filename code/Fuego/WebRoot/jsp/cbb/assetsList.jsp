<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="widget-content nopadding" style="overflow-x:auto;height:500px;overflow-y:auto;" >					
	<table class="table table-bordered " >
	   <thead>
		  <tr>
			<c:if test="${true == assetsPage.showCheckBox}"> 
			  <th>选择</th>
			</c:if>
			<th>资产编号</th>
			<th>资产名称</th>
			<th>资产来源</th>								
			<th>生产厂家</th>
			<th>规格型号</th>
			<th>单位</th>
			<th>数量</th>
			<th>购建日期</th>
			<th>原值</th>
			<th>规定使用年限</th>								
			<th>到期日</th>
			<th>责任部门</th>
			<th>责任人</th>
			<th>资产分类</th>
			<th>资产性质</th>
			<th>存放地点</th>
			<th>技术状态</th>
			<th>最后盘点日期</th>

			
			<c:if test="${true == assetsPage.showCheckState}"> 
			  <th>盘点状态</th>
			  <th>盘点数量</th>
			</c:if>
			<c:if test="${true == assetsPage.showReceiveState}"> 
			  <th>验收状态</th>
			</c:if>
	        <th>备注</th>
	
			<c:if test="${true == assetsPage.showModifyBtn}"> 
			  <th>修改</th>
			</c:if>
			</tr>
	   </thead>
       <tbody>
         <c:forEach var="assetsInfo" varStatus="iterStatus" items="${assetsPage.assetsList}"> 
	      <tr>
	        
	       	<c:if test="${true == assetsPage.showCheckBox}"> 
	          <td><input type="checkbox"  name="assetsIDList" value="${assetsInfo.assets.assetsID}" style="width:40px"></td>
	        </c:if>  
                <td style="text-align:center"><label style="width:150px">${assetsInfo.assets.assetsID}</label><input style="width:120px; display:none"  name="assetsList[${iterStatus.index}].assets.assetsID" value="${assetsInfo.assets.assetsID}"></input></td>	        <td style="text-align:center"><input style="width:120px" name="assetsList[${iterStatus.index}].assets.assetsName" value="${assetsInfo.assets.assetsName}"></td>
	        <td style="text-align:center"><label style="width:150px">${assetsInfo.assets.manufacture}</label><input style="width:120px; display:none"  name="assetsList[${iterStatus.index}].assets.manufacture" value="${assetsInfo.assets.manufacture}"></td>
	        <td style="text-align:center"><label style="width:150px">${assetsInfo.assets.spec}</label><input style="width:120px; display:none" name="assetsList[${iterStatus.index}].assets.spec" value="${assetsInfo.assets.spec}"></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.assetsSRC}</label></td>
	        
	        <td style="text-align:center"><label style="width:40px">${assetsInfo.assets.unit}</label></td>
	        <td style="text-align:center"><label style="width:60px">${assetsInfo.assets.quantity}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.purchaseDate}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.originalValue}</label></td>
	        <td style="text-align:center"><label style="width:60px">${assetsInfo.assets.expectYear}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.dueDate}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.dept}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.duty}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.assetsType}</label></td>
			 <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.attrType}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.location}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.techState}</label></td>
	        <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.checkDate}</label></td>
	        <c:if test="${false == assetsPage.showNote}"> 
			  <td style="text-align:center"><label style="width:80px">${assetsInfo.assets.note}</label></td>
			</c:if>

			<c:if test="${true == assetsPage.showCheckState}"> 
			  <td style="text-align:center"> 
			  	<select name="assetsList[${iterStatus.index}].extAttr.checkState" style="width: 200px"  >
							 <option>${assetsInfo.extAttr.checkState}</option>
							 <option>未盘点</option>
							 <option>已盘点</option>	
				</select>
			    <td style="text-align:center">
			      <input style="width:80px" type="text" value="${assetsInfo.extAttr.checkCnt}" name="assetsList[${iterStatus.index}].extAttr.checkCnt" />
			    </td>	
	 
			</c:if>
			<c:if test="${true == assetsPage.showReceiveState}"> 
			  <td style="text-align:center">
			   <select name="assetsList[${iterStatus.index}].extAttr.receiveState" style="width: 200px"  >
							 <option>${assetsInfo.extAttr.receiveState}</option>
							 <option>未验收</option>
							 <option>已验收</option>	
				</select>
			  </td>
 			</c:if>
			<c:if test="${true == assetsPage.showNote}"> 
			   <td style="text-align:center">
			    <input style="width:80px" type="text" value="${assetsInfo.extAttr.note}" name="assetsList[${iterStatus.index}].extAttr.note" />
			  </td>
 			</c:if>
	        <c:if test="${true == assetsPage.showModifyBtn}"> 
			  <td style="text-align:center"> <a href="<%=request.getContextPath()%>/AssetsModifyInit.do?assetsID=${assetsInfo.assets.assetsID}">修改</a></td>
			</c:if>
	   </tr>		
         </c:forEach>
       </tbody>
     </table>  
</div>

 
