<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--@elvariable id="shortText" type="java.lang.String"--%>
<%--@elvariable id="longText" type="java.lang.String"--%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>         <%-- Added for Eclipse --%>
<%@ taglib prefix="fmtdates" uri="http://www.wrox.com/jsp/tld/wrox" %>  <%-- Added for Eclipse --%>
<template:main htmlTitle="Abbreviating Text">
    <b>Short text:</b> ${fmtdates:abbreviateString(shortText, 32)}<br />
    <b>Long text:</b> ${fmtdates:abbreviateString(longText, 32)}<br />
</template:main>