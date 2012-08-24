package carte.elements;

import sdljava.SDLException;
import sdljava.image.SDLImage;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;
import util.Position;

public class Barriere extends Case {

    public Barriere(SDLSurface screen, Position position) throws SDLException {
        super(screen, position);
        this.surface = SDLImage.load("images/carte/barriere.png");

        // Gestion de la transparence
        this.surface.setColorKey(SDLVideo.SDL_SRCCOLORKEY, this.surface.mapRGB(0, 0, 0));
        this.surface.setAlpha(SDLVideo.SDL_SRCALPHA, 200);
    }

    public Barriere(SDLSurface screen, Position position, boolean gauche) throws SDLException {
        super(screen, position);
        this.surface = SDLImage.load("images/carte/barriereDroite1.png");

        // Gestion de la transparence
        this.surface.setColorKey(SDLVideo.SDL_SRCCOLORKEY, this.surface.mapRGB(0, 0, 0));
        this.surface.setAlpha(SDLVideo.SDL_SRCALPHA, 200);
    }
}
