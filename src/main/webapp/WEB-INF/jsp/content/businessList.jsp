<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.page.css"/>
		<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/jquery.page.js"></script>
		<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
		<script type="text/javascript" src="${basePath}/js/content/businessList.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="${basePath}/business/search" id="mainForm" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
			<input type="hidden" id="message" value="${pageCode.msg}"/>
			<input type="hidden" id="basePath" value="${basePath}"/>
			<input type="hidden" name="page.currentPage" id="currentPage" value="1"/>
			<div class="right">
				<div class="current">当前位置：<a href="#">内容管理</a> &gt; 商户管理</div>
				<div class="rightCont">
					<p class="g_title fix">商户列表</p>
					<table class="tab1">
						<tbody>
							<tr>
								<td align="right" width="80">标题：</td>
								<td>
									<input name="title" id="title"  class="allInput" type="text"/>
								</td>
	                            <td style="text-align: right;" width="150">
	                            	<input class="tabSub" value="查询" onclick="search();" type="button"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                            	<%--<t:auth url="/businesses/addPage" method="GET">--%>
	                            		<%--<input class="tabSub" value="添加" onclick="location.href='${basePath}/businesses/addPage'" type="button"/>--%>
	                            	<%--</t:auth>--%>
										<input class="tabSub" value="添加" onclick="location.href='${basePath}/business/addPage'" type="button"/>
	                            </td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%" id="tab">
							<tbody>
								<tr>
								    <th>序号</th>
								    <th>标题</th>
								    <th>副标题</th>
								    <th>城市</th>
								    <th>类别</th>
								    <th>操作</th>
								</tr>
								
								<c:forEach items="${bsList}" var="item" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>${item.title}</td>
										<td>${item.subtitle}</td>
										<td>${item.cityDic.name}</td>
										<td>${item.categoryDic.name}</td>
										<td>
											<a href="javascript:void(0);" onclick="modifyInit('${item.id}')">修改</a>
											<a href="javascript:void(0);" onclick="remove('${item.id}')">删除</a>
										</td>
											<%--<t:auth url="/businesses/${item.id}" method="PUT">--%>
												<%--<a href="javascript:void(0);" onclick="modifyInit('${item.id}')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
											<%--</t:auth>--%>
											<%--<t:auth url="/businesses/${item.id}" method="DELETE">--%>
												<%--<a href="javascript:void(0);" onclick="remove('${item.id}')">删除</a>--%>
											<%--</t:auth>--%>
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
                        url: "${basePath}/business/getlist/"+page,
                        //路径
                        success: function(data) {
                            $("#tab   tr:not(:first)").html("");
                            var str = "" ;
                            for (var i in data) {
                                str +="<tr>" +
                                    "<td>" + (Number(i)+1) + "</td>" +
                                    "<td>" + data[i].title + "</td>" +
                                    "<td>" + data[i].subtitle + "</td>"+
                                    "<td>" + data[i].cityDic.name + "</td>"+
                                    "<td>" + data[i].categoryDic.name + "</td>"+
                                    "<td>" +
                                    "<a href='javascript:void(0);' onclick='modifyInit("+data[i].id+")'>修改</a>"+
                                    "<a href='javascript:void(0);' onclick='remove("+data[i].id+")'>删除</a>"+
                                    "</td>"+
                                    "</tr>";
                            }

                            tbody.innerHTML += str;
                        }
                    });
                }
            });
        })
        function search(){
            var param=document.getElementById("title").value;
            if(param==null || param=="")
                param="BACD6F4771952C9C5D254DE71C485B05";
            $.ajax({
                type: "GET",
                //提交方式
                url: "${basePath}/business/search/"+param,
                //路径
                success: function(data) {
                    $("#tab   tr:not(:first)").html("");
                    var str = "" ;
                    for (var i in data) {
                        str +="<tr>" +
                            "<td>" + ${s.index + 1} + "</td>" +
                            "<td>" + data[i].title + "</td>" +
                            "<td>" + data[i].subtitle + "</td>" +
                            "<td>" + data[i].cityDic.name+ "</td>" +
                            "<td>" + data[i].categoryDic.name + "</td>" +
                            "<td>" +
                            "<a href='javascript:void(0);' onclick='modifyInit("+data[i].id+")'>修改</a>"+
                            "<a href='javascript:void(0);' onclick='remove("+data[i].id+")'>删除</a>"+
                            "</td>"+
                            "</tr>";
                    }

                    tbody.innerHTML += str;
                }
            });
        }
	</script>
</html>