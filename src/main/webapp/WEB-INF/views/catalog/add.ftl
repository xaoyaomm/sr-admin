<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/catalog/add" method="POST" onsubmit="return validateCallback(this,dialogAjaxDone);">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>名称：</th>
		   <td><input type="text" name="name" value="" /></td>
		</tr>
		<tr>
		   <th>排序：</th>
		   <td><input type="text" name="order" value="" /></td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>