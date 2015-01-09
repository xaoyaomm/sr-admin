<form action="${request.contextPath}/hot/add" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
<div class="pageContent">
	<div class="pageFormContent" layoutH="97">

		<dl class="nowrap">
			<dt>商品ID：</dt>
			<dd>
				<input name="id" bringBackName="pros.id" type="text" class="required" readonly="readonly"/>
				<a class="btnLook" href="${request.contextPath}/product/search?lookup=1" lookupGroup="pros" width="900" height="700">查找带回</a>
			</dd>
		</dl>
		<dl class="nowrap">
			<dt>商品名称：</dt>
			<dd>
				<input class="required" name="name" bringBackName="pros.proName" readonly="readonly"  type="text"/>
			</dd>
		</dl>
		<dl class="nowrap">
			<dt>销售数量：</dt>
			<dd>
				<input type="text" name="total" value="" class="required digits" />
			</dd>
		</dl>
		
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>
</form>
