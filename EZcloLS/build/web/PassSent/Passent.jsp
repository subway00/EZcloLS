<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EZcloLS PasswordSent</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
        <link href="/EZcloLS/css/PassStyle.css" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    </head>

    <body>
        <div class="container-fluid">

            <div class="header row">
                <div class="logo col-6">
                    <h1><a href="/EZcloLS/index/login.jsp"><img src="/EZcloLS/img/logo-f.svg" alt=""></a></h1>
                    <b>Memorize Vacabulary By Cloze</b> 
                </div>


                <div class="nav col">
                    <div class="nav-box">
                        <div class="options"> <a class= "nonActive" href="/EZcloLS/index/login.jsp">登入</a> </div>
                        <div class="options"> <a class= "nonActive" href="/EZcloLS/Register/Register.jsp">註冊</a> </div>
                        <div class="options"> <a class= "active" href="#">忘記密碼</a> </div>
                    </div>
                </div>
            </div>





            <div class="content row">
                <div class="vaild col d-none d-lg-block">

                </div>
                <div class="pass-sent-form col">
                    <div class="note">
                        <div class="note-text">
                            <p> 您可以透過以下步驟取得新密碼</p>
                            <p>1.在本頁輸入您註冊時的信箱並提交給我們</p>
                            <p>2.我們會在半小時內將新密碼寄到您的信箱</p>
                            <p>3.請您用新密碼重新登入 </p>
                        </div>
                    </div>
                    <div class="pass-form">
                        <form action="/EZcloLS/RecoverAccServlet" method="POST" onsubmit="loading('處理中......')">
                            <div class="form-content form-group">

                                <p></p>
                                <label for="exampleInputEmail1">您的會員信箱</label>
                                <input name = "email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                <small id="emailHelp" class="form-text text-muted">請輸入您註冊時的信箱</small>
                                <div class="button-box">
                                    <input type="submit" class="btn btn-primary" value="提交信箱" width="200" >
                                </div>
                            </div>
                        </form>
                    </div>
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
        <script src="../js/Tools.js" type="text/javascript"></script>
    </body>
</html>