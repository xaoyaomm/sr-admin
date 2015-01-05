 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
 <@dwz.pagerForm action="${request.contextPath}/customer/search" page=page>
</@dwz.pagerForm>
 <div class="pageHeader">
	<form action="${request.contextPath}/customer/search" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					从：<input type="text" name="start" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${start}"/>
				</td>
				<td>
					到：<input type="text" name="end" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${end}" />
				</td>
				<td>
					地区：
				</td>
				<td>
					<select class="combox" style="width:200px" name="pid" id="w_combox_pro" ref="w_combox_city" refUrl="${request.contextPath}/area/getchild?pid={value}">
						<#list areapList as item>
							<#if item.id??&&item.id== areapSelect>
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
					</select>
				</td>
				<td>
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
				</td>
				<td>
					<select class="combox" style="width:200px" name="type" id="w_combox_type">
								<option value="1" <#if type??&&type== 1>selected="selected"</#if>>注册用户</option>
								<option value="0" <#if type??&&type== 0>selected="selected"</#if>>临时用户</option>
							
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
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>用户类型</th>
                <th>注册时间</th>
                <th>最后登录时间</th>
                <th>总订单数</th>
                <th>成功订单数</th>
                <th>未接订单数</th>
                <th>失败订单数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list userList as item>
			   <tr target="id"  rel="${item.user.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.user.nickName!}</td>
			      <td><#if item.user.type??&&item.user.type=="customer">注册用户<#else>临时用户</#if></td>
			      <td>${item.user.createTime?number?number_to_datetime}</td>
			      <td>${item.user.lastUserTime?number?number_to_datetime}</td>
			      <td><#if item.totalOrder gt 0><a  href="${request.contextPath}/customer/orders/${item.user.id!}?type=1" height="700" width="1000" mask="true" target="dialog" title="总订单" rel="customer_orders">${item.totalOrder!}</a><#else>0</#if></td>
			      <td><#if item.totalSucc gt 0><a  href="${request.contextPath}/customer/orders/${item.user.id!}?type=2" height="700" width="1000" mask="true" target="dialog" title="成功订单" rel="customer_orders">${item.totalSucc!}</a><#else>0</#if></td>
			      <td><#if item.totalNone gt 0><a  href="${request.contextPath}/customer/orders/${item.user.id!}?type=3" height="700" width="1000" mask="true" target="dialog" title="未抢订单" rel="customer_orders">${item.totalNone!}</a><#else>0</#if></td>
			      <td><#if item.totalFail gt 0><a  href="${request.contextPath}/customer/orders/${item.user.id!}?type=4" height="700" width="1000" mask="true" target="dialog" title="失败订单" rel="customer_orders">${item.totalFail!}</a><#else>0</#if></td></a>
			      <td><a  href="${request.contextPath}/customer/detail/${item.user.id!}" height="700" width="800" mask="true" target="dialog" title="详细信息" rel="customer_info">查看详情</a></td>
			   </tr>
			</#list>
		</tbody>
	</table>
	<@dwz.pagerBar page=page/>
</div>