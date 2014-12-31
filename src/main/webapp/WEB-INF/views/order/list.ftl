 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
 <@dwz.pagerForm action="${request.contextPath}/order/search" page=page>
</@dwz.pagerForm>
 <div class="pageHeader">
	<form action="${request.contextPath}/order/search" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
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
					<select class="combox" style="width:200px" name="status" id="w_combox_status">
								<option value="1" <#if status??&&status== 1>selected="selected"</#if>>成功订单</option>
								<option value="0" <#if status??&&status== 0>selected="selected"</#if>>无人接单</option>
								<option value="2" <#if status??&&status== 2>selected="selected"</#if>>失败订单</option>
							
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
				<th width="20" >序号</th>
				<th width="150">订单号</th>
                <th width="220">送货地址</th>
                <th width="220">接单商户</th>
                <th width="220">推送商家</th>
                <th width="220">下单时间</th>
                <th width="220">城市</th>
                <th width="220">订单总价(元)</th>
				<th width="50">订单状态</th>
				<th width="50">查看详情</th>
			</tr>
		</thead>
		<tbody>
			<#list orderList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.id!}</td>
			      <td>${item.toAddress!}</td>
			      <td>${item.merchantsName!}</td>
			      <td>${item.offers?size!}</td>
			      <td>${item.createDate?number?number_to_datetime}</td>
			      <td>${item.city!}</td>
			      <td>${item.totalPrice! / 100}</td>
			      <td><#if item.status??&&item.status==0>无人接单<#elseif item.status==1>已抢单<#elseif item.status==2>送货中<#elseif item.status==4>已送达<#elseif item.status==6>已确认<#elseif item.status==9>用户标记未送达<#elseif item.status==10>订单取消</#if></td>
			      <td><a class="btnEdit" href="${request.contextPath}/order/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="product_info">编辑</a></td>
			   </tr>
			</#list>
		</tbody>
	</table>
	<@dwz.pagerBar page=page/>
</div>