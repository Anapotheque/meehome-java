package carte;

import java.util.HashMap;

import sdljava.SDLException;
import sdljava.video.SDLSurface;
import util.Position;
import carte.elements.Barriere;
import carte.elements.Sol;

public class Carte implements Affichage {

    private Sol[][] mapFont;

    private HashMap<Position, Object> mapObstacle;

    private static final int SIZE_MAX = 16;

    @SuppressWarnings("unchecked")
    public Carte(SDLSurface screen) throws SDLException {

        // On dessine toute la pelouse
        mapFont = new Sol[SIZE_MAX][SIZE_MAX];
        for (int x = 0; x < SIZE_MAX; x++) {
            for (int y = 0; y < SIZE_MAX; y++) {
                Position position = new Position(x, y);
                mapFont[x][y] = new Sol(screen, position);
            }
        }

        // On dessine les obstacle - decors
        mapObstacle = new HashMap<Position, Object>();

        Position position = new Position(0, 1);
        mapObstacle.put(position, new Barriere(screen, position));

        position = new Position(0, 1);
        mapObstacle.put(position, new Barriere(screen, position, true));

        position = new Position(1, 0);
        mapObstacle.put(position, new Barriere(screen, position));

        position = new Position(1, 1);
        mapObstacle.put(position, new Barriere(screen, position, true));
    }

    @Override
    public void delete() throws SDLException {
        for (int x = 0; x < SIZE_MAX; x++) {
            for (int y = 0; y < SIZE_MAX; y++) {
                mapFont[x][y].delete();
            }
        }

        for (Position position : mapObstacle.keySet()) {
            Object obj = mapObstacle.get(position);
            if (obj instanceof Barriere) {
                ((Barriere ) obj).delete();
            }
        }
    }

    @Override
    public void show() throws SDLException {
        for (int x = 0; x < SIZE_MAX; x++) {
            for (int y = 0; y < SIZE_MAX; y++) {
                mapFont[x][y].show();
            }
        }

        for (Position position : mapObstacle.keySet()) {
            Object obj = mapObstacle.get(position);
            if (obj instanceof Barriere) {
                ((Barriere ) obj).show();
            }
        }
    }
}
