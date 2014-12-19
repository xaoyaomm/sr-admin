<h2 class="contentTitle">FTP设置</h2>
<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/wechat/ftpUpdate" onsubmit="return validateCallback(this, dialogReloadRel);">
<input type="hidden" name="id" value="${ftpConfig.id!}"/>
<table width="100%" cellspacing="1" cellpadding="3" class="input">
		<tbody>		
		<tr>
		   <th><span style="font-weight:bold;">服务器IP:</span></th>
		   <td><input type="text" name="ftpService" class="required" value="${ftpConfig.ftpService!}" size="30"/></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">FTP端口号:</span></th>
		   <td><input type="text" name="ftpPort" class="required digits" value="${ftpConfig.ftpPort!}" size="5"/></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">FTP用户名:</span></th>
		   <td><input type="text" name="ftpUser" class="required" value="${ftpConfig.ftpUser!}"/></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">FTP密码:</span></th>
		   <td><input type="text" name="ftpPwd" class="required" value="${ftpConfig.ftpPwd!}"/></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">远程目录:</span></th>
		   <td><input type="text" name="ftpPath" class="required" value="${ftpConfig.ftpPath!}" size="30"/></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">访问URL:</span></th>
		   <td><input type="text" name="ftpUrl" class="required url" value="${ftpConfig.ftpUrl!}" size="40"/></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">文件上传最大值:</span></th>
		   <td><input type="text" name="fileSize" class="required digits" value="${ftpConfig.fileSize!}" size="5"/>&nbsp;<span style="font-weight:bold;color:red;">M</span></td>
		</tr>
		<tr>
		   <th><span style="font-weight:bold;">允许上传文件扩展名:</th>
		   <td><input type="text" name="fileType" class="required" value="${ftpConfig.fileType!}"/></td>
		</tr>				
		</tbody>
</table>
<@dwz.form_bar submitTitle="确定" closeTitle="取消"/>
</@dwz.form>
</@dwz.layout_content>