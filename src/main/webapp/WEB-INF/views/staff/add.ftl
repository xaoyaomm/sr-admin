<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/staff/add" method="POST" onsubmit="return iframeCallback(this, dialogReloadRel);" enctype="multipart/form-data">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>姓名：</th>
		   <td><input type="text" name="name" value="" class="required"/></td>
		</tr>
		<tr>
		   <th>手机号：</th>
		   <td><input type="text" name="phone" value="" size="11" class="required" maxlength="11"/></td>
		</tr>
		</tbody>	
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>