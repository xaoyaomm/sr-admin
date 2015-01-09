 <#setting datetime_format="yyyy-MM-dd HH:mm:ss"/>
<div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a href="${request.contextPath}/campaign/cre" height="500" width="800" mask="true" target="dialog" class="add" title="添加新内容" rel="add_campaign_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="list" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="40">序号</th>
				<th width="220">标题</th>
				<th width="220">banner</th>
                <th width="220">活动网址</th>
                <th width="220">开始时间</th>
                <th width="220">结束时间</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list camList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td>${item_index+1}</td>
			      <td>${item.name!}</td>
			      <td class="img"><img src="${item.bannerUrl!}"/></td>
			      <td>${item.pageUrl!}</td>
			      <td>${item.start?number?number_to_datetime}</td>
			      <td>${item.end?number?number_to_datetime}</td>
			      <td>
			        <a class="btnEdit" href="${request.contextPath}/campaign/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="campaign_info">编辑</a>
			        <a class="btnDel" href="${request.contextPath}/campaign/del?id=${item.id!}"  target="ajaxTodo" title="你确定要删除吗?">移除</a>
			      </td>
			   </tr>
			</#list>
		</tbody>
	</table>
</div>