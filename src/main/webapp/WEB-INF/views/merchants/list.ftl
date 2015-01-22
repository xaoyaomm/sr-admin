 <#setting datetime_format="yyyy-MM-dd HH:mm"/>
 <@dwz.pagerForm action="${request.contextPath}/merchants/search" page=page>
</@dwz.pagerForm>
 <div class="pageHeader">
	<form action="${request.contextPath}/merchants/search" onsubmit="return navTabSearch(this);" method="POST" id="pageForm" rel="pagerForm">
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					从：<input type="text" name="start" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${start}"/>
				</td>
				<td>
					到：<input type="text" name="end" class="date" readonly="true" dateFmt="yyyy-MM-dd" value="${end}" />
				</td>
				<td>
					地区：
				</td>
				<td>
					<select class="combox" style="width:200px" name="pid" id="w_combox_pro" ref="w_combox_city" refUrl="${request.contextPath}/area/getchild?pid={value}">
						<#list areapList as item>
							<#if item.id??&&item.id== areapSelect>
								<option value="${item.id}" selected="selected">${item.title}</option>
							<#else>
								<option value="${item.id}">${item.title}</option>
							</#if>
						</#list>
					</select>
				</td>
				<td>
					<select class="combox" style="width:200px" name="cid" id="w_combox_city">
						<#list areacList as item>
							<#if item.id??&&item.id== areacSelect>
								<#assign cityName="${item.title}">
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
				
				<td>
                    <span style="color:red">成功订单数：已抢到单且未被取消或标记未送达的订单</span>
                </td>
                <td>
                    <span style="color:red">失败订单数：已抢到单且被取消或标记未送达的订单</span>
                </td>
                
			</tr>
			<tr>
				<td>
					商户名称：<input type="text" name="mercName" value=""/>
				</td>
				<td>
					商户电话：<input type="text" name="mercPhone" value=""/>
				</td>
				<td>
					<input type="submit" value="按商户信息搜索" onclick=""/>
				</td>
				<td>
                    <span style="color:red">${searchStr_merc!}</span>
                </td>
			</tr>
		</table>
		
	</div>           
</form>
</div>

<div class="pageContent">	
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>帐号状态</th>
                <th>注册时间</th>
                <th>最后登录时间</th>
                <th>推广用户数</th>
                <th>收到订单数</th>
                <th>尝试抢单数</th>
                <th>成功抢单数</th>
                <th>抢单成功率</th>
                <th>成功订单数</th>
                <th>失败订单数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#list mercList as item>
			   <tr target="id"  rel="${item.user.id!}">
			      <td>${item_index+1}</td>
			       <td>${item.user.nickName!}</td>
			      <td><#if item.user.status==1><span style="color:blue">正常</span><a class="btnEdit" href="${request.contextPath}/merchants/opera/${item.user.id!}?type=1"  target="ajaxTodo" title="你确定要锁定该商户吗?">锁定</a>
			      <#else><span style="color:red">锁定</span><a class="btnEdit" href="${request.contextPath}/merchants/opera/${item.user.id!}?type=2"  target="ajaxTodo" title="你确定要解锁该商户吗?">解锁</a></#if></td>
			      <td>${item.user.createTime?number?number_to_datetime}</td>
			      <td>${item.user.lastUserTime?number?number_to_datetime}</td>
			      <td><#if item.totalRecuser gt 0><a  href="${request.contextPath}/merchants/recs/${item.user.id!}" height="700" width="1000" mask="true" target="dialog" title="推广用户" rel="merchants_recs">${item.totalRecuser!}</a><#else>0</#if></td>
			      <td><#if item.totalOrder gt 0><a  href="${request.contextPath}/merchants/orders/${item.user.id!}?type=1" height="700" width="1000" mask="true" target="dialog" title="收到订单" rel="merchants_orders">${item.totalOrder!}</a><#else>0</#if></td>
			      <td><#if item.totalTry gt 0><a  href="${request.contextPath}/merchants/orders/${item.user.id!}?type=2" height="700" width="1000" mask="true" target="dialog" title="尝试抢单" rel="merchants_orders">${item.totalTry!}</a><#else>0</#if></td>
			      <td><#if item.totalTrySucc gt 0><a  href="${request.contextPath}/merchants/orders/${item.user.id!}?type=3" height="700" width="1000" mask="true" target="dialog" title="成功抢单" rel="merchants_orders">${item.totalTrySucc!}</a><#else>0</#if></td>
			      <td><#if item.totalTry gt 0 && item.totalTrySucc gt 0>${(item.totalTrySucc/item.totalTry)?string("0.##%")}<#else>0%</#if></td>
			      <td><#if item.totalSucc gt 0><a  href="${request.contextPath}/merchants/orders/${item.user.id!}?type=4" height="700" width="1000" mask="true" target="dialog" title="成功订单" rel="merchants_orders">${item.totalSucc!}</a><#else>0</#if></td>
			      <td><#if item.totalFail gt 0><a  href="${request.contextPath}/merchants/orders/${item.user.id!}?type=5" height="700" width="1000" mask="true" target="dialog" title="失败订单" rel="merchants_orders">${item.totalFail!}</a><#else>0</#if></td></a>
			      <td><a  href="${request.contextPath}/merchants/detail/${item.user.id!}" height="700" width="800" mask="true" target="dialog" title="详细信息" rel="customer_info">查看详情</a></td>
			   </tr>
			</#list>
		</tbody>
	</table>
	<@dwz.pagerBar page=page/>
</div>