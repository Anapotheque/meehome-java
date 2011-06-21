@echo off
set utilisateur=%1
set base=%2
set fichier=%3
set motdepasse=%4

@echo -----------------------------------------------------------------------
@echo -- POPULATE DE L'ARCHITECTURE DE %base%
@echo -----------------------------------------------------------------------

mysql -u %utilisateur% -p%motdepasse% %base% < %fichier%