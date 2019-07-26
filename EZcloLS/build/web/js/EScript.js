// JavaScript Document

function prepareExam() {
    var answer = document.getElementById("answer");
    var ans = answer.getElementsByTagName("input");
    for (var i = 0; i < ans.length; i++) {
        ans[i].setAttribute("placeholder",ans[i].name); 
    }
}
