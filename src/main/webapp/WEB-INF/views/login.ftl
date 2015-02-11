<html>
<head>
	<title>管理后台</title>
    <style>
    #jive-loginBox {
    display: block;
    position: relative;
    width: 500px;
    text-align: left;
    top: 148px;
}
#jive-loginHeader {
    font-size: 14pt;
    font-weight: bold;
    color: #555555;
    margin: 0;
}
#jive-loginTable {
    display: block;
    position: relative;
    clear: both;
    width: auto;
    margin: 10px 0 0 0;
    padding: 0;
}
#jive-loginTable table {
    clear: both;
    margin-top: 20px;
}
#jive-loginTable td.loginFormTable {
    padding: 17px 17px 7px 55px;
    background: #e0e0e0 url(../styles/login/images/jive-login-form-bg-gray.gif) repeat-x top;
    border: 1px solid #bbbbbb;
    -moz-border-radius: 5px;
}
.loginFormTable TD {
    text-align : left;
}
#jive-loginTable td.loginFormTable table {
    margin-top: 0;
}
#jive-loginVersion {
    color: #999999;
    font-weight: normal;
    font-size: 10px;
    padding-top: 4px;
}

.jive-login-label {
    font-size: 11px;
    font-weight: bold;
    color: #515151;
    text-align : left;
}

#jive-login-header {
    font-size: 14pt;
    font-weight: bold;
    color: #555555;
    margin: 0;
}

BODY {
    background: #b6b6b6 url(../styles/login/images/jive-login-bg.gif) repeat-x top;
    padding: 0;
    margin: 0;
}
</style>
</head>

<body>

<form action="login/login" name="loginForm" method="post">

<div align="center">
    <div id="jive-loginBox">
        
        <div align="center" id="jive-loginTable">

            <span id="jive-login-header">
            管理后台
            </span>

            <div style="text-align: center; width: 380px;">
            <table cellpadding="0" cellspacing="0" border="0" align="center">
                <tr>
                    <td align="right" class="loginFormTable">

                        <table cellpadding="2" cellspacing="0" border="0">
						<#if error ??>
                        <tr>
                                <td colspan="3">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        
                                    <tr valign="top">
                                        <td><img src="../styles/login/images/error-16x16.gif" width="16" height="16" border="0" alt="" vspace="2"></td>
                                        <td><div class="jive-error-text" style="padding-left:5px; color:#cc0000;">登录错误: ${error}.</div></td>
                                    </tr>
                                        
                                    </table>
                                </td>
                            </tr>
						</#if>
                        
                        <tr>
                            <td><input type="text" name="username" size="15" maxlength="50" id="u01" value=""></td>
                            <td><input type="password" name="password" size="15" maxlength="50" id="p01"></td>
                            <td align="center"><input type="submit" value="&nbsp; 登录 &nbsp;"></td>
                        </tr>
                        <tr valign="top">
                            <td class="jive-login-label"><label for="u01">用户名</label></td>
                            <td class="jive-login-label"><label for="p01">密码</label></td>
                            <td>&nbsp;</td>
                        </tr>
                        </table>
                    </td>
                </tr>

            </table>
            </div>
        </div>

    </div>
</div>

</form>
</body>
</html>