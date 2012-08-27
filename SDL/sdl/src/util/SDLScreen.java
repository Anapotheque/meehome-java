package util;

import sdljava.SDLException;
import sdljava.event.SDLEvent;
import sdljava.video.SDLSurface;

public abstract class SDLScreen {

    protected static SDLSurface screen;

    protected static SDLEvent event;

    public abstract void show() throws SDLException;

    public abstract void delete() throws SDLException;

    public abstract void eventManager() throws SDLException;

    public static SDLSurface getScreen() {
        return screen;
    }

    public static void setScreen(SDLSurface screen) {
        SDLScreen.screen = screen;
    }

    public static SDLEvent getEvent() {
        return event;
    }

    public static void setSDLEvent(SDLEvent event) {
        SDLScreen.event = event;
    }
}
