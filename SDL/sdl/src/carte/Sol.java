package carte;

import sdljava.SDLException;
import sdljava.image.SDLImage;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;

public class Sol extends Case  {
	
	public Sol(SDLSurface screen, SDLRect position) throws SDLException{
		position.height = 32;
		position.width = 32;
		this.position = position;
		this.screen = screen;
		this.surface = SDLImage.load("images/sprite.png");
		this.surface.fillRect(position, screen.mapRGB(0, 45, 78));
	}
}
