// JavaScript Document

function cunstructNewPaper(t_num) {
    var fd =
            "T_Number=" + t_num;
    window.open('..ConstructPaperServlet?' + fd);
}

function prepareExam(t_num) {
    var fd = "T_Number=" + t_num;
    window.location.href = '/EZcloLS/PrepareExamServlet?'+fd;
}

function createNewFile(ele) {
    var name = ele.value;
    var data =
            "M_Number=" + number
            + " &F_Name=" + name;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function (data) {
        if (this.readyState === 4 && this.status === 200) {
            Ajax_getPaperList(number);
        }
    };
    xhttp.open("POST", "CreateNewFileServlet", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhttp.send(data);
}


function getfocus() {
    'use strict';
    var frame = document.getElementById("edit_panel");
    var result = (frame.contentWindow || frame.contentDocument);
    if (result.document) {
        result = result.document;
    }
    return result;
}


function construct_paper_s() {
    var doc = getfocus();
    var content = doc.getElementById("content");
    var paper_tmp = document.getElementById("paper");
    var paper = paper_tmp.textContent;

    if (paper !== "")
        content.innerHTML = paper;
    paper.textContent = "";

    content.addEventListener("input", function () {
        if (content.childElementCount === 0) {
            content.innerHTML = "<p class='paragraph'><br></p>";
        }
    }, false);
    
    content.addEventListener("focusin", function (e) {
        var node = e.target;
        if(node.nodeName==="INPUT"){
            node.value = node.name;
            node.readOnly =false;
        }
    }, false);
    
    content.addEventListener("focusout", function (e) {
        var node = e.target;
        if(node.nodeName==="INPUT"){
            node.name = node.value;
            node.value = "";
            node.readOnly = true;
        }
    }, false);
    
    content.addEventListener("paste", function (e) {
        // cancel paste
        e.preventDefault();

        // get text representation of clipboard
        var text = (e.originalEvent || e).clipboardData.getData('text/plain');

        // insert text manually
        getfocus().execCommand("insertHTML", false, text);
    });
}

function copy() {
    'use strict';
    getfocus().execCommand('copy');
}

async function paste() {
    'use strict';
    const text = await navigator.clipboard.readText();
    getfocus().execCommand('insertText', false, text);
}

function cut() {
    'use strict';
    getfocus().execCommand('cut');
}

function del() {
    'use strict';
    getfocus().execCommand('delete');
}

function insert(txt) {
    getfocus().execCommand("insertHTML", true, txt);
}

function insertTxt(txt) {
    getfocus().execCommand("insertText", true, txt);
}

function save_s(f_num, t_num) {
    setState(false);
    var name = document.getElementById("name").value;
    var content = getfocus().getElementById("content").cloneNode(true);
    var letter = getfocus().getElementById("content").cloneNode(true);

    if (name === null || name === "") {
        name = "default";
    }

    var input = content.getElementsByTagName("input");
    for (var i = 0; i < input.length; i++) {
        input[i].value = "";
        input[i].name = "";
        input[i].removeAttribute("readOnly");
    }

    input = letter.getElementsByTagName("input");
    for (var i = 0; i < input.length; i++) {
        input[i].setAttribute("readOnly", true);
    }

    var contentxt = content.innerHTML.replace(/&nbsp;/g, " ");
    var answertxt = letter.innerHTML.replace(/&nbsp;/g, " ");

    var data =
            "T_Name=" + name +
            "&T_Content=" + contentxt +
            "&T_Letter=" + answertxt +
            "&T_Number=" + t_num;


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            alert("儲存完畢");
        }
    };
    xhttp.open("POST", "SavePaperServlet", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

    xhttp.send(data);
}

function exit() {

}

function previous() {
    getfocus().execCommand("undo");
}

function next() {
    getfocus().execCommand("redo");
}

function prepare() {
    var content = getfocus().getElementById("content");
    var pcontent = document.getElementById("content");
    pcontent.textContent = content.innerHTML;
}

function btnState(btn, count) {
    if (count > 0) {
        btn.setAttribute("class", "text-white bg-secondary col-4 col-xs-2 col-sm-2 col-md-6 list-group-item list-group-item-action");
    } else {
        btn.setAttribute("class", "text-white bg-secondary col-4 col-xs-2 col-sm-2 col-md-6 list-group-item list-group-item-action disabled");
    }
}

function getSelectionNodes(select) {
    var nodeArray = new Array();
    if (select.isCollapsed) {
        nodeArray.push(select.anchorNode);
        return nodeArray;
    }

    var startNode = select.anchorNode;
    var endNode = select.focusNode;
    var parent = select.anchorNode.parentElement;
    var startIndex = Array.prototype.indexOf.call(parent.childNodes, startNode);
    var endIndex = Array.prototype.indexOf.call(parent.childNodes, endNode);
    if (startIndex > endIndex) {
        var tmp = startIndex;
        startIndex = endIndex;
        endIndex = tmp;
    }

    if (startIndex < 0 && endIndex < 0) {
        return nodeArray;
    } else if (startIndex < 0) {
        startIndex = endIndex;
    }

    for (var i = startIndex; i <= endIndex; i++) {
        nodeArray.push(parent.childNodes[i]);
    }

    return nodeArray;
}

function isTagContain(tag, select) {
    var arrayNode = getSelectionNodes(select);
    for (var i = 0; i < arrayNode.length; i++) {
        if (arrayNode[i].nodeName.toLowerCase() === tag.toLowerCase()) {
            return true;
        }
    }
    return false;
}

function getClozeList() {
    var nodeBuffer = new Array();
    var nodes = getfocus().childNodes;
    var count = getfocus().childElementCount;
}

function cloze() {
    'use strict';
    setState(false);
    var doc = getfocus();
    var select = doc.getSelection();
    var parent = select.anchorNode.parentElement;
    if (parent.nodeName !== "P" || select.isCollapsed || isTagContain("input", select)) {
        console.log("cloze:false");
        return false;
    }
    console.log("cloze:true");

    var txt = select.getRangeAt(0).toString();
    var buffer = ""
    txt = txt.split(" ");
    
    for(var i = 0;i<txt.length;i++){
        if(txt[i]!==""){
            var answerNode = doc.createElement("input");
            answerNode.readOnly = true;
            answerNode.setAttribute("name", txt[i]);
            buffer = buffer.concat(answerNode.outerHTML);
        }
        
    }
    insert(buffer);
    parent.normalize();
}

function uncloze() {
    'use strict';
    setState(false);
    var select = getfocus().getSelection();

    var node = select.anchorNode.childNodes[select.anchorOffset];
    var parent = node.parentNode;

    var range = getfocus().createRange();
    range.setStart(parent, select.anchorOffset);
    range.setEnd(parent, select.anchorOffset + 1);

    select.removeAllRanges();
    select.addRange(range);

    console.log(getfocus().createTextNode(node.name));
    insertTxt(node.name);
    parent.normalize();
}

function setState(state) {
    var input = getfocus().getElementsByTagName("input");

    for (var i = 0; i < input.length; i++) {
        input[i].blur();
    }
}