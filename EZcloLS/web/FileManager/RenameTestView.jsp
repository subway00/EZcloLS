<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    int k = 0;
    int l = 0;
    RequestDispatcher rd = request.getRequestDispatcher("../RenameTestController");
    rd.include(request, response);
%>
<c:forEach var="testname" items="${displayTest}">
    <tr>
        <td><div class="minus" data-toggle="modal" data-target="#deleteTest<%=k +=1%>"> <b>[</b></div></td>
        <td><div class="testname">${testname.tname}</div></td>
        <td>${testname.tbuildtime}</td>
        <td>${testname.rtesttime}</td>
        <td><div class="operate-button-box">
                <button type="button" class="btn btn-outline-success" onclick="cunstructNewPaper(${testname.tnumber})">編輯</button> 		  		
                <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#renameTest<%=l+=1%>">命名</button>
                <button type="button" class="btn btn-outline-success" onclick="prepareExam(${testname.tnumber})">測驗</button>  
            </div>
        </td>	
    </tr>

</c:forEach >
