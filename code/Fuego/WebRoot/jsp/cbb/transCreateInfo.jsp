<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="control-group">
     <label class="control-label">审批人</label >                                   
			 <div class="controls">
            <select name="handleUser"  style="width:100px">
			<c:if test="${'systemUser.role' == GASSTATION}">
				<c:forEach var="i" items= "${deptList}"> 																								  
 
				       <option id="${i}"/>${i}		
	 																				
			    </c:forEach>
			</c:if>
		</select>	
             </div>                          
</div>
 
 
