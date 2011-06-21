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

    function Ajax(id_div,servlet){

    //id_div : Reference la div à remplacer
    //servlet : actio nservlet qui va remplir la div

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
    req.send(null);
    }
    