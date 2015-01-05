<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>小店快跑管理系统</title>
<link rel="shortcut icon" href="http://www.iuni.com/favicon.ico" type="image/x-icon">
<link href="${request.contextPath}/styles/wechat/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${request.contextPath}/styles/wechat/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${request.contextPath}/styles/wechat/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${request.contextPath}/styles/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${request.contextPath}/styles/wechat/themes/css/table_input.css" rel="stylesheet" type="text/css" media="screen"/>
 

<!--[if IE]>
<link href="${request.contextPath}/styles/wechat/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${request.contextPath}/styles/wechat/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${request.contextPath}/styles/wechat/js/customer.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/jquery.validate.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
 

<script src="${request.contextPath}/styles/wechat/js/dwz.core.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.drag.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.tree.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.ui.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.theme.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.tab.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.resize.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.stable.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.database.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.effects.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.panel.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.history.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.combox.js" type="text/javascript"></script>
<script src="${request.contextPath}/styles/wechat/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="${request.contextPath}/styles/wechat/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${request.contextPath}/styles/wechat/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("${request.contextPath}/styles/wechat/dwz.frag.xml", {
        loginUrl:"",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:true,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${request.contextPath}/styles/wechat/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<ul class="nav">
				    <li><a href="">欢迎你 <#if user ??>${user.userName}</#if></a></li>
				    <li><a href="">小店快跑管理系统</a></li>
					<li><a href="${request.contextPath}/index" target="_blank">首页</a></li>
					<li><a href="${request.contextPath}/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionContent">
						<ul class="tree treeFolder"> 
							<#if user.roleId == 1>
							<li><a href="/user/list" target="navTab" rel="tab_user">用户管理</a></li>
							</#if>
							<li><a>用户管理</a>
								<ul>
									<li><a href="/customer/search" target="navTab" rel="tab_customer">用户管理</a></li>
									<li><a href="/merchants/list" target="navTab" rel="tab_merchants">商户管理</a></li>
									<li><a href="/statis/user" target="navTab" rel="tab_statis_user">用户统计</a></li>
								</ul>
							</li>
							<li><a>订单管理</a>
								<ul>
									<li><a href="/order/search" target="navTab" rel="tab_order">订单列表</a></li>
									<li><a href="/statis/order" target="navTab" rel="tab_statis_order">订单统计</a></li>
								</ul>
							</li>  
							<li><a>商品管理</a>
								<ul>
									<li><a href="/catalog/list" target="navTab" rel="tab_catalog">分类管理</a></li>
									<li><a href="/product/search" target="navTab" rel="tab_product">商品管理</a></li>
									<li><a href="/statis/product" target="navTab" rel="tab_statis_product">商品统计</a></li>
								</ul>
							</li> 
						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
					 <div class="accountInfo">
						 <div class="right">
							<p>2014</p>
						</div>
						<p><span>欢迎访问小店快跑管理系统。</span></p>
					 </div>
					 	<@dwz.layout_form_content layoutH="80">
						   <@dwz.fieldset title="基本信息">
							<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>用户名：</th><td>${user.userName!}</td>
		</tr>
		<tr>
		   <th>昵称：</th><td>${user.nickName!}</td>
		</tr>
		<tr>
		   <th>手机号：</th><td>${user.phone!}</td>
		</tr>
		<tr>
		   <th>状态：</th>
		   <td><#if (user.status?exists && user.status == 1)>有效<#else>禁用</#if></td>
		</tr>
		<tr>
		   <th>角色：</th>
		   <td> <#if (user.roleId?exists && user.roleId == 1)>管理员</#if>
		    <#if (user.roleId?exists && user.roleId == 2)>普通用户</#if></td>
		</tr>
		<tr>
		   <th>创建时间：</th><td>${user.createTime?number?number_to_datetime}</td>
		</tr>
		</tbody>
		</table>
						   </@dwz.fieldset>
						 </@dwz.layout_form_content >
				</div>
				
			</div>
		</div>

	</div>
</div>
<div id="footer">Copyright © 2014 StoreRun</div>

</body>
</html>