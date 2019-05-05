$(window).ready(function() {

  var status = $("#status");
  $(status).html("");
  $(status).hide();
  // START PARDIDA
  $("#start").on("click", function() {
    $("#placar-1").html("0");
    $("#placar-2").html("0");
    ataque();
  });
  // START PARDIDA

  // PLACAR
  var scoreA = 0;
  var scoreB = 0;
  var fimDeJogo = false;
  // PLACAR
  // RODADA DE ATAQUE
  function ataque() {
    var turn = Math.floor((Math.random() * 100) + 1);
    console.log(turn);
    if (turn % 2 == 0) {
      // SE PAR ESTIVER ATACANDO
      $(status).show();
      $(status).html("PAR ESTA ATACANDO");
      setTimeout(function() {
        var chance = Math.floor((Math.random() * 100) + 1);
        if (chance % 2 == 0) {
          $(status).html("PAR FEZ O GOL !");
          scoreA ++;
          $("#placar-1").html(scoreA);
          setTimeout(function() {
            $(status).hide()
          }, 3000);
        } else {
          $(status).html("PAR PERDEU O GOL !");
          setTimeout(function() {
            $(status).hide()
          }, 3000);
        }
      }, 3000);
      // SE PAR ESTIVER ATACANDO
    }
    // SE IMPAR ESTIVER ATACANDO
    else {
      $(status).show();
      $(status).html("IMPAR ESTA ATACANDO");
      setTimeout(function() {
        var chance = Math.floor((Math.random() * 100) + 1);
        if (chance % 2 == 0) {
          $(status).html("IMPAR FEZ O GOL !");
          scoreB ++;
          $("#placar-2").html(scoreB);
          setTimeout(function() {
            $(status).hide()
          }, 3000);
        } else {
          $(status).html("IMPAR PERDEU O GOL !");
          setTimeout(function() {
            $(status).hide()
          }, 3000);
        }
      }, 3000);
    }
    if(scoreA == 3 || scoreB == 3){
      fimDeJogo = true;
    } else{
      fimDeJogo = false;
    }
    if(fimDeJogo == false)
    {
      setTimeout(function(){
        ataque();
      },7000);
    }
    console.log("PLACAR "+scoreA);
    console.log("PLACAR "+scoreB);
  }

  // SE IMPAR ESTIVER ATACANDO
  // RODADA DE ATAQUE

});
