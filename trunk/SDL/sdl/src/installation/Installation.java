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
import carte.Carte;

public class Installation {

    private static final int WIDTH = 16 * 32;

    private static final int HEIGHT = 16 * 32;

    private static final int COLOR = 32;

    private static final String TITRE = "MyGame";

    private SDLSurface screen = null;

    public void init() throws SDLException {
        SDLMain.init(SDLMain.SDL_INIT_VIDEO);
        screen = SDLVideo.setVideoMode(WIDTH, HEIGHT, COLOR, SDLVideo.SDL_ENABLE | SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_DOUBLEBUF);
        SDLVideo.wmSetCaption(TITRE, null);
    }

    public Installation() throws SDLException, InterruptedException {

        init();

        // Gestion des evenements 1/2
        SDLEvent event = null;
        boolean running = true;

        // SDLSurface image = SDLImage.load("images/font.jpg");
        // SDLRect imagePos = new SDLRect(0, 0);
        //
        SDLSurface tank = SDLImage.load("images/carte/barriereDroite1.png");
        tank.setColorKey(SDLVideo.SDL_SRCCOLORKEY, tank.mapRGB(0, 0, 0));
        SDLRect tankPos = new SDLRect(100, 100);
        //
        // Gestion de la transparence
        tank.setAlpha(SDLVideo.SDL_SRCALPHA, 200);

        // Gestion de la carte
        Carte carte = new Carte(this.screen);

        /* Gestion des événement 2/2 Boucle Principale Le code est exécuté TANT QUE la variable booléene boucle est vrai SI l'événement SDL_QUIT est vrai ALORS la variable boucle est fausse et donc on sort de la boucle */
        while (running) {
            event = SDLEvent.waitEvent();

            if (event.getType() == SDLEvent.SDL_QUIT) {
                running = false;
            }
            if (event.getType() == SDLEvent.SDL_KEYDOWN) {
                // On génère un nouvel événement de type SDLKeyboardEvent - Attention au cast !
                SDLKeyboardEvent eventK = (SDLKeyboardEvent ) event;

                // Les méthodes getType() et getSym() se ressemblent sur le principe d'utilisation
                switch (eventK.getSym()) {
                // Si la touche espace est pressée
                    case SDLKey.SDLK_ESCAPE:
                        running = false;
                        break;
                }
            }
            carte.show();
            tank.blitSurface(screen, tankPos);
            screen.flip();
        }

        // On quitte :: Libération de la mémoire
        carte.delete();
        screen.freeSurface();
        tank.freeSurface();
        SDLMain.quit();
    }

    public static void main(String[] args) throws SDLException, InterruptedException {
        new Installation();
    }
}
