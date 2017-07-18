<%@ page pageEncoding="UTF-8"%><%@ taglib prefix="my"
	uri="/WEB-INF/tld/my.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>Hello taglib</title>
<style type="text/css">
* {
	font-family: "Arial";
	font-size: 72px;
}
</style>
</head>
<body>
	<my:time format="yyyy-MM-dd" backColor="blue" foreColor="yellow" />
</body>
</html>