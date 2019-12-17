<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
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
            会议预定 > 申请信息
        </div>
        <form method="post" action="/meeting/updateMpStatus?status=3">
            <fieldset>
                <legend>申请信息</legend>
                <%
                    String mid = request.getParameter("mid");
                    session.setAttribute("mid",mid);
                    String whatis = request.getParameter("whatis");
                    String[] m = {"employeename","departmentname","noid","phone","time","sex","room"};
                    String[] what = whatis.split(" ");
                    int[] a = new int[7];
                    for(int i=0,j=0;i<7;i++){
                        if(j<what.length&&m[i].equals(what[j])){
                            j++;
                            a[i]=1;
                        }else {
                            a[i]=0;
                        }
                        session.setAttribute("i",i);
                    }
                    session.setAttribute("a",a);
                %>
                <table class="formtable" style="width:50%">
                    <c:if test="${error!=null}">
                        <tr>
                            <td colspan="2">${error}</td>
                        </tr>
                    </c:if>

                    <tr>
                        <td>
                            <input type="hidden" id="mid" name="mid" value=${sessionScope.mid} maxlength="20"/>
                        </td>
                        <c:if test="${param.type!=null}">
                            <td>
                                <input type="hidden" value="sub" name="type" id="type"/>
                            </td>
                        </c:if>
                    </tr>

                    <tr>
                        <c:if test="${sessionScope.a[0]==1}">
                            <td>姓名</td>
                            <td>
                                <input type="text" id="employeename" name="employeename" maxlength="20"/>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${sessionScope.a[1]==1}">
                            <td>工作单位</td>
                            <td>
                                <input type="text" id="departmentname" name="departmentname" maxlength="20"/>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${sessionScope.a[2]==1}">
                            <td>身份证</td>
                            <td>
                                <input type="text" id="noid" name="noid" maxlength="20"/>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${sessionScope.a[3]==1}">
                            <td>联系电话</td>
                            <td>
                                <input type="text" id="phone" name="phone" maxlength="20"/>
                            </td>
                        </c:if>
                    </tr>
                    <c:if test="${sessionScope.a[4]==1}">
                        <td>参会时间</td>
                        <td>
                            <input type="text" id="time" name="time" maxlength="20"/>
                        </td>
                    </c:if>
                    <tr>
                        <c:if test="${sessionScope.a[5]==1}">
                            <td>性别</td>
                            <td>
                                <input type="text" id="sex" name="sex" maxlength="20"/>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${sessionScope.a[6]==1}">
                            <td>是否需要房间</td>
                            <td>
                                <input type="text" id="room" name="room" maxlength="20"/>
                            </td>
                        </c:if>
                    </tr>



                    <tr>
                        <td colspan="6" class="command">
                            <input type="submit" class="clickbutton" value="提交"/>
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
