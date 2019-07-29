<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EZclo register</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
        <link href="/EZcloLS/css/RegStyle.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"  crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid" p-0>
            <div class="header row">
                <div class="logo col-6">
                    <h1><a href="/EZcloLS/index/login.jsp"><img src="/EZcloLS/img/logo-f.svg" alt=""/></a></h1>
                    <b>Memorize Vacabulary By Cloze</b>
                </div>
                <div class="nav col">
                    <div class="nav-box">
                        <div class="options"> <a class= "nonActive" href="/EZcloLS/index/login.jsp">登入</a> </div>
                        <div class="options"> <a class= "active" href="#">註冊</a> </div>
                        <div class="options"> <a class= "nonActive" href="/EZcloLS/AboutUs/AboutUs.jsp">體驗EZclo</a> </div>
                    </div>
                </div>
            </div>
            <div class="content row">
                <div class="vaild col  d-none d-lg-block"> </div>
                <div class="register-box col">
                    <form action="/EZcloLS/RegisterController" method="POST" id="register_form" role="form" data-toggle="validator">
                        <div class="email-box">
                            <label for="validationServer01">註冊信箱</label>
                            <input type="email" name="email" class="form-control " id="validationServer01" placeholder="example@example.com.tw"  required>
                            <div class="valid-feedback"> 有效信箱 </div>
                            <div class="invalid-feedback"> 信箱已註冊 </div>
                        </div>
                        <div class ="text-birth-Box"> 您的出生年/月/日&nbsp;
                            <div class="birth-box">
                                <label class="btn1 ">
                                    <input type="date" name="date" max="2019-12-31" min="1930-01-01" class="form-control" autocomplete="on"  required>
                                </label>
                            </div>
                        </div>
                        <div class="gender-check"> 會員性別&nbsp;&nbsp;&nbsp;
                            <div class="custom-control custom-radio custom-control-inline" >
                                <input type="radio" value="M" name="gender" id="customRadioInline1" class="custom-control-input"  checked="true" >
                                <label class="custom-control-label" for="customRadioInline1">男</label>
                            </div>
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" value="F" name="gender" id="customRadioInline2" class="custom-control-input">
                                <label class="custom-control-label" for="customRadioInline2">女</label>
                            </div>
                        </div>
                        <div class="pass-box">
                            <label>設定會員密碼</label>
                            <input type="password" name="password" id="inputPassword" class="form-control"  placeholder="設定密碼" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{6,}$" data-error="請輸入含有英文字母及數字的密碼，至少六個字元。" required="required">
                            <small>請輸入含有英文字母及數字的密碼，至少六個字元。</small>
                            <div class="help-block with-errors"></div>
                            <div class="form-group">
                                <input id="inputConfirmPassword" name="ConfirmPassword" class="form-control" type="password" placeholder="確認密碼" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{6,}$" data-match="#inputPassword" data-error="密碼未吻合！" required="required">
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                        <div class="agreement-box">
                            <div class="custom-control custom-checkbox mb-3">
                                <input type="checkbox" class="custom-control-input" id="customControlValidation1" required>
                                <label class="custom-control-label" for="customControlValidation1"><a href="#">同意EZcloLS Learning System 的使用條款</a> </label>
                            </div>
                        </div>
                        <div class="button-box">
                            <input type="submit" name="submit" value="提交註冊" class="btn btn-primary">
                        </div>
                    </form>
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