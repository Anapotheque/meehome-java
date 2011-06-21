    ajouter = new Image();
    ajouter.src = "images/ajouter.gif"; /*graphique standard  */
    ajoutergris = new Image();
    ajoutergris.src = "images/ajoutergris.gif"; /*graphique noir & blanc */

    modifier = new Image();
    modifier.src = "images/modifier.gif"; /*graphique standard  */
    modifiergris = new Image();
    modifiergris.src = "images/modifiergris.gif"; /*graphique noir & blanc */

    supprimer = new Image();
    supprimer.src = "images/supprimer.gif"; /*graphique standard  */
    supprimergris = new Image();
    supprimergris.src = "images/supprimergris.gif"; /*graphique noir & blanc */

    search = new Image();
    search.src = "images/search.gif"; /*graphique standard  */
    searchgris = new Image();
    searchgris.src = "images/searchgris.gif"; /*graphique noir & blanc */

    suivant = new Image();
    suivant.src = "images/suivant.png"; /*graphique standard  */
    suivantgris = new Image();
    suivantgris.src = "images/suivant.png"; /*graphique noir & blanc */
      
    /**********************************************************/
    /*    Fonction permettant le changement d'une image       */
    /**********************************************************/
    
    function change_image(image_no,objet_image) {
    window.document.images[image_no].src = objet_image.src;
    }