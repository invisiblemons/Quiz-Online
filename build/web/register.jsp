<%-- 
    Document   : register
    Created on : Fed 27, 2021, 9:23:39 PM
    Author     : MONS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="./css/header.css">
        <link rel="stylesheet" href="./css/registration.css">
         <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
          
        <title>Register Page</title>
    </head>
    <body>
        <div class="container-fluid pt-0 pl-0 pr-0">
            <div class="row">
                <div class="col">
                    <div class="header">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <a href="#" class="logo">Quiz Online</a>
                        <div class="header-right">
                            <a href="login.jsp">Login</a>
                            <a class="active" href="#">Register</a>
                        </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col">
                    <form action="register" method="POST">
                        <div class="container">
                            <div style="text-align: center;">
                                <h1>Register</h1>
                                <p>Please fill in this form to create an account.</p>
                            </div>
                            <hr>
                            <c:set var="error" value="${requestScope.ERROR}"/>
            
                            <c:if test="${not empty error.duplicateEmail}">
                                <label for="email"><b>Email</b></label>
                                <input style="border: 2px red solid;" type="email" placeholder="Enter Email" name="email" value="${param.email}" id="email" required>
                                <h2 style="color: red;margin-top: -10px;">${error.duplicateEmail}</h2>
            
                            </c:if>
                            <c:if test="${empty error.duplicateEmail}">
                                <label for="email"><b>Email</b></label>
                                <input type="email" placeholder="Enter Email" name="email" value="${param.email}" id="email" required>
                            </c:if>
            
                            <label for="name"><b>Name</b></label>
                            <input type="text" placeholder="Enter Name" name="name" value="${param.name}" id="name" required>
            
                            <c:if test="${not empty error.passwordNotEmpty}">
                                <h2 style="color: red;">Password Not empty!!!</h2>
                                <label for="psw"><b>Password</b></label>
                                <input style="border: 2px red solid;" type="password" placeholder="Enter Password" name="password" id="psw" required>
            
                                <label for="psw-repeat"><b>Repeat Password</b></label>
                                <input type="password" placeholder="Repeat Password" name="confirm" id="psw-repeat" required>
                            </c:if>
                            <c:if test="${empty error.passwordNotEmpty}">
                                <c:if test="${not empty error.passwordNotMatched}">
                                    <label for="psw"><b>Password</b></label>
                                    <input style="border: 2px red solid;" type="password" placeholder="Enter Password" name="password" id="psw" required>
            
                                    <label for="psw-repeat"><b>Repeat Password</b></label>
                                    <input style="border: 2px red solid;" type="password" placeholder="Repeat Password" name="confirm" id="psw-repeat" required>
                                    <h2 style="color: red;margin-top: -10px;">${error.passwordNotMatched}</h2>
            
                                </c:if>
                                <c:if test="${empty error.passwordNotMatched}">
            
                                    <label for="psw"><b>Password</b></label>
                                    <input type="password" placeholder="Enter Password" name="password" id="psw" required>
            
                                    <label for="psw-repeat"><b>Repeat Password</b></label>
                                    <input type="password" placeholder="Repeat Password" name="confirm" id="psw-repeat" required>
            
                                </c:if>
                            </c:if>
            
            
            
                            <hr>
                            <button type="submit" class="registerbtn">Register</button>
                        </div>
            
                        <div class="container signin">
                            <div class="row">
                                <div class="col-sm-2"></div>
                                <div class="col">
                                    <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
                                </div>
                                <div class="col-sm-2"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>

    </body>
</html>

