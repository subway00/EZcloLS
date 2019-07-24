<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%if(session.getAttribute("email")==null) response.sendRedirect("/EZcloLS/index/login.jsp");%> 

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EZclo File Management</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link href="/EZcloLS/css/homeStyle.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css" >
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    </head>
    <body>
        <div id="modalgrow">


        </div>
        <div class="container-fluid" p-0>
            <div class="header row">
                <div class="logo col">
                    <h1><a href="#"><img src="/EZcloLS/img/logo-f.svg" alt=""/></a></h1>
                    <b>Memorize Vacabulary By Cloze</b>
                </div>
                <div class="account-box col">
                    <!--                    <div class="dropdown">
                                            <button class="account-botton btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span>一般會員 howard</span> </button>
                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> <a class="dropdown-item" href="#">帳號資訊</a> <a class="dropdown-item" data-toggle="modal" data-target="#exampleModalCenter">登出</a> </div>
                                        </div>-->
                    <div class="dropdown">
                        <button class="account-botton btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <span>一般會員 howard</span> </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> 

                            <!-- Button trigger modal 開始-->
                            <a class="dropdown-item accountInf" data-toggle="modal" data-target="#exampleModalLong">帳號資訊</a>
                            <!-- Button trigger modal結束 -->
                            <a class="dropdown-item" data-toggle="modal" data-target="#exampleModalCenter">登出</a> </div>
                    </div>
                </div>
            </div>


            <div class="content row">
                <div class="file-area col-4">
                    <p id="file-title">您的學習夾</p>

                    <div class="add-file-box" data-toggle="modal" data-target="#exampleModal1">

                        <div class="add-file-text">
                            <h5>新增學習夾</h5></div>
                        <div class="add-file-pic">
                            <img src="/EZcloLS/img/folder (2).svg" alt=""/>
                        </div>

                    </div>
                    <!-- 資料夾 -->
                    <div class="file-card-area">

                    </div>
                </div>



                <div class="exam col">
                    <div class="add-exam row">
                        <p>您的試卷</p>
                        <div class="add-exam-button">
                            <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal2">新增試卷</button>

                        </div>
                    </div>



                    <table class="table table-hover" >
                        <thead>
                            <tr>
                                <th scope="exam-name col">#</th>
                                <th scope="exam-name col">檔案名稱</th>
                                <th scope="build-time col">建立時間</th>
                                <th scope="exam-timecol">上次測驗時間</th>
                                <th scope="col" width="280">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="footer row">
                <div class="footertext">
                    <h5>臺北市職能發展學院</h5>
                    <h6>臺北市士林區士東路301號</h6>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="/EZcloLS/js/FileManagerScript.js"></script>
    </body>
</html>

