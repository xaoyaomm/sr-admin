<div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/hot/cre" height="400" width="800" mask="true" target="dialog" class="add" title="添加新内容" rel="add_hot_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="20">序号</th>
				<th width="150">商品ID</th>
                <th width="220">名称</th>
                <th width="220">数量</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list hotList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.id!}</td>
			      <td>${item.name!}</td>
			      <td>${item.total!}</td>
			      <td>
			        <a class="btnDel" href="${request.contextPath}/hot/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?">移除</a>
			      </td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>