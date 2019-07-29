var thisfilename;
var renamefile;
var deletefilename;
var pw1
var pw2;

$(function () {
    //css transfer
    $(document).on("click", ".file", function () {
        $(".file").css({"border": "none"});
        $(this).css({"border": "solid #00B4AA"});
    })
    //get all file & modal when first enter this page.
    ableFile();
    OptionFileModal();
    ableTest();
    //click newfile
    $(document).on("click", ".newfile", function () {
        newfile = $("#recipient-name1").val();
        newFileLengthCheck(newfile);
        if (isEmptyObject(newfile)) {
            alert("尚未輸入資料夾名稱請重新輸入");
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
            $.post("/EZcloLS/NewFileCheckController", {rename: rename}, function (data) {
                $.post("/EZcloLS/RenameFileController", {rename: rename, thisname: thisfilename}, function () {
                    ableFile();
                    OptionFileModal();
                    bindModal();
                    closeModal();
                });
            })
        });
    });
    //click delete file
    $(document).on("click", ".file-edit", function () {
        deletefilename = $(this).parent().siblings("p").text();
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
        if (newTestLengthCheck(newtest)) {
            $("#recipient-name2").val("");
        } else {
            $.post("/EZcloLS/FileManager/NewTestView.jsp", {newtest: newtest}, function (data) {
                newTestCheck(data);
                OptionFileModal();
                bindModal();
                closeModal();
            });
        }
    });
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
    //click rename test
    $(document).on("click", ".renametest", function () {
        var testnumber = $(this).siblings("input").val();
        var rename = $(this).parents().siblings(".modal-body").find(".rename").val();
        $.post("/EZcloLS/FileManager/RenameTestView.jsp", {testnumber: testnumber, rename: rename}, function (data) {
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
//            ableFile();
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
    })
    $(document).on("blur", "#accIF_pw2", function () {
        $("#exampleModalLong").find("p").text("");
        pw2 = $(this).val();
        console.log(pw2);
        judgePW(pw1, pw2);
    })
    //reverse PW
    $(document).on("click", "#reversePwbtn", function () {
        var checkPW1 = pw1.match(/^(?=.*\d)(?=.*[a-zA-Z]).{6,}$/);
        var checkPW2 = pw2.match(/^(?=.*\d)(?=.*[a-zA-Z]).{6,}$/);
        if (!checkPW1 || !checkPW2) {
            $("#exampleModalLong").find("p").text("輸入格式錯誤，請重新輸入");
        } else {
            $.post("/EZcloLS/ReversePw", {pw: pw2});
            bindModal();
            closeModal();
            $("#accIF_pw1").val("");
            $("#accIF_pw2").val("");
            alert("密碼變更成功");
        }
    })
});
function isEmptyObject(obj) {
    for (var key in obj) {
        return false;
    }
    return true;
}
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
function newFileLengthCheck(newfile) {
//    console.log("newfile:   " + newfile);
    var strlength = newfile.length;
//    console.log("newfile length:   " + strlength);
    var checkfile = strlength <= 20;
//    console.log("checkfile:     "+ checkfile)
    if (!checkfile) {
        alert("超出最大資料夾名稱長度限制，請重新輸入")
//        alert("符合長度")
    }
}
function newFileSuccess() {
    $("#recipient-name1").val("");
}
function newTestLengthCheck(newtest) {
    var strlength = newtest.length;
    var checktest = strlength <= 20;
    if (!checktest) {
        alert("超出最大試卷名稱長度限制，請重新輸入")
        return true;
    } else {
        return false;
    }
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
    console.log(html);
    var ty = $(html).filter("tr");
    console.log(ty);
    if (ty.length === 0) {
        alert("請先點選資料夾，再新增試卷");
    } else {
        $("tbody").html(data);
    }
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


