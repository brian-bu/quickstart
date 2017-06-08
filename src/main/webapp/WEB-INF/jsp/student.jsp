<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.ResultSet"  %>
<% String path = request.getContextPath(); %>
<% String base = "http://" + request.getServerName()+":"+request.getServerPort(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生系统</title>
<style>
	body{background:#f6f6f6;}
	.main{width:700px;margin: 0 auto;}
	.edit{width:700px;background:#FFF;border:#999 1px solid;}
	
	.edit form ul li{list-style:outside none;margin:5px auto;height:30px}
	.edit form ul li span {width: 68px; float: left; padding-right: 10px;
	color: #666; height: 26px; font: normal 14px/26px arial; text-align: right;}
	.edit form ul li span b{color: #f00; padding-left:3px;}
	.edit form ul li input{width: 234px;height: 24px;border: 1px #b6b6b6 solid;
background: #fff;line-height: 24px;padding: 0px 5px;}
	.show table{width:700px;background:#FFF;border:#FF9 1px solid;margin-top:10px;border-collapse: collapse;}
	.show table tr{ border:1px #b6b6b6 dashed;margin:3px; padding-right: 10px;
	color: #666; height: 30px; font: normal 14px/26px arial; text-align:center;}
</style>
<script type="text/javascript" src="<%=base + path %>/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){ 
			
			$.ajax({
				url:'<%=path%>/student/delete',
				data:$(this).attr("v"),
				type:"GET",
				dataType: "text",
				success:function(msg){
					
				},
				error:function(msg){
					alert('error');
				}
			});
			 
			$(this).parent().parent().remove();
			return false;
		});
		
	});
</script>
</head>
<body>
<div class="main">
<h2 align="center">旷课学生系统</h2>
	<div class="edit">
    	<form action="<%=path %>/student/insert" method="post">
        	<ul>
            	<li><span><b>*</b>姓名:</span><input name="name" /></li>
                <li><span><b>*</b>性别:</span>
                	<input name="sex" type="radio" value="男" style="width:20px"/>
                	<input name="sex" type="radio" value="女" style="width:20px"/>
                </li>
                <li><span><b>*</b>年龄:</span><input name="age"/></li>
                <li><span>备注:</span><input name="memo"></input></li>
                <li><span></span>
                	<input type="submit" value="保存" style="width:100px"/>
                    <input type="reset" value="重置" style="width:100px"/>
                </li>
            </ul>
        </form>
    </div>
    <div class="show">
    
    	<table>
        	<tr>
          		<td width="10%"><b>序号</b></td>
                <td width="20%"><b>姓名</b></td>
                <td width="15%"><b>性别</b></td>
                <td width="15%"><b>年龄</b></td>
                <td width="25%"><b>备注</b></td>
                <td width="15%"><b>操作</b></td>
            </tr>
            <c:forEach var="s" items="${student}" varStatus="status">
            	 <tr id='${s.id}'>
            	 <td>${status.index + 1}</td>
            	 <td>${s.name}</td>
            	 <td>${s.sex}</td>  
            	 <td>${s.age}</td> 
            	 <td>${s.memo}</td> 
            	 <td><a class='delete' id='${s.id}' href="#">删除</a></td>
            	 </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>