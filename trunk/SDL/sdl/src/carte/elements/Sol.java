package carte.elements;

import sdljava.SDLException;
import sdljava.image.SDLImage;
import sdljava.video.SDLSurface;
import util.Position;

public class Sol extends Case {

    public Sol(SDLSurface screen, Position position) throws SDLException {
        super(screen, position);
        this.surface = SDLImage.load("images/carte/sol.png");
    }
}
