<%-- 
    Document   : test
    Created on : Jun 5, 2020, 8:44:14 AM
    Author     : hp
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:url var="url_jqlib" value="/static/js/jquery-3.5.1.min.js"/>
        <script src="${url_jqlib}"></script>
        <script>
            $(document).ready(function(){
                //alert('JQuery is ready'); 
                $("#id_get_time").click(function (){
                  // alert('Button clicked'); 
                  $.ajax({
                      url: 'get_time',
                      success: function (data) {
                       $("#id_time").html(data); 
                    }
                  })
                });
            });
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>AJAX TEST PAGE</h1>
        <button id="id_get_time">Get Server Time</button> <br/>
        <p id="id_time"></p>
    </body>
</html>
