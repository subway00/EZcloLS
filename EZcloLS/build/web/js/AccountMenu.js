var pw1;
var pw2;

$(function () {
    $.get("/EZcloLS/AccountMenu/Menu.jsp", function (data) {
        $("#modal-account").html(data);
    });
    
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
    });
    
    $(document).on("blur", "#accIF_pw2", function () {
        $("#exampleModalLong").find("p").text("");
        pw2 = $(this).val();
        console.log(pw2);
        judgePW(pw1, pw2);
    });
//    $("#exampleModalLong").siblings("p").text(judgePW(pw1, pw2));
    //reverse PW
    $(document).on("click", "#reversePwbtn", function () {
        $.post("/EZcloLS/ReversePw", {pw: pw2});
        bindModal();
        closeModal();
        $("#accIF_pw1").val("");
        $("#accIF_pw2").val("");
        alert("密碼變更成功");
    });
});

function isEmptyObject(obj) {
    for (var key in obj) {
        return false;
    }
    return true;
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
    if (pw1 !== pw2) {
        result = "密碼輸入錯誤，請重新輸入";
        $("#accIF_pw1").val("");
        $("#accIF_pw2").val("");
        $("#exampleModalLong").find("p").text(result);
        console.log(result);
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


