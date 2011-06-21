@echo off
set utilisateur=thetanos
set base=my
set motdepasse=


@echo -----------------------------------------------------------------------
@echo -- CONNEXION A MY
@echo -----------------------------------------------------------------------

mysql -u %utilisateur% -p%motdepasse% %base%
