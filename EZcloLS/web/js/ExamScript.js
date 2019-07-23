// JavaScript Document

function prepareExam() {
    var answer = getfocus().getElementById("answer");
    var h_answer = document.getElementById("h_answer");
    answer.innerHTML = h_answer.textContent;
    
    var paper = getfocus().getElementById("content");
    var h_paper = document.getElementById("h_paper");
    paper.innerHTML = h_paper.textContent;
    
    var ans = answer.getElementsByTagName("input");
    for (var i = 0; i < ans.length; i++) {
        ans[i].setAttribute("placeholder",ans[i].name); 
    }
}

function getfocus() {
    'use strict';
    var frame = document.getElementById("exam_panel");
    var result = (frame.contentWindow || frame.contentDocument);
    if (result.document) {
        result = result.document;
    }
    return result;
}

function Ajax_submit_paper(num) {

    var content = getfocus().getElementById("content");
    var answer = getfocus().getElementById("answer");
    var con = content.getElementsByTagName("input");
    var ans = answer.getElementsByTagName("input");

    var inp = new Array();
    for (var i = 0; i < con.length; i++) {
        inp.push(con[i].value);
    }
    var comp = new Array();
    for (var i = 0; i < ans.length; i++) {
        comp.push(ans[i].name);
    }

    var data = "number=" + num;
    for(var i = 0;i<inp.length;i++){
        data = data.concat("&input=",inp[i]);
    }
    
    for(var i = 0;i<comp.length;i++){
        data = data.concat("&comp=",comp[i]);
    }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function (data) {
        if (this.readyState === 4 && this.status === 200) {
            var jsObj = JSON.parse(data.srcElement.responseText);
            var rs = jsObj.Result[0];

            for (var i = 0; i < con.length; i++) {
                con[i].setAttribute("readonly",true);
                if (rs[i]) {      
                    con[i].setAttribute("style", "background-color:aquamarine");
                }
                else{
                    con[i].setAttribute("style", "background-color:pink");
                }
            }
            
            document.getElementById("opt_exexam").classList.remove("d-none");
            document.getElementById("opt_exam").classList.add("d-none");

            document.getElementById("disabledTextInput5").value = "測驗試卷:" + jsObj.P_Count[0];
            document.getElementById("disabledTextInput6").value = "總填空數:" + jsObj.T_Block[0];
            document.getElementById("disabledTextInput7").value = "答對數:" + jsObj.T_Right[0];
            document.getElementById("disabledTextInput8").value = "命中率:" + jsObj.T_Rate[0] + "%";
            document.getElementById("disabledTextInput9").value = "本次成績:" + jsObj.Score[0];
            document.getElementById("result_panel").style.display = "inline-block";
        }
    }
    
    xhttp.open("POST", "CheckAnswerServlet", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhttp.send(data);
}

function showAnswer() {
    var exam = getfocus().getElementById("exam_panel");
    if (exam.className === "hiddentxt") {
        exam.setAttribute("class", "showtxt");
    } else {
        exam.setAttribute("class", "hiddentxt");
    }
}

