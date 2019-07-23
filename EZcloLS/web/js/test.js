var thisfilename;
var renamefile;
var deletefilename;
$(function () {
    //get all file & modal when first enter this page.
    ableFile();
    OptionFileModal();
    testFile();
    //click newfile
    $(document).on("click", "#newfile", function () {
        var newfile = $("#recipient-name1").val();
        $.post("/EZcloLS/FileManager/NewFileView.jsp", {newfile: newfile}, function (data) {
            $(".file-card-area").html(data);
        })
        $("#exampleModal1").modal('hide');
        newFileSuccess();
    })
    //click renamefile
    thisfilename = getEditFileName();

    $(document).on("click", "#renamefile", function () {
        rename = $(this).parent().siblings(".modal-body").find("#recipient-name3").val()
        renamefile = rename;
        //alert(thisfilename);
        //alert(renamefile);
        $.post("/EZcloLS/RenameFileController", {rename: renamefile, thisname: thisfilename}, function (data) {
            ableFile();
            OptionFileModal();
        })
        bindModal();
        closeModal();
    })
    //click deletefile
  
    deletefilename = getDeleteFileName();
    $(document).on("click", "#deletefile", function () {
        $.post("/EZcloLS/DeleteFileController", {deletefilename: deletefilename}, function (data) {
            ableFile();
            OptionFileModal();
        })
        bindModal();
        closeModal();
    })
    //click new test
        $(document).on("click", "#newtest", function () {
        var newtest = $("#recipient-name2").val();
        $.post("/EZcloLS/FileManager/NewTestView.jsp", {newtest: newtest}, function (data) {
            $("tbody").html(data);
        })
        $("#exampleModal2").modal('hide');
        newTestSuccess();
    })
    //log out
    $()
    
})
function ableFile() {
    $.get("/EZcloLS/FileManager/AbleFile.jsp", function (data) {
        $(".file-card-area").html(data)
    })
}
function testFile() {
    $.post("/EZcloLS/FileManager/NewTestView.jsp", function (data) {
            $("tbody").html(data);
        })
}
function OptionFileModal() {
    $.get("/EZcloLS/FileManager/OptionFileModal.jsp", function (data) {
        $("#modalgrow").html(data);
    })
}

function getEditFileName() {
    $(document).on("click", ".file-edit", function () {
        thisfilename = $(this).parent().siblings("p").text();
        //alert("thisfilename  "+thisfilename)
        return thisfilename
    })
}
function getDeleteFileName() {
    $(document).on("click", ".file-edit", function () {
        deletefilename = $(this).parent().siblings("p").text();
        //alert("thisfilename  "+thisfilename)
        return deletefilename
    })
}
function getFileRename() {
    $(document).on("click", ".file-edit", function () {
        var i = $(this).siblings().find(".rename").attr("data-target");
        // alert(i);
    })
}
function getFileDelete() {
    $(document).on("click", ".file-edit", function () {
        var i = $(this).siblings().find(".delete").attr("data-target");
        // alert(i);
    })
}
function activeElement() {
    rename = $(this).parent().siblings().find("p").val();
    return rename;
}
//function closeModal(thisfilename) {
//    var nowmodal = "#" + thisfilename;
//    nowmodal = $(nowmodal);
//    return nowmodal;
//}

function newFileSuccess() {
    $("#recipient-name1").val("")
}
function newTestSuccess() {
    $("#recipient-name2").val("")
}









function bindModal() {
    $(".modal").bind("hide.bs.modal", function () {
        $(".modal-backdrop").remove();
    })
}
function closeModal() {
    $(".modal").map(function () {
        if (!$(this).is(":hidden")) {
            $(this).modal('hide');
        }
    });
}


