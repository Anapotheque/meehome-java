    /*****************************************************************************************/
    /*                           Chargement de la variable xhr                               */
    /*****************************************************************************************/

    function getXMLHttpRequestCalques(){
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

    function getHTMLCodeRequestCalques(id_div,servlet,send,id_formulaire){

    //id_div : Chaine permettant d'identifier la balise div
    //servlet: URL pattern de la servlet en question
    //send: valeurs à envoyées au serveur, null si aucunes valeurs à retourner
    //id_formulaire: correspond au formulaire que l'on veut recuperer

    var sending = "";
    var pb = null;

    if(id_formulaire != 'Null'){

    /*TEST AVANT SUPPRESSION AJOUT OU MODIFICATION*/
    if(servlet == "SuppressionCalques" || servlet == "EnregistrerAjout" || servlet == "EnregistrerModification" || servlet == "EnregistrerAjoutCase" || servlet == "EnregistrerMigration" || servlet == "EnregistrementSortie" || servlet == "SortirCalquesActions" || servlet == "EnregistrementRetour" || servlet == "RetourCalquesActions")
       if(!confirm('Etes vous certain ?'))
            pb = 'pb';

    /*PASSAGE DE PARAMETRE CODE CASE ET CODE COLLECTION*/
    if(servlet == "GetCalques" || servlet == "GetCalquesSelected" || servlet == "GetCalquesSortis" || servlet == "GetRetourCalques" || servlet == "GetRetourCalquesSelected"  || servlet == "VoirCases" || servlet == "GetCalquesGestions" || servlet == "GetCalquesSelectedGestions" ){
    for(i = 0 ; i< document.getElementById(id_formulaire).collection.length; i++){
    if(document.getElementById(id_formulaire).collection.options[i].selected == true)
    var codeCollection = document.getElementById(id_formulaire).collection.options[i].value;   
    }
    sending =send+"&codeCollection="+codeCollection;
    }

    /*PASSAGE DE PARAMETRE CODE CASE ET CODE COLLECTION*/
    if(send == 'NumeroCase'){
    for(i = 0 ; i< document.getElementById(id_formulaire).cases.length; i++){
    if(document.getElementById(id_formulaire).cases.options[i].selected == true)
    var codeCase = document.getElementById(id_formulaire).cases.options[i].value;   
    }
    sending = "&codeCase="+codeCase;        

    for(i = 0 ; i< document.getElementById(id_formulaire).collection.length; i++){
    if(document.getElementById(id_formulaire).collection.options[i].selected == true)
    var codeCollection = document.getElementById(id_formulaire).collection.options[i].value;   
    }
    sending = sending+"&codeCollection="+codeCollection;
    }

    /*AJOUT CALQUES*/
    if(servlet == "EnregistrerAjout"){

    var enteteCalque = null;

    for(i = 0 ; i< document.getElementsByName("option").length; i++){
    if(document.getElementsByName("option")[i].checked)
        var enteteCalque = document.getElementsByName("option")[i].value;
    }
    
    if(enteteCalque == null){
        pb = 'pb';
        alert("ATTENTION VOUS DEVEZ IMPERATIVEMENT SELECTIONNER UN TYPE D'ENTETE POUR CE CALQUE");
        
    }

    var numeroCalque = document.getElementById(id_formulaire).numeroCalque.value;
    
    sending =send+"&numeroCalque="+numeroCalque+"&enteteCalque="+enteteCalque;
    }

    /*MODIFIER CALQUES*/
    if(servlet == "ModifierCalques"){

    var nombre = 0;
    var codeCalques = null;

    for(i = 0 ; i< document.getElementsByName("box").length; i++){
    if(document.getElementsByName("box")[i].checked == true)
    if(codeCalques != null){
    alert("ATTENTION VOUS NE POUVEZ MODIFIER QU'UN SEUL CALQUE A LA FOIS !");
    pb = 'pb';
    }
    else   codeCalques = document.getElementsByName("box")[i].value; 
    }



    if(codeCalques == null){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN CALQUE POUR LA MODIFICATION !");
    pb = 'pb';
    }




    for(i = 0 ; i< document.getElementById(id_formulaire).cases.length; i++){
    if(document.getElementById(id_formulaire).cases.options[i].selected == true)
    var codeCase = document.getElementById(id_formulaire).cases.options[i].value;   
    }    
    sending = "&codeCalques="+codeCalques+"&codeCase="+codeCase;
    }

    /*ENREGISTREMENT DES MODIFICATIONS*/
    if(servlet == "EnregistrerModification"){

    var numeroCalque = document.getElementById(id_formulaire).numeroCalque.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).cases.length; i++){
    if(document.getElementById(id_formulaire).cases.options[i].selected == true)
    var codeCase = document.getElementById(id_formulaire).cases.options[i].value;   
    }    
    sending = "&numeroCalque="+numeroCalque+"&codeCase="+codeCase;
    }

    /*PERMET DE SUPPRIMER UNE SERIE DE CALQUES*/
    if(servlet == "SuppressionCalques"){
    var codeCalques = "@";
    for(i = 0 ; i< document.getElementsByName("box").length; i++){
    if(document.getElementsByName("box")[i].checked == true)
    var codeCalques = codeCalques+document.getElementsByName("box")[i].value+"@";   
    }
    sending = "&codeCalques="+codeCalques;
    }

    //RECUPERATION PAR AJAX DE CASES EN FONCTION DE LA COLLECTION CHOISIE
    if(servlet == "GetSelectedCases"){
    for(i = 0 ; i< document.getElementById(id_formulaire).collectionSelection.length; i++){
    if(document.getElementById(id_formulaire).collectionSelection.options[i].selected == true)
    var codeCollection = document.getElementById(id_formulaire).collectionSelection.options[i].value;   
    }
    sending = "&codeCollection="+codeCollection;
    }

    //ON RECUPERE LE CODE COLLECTION ET LE CODE CASE POUR LA MIGRATION
    if(servlet == "EnregistrerMigration"){
    for(i = 0 ; i< document.getElementById(id_formulaire).casesSelection.length; i++){
    if(document.getElementById(id_formulaire).casesSelection.options[i].selected == true){
    var codeCaseMigration = document.getElementById(id_formulaire).casesSelection.options[i].value;  
    }
    }
    sending = "codeCaseMigration="+codeCaseMigration;
    }

    //RECUPERATION PAR AJAX DU NUMERO DE RANGEMENT DE CASE POUR DETECTION DE CODE 0
    if(servlet == "GetNumeroCalquesAjout"){
    for(i = 0 ; i< document.getElementById(id_formulaire).alpha.length; i++){
    if(document.getElementById(id_formulaire).alpha.options[i].selected == true)
    var alpha = document.getElementById(id_formulaire).alpha.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).Numerique1.length; i++){
    if(document.getElementById(id_formulaire).Numerique1.options[i].selected == true)
    var Numerique1 = document.getElementById(id_formulaire).Numerique1.options[i].value;   
    }
    sending = "&alpha="+alpha+"&Numerique1="+Numerique1;
    }

    //ENREGISTREMENT D'UNE CASE
    if(servlet == "EnregistrerAjoutCase"){
    for(i = 0 ; i< document.getElementById(id_formulaire).alpha.length; i++){
    if(document.getElementById(id_formulaire).alpha.options[i].selected == true)
    var alpha = document.getElementById(id_formulaire).alpha.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).Numerique1.length; i++){
    if(document.getElementById(id_formulaire).Numerique1.options[i].selected == true)
    var numerique1 = document.getElementById(id_formulaire).Numerique1.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).Numerique2.length; i++){
    if(document.getElementById(id_formulaire).Numerique2.options[i].selected == true)
    var numerique2 = document.getElementById(id_formulaire).Numerique2.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).collection.length; i++){
    if(document.getElementById(id_formulaire).collection.options[i].selected == true)
    var codeCollection = document.getElementById(id_formulaire).collection.options[i].value;   
    }

    var temp = alpha+numerique1;
    if(numerique2!="null")
    temp += "."+numerique1;

    if(numerique1 == "null"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN NUMERO !");
    pb = 'pb';
    }

    if(numerique1 != "0" && numerique1 != "null" && numerique2 == "null"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN NUMERO !");
    pb = 'pb';
    }

    sending = "numeroCase="+temp+"&codeCollection="+codeCollection;
    }


/*--------------------------------------------------------------------------------------------------*/

/*SORTIE DE CALQUE*/

/*--------------------------------------------------------------------------------------------------*/

    if(servlet == "SortirCalquesActions"){

    var codeCalques = "@";
    for(i = 0 ; i< document.getElementsByName("box").length; i++){
    if(document.getElementsByName("box")[i].checked == true)
    var codeCalques = codeCalques+document.getElementsByName("box")[i].value+"@";   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).entreprise.length; i++){
    if(document.getElementById(id_formulaire).entreprise.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).entreprise.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).agents.length; i++){
    if(document.getElementById(id_formulaire).agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).agents.options[i].value;   
    }

    sending = "codeCalques="+codeCalques+"&codeEntreprise="+codeEntreprise+"&codeAgent="+codeAgent;

    if(codeAgent == "null"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN AGENT !");
    pb = 'pb';
    }
    if(codeCalques == "@"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN OU DES CALQUES !");
    pb = 'pb';
    }
    if(codeEntreprise == "null"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UNE ENTREPRISE !");
    pb = 'pb';
    }
    }

    if(servlet == "EnregistrementSortie"){

    var codeCalques = "@";
    for(i = 0 ; i< document.getElementsByName("box").length; i++){
    if(document.getElementsByName("box")[i].checked == true)
    var codeCalques = codeCalques+document.getElementsByName("box")[i].value+"@";   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).entreprise.length; i++){
    if(document.getElementById(id_formulaire).entreprise.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).entreprise.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).agents.length; i++){
    if(document.getElementById(id_formulaire).agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).agents.options[i].value;   
    }

    sending = "codeCalques="+codeCalques+"&codeEntreprise="+codeEntreprise+"&codeAgent="+codeAgent;

    if(codeAgent == "null"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN AGENT !");
    pb = 'pb';
    }
    if(codeCalques == "@"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN OU DES CALQUES !");
    pb = 'pb';
    }
    if(codeEntreprise == "null"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UNE ENTREPRISE !");
    pb = 'pb';
    }

    }

    if(servlet == "RetourCalquesActions"){

    var codeCalques = "@";
    for(i = 0 ; i< document.getElementsByName("box").length; i++){
    if(document.getElementsByName("box")[i].checked == true)
    var codeCalques = codeCalques+document.getElementsByName("box")[i].value+"@";   
    }

    sending = "codeCalques="+codeCalques;

    if(codeCalques == "@"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN OU DES CALQUES !");
    pb = 'pb';
    }
    }

    if(servlet == "EnregistrementRetour"){

    var codeCalques = "@";
    for(i = 0 ; i< document.getElementsByName("box").length; i++){
    if(document.getElementsByName("box")[i].checked == true)
    var codeCalques = codeCalques+document.getElementsByName("box")[i].value+"@";   
    }

    sending = "codeCalques="+codeCalques;

    if(codeCalques == "@"){
    alert("ATTENTION VOUS DEVEZ CHOISIR UN OU DES CALQUES !");
    pb = 'pb';
    }
    }



    /*HISTORIQUE CALQUES*/
    if(servlet == "AffichageInformationCalquesAgents"){
    for(i = 0 ; i< document.getElementById(id_formulaire).agents.length; i++){
    if(document.getElementById(id_formulaire).agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).agents.options[i].value;   
    }
    sending = "codeAgent="+codeAgent;
    }

    if(servlet == "AffichageInformationCalquesEntreprises"){
    for(i = 0 ; i< document.getElementById(id_formulaire).entreprise.length; i++){
    if(document.getElementById(id_formulaire).entreprise.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).entreprise.options[i].value;   
    }
    sending = "codeEntreprise="+codeEntreprise;
    }

    if(servlet == "AffichageInformationCalquesGares"){
    for(i = 0 ; i< document.getElementById(id_formulaire).collection.length; i++){
    if(document.getElementById(id_formulaire).collection.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).collection.options[i].value;   
    }
    sending = "codeGare="+codeGare;
    }



    }
    else sending = send;
















    if(pb == null){
    show(); //Affichage du menu opur faire patienter
    var req = null;   
    req = getXMLHttpRequestCalques();
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