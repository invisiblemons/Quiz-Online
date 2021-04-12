<%-- 
    Document   : result
    Created on : Mar 2, 2021, 7:45:29 PM
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
        <title>RESULT</title>
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
                            <a href="homeStudent.jsp">Home</a>
                            <a style="color: red;" >Welcome, ${user.name}</a>
                            <a class="active" href="#">Result</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
        <div style="margin-top: 20px;"></div>
        <c:set var="result" value="${requestScope.RESULT}"/>
        <div style="width: 80%; margin: auto;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-2"></div>
                    <div class="col">
                        <div class="card border-primary mb-3" style="max-width: 80rem;">
                            <div class="card-header">FINISHED</div>
                            <div class="card-body text-primary" style="text-align: center;">
                                <p class="card-text">Number Of Question Correct: ${result.numberCorrect}</p>
                                <p class="card-text">Score: ${result.score}</p>
                                <a href="homeStudent.jsp" class="btn btn-outline-primary">Home</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-2"></div>
                </div>
            </div>
        </div>

    </body>

</body>
</html>
