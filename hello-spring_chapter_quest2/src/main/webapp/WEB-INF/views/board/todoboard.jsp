<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" type="text/css" href="/css/todoboard.css">
    </head>

    <body>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>완료?</th>
                    <th>TODO Subject</th>
                    <th>기한</th>
                    <th>비고</th>
                </tr>
            </thead>
            
            <tbody>
                <tr>
                    <td>1</td>
                    <td>DONE</td>
                    <td>Todo Item 추가하기</td>
                    <td>2024-10-01</td>
                    <td>삭제</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>DONE</td>
                    <td>Spring Boot Controller 익히기</td>
                    <td>2024-10-02</td>
                    <td>완료</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>DONE</td>
                    <td>Dao Mapper 연동하기</td>
                    <td>2024-10-01</td>
                    <td>삭제</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>DONE</td>
                    <td>JSP View 만들기</td>
                    <td>2024-10-10</td>
                    <td>삭제</td>
                </tr>
            </tbody>

        </table>
    </body>

    </html>