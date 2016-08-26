<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <!--显示表单内容-->
  <DIV ID="MainArea">
		<DIV CLASS="ItemBlock_Title1">
        </DIV> 

        <DIV CLASS="ItemBlockBorder" STYLE="margin-left: 15px;margin-top: 150px;">
            <DIV CLASS="ItemBlock" STYLE="text-align: center; font-size: 16px;">
                <img  src="${pageContext.request.contextPath}/images/face.png">恭喜您!     人员信息修改成功。
            </DIV>
        </DIV>
        
        <!-- 操作 -->
        <DIV ID="InputDetailBar" STYLE="text-align: right;margin-right: 270px;margin-top: 60px;">
            <a href="personnelAction_personnelEditUI?id=${globle_user.id}"   style="color:black"><IMG SRC="${pageContext.request.contextPath}/images/goBack.png"/></A>
        </DIV>
    
</DIV>

</body>
</html>