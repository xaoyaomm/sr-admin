<form action="${request.contextPath}/product/search" method="POST" id="pagerForm">
	<input type="hidden" name="pageNum" value="1" />
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/product/search" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	 <select class="combox" style="width:200px" name="pid" id="w_combox_pro" ref="w_combox_city" refUrl="${request.contextPath}/area/getchild?pid={value}">
		<#list areapList as item>
			<#if item.id??&&item.id== areapSelect>
			<option value="${item.id}" selected="selected">${item.title}</option>
			<#else>
			<option value="${item.id}">${item.title}</option>
			</#if>
		</#list>
	</select>
	<select class="combox" style="width:200px" name="cid" id="w_combox_city">
		<#list areacList as item>
			<#if item.id??&&item.id== areacSelect>
			<#assign cityName="${item.title}">
			<option value="${item.id}" selected="selected">${item.title}</option>
			<#else>
			<option value="${item.id}">${item.title}</option>
			</#if>
		</#list>
	</select>
	<select class="combox" style="width:200px" name="catalog_id" id="w_combox_catalog">
		<#list catalogList as item>
			<#if item.id??&&item.id == catalogSelect>
			<#assign catalogName="${item.name}">
			<option value="${item.id}" selected="selected">${item.name}</option>
			<#else>
			<option value="${item.id}">${item.name}</option>
			</#if>
		</#list>
	</select>
    <input type="submit" value="搜索" onclick=""/>             
</form>
</div>

<div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/product/cre?pid=${areapSelect}&cid=${areacSelect}&catalog_id=${catalogSelect}" height="500" width="800" mask="true" target="dialog" class="add" title="添加新商品" rel="add_product_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="80">
		<thead>
			<tr>
				<th>序号</th>
				<th>排序</th>
				<th>图片</th>
                <th>名称</th>
                <th>单价(分)</th>
                <th>分类</th>
                <th>城市</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list productList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.order!}</td>
			      <td class="img"><img src="${item.imgUrl!}" width="60" /></td>
			      <td>${item.name!}</td>
			      <td>${item.price!}</td>
			      <td>${catalogName!}</td>
			      <td>${cityName!}</td>
			      <td><a class="btnEdit" href="${request.contextPath}/product/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="product_info">编辑</a>
			      <a class="btnDel" href="${request.contextPath}/product/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?">移除</a></td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>