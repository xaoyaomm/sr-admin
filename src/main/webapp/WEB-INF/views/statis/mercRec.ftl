 <form action="${request.contextPath}/statis/mercrec" method="POST" id="pagerForm">
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/statis/mercrec" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					从：<input type="text" name="start" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${m_start}"/>
				</td>
				<td>
					到：<input type="text" name="end" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${m_end}" />
				</td>
				
				<td>
					<select class="combox" style="width:200px" name="limit" id="w_combox_limit">
								<option value="20" <#if m_limit??&&m_limit== 20>selected="selected"</#if>>20条</option>
								<option value="50" <#if m_limit??&&m_limit== 50>selected="selected"</#if>>50条</option>
								<option value="100" <#if m_limit??&&m_limit== 100>selected="selected"</#if>>100条</option>
								<option value="200" <#if m_limit??&&m_limit== 200>selected="selected"</#if>>200条</option>
								<option value="500" <#if m_limit??&&m_limit== 200>selected="selected"</#if>>500条</option>
					</select>
				</td>

				<td>
					<input type="submit" value="搜索" onclick=""/>
				</td>
			</tr>
		</table>
		
	</div>           
</form>

<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>序号</th>
				<th>推广数量</th>
				<th>店铺推广码</th>
				<th>店铺名称</th>
				<th>店铺地址</th>
				<th>联系电话</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list merc_users as item>
			   <tr target="id"  rel="${item.user.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.totalRecuser!}</td>
			      <td>${item.user.mercNum!}</td>
			      <td>${item.user.nickName!}</td>
			      <td>${item.user.address!}&nbsp;<span style="color:blue">(${item.user.location[0]!},${item.user.location[1]})</span></td>
			      <td>${item.user.phone!}</td>
			      <td>
			        <a class="btnInfo" href="${request.contextPath}/merchants/recs/${item.user.id!}/?start=${m_start!}&end=${m_end!}" height="700" width="1000" mask="true" target="dialog" title="查看详细信息" rel="merchants_recs">查看详细</a>
			      </td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>
