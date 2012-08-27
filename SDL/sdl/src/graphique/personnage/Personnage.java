package graphique.personnage;

import graphique.carte.elements.Case;
import graphique.carte.elements.TypeCase;
import sdljava.SDLException;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.image.SDLImage;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import util.Position;
import util.SDLScreen;
import util.Util;

public class Personnage extends SDLScreen {

    // Definition des elements de la carte
    public static final String PATH_TO_SPRITE = "images/personnage/avion.gif";

    private Case personnage;

    private SDLSurface element;

    private boolean acceleration = false;

    boolean isRunning = true;

    public Personnage() throws SDLException {
        this.element = initialisationElement();
        personnage = new Case(TypeCase.Sol1, new Position(3, 3));
    }

    private SDLSurface initialisationElement() throws SDLException {
        SDLSurface sdlSurface = SDLImage.load(PATH_TO_SPRITE);
        sdlSurface.setColorKey(SDLVideo.SDL_SRCCOLORKEY, sdlSurface.mapRGB(0, 0, 0));
        sdlSurface.setAlpha(SDLVideo.SDL_SRCALPHA, 200);
        return sdlSurface;
    }

    @Override
    public void eventManager() {

        if (event instanceof SDLKeyboardEvent) {
            SDLKeyboardEvent keyEvent = (SDLKeyboardEvent ) event;

            if (keyEvent.getType() == SDLEvent.SDL_KEYUP) {
                switch (keyEvent.getSym()) {
                    case SDLKey.SDLK_LSHIFT:
                        this.acceleration = false;
                        break;
                }
            }

            if (keyEvent.getType() == SDLEvent.SDL_KEYDOWN) {
                switch (keyEvent.getSym()) {

                    case SDLKey.SDLK_LSHIFT:
                        this.acceleration = true;
                        break;

                    case SDLKey.SDLK_ESCAPE:
                        isRunning = false;
                        break;

                    case SDLKey.SDLK_UP:
                        if (this.acceleration)
                            this.personnage.getPosition().setY(this.personnage.getPosition().getY() - 4);
                        else
                            this.personnage.getPosition().setY(this.personnage.getPosition().getY() - 2);
                        break;

                    case SDLKey.SDLK_DOWN:
                        if (this.acceleration)
                            this.personnage.getPosition().setY(this.personnage.getPosition().getY() + 4);
                        else
                            this.personnage.getPosition().setY(this.personnage.getPosition().getY() + 2);
                        break;

                    case SDLKey.SDLK_LEFT:
                        if (this.acceleration)
                            this.personnage.getPosition().setX(this.personnage.getPosition().getX() - 4);
                        else
                            this.personnage.getPosition().setX(this.personnage.getPosition().getX() - 2);
                        break;

                    case SDLKey.SDLK_RIGHT:
                        if (this.acceleration)
                            this.personnage.getPosition().setX(this.personnage.getPosition().getX() + 4);
                        else
                            this.personnage.getPosition().setX(this.personnage.getPosition().getX() + 2);
                        break;
                }
            }
        }
    }

    @Override
    public void show() throws SDLException {
        Util.affichage(personnage.getSprite(), personnage.getPosition(), this.element);
    }

    @Override
    public void delete() throws SDLException {
        this.element.freeSurface();
    }

    public SDLSurface getElement() {
        return element;
    }

    public void setElement(SDLSurface element) {
        this.element = element;
    }

    public boolean isAcceleration() {
        return acceleration;
    }

    public void setAcceleration(boolean acceleration) {
        this.acceleration = acceleration;
    }

    public Case getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Case personnage) {
        this.personnage = personnage;
    }
}
