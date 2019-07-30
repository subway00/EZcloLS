<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%if (session.getAttribute("email") == null) {
        response.sendRedirect("/EZcloLS/index/login.jsp");
    }%> 
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Ezclo Testing</title>
        <link href="/EZcloLS/css/ExamStyle.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css" >
         
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    </head>

    <body>

        <div class="container-fluid">
            <div class="header row align-items-end">
                <div class="logo col-6 col-md-8 d-flex align-items-end">
                    <h1><a href="#"><img src="/EZcloLS/img/logo-f.svg" alt=""/></a></h1>
                    <p>&nbsp;&nbsp;&nbsp;</p>
                    <b  class="d-none d-md-block">Memorize Vacabulary By Cloze</b>
                </div>

                <div class="account-box col-6 col-md-4 ">
                            <div id="modal-account"></div>
                </div>
            </div>


            <div class="content mt-2">
                <div class="info_box row ">
                    <fieldset disabled class="col-12 col-md-6 ">	
                        <input type="text" id="disabledTextInput1"  placeholder="考試科目: 英文">	
                    </fieldset>

                    <fieldset disabled class="col-12 col-md-6 ">	
                        <input type="text" id="disabledTextInput2"  placeholder="試卷名稱: ${Paper.getName()}">	
                    </fieldset>

                    <fieldset disabled class="col-12 col-md-6 ">
                        <input type="text" id="disabledTextInput3"  placeholder="監考人員: EZcloLS">	
                    </fieldset>

                    <fieldset disabled class="col-12 col-md-6 ">
                        <input type="text" id="disabledTextInput4"  placeholder="應考人員:${Email}">	
                    </fieldset>
                </div>

                <div id="result_panel" style="display: none">
                    <div id="text-test-result" >測驗結果</div>
                    <div class="info_box d-flex row">
                        <fieldset disabled class="col-6 col-lg">	
                            <input type="text" id="disabledTextInput5" placeholder="測驗試卷:">	
                        </fieldset>

                        <fieldset disabled class="col-6 col-lg">
                            <input type="text" id="disabledTextInput6" placeholder="總填空數:">	
                        </fieldset>

                        <fieldset disabled class="col-6 col-lg">
                            <input type="text" id="disabledTextInput7" placeholder="答對數:">
                        </fieldset>

                        <fieldset disabled class="col-6 col-lg">
                            <input type="text" id="disabledTextInput8" placeholder="命中率:">	
                        </fieldset>

                        <fieldset disabled class="col-6 col-lg">
                            <input type="text" id="disabledTextInput9" placeholder="本次成績:">	
                        </fieldset>
                    </div>
                </div>
            </div>

            <div class = "mt-3 row">
                <div class="test-content-title">
                    試卷內容
                </div>
                <iframe id="exam_panel" class="flex-fill test-content" src="/EZcloLS/ConstructExam?T_Number=${Paper.getT_number()}" ></iframe>	
            </div>

            <div class="fixed-bottom d-flex justify-content-end mb-3 mr-5">

                <div id="opt_exam" >
                    <div class="submit-exam-btn float-left" onclick="Ajax_submit_paper(${Paper.getT_number()})">
                        交卷
                    </div>
                    <div class="display-article-btn float-left" onclick="showAnswer()">
                        原文
                    </div>
                    <div class="file-manager-btn float-left" onclick="self.location = document.referrer;">
                        返回
                    </div>
                </div>

                <div id="opt_exexam" class=" d-none">
                    <div class="display-article-btn  float-left" onclick="showAnswer()">
                        原文
                    </div>
                    <div class="file-manager-btn  float-left" onclick="self.location = document.referrer;">
                        返回
                    </div>
                </div>
            </div>



            <div class="footer row ">
                <div class="footertext">
                    <h5>臺北市職能發展學院</h5>
                    <h6>臺北市士林區士東路301號</h6>
                </div>
            </div>		
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>                
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script> 
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="/EZcloLS/js/ExamScript.js"></script>
        <script src="/EZcloLS/js/AccountMenu.js"></script>
    </body>
</html>
