package graphique.personnage.elements;

import sdljava.SDLException;
import sdljava.video.SDLRect;
import util.Position;

public class Case {

    // Position de l'element dans la fenetre
    private SDLRect position = null;

    private SDLRect sprite = null;

    private static final int WIDTH = 32;

    private static final int HEIGHT = 32;

    public Case(TypeCase typeCase) throws SDLException {
        this.setSprite(typeCase.getSdlRect());
        this.position = new SDLRect();
    }

    public Case(TypeCase typeCase, Position position) throws SDLException {
        this.setSprite(typeCase.getSdlRect());
        this.position = new SDLRect(position.getX() * WIDTH, position.getY() * HEIGHT);
    }

    public SDLRect getPosition() {
        return position;
    }

    public void setPosition(SDLRect position) {
        this.position = position;
    }

    public SDLRect getSprite() {
        return sprite;
    }

    public void setSprite(SDLRect sprite) {
        this.sprite = sprite;
    }
}
