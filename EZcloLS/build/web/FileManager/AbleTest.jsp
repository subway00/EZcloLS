
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    int k = 0;
    int l = 0;
    RequestDispatcher rd = request.getRequestDispatcher("/EZcloLS/AbleTestController");
    rd.include(request, response);
%>
<c:forEach var="testname" items="${ableTest}">
    <tr>
        <td><div class="minus" data-toggle="modal" data-target="#deleteTest<%=k+=1%>"> <b>[</b></div></td>
        <td><div class="testname">${testname}</div></td>
        <td>2019-06-30</td>
        <td>2019-06-30</td>
        <td><div class="operate-button-box">
                <a href="../EditCloze/Edit.html"><button type="button" class="btn btn-outline-success">編輯</button></a> 		  		
                <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#renameTest<%=l+=1%>">命名</button>
                <a href="../Testing/testing.html"><button type="button" class="btn btn-outline-success">測驗</button></a>	  
            </div>
        </td>	
    </tr>

</c:forEach >
