    /*****************************************************************************************/
    /*                           Chargement de la variable xhr                               */
    /*****************************************************************************************/

    function getXMLHttpRequestCourrier(){
    var xhr = null;
    if(window.XMLHttpRequest) // Firefox et autres
    xhr = new XMLHttpRequest();
    else if(window.ActiveXObject){ // Internet Explorer
    try {
    xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
    xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    }
    else { // XMLHttpRequest non supporté par le navigateur
    alert("Votre navigateur ne supporte pas les objets XMLHTTPRequest...");
    xhr = false;
    }
    return xhr;
    }

    /*******************************************************************************************/
    /*    Fonction permettant de modifier le contenu d'un DIV selon son identifiant            */
    /*******************************************************************************************/

    function getHTMLCodeRequestCourrier(id_div,servlet,send,id_formulaire,id){

    //id_div : Chaine permettant d'identifier la balise div
    //servlet: URL pattern de la servlet en question
    //send: valeurs à envoyées au serveur, null si aucunes valeurs à retourner
    //id_formulaire: correspond au formulaire que l'on veut recuperer

    var sending = "";
    var pb = null;

    if(id_formulaire != 'null'){

    if(send == "choix_menu=AffChoixGare"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Agents.length; i++){
    if(document.getElementById(id_formulaire).Agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).Agents.options[i].value;   
    }
    sending = send+"&codeAgent="+codeAgent;
    }

    if(send == "choix_menu=AffChoixIndice"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Gares.length; i++){
    if(document.getElementById(id_formulaire).Gares.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).Gares.options[i].value;   
    }
    sending = send+"&codeGare="+codeGare;
    }

    if(send == "choix_menu=AffChoixCategorie"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Indice.length; i++){
    if(document.getElementById(id_formulaire).Indice.options[i].selected == true)
    var codeEtude = document.getElementById(id_formulaire).Indice.options[i].value;   
    }
    sending = send+"&codeEtude="+codeEtude;
    }

    if(send == "choix_menu=LoadData"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Categories.length; i++){
    if(document.getElementById(id_formulaire).Categories.options[i].selected == true)
    var codeCategorie = document.getElementById(id_formulaire).Categories.options[i].value;   
    }
    sending = send+"&codeCategorie="+codeCategorie;
    }

    if(servlet == "DonneeCourrier"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Lettres.length; i++){
    if(document.getElementById(id_formulaire).Lettres.options[i].selected == true)
    var codeLettre = document.getElementById(id_formulaire).Lettres.options[i].value;   
    }
    sending = "&codeLettre="+codeLettre;
    }

    if(servlet == "EnregistrementDonnee"){
    var destinataire = document.getElementById(id_formulaire).Destinataire.value;
    var indice = document.getElementById(id_formulaire).Indice.value;
    var affaires = document.getElementById(id_formulaire).Affaires.value;
    var object = document.getElementById(id_formulaire).Object.value;
    var detail = document.getElementById(id_formulaire).Detail.value;

    var chaine = "&destinataire="+destinataire+"&indice="+indice+"&affaires="+affaires+"&object="+object+"&detail="+detail;
    sending = chaine;
    }

    if(servlet == "EnregistrementAR"){
    var dateIndice = document.getElementById(id_formulaire).MyDate.value;
    sending = "&dateIndice="+dateIndice;
    }

    if(servlet == "EnregistrementCopie"){
    var copie = document.getElementById(id_formulaire).copie.value;
    sending = "&copie="+copie;
    }
    if(servlet == "EnregistrementPJ"){
    var pj = document.getElementById(id_formulaire).pj.value;
    sending = "&pj="+pj;
    }
    if(servlet == "EnregistrementExemplaire"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Exemplaire.length; i++){
    if(document.getElementById(id_formulaire).Exemplaire.options[i].selected == true)
    var Exemplaire = document.getElementById(id_formulaire).Exemplaire.options[i].value;   
    }
    sending = "&Exemplaire="+Exemplaire;
    }
    if(servlet == "EnregistrementSuivi"){
    var sesco = document.getElementById(id_formulaire).sesco.value;
    sending = "&sesco="+sesco;
    }
    if(servlet == "EnregistrementTexte"){
    var texte = document.getElementById(id_formulaire).texte.value;
    var textegras = document.getElementById(id_formulaire).textegras.value;
    sending = "&texte="+texte+"&textegras="+textegras;
    }
    if(servlet == "RedirectionChoixAffaires"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Filtre.length; i++){
    if(document.getElementById(id_formulaire).Filtre.options[i].selected == true)
    var Filtre = document.getElementById(id_formulaire).Filtre.options[i].value;   
    }
    sending = "&Filtre="+Filtre;
    }
    if(servlet == "RedirectionChoixListe"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Filtre.length; i++){
    if(document.getElementById(id_formulaire).Filtre.options[i].selected == true)
    var Filtre = document.getElementById(id_formulaire).Filtre.options[i].value;   
    }
    sending = "&Filtre="+Filtre;
    }
    if(servlet == "ListeAffparCodeAffaire"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Affaires.length; i++){
    if(document.getElementById(id_formulaire).Affaires.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).Affaires.options[i].value;   
    }
    sending = "&codeAffaire="+codeAffaire;
    }
    if(servlet == "ListAffparCodeAgent"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Filtre.length; i++){
    if(document.getElementById(id_formulaire).Filtre.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).Filtre.options[i].value;   
    }
    sending = "&codeAgent="+codeAgent;
    }




    if(servlet == "SelectionAgent"){
    
    for(i = 0 ; i< document.getElementById(id_formulaire).Affaires.length; i++){
    if(document.getElementById(id_formulaire).Affaires.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).Affaires.options[i].value;   
    } 
    sending = "&codeAffaire="+codeAffaire;
    }
    if(servlet == "SaisieDestinataire"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Agents.length; i++){
    if(document.getElementById(id_formulaire).Agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).Agents.options[i].value;   
    }   
    sending = "&codeAgent="+codeAgent;
    }
    if(servlet == "Saisie_Designation"){
    var destinataire = document.getElementById(id_formulaire).destinataire.value;   
    sending = "&destinataire="+destinataire;
    }
    if(servlet == "NumeroLettre"){
    var designation = document.getElementById(id_formulaire).designation.value;   
    sending = "&designation="+designation;
    }

    if(servlet == "GetCourrierGestionTypeLettre"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Categories.length; i++){
    if(document.getElementById(id_formulaire).Categories.options[i].selected == true)
    var codeCategorie = document.getElementById(id_formulaire).Categories.options[i].value;   
    }  
    sending = "&codeCategorie="+codeCategorie;
    }

    if(servlet == "RedirectGestion"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Lettres.length; i++){
    if(document.getElementById(id_formulaire).Lettres.options[i].selected == true)
    var codeLettre = document.getElementById(id_formulaire).Lettres.options[i].value;   
    }  
    sending = "&codeLettre="+codeLettre;
    }




    if(servlet == "GestionSaveCopie" || servlet == "GestionSavePJ" || servlet == "GestionSaveTexte" || servlet == "GestionSaveTexteGras")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "TexteCourrier" || servlet == "PJCourrier" || servlet == "CopieCourrier" || servlet == "ARCourrier" || servlet == "SuiviCourrier" || servlet == "ShowDonneeCourrier")
       if(!confirm('AVEZ VOUS FAIT UNE SAUVEGARDE VOS MODIFICATIONS ?'))
            pb = 'pb';
                     
    if(servlet == "GestionSaveCopie" || servlet == "GestionSavePJ" || servlet == "GestionSaveTexte" || servlet == "GestionSaveTexteGras"){
    var string = document.getElementById(id_formulaire).Texte.value; 
    sending = "&string="+string;
    }

    if(servlet == "GestionSaveAR" || servlet == "GestionSaveSuivi")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';
    
    if(servlet == "GestionSaveAR" || servlet == "GestionSaveSuivi"){
        if(document.getElementById(id_formulaire).box.checked)  var box = 1;
        else    var box = 0;
    sending = "&box="+box;
    }



    if(servlet == "AjouterCategorie")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "AjouterCategorie"){
    var Categorie = document.getElementById(id_formulaire).categorie.value; 
    sending = "&Categorie="+Categorie;
    }

   if(servlet == "AjouterLettre")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "AjouterLettre"){
    var Lettre = document.getElementById(id_formulaire).lettre.value; 
    sending = "&Lettre="+Lettre;
    }

    if(servlet == "ModifierCategorie"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Categories.length; i++){
    if(document.getElementById(id_formulaire).Categories.options[i].selected == true)
    var code = document.getElementById(id_formulaire).Categories.options[i].value;   
    }  
    sending = "&code="+code;
    }

    if(servlet == "ModifierCategorie"  && send == "Enregistrer")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "ModifierCategorie"  && send == "Enregistrer"){
    var Categorie = document.getElementById(id_formulaire).categorie.value; 
    sending = "&Categorie="+Categorie;
    }


    if(servlet == "ModifierLettre"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Lettres.length; i++){
    if(document.getElementById(id_formulaire).Lettres.options[i].selected == true)
    var code = document.getElementById(id_formulaire).Lettres.options[i].value;   
    }  
    sending = "&code="+code;
    }

    if(servlet == "ModifierLettre"  && send == "Enregistrer")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "ModifierLettre"  && send == "Enregistrer"){
    var Lettre = document.getElementById(id_formulaire).lettre.value; 
    sending = "&Lettre="+Lettre;
    }




    if(servlet == "SupprimerCategorie")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "SupprimerCategorie"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Categories.length; i++){
    if(document.getElementById(id_formulaire).Categories.options[i].selected == true)
    var code = document.getElementById(id_formulaire).Categories.options[i].value;   
    }  
    sending = "&code="+code;
    }

    if(servlet == "SupprimerLettre")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    if(servlet == "SupprimerLettre"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Lettres.length; i++){
    if(document.getElementById(id_formulaire).Lettres.options[i].selected == true)
    var code = document.getElementById(id_formulaire).Lettres.options[i].value;   
    }  
    
    sending = "&code="+code;
    }

    if(servlet == "AffichageGestionLettre"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Categories.length; i++){
    if(document.getElementById(id_formulaire).Categories.options[i].selected == true)
    var code = document.getElementById(id_formulaire).Categories.options[i].value;   
    }  
    sending = "&code="+code;
    }

    if(servlet == "DeleteCourrierDepart"){

    if(servlet == 'DeleteCourrierDepart')
            if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    sending = "CodeLettre="+id;
    }

    if(servlet == "ModifyCourrierDepart"){
    sending = "CodeLettre="+id;
    }

    if(servlet == "EnregistrerModificationCourrierDepart"){

    for(i = 0 ; i< document.getElementById(id_formulaire).affaire.length; i++){
    if(document.getElementById(id_formulaire).affaire.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).affaire.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).agent.length; i++){
    if(document.getElementById(id_formulaire).agent.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).agent.options[i].value;   
    }
    var designation = document.getElementById(id_formulaire).destination.value;
    var destination = document.getElementById(id_formulaire).destinataire.value;

    sending = "codeAffaire="+codeAffaire+"&codeAgent="+codeAgent+"&designation="+designation+"&destination="+destination;
    }

    }
    else sending = send;

    if(pb == null){
    show(); //Affichage du menu opur faire patienter
    var req = null;   
    req = getXMLHttpRequestCourrier();
    req.onreadystatechange = function(){
    if(req.readyState == 4 /*&& req.status == 200*/){
    document.getElementById(id_div).innerHTML = req.responseText;
    hidden();
    }
    };

    req.open("POST",servlet,true);
    req.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    req.send(sending);
    }
    }