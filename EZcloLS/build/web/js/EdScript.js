// JavaScript Document
function resizeInput() {
    $(this).attr('size', $(this).attr('name').length);
    console.log("hghg");
}

function inputLimit() {
    $(this).attr('size', $(this).attr('name').length);
    console.log("hghg");
}

function copy() {
    'use strict';
    document.execCommand('copy');
}

function cut() {
    'use strict';
    document.execCommand('cut');
}

function paste(len, e) {
    var diff = max_text - len;
    var text = (e.originalEvent || e).clipboardData.getData('text/plain');
    if (text.length > diff) {
        text = text.substring(0, diff);
    }
    document.execCommand('insertText', false, text);
}

function isControlKey(key) {
    var limit = Array.from([8, 12, 16, 17, 18, 19, 20, 27, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 45, 46, 47, 91]);
    for (var l in limit) {
        if (key === limit[l]) {
            return true;
        }
    }
    return false;
}

var spk = false;
var max_text = 1000;
function construct_paper_s() {
    var content = document.getElementById("content");

    if (content.childElementCount === 0) {
        content.innerHTML = "<p><br></p>";
    }

    $('input').on('keydown paste keyup', function (event) {
        var count = 0;
        var clozes = content.getElementsByTagName("input");

        for (var i = 0; i < clozes.length; i++) {
            if (clozes[i].value === null || clozes[i].value === "") {
                count += clozes[i].name.length;
            } else {
                count += clozes[i].value.length;
            }
        }

        if (event.type === "keydown" && isControlKey(event.keyCode)) {
            spk = event.keyCode === 17 || event.keyCode === 16 || event.keyCode === 91;
        }

        if (event.type === "keydown" && content.textContent.length + count >= max_text && !isControlKey(event.keyCode) && !spk) {

            event.preventDefault();
        }
        console.log(content.textContent.length + count);
        if (event.type === "keyup") {
            spk = !event.keyCode === 17 || !event.keyCode === 16 || !event.keyCode === 91;
        } else if (event.type === "paste") {

            paste(content.textContent.length + count, event);
            event.preventDefault();
        }
    });


    $(content).on('keypress keydown keyup paste', function (event) {
        var count = 0;
        var clozes = content.getElementsByTagName("input");
        var pa = content.getElementsByTagName("p");
        for (var i = 0; i < clozes.length; i++) {
            if (clozes[i].value === null || clozes[i].value === "") {
                count += clozes[i].name.length;
            } else {
                count += clozes[i].value.length;
            }
        }

        if (event.type === "keydown" && isControlKey(event.keyCode)) {
            spk = event.keyCode === 17 || event.keyCode === 16 || event.keyCode === 91;
        }

        //check height
        var th = 0;
        var sh = 0;
        for (var i = 0; i < pa.length; i++) {
            if (i === 0) {
                sh = pa[i].offsetHeight;
            }
            th += pa[i].offsetHeight;
        }

        if (event.type === "keydown" && content.textContent.length + count >= max_text && !isControlKey(event.keyCode) && !spk) {
            event.preventDefault();
        }

        if (event.type === "keydown" && event.keyCode === 13 && th >= content.offsetHeight - sh) {
            event.preventDefault();
        }

        if (event.type === "keyup") {
            spk = !event.keyCode === 17 || !event.keyCode === 16 || !event.keyCode === 91;
        } else if (event.type === "paste") {

            paste(content.textContent.length + count, event);
            event.preventDefault();
        }
    });

    content.addEventListener("input", function (event) {
        if (content.childElementCount === 0) {
            content.innerHTML = "<p><br></p>";
        }
    }, false);

    content.addEventListener("focusin", function (e) {
        var node = e.target;
        if (node.nodeName === "INPUT") {
            node.value = node.name;
            node.readOnly = false;
        }
    }, false);

    content.addEventListener("focusout", function (e) {
        var node = e.target;
        if (node.nodeName === "INPUT") {
            node.name = node.value;
            node.value = "";
            node.readOnly = true;
        }
    }, false);
}
