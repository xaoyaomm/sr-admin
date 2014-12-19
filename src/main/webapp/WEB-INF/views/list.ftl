 <@dwz.pagerForm action="${request.contextPath}/wechat/list${search!}" page=page>
	<input type="hidden" name="title" value="${title!}"/>
</@dwz.pagerForm>
 <div class="pageHeader">
	<form action="${request.contextPath}/wechat/list/search" onsubmit="return navTabSearch(this);" method="POST">
	 标题：<input type="text" name="title" value="${title!}"/>
    <input type="submit" value="搜索"/>             
</form>
</div>

 <div class="pageContent">	
	<div class="panelBar">
		<ul class="toolBar">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${request.contextPath}/wechat/delete" class="delete"><span>批量移除</span></a></li>
			<li><a href="${request.contextPath}/wechat/cre" height="500" width="800" mask="true" target="dialog" class="add" title="添加新内容" rel="add_audio_info"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="20">序号</th>
				<th width="150">标题</th>
                <th width="220">文件地址</th>
                <th width="40">是否已发布</th>
                <th width="40">是否为NEW</th>
                <th width="70">创建时间</th>
                <th width="70">发布时间</th>
                <th width="35">排序(降序)</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<#list audioList as item>
			   <tr target="id"  rel="${item.id!}">
			      <td><input name="ids" value="${item.id!}" type="checkbox"></td>
			      <td>${item.getSequenceNumber(page, item_index+1)}</td>
			      <td>
			         <a href="${request.contextPath}/wechat/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="audio_info">${item.title!}</a>
			      </td>
			      <td>${item.audioUrl!}</td>
			      <td><#if item.status??&&item.status==1>已发布<#else>未发布</#if></td>
			      <td><#if item.mark??&&item.mark==1>是<#else>否</#if></td>
			      <td>${item.createDate?string('yyyy-MM-dd HH:mm:ss')}</td>
			      <td>${item.releaseDate?string('yyyy-MM-dd HH:mm:ss')}</td>
			      <td>${item.sort!}</td>
			      <td>
			        <a class="btnEdit" href="${request.contextPath}/wechat/detail/${item.id!}" height="500" width="800" mask="true" target="dialog" title="详细信息" rel="audio_info">编辑</a>
			        <a class="btnDel" href="${request.contextPath}/wechat/del?id=${item.id!}" callback="reloadRel" target="ajaxTodo" title="你确定要删除吗?">移除</a>
			        </td>
			   </tr>
			</#list>
		</tbody>
	</table>
 	<@dwz.pagerBar page=page/>
</div>