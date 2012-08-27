package graphique.personnage.elements;

import sdljava.video.SDLRect;

public enum TypeCase {

    Barriere1(1, 1, 5), Barriere2(2, 2, 5), Barriere3(3, 3, 5), Barriere4(4, 4, 5), Barriere5(4, 5, 5), Sol1(5, 1, 1), Sol2(6, 12, 5);

    private int code;

    private SDLRect sdlRect;

    TypeCase(int code, int positionX, int positionY) {
        // Calcul des positions de chaques elements
        int x = (positionX - 1) * 32 + positionX;
        int y = (positionY - 1) * 32 + positionY;
        this.setSdlRect(new SDLRect(x, y, 32, 32));
        this.code = code;
    }

    public SDLRect getSdlRect() {
        return sdlRect;
    }

    public void setSdlRect(SDLRect sdlRect) {
        this.sdlRect = sdlRect;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
