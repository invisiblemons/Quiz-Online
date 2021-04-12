<%-- 
    Document   : login
    Created on : Mar 1, 2021, 9:24:28 PM
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
          
        <title>Login Page</title>
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
                            <a class="active" href="#">Login</a>
                            <a href="register.jsp">Register</a>
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
                    <form action="login" method="POST">
                        <div class="container">
                            <div style="text-align: center;">
                                <h1>Login</h1>
                            </div>
                            <hr>
                            
                            <c:if test="${not empty requestScope.FAILEMAIL}">
                                <label for="email"><b>Email</b></label>
                                <input style="border: 2px red solid;" type="email" placeholder="Enter Email" name="email" id="email" required>
                                <h2 style="color: red;margin-top: -10px;">${requestScope.FAILEMAIL}</h2>
                            </c:if>
            
                            <c:if test="${empty requestScope.FAILEMAIL}">
                                <label for="email"><b>Email</b></label>
                                <input type="email" placeholder="Enter Email" name="email" value="${param.email}" id="email" required>
                            </c:if>
            
                            <c:if test="${not empty requestScope.FAILPASS}">
                                <label for="psw"><b>Password</b></label>
                                <input  style="border: 2px red solid;" type="password" placeholder="Enter Password" name="password" id="psw" required>
                                <h2 style="color: red;margin-top: -10px;">${requestScope.FAILPASS}</h2>
                            </c:if>
                            <c:if test="${empty requestScope.FAILPASS}">
                                <label for="psw"><b>Password</b></label>
                                <input type="password" placeholder="Enter Password" name="password" id="psw" required>
                            </c:if>
                                
                            <hr>
                            <button type="submit" class="loginbtn">Login</button>
                        </div>
            
                        <div class="container signin">
                            <p>Register an account? <a href="register.jsp">Register</a>.</p>
                        </div>
                    </form>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>

    </body>
</html>

