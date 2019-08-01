$(function () {
    $(".exam").css({"overflow": "scroll"});
    //css transfer
    var clickfile;
    $(document).on("click", ".file", function () {
        $(".file").css({"border": "none"});
        $(this).css({"border": "solid #00B4AA"});
        clickfile = $(this);
    })
    $(document).on("mouseover", ".file", function () {
        $(this).css({"border": "solid #00B4AA"})
    })
    $(document).on("mouseleave", ".file", function () {
        $(".file").not(clickfile).css({"border": "none"})
    })
    //get all file & modal when first enter this page.
    ableFile();
    OptionFileModal();
    ableTest();
    //click newfile
    $(document).on("click", ".newfile", function () {
        newfile = $("#recipient-name1").val();
        if (nameProtect(newfile)) {
            $("#recipient-name1").val("");
        } else {
            $.post("/EZcloLS/NewFileCheckController", {newfile: newfile}, function (data) {
                if (data === "ERROR") {
                    alert("重複資料夾名稱，請重新輸入");
                    $("#recipient-name1").val("");
                } else {
                    $.post("/EZcloLS/FileManager/NewFileView.jsp", {newfile: newfile}, function (data) {
                        $(".file-card-area").html(data);
                        ableFile();
                        OptionFileModal();
                        bindModal();
                        closeModal();
                    });
                }
            });
        }
    });
    //click renamefile
    $(document).on("click", ".file-edit", function () {
        thisfilename = $(this).parent().siblings("p").text();
        $(document).on("click", ".renamefile", function () {
            rename = $(this).parent().siblings(".modal-body").find(".rename").val();
            if (nameProtect(rename)) {
                $(this).parent().siblings(".modal-body").find(".rename").val("");
            } else {
                $.post("/EZcloLS/NewFileCheckController", {rename: rename}, function (data) {
                    $.post("/EZcloLS/RenameFileController", {rename: rename, thisname: thisfilename}, function () {
                        ableFile();
                        OptionFileModal();
                        bindModal();
                        closeModal();
                    });
                })
            }
        });
    });
    //click delete file
    $(document).on("click", ".file-edit", function () {
        var deletefilename = $(this).parent().siblings("p").text();
        $(document).on("click", ".deletefile", function () {
            $.post("/EZcloLS/DeleteFileController", {deletefilename: deletefilename}, function () {
                ableFile();
                OptionFileModal();
                bindModal();
                closeModal();
            });
        });
    })
    //click new test
    $(document).on("click", ".newtest", function () {
        var newtest = $("#recipient-name2").val();
        if (nameProtect(newtest)) {
            $("#recipient-name2").val("");
        } else {
            $.post("/EZcloLS/FileManager/NewTestView.jsp", {newtest: newtest}, function (data) {
                newTestCheck(data);
                OptionFileModal();
                bindModal();
                closeModal();
//                examScroll();
            });
        }
    });

    //click rename test
    $(document).on("click", ".renametest", function () {
        var testnumber = $(this).siblings("input").val();
        var rename = $(this).parents().siblings(".modal-body").find(".rename").val();
        if (nameProtect(rename)) {
            $(this).parents().siblings(".modal-body").find(".rename").val("");
        } else {
            $.post("/EZcloLS/FileManager/RenameTestView.jsp", {testnumber: testnumber, rename: rename}, function (data) {
                $("tbody").html(data);
                OptionFileModal();
                bindModal();
                closeModal();

            })
        }
    })
    //click delete test
    $(document).on("click", ".deletetest", function () {
        var testnumber = $(this).siblings("input").val();
        $.post("/EZcloLS/FileManager/DeleteTestView.jsp", {testnumber: testnumber}, function (data) {
            $("tbody").html(data);
            OptionFileModal();
            bindModal();
            closeModal();
        })
    })
    //Click file get test
    $(document).on("click", ".card-header p", function () {
        clickfile = $(this).text();
        $.post("/EZcloLS/FileManager/AbleTest.jsp", {clickfile: clickfile}, function (data) {
            $("tbody").html(data);
        })
        $.post("/EZcloLS/FileManager/OptionFileModal.jsp", {clickfile: clickfile}, function (data) {
            $("#modalgrow").html(data);
        })
    })
    //Account Inf
    $(document).on("click", ".accountInf", function () {
        $.post("/EZcloLS/AccountInfController", function (jsonObj) {
            $("#exampleModalLong").find("#accIF_email").val(jsonObj.M_Email);
            $("#exampleModalLong").find("#accIF_gender").val(genderConvert(jsonObj.M_Gender));
            $("#exampleModalLong").find("#accIF_born").val(jsonObj.M_Born);
            $("#exampleModalLong").find("#accIF_finishTest").val("已測驗試卷: " + jsonObj.FinishTest);
            $("#exampleModalLong").find("#accIF_totalQuestions").val("總答題數: " + jsonObj.TotalQuestions);
            $("#exampleModalLong").find("#accIF_rightCount").val("答對題數: " + jsonObj.RightCount);
            $("#exampleModalLong").find("#accIF_rightRate").val("命中率: " + jsonObj.RightRate + "%");
        });
    });
    //帳號資訊:判斷兩次密碼是否相同
    $(document).on("blur", "#accIF_pw1", function () {
        $("#exampleModalLong").find("p").text("");
        pw1 = $(this).val();
        console.log(pw1);
    })
    $(document).on("blur", "#accIF_pw2", function () {
        $("#exampleModalLong").find("p").text("");
        pw2 = $(this).val();
        console.log(pw2);
        judgePW(pw1, pw2);
    })
    //reverse PW
    $(document).on("click", "#reversePwbtn", function () {
        var pw1name = $("#accIF_pw1").val();
        var pw2name = $("#accIF_pw2").val();
        if (!pw1name || !pw2name) {
            alert("尚未輸入密碼，請重新輸入");
        } else {
            var checkPW1 = pw1.match(/^(?=.*\d)(?=.*[a-zA-Z]).{6,}$/);
            var checkPW2 = pw2.match(/^(?=.*\d)(?=.*[a-zA-Z]).{6,}$/);
//            var checkPW3 = pw1.match(/^\s/);
//            var checkPW4 = pw2.match(/^\s/);
            if (!checkPW1 || !checkPW2 ) {
                $("#exampleModalLong").find("p").text("輸入格式錯誤，請重新輸入");
            } else {
                $.post("/EZcloLS/ReversePw", {pw: pw2});
                bindModal();
                closeModal();
                $("#accIF_pw1").val("");
                $("#accIF_pw2").val("");
                alert("密碼變更成功");
            }
        }
        $("#accIF_pw1").val("");
        $("#accIF_pw2").val("");
    })

});
function ableFile() {
    $.get("/EZcloLS/FileManager/AbleFile.jsp", function (data) {
        $(".file-card-area").html(data);
    });
}
function ableTest() {
    $.get("/EZcloLS/FileManager/AbleTest.jsp", function (data) {
        $("tbody").html(data);
    });
}
function OptionFileModal() {
    $.get("/EZcloLS/FileManager/OptionFileModal.jsp", function (data) {
        $("#modalgrow").html(data);
    });
}
function getFileDelete() {
    $(document).on("click", ".file-edit", function () {
        var i = $(this).siblings().find(".delete").attr("data-target");
    });
}
function getTestDelete() {
    $(document).on("click", ".minus", function () {
        deletetestname = $(this).siblings().find(".testname").text();
        return deletetestname;
    })
}
function activeElement() {
    rename = $(this).parent().siblings().find("p").val();
    return rename;
}
function newFileSuccess() {
    $("#recipient-name1").val("");
}
function newTestSuccess() {
    $("#recipient-name2").val("");
}
function genderConvert(gender) {

    if (gender == "M") {
        gender = "男";
    } else if (gender == "F") {
        gender = "女";
    }
    return gender;
}
function judgePW(pw1, pw2) {
    var result;
    if (pw1 != pw2) {
        result = "密碼輸入錯誤，請重新輸入";
        $("#accIF_pw1").val("");
        $("#accIF_pw2").val("");
        console.log(result)
    }
    $("#exampleModalLong").find("p").text(result);
}
function newTestCheck(data) {
    var html = $.parseHTML(data);
    var ty = $(html).filter("tr");
    if (ty.length === 0) {
        alert("請先點選資料夾，再新增試卷");
    } else {
        $("tbody").html(data);
    }
}
function nameProtect(name) {
    var yn;
    var alertString;
    var checkinjection = name.match(/select|update|delete|exec|count|'|"|=|;|>|<|%/i);
    var strlength = name.length;
    var checkname = strlength <= 20;
    var checkNameSpace = name.match(/^\s/);
    console.log(checkNameSpace)
    if (checkinjection) {
        alertString = "請您不要在引數中輸入特殊字元和SQL關鍵字";
        yn = true;
    } else if (!checkname) {
        alertString = "超出最大名稱長度限制，請重新輸入";
        yn = true;
    } else if (checkNameSpace || !name) {
        alertString = "尚未輸入名稱，請重新輸入";
        yn = true;
    } else {
        return false;
    }
    alert(alertString);
    return yn;
}
function examScroll() {

}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function bindModal() {
    $(".modal").on("hide.bs.modal", function () {
        $(".modal-backdrop").remove();
    })
}
function closeModal() {
    $(".modal").modal('hide');
    $(document.body).removeClass("modal-open");
}


