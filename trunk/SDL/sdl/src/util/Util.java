package util;

import sdljava.SDLException;
import sdljava.video.SDLRect;
import sdljava.video.SDLSurface;

public class Util {
    public static void affichage(int x, int y, SDLSurface source) throws SDLException {
        SDLRect position = new SDLRect(x, y);
        source.blitSurface(SDLScreen.screen, position);
    }

    public static void affichage(SDLRect sprite, int x, int y, SDLSurface source) throws SDLException {
        SDLRect position = new SDLRect(x, y);
        source.blitSurface(sprite, SDLScreen.screen, position);
    }

    public static void affichage(SDLRect position, SDLSurface source) throws SDLException {
        source.blitSurface(SDLScreen.screen, position);
    }

    public static void affichage(SDLRect sprite, SDLRect position, SDLSurface source) throws SDLException {
        source.blitSurface(sprite, SDLScreen.screen, position);
    }
}
