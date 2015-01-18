 <form action="${request.contextPath}/statis/order" method="POST" id="pagerForm">
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/statis/order" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					从：<input type="text" name="start" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${so_start}"/>
				</td>
				<td>
					到：<input type="text" name="end" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${so_end}" />
				</td>
				<td>
					地区：
				</td>
				<td>
					<select class="combox" style="width:200px" name="pid" id="w_combox_so" ref="w_combox_city_so" refUrl="${request.contextPath}/area/getchild?pid={value}">
						<#list so_areapList as item>
							<#if item.id??&&item.id== so_areapSelect>
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
					</select>
				</td>
				<td>
					<select class="combox" style="width:200px" name="cid" id="w_combox_city_so">
						<#list so_areacList as item>
							<#if item.id??&&item.id== so_areacSelect>
								<#assign cityName="${item.title}">
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
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
<@dwz.layout_content>
<@dwz.fieldset title="订单统计信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>总订单数：</th>
		   <td>${totalOrder!}
		   <a href="${request.contextPath}/statis/orderchart?data=${totalOrderListStr?url}&dates=${dateStr?url}" width="1500" height="700" mask="true" target="dialog" title="总订单统计" rel="order_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>总成功订单数：</th>
		   <td>${totalSucc!}
		   <a href="${request.contextPath}/statis/orderchart?data=${totalSuccListStr?url}&dates=${dateStr?url}" width="1500" height="700" mask="true" target="dialog" title="总成功订单统计" rel="order_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>总失败订单数：</th>
		   <td>${totalFail!}
		   <a href="${request.contextPath}/statis/orderchart?data=${totalFailListStr?url}&dates=${dateStr?url}" width="1500" height="700" mask="true" target="dialog" title="总失败订单统计" rel="order_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>总未接订单数：</th>
		   <td>${totalNone!}
		   <a href="${request.contextPath}/statis/orderchart?data=${totalNoneListStr?url}&dates=${dateStr?url}" width="1500" height="700" mask="true" target="dialog" title="总未接订单统计" rel="order_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>订单总金额：</th>
		   <td>${totalPrice!/ 100}元
		   <a href="${request.contextPath}/statis/orderchart?data=${totalPriceListStr?url}&dates=${dateStr?url}" width="1500" height="700" mask="true" target="dialog" title="总金额统计" rel="order_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>订单平均价格：</th>
		   <td>${totalAvgPrice!/ 100}元
		   <a href="${request.contextPath}/statis/orderchart?data=${totalAvgPriceListStr?url}&dates=${dateStr?url}" width="1500" height="700" mask="true" target="dialog" title="平均价格统计" rel="order_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		<tr>
		</tbody>
</table>
</@dwz.fieldset>
</@dwz.layout_content>