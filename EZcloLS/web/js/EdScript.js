// JavaScript Document

function construct_paper_s() {
    var content = document.getElementById("content");

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
        document.execCommand("insertHTML", false, text);
    });
}
