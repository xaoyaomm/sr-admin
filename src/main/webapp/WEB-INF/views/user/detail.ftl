<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/user/update" method="POST" onsubmit="return iframeCallback(this, dialogReloadRel);" enctype="multipart/form-data">
<input type="hidden" name="id" value="${user_d.id!}"/>
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>用户名：</th>
		   <td><input type="text" name="userName" value="${user_d.userName!}" readonly="readonly"/></td>
		</tr>
		<tr>
		   <th>新密码：</th>
		   <td><input type="password" name="pwd" value="" /></td>
		</tr>
		<tr>
		   <th>昵称：</th>
		   <td><input type="text" name="nickName" value="${user_d.nickName!}"/></td>
		</tr>
		<tr>
		   <th>手机号：</th>
		   <td><input type="text" name="phone" value="${user_d.phone!}"/></td>
		</tr>
		<tr>
		   <th>状态：</th>
		   <td>
		       <select class="required" name="status">			
				 <option value="0"<#if (user_d.status?exists && user_d.status == 0)>selected</#if>>&nbsp;禁用&nbsp;</option>
				 <option value="1"<#if (user_d.status?exists && user_d.status == 1)>selected</#if>>&nbsp;有效&nbsp;</option>
		       </select>
		   </td>
		</tr>
		<tr>
		   <th>角色：</th>
		   <td>
		       <select class="required" name="roleId">			
				 <option value="1"<#if (user_d.roleId?exists && user_d.roleId == 1)>selected</#if>>&nbsp;管理员&nbsp;</option>
				 <option value="2"<#if (user_d.roleId?exists && user_d.roleId == 2)>selected</#if>>&nbsp;普通用户&nbsp;</option>
		       </select>
		   </td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>