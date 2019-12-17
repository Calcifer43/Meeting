<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            会议管理 > 会议申请审批
        </div>
        <table class="listtable">
            <caption>所有待审批信息：</caption>
            <tr class="listheader">
                <th>会议号</th>
                <th>申请人</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${mts2}" var="mr">
                <tr>
                    <td>${mr.meetingid}</td>
                    <td>
                            ${mr.employeename}
                    </td>
                    <td>
                        <a class="clickbutton" href="/meeting/updateMpStatus?status=1&mid=${mr.meetingid}&empid=${mr.employeeid}">通过</a>
                        <a class="clickbutton" href="/meeting/updateMpStatus?status=-1&mid=${mr.meetingid}">不通过</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="page-footer">
    <hr/>
    更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
    <img src="images/footer.png" alt="CoolMeeting"/>
</div>
</body>
</html>