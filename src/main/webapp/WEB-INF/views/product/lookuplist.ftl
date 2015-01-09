<form action="${request.contextPath}/product/search" method="POST" id="pagerForm">
	<input type="hidden" name="pageNum" value="1" />
	<input name="lookup" value="1" type="hidden">
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/product/search" onsubmit="return dialogSearch(this);" method="POST" id="pageForm" rel="pagerForm">
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
	<input name="lookup" value="1" type="hidden">
    <input type="submit" value="搜索" onclick=""/>             
</form>
</div>

<div class="pageContent">	
	<div class="panelBar">
	</div>
	<table class="list" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="100" >序号</th>
				<th width="150">排序</th>
				<th width="150">图片</th>
                <th width="220">名称</th>
                <th width="220">单价(分)</th>
                <th width="220">分类</th>
                <th width="220">操作</th>
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
			      <td><a class="btnSelect" href="javascript:$.bringBack({id:'${item.id!}', proName:'${item.name!}'})"  title="选择此商品" >选择</a></td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>