<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.IOException,java.net.URL,javax.servlet.*,main.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>果壳天气</title>
</head>
<body>
	<%
		//调用工具类得到weather对象，将其字段返回给浏览器显示出来
		String myAppid = "acb4be18d51ea8b92e5d750d8af72adb";
		String newAppid = request.getParameter("appid");
		if (newAppid != null) {
			myAppid = newAppid;
		}
		String city = "beijing";
		String cityTemp = request.getParameter("city");
		if (cityTemp != null) {
			city = cityTemp;
		}
		String country = "china";
		String countryTemp = request.getParameter("country");
		if (countryTemp != null) {
			country = countryTemp;
		}
		URL myUrl = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country
				+ "&appid=acb4be18d51ea8b92e5d750d8af72adb");

		Weather myWeather = JsonUtil.getWeather(myUrl);
	%>
	<div>
		<h1 style="color: red">欢迎来到果壳天气</h1>

		<form action="index.jsp" method="GET">
			城市：<input type="text" name="city" /> (拼音)<br> 国家：<input
				type="text" name="country" value="china" /><br> Appid: <input
				type="text" name="appid" />(如果Appid过期的话)<br> <br> <input type="submit"
				value="查询" /> <br> <br>
		</form>
	</div>
	<div>
		<strong style="color: red">查询结果:</strong><br>
		城市：<%=myWeather.city%><br> 国家：<%=myWeather.country%><br>
		位置：[<%=myWeather.lon%>°,<%=myWeather.lat%>°] <br> 天气：<%=myWeather.main%>,<%=myWeather.description%><br>
		当前温度：<%=myWeather.temp%>°C <br> 湿度：<%=myWeather.humidity%>% <br>
		气压：<%=myWeather.pressure%>hpa 
		<br> 风速：<%=myWeather.wind_spd%>m/s <br>
	</div>


</body>
</html>