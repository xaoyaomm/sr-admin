<#-- 
class="tree treeFolder treeCheck expand"
-->
<#macro checkBoxtree object checkValue="" class="tree treeFolder treeCheck expand" oncheck="">
     <ul class="${class}" oncheck="kkk">
	 <#assign depth = 1 />
	</ul>
</#macro>

<#macro checkBoxTree_recursion children checkValue>
    <#if children?exists && children?size gt 0>
    	<ul>
        <#list children as child>
			<li><a tname="regions" tvalue="${child.id}" <#if (checkValue?exists&&checkValue?index_of(child.id)>=0)>checked="true"</#if>>${child.name}</a>			
			<#assign depth = depth + 1 />
            <@checkBoxTree_recursion children=child.children checkValue=checkValue/>
            <#assign depth = depth - 1 />
            </li>
		</#list>
		</ul>
	</#if>	
</#macro>
 