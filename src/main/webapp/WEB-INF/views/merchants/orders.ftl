 <#setting datetime_format="yyyy-MM-dd HH:mm"/>

<div class="pageContent">	
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>序号</th>
				<th>下单时间</th>
                <th>送货地址</th>
                <th>商品详情</th>
                <th>订单总价(元)</th>
                <th>响应时间</th>
				<th>订单状态</th>
			</tr>
		</thead>
		<tbody>
			<#list merc_orders as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.createDate?number?number_to_datetime}</td>
			      <td>${item.toAddress!}</td>
			      <#assign prosDetail="">
			      <#list item.products as pro>
			      	<#assign prosDetail="${prosDetail}${pro.productName}(${pro.amount}),">
			      </#list>
			      <td title="${prosDetail}">
			      	<#if prosDetail?length gt 45>${prosDetail?substring(0,45)}...<#else>${prosDetail}</#if>
			      </td>
			      <td>${item.totalPrice! / 100}</td>
			      <td><#if item.offerDate??&& item.offerDate gt 0>#{(item.offerDate-item.createDate)/1000;M0}秒<#else>未抢单</#if></td>
			      <td><#if item.status??&&item.status==0>无人接单<#elseif item.merchantsId!=merc_id>被别人抢单<#elseif item.status==1>已抢单<#elseif item.status==2>送货中<#elseif item.status==4>已送达<#elseif item.status==6>已确认<#elseif item.status==9>用户标记未送达<#elseif item.status==10>订单取消</#if></td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>