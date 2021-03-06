<script type="text/javascript">
   
</script>
<br/>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/version/update" method="POST" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
<input type="hidden" name="id" value="${releaseVersion.id!}"/>
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th>客户端类型：</th>
		   <td>
		   		<select class="combox" style="width:200px" name="clientType" id="v_combox_type">
								<option value="customer" selected="selected">用户端</option>
								<option value="merchants" >商户端</option>
					</select>
		   </td>
		</tr>
		<tr>
		   <th>版本CODE：</th>
		   <td><input type="text" name="versionCode" class="required digits" min="100" value="${releaseVersion.versionCode!}" max="9999" size="50"/><span style="color:red">版本CODE为100-9999之间的整数值,如1002</span></td>
		</tr>
		<tr>
		   <th>版本号：</th>
		   <td><input type="text" name="versionCodeName" value="${releaseVersion.versionCodeName!}" class="required" size="50"/><span style="color:red">例:1.0.0.1</span></td>
		</tr>
		<tr>
		   <th>是否强制更新：</th>
		   <td>
		   		<label><input type="radio" class="required" name="mustUpdate" <#if !releaseVersion.mustUpdate> checked="checked"</#if> value="false" />否</label>
				<label><input type="radio" class="required" name="mustUpdate" <#if releaseVersion.mustUpdate> checked="checked"</#if>  value="true"/>是</label>
		   </td>
		</tr>
		<tr>
		   <th>版本描述：</th>
		   <td>
		   		<div class="unit">
							<textarea class="editor" name="desc" xheight="200" xwidth="600" rows="6" cols="100" tools="Cut,Copy,Paste,Pastetext,|,Align,List,Bold,Italic,Underline,FontColor,BackColor,|,Source,Preview">${releaseVersion.desc!}</textarea>
				</div>
		   </td>
		</tr>
		<tr>
		   <th>当前APK下载地址：</th>
		   <td><a href="${releaseVersion.downloadUrl!}">${releaseVersion.downloadUrl!}</a></td>
		</tr>
		<tr>
		   <th>更新APK文件：</th>
		   <td>
		       <input type="file" name="file" size="40" />
		   </td>
		</tr>
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>