<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/staff/update" method="POST" onsubmit="return iframeCallback(this, dialogReloadRel);" enctype="multipart/form-data">
<input type="hidden" name="id" value="${staff.id!}"/>
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>姓名：</th>
		   <td><input type="text" name="name" value="${staff.name!}"/></td>
		</tr>
		<tr>
		   <th>手机号：</th>
		   <td><input type="text" name="phone" value="${staff.phone!}"/></td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>