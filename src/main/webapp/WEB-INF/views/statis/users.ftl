 <form action="${request.contextPath}/statis/users" method="POST" id="pagerForm">
</form>
 <div class="pageHeader">
	<form action="${request.contextPath}/statis/users" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					从：<input type="text" name="start" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${us_start}"/>
				</td>
				<td>
					到：<input type="text" name="end" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${us_end}" />
				</td>
				<td>
					地区：
				</td>
				<td>
					<select class="combox" style="width:200px" name="pid" id="w_combox_us" ref="w_combox_city_us" refUrl="${request.contextPath}/area/getchild?pid={value}">
						<#list us_areapList as item>
							<#if item.id??&&item.id== us_areapSelect>
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
					</select>
				</td>
				<td>
					<select class="combox" style="width:200px" name="cid" id="w_combox_city_us">
						<#list us_areacList as item>
							<#if item.id??&&item.id== us_areacSelect>
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
<@dwz.fieldset title="用户统计信息">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>新增临时用户数：</th>
		   <td>${statis_vo.totalNewVisitor!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=1" width="1500" height="700" mask="true" target="dialog" title="新增临时用户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>新增注册用户数：</th>
		   <td>${statis_vo.totalNewCustomer!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=2" width="1500" height="700" mask="true" target="dialog" title="新增注册用户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>登录用户数：</th>
		   <td>${statis_vo.totalLoginUsers!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=3" width="1500" height="700" mask="true" target="dialog" title="登录用户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>新增商户数：</th>
		   <td>${statis_vo.totalNewMerc!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=4" width="1500" height="700" mask="true" target="dialog" title="新增商户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>登录商户数：</th>
		   <td>${statis_vo.totalLoginMerc!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=5" width="1500" height="700" mask="true" target="dialog" title="登录商户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		<tr>
		   <th>接单商户数：</th>
		   <td>${statis_vo.totalOrderMercs!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=6" width="1500" height="700" mask="true" target="dialog" title="接单商户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		<tr>
		<tr>
		   <th>下单用户数：</th>
		   <td>${statis_vo.totalOrderUsers!}
		   <a href="${request.contextPath}/statis/userschart?start=${us_start?url}&end=${us_end?url}&cid=${us_areacSelect}&type=7" width="1500" height="700" mask="true" target="dialog" title="下单用户数" rel="users_statis_info"><img src="${request.contextPath}/styles/images/chart.png"/></a>
		   </td>
		</tr>
		</tbody>
</table>
</@dwz.fieldset>
</@dwz.layout_content>