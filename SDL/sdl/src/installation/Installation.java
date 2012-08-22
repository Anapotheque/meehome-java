package installation;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class Installation {
	
	/**
	 *     	SDL_DISABLE 	: cache le curseur de la souris ;
    		SDL_ENABLE 		: affiche le curseur de la souris (actif d'origine) ;
    		SDL_DOUBLEBUF 	: active le Double Buffering ;
    		SDL_FULLSCREEN 	: affiche la fen�tre en plein �cran ;
    		SDL_HWACCEL 	: utilisation de l'acc�l�ration mat�rielle ;
    		SDL_HWSURFACE 	: les surfaces sont stock�es dans la m�moire vid�o (plus rapide) ;
    		SDL_OPENGL 		: vous avez devin� non ? :) Initialisation de OpenGL ;
    		SDL_SWSURFACE 	: les surfaces sont stock�es dans la m�moire syst�me (plus lent).
    		
	 * @throws SDLException
	 * @throws InterruptedException
	 */
	
	public Installation() throws SDLException, InterruptedException {
		
		// Initialisation SDL Video
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		
		// Creation de la fenetre principale
		SDLSurface screen = SDLVideo.setVideoMode(400, 200, 32, SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_DOUBLEBUF);
		
		// Definition du titre de la video
		SDLVideo.wmSetCaption("SDL Windows", null);
		
		// Changement de la couleur de fond de la fenetre
		long blue = screen.mapRGB(0, 0, 255);
		long red = screen.mapRGB(255, 0, 0);
		long green = screen.mapRGB(0, 255, 0);
		
		screen.fillRect(blue);
		screen.flip();
		
		// Reste ouvert qlq sec
		Thread.sleep(2000);
		
		screen.fillRect(red);
		screen.flip();
		
		// Reste ouvert qlq sec
		Thread.sleep(2000);
		
		screen.fillRect(green);
		screen.flip();
		
		// Reste ouvert qlq sec
		Thread.sleep(2000);
		
		// Liberation memoire de la fenetre
		screen.freeSurface();
		
		// Fermeture du pgm
		SDLMain.quit();
	}
	
	public static void main(String[] args) throws SDLException, InterruptedException {
		new Installation();
	}
}
