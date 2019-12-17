<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议管理系统</title>
    <link rel="stylesheet" href="styles/common.css"/>
    <style type="text/css">
        #divfrom {
            float: left;
            width: 200px;
        }

        #divto {
            float: left;
            width: 200px;
        }

        #divoperator {
            float: left;
            width: 50px;
            padding: 60px 5px;
        }

        #divoperator input[type="button"] {
            margin: 10px 0;
        }

        #selDepartments {
            display: block;
            width: 100%;
        }

        #selEmployees {
            display: block;
            width: 100%;
            height: 200px;
        }

        #selSelectedEmployees {
            display: block;
            width: 100%;
            height: 225px;
        }
    </style>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript" src="jquery.qrcode.min.js"></script>
    <script type="application/javascript">
        var num=0;
        $(function(){

            $("#sub_btn").click(function(){
                $("#code").empty();
                var str = toUtf8($("#mytxt").val());

                $("#code").qrcode({
                    render: "table",
                    width: 200,
                    height:200,
                    text: str
                });
            });
        })
        function toUtf8(str) {
            var out, i, len, c;
            out = "";
            len = str.length;
            for(i = 0; i < len; i++) {
                c = str.charCodeAt(i);
                if ((c >= 0x0001) && (c <= 0x007F)) {
                    out += str.charAt(i);
                } else if (c > 0x07FF) {
                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                    out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
                } else {
                    out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
                }
            }
            return out;
        }
        function base64 (content) {
            return window.btoa(unescape(encodeURIComponent(content)));
        }
        /*
        *@tableId: table的Id
        *@fileName: 要生成excel文件的名字（不包括后缀，可随意填写）
        */
        function tableToExcel(tableID,fileName) {
            var table = document.getElementById(tableID);
            var excelContent = table.innerHTML;
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += "<head><meta charset=\"UTF-8\"></head>";
            excelFile += "<body><table>";
            excelFile += excelContent;
            excelFile += "</table></body>";
            excelFile += "</html>";
            var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
            var a = document.createElement("a");
            a.download = fileName + ".xls";
            a.href = link;
            a.click();
        }

        function employee(employeeid, employeename) {
            this.employeeid = employeeid;
            this.employeename = employeename;
        }
        function department(departmentid, departmentname, employees) {
            this.departmentid = departmentid;
            this.departmentname = departmentname;
            this.employees = employees;
        }
        var data = new Array(
            new department(1, "技术部", new Array(
                new employee(1001, "a00"), new employee(1002, "a01"), new employee(1003, "a02"), new employee(1004, "a03"))),
            new department(2, "销售部", new Array(
                new employee(2001, "b00"), new employee(2002, "b01"), new employee(2003, "b02"), new employee(2004, "b03"))),
            new department(3, "市场部", new Array(
                new employee(3001, "c00"), new employee(3002, "c01"), new employee(3003, "c02"), new employee(3004, "c03"))),
            new department(4, "行政部", new Array(
                new employee(4001, "d00"), new employee(4002, "d01"), new employee(4003, "d02"), new employee(4004, "d03"))));

        var selDepartments;
        var selEmployees;
        var selSelectedEmployees;

        function body_load() {
            selDepartments = document.getElementById("selDepartments");
            selEmployees = document.getElementById("selEmployees");
            selSelectedEmployees = document.getElementById("selSelectedEmployees");

            for (var i = 0; i < data.length; i++) {
                var dep = document.createElement("option");
                dep.value = data[i].departmentid;
                dep.text = data[i].departmentname;
                selDepartments.appendChild(dep);
            }

            fillEmployees();
        }

        function fillEmployees() {
            clearList(selEmployees);
            var departmentid = selDepartments.options[selDepartments.selectedIndex].value;
            var employees;
            for (var i = 0; i < data.length; i++) {
                if (departmentid == data[i].departmentid) {
                    employees = data[i].employees;
                    break;
                }
            }
            for (i = 0; i < employees.length; i++) {
                var emp = document.createElement("option");
                emp.value = employees[i].employeeid;
                emp.text = employees[i].employeename;
                selEmployees.appendChild(emp);
            }
        }

        function clearList(list) {
            while (list.childElementCount > 0) {
                list.removeChild(list.lastChild);
            }
        }

        function selectEmployees() {
            for (var i = 0; i < selEmployees.options.length; i++) {
                if (selEmployees.options[i].selected) {
                    addEmployee(selEmployees.options[i]);
                    selEmployees.options[i].selected = false;
                }
            }
        }

        function deSelectEmployees() {
            var elementsToRemoved = new Array();
            var options = selSelectedEmployees.options;
            for (var i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    elementsToRemoved.push(options[i]);
                }
            }
            for (i = 0; i < elementsToRemoved.length; i++) {
                selSelectedEmployees.removeChild(elementsToRemoved[i]);
            }
        }

        function addEmployee(optEmployee) {
            var options = selSelectedEmployees.options;
            var i = 0;
            var insertIndex = -1;
            while (i < options.length) {
                if (optEmployee.value == options[i].value) {
                    return;
                } else if (optEmployee.value < options[i].value) {
                    insertIndex = i;
                    break;
                }
                i++;
            }
            var opt = document.createElement("option");
            opt.value = optEmployee.value;
            opt.text = optEmployee.text;

            if (insertIndex == -1) {
                selSelectedEmployees.appendChild(opt);
            } else {
                selSelectedEmployees.insertBefore(opt, options[insertIndex]);
            }
        }
    </script>
</head>
<body onload="body_load()">
<jsp:include page="top.jsp"/>
<div class="page-body">
    <jsp:include page="leftMenu.jsp"/>
    <div class="page-content">
        <div class="content-nav">
            个人中心> 会议信息
        </div>
        <form>
            <fieldset>
                <legend>会议信息</legend>
                <table class="formtable">
                    <tr>
                        <td>会议名称：</td>
                        <td>${mt.meetingname}</td>
                    </tr>
                    <tr>
                        <td>预计参加人数：</td>
                        <td>${mt.numberofparticipants}</td>
                    </tr>
                    <tr>
                        <td>预计开始时间：</td>
                        <td>${mt.starttime}</td>
                    </tr>
                    <tr>
                        <td>宾馆名称：</td>
                        <td>${mt.hotelname}</td>
                    </tr>
                    <tr>
                        <td>会议室名称:</td>
                        <td>${mt.roomname}</td>
                    </tr>
                    <tr>
                        <td>预计结束时间：</td>
                        <td>${mt.endtime}</td>
                    </tr>

                    <tr>
                        <td>会议说明：</td>
                        <td>
                            <textarea id="description" rows="5" readonly>${mt.description}</textarea>
                        </td>
                    </tr>

                    <c:if test="${loginUser.role!=3}">
                    <tr>
                        <td>参会人员：</td>
                        <td>
                            <table class="listtable" id="item">
                                <tr class="listheader">
                                    <c:if test="${mps[0].employeename!=NULL}">
                                         <th>姓名</th>
                                    </c:if>
                                    <c:if test="${mps[0].departmentname!=null}">
                                        <th>工作单位</th>
                                    </c:if>
                                    <c:if test="${mps[0].noid!=null}">
                                        <th>身份证</th>
                                    </c:if>
                                    <c:if test="${mps[0].phone!=null}">
                                        <th>联系电话</th>
                                    </c:if>
                                    <c:if test="${mps[0].time!=null}">
                                        <th>参会时间</th>
                                    </c:if>
                                    <c:if test="${mps[0].sex!=null}">
                                        <th>性别</th>
                                    </c:if>
                                    <c:if test="${mps[0].room!=null}">
                                        <th>是否需要房间</th>
                                    </c:if>

                                </tr>
                                <c:forEach items="${mps}" var="mp">
                                    <tr>
                                        <c:if test="${mps[0].employeename!=null}">
                                            <td>${mp.employeename}</td>
                                        </c:if>
                                        <c:if test="${mps[0].departmentname!=null}">
                                            <td>${mp.departmentname}</td>
                                        </c:if>
                                        <c:if test="${mps[0].noid!=null}">
                                            <td>${mp.noid}</td>
                                        </c:if>
                                        <c:if test="${mps[0].phone!=null}">
                                            <td>${mp.phone}</td>
                                        </c:if>
                                        <c:if test="${mps[0].time!=null}">
                                            <td>${mp.time}</td>
                                        </c:if>
                                        <c:if test="${mps[0].sex!=null}">
                                            <td>${mp.sex}</td>
                                        </c:if>
                                        <c:if test="${mps[0].room!=null}">
                                            <td>${mp.room}</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </table>
                            <hr/>
                            <a href="#" onclick="tableToExcel('item','data')">导出参会者信息</a>
                        </td>
                    </tr>
                    </c:if>


                    <tr>
                        <tr>
                            <td class="command" colspan="2">
                                <c:if test="${type=='alter'}">
                                    <input type="button" class="clickbutton" value="撤销会议"
                                           onclick="window.location.href='/meeting/cancelmeeting?mid=${mt.meetingid}';"/>
                                    <input type="button" class="clickbutton" value="产生会议码"
                                           onclick="alert(${mt.meetingid});"/>
                                    <input type="hidden" class="input" id="mytxt" value="${mt.meetingid}"> <input type="button" id="sub_btn" value="产生会议二维码">

                                </c:if>
                                <c:if test="${loginUser.role==3&&type=='attend'}">
                                    <input type="button" class="clickbutton" value="确定申请"
                                           onclick="window.location.href='/meeting/submitmessage2.jsp?status=3&whatis=${mt.whatis}&mid=${mt.meetingid}&type=sub';"/>
                                </c:if>
                                <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
                            </td>
                        </tr>
                    </tr>

                </table>
                <c:if test="${num!=0}">
                    <div id="code" align="center"></div>
                </c:if>
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
