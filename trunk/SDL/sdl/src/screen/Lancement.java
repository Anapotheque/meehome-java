package screen;

import java.util.ArrayList;
import java.util.List;

import sdljava.SDLException;
import sdljava.SDLMain;
import sdljava.event.SDLEvent;
import sdljava.video.SDLVideo;
import son.Son;
import util.SDLScreen;
import font.Font;
import graphique.carte.Carte;
import graphique.personnage.Personnage;

public class Lancement {

    private static final int WIDTH = 16 * 32;

    private static final int HEIGHT = 16 * 32;

    private static final int COLOR = 32;

    private static final String TITRE = "MyGame";

    public void init() throws SDLException {
        SDLMain.init(SDLMain.SDL_INIT_VIDEO);
        SDLScreen.setScreen(SDLVideo.setVideoMode(WIDTH, HEIGHT, COLOR, SDLVideo.SDL_ENABLE | SDLVideo.SDL_HWSURFACE | SDLVideo.SDL_DOUBLEBUF | SDLMain.SDL_INIT_AUDIO));
        SDLVideo.wmSetCaption(TITRE, null);
        SDLEvent.enableKeyRepeat(20, 20);
    }

    public void menuJeux() throws SDLException, InterruptedException {
        init();
        boolean isRunning = true;
        Font font = new Font();

        List<SDLScreen> list = new ArrayList<SDLScreen>();
        list.add(new Font());
        list.add(new Son());

        execution(list);
    }

    public Lancement() throws SDLException, InterruptedException {
        menuJeux();
        jeux();
    }

    public void jeux() throws SDLException, InterruptedException {
        init();

        List<SDLScreen> list = new ArrayList<SDLScreen>();
        list.add(new Carte());
        list.add(new Font());
        list.add(new Personnage());
        list.add(new Son());

        execution(list);
    }

    private void execution(List<SDLScreen> list) throws SDLException, InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            SDLScreen.setSDLEvent(SDLEvent.pollEvent());

            if (SDLScreen.getEvent() instanceof SDLEvent) {
                switch (SDLScreen.getEvent().getType()) {
                    case SDLEvent.SDL_QUIT:
                        isRunning = false;
                        break;
                }
            }

            for (SDLScreen elt : list) {
                elt.eventManager();
                elt.show();
            }

            SDLScreen.getScreen().flip();
            Thread.sleep(10);
        }

        // On quitte :: Libération de la mémoire
        for (SDLScreen elt : list) {
            elt.delete();
        }

        SDLScreen.getScreen().freeSurface();
        SDLMain.quit();
    }

    public static void main(String[] args) throws SDLException, InterruptedException {
        Lancement l = new Lancement();
    }
}
