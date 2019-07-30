<%@page contentType="text/html" pageEncoding="UTF-8"%><!doctype html>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EZcloLS Login</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
        <link href="/EZcloLS/css/LoginStyle.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <body>
        <div class="container-fluid" p-0>
            <div class="header row">
                <div class="logo col-6">
                    <h1><a href="/EZcloLS/index/login.jsp"><img  src="/EZcloLS/img/logo-f.svg" alt=""/></a></h1>
                    <b>Memorize Vacabulary By Cloze</b> </div>
                <div class="nav col">
                    <div class="nav-box" >
                        <div class="options"> <a class= "active" href="#" >登入</a> </div>
                        <div class="options"> <a class= "nonActive" href="/EZcloLS/Register/Register.jsp">註冊</a> </div>
                        <div class="options"> <a class= "pre nonActive" href="/EZcloLS/AboutUs/AboutUs.jsp">體驗EZclo</a> </div>
                    </div>
                </div>
            </div>




            <div class="content row">
                <div class="vaild col  d-none d-lg-block"> </div>
                <div class="regiForm col">
                    <div class="loginbox login-form">


                        <form action="/EZcloLS/LoginController" method="POST">
                            <div class="form-group">
                                <label for="exampleInputEmail1">會員電子信箱</label>
                                <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                <small id="emailHelp" class="form-text text-muted">請輸入您註冊時的電子信箱</small> </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">會員密碼</label>
                                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="bottonBox row">
                                <div class="signbox">
                                    <input type="submit" value="登入系統" class="btn btn-primary">
                                    <!-- <h5>登入系統</h5> -->
                                    <!--              </button>-->
                                </div>
                                <div class="smalloptions"> <a href="/EZcloLS/PassSent/Passent.jsp">忘記密碼</a> <a href="/EZcloLS/Register/Register.jsp">我要註冊</a> </div>
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
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script> 
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    </body>
</html>