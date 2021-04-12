<%-- 
    Document   : showHistory
    Created on : Mar 2, 2021, 9:31:35 PM
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
        <title>History Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <div class="container">
            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col">
                    <div class="header">
                        <a href="" class="logo">Quiz Online</a>
                        <div class="header-right">
                            <c:if test="${user.role == 'admin'}">
                                <a href="loadQuestionAdmin">Home</a>
                            </c:if>
                            <c:if test="${user.role == 'student'}">
                                <a href="homeStudent.jsp">Home</a>
                            </c:if>
            
                            <a style="color: red;" >Welcome, ${user.name}</a>
                            <c:if test="${user.role == 'admin'}">
                                <a href="addNewQuestion.jsp">Add New Question</a>    
                            </c:if>
                            <a class="active" href="#">Show History</a>
                            <a href="logOut">Log out</a>
                        </div>
                    </div> 
                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>   
        <div style="margin-top: 40px;"></div>
        <c:if test="${user.role == 'admin'}">
            <div style="width: 80%; margin: auto;">

                <div class="container">
                    <div class="row">
                        <div class="col-sm-2"></div>
                        <div class="col">
                            <form action="showHistoryQuiz">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <button class="btn btn-outline-primary" type="submit">Search</button>
                                    </div>
                                    <c:if test="${param.subjectID == null}">
                                        <select class="custom-select" id="inputGroupSelect03" name="subjectID">
                                            <option value="prj321">PRJ321</option>
                                            <option value="prj311">PRJ311</option>
                                        </select>    
                                    </c:if>
                                    <c:if test="${param.subjectID == 'prj321'}">
                                        <select class="custom-select" id="inputGroupSelect03" name="subjectID">
                                            <option value="prj321">PRJ321</option>
                                            <option value="prj311">PRJ311</option>
                                        </select>    
                                    </c:if>
                                    <c:if test="${param.subjectID == 'prj311'}">
                                        <select class="custom-select" id="inputGroupSelect03" name="subjectID">
                                            <option value="prj311">PRJ311</option>
                                            <option value="prj321">PRJ321</option>
                                        </select>    
                                    </c:if>
            
                                </div>
                            </form>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>
                </div>

            </div>
        </c:if>

        <c:set var="list" value="${requestScope.LISTRESULT}"/>
        <div class="container">
            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col">
                    <c:if test="${not empty list}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Email</th>
                        <th scope="col">subjectID</th>
                        <th scope="col">Date Take Quiz</th>
                        <th scope="col">Time Take Quiz</th>
                        <th scope="col">Answer Correct</th>
                        <th scope="col">Total Question</th>
                        <th scope="col">Score</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <th scope="row">${counter.count}.</th>
                            <td>${dto.email}</td>
                            <td>${dto.subjectID}</td>
                            <td>${dto.dateTakeQuiz}</td>
                            <td>${dto.timeTakeQuiz}</td>
                            <td>${dto.numberCorrect}</td>
                            <td>${dto.numberQuestion}</td>
                            <td>${dto.score}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>

    </body>
</html>
