<%-- 
    Document   : doquiz
    Created on : Mar 1, 2021, 7:58:16 AM
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
        <title>DO QUIZ</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col">
                    <div class="header">

                        <c:set var="user" value="${sessionScope.USER}"/>
                        <a href="#" class="logo">Quiz Online</a>
                        <div class="header-right">
                            <a>Welcome, ${user.name}</a>
                            <a class="active" href="#">DO QUIZ</a>
                            <a>
                                <div style="color: red;"> Timeout <span id="time">${param.mRemaining}:${param.sRemaining}</span></div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
        <div style="margin-top: 40px;"></div>
        <c:set var="map" value="${requestScope.MAPDISPLAYQUIZ}"/>
        <c:if test="${not empty map}">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <c:forEach var="dto" items="${map}">
                <form action="takeQuizPrj" method="POST">
                    <div class="row" style="margin: auto;">
                        <div class="col-sm-2">
                        </div>
                        <div class="col-sm-2">
                            <div class="card border-primary mb-3" style="max-width: 18rem;">
                                <div class="card-header key">
                                    <h4>Question ${dto.key}</h4>
                                </div>
                            </div>
                        </div>
                        <c:set var="listAnswer" value="${dto.value}"/>
                        <c:forEach var="answer" items="${listAnswer}">
                            <c:set var="questionID" value="${answer.questionID}" />
                            <c:set var="questionContent" value="${answer.questionContent}"/>
                        </c:forEach>
                        <input type="hidden" name="key" value="${questionID}" />
                        <div class="col-sm-6">
                            <div class="card border-primary mb-3">
                                <div class="card-header">
                                    <h4>${questionContent}</h4>
                                </div>
                                <div class="card-body text-primary">
                                    <h5 class="card-title">Select One:</h5>
                                    <p class="card-text">
                                        <c:forEach var="answer" items="${listAnswer}">
                                            <c:if test="${answer.correct eq false}">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="userChoose" id="userChoose${answer.answerID}" value="${answer.answerID}">
                                                <label class="form-check-label" for="userChoose${answer.answerID}">
                                                    ${answer.answerContent}
                                                </label>
                                            </div>
                                        </c:if>
                                        <c:if test="${answer.correct eq true}">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="userChoose" id="userChoose${answer.answerID}" value="${answer.answerID}" checked>
                                                <label class="form-check-label" for="userChoose${answer.answerID}">
                                                    ${answer.answerContent}
                                                </label>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div style="margin-top: 20px;"></div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">

                            <c:set var="totalPage" value="${sessionScope.TOTALPAGE}"/>
                            <c:set var="pageIndex" value="${sessionScope.PAGEINDEX}"/>
                            
                            
                            <c:if test="${pageIndex<=1}">
                                <li class="page-item disabled">
                                    <button class="page-link" tabindex="-1">Previous</button>
                                </li>
                            </c:if>
                            <c:if test="${pageIndex>1}">
                                <li class="page-item ">
                                    <button name="pageIndex" value="${pageIndex-1}" type="submit" class="page-link">Previous</button>
                                </li>
                            </c:if>

                                
                                
                            <c:forEach var="page" begin="1" end="${totalPage}">
                                <c:if test="${pageIndex == page}">
                                    <li class="page-item active">
                                        <button name="pageIndex" value="${page}" type="submit" class="page-link">${page}</button>
                                    </li>    
                                </c:if>
                                <c:if test="${pageIndex != page}">
                                    <li class="page-item ">
                                        <button name="pageIndex" value="${page}" type="submit" class="page-link">${page}</button>
                                    </li>
                                </c:if>
                            </c:forEach>
                                    
                                    
                                    
                            <c:if test="${pageIndex>=totalPage}">
                                <li class="page-item disabled">
                                    <button class="page-link" tabindex="-1">Next</button>
                                </li>
                            </c:if>
                            <c:if test="${pageIndex<totalPage}">
                                <li class="page-item ">
                                    <button name="pageIndex"  value="${pageIndex+1}" type="submit" class="page-link">Next</button>
                                </li>
                            </c:if>

                        </ul>
                    </nav>
                    <input type="hidden" id="timeRemaining" name="timeRemaining"/>
                    <input type="hidden" id="mRemaining" name="mRemaining"/>
                    <input type="hidden" id="sRemaining" name="sRemaining"/>

                    <div class="row" style="margin: auto;">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-2"></div>
                        <div class="col-sm-6">

                            <input type="hidden" name="totalPage" value="${totalPage}" />
                            <input type="hidden" name="email" value="${user.email}" />
                            <button id="finish" style="float: right;" name="btAction" value="FinishQuizServlet" type="submit" class="btn btn-outline-success">Finished</button>
                        </div>
                    </div>
                </form>

            </c:forEach>
                    </div>
                </div>
            </div>

        </c:if>




        <script>
            <c:set var="timeout" value="${sessionScope.TIMEOUT}"/>
            document.getElementById("timeRemaining").value = ${timeout};
            function startTimer(duration, display) {
                var timer = duration, minutes, seconds;
                setInterval(function () {

                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    display.textContent = minutes + ":" + seconds;
                    document.getElementById("timeRemaining").value = timer;
                    document.getElementById("mRemaining").value = minutes;
                    document.getElementById("sRemaining").value = seconds;
                    if (--timer < 0) {
                        document.getElementById("finish").click(); // Click on the checkbox
                    }
                }, 1000);
            }

            window.onload = function () {
                var fifteenMinutes = ${timeout},
                        display = document.querySelector('#time');
                startTimer(fifteenMinutes, display);
            };
        </script>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    </body>
</html>
