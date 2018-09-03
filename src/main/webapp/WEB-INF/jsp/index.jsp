<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>I'm a title!!!</title>
</head>
<body>
<c:if test="${name != ''}">
    ${name}
</c:if>
</br>
I'm a jsp page.
</body>
</html>
