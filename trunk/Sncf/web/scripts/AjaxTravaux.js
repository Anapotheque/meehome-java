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

    function getHTMLCodeRequestTravaux(id_div,servlet,id_formulaire,id){

    var pb = null;
    var send = "";

    if(servlet == "EnregistrerCommande"){

    for(i = 0 ; i< document.getElementById(id_formulaire).entreprise.length; i++){
    if(document.getElementById(id_formulaire).entreprise.options[i].selected == true){

    if(document.getElementById(id_formulaire).entreprise.options[i].value == 'null'){
      alert('Veuillez selectionner une entreprise');
    pb = 'pb';
    }

    send += "codeEntreprise="+document.getElementById(id_formulaire).entreprise.options[i].value;   
    }
    }

    send += "&commande="+document.getElementById(id_formulaire).commande.value;
    send += "&EtablissementDocumentPrix="+document.getElementById(id_formulaire).EtablissementDocumentPrix.value;
    send += "&EtablissementDocumentPourcentage="+document.getElementById(id_formulaire).EtablissementDocumentPourcentage.value;
    send += "&ModificationDocumentPrix="+document.getElementById(id_formulaire).ModificationDocumentPrix.value;
    send += "&ModificationDocumentPourcentage="+document.getElementById(id_formulaire).ModificationDocumentPourcentage.value;
    send += "&TraitementPrix="+document.getElementById(id_formulaire).TraitementPrix.value;
    send += "&TraitementPourcentage="+document.getElementById(id_formulaire).TraitementPourcentage.value;
    send += "&TeintagePrix="+document.getElementById(id_formulaire).TeintagePrix.value;
    send += "&TeintagePourcentage="+document.getElementById(id_formulaire).TeintagePourcentage.value;
    send += "&BordagePrix="+document.getElementById(id_formulaire).BordagePrix.value;
    send += "&BordagePourcentage="+document.getElementById(id_formulaire).BordagePourcentage.value;
    send += "&HelioPrix="+document.getElementById(id_formulaire).HelioPrix.value;
    send += "&HelioPourcentage="+document.getElementById(id_formulaire).HelioPourcentage.value;
    send += "&PhotocopiePrix="+document.getElementById(id_formulaire).PhotocopiePrix.value;
    send += "&PhotocopiePourcentage="+document.getElementById(id_formulaire).PhotocopiePourcentage.value;
    send += "&CC1Prix="+document.getElementById(id_formulaire).CC1Prix.value;
    send += "&CC1Pourcentage="+document.getElementById(id_formulaire).CC1Pourcentage.value;
    send += "&CC2Prix="+document.getElementById(id_formulaire).CC2Prix.value;
    send += "&CC2Pourcentage="+document.getElementById(id_formulaire).CC2Pourcentage.value;
    send += "&CC3Prix="+document.getElementById(id_formulaire).CC3Prix.value;
    send += "&CC3Pourcentage="+document.getElementById(id_formulaire).CC3Pourcentage.value;
    send += "&CCSupPrix="+document.getElementById(id_formulaire).CCSupPrix.value;
    send += "&CCSupPourcentage="+document.getElementById(id_formulaire).CCSupPourcentage.value;
    send += "&DAO1A4Prix="+document.getElementById(id_formulaire).DAO1A4Prix.value;
    send += "&DAO1A4Pourcentage="+document.getElementById(id_formulaire).DAO1A4Pourcentage.value;
    send += "&DAO2A4Prix="+document.getElementById(id_formulaire).DAO2A4Prix.value;
    send += "&DAO2A4Pourcentage="+document.getElementById(id_formulaire).DAO2A4Pourcentage.value;
    send += "&DAO3A4Prix="+document.getElementById(id_formulaire).DAO3A4Prix.value;
    send += "&DAO3A4Pourcentage="+document.getElementById(id_formulaire).DAO3A4Pourcentage.value;
    send += "&SupplementPrix="+document.getElementById(id_formulaire).SupplementPrix.value;
    send += "&SupplementPourcentage="+document.getElementById(id_formulaire).SupplementPourcentage.value;

    if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    if(document.getElementById(id_formulaire).commande.value == ''){
      alert('Veuillez renseigner un numero de commande');
    pb = 'pb';
    }


    }


    if(servlet == "EnregistrementModificationAffichageTravaux"){

    var temp = 0;

   send += "&MyDate="+document.getElementById(id_formulaire).MyDate.value;

    send += "&Tirage="+document.getElementById(id_formulaire).Tirage.value;
    send += "&DAO1="+document.getElementById(id_formulaire).DAO1.value;
    send += "&DAO2="+document.getElementById(id_formulaire).DAO2.value;
    send += "&DAO3="+document.getElementById(id_formulaire).DAO3.value;

    for(i = 0 ; i< document.getElementsByName("Supplement").length; i++){
    if(document.getElementsByName("Supplement")[i].checked == true)
    var temp = 1; 
    }

    send += "&Supplement="+temp; 

    send += "&Photocopie="+document.getElementById(id_formulaire).Photocopie.value;
    send += "&CC1="+document.getElementById(id_formulaire).CC1.value;
    send += "&CC2="+document.getElementById(id_formulaire).CC2.value;
    send += "&CC3="+document.getElementById(id_formulaire).CC3.value;
    send += "&CC="+document.getElementById(id_formulaire).CC.value;
    send += "&Bordage="+document.getElementById(id_formulaire).Bordage.value;
    send += "&Etablissement="+document.getElementById(id_formulaire).Etablissement.value;
    send += "&Modification="+document.getElementById(id_formulaire).Modification.value;
    send += "&Traitement="+document.getElementById(id_formulaire).Traitement.value;
    send += "&Teintage="+document.getElementById(id_formulaire).Teintage.value;
    send += "&NumOE="+document.getElementById(id_formulaire).NumOE.value;

    if(!confirm('Etes vous certain ?'))
    pb = 'pb';

    }


    if(servlet == "EnregistrerModificationCommandes"){

    for(i = 0 ; i< document.getElementById(id_formulaire).entreprise.length; i++){
    if(document.getElementById(id_formulaire).entreprise.options[i].selected == true){
    if(document.getElementById(id_formulaire).entreprise.options[i].value == 'null'){
    alert('Veuillez selectionner une entreprise');
    pb = 'pb';
    }
    send += "codeEntreprise="+document.getElementById(id_formulaire).entreprise.options[i].value;   
    }
    }

    send += "&commande="+document.getElementById(id_formulaire).commande.value;
    send += "&EtablissementDocumentPrix="+document.getElementById(id_formulaire).EtablissementDocumentPrix.value;
    send += "&EtablissementDocumentPourcentage="+document.getElementById(id_formulaire).EtablissementDocumentPourcentage.value;
    send += "&ModificationDocumentPrix="+document.getElementById(id_formulaire).ModificationDocumentPrix.value;
    send += "&ModificationDocumentPourcentage="+document.getElementById(id_formulaire).ModificationDocumentPourcentage.value;
    send += "&TraitementPrix="+document.getElementById(id_formulaire).TraitementPrix.value;
    send += "&TraitementPourcentage="+document.getElementById(id_formulaire).TraitementPourcentage.value;
    send += "&TeintagePrix="+document.getElementById(id_formulaire).TeintagePrix.value;
    send += "&TeintagePourcentage="+document.getElementById(id_formulaire).TeintagePourcentage.value;
    send += "&BordagePrix="+document.getElementById(id_formulaire).BordagePrix.value;
    send += "&BordagePourcentage="+document.getElementById(id_formulaire).BordagePourcentage.value;
    send += "&HelioPrix="+document.getElementById(id_formulaire).HelioPrix.value;
    send += "&HelioPourcentage="+document.getElementById(id_formulaire).HelioPourcentage.value;
    send += "&PhotocopiePrix="+document.getElementById(id_formulaire).PhotocopiePrix.value;
    send += "&PhotocopiePourcentage="+document.getElementById(id_formulaire).PhotocopiePourcentage.value;
    send += "&CC1Prix="+document.getElementById(id_formulaire).CC1Prix.value;
    send += "&CC1Pourcentage="+document.getElementById(id_formulaire).CC1Pourcentage.value;
    send += "&CC2Prix="+document.getElementById(id_formulaire).CC2Prix.value;
    send += "&CC2Pourcentage="+document.getElementById(id_formulaire).CC2Pourcentage.value;
    send += "&CC3Prix="+document.getElementById(id_formulaire).CC3Prix.value;
    send += "&CC3Pourcentage="+document.getElementById(id_formulaire).CC3Pourcentage.value;
    send += "&CCSupPrix="+document.getElementById(id_formulaire).CCSupPrix.value;
    send += "&CCSupPourcentage="+document.getElementById(id_formulaire).CCSupPourcentage.value;
    send += "&DAO1A4Prix="+document.getElementById(id_formulaire).DAO1A4Prix.value;
    send += "&DAO1A4Pourcentage="+document.getElementById(id_formulaire).DAO1A4Pourcentage.value;
    send += "&DAO2A4Prix="+document.getElementById(id_formulaire).DAO2A4Prix.value;
    send += "&DAO2A4Pourcentage="+document.getElementById(id_formulaire).DAO2A4Pourcentage.value;
    send += "&DAO3A4Prix="+document.getElementById(id_formulaire).DAO3A4Prix.value;
    send += "&DAO3A4Pourcentage="+document.getElementById(id_formulaire).DAO3A4Pourcentage.value;
    send += "&SupplementPrix="+document.getElementById(id_formulaire).SupplementPrix.value;
    send += "&SupplementPourcentage="+document.getElementById(id_formulaire).SupplementPourcentage.value;

    if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    if(document.getElementById(id_formulaire).commande.value == ''){
      alert('Veuillez renseigner un numero de commande');
    pb = 'pb';
    }
    }

    if(servlet == "DeleteCommande"){
    if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    send = "CodeCommande="+id;
    }

    if(servlet == "DeleteTravaux"){
    if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    send = "CodeTravaux="+id;
    }



    if(servlet == "ModifyCommande"){
    send = "CodeCommande="+id;
    }

    if(servlet == "ModifyTravaux"){
    send = "numeroFiche="+id;
    }

    if(servlet == "GetImputation"){
    for(i = 0 ; i< document.getElementById(id_formulaire).affaire.length; i++){
    if(document.getElementById(id_formulaire).affaire.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).affaire.options[i].value;   
    }
    send = "codeAffaire="+codeAffaire;
    }

    if(servlet == "EnregistrerFichedeTravail"){

    if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    for(i = 0 ; i< document.getElementById(id_formulaire).agent.length; i++){
    if(document.getElementById(id_formulaire).agent.options[i].selected == true){
    if(document.getElementById(id_formulaire).agent.options[i].value == 'null'){
      alert('Veuillez selectionner un agent');
    pb = 'pb';
    }
    var codeAgent = document.getElementById(id_formulaire).agent.options[i].value;   
    }
    }

    for(i = 0 ; i< document.getElementById(id_formulaire).commande.length; i++){
    if(document.getElementById(id_formulaire).commande.options[i].selected == true){
    if(document.getElementById(id_formulaire).commande.options[i].value == 'null'){
      alert('Veuillez selectionner une commande');
    pb = 'pb';
    }
    var codeCommande = document.getElementById(id_formulaire).commande.options[i].value;  
    }
    }

    for(i = 0 ; i< document.getElementById(id_formulaire).affaire.length; i++){
    if(document.getElementById(id_formulaire).affaire.options[i].selected == true){

    if(document.getElementById(id_formulaire).affaire.options[i].value == 'null'){
      alert('Veuillez selectionner une affaire');
    pb = 'pb';
    }
    var codeAffaire = document.getElementById(id_formulaire).affaire.options[i].value;   
    }
    }

    for(i = 0 ; i< document.getElementsByName("imputation").length; i++){
    if(document.getElementsByName("imputation")[i].checked)
        var imputation = document.getElementsByName("imputation")[i].value;
    }



    send = "codeAgent="+codeAgent+"&codeCommande="+codeCommande+"&codeAffaire="+codeAffaire+"&imputation="+imputation;
    send += "&date="+document.getElementById(id_formulaire).date.value;
    }

    if(servlet == "EnregistrerModifyTravaux"){
    for(i = 0 ; i< document.getElementById(id_formulaire).agent.length; i++){
    if(document.getElementById(id_formulaire).agent.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).agent.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).commande.length; i++){
    if(document.getElementById(id_formulaire).commande.options[i].selected == true)
    var codeCommande = document.getElementById(id_formulaire).commande.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).affaire.length; i++){
    if(document.getElementById(id_formulaire).affaire.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).affaire.options[i].value;   
    }
    for(i = 0 ; i< document.getElementsByName("imputation").length; i++){
    if(document.getElementsByName("imputation")[i].checked)
        var imputation = document.getElementsByName("imputation")[i].value;
    }

    if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    send = "codeAgent="+codeAgent+"&codeCommande="+codeCommande+"&codeAffaire="+codeAffaire+"&imputation="+imputation;
    send += "&date="+document.getElementById(id_formulaire).date.value;
    }

    if(servlet == "ModificationAffichageTravaux"){

    for(i = 0 ; i< document.getElementById(id_formulaire).NumFiche.length; i++){
    if(document.getElementById(id_formulaire).NumFiche.options[i].selected == true)
    var numeroFiche = document.getElementById(id_formulaire).NumFiche.options[i].value;   
    }
    send = "&numeroFiche="+numeroFiche;
    }

    if(servlet == "EnregistrementNumOE"){

        if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    for(i = 0 ; i< document.getElementById(id_formulaire).commande.length; i++){
    if(document.getElementById(id_formulaire).commande.options[i].selected == true){

    if(document.getElementById(id_formulaire).commande.options[i].value == 'null'){
      alert('Veuillez selectionner une commande');
    pb = 'pb';
    }

    var numeroCommande = document.getElementById(id_formulaire).commande.options[i].value;   
    }
    }

    var numOE = document.getElementById(id_formulaire).NumOE.value;

    if(document.getElementById(id_formulaire).NumOE.value == ''){
      alert('Veuillez selectionner un numOE');
    pb = 'pb';
    }



    send = "&numeroCommande="+numeroCommande+"&numOE="+numOE;
    }

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
    req.send(send);
    }
    }