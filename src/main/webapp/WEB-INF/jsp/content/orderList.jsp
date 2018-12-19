<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.page.css"/>
		<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/jquery.page.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="${basePath}/orders/search" id="mainForm" method="post">
			<input id="id" name="id" type="hidden">
			<input name="page.currentPage" id="currentPage" value="1" type="hidden">
			<div class="right">
				<div class="current">当前位置：<a href="#">内容管理</a> &gt; 订单查询</div>
				<div class="rightCont">
					<p class="g_title fix">订单列表</p>
					<table class="tab1">
						<tbody>
							<tr>
								<td width="80" align="right">手机号：</td>
								<td>
									<input name="title" id="title" value="" class="allInput" type="text">
								</td>
	                            <td style="text-align: right;" width="150">
	                            	<input class="tabSub" value="查询" onclick="search('1');" type="button">&nbsp;&nbsp;&nbsp;&nbsp;
	                            </td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%" id="tab">
							<tbody>
								<tr>
								    <th>序号</th>
								    <th>手机号</th>
								    <th>订单号</th>
								    <th>金额(元)</th>
								</tr>

								<c:forEach items="${ordersList}" var="item" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>${item.member.phone}</td>
										<td>${item.id}</td>
										<td>${item.price}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- 分页 -->
						<div id="page"></div>
					</div>
				</div>
			</div>
		</form>
	
</body>
	<script>
        var tbody=window.document.getElementById("tab");
        $(function(){
            $("#page").Page({
                totalPages: ${pagenum},//分页总数
                liNums: 5,//分页的数字按钮数(建议取奇数)
                activeClass: 'activP', //active 类样式定义
                callBack : function(page){
                    $.ajax({
                        type: "GET",
                        //提交方式
                        url: "${basePath}/orders/getlist/"+page,
                        //路径
                        success: function(data) {
                            $("#tab   tr:not(:first)").html("");
                            var str = "" ;
                            for (var i in data) {
                                str +="<tr>" +
                                    "<td>" + (Number(i)+1) + "</td>" +
                                    "<td>" + data[i].member.phone + "</td>" +
                                    "<td>" + data[i].id + "</td>"+
                                    "<td>" + data[i].price + "</td>"+
                                    "</tr>";
                            }
                            tbody.innerHTML += str;
                        }
                    });
                }
            });
        })
	</script>
</html>