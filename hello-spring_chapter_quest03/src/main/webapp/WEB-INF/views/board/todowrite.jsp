<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>게시글 작성하기</title>



        <link rel="stylesheet" type="text/css" href="/css/common.css">


    </head>

    <body>
        <form method="post" action="/board/write">
            <div class="grid">
                <label for="subject">제목</label>
                <input id="subject" type="text" name="subject" />

                <label for="endDt">기한</label>
                <input id="endDt" type="date" name="endDt" />


                <div class="btn-group">
                    <div class="right-align">
                        <input type="submit" value="등록" />
                    </div>
                </div>

            </div>
        </form>

    </body>

    </html>