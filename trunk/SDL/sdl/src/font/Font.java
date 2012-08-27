package font;

import sdljava.SDLException;
import sdljava.ttf.SDLTTF;
import sdljava.ttf.SDLTrueTypeFont;
import sdljava.video.SDLColor;
import sdljava.video.SDLSurface;
import util.SDLScreen;
import util.Util;

public class Font extends SDLScreen {

    SDLTrueTypeFont titre;

    SDLTrueTypeFont texte;

    SDLSurface monTexte;

    public Font() throws SDLException {

        // Initialisation de la bibliothèque SDL_ttf
        SDLTTF.init();
        // Ouverture d'un font
        monFont = SDLTTF.openFont("font/verdana.ttf", 16);

        SDLColor blanc = new SDLColor(255, 255, 255);

        // Un texte à l'écran n'est qu'une SDLSurface
        monTexte = monFont.renderTextBlended("Bienvenu sur Arena 1.0", blanc);
    }

    public void show() throws SDLException {
        Util.affichage(10, 10, monTexte);
    }

    public void delete() throws SDLException {
        monTexte.freeSurface();
        monFont.closeFont();
        SDLTTF.quit();
    }

    @Override
    public void eventManager() throws SDLException {
    }
}
