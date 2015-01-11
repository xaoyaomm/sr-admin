<div class="pageContent">	
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>用户类型</th>
				<th>昵称</th>
				<th>手机号</th>
				<th>注册地</th>
				<th>注册版本</th>
				<th>当前版本</th>
                <th>注册时间</th>
                <th>最后登录时间</th>
			</tr>
		</thead>
		<tbody>
			<#list rec_users as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.userName!}</td>
			      <td><#if item.type??&&item.type=="customer">注册用户<#else>临时用户</#if></td>
			      <td>${item.nickName!}</td>
			      <td>${item.phone!}</td>
			      <td>${item.city!}</td>
			      <td>${item.registerVer!}</td>
			      <td>${item.currVer!}</td>
			      <td>${item.createTime?number?number_to_datetime}</td>
			      <td>${item.lastUserTime?number?number_to_datetime}</td>
			   </tr>
			</#list>
		</tbody>
	</table>
	</div>