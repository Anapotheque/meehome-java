<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        
        <title>SNCF V3.0 AJAX MVC CSS</title>
        
        <!Inclusion des feuilles de styles>
        <link rel=stylesheet type="text/css" href="css/main.css">
        <link rel=stylesheet type="text/css" href="css/corps.css">
        <link rel=stylesheet type="text/css" href="css/cadre.css">
        <link rel=stylesheet type="text/css" href="css/onglets.css">
        <link rel=stylesheet type="text/css" href="css/bouton.css">
        <link rel=stylesheet type="text/css" href="css/menu.css">
        <link rel=stylesheet type="text/css" href="css/doneeSaisie.css">
        <link rel=stylesheet type="text/css" href="css/calendrier.css">
        <link rel=stylesheet type="text/css" href="css/icone.css">
        
        <!Inclusion des scripts>
        <script src="scripts/images.js" type="text/javascript"></script>
        <script src="scripts/divers.js" type="text/javascript"></script>
        <script src="scripts/AjaxEtudes.js" type="text/javascript"></script>
        <script src="scripts/AjaxCourrier.js" type="text/javascript"></script>
        <script src="scripts/menu.js" type="text/javascript"></script>
        <script src="scripts/calendrier.js" type="text/javascript"></script>
        <script src="scripts/AjaxCalques.js" type="text/javascript"></script>
        <script src="scripts/Functions.js" type="text/javascript"></script>
        <script src="scripts/Ajax.js" type="text/javascript"></script>
        <script src="scripts/AjaxTravaux.js" type="text/javascript"></script>
        <script src="scripts/ActionTravauxMarches.js" type="text/javascript"></script>

        
        
    </head>
    
    <body>   
        
        <div id="DivLoadFont">
            <div id="DivLoad">
                <h2>VOTRE PAGE EST EN COURS DE CHARGEMENT VEUILLEZ PATIENTER</h2>
                <img src="IMG/attente.gif" width="80" height="79" alt="Veuillez patienter"/>
            </div>               
        </div>
        
        <!Balise d'identification pour le remplacement des informations avec les servelts pour les menus ( connection, base, infos, recherche)>
        <div id="listInfos">
            
            <!Corps du cadre>
            <div id="ConnectionWindows"> 
                <div id="head">connexion</div>               
                
                <form id="champ"><br>
                    <h2>LOGIN</h2><input type="text" id="login" name="login" value="">   
                    <h2>PASSWORD</h2><input type="password" id="password" name="password" value="">
                </form>
                <!Lien vers la servlet 'connection' qui fera le lien avec un model java avec envoi de parametre 'mode_connect=connection' et remplacement Ajax opur la balise div id='listInfos'>
                
                <div id="bouton1">
                    <ul>
                        <li>
                            <a href="javascript:getHTMLCodeRequest('listInfos','connection','mode_connect=connection','champ')">Connexion</a>
                            <a href=javascript:getHTMLCodeRequest('informations','InscriptionUtilisateurs','choix_menu=null','null')>Inscription</a>
                        </li>
                    </ul>
                </div>
                
            </div>
            
            <div id="informations"></div>
            
        </div>
        
        
        <!-- CALENDRIER EN MODE CACHE CACHE -->

        <TABLE bgColor=#ffffff border=1 cellPadding=0 cellSpacing=3 id=calendar style="DISPLAY: none; POSITION: absolute; Z-INDEX: 4">
            <TBODY>
            <TR>
                <TD colSpan=7 vAlign=center>
                    <!-- Month combo box -->
                    <SELECT id=month onchange=newCalendar()> 
                        <SCRIPT language=JavaScript>
                            // Output months into the document.
                            // Select current month.
                            for (var intLoop = 0; intLoop < months.length; intLoop++)
                            document.write("<OPTION " +	(today.month == intLoop ? "Selected" : "") + ">" + months[intLoop]);
                                                </SCRIPT>
                    </SELECT> 
                    <!-- Year combo box -->
                    <SELECT id=year onchange=newCalendar()> 
                        <SCRIPT language=JavaScript>
                            // Output years into the document.
                            // Select current year.
                            for (var intLoop = 1900; intLoop < 2028; intLoop++)
                            document.write("<OPTION " + (today.year == intLoop ? "Selected" : "") + ">" + intLoop);
                                                </SCRIPT>
                    </SELECT> 
                    
                </TD>
            </TR>
            
            
            
            <TR class=days>
                <!-- Generate column for each day. -->
                <SCRIPT language=JavaScript>
                    // Output days.
                    for (var intLoop = 0; intLoop < days.length; intLoop++)
                    document.write("<TD>" + days[intLoop] + "</TD>");
                </SCRIPT>
            </TR>
            
            
            <TBODY class=dates id=dayList onclick="getDate('')" vAlign=center>
                <!-- Generate grid for individual days. -->
                <SCRIPT language=JavaScript>
                for (var intWeeks = 0; intWeeks < 6; intWeeks++)
                {
                document.write("<TR>");
                for (var intDays = 0; intDays < days.length; intDays++)
                document.write("<TD></TD>");
                document.write("</TR>");
                }
                </SCRIPT>
                
            <!-- Generate today day. --></TBODY>
            <TBODY>
                <TR>
                    <TD class=today colSpan=5 id=todayday onclick=getTodayDay()></TD>
                    <TD align=right colSpan=2><A href="javascript:HideCalendar();"><SPAN style="COLOR: black; FONT-SIZE: 10px"><B>Cacher</B></SPAN></A></TD>
                </TR>
            </TBODY>
            
        </TABLE>
        
    </body>
</html>

