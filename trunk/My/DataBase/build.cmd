@ECHO OFF
set utilisateur=thetanos
set base=my
set motdepasse=

set fichier_drop=../scripts/drop_archi.sql
set fichier_creation=../scripts/creation_archi.sql
set fichier_populate=../scripts/populate_archi.sql
set fichier_control=../scripts/control_archi.sql

cd cmd

call drop.cmd 		%utilisateur% %base% %fichier_drop% 	%motdepasse%
call creation.cmd	%utilisateur% %base% %fichier_creation% %motdepasse%
call populate.cmd	%utilisateur% %base% %fichier_populate% %motdepasse%
call control.cmd 	%utilisateur% %base% %fichier_control% 	%motdepasse%

pause