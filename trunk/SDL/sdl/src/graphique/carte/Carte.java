package graphique.carte;

import graphique.carte.elements.Case;
import graphique.carte.elements.TypeCase;

import java.util.ArrayList;
import java.util.List;

import sdljava.SDLException;
import sdljava.image.SDLImage;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import util.Position;
import util.SDLScreen;
import util.Util;

public class Carte extends SDLScreen {

    // Definition des elements de la carte
    public static final String PATH_TO_SPRITE = "images/sprite.png";

    // List chaque element du fond de carte
    private List<Case> font;

    // List chaque obstacle de la carte
    private List<Case> obstacle;

    private SDLSurface elementCarte;

    // Taille de la carte
    private static final int SIZE_MAX = 64;

    public Carte() throws SDLException {

        // Chargement des elements de la carte
        this.elementCarte = initialisationElementCarte();

        // Initialisation du fond de la carte
        this.font = initialisationFontCarte();

        // Initialisation des obstacles de la carte
        this.obstacle = initialisationObstacleCarte();

    }

    private SDLSurface initialisationElementCarte() throws SDLException {
        SDLSurface sdlSurface = SDLImage.load(PATH_TO_SPRITE);
        sdlSurface.setColorKey(SDLVideo.SDL_SRCCOLORKEY, sdlSurface.mapRGB(0, 0, 0));
        sdlSurface.setAlpha(SDLVideo.SDL_SRCALPHA, 200);
        return sdlSurface;
    }

    private List<Case> initialisationFontCarte() throws SDLException {
        List<Case> list = new ArrayList<Case>();
        for (int x = 0; x < SIZE_MAX; x++) {
            for (int y = 0; y < SIZE_MAX; y++) {
                list.add(new Case(TypeCase.Sol1, new Position(x, y)));
            }
        }
        return list;
    }

    private List<Case> initialisationObstacleCarte() throws SDLException {
        List<Case> list = new ArrayList<Case>();
        list.add(new Case(TypeCase.Barriere1, new Position(10, 10)));
        list.add(new Case(TypeCase.Barriere2, new Position(64, 10)));
        list.add(new Case(TypeCase.Barriere3, new Position(1, 0)));
        list.add(new Case(TypeCase.Barriere4, new Position(20, 10)));
        list.add(new Case(TypeCase.Barriere5, new Position(12, 10)));
        return list;
    }

    public void show() throws SDLException {
        for (Case obj : font) {
            Util.affichage(obj.getSprite(), obj.getPosition(), this.elementCarte);
        }
        for (Case obj : obstacle) {
            Util.affichage(obj.getSprite(), obj.getPosition(), this.elementCarte);
        }
    }

    public SDLSurface getElementCarte() {
        return elementCarte;
    }

    public void setElementCarte(SDLSurface elementCarte) {
        this.elementCarte = elementCarte;
    }

    @Override
    public void delete() throws SDLException {
        this.elementCarte.freeSurface();
    }

    @Override
    public void eventManager() throws SDLException {
    }
}
