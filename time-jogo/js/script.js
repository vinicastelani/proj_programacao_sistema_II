$(document).ready(function(){
  var ballLeft = ("#ball-left");
  var ballRight = ("#ball-right");

  
  $(ballLeft).hover(function(){
    $(ballLeft).animate({top: "-10"});
    $(ballLeft).animate({top: "0"});
  });

  $(ballRight).hover(function(){
    $(ballRight).animate({top: "-10"});
    $(ballRight).animate({top: "0"});
  });


});
