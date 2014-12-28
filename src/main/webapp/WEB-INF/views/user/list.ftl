 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
 <@dwz.pagerForm action="${request.contextPath}/user/list${search!}" page=page>
	<input type="hidden" name="name" value=""/>
</@dwz.pagerForm>
 <div class="pageHeader">
	<form action="${request.contextPath}/user/list/search" onsubmit="return navTabSearch(this);" method="POST">
	 用户名：<input type="text" name="userName" value="${userName!}"/>
    <input type="submit" value="搜索"/>             
</form>
</div>

 <div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/user/cre" height="500" width="800" mask="true" target="dialog" class="add" title="添加新内容" rel="add_user_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="20">序号</th>
				<th width="150">用户名</th>
                <th width="220">昵称</th>
                <th width="40">手机号</th>
                <th width="40">状态</th>
                <th width="40">角色</th>
                <th width="70">创建时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list userList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td><input name="ids" value="${item.id!}" type="checkbox"></td>
			      <td>${page.pageNum * (item_index+1)}</td>
			      <td>${item.userName!}</td>
			      <td>${item.nickName!}</td>
			      <td>${item.phone!}</td>
			      <td><#if item.status??&&item.status==1>有效<#else>禁用</#if></td>
			      <td><#if item.roleId??&&item.roleId==1>管理员<#else>普通用户</#if></td>
			      <td>${item.createTime?number?number_to_datetime}</td>
			      <td>
			        <a class="btnEdit" href="${request.contextPath}/user/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="user_info">编辑</a>
			        <a class="btnDel" href="${request.contextPath}/user/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?">移除</a>
			        </td>
			   </tr>
			</#list>
		</tbody>
	</table>
 	<@dwz.pagerBar page=page/>
</div>