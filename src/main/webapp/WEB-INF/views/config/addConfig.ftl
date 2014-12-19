<@dwz.layout_content>
<@dwz.form action="${request.contextPath}/wechat/saveConfig" onsubmit="return validateCallback(this, dialogReloadRel);">
	<@dwz.layout_form_content layoutH="58">
		<@dwz.label_input content="配置模块名称:">
			<input type="text" name="moudleName" class="required" size="35" maxlength="100"/>
		</@dwz.label_input>	
		<@dwz.label_input content="配置名称:">
			<input type="text" name="configName" class="required" size="35" maxlength="100"/>
		</@dwz.label_input>	
		<@dwz.label_input content="配置值:">
			<input type="text" name="configValue" class="required" size="35" maxlength="100"/>
		</@dwz.label_input>	
		<@dwz.label_input content="描述:">
			<textarea name="description" cols="35" rows="3" maxlength="255"></textarea>
		</@dwz.label_input>										
	</@dwz.layout_form_content>
	<@dwz.form_bar submitTitle="确定" closeTitle="关闭"/>	
</@dwz.form>
</@dwz.layout_content>