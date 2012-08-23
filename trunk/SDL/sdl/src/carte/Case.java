package carte;

import sdljava.SDLException;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;

public class Case implements Affichage {
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	protected SDLSurface screen = null;
	protected SDLSurface surface = null;
	protected SDLRect position = null;

	@Override
	public void show() throws SDLException {
		this.surface.setClipRect(position);
		this.surface.blitSurface(this.screen,this.position);
		
	}
	
	@Override
	public void delete() throws SDLException {
		this.surface.freeSurface();
	}
}
