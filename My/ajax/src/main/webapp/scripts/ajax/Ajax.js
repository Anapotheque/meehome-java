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

    /*****************************************************************************************/
    /*                           Fonction de recuperation d'input                            */
    /*****************************************************************************************/

    function getInput(nom_Form,number){
    var result = "";
    result = document.getElementById(nom_Form)[number].value;
    return result;
    }

    /*****************************************************************************************/
    /*                           Fonction de recuperation des listes de checking             */
    /*****************************************************************************************/

    function getChecked(id_Check){
    var result = "";
    var id = new Array(document.getElementsByName(id).length);
    var NombreSelect = 0;
    var chaine = "";
    var j=1;

    for(i = 0 ; i< document.getElementsByName(id_Check).length; i++){
        if(document.getElementsByName(id_Check)[i].checked == true){
            id[i] = document.getElementsByName(id_Check)[i].value;   
            chaine = chaine+"&"+id_Check+j+"="+id[i];
            j++;
            NombreSelect= NombreSelect+1;
        }
    }

    if(NombreSelect != 0){
    result = "NombreSelect="+NombreSelect+chaine;
    }
    return result;
    }

    /*******************************************************************************************/
    /*    Fonction permettant de modifier le contenu d'un DIV selon son identifiant            */
    /*******************************************************************************************/

    function Ajax(id_div,servlet,nom_Form,send){

    //DEBUT DU CODE
    //id_div : Reference la div à remplacer
    //servlet : action servlet qui va remplir la div
    //nom_Form : nom du formulaire ou l'on recupere les informations
    //send : envoi de parametres via une autre servlet

    var sending = null;
    var pb = null;

    /**********************************************************************/
    /*RECUPERATION DES DONNEES FORMULAIRES                                */
    /**********************************************************************/
    if(nom_Form != "null"){

    /**********************************************************************/
    /*LOGIN                                                               */
    /**********************************************************************/

    if(servlet == "Login"){
        var login = getInput(nom_Form,"0");
        var password = getInput(nom_Form,"1");
        
        if(login == "" || password == ""){
            alert("Vous devez renseigner le champ login et le champ password");
            pb = "pb";
        }else{
            var sending = "login="+login+"&password="+password;
        }   
    }

    /**********************************************************************/
    /*FIN LOGIN                                                           */
    /**********************************************************************/
    /**********************************************************************/
    /*ACTION MAIL                                                        */
    /**********************************************************************/

    if(servlet == "ActionMail" ||
       servlet == "SendMail"   ||
       servlet == "AjouterPDF" ||
       servlet == "ChoixNewsLetter"){

        var numberselect;

      if(getChecked("idPDF") != ""){
        sending = getChecked("idPDF");
        numberselect = sending.charAt(13);
      }
      else{
        alert("Veuillez choisir un ou plusieur PDF");
        pb = "pb";
      }
      if(servlet == "ChoixNewsLetter"){
        if(numberselect > 1 ){
             alert("Veuillez choisir un seul PDF");
             pb = "pb";
        }
      }
    }

    /**********************************************************************/
    /*FIN ACTION MAIL                                                     */
    /**********************************************************************/
    /**********************************************************************/
    /*CHOIXNEWSLETTER                                                     */
    /**********************************************************************/
    if(servlet == "ActionNewsLetter" ||
       servlet == "AjouterDestinataires"){

      if(getChecked("idContact") != ""){
        sending = getChecked("idContact");
      }
      else{
        alert("Veuillez choisir un ou plusieur contact");
        pb = "pb";
      }
    }

    /**********************************************************************/
    /*FIN CHOIXNEWSLETTER                                                 */
    /**********************************************************************/
    /**********************************************************************/
    /*ACTIONDIVERS                                                        */
    /**********************************************************************/
    if(servlet == "ActionDivers"){
        var sujet = getInput(nom_Form,"0");
        var body = getInput(nom_Form,"1");
        var sending = "sujet="+sujet+"&body="+body;
    }

    /**********************************************************************/
    /*FIN ACTIONDIVERS                                                    */
    /**********************************************************************/
    /**********************************************************************/
    /*NEW UPLOAD                                                          */
    /**********************************************************************/
    if(servlet == "SaveNewInfos"){
        var nom = document.getElementById(nom_Form).nom.value;
        var prenom = document.getElementById(nom_Form).prenom.value;
        var adresseMail = document.getElementById(nom_Form).adressemail.value;

        for(i = 0 ; i< document.getElementById(nom_Form).active.length; i++){
            if(document.getElementById(nom_Form).active.options[i].selected == true){
                var active = document.getElementById(nom_Form).active.options[i].value;   
            }
        }

        if(nom == "" || prenom == "" || adresseMail == ""){
            alert("Tout les champs doivent etre remplis");
            pb='pb';
        }else{
            var sending = "&nom="+nom+"&prenom="+prenom+"&adresseMail="+adresseMail+"&active="+active;
        }
    }

    /**********************************************************************/
    /*FIN NEW UPLOAD                                                      */
    /**********************************************************************/
    /**********************************************************************/
    /*RECHERCHE                                                           */
    /**********************************************************************/
    if(servlet == "Show" && send != "idfilter=0"){

    var search = document.getElementById(nom_Form).search.value;

    for(i = 0 ; i< document.getElementById(nom_Form).filter.length; i++){
        if(document.getElementById(nom_Form).filter.options[i].selected == true){
            var idfiltre = document.getElementById(nom_Form).filter.options[i].value;   
        }
    }
        sending = "&search="+search+"&idfilter="+idfiltre; 
    }

    /**********************************************************************/
    /*FIN RECHERCHE                                                       */
    /**********************************************************************/
    /**********************************************************************/
    /*Disabled ADRESSEMAIL                                                  */
    /**********************************************************************/
    if(servlet == "Disabled"){

      if(getChecked("idAdresseMail") != ""){
        sending = getChecked("idAdresseMail");
      }
      else{
        alert("Veuillez choisir une ou plusieurs adresses");
        pb = "pb";
      }
    }

    /**********************************************************************/
    /*FIN Disabled ADRESSEMAIL                                              */
    /**********************************************************************/
    /**********************************************************************/
    /*Enabled ADRESSEMAIL                                                  */
    /**********************************************************************/
    if(servlet == "Enabled"){
      if(getChecked("idAdresseMail") != ""){
        sending = getChecked("idAdresseMail");
      }
      else{
        alert("Veuillez choisir une ou plusieurs adresses");
        pb = "pb";
      }
    }
    /**********************************************************************/
    /*FIN Enabled ADRESSEMAIL                                              */
    /**********************************************************************/
    /**********************************************************************/
    /*MODIFY ADRESSEMAIL                                                  */
    /**********************************************************************/
    if(servlet == "Modify"){
        var NombreSelect = 0;
        var idAdresseMail = "";

        for(i = 0 ; i< document.getElementsByName("idAdresseMail").length; i++){
            if(document.getElementsByName("idAdresseMail")[i].checked == true){
                NombreSelect= NombreSelect+1;
                idAdresseMail = document.getElementsByName("idAdresseMail")[i].value;
            }
        }
            if(NombreSelect == 1){
                sending = "idAdresseMail="+idAdresseMail;
            }else if(NombreSelect > 1){
                alert("Veuillez choisir une seule adresse");
                pb = "pb";
            }else if(NombreSelect == 0){
                alert("Veuillez choisir une adresse");
                pb = "pb";
            }
    }

    /**********************************************************************/
    /*FIN MODIFY ADRESSEMAIL                                              */
    /**********************************************************************/
    /**********************************************************************/
    /*FIN RECUPERATION DES DONNEES FORMULAIRES                            */
    /**********************************************************************/
    }
    else{
        sending = send;
    }

    if(pb != "pb"){
    show("progress");
    var req = null;   
    req = getXMLHttpRequest();
    req.onreadystatechange = function(){
    if(req.readyState == 4){
    document.getElementById(id_div).innerHTML = req.responseText;
    hidden("progress");
    }
    };

    req.open("POST",servlet,true);
    req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    req.send(sending);
    }
    }