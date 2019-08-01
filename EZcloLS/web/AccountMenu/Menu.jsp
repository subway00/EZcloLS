<%-- 
    Document   : Menu
    Created on : 2019/7/29, 上午 09:39:14
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="/EZcloLS/css/MenuStyle.css" rel="stylesheet" type="text/css">
<div class="dropdown d-flex justify-content-end ">
    <button class="account-botton btn btn-secondary dropdown-toggle " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
        <span>一般會員</span> 
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> 
        <!-- Button trigger modal 開始-->
        <a class="dropdown-item accountInf" data-toggle="modal" data-target="#exampleModalLong">帳號資訊</a>
        <!-- Button trigger modal結束 -->
        <a class="dropdown-item" data-toggle="modal" data-target="#exampleModalCenter">登出</a> 
    </div>
</div>
<!-- 帳號資訊欄視窗--------------->
<div class="modal fade" id="exampleModalLong"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle" >會員帳號</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"> </button>
            </div>
            <div class="modal-body">
                <div class="account-input-box">
                    <label><i class="fa fa-envelope" aria-hidden="true"></i></label>
                    <input type="text" readonly="value" id="accIF_email">
                    <br>

                    <label><i class="fa fa-venus-mars" aria-hidden="true"></i></label> 
                    <input type="text" readonly="value" id="accIF_gender">
                    <br>

                    <label><i class="fa fa-birthday-cake" aria-hidden="true"></i></label>
                    <input type="text" readonly="value" id="accIF_born">
                    <br>   

                    <label><i class="fa fa-file"></i></label>    
                    <input type="text" readonly="value" id="accIF_finishTest">
                    <br>

                    <label><i class="fa fa-list-ol" aria-hidden="true"></i></label>
                    <input type="text" readonly="value" id="accIF_totalQuestions">
                    <br> 

                    <label><i class="fa fa-check-square" aria-hidden="true"></i></label>
                    <input type="text" readonly="value" id="accIF_rightCount">
                    <br>  

                    <label><i class="fa fa-bullseye" aria-hidden="true"></i></label>	  
                    <input type="text" readonly="value" id="accIF_rightRate">
                    <br> <br>

                    <div class="change-password-box">	  
                        <b>修改密碼</b> <br>
                        <input type="password"  value="" id="accIF_pw1">
                        <label>輸入新密碼</label>  <br>  

                        <input type="password"  value="" id="accIF_pw2">
                        <label>確認新密碼</label>  <br> 
                        <p></p>
                    </div>  
                </div>  
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                <button type="button" class="btn btn-primary" id="reversePwbtn">更新密碼</button>
            </div>
        </div>
    </div>
</div>	
<!----------------------------------------------------------------------------------------------/帳號資訊欄視窗-->
<!--功能視窗-->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">系統通知</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                你確定要離開本系統嗎?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                <!-- <button type="button" class="btn btn-primary">登出系統</button> -->
                <form action="/EZcloLS/LogOutController" method="POST">
                    <input type="submit" value="登出系統" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>
</div>
