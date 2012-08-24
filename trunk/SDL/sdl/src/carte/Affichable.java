package carte;

import sdljava.SDLException;

public interface Affichable {

    public void show() throws SDLException;

    public void delete() throws SDLException;
}
