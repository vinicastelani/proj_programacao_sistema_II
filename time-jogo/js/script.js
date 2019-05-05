$(window).ready(function(){
  var bool = true;
  $("#submitSquad").on("click",function(){
  if(!bool){
    $("#return-message").addClass("success");
    $("#return-message").html("Clube inserido!");
    $("#return-message").fadeIn(1000, function(){
      setTimeout(function(){
        $("#return-message").fadeOut();
        $("#return-message").removeClass("success");
      },1000);
    });
  }else{
    $("#return-message").addClass("fail");
    $("#return-message").html("Erro na inserção do clube!");
    $("#return-message").fadeIn(1000, function(){
      setTimeout(function(){
        $("#return-message").fadeOut();
        $("#return-message").removeClass("fail");
      },1000);
    });
  }
  });


});
