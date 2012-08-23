package carte;

import java.util.HashMap;
import java.util.Map;

import sdljava.SDLException;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;

public class Carte implements Affichage {

	private Map<SDLRect, Object> map = new HashMap<SDLRect, Object>();
	
	public Carte(SDLSurface screen) throws SDLException{
		
		SDLRect position = null;
		
		position = new SDLRect(10, 10);
		map.put(position, new Brique(screen,position));
		
		
		/*
		position = new SDLRect(Case.WIDTH * 0, Case.HEIGHT * 1);
		map.put(position, new Brique(screen,position));
		*/
		/*
		position = new SDLRect(Case.WIDTH * 1, Case.HEIGHT * 0);
		map.put(position, new Brique(screen,position));
		
		position = new SDLRect(Case.WIDTH * 1, Case.HEIGHT * 1);
		map.put(position, new Brique(screen,position));*/
	}

	@Override
	public void delete() throws SDLException {
		for(Object obj : this.map.values()) {
			if (obj instanceof Brique) {
				((Brique) obj).delete();
			}
		}
	}

	@Override
	public void show() throws SDLException {
		for(SDLRect position : this.map.keySet()) {
			Object obj = this.map.get(position);
			if (obj instanceof Brique) {
				((Brique) obj).show();
			}
		}
	}
}
