package carte.elements;

import sdljava.SDLException;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;
import util.Position;
import carte.Affichable;

public class Case implements Affichable {

    public static final int WIDTH = 32;

    public static final int HEIGHT = 32;

    protected SDLSurface screen = null;

    protected SDLSurface surface = null;

    protected SDLRect position = null;

    public Case(SDLSurface screen, Position position) throws SDLException {
        this.screen = screen;
        this.position = new SDLRect(Case.WIDTH * position.getX(), Case.HEIGHT * position.getY());
    }

    @Override
    public void show() throws SDLException {
        this.surface.blitSurface(this.screen, this.position);

    }

    @Override
    public void delete() throws SDLException {
        this.surface.freeSurface();
    }
}
