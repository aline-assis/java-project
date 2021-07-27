<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>${param.title} - Health Track</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <meta name="theme-color" content="#2c3e50">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <link rel='stylesheet' type='text/css' media='screen' href='css/bootstrap/bootstrap.min.css'>
    <link rel='stylesheet' type='text/css' href='css/fontawesome/all.min.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='css/main.css'>
    <script src='js/jquery/jquery-3.5.1.min.js'></script>
    <script src='js/boostrap/bootstrap.bundle.min.js'></script>
    <script src='js/3rdParty/moment.min.js'></script>
    <c:forEach var="jsFile" items="${paramValues.js}">
    	<script src='js/${jsFile}'></script>
    </c:forEach>
</head>