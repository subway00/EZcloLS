// JavaScript Document
$(function () {
    var count=0;
	var answers={"q1":"comprehension","q2":"combination","q3":"clues","q4":"definition","q5":"recognize","q6":"Dedicate","q7":"incorporate"};
	
	$("#score").click(function(){
		$.each(answers, function(question,answers) {
			if ($("input:text[name="+"'"+question+"'"+"]").val()==answers){
				count = count + 1;
			}else {
				($("input:text[name="+"'"+question+"'"+"]")).css({backgroundColor:"rgba(251,0,241,0.3)"});
			}
			
			  });
	
		$("#correct").val("答對題數: " + count);
		var percent=(count / 7) * 100;
		$("#percentage").val("命中率: " + percent.toFixed(2) + "%");  		
	});
        		
	$("#resetAll").click(function(){
		count=0;
		$("form input:not(.grades-box input)").css({backgroundColor:"white",color:"black"});
	});
});
