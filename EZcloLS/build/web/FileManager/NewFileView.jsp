
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    int i = 0;
    int j = 0;

    RequestDispatcher rd = request.getRequestDispatcher("../NewFileController");
    rd.include(request, response);
%>
<c:forEach var="filename" items="${map}">
    <div class="file card text- white bg-secondary text-right" id="defaultfile">
        <div class="card-header">
            <p>${filename.value}</p>
            <div class="file-drop dropdown">
                <button class="file-edit btn btn-secondary dropdown-toggle
                        " type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="choosefile"> </button>
                <div class="dropdown-menu deletefilebtn" aria-labelledby="dropdownMenuButton"> <a class="dropdown-item rename" data-toggle="modal" data-target="renameindex<%=i += 1%>">重新命名</a> <a class="dropdown-item delete"  data-toggle="modal" data-target="deleteindex<%=j += 1%> " >刪除</a> </div>
            </div>
        </div>
    </div>
</c:forEach>



