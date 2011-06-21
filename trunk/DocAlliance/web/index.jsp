<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="SHORTCUT ICON" href="img/Ico.ico"/>
        <%-- TITRE DOCUMENT --%>
        <title>DocAlliance</title>
        
        <%-- CHARGEMENT DES CSS DE STRUCTURE--%>
        <link type="text/css" rel="stylesheet" href="./css/structure/header.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/container.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/box.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/content.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/footer.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/message/validation/validation.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/message/erreur/erreur.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/message/warning/warning.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/message/admin/admin.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/chargement.css" />
        <link type="text/css" rel="stylesheet" href="./css/structure/data.css" />
        <%-- CHARGEMENT DES CSS OUTILS ET DIVERS--%>
        <link type="text/css" rel="stylesheet" href="./css/main.css" />
        <link type="text/css" rel="stylesheet" href="./css/outils/menu-deroulant.css" />
        <link type="text/css" rel="stylesheet" href="./css/outils/bouton.css" />
        <link type="text/css" rel="stylesheet" href="./css/outils/table.css" />
        <link type="text/css" rel="stylesheet" href="./css/outils/select.css" />
        
        <%-- CHARGEMENT DES SCRIPTS --%>
        <script src="./scripts/ajax/Ajax.js" type="text/javascript"></script>
        <script src="./scripts/Divers.js" type="text/javascript"></script>
        <script src="./scripts/Reload.js" type="text/javascript"></script>
        <script src="./scripts/ShowDiv.js" type="text/javascript"></script>
        <script src="./scripts/menu-deroulant.js" type="text/javascript"></script>
        
    </head>
    <body>
        
        <%-- CHARGEMENT DU MENU GRAPHIQUE HAUT--%>
        <div class="header">
            <div id="progress"></div>
        </div>
        
        <%-- CHARGEMENT DU MENU GRAPHIQUE --%>
        <div class="box" style='text-align : center;'>
            
            <%-- CHARGEMENT DU HAUT --%>
            <div class="header-box"></div>
            
            <%-- CHARGEMENT DU CONTENU --%>
            <div class="content-box">
                
                <%-- BALISE DE RECHARGEMENT AJAX --%>
                <div id="rechargementMenu">
                    <%-- INFORMATION DE CONNECTION    --%>
                    <form id="champ" action="javascript:Ajax('rechargementMenu','Login','champ')">
                        
                        <table style='width : 300px; font-size: 120%; font-weight: bold'>
                            <tr>
                                <td>Utilisateur</td>
                                <td><input type="text" id='login' name="login"></td>
                            </tr>
                            <tr>
                                <td>Mot de passe</td>
                                <td><input type="password" id='password' name="password"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Connexion"></td>
                            </tr>                            
                        </table>
                        
                    </form>
                    <%-- FIN BALISE DE RECHARGEMENT AJAX --%>
                </div>
                
                <%-- FIN CHARGEMENT DU CONTENU --%>
            </div>
            
            <%-- CHARGEMENT DU BAS --%>
            <div class="bottom-box"></div>
            
            <%-- FIN CHARGEMENT DU MENU GRAPHIQUE --%>
        </div>
        
        <%-- FIN CHARGEMENT DU MENU GRAPHIQUE --%>
        <div class='box'>
            
            <%-- CHARGEMENT DU HAUT --%>
            <div class='header-box'></div>
            
            <%-- CHARGEMENT DU CONTENU --%>
            <div class='content-box'>
                
                <%-- BALISE DE RECHARGEMENT AJAX --%>
                <div id="Accueil">
                </div>
                
                
                <%-- FIN CHARGEMENT DU CONTENU --%>
            </div>
            
            <%-- CHARGEMENT DU BAS --%>
            <div class='bottom-box'></div>
            
            <%-- FIN CHARGEMENT DU MENU GRAPHIQUE --%>
        </div>
        
        <%-- CHARGEMENT DU MENU GRAPHIQUE BAS --%>
        <div class="footer"><br><a href="http://www.alliance-concept.fr" target="_BLANK">Alliance</a></div>
        
    </body>
</html>
