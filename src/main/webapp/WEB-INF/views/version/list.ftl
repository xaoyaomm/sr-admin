 <#setting datetime_format="yyyy-MM-dd HH:mm:ss"/>
 <form action="${request.contextPath}/version/list" method="POST" id="pagerForm">
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/version/list" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<select class="combox" style="width:200px" name="type" id="w_combox_type">
								<option value="0" <#if version_type??&&version_type== 0>selected="selected"</#if>>全部</option>
								<option value="1" <#if version_type??&&version_type== 1>selected="selected"</#if>>用户端</option>
								<option value="2" <#if version_type??&&version_type== 2>selected="selected"</#if>>商户端</option>
							
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/version/cre" height="600" width="860" mask="true" target="dialog" class="add" title="添加新版本" rel="add_version_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="40">序号</th>
				<th width="100">版本类型</th>
				<th>版本CODE</th>
                <th>版本号</th>
                <th>是否强制更新</th>
                <th>版本描述</th>
                <th>下载地址</th>
                <th>发布时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list versionList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td><#if item.clientType??&&item.clientType=="customer">用户端<#else>商户端</#if></td>
			      <td>${item.versionCode!}</td>
			      <td>${item.versionCodeName!}</td>
			      <td><#if item.mustUpdate??&&item.mustUpdate==true>是<#else>否</#if></td>
			      <td>${item.desc!}</td>
				  <td>${item.downloadUrl!}</td>
				  <td>${item.createDate?number?number_to_datetime}</td>
			      <td>
			        <a class="btnEdit" href="${request.contextPath}/version/detail/${item.id!}" height="600" width="860" mask="true" target="dialog" title="详细信息" rel="version_info">编辑</a>
			        <a class="btnDel" href="${request.contextPath}/version/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?">移除</a>
			      </td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>