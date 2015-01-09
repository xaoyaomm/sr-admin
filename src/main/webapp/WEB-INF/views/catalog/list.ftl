<div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/catalog/cre" height="500" width="800" mask="true" target="dialog" class="add" title="添加新内容" rel="add_catalog_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="20">序号</th>
				<th width="150">排序</th>
                <th width="220">名称</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list catalogList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.order!}</td>
			      <td>${item.name!}</td>
			      <td>
			        <a class="btnEdit" href="${request.contextPath}/catalog/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="catalog_info">编辑</a>
			        <a class="btnDel" href="${request.contextPath}/catalog/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?删除分类会删除分类下所有的商品信息!">移除</a>
			      </td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>