<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/campaign/add" method="POST" onsubmit="return validateCallback(this,dialogAjaxDone);">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>标题：</th>
		   <td><input type="text" name="name" value="" class="required" size="50"/></td>
		</tr>
		<tr>
		   <th>banner地址：</th>
		   <td><input type="text" name="bannerUrl" value="" class="required" size="80"/></td>
		</tr>
		<tr>
		   <th>活动页地址：</th>
		   <td><input type="text" name="pageUrl" value="" class="required" size="80"/></td>
		</tr>
		<tr>
		   <th>开始时间：</th>
		   <td><input type="text" name="startTime" class="date required" readonly="true" dateFmt="yyyy-MM-dd HH:mm:ss" value=""/></td>
		</tr>
		<tr>
		   <th>结束时间：</th>
		   <td><input type="text" name="endTime" class="date required" readonly="true" dateFmt="yyyy-MM-dd HH:mm:ss" value=""/></td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>