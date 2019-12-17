<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <script src="./My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            会议预定 > 搜索会议
        </div>
        <form action="/meeting/cheack?type=attend" method="post">
            <fieldset>
                <legend>搜索会议号</legend>
                <table class="formtable">
                    <tr>
                        <td>
                            会议号：
                        </td>
                        <td>
                            <input type="text" id="meetingid" name="meetingid"  maxlength="20"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="command">
                            <input type="submit" class="clickbutton" value="查询"/>
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
