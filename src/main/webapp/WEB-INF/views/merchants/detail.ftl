 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
<@dwz.layout_content>
<table width="100%" class="input">
		<tbody>	
		<tr>
		   <th>店铺ID：</th>
		   <td>${merchants.id!}</td>
		</tr>
		<tr>
		   <th>推广编码：</th>
		   <td>${merchants.mercNum!}</td>
		</tr>
		</tbody>
		</table>	
<@dwz.fieldset title="个人信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>用户名：</th>
		   <td>${merchants.userName!}</td>
		</tr>
		<tr>
		   <th>店铺名称：</th>
		   <td>${merchants.nickName!}</td>
		</tr>
		<tr>
		   <th>联系电话：</th>
		   <td>${merchants.phone!}</td>
		</tr>
		<tr>
		   <th>店铺地址：</th>
		   <td>${merchants.address!}&nbsp;<span style="color:blue">(${merchants.location[0]!},${merchants.location[1]})</span></td>
		</tr>
		<tr>
		   <th>注册版本：</th>
		   <td>${merchants.registerVer!}</td>
		</tr>
		<tr>
			<th>当前版本：</th>
		   <td>${merchants.currVer!}</td>
		</tr>
		</tbody>
</table>
</@dwz.fieldset>
</@dwz.layout_content>