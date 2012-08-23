package carte;

import sdljava.SDLException;
import sdljava.image.SDLImage;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;

public class Brique extends Case  {
	
	public Brique(SDLSurface screen, SDLRect position) throws SDLException{
		position.height = 32;
		position.width = 32;
		this.position = position;
		this.screen = screen;
		this.surface = SDLImage.load("images/sprite.png");
	}
}
