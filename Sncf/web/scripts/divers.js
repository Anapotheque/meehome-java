function contact() {
var contact = "*******************************************\n"+
              "Nom Prenom\n"+
              "RABALLAND Romain\n\n"+
              "*******************************************\n"+  
              "Addresse\n"+
              "47 Allee des magnolias\n"+
              "Jardin de l'oppidum\n"+
              "83190 Ollioules\n\n"+
              "*******************************************\n"+
              "Telephone\n"+
              "06.63.96.89.55\n"+
              "04.94.63.00.90\n\n"+
              "*******************************************\n"+
              "Courriel\n"+
              "raballand.romain@gmail.com\n";
              "*******************************************";

alert(contact);
}
function show(){
document.getElementById('DivLoad').style.visibility = "visible";
document.getElementById('DivLoadFont').style.visibility = "visible";
}
function hidden(){
document.getElementById('DivLoad').style.visibility = "hidden";
document.getElementById('DivLoadFont').style.visibility = "hidden";
}
function show2(){
document.getElementById('DivLoad2').style.visibility = "visible";
}
function tpsConnection(){
alert("Pour augmenter votre temps de connection, contactez votre administrateur");
}