<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Station ID</title>
</head>
<body>
<form action="http://localhost:8081/weatherAPI/backend/getWeather/readFile/post" method="post">
    Station ID: <input type = "text" name = "stationID"/><br/>
    <input type = "submit" value = "submit"/>
</form>

</body>
</html>