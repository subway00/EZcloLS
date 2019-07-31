function loading(title_txt){
    var cap = document.createElement("span");
    var title = document.createElement("p");
    var width = document.body.clientWidth;
    var height = document.body.clientHeight;
    
    cap.setAttribute("name","LoadingPage");
    cap.setAttribute("style","width:"+width+"px;height:"+height+"px;top:0px;position:absolute;background-color:#00000050;opacity:0");
    
    title.setAttribute("style","top:50%;left:50%;position:fixed;color:#FFFFFFFF;");
    title.style.fontSize = "30px";
    title.style.fontFamily = "Gotham,'Helvetica Neue',Helvetica, Arial,'微軟正黑體','sans-serif'";
    title.innerHTML=title_txt;
    
    cap.appendChild(title);
    document.body.appendChild(cap);
    $(cap).animate({opacity: 1}, 1000);
}

function stop_loading(){
    var pages = document.getElementsByName("LoadingPage");
    for(var p in pages){
        document.removeChild(p);
    }
}

