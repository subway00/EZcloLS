<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    int i = 0;
    RequestDispatcher rd = request.getRequestDispatcher("/OptionModalController");
    rd.include(request, response);

%>
<!-- 帳號資訊欄視窗--------------->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">會員帳號</h5>
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
                    <div class="change-password-box valid">	  
                        <b>修改密碼</b> <br>
                        <input type="password"  id="accIF_pw1" class="form-control" placeholder="設定密碼">
                        <input type="password"  id="accIF_pw2" class="form-control" placeholder="確認密碼">
                        <br> 
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
<!--------------------------------------------------------------------------------------/功能視窗-->
<!--------------------新增資料夾視窗-->
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">新增學習夾</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">請為你的學習夾命名:</label>
                        <input type="text" class="form-control" id="recipient-name1">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary closeModal" data-dismiss="modal">關閉</button>
                <button type="button" class="btn btn-primary newfile" >建立學習夾</button>
            </div>
        </div>
    </div>
</div>
<!-----------------------------------------------------------------------------------------------/新增資料夾視窗-->
<!-- 新增試卷視窗 -------------------------->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">新增試卷</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">請為你的試卷命名:</label>
                        <input type="text" class="form-control" id="recipient-name2">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                <button type="button" class="btn btn-primary newtest" >建立試卷</button>
            </div>
        </div>
    </div>
</div>
<!---------------------------------------------------------------------------------/新增試卷視窗-->
<!-------------------刪除檔案視窗-->
<c:forEach var="filename" items="${ablefile}">
    <div class="modal fade" id="${IndexProducer.deleteIndex}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document" id="file-modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel4">刪除檔案</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">你確定要刪除"${filename}"這個學習夾嗎?</label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                    <button type="button" class="btn btn-primary deletefile" >刪除學習夾
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!------------------------------------------------------------------------------------------/刪除檔案視窗>
    <!-------------------更改檔案視窗-->
    <div class="modal fade" id="${IndexProducer.renameIndex}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel3">更改檔案名稱</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">請為你的學習夾重新命名</label>
                            <input type="text" class="form-control rename" >
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                    <button type="button" class="btn btn-primary renamefile" >更新檔名
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!--    ------------------------------------------------------------------------------------更改檔案視窗-->
</c:forEach >

<c:forEach var="testname" items="${abletest}">
    <!------------ 刪除試卷 -->
    <div class="modal fade" id="${IndexProducer.deleteTest}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel5">刪除檔案</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">你確定要刪除"${testname.tname}"這張試卷嗎?</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <form class="formclass">
                        <input type="hidden" name="T_Number" value="${testname.tnumber}">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                        <button type="button" class="btn btn-primary deletetest">刪除試卷</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-------------------------------------------------------------------------------------/刪除試卷視窗-->
    <!------------ 更改試卷視窗 -->
    <div class="modal fade" id="${IndexProducer.renameTest}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" >更改試卷名稱</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="formclass">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">請為你的試卷重新命名</label>
                            <input type="text" class="form-control rename">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <form>
                        <input type="hidden" name="T_Number" value="${testname.tnumber}">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
                        <button type="button" class="btn btn-primary renametest">更新檔名
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--------------------------------------------------------------------------------------------/更改試卷視窗-->
</c:forEach>