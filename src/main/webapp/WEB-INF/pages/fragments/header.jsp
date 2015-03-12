<%-- 
    Document   : header
    Created on : Mar 12, 2015, 10:04:57 AM
    Author     : Pozitron
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="<c:url value="/static/styles/entresuelo.css" />" rel="stylesheet">
        <link type="text/css" href="<c:url value="/static/styles/tables.css" />" rel="stylesheet">
        <title></title>
    </head>

    <body>

        <center>
            <a href='/Entresuelo/allItems'>Перечень содержимого</a>
            <span>&nbsp;&nbsp;&nbsp;</span>

            <a href='/Entresuelo/allLocations'>Перечень локаций</a>
            <span>&nbsp;&nbsp;&nbsp;</span>

            <a href='/Entresuelo/allCategories'>Перечень категорий</a>
            <span>&nbsp;&nbsp;&nbsp;</span>

            <a href='/Entresuelo/login?logout'>Выйти из системы</a>		
        </center>
        <hr>
