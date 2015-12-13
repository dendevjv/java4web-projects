<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        <!-- This line added for Eclipse -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  <!-- This line added for Eclipse -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       <!-- This line added for Eclipse -->
<%-- @elvariable id="contacts" type="java.util.Set<ru.denvevjv.Contact>" --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="title.browser" /></title>
</head>
<body>

    <h2><fmt:message key="title.page" /></h2>
    
    <c:choose>
        <c:when test="${fn:length(contacts) == 0}">
            <i><fmt:message key="message.noContacts" /></i>
        </c:when>
        <c:otherwise>
            <c:forEach items="${contacts}" var="contact">
                <b>
                    <c:out value="${contact.lastName}, ${contact.firstName}" />
                </b><br />
                <c:out value="${contact.address}" /><br />
                <c:out value="${contact.phoneNumber}" /><br />
                <c:if test="${contact.birthDay != null}">
                    <fmt:message key="label.birthday" />:
                    ${contact.birthDay.month.getDisplayName(
                        'FULL', pageContext.response.locale
                    )}&nbsp;${contact.birthDay.dayOfMonth}<br />
                </c:if>
                <fmt:message key="label.creationDate" />:
                <fmt:formatDate value="${contact.oldDateCreated}" type="both" dateStyle="long" timeStyle="long" />
                <br /><br />
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>