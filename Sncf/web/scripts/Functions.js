function GetSelected(Nom_formulaire,Nom_Select){

    alert(nom(Nom_Select));
    alert(document.getElementById(Nom_formulaire).nom(Nom_Select).length);

    var temp = null;

    for(i = 0 ; i< document.getElementById(Nom_formulaire).Nom_Select.length; i++){

        if(document.getElementById(Nom_formulaire).Nom_Select.options[i].selected == true)
            temp = document.getElementById(id_formulaire).Nom_Select.options[i].value;   

    }

    alert('EXIT');

    if(!temp == null) return temp;
    else alert('ATTENTION UN PROBLEME A ETE DETECTE DANS LE TRAITEMENT DU SELECT \"+Nom_Select+\" POUR LE FORMULAIRE \"+Nom_formulaire+\"');

}

function nom(nom){
return nom;
}

