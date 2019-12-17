<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <style type="text/css">
    </style>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            会议预定 > 预定会议
        </div>
        <form method="post" action="/meeting/dobookmeeting">
            <fieldset>
                <legend>所需信息</legend>
                <table class="formtable" style="width:50%">
                    <tr>
                        <td>姓名：</td>
                        <td>
                            <select name="employeename">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>工作单位：</td>
                        <td>
                            <select name="department">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>身份证：</td>
                        <td>
                            <select name="idno">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td>
                            <select name="sex">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>参会时间：</td>
                        <td>
                            <select name="time">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>是否需要房间：</td>
                        <td>
                            <select name="room">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>联系电话：</td>
                        <td>
                            <input type="text" id="phone" name="phone" maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td>电子邮件：</td>
                        <td>
                            <select name="email">
                                <option value="yes">是</option>
                                <option value="no">否</option>
                            </select>
                        </td>
                    <tr>
                        <td colspan="6" class="command">
                            <input type="submit" class="clickbutton" value="注册"/>
                            <input type="reset" class="clickbutton" value="重置"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>
