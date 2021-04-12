<%-- 
    Document   : addNewQuestion
    Created on : Mar 1, 2021, 8:59:36 PM
    Author     : MONS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Add Question Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/header.css">
        <link rel="stylesheet" href="./css/manageQuizAdmin.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
                            <a href="loadQuestionAdmin">Home</a>
                            <a  >Welcome, ${user.name}</a>
                            <a class="active" href="#">Add New Question</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
        <div style="margin-top: 40px;"></div>
        <div style="width: 80%;margin: auto;">
            <c:set var="err" value="${requestScope.ERROR}"/>
            <div class="container">
                <div class="row">
                    <div class="col-sm-2"></div>
                <div class="col">
                    <form action="validAddNewQuestion">
                        <input type="hidden" name="email" value="${user.email}" />
                        <div class="input-group input-group-lg">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-lg">Question Content</span>
                            </div>
                            <input name="questionContent" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                                   value="${param.questionContent}" required>
                        </div>
        
                        <div class="groupAnswer">
        
                            <div style="margin-top: 10px;"></div>
                            <c:if test="${empty err.duplicateAnswer}">
                                <div class="input-group-prepend">
                                    <span style="color: green;font-size: 18px;" class="input-group-text" id="inputGroup-sizing-lg">Content
                                        Answer</span>
                                </div>
                                <input name="contentAnswer" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" value="" required>
                                <input name="contentAnswer" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" value="" required>
                                <input name="contentAnswer" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" value="" required>   
                                <div class="input-group input-group-lg">
                                    <div class="input-group-prepend">
                                        <span style="color: blue;" class="input-group-text" id="inputGroup-sizing-lg">Answer Correct</span>
                                    </div>
                                    <input name="answerCorrect" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                                           value="" required>
                                </div>
                            </c:if>
                            <c:if test="${not empty err.duplicateAnswer}">
                                <h2 style="color: red;">Duplicate Answer!!!</h2>
                                <div class="input-group-prepend">
                                    <span style="color: green;font-size: 18px;" class="input-group-text" id="inputGroup-sizing-lg">Content
                                        Answer</span>
                                </div>
                                <c:set var="errContentAnswer" value="${requestScope.ANSWERDUPLI}"/>
                                <c:forEach var="cont" items="${errContentAnswer}">
                                    <input style="border: 2px solid red;" name="contentAnswer" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" value="${cont}" required>       
                                </c:forEach>
                                <div class="input-group input-group-lg">
                                    <div class="input-group-prepend">
                                        <span style="color: blue;" class="input-group-text" id="inputGroup-sizing-lg">Answer Correct</span>
                                    </div>
                                    <input style="border: 2px solid red;" name="answerCorrect" type="text" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm"
                                           value="${param.answerCorrect}" required>
                                </div>
                            </c:if>
        
        
        
                        </div>
                        Subject
                        <select class="custom-select" name="subjectID">
                            <option value="PRJ321">PRJ321</option>
                            <option value="PRJ311">PRJ311</option>
                        </select>
                        Status
                        <select class="custom-select" name="status">
                            <option value="true">Active</option>
                            <option value="false">InActive</option>
                        </select>
                        <div class="dropdown-divider"></div>
                        <div style="margin-top: 20px;"></div>
                        <div style="margin: auto; width: 80%;">
                            <button  type="submit" class="btn btn-outline-info btn-lg btn-block">Add New Question</button>
                        </div>
                        <div style="margin-top: 20px;"></div>
                    </form>
                </div>
                <div class="col-sm-2"></div>
                </div>
            </div>
        </div>           


    </body>
</html>
