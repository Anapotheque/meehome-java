    function SelectAll(id){
       for(i = 0 ; i< document.getElementsByName(id).length; i++){
       document.getElementsByName(id)[i].checked = true;
      }
    }

    function DeSelectAll(id){
       for(i = 0 ; i< document.getElementsByName(id).length; i++){
       document.getElementsByName(id)[i].checked = false;
      }
    }

    function GenerationAdresseMail(form){

        var nom = document.getElementById(form).nom.value;
        var prenom = document.getElementById(form).prenom.value;
        var adressemail = document.getElementById(form).adressemail.value;

        //CAS : 010, 100, 110  | 1 : modified, 0 : non rempli
        if(nom == "" && prenom != "" && adressemail =="@alliance-concept.fr" || nom != "" && prenom == "" && adressemail =="@alliance-concept.fr" || nom != "" && prenom != "" && adressemail =="@alliance-concept.fr"){
            document.getElementById(form).adressemail.value = prenom.charAt(0)+nom+'@alliance-concept.fr';
        }
    }