package installation;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.image.SDLImage;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;
import sdljava.video.SDLVideo;

public class Installation {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 545;
	private static final int COLOR = 32;
	
	/**
	 * tuto : http://www.siteduzero.com/tutoriel-3-90661-les-surfaces.html
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
	
	private SDLSurface screen = null;
	
	public void init() throws SDLException {
		// Initialisation SDL Video
		SDLMain.init(SDLMain.SDL_INIT_VIDEO);
		
		// Creation de la fenetre principale
		screen = SDLVideo.setVideoMode(WIDTH, HEIGHT, COLOR, SDLVideo.SDL_ENABLE | SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_DOUBLEBUF);
		
		// Definition du titre de la video
		SDLVideo.wmSetCaption("My SDL", null);
	}
	
	public Installation() throws SDLException, InterruptedException {
		
		init();
		
		// Gestion des evenements 1/2
		SDLEvent event = null;
		boolean running = true;
		
		SDLSurface image = SDLImage.load("images/font.jpg");
		SDLRect imagePos = new SDLRect(0, 0);
		
		SDLSurface tank = SDLImage.load("images/tank.png");
		tank.setColorKey(SDLVideo.SDL_SRCCOLORKEY, tank.mapRGB(255, 0, 255));
		SDLRect tankPos = new SDLRect(100, 100);
		
		// Gestion de la transparence
		tank.setAlpha(SDLVideo.SDL_SRCALPHA, 200);
		
		/*
		 *  Gestion des �v�nement 2/2
		 *  
		 *  Boucle Principale
		 *  
		 *  Le code est ex�cut� TANT QUE la variable bool�ene boucle est vrai
		 *  SI l'�v�nement SDL_QUIT est vrai ALORS la variable boucle est fausse et donc on sort de la boucle
		 */
		while (running) {
			event = SDLEvent.waitEvent();
			
			if (event.getType() == SDLEvent.SDL_QUIT){
				running = false;
			}
			if (event.getType() == SDLEvent.SDL_KEYDOWN) {
				// On g�n�re un nouvel �v�nement de type SDLKeyboardEvent - Attention au cast !
				SDLKeyboardEvent eventK = (SDLKeyboardEvent) event;
				
				// Les m�thodes getType() et getSym() se ressemblent sur le principe d'utilisation
				switch (eventK.getSym()) {
				
				// Si la touche espace est press�e 
				case SDLKey.SDLK_ESCAPE: running = false;break;
				}
			}
			
			image.blitSurface(screen, imagePos);
			tank.blitSurface(screen, tankPos);
			screen.flip();
		}
		
		

		// On quitte :: Lib�ration de la m�moire
		tank.freeSurface();
		image.freeSurface();
		screen.freeSurface();
		SDLMain.quit();
	}
	
	public static void main(String[] args) throws SDLException, InterruptedException {
		new Installation();
	}
}
