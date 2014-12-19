<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/wechat/add" method="POST" onsubmit="return iframeCallback(this, dialogReloadRel);" enctype="multipart/form-data">
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>标题：</th>
		   <td><input type="text" name="title" value="" size="60" class="required" maxlength="100"/></td>
		</tr>
		<tr>
		   <th>排序序号：</th>
		   <td><input type="text" name="sort" value="" class="required digits" max="999999999"/></td>
		</tr>
		<tr>
		   <th>发布时间：</th>
		   <td><input type="text" name="releaseDate" value="" readonly="readonly" class="date required" dateFmt="yyyy-MM-dd HH:mm:ss"/><a href="javascript:void(0);"></a></td>
		</tr>
		<tr>
		   <th>是否推荐为NEW：</th>
		   <td>
		       <select class="required" name="mark">			
				 <option value="0">&nbsp;否&nbsp;</option>
				 <option value="1" selected>&nbsp;是&nbsp;</option>
		       </select>
		   </td>
		</tr>
		<tr>
		   <th>上传文件：</th>
		   <td>
		       <input type="file" name="uploadfile" class="required" size="40"/> 
		   </td>
		</tr>
		<tr>
		   <th>内容：</th>
		   <td><textarea name="content" cols="100" rows="10" class="textInput required"></textarea></td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>