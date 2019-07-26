<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%if (session.getAttribute("email") == null) {
        response.sendRedirect("/EZcloLS/index/login.jsp");
    }%> 
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>EZcloLS Edit Cloze</title>
        <link href="/EZcloLS/css/EditStyle.css" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
    </head>

    <body>

        <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel2">使用說明</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body ">
                        <form>
                            <div class="row">
                                <div class="form-group readme-panel col-12 col-md-6">
                                    <img src="/EZcloLS/img/editor.svg"  alt=""/>
                                </div>
                                <div class="readme-exp-panel list-group col-12 col-md-6">
                                    <div class="form-group list-group-item list-group-flush">
                                        <img src="/EZcloLS/img/cloze.svg"  alt=""/>
                                        <label for="recipient-name" class="col-form-label">克漏字工具</label>
                                        <p>製作填空-可將反白的文字轉換為填空</p>
                                        <p>取消填空-可將選取的填空轉換為文字</p>
                                    </div>
                                    <div class="form-group list-group-item">
                                        <img src="/EZcloLS/img/toolbar.svg" alt=""/>
                                        <label for="recipient-name" class="col-form-label">基本工具列</label>
                                        <p>提供文字編輯的基本操作、如復原、重做、複製、 剪下、貼上、儲存</p>
                                    </div>
                                    <div class="form-group list-group-item">
                                        <img src="/EZcloLS/img/paper.svg" alt=""/>
                                        <label for="recipient-name" class="col-form-label">文章編輯</label>
                                        <p>使用者您可將需要的文章放至此處編輯</p>
                                    </div>
                                    <div class="form-group list-group-item">
                                        <img src="/EZcloLS/img/rename.svg" alt=""/>
                                        <label for="recipient-name" class="col-form-label">試卷名稱</label>
                                        <p>此處將會顯示您的試卷名稱</p>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <div class="container-fluid">        
            <div class="header row align-items-end">
                <div class="logo col-12 col-md-8 d-flex align-items-end">
                    <h1><a href="#"><img src="/EZcloLS/img/logo-f.svg" alt=""/></a></h1>
                    <p>&nbsp;&nbsp;&nbsp;</p>
                    <b>Memorize Vacabulary By Cloze</b> 
                </div>

                <div class="account-box col-12 col-md-4 ">
                    <div class="dropdown d-flex justify-content-end">
                        <button class="account-botton btn btn-secondary dropdown-toggle " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
                            <span>一般會員 howard</span> 
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> 
                            <a class="dropdown-item" href="#">帳號資訊</a> <a class="dropdown-item" href="#">登出</a> 
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-2">
                <div class="rename col-12 col-lg-4 p-3" >
                    <div class="rename-box">
                        <div class="input-group">
                            <input id = "name" type="text" minlength="1"  maxlength="20" class="form-control" placeholder="Original file name" aria-label="Recipient's username" aria-describedby="button-addon2" value="${Paper.getName()}">
                            <div class="input-group-append">
                                <button class="btn btn-outline-info" type="button" id="button-addon2">重新命名</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="toolbar-box col-12 col-lg-8 p-3">
                    <form id="mainform" class="row" >
                        <div class="toolbar-btn col-2 text-center" onClick="previous()">
                            <button type="button" class="badge-pill w-100 " > 復原 </button>
                        </div>
                        <div class="toolbar-btn col-2 text-center" onClick="next()">
                            <button type="button" class="badge-pill w-100"> 重做 </button>
                        </div>
                        <div class="toolbar-btn col-2 text-center" onClick="copy()">
                            <button type="button" class="badge-pill w-100"> 複製 </button>
                        </div>
                        <div class="toolbar-btn col-2 text-center" onClick="cut()">
                            <button type="button" class="badge-pill w-100"> 剪下 </button>
                        </div>
                        <div class="col-2 text-center toolbar-btn" onClick="paste()">
                            <button type="button" class="badge-pill w-100"> 貼上 </button>
                        </div>
                        <div class="save-btn col-2 text-center" onclick="save_s(${Paper.getF_number()},${Paper.getT_number()})">
                            <button type="button" class="badge-pill w-100" >存檔 </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="article">以下請貼上想要製作克漏字的文章</div>
            </div>
            <div class="row">
                <div id="cloze_toolbar" class="make-cloze-bar">
                    <div class="d-inline-flex">
                        <div>
                            <div class="make-clo-btn" onClick="cloze()"> 
                                <img src="/EZcloLS/img/excavator.svg" alt=""/>
                                <p>製作填空</p>
                            </div>
                            <div class="make-clo-btn" onClick="uncloze()"> 
                                <img src="/EZcloLS/img/brickwall.svg" alt=""/>
                                <p>取消填空</p>
                            </div>
                            <div class="make-clo-btn"  data-toggle="modal" data-target="#exampleModal2"> 
                                <img src="/EZcloLS/img/manual.svg" alt=""/>
                                <p>使用說明</p>
                            </div>
                        </div>
                        <div id="text-cloze-bar" class="toggle"></div>
                    </div>

                </div>
                <iframe id="edit_panel" class=" flex-fill cloze-area" src="/EZcloLS/ConstructPaper?T_Number=${Paper.getT_number()}" ></iframe>	
            </div>

            <div class="row footer">
                <div class="footertext">
                    <h5>臺北市職能發展學院</h5>
                    <h6>臺北市士林區士東路301號</h6>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script> 
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="/EZcloLS/js/EditorScript.js"></script>
    </body>
</html>
