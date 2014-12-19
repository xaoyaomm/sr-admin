 <@dwz.pagerForm action="${request.contextPath}/wechat/config" page=page>
</@dwz.pagerForm>
 <div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			 <@dwz.tool_button content="添加配置" class="add" rel="region_add" width="500" height="300" href="${request.contextPath}/wechat/addConfig"/>
	  	     <@dwz.tool_button content="修改配置" class="edit" width="500" height="300" href="${request.contextPath}/wechat/updateConfig/{id}"/>
	  	     <@dwz.tool_button content="删除配置" class="delete" target="ajaxTodo" callback="reloadRel" href="${request.contextPath}/wechat/delConfig/{id}" title="确认要删除该配置?"/>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="84">
		<thead>
			<tr>
				<th width="50">模块名称</th>
                <th width="120">配置名称</th>
                <th width="420">配置值</th>
                <th width="140">描述信息</th>
			</tr>
		</thead>
		<tbody>
			<#list dataList as item>
			   <tr target="id"  rel="${item.id!}">
			   <td>${item.moudleName!}</td>
			   <td>${item.configName!}</td>
			   <td>${item.configValue!}</td>
			   <td>${item.description!}</td>
			   </tr>
			</#list>
		</tbody>
	</table>
 	<@dwz.pagerBar page=page/>
</div>