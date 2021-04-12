<%-- 
    Document   : homeStudent
    Created on : Mar 1, 2021, 10:56:13 PM
    Author     : MONS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/header.css">
        <title>Home Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>

        <div class="container">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col">
                    <div class="header">

                        <a href="#" class="logo">Quiz Online</a>
                        <div class="header-right">
                            <a class="active" href="homeStudent.jsp">Home</a>
                            <a style="color: red;" >Welcome, ${user.name}</a>
                            <a href="showHistoryStudent" >Show History</a>
                            <a href="logOut">Log out</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
        <div style="margin-top: 40px;"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-8">
                    <div style="width: 80%; margin: auto;">
                        <div class="card border-primary mb-3 key" style="max-width: 20rem;">
                            <div class="card-header">PRJ321</div>
                            <div class="card-body text-primary" style="text-align: center;">
                                <h5 class="card-title">Java Web</h5>
                                <p class="card-text"></p>
                                <p class="card-text">Number Question: 10</p>
                                <p class="card-text">Time limit: 15 mins</p>
                                <form action="takeQuiz">
                                    <input type="hidden" name="email" value="${user.email}" />
                                    <button name="subject" type="submit" value="prj321" class="btn btn-outline-primary">Take A Quiz</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div style="width: 80%; margin: auto;">
                        <div class="card border-primary mb-3 key" style="max-width: 20rem;">
                            <div class="card-header">PRJ311</div>
                            <div class="card-body text-primary" style="text-align: center;">
                                <h5 class="card-title">Java Desk</h5>
                                <p class="card-text"></p>
                                <p class="card-text">Number Question: 10</p>
                                <p class="card-text">Time limit: 15 mins</p>
                                <form action="takeQuiz">
                                    <input type="hidden" name="email" value="${user.email}" />
                                    <button name="subject" type="submit" value="prj311" class="btn btn-outline-primary">Take A Quiz</button>
                                </form>
                            </div>
                        
                    </div>
                </div>
                <div class="col-sm-4"></div>
            </div>
        </div>

    </body>
</html>
