 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
<@dwz.layout_content>
<table width="100%" class="input">
		<tbody>	
		<tr>
		   <th>订单编号：</th>
		   <td>${order.id!}</td>
		</tr>
		</tbody>
		</table>	
<@dwz.fieldset title="买家信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>姓名：</th>
		   <td>${order.customerName!}</td>
		   <th>电话：</th>
		   <td>${order.customerPhone!}</td>
		</tr>
		<tr>
		   <th>送货地址：</th>
		   <td>${order.toAddress!}</td>
		</tr>
		</tbody>
</table>
</@dwz.fieldset>
<@dwz.fieldset title="商家信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>店铺名：</th>
		   <td>${order.merchantsName!}</td>
		   <th>电话：</th>
		   <td>${order.merchantsPhone!}</td>
		</tr>
		<tr>
		   <th>店铺地址：</th>
		   <td>${order.fromAddress!}</td>
		</tr>
		</tbody>
</table>
</@dwz.fieldset>
<@dwz.fieldset title="商品信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
	<tbody>	
	 <#assign x = 0 />
	<#list pros as item>
	<#if item_index%3 ==0><tr><#assign x=x+1></#if>
		   <th>${item.productName!}：</th>
		   <td>购买${item.amount!}份</td>
		<#if x*3 ==item_index><tr></#if>
	</#list>
		<tr>
		   <th>总价：</th>
		   <td>${order.totalPrice / 100!}元</td>
		</tr>
	</tbody>
</table>
</@dwz.fieldset>
<@dwz.fieldset title="订单信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>下单时间：</th>
		   <td>${order.createDate?number?number_to_datetime}</td>
		   
		</tr>
		<tr>
		   <th>接单时间：</th>
		   <td><#if order.offerDate?? && order.offerDate gt 0>${order.offerDate?number?number_to_datetime}<#else>未接单</#if></td>
		</tr>
		<tr>
		   <th>接单用时：</th>
		   <td><#if order.offerDate?? && order.offerDate gt 0>#{(order.offerDate-order.createDate)/1000;M0}秒<#else>未接单</#if></td>
		</tr>
		<tr>
		   <th>送达时间：</th>
		   <td><#if order.arrivedDate??&&order.arrivedDate gt 0>${order.arrivedDate?number?number_to_datetime}<#else>无送达时间</#if></td>
		</tr>
		<tr>
		   <th>确认时间：</th>
		   <td><#if order.confirmDate??&&order.confirmDate gt 0>${order.confirmDate?number?number_to_datetime}<#else>无确认时间</#if></td>
		</tr>
		<tr>
		   <th>送货时间：</th>
		   <td><#if order.arrivedDate??&&order.arrivedDate gt 0>#{(order.arrivedDate-order.offerDate)/1000/60;M0}分钟<#elseif order.confirmDate??&&order.confirmDate gt 0>#{(order.confirmDate-order.offerDate)/1000/60;M0}分钟<#else>未送达</#if></td>
		</tr>
		</tbody>
</table>
</@dwz.fieldset>
</@dwz.layout_content>