<%-- 
    Document   : change
    Created on : 24.10.2013, 10:29:40
    Author     : Александр
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href="style.css" type="text/css" rel="stylesheet"> 
    </head>
    <body>
        <h1><center>Страница изменения понятий</center></h1>
        <table width="200" border="0">
  <tr>
    <td>
<div class="general_menu">
        	<ul><a href="index.jsp">Главная</a></ul>
            <ul><a href="search.jsp">Поиск</a></ul>
            <ul><a href="add.jsp">Добавление</a></ul>
            <ul><a href="change.jsp">Изменение</a></ul>
            <ul><a href="erase.jsp">Удаление</a></ul>
      </div>
    </td>
    <td>
    	
  	 Форма изменения понятия:
    	<table width="200" border="0">
        <form name="form1" method="post" action="CServlet">
      
  <tr>
    <td>Название:</td>
    <td><input type="text" name="changinName"/></td>
  </tr>
   <tr>
    <td>Содержание:</td>
    <td><input type="text" name="changingContent"/></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" value="Измениеть"/></td>
    </tr>
   </form>
</table>

    </td>
  </tr>
</table>
    </body>
</html>
