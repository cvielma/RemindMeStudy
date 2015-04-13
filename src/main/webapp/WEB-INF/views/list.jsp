<%-- 
    Document   : list
    Created on : Apr 9, 2015, 6:03:50 PM
    Author     : Christian Vielma <cvielma@librethinking.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type = "text/javascript">
            var baseUrl = '<%=request.getContextPath()%>';
            var newList = ${todolistjson};
        </script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 
        <script src="<%=request.getContextPath()%>/resources/js/todolist.js" type="text/javascript"></script>
            
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/todolist.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div id = "header" class="center">
            <header>
                <h1>To Do List</h1>
            </header>
        </div>
        <div id ="content" class="center">
            <div class = "left">
                <p> left</p>
            </div>
            <div class = "right">
                <p> right</p>
            </div>
            <div class = "center">
                <div>
                    <form id = "createTaskForm" onsubmit="return false;">
                        <input type="text" id="createTaskName" name="name"></input>
                        <button id = "createTaskButton" type="button">Create</button>
                        <button id = "showList" type="button">Refresh</button>
                    </form>
                </div>
                <div id = "list"> </div>
            </div>            
        </div>
        <div id = "footer" class="center">
            <footer>
                <small>www.librethinking.com</small>               
            </footer>               
        </div>                    
    </body>
    <script>
        $('h1').text(($('h1').text() + ' (' + window.screen.availWidth + 'x' + window.screen.availHeight +')'));
    </script>
</html>
