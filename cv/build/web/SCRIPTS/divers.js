function show(ID){
document.getElementById(ID).style.visibility = "visible";
if(ID != 'Outils_Menu_Formations')
document.getElementById('Outils_Menu_Formations').style.visibility = "hidden";
if(ID != 'Outils_Menu_Comptence')
document.getElementById('Outils_Menu_Comptence').style.visibility = "hidden";
if(ID != 'Outils_Menu_Experience')
document.getElementById('Outils_Menu_Experience').style.visibility = "hidden";
if(ID != 'Outils_Menu_EtatCivil')
document.getElementById('Outils_Menu_EtatCivil').style.visibility = "hidden";


}

function hidden(){
document.getElementById('demarrer').style.visibility = "hidden";
document.getElementById('Outils_Menu_Comptence').style.visibility = "hidden";
document.getElementById('Outils_Menu_Formations').style.visibility = "hidden";
document.getElementById('Outils_Menu_Experience').style.visibility = "hidden";
document.getElementById('Outils_Menu_EtatCivil').style.visibility = "hidden";
}