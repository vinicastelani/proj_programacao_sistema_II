$ = jQuery;
$(document).ready(function() {
  var timeA = $("#time1");
  var timeB = $("#time2");
  var timeAStatus = $("#time1-status");
  var timeBStatus = $("#time2-status");


  var start = $("#iniciar");
  var endgame = $(".endgame");
  var scoreA = 0;
  var scoreB = 0;
  var fimDeJogo = false;
  timeAStatus.html("");
  timeBStatus.html("");
  function partida() {
    var random = Math.floor((Math.random() * 100)); //Retorna um numero inteiro de 0 a 100

    if ((Math.floor(random % 2)) == 0) {
      scoreA++;
      setTimeout(function(){
        $(timeA).html(scoreA);
      },3000);
    } else {
      $(timeB).html(scoreB);
      scoreB++;
      setTimeout(function(){
        $(timeB).html(scoreB);
      },3000);
    }
    if (scoreA == 3 || scoreB == 3) {
      fimDeJogo = true;
    }
    setTimeout(function(){
      if (!fimDeJogo) {
        setTimeout(function() {
          partida();
        }, 3000);
      } else {
        endgame.show();
      }
    },3000);
  }
  $(start).on("click", function(){
    partida();
  });
});
