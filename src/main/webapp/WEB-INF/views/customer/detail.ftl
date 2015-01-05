 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
<@dwz.layout_content>
<table width="100%" class="input">
		<tbody>	
		<tr>
		   <th>用户ID：</th>
		   <td>${customer.id!}</td>
		</tr>
		</tbody>
		</table>	
<@dwz.fieldset title="个人信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>用户名：</th>
		   <td>${customer.userName!}</td>
		</tr>
		<tr>
		   <th>姓名：</th>
		   <td>${customer.nickName!}</td>
		</tr>
		<tr>
		   <th>电话：</th>
		   <td>${customer.phone!}</td>
		</tr>
		<tr>
		   <th>注册版本：</th>
		   <td>${customer.registerVer!}</td>
		   <th>当前版本：</th>
		   <td>${customer.currVer!}</td>
		</tr>
		</tbody>
</table>
</@dwz.fieldset>
<@dwz.fieldset title="地址信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<#list adds as item>
		<tr>
		   <td>${item.address!}&nbsp;<span style="color:blue">(${item.location[0]!},${item.location[1]})</span>&nbsp;<span style="color:green">${item.name!}</span>&nbsp;<span style="color:#1DD2C1">${item.phone!}</span><#if item.id==customer.addressId><span style="color:red">默认地址</span></#if></td>
		</tr>
		</#list>
		</tbody>
</table>
</@dwz.fieldset>
</@dwz.layout_content>