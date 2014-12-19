 <@dwz.pagerForm action="${request.contextPath}/wechat/logList" page=page>
</@dwz.pagerForm>
</div>

 <div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${request.contextPath}/wechat/delLog" class="delete"><span>批量移除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="85" asc="asc" desc="desc">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120">用户IP</th>
				<th width="120">访问URI</th>
				<th width="100">操作时间</th>
				<th width="70">操作人</th>
				<th width="120">参数信息</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list logList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td><input name="ids" value="${item.id!}" type="checkbox"></td>
			      <td>${item.addr!}</td>
			      <td>${item.uri!}</td>
			      <td>${item.time?string("yyyy-MM-dd HH:mm:ss")}</td>
			      <td>${item.operator!}</td>
			      <td>${item.parameters!}</td>
			      <td>
			        <a class="btnDel" href="${request.contextPath}/wechat/delLog?ids=${item.id!}" target="ajaxTodo" title="你确定要删除吗?">移除</a>
			        </td>
			   </tr>
			</#list>
		</tbody>
	</table>
 	<@dwz.pagerBar page=page/>
</div>