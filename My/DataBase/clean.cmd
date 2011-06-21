@ECHO OFF
set utilisateur=thetanos
set base=my
set motdepasse=

set fichier_purge=../scripts/purge_archi.sql
set fichier_populate=../scripts/populate_archi.sql
set fichier_control=../scripts/control_archi.sql

cd cmd

call purge.cmd %utilisateur% %base% %fichier_purge% %motdepasse%
call populate.cmd	%utilisateur% %base% %fichier_populate% %motdepasse%
call control.cmd 	%utilisateur% %base% %fichier_control% 	%motdepasse%

pause