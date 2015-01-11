 <form action="${request.contextPath}/statis/product" method="POST" id="pagerForm">
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/statis/product" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					从：<input type="text" name="start" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${p_start}"/>
				</td>
				<td>
					到：<input type="text" name="end" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${p_end}" />
				</td>
				<td>
					地区：
				</td>
				<td>
					<select class="combox" style="width:200px" name="pid" id="w_combox_pro_h" ref="w_combox_city_p" refUrl="${request.contextPath}/area/getchild?pid={value}">
						<#list p_areapList as item>
							<#if item.id??&&item.id== p_areapSelect>
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
					</select>
				</td>
				<td>
					<select class="combox" style="width:200px" name="cid" id="w_combox_city_p">
						<#list p_areacList as item>
							<#if item.id??&&item.id== p_areacSelect>
								<#assign cityName="${item.title}">
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
					</select>
				</td>
				<td>
					<select class="combox" style="width:200px" name="limit" id="w_combox_limit">
								<option value="20" <#if limit??&&limit== 20>selected="selected"</#if>>20条</option>
								<option value="50" <#if limit??&&limit== 50>selected="selected"</#if>>50条</option>
								<option value="100" <#if limit??&&limit== 100>selected="selected"</#if>>100条</option>
								<option value="200" <#if limit??&&limit== 200>selected="selected"</#if>>200条</option>
					</select>
				</td>
				<td>
					<input type="submit" value="搜索" onclick=""/>
				</td>
			</tr>
		</table>
		
	</div>           
</form>
</div>

<div class="pageContent">	
	<table class="list" width="100%" layoutH="70">
		<thead>
			<tr>
				<th>排名</th>
				<th>商品名称</th>
                <th>商品图片</th>
                <th>商品单价</th>
                <th>销售数量</th>
			</tr>
		</thead>
		<tbody>
			<#list hot_productsList as item>
			   <tr target="id"  rel="">
			      <td>${item_index+1}</td>
			      <td>${item.productName!}</td>
			      <td class="img"><img src="${item.productImg!}" width="60" /></td>
			      <td>${item.productPrice! / 100}</td>
			      <td>${item.totalAmount!}</td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>