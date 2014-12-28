<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/product/update" method="POST" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<input type="hidden" name="id" value="${product.id!}"/>
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>	
		<tr>
		   <th>排序：</th>
		   <td><input type="text" name="order" value="${product.order!}" /></td>
		</tr>	
		<tr>
		   <th>名称：</th>
		   <td><input type="text" name="name" value="${product.name!}"/></td>
		</tr>
		<tr>
		   <th>单价(分)：</th>
		   <td><input type="text" name="price" value="${product.price!}" /></td>
		</tr>
		<tr>
			<th>城市：</th>
			<td>
				<select class="combox" style="width:200px" name="pid_d"  id="detail_combox_pro">
							<option value="${areapSelect.id}" selected="selected">${areapSelect.title}</option>
				</select>
				<select class="combox" style="width:200px" name="cid_d" id="detail_combox_city">
							<option value="${areacSelect.id}" selected="selected">${areacSelect.title}</option>
				</select>
			</td>
		</tr>
		<tr>
		   <th>分类：</th>
		   <td>
		   		<select class="combox" style="width:200px" name="catalog_id_d" id="detail_combox_catalog">
							<option value="${catalogSelect.id}" selected="selected">${catalogSelect.name}</option>
				</select>
		   </td>
		</tr>
		<tr>
		   <th>上传文件：</th>
		   <td>
		       <input type="file" name="img" size="40"/><img src="${product.imgUrl!}" alt="."/>
		   </td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>