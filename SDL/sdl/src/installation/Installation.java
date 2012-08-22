package installation;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class Installation {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private static final int COLOR = 32;
	
	/**
	 * tuto : http://www.siteduzero.com/tutoriel-3-90661-les-surfaces.html
	 *     	SDL_DISABLE 	: cache le curseur de la souris ;
    		SDL_ENABLE 		: affiche le curseur de la souris (actif d'origine) ;
    		SDL_DOUBLEBUF 	: active le Double Buffering ;
    		SDL_FULLSCREEN 	: affiche la fenêtre en plein écran ;
    		SDL_HWACCEL 	: utilisation de l'accélération matérielle ;
    		SDL_HWSURFACE 	: les surfaces sont stockées dans la mémoire vidéo (plus rapide) ;
    		SDL_OPENGL 		: vous avez deviné non ? :) Initialisation de OpenGL ;
    		SDL_SWSURFACE 	: les surfaces sont stockées dans la mémoire système (plus lent).
    		
	 * @throws SDLException
	 * @throws InterruptedException
	 */
	public Installation() throws SDLException, InterruptedException {
		
		// Initialisation SDL Video
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		
		// Creation de la fenetre principale
		SDLSurface screen = SDLVideo.setVideoMode(WIDTH, HEIGHT, COLOR, SDLVideo.SDL_ENABLE | SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_DOUBLEBUF);
		
		// Definition du titre de la video
		SDLVideo.wmSetCaption("SDL Windows", null);
		
		// Changement de la couleur de fond de la fenetre
		long blue = screen.mapRGB(0, 0, 255);
		long red = screen.mapRGB(255, 0, 0);
		long green = screen.mapRGB(0, 255, 0);
		
		screen.fillRect(blue);
		
		// On crée un nouveau rectangle
		SDLSurface rectangle = SDLVideo.createRGBSurface(SDLVideo.SDL_HWSURFACE, 200, 150, 32, 0, 0, 0, 0);
		// On attache le rectangle sur la fenêtre (qui est une SDLSurface du doux nom de screen)
		rectangle.blitSurface(screen);
		
		// On affiche le tout ! C'est la fenêtre qui contient la surface rectangle, donc c'est elle qu'on flip
		screen.flip();
		
		//////////////////////////////////////////////////
		Thread.sleep(2000);
		screen.freeSurface();
		SDLMain.quit();
	}
	
	public static void main(String[] args) throws SDLException, InterruptedException {
		new Installation();
	}
}
