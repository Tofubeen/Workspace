<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>로그인 화면</title>
                <link rel="stylesheet" type="text/css" href="/css/memberlogin.css">
                <script></script>
            </head>

            <body>
                <form:form modelAttribute="loginMemberVO" method="post">


                    <div>
                        <div class="grid">
                            <label for="id">아이디</label>
                            <input id="id" type="text" name="id" value="${loginMemberVO.id}" />

                            <label for="password">비밀번호</label>
                            <input id="password" type="password" name="password" value="${loginMemberVO.password}" />

                            <div class="btn-group">
                                <div class="right-align">
                                    <input id="btn-regist" type="submit" value="로그인" />
                                </div>
                            </div>

                            <div class="error-container">
                                <form:errors path="id" element="div" cssClass="errors" />
                            </div>
                            <div class="error-container">
                                <form:errors path="password" element="div" cssClass="errors" />
                            </div>
                            <c:if test="${not empty message}">

                                <div class="error-container">
                                    <div class="errors">
                                        ${message}
                                    </div>
                                </div>
                            </c:if>

                        </div>
                </form:form>
                </div>
            </body>

            </html>