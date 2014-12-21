<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/user/add" method="POST" onsubmit="return iframeCallback(this, dialogReloadRel);" enctype="multipart/form-data">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>用户名：</th>
		   <td><input type="text" name="userName" value="" class="required"/></td>
		</tr>
		<tr>
		   <th>密码：</th>
		   <td><input type="password" name="pwd" value="" class="required"/></td>
		</tr>
		<tr>
		   <th>昵称：</th>
		   <td><input type="text" name="nickName" value="" class="required"/></td>
		</tr>
		<tr>
		   <th>手机号：</th>
		   <td><input type="text" name="phone" value="" size="11" class="required" maxlength="11"/></td>
		</tr>
		<tr>
		   <th>角色：</th>
		   <td>
		       <select class="required" name="roleId">			
				 <option value="1">&nbsp;管理员&nbsp;</option>
				 <option value="2" selected>&nbsp;普通用户&nbsp;</option>
		       </select>
		   </td>
		</tr>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>