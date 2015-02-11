 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
 <@dwz.pagerForm action="${request.contextPath}/staff/search" page=page>
	<input type="hidden" name="name" value=""/>
</@dwz.pagerForm>
 <div class="pageHeader">
	<form action="${request.contextPath}/staff/search" onsubmit="return navTabSearch(this);" method="POST">
	 姓名：<input type="text" name="name" value="${name!}"/>
	 手机号：<input type="text" name="phone" value="${phone!}"/>
    <input type="submit" value="搜索"/>             
</form>
</div>

 <div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/staff/cre" height="500" width="800" mask="true" target="dialog" class="add" title="添加新内容" rel="add_staff_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
                <th>手机号</th>
                <th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list staffList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${page.pageNum * (item_index+1)}</td>
			      <td>${item.name!}</td>
			      <td>${item.phone!}</td>
			      <td>${item.createDate?number?number_to_datetime}</td>
			      <td>
			        <a class="btnEdit" href="${request.contextPath}/staff/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="staff_info">编辑</a>
			        <a class="btnDel" href="${request.contextPath}/staff/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?">移除</a>
			        </td>
			   </tr>
			</#list>
		</tbody>
	</table>
 	<@dwz.pagerBar page=page/>
</div>