    /*****************************************************************************************/
    /*                           Chargement de la variable xhr                               */
    /*****************************************************************************************/
    
    function getXMLHttpRequest(){
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

    function getHTMLCodeRequest(id_div,servlet,send,id_formulaire,id){

    //id_div : Chaine permettant d'identifier la balise div
    //servlet: URL pattern de la servlet en question
    //send: valeurs à envoyées au serveur, null si aucunes valeurs à retourner
    //id_formulaire: correspond au formulaire que l'on veut recuperer
    //id: correspond au numero de l'element du tableau

    //Permet l'envoie de données avec l'ajax si un formulaire est saisi (! null )-----------------

    var sending = null;
    var pb = null;

    if(send == 'choix_menu=SupprAll' || send == 'choix_menu=New' || send == 'choix_menu=Delete' || send == 'choix_menu=Modify')
            if(!confirm('Etes vous certain ?'))
                pb = 'pb';

    if(id_formulaire != "null"){

    //--------------------------------------------------------------------------------------------
    //SI ON A CHOISI L'OPTION CONNECTION ON ENREGISTRE LES DONNEES DU FORMULAIRE DE CONNECTION
    //--------------------------------------------------------------------------------------------

    if(servlet == "connection"){
    var login = document.getElementById(id_formulaire).login.value;
    var password = document.getElementById(id_formulaire).password.value;

    sending = send+"&login="+login+"&password="+password;
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //INSCRIPTION ND'UN NOUVEL UTILISATEUR
    //--------------------------------------------------------------------------------------------
    else if(servlet == "InscriptionUtilisateurs"){

    var login = document.getElementById(id_formulaire).login.value;
    var password = document.getElementById(id_formulaire).password.value;
    var nom = document.getElementById(id_formulaire).nom.value;
    var prenom = document.getElementById(id_formulaire).prenom.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).sous_groupe.length; i++){
    if(document.getElementById(id_formulaire).sous_groupe.options[i].selected == true)
    var sous_groupe = document.getElementById(id_formulaire).sous_groupe.options[i].value;   
    }

    if(login == '' || password == '' || nom == '' || prenom == '' ){
      alert('Veuillez renseigner tout les champs');
    pb = 'pb';
    }


    sending = send+"&login="+login+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&sous_groupe="+sous_groupe+"&role=Intrus";  

    }
    //-------------------------------------------------------------------------------------------




































    

    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE UTILISATEUR
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT USERS
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewUtilisateurs"){
    var login = document.getElementById(id_formulaire).login.value;

    var password = document.getElementById(id_formulaire).password.value;
    var nom = document.getElementById(id_formulaire).nom.value;
    var prenom = document.getElementById(id_formulaire).prenom.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).sous_groupe.length; i++){
    if(document.getElementById(id_formulaire).sous_groupe.options[i].selected == true)
    var sous_groupe = document.getElementById(id_formulaire).sous_groupe.options[i].value;   
    }
    for(j = 0 ; j< document.getElementById(id_formulaire).role.length; j++){
    if(document.getElementById(id_formulaire).role.options[j].selected == true)
    var role = document.getElementById(id_formulaire).role.options[j].value;   
    }

    sending = send+"&login="+login+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&sous_groupe="+sous_groupe+"&role="+role;  
    }
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE USERS
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteUtilisateurs"){
    sending = send+"&codeUser="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE USERS
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyUtilisateurs"){
    if(id != "null"){
    sending = send+"&codeUser="+id;  
    }
    else if(id == "null"){
    var login = document.getElementById(id_formulaire).login.value;

    var password = document.getElementById(id_formulaire).password.value;
    var nom = document.getElementById(id_formulaire).nom.value;
    var prenom = document.getElementById(id_formulaire).prenom.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).sous_groupe.length; i++){
    if(document.getElementById(id_formulaire).sous_groupe.options[i].selected == true)
    var sous_groupe = document.getElementById(id_formulaire).sous_groupe.options[i].value;   
    }
    for(j = 0 ; j< document.getElementById(id_formulaire).role.length; j++){
    if(document.getElementById(id_formulaire).role.options[j].selected == true)
    var role = document.getElementById(id_formulaire).role.options[j].value;   
    }
    for(j = 0 ; j< document.getElementById(id_formulaire).bonus.length; j++){
    if(document.getElementById(id_formulaire).bonus.options[j].selected == true)
    var bonus = document.getElementById(id_formulaire).bonus.options[j].value;   
    }

    sending = send+"&login="+login+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&sous_groupe="+sous_groupe+"&role="+role+"&bonus="+bonus;  
    
    }

    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE USERS
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchUtilisateurs"){
    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;
    sending = send+"&mot_clef="+mot_clef;  
    }
    //-------------------------------------------------------------------------------------------



    //--------------------------------------------------------------------------------------------
    //INSCRIPTION ND'UN NOUVEL UTILISATEUR
    //--------------------------------------------------------------------------------------------
    else if(servlet == "InscriptionUtilisateurs"){

    var login = document.getElementById(id_formulaire).login.value;
    var password = document.getElementById(id_formulaire).password.value;
    var nom = document.getElementById(id_formulaire).nom.value;
    var prenom = document.getElementById(id_formulaire).prenom.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).sous_groupe.length; i++){
    if(document.getElementById(id_formulaire).sous_groupe.options[i].selected == true)
    var sous_groupe = document.getElementById(id_formulaire).sous_groupe.options[i].value;   
    }
    sending = send+"&login="+login+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&sous_groupe="+sous_groupe+"&role=Intrus";  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE UTILISATEUR
    //*******************************************************************************************















    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG AFFAIRE
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewAffaires"){

    var chaine = "";

    var chaine = "&nom="+document.getElementById(id_formulaire).nom.value;
    var chaine = chaine+"&observations="+document.getElementById(id_formulaire).observations.value;
    var chaine = chaine+"&imputation="+document.getElementById(id_formulaire).imputation.value;
    var chaine = chaine+"&compteEtude="+document.getElementById(id_formulaire).compteEtude.value;
    var sycomore = document.getElementById(id_formulaire).sycomore1.value+" | "+document.getElementById(id_formulaire).sycomore2.value+" | "+document.getElementById(id_formulaire).sycomore3.value;
    var chaine = chaine+"&sycomore="+sycomore;
    var chaine = chaine+"&parametrage="+document.getElementById(id_formulaire).parametrage.value;

    if(document.getElementById(id_formulaire).nom.value == '' || document.getElementById(id_formulaire).nom.value == 'A REMPLIR'){
      alert('Veuillez renseigner un nom');
    pb = 'pb';
    }
    else
        sending = send+chaine;  
    }

    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteAffaires"){
    sending = send+"&codeAffaire="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyAffaires"){
    if(id != "null"){
    sending = send+"&codeAffaire="+id;  
    }
    else if(id == "null"){

    var chaine = "";

    var chaine = "&nom="+document.getElementById(id_formulaire).nom.value;
    var chaine = chaine+"&observations="+document.getElementById(id_formulaire).observations.value;
    var chaine = chaine+"&imputation="+document.getElementById(id_formulaire).imputation.value;
    var chaine = chaine+"&compteEtude="+document.getElementById(id_formulaire).compteEtude.value;
    var chaine = chaine+"&sycomore="+document.getElementById(id_formulaire).sycomore.value;
    var chaine = chaine+"&parametrage="+document.getElementById(id_formulaire).parametrage.value;

    if(document.getElementById(id_formulaire).nom.value == ''){
      alert('Veuillez renseigner un nom');
    pb = 'pb';
    }

        sending = send+chaine;  
    
    }
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchAffaires"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;

    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE AFFAIRES
    //*******************************************************************************************




    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG FACTURE
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewFactures"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Entreprises.length; i++){
    if(document.getElementById(id_formulaire).Entreprises.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).Entreprises.options[i].value;   
    }

    var objet = document.getElementById(id_formulaire).objet.value;
    var referenceFacture = document.getElementById(id_formulaire).referenceFacture.value;
    var dateFacture = document.getElementById(id_formulaire).dateFacture.value;
    var imputation = document.getElementById(id_formulaire).imputation.value;
    var debit = document.getElementById(id_formulaire).debit.value;
    var credit = document.getElementById(id_formulaire).credit.value;

    if(codeEntreprise == 'null'){
        alert('Veuillez choisir une entreprise');
        pb = 'pb';
    }

        
    sending = send+"&codeEntreprise="+codeEntreprise+"&objet='"+objet+"'&referenceFacture="+referenceFacture+"&dateFacture="+dateFacture+"&imputation="+imputation+"&debit="+debit+"&credit="+credit;  
}
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteFactures"){
    sending = send+"&codeFacture="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyFactures"){
    if(id != "null"){
    sending = send+"&codeFacture="+id;  
    }
    else if(id == "null"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Entreprises.length; i++){
    if(document.getElementById(id_formulaire).Entreprises.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).Entreprises.options[i].value;   
    }

    var objet = document.getElementById(id_formulaire).objet.value;
    var referenceFacture = document.getElementById(id_formulaire).referenceFacture.value;
    var dateFacture = document.getElementById(id_formulaire).dateFacture.value;
    var imputation = document.getElementById(id_formulaire).imputation.value;
    var debit = document.getElementById(id_formulaire).debit.value;
    var credit = document.getElementById(id_formulaire).credit.value;

    sending = send+"&codeEntreprise="+codeEntreprise+"&objet="+objet+"&referenceFacture="+referenceFacture+"&dateFacture="+dateFacture+"&imputation="+imputation+"&debit="+debit+"&credit="+credit;  
     }

    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchFactures"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;

    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE FACTURE
    //*******************************************************************************************







 //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG RCH
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewRch"){

    var codeRch = document.getElementById(id_formulaire).codeRch.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).Agents.length; i++){
    if(document.getElementById(id_formulaire).Agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).Agents.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).Entreprises.length; i++){
    if(document.getElementById(id_formulaire).Entreprises.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).Entreprises.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).Etudes.length; i++){
    if(document.getElementById(id_formulaire).Etudes.options[i].selected == true)
    var codeEtude = document.getElementById(id_formulaire).Etudes.options[i].value;   
    }

    var emission = document.getElementById(id_formulaire).emission.value;
    var expedition = document.getElementById(id_formulaire).expedition.value;
    var observations = document.getElementById(id_formulaire).observations.value;
    var reception = document.getElementById(id_formulaire).reception.value;
    var remisAgent = document.getElementById(id_formulaire).remisAgent.value;
    var archive = document.getElementById(id_formulaire).archive.value;

    if(document.getElementById(id_formulaire).codeRch.value == ''){
    alert('Veuillez renseigner le "codeRch"');
    pb = 'pb';
    }

    sending = send+"&codeRch="+codeRch+"&codeAgent="+codeAgent+"&codeEntreprise="+codeEntreprise+"&codeEtude="+codeEtude+"&emission="+emission+"&expedition="+expedition+"&observations="+observations+"&remisAgent="+remisAgent+"&archive="+archive+"&reception="+reception;  
    
}
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteRch"){
    sending = send+"&codeRch="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyRch"){
    if(id != "null"){
    sending = send+"&codeRch="+id;  
    }
    else if(id == "null"){
    for(i = 0 ; i< document.getElementById(id_formulaire).indices.length; i++){
    if(document.getElementById(id_formulaire).indices.options[i].selected == true)
    var codeEtude = document.getElementById(id_formulaire).indices.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).agents.length; i++){
    if(document.getElementById(id_formulaire).agents.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).agents.options[i].value;   
    }
    for(i = 0 ; i< document.getElementById(id_formulaire).entreprises.length; i++){
    if(document.getElementById(id_formulaire).entreprises.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).entreprises.options[i].value;   
    }

    var emission = document.getElementById(id_formulaire).emission.value;
    var expedition = document.getElementById(id_formulaire).expedition.value;
    var observations = document.getElementById(id_formulaire).observations.value;
    var reception = document.getElementById(id_formulaire).reception.value;
    var remisAgent = document.getElementById(id_formulaire).remisAgent.value;
    var archive = document.getElementById(id_formulaire).archive.value;

    sending = send+"&codeRch="+codeRch+"&codeAgent="+codeAgent+"&codeEntreprise="+codeEntreprise+"&codeEtude="+codeEtude+"&emission="+emission+"&expedition="+expedition+"&observations="+observations+"&remisAgent="+remisAgent+"&archive="+archive+"&reception="+reception;  
    

    }

    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchRch"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;

    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE RCH
    //*******************************************************************************************






 //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG ENTREPRISES
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewEntreprises"){

    var entreprise = document.getElementById(id_formulaire).entreprise.value;
    var batiment = document.getElementById(id_formulaire).batiment.value;
    var rue = document.getElementById(id_formulaire).rue.value;
    var lieudit = document.getElementById(id_formulaire).lieudit.value;
    var codePostal = document.getElementById(id_formulaire).codePostal.value;
    var ville = document.getElementById(id_formulaire).ville.value;
    var specialite = document.getElementById(id_formulaire).specialite.value;
    var telephone = document.getElementById(id_formulaire).telephone.value;
    var fax = document.getElementById(id_formulaire).fax.value;
    var correspondant = document.getElementById(id_formulaire).correspondant.value;
    var memo = document.getElementById(id_formulaire).memo.value;
    var actif = document.getElementById(id_formulaire).actif.value;

    if(entreprise == 'A REMPLIR' || entreprise == ''){
    alert('Veuillez renseigner le champ Entreprise');
    pb = 'pb';
    }
    else
    sending = send+"&entreprise="+entreprise+"&batiment="+batiment+"&rue="+rue+"&lieudit="+lieudit+"&codePostal="+codePostal+"&ville="+ville+"&specialite="+specialite+"&telephone="+telephone+"&fax="+fax+"&correspondant="+correspondant+"&memo="+memo+"&actif="+actif;  
    
    }
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteEntreprises"){
    sending = send+"&codeEntreprise="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyEntreprises"){
    if(id != "null"){
    sending = send+"&codeEntreprise="+id;  
    }
    else if(id == "null"){

    var entreprise = document.getElementById(id_formulaire).entreprise.value;
    var batiment = document.getElementById(id_formulaire).batiment.value;
    var rue = document.getElementById(id_formulaire).rue.value;
    var lieudit = document.getElementById(id_formulaire).lieudit.value;
    var codePostal = document.getElementById(id_formulaire).codePostal.value;
    var ville = document.getElementById(id_formulaire).ville.value;
    var specialite = document.getElementById(id_formulaire).specialite.value;
    var telephone = document.getElementById(id_formulaire).telephone.value;
    var fax = document.getElementById(id_formulaire).fax.value;
    var correspondant = document.getElementById(id_formulaire).correspondant.value;
    var memo = document.getElementById(id_formulaire).memo.value;
    var actif = document.getElementById(id_formulaire).actif.value;


    if(entreprise == ''){
    alert('Veuillez renseigner le champ Entreprise');
    pb = 'pb';
    }
    else
    sending = send+"&entreprise="+entreprise+"&batiment="+batiment+"&rue="+rue+"&lieudit="+lieudit+"&codePostal="+codePostal+"&ville="+ville+"&specialite="+specialite+"&telephone="+telephone+"&fax="+fax+"&correspondant="+correspondant+"&memo="+memo+"&actif="+actif;  
    
    

    }

    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchEntreprises"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;

    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------
    //ON AFFICHE LA QUALITE DES ETUDES PAR ENTREPRISE
    //--------------------------------------------------------------------------------------------
    else if(servlet == "Qualites"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var codeEntreprise = document.getElementById(id_formulaire).filtre.options[i].value;   
    }
    sending = send+"&codeEntreprise="+codeEntreprise;  
    }

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE ENTREPRISES
    //*******************************************************************************************








    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG AGENTS
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewAgents"){
    var chaine = null;
    chaine = "&nom="+document.getElementById(id_formulaire).nom.value;
    chaine = chaine+"&prenom="+document.getElementById(id_formulaire).prenom.value;
    chaine = chaine+"&initiales="+document.getElementById(id_formulaire).initiales.value;
    chaine = chaine+"&qualification="+document.getElementById(id_formulaire).qualification.value;
    chaine = chaine+"&grade="+document.getElementById(id_formulaire).grade.value;
    chaine = chaine+"&cp="+document.getElementById(id_formulaire).cp.value;
    chaine = chaine+"&telephonesncf="+document.getElementById(id_formulaire).telephonesncf.value;
    chaine = chaine+"&telephoneptt="+document.getElementById(id_formulaire).telephoneptt.value;
    chaine = chaine+"&mail="+document.getElementById(id_formulaire).mail.value;

    if(document.getElementById(id_formulaire).nom.value == '' || document.getElementById(id_formulaire).nom.value == 'A REMPLIR'){
    alert('Veuillez renseigner le champ "nom"');
    pb = 'pb';
    }

    if(document.getElementById(id_formulaire).prenom.value == '' || document.getElementById(id_formulaire).nom.value == 'A REMPLIR'){
    alert('Veuillez renseigner le champ "prenom"');
    pb = 'pb';
    }

    else
    sending = send+chaine;  
    }
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteAgents"){
    sending = send+"&codeAgent="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyAgents"){
    if(id != "null"){
    sending = send+"&codeAgent="+id;  
    }
    else if(id == "null"){

    var chaine = "";

    chaine = "&nom="+document.getElementById(id_formulaire).nom.value;
    chaine = chaine+"&prenom="+document.getElementById(id_formulaire).prenom.value;
    chaine = chaine+"&initiales="+document.getElementById(id_formulaire).initiales.value;
    chaine = chaine+"&qualification="+document.getElementById(id_formulaire).qualification.value;
    chaine = chaine+"&grade="+document.getElementById(id_formulaire).grade.value;
    chaine = chaine+"&cp="+document.getElementById(id_formulaire).cp.value;
    chaine = chaine+"&telephonesncf="+document.getElementById(id_formulaire).telephonesncf.value;
    chaine = chaine+"&telephoneptt="+document.getElementById(id_formulaire).telephoneptt.value;
    chaine = chaine+"&mail="+document.getElementById(id_formulaire).mail.value;

    if(document.getElementById(id_formulaire).nom.value == ''){
    alert('Veuillez renseigner le champ "nom"');
    pb = 'pb';
    }
    if(document.getElementById(id_formulaire).prenom.value == ''){
    alert('Veuillez renseigner le champ "prenom"');
    pb = 'pb';
    }

    else
    sending = send+chaine;  
    
    }



    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchAgents"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;
    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON AFFICHE l'HISTORIQUE EN FONCTION D'UNE GARE CHOISIE
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SuivisAgentPT"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).filtre.options[i].value;   
    }
    sending = send+"&codeAgent="+codeAgent;  
    }
    else if(servlet == "SuivisAgentSE"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).filtre.options[i].value;   
    }
    sending = send+"&codeAgent="+codeAgent;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE AGENTS
    //*******************************************************************************************


    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG FMR
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewFmr"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Gare.length; i++){
    if(document.getElementById(id_formulaire).Gare.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).Gare.options[i].value;   
    }

    var numeroFmr = document.getElementById(id_formulaire).numeroFmr.value;


    var etablissement = document.getElementById(id_formulaire).etablissement.value;
    var envoi = document.getElementById(id_formulaire).envoi.value;
    var traitement = document.getElementById(id_formulaire).traitement.value;




    var accord = document.getElementById(id_formulaire).accord.value;

    var incorporation = document.getElementById(id_formulaire).incorporation.value;
    var memo = document.getElementById(id_formulaire).memo.value;

    if(document.getElementById(id_formulaire).numeroFmr.value == ''){
    alert('Veuillez renseigner le champ "numero Fmr"');
    pb = 'pb';
    }
    if(codeGare == ''){
    alert('Veuillez selectionner une gare');
    pb = 'pb';
    }


    sending = send+"&codeGare="+codeGare+"&numeroFmr="+numeroFmr+"&etablissement="+etablissement+"&envoi="+envoi+"&traitement="+traitement+"&accord="+accord+"&incorporation="+incorporation+"&memo="+memo;  
      
}
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteFmr"){
    sending = send+"&codeFmr="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyFmr"){
    if(id != "null"){
    sending = send+"&codeFmr="+id;  
    }
    else if(id == "null"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Gare.length; i++){
    if(document.getElementById(id_formulaire).Gare.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).Gare.options[i].value;   
    }
    var numeroFmr = document.getElementById(id_formulaire).numeroFmr.value;
    var etablissement = document.getElementById(id_formulaire).etablissement.value;
    var envoi = document.getElementById(id_formulaire).envoi.value;
    var traitement = document.getElementById(id_formulaire).traitement.value;
    var accord = document.getElementById(id_formulaire).accord.value;
    var incorporation = document.getElementById(id_formulaire).incorporation.value;
    var memo = document.getElementById(id_formulaire).memo.value;
   
    if(document.getElementById(id_formulaire).numeroFmr.value == ''){
    alert('Veuillez renseigner le champ "numero Fmr"');
    pb = 'pb';
    }
    if(codeGare == ''){
    alert('Veuillez selectionner une gare');
    pb = 'pb';
    }

    sending = send+"&codeGare="+codeGare+"&numeroFmr="+numeroFmr+"&etablissement="+etablissement+"&envoi="+envoi+"&traitement="+traitement+"&accord="+accord+"&incorporation="+incorporation+"&memo="+memo;  
    }



    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchFmr"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;
    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE FMR
    //*******************************************************************************************

    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG GARES
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewGares"){

    var gare = document.getElementById(id_formulaire).gare.value;
    var km = document.getElementById(id_formulaire).km.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).groupe.length; i++){
    if(document.getElementById(id_formulaire).groupe.options[i].selected == true)
    var groupe = document.getElementById(id_formulaire).groupe.options[i].value;   
    }

    var infos = document.getElementById(id_formulaire).infos.value;

    var codeEven = new Array(document.getElementById(id_formulaire).Even.length);

    var NombreSelect = 0;
    var chaine = "";
    var j=1;

    for(i = 0 ; i< document.getElementById(id_formulaire).Even.length; i++){
    if(document.getElementById(id_formulaire).Even.options[i].selected == true){
    codeEven[i] = document.getElementById(id_formulaire).Even.options[i].value;
    chaine= chaine+"&codeEven"+j+"="+codeEven[i];
    j++;
    NombreSelect= NombreSelect+1;
    }
    }

    if(document.getElementById(id_formulaire).gare.value == '' || document.getElementById(id_formulaire).gare.value == 'A REMPLIR'){
    
    alert('Veuillez renseigner le champ "GARE"');
    pb = 'pb';
    }

    if(document.getElementById(id_formulaire).km.value == '' || document.getElementById(id_formulaire).km.value == 'A REMPLIR'){
    alert('Veuillez renseigner le champ "LIGNE/KM"');
    pb = 'pb';
    }

    sending = send+"&gare="+gare+"&km="+km+"&groupe="+groupe+"&infos="+infos+"&NombreSelect="+NombreSelect+chaine; 
 
    }
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteGares"){
    sending = send+"&codeGare="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyGares"){   

    if(id != "null" && id!= "association1" && id!= "association2"){
    sending = send+"&codeGare="+id;  
    }
    else if(id == "null"){

    var gare = document.getElementById(id_formulaire).gare.value;
    var km = document.getElementById(id_formulaire).km.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).groupe.length; i++){
    if(document.getElementById(id_formulaire).groupe.options[i].selected == true)
    var groupe = document.getElementById(id_formulaire).groupe.options[i].value;   
    }

    var infos = document.getElementById(id_formulaire).infos.value;

    var codeSections = new Array(document.getElementById(id_formulaire).association1.length);
    var NombreSelect = 0;
    var chaine = "";
    var j=1;

    for(i = 0 ; i< document.getElementById(id_formulaire).association1.length; i++){
    if(document.getElementById(id_formulaire).association1.options[i].selected == true){
    codeSections[i] = document.getElementById(id_formulaire).Section.options[i].value;
    chaine= chaine+"&codeSections"+j+"="+codeSections[i];
    j++;
    NombreSelect= NombreSelect+1;
    }
    }

    if(document.getElementById(id_formulaire).gare.value == ''){
    alert('Veuillez renseigner le champ "GARE"');
    pb = 'pb';
    }

    if(document.getElementById(id_formulaire).km.value == ''){
    alert('Veuillez renseigner le champ "LIGNE/KM"');
    pb = 'pb';
    }

    sending = send+"&gare="+gare+"&km="+km+"&groupe="+groupe+"&infos="+infos+"&NombreSelect="+NombreSelect+chaine; 
    
    }
    else if(id == "association1"){

    for(i = 0 ; i< document.getElementById(id_formulaire).association1.length; i++){
    if(document.getElementById(id_formulaire).association1.options[i].selected == true)
    var codeEven = document.getElementById(id_formulaire).association1.options[i].value;   
    }

    sending = send+"&codeEven="+codeEven; 
    }

    else if(id == "association2"){

    for(i = 0 ; i< document.getElementById(id_formulaire).association2.length; i++){
    if(document.getElementById(id_formulaire).association2.options[i].selected == true)
    var codeEven = document.getElementById(id_formulaire).association2.options[i].value;   
    }

    sending = send+"&codeEven="+codeEven; 
    }

    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchGares"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;
    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }

    //--------------------------------------------------------------------------------------------
    //ON AFFICHE l'HISTORIQUE EN FONCTION D'UNE GARE CHOISIE
    //--------------------------------------------------------------------------------------------
    else if(servlet == "HistoriquesPT"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).filtre.options[i].value;   
    }
    sending = send+"&codeGare="+codeGare;  
    }
    else if(servlet == "HistoriquesSE"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).filtre.options[i].value;   
    }
    sending = send+"&codeGare="+codeGare;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE GARES
    //*******************************************************************************************



    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG EVEN
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewEven"){

    var nom = document.getElementById(id_formulaire).nom.value;

    var codeGare = new Array(document.getElementById(id_formulaire).Gare.length);

    var NombreSelect = 0;
    var chaine = "";
    var j=1;

    for(i = 0 ; i< document.getElementById(id_formulaire).Gare.length; i++){
    if(document.getElementById(id_formulaire).Gare.options[i].selected == true){
    codeGare[i] = document.getElementById(id_formulaire).Gare.options[i].value;
    chaine= chaine+"&codeGare"+j+"="+codeGare[i];
    j++;
    NombreSelect= NombreSelect+1;
    }
    }

   if(document.getElementById(id_formulaire).nom.value == '' || document.getElementById(id_formulaire).nom.value == 'A REMPLIR'){
    alert('Veuillez renseigner le champ "nom"');
    pb = 'pb';
    }

    sending = send+"&nom="+nom+"&NombreSelect="+NombreSelect+chaine;  
    }
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteEven"){
    sending = send+"&codeEven="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyEven"){
    if(id != "null" && id!= "association1" && id!= "association2"){
    sending = send+"&codeEven="+id;  
    }
    else if(id == "null"){

    var nom = document.getElementById(id_formulaire).nom.value;

    sending = send+"&nom="+nom;  

    }else if(id == "association1"){
    for(i = 0 ; i< document.getElementById(id_formulaire).association1.length; i++){
    if(document.getElementById(id_formulaire).association1.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).association1.options[i].value;   
    }

   if(document.getElementById(id_formulaire).nom.value == ''){
    alert('Veuillez renseigner le champ "nom"');
    pb = 'pb';
    }

    sending = send+"&codeGare="+codeGare; 
    }

    else if(id == "association2"){

    for(i = 0 ; i< document.getElementById(id_formulaire).association2.length; i++){
    if(document.getElementById(id_formulaire).association2.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).association2.options[i].value;   
    }

    sending = send+"&codeGare="+codeGare; 
    }

    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchEven"){

    for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;
    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE EVEN
    //*******************************************************************************************






    //*******************************************************************************************
    //                          GESTION AJAX POUR LA TABLE DVSG ETUDES
    //*******************************************************************************************

    //--------------------------------------------------------------------------------------------
    //ON ENREGISTRE LES DONNEES DU FORMULAIRE D'AJOUT
    //--------------------------------------------------------------------------------------------
    else if(servlet == "NewEtudes"){

    if(id == -1){
    
    for(i = 0 ; i< document.getElementById(id_formulaire).Gares.length; i++){
    if(document.getElementById(id_formulaire).Gares.options[i].selected == true)
    var codeGare = document.getElementById(id_formulaire).Gares.options[i].value;
    }

    sending = "choix_menu="+codeGare;
    }
    else{

    var chaine = "";

    var indice = document.getElementById(id_formulaire).indice.value;
    var codeAffaire = new Array(document.getElementById(id_formulaire).Affaires.length);
    var codeAgent = new Array(document.getElementById(id_formulaire).Agents.length);

    var NombreSelectAffaires = 0;
    var NombreSelectAgents = 0;

    
    
    var j=1;
    for(i = 0 ; i< document.getElementById(id_formulaire).Affaires.length; i++){
    if(document.getElementById(id_formulaire).Affaires.options[i].selected == true){
    codeAffaire[i] = document.getElementById(id_formulaire).Affaires.options[i].value;
    chaine= chaine+"&codeAffaire"+j+"="+codeAffaire[i];
    j++;
    NombreSelectAffaires = NombreSelectAffaires+1;
    }
    }

    var j=1;
    for(i = 0 ; i< document.getElementById(id_formulaire).Agents.length; i++){
    if(document.getElementById(id_formulaire).Agents.options[i].selected == true){
    codeAgent[i] = document.getElementById(id_formulaire).Agents.options[i].value;
    chaine= chaine+"&codeAgent"+j+"="+codeAgent[i];
    j++;
    NombreSelectAgents = NombreSelectAgents+1;
    }
    }

    for(i = 0 ; i< document.getElementById(id_formulaire).Gares.length; i++){
    if(document.getElementById(id_formulaire).Gares.options[i].selected == true){
    codeGare = document.getElementById(id_formulaire).Gares.options[i].value;
    chaine= chaine+"&codeGare="+codeGare;
    }
    }

    for(i = 0 ; i< document.getElementById(id_formulaire).Entreprises.length; i++){
    if(document.getElementById(id_formulaire).Entreprises.options[i].selected == true){
    codeEntreprise = document.getElementById(id_formulaire).Entreprises.options[i].value;
    chaine= chaine+"&codeEntreprise="+codeEntreprise;
    }
    }

    if(codeGare == 'null'){
        alert('Veuillez choisir une gare');
        pb = 'pb';
    }
    if(codeEntreprise == 'null'){
        alert('Veuillez choisir une entreprise');
        pb = 'pb';
    }
    if(codeAffaire[0] == 'null'){
        alert('Veuillez choisir une ou des affaires');
        pb = 'pb';
    }
    if(codeAgent[0] == 'null'){
        alert('Veuillez choisir un ou des agents');
        pb = 'pb';
    }

    sending = send+"&indice="+indice+"&NombreSelectAgents="+NombreSelectAgents+"&NombreSelectAffaires="+NombreSelectAffaires+chaine;  
    }
    }
    //-------------------------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------------------------
    //ON SUPPRIME UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "DeleteEtudes"){
    sending = send+"&codeEtude="+id;  
    }
    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON MODIFIE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "ModifyEtudes"){

    if(id != "null" && id!= "Affaire1" && id!= "Affaire2" && id!= "Agent1" && id!= "Agent2"){
    sending = send+"&codeEtude="+id;  
    }
    else if(id == "null"){

    var indice = document.getElementById(id_formulaire).indice.value;
    var detail = document.getElementById(id_formulaire).detail.value;
    var miseEnService = document.getElementById(id_formulaire).miseEnService.value;

    var envoiTx = document.getElementById(id_formulaire).envoiTx.value;
    var sescoTx = document.getElementById(id_formulaire).sescoTx.value;

    for(i = 0 ; i< document.getElementById(id_formulaire).Gares.length; i++){
    if(document.getElementById(id_formulaire).Gares.options[i].selected == true){
    codeGare = document.getElementById(id_formulaire).Gares.options[i].value;
    chaine= chaine+"&codeGare="+codeGare;
    }
    }

    for(i = 0 ; i< document.getElementById(id_formulaire).Entreprises.length; i++){
    if(document.getElementById(id_formulaire).Entreprises.options[i].selected == true){
    codeEntreprise = document.getElementById(id_formulaire).Entreprises.options[i].value;
    chaine= chaine+"&codeEntreprise="+codeEntreprise;
    }
    }

    for(i = 0 ; i< document.getElementById(id_formulaire).difficulte.length; i++){
    if(document.getElementById(id_formulaire).difficulte.options[i].selected == true)
    var difficulte = document.getElementById(id_formulaire).difficulte.options[i].value;
    }

    var poidsKg = document.getElementById(id_formulaire).poidsKg.value;
    var contraires = document.getElementById(id_formulaire).contraires.value;
    var autres = document.getElementById(id_formulaire).autres.value;


if(document.getElementById(id_formulaire).relations.checked)  var relations = 1;
else    var relations = 0;

if(document.getElementById(id_formulaire).materiel.checked)  var materiel = 1;
else    var materiel = 0;

if(document.getElementById(id_formulaire).delais.checked)  var delais = 1;
else    var delais = 0;

if(document.getElementById(id_formulaire).reports.checked)  var reports = 1;
else    var reports = 0;

if(document.getElementById(id_formulaire).metre.checked)  var metre = 1;
else    var metre = 0;

    var projet = document.getElementById(id_formulaire).projet.value;
    var etudeTravaux = document.getElementById(id_formulaire).etudeTravaux.value;
    var conforme = document.getElementById(id_formulaire).conforme.value;

    if(codeGare == 'null'){
        alert('Veuillez choisir une gare');
        pb = 'pb';
    }
    else{
    var qualite ="&difficulte="+difficulte+"&poidsKg="+poidsKg+"&contraires="+contraires+"&autres="+autres+"&relations="+relations+"&materiel="+materiel+"&delais="+delais+"&reports="+reports+"&metre="+metre;
    sending = send+"&indice="+indice+"&detail="+detail+"&miseEnService="+miseEnService+"&codeEntreprise="+codeEntreprise+"&envoiTx="+envoiTx+"&sescoTx="+sescoTx+"&codeGare="+codeGare+"&projet="+projet+"&etudeTravaux="+etudeTravaux+"&conforme="+conforme+qualite;
    }
    }
    


    else if(id == "Affaire1"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Affaire1.length; i++){
    if(document.getElementById(id_formulaire).Affaire1.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).Affaire1.options[i].value;   
    }

    

    sending = send+"&codeAffaire="+codeAffaire; 
    }

    else if(id == "Affaire2"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Affaire2.length; i++){
    if(document.getElementById(id_formulaire).Affaire2.options[i].selected == true)
    var codeAffaire = document.getElementById(id_formulaire).Affaire2.options[i].value;   
    }

    sending = send+"&codeAffaire="+codeAffaire; 
    }

    else if(id == "Agent1"){
    for(i = 0 ; i< document.getElementById(id_formulaire).Agent1.length; i++){
    if(document.getElementById(id_formulaire).Agent1.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).Agent1.options[i].value;   
    }

    sending = send+"&codeAgent="+codeAgent; 

    }

    else if(id == "Agent2"){

    for(i = 0 ; i< document.getElementById(id_formulaire).Agent2.length; i++){
    if(document.getElementById(id_formulaire).Agent2.options[i].selected == true)
    var codeAgent = document.getElementById(id_formulaire).Agent2.options[i].value;   
    }
    sending = send+"&codeAgent="+codeAgent; 
    }
}
    

    //-------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------
    //ON RECHERCHE UN ELEMENT DE LA TABLE 
    //--------------------------------------------------------------------------------------------
    else if(servlet == "SearchEtudes"){

        for(i = 0 ; i< document.getElementById(id_formulaire).filtre.length; i++){
    if(document.getElementById(id_formulaire).filtre.options[i].selected == true)
    var filtre = document.getElementById(id_formulaire).filtre.options[i].value;   
    }

    var mot_clef = document.getElementById(id_formulaire).mot_clef.value;
    sending = send+"&mot_clef="+mot_clef+"&filtre="+filtre;  
    }
    //-------------------------------------------------------------------------------------------

    //*******************************************************************************************
    //                          FIN GESTION AJAX POUR LA TABLE ETUDES
    //*******************************************************************************************




    }  
 




















































































    //-------------------------------------------------------------------------------------------
    //Permet d'envoyer directement sur la servlet sans passer par l'envoie de formulaire
    //-------------------------------------------------------------------------------------------

    else{
    sending = send;
    }

    //--------------------------------------------------------------------------------------------
    //          ENVOIE DES DONNEES PAR AJAX
    //--------------------------------------------------------------------------------------------
    
    if(pb == null){
    show(); //Affichage du menu opur faire patienter
    var req = null;   
    req = getXMLHttpRequest();
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
    //-------------------------------------------------------------------------------------------
    }
