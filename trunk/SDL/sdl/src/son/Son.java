package son;

import sdljava.SDLException;
import sdljava.audio.SDLAudio;
import sdljava.event.SDLEvent;
import sdljava.event.SDLKey;
import sdljava.event.SDLKeyboardEvent;
import sdljava.mixer.MixChunk;
import sdljava.mixer.MixMusic;
import sdljava.mixer.SDLMixer;
import util.SDLScreen;

public class Son extends SDLScreen {

    private MixChunk monSon;

    private MixMusic maMusique;

    public Son() throws SDLException {

        // Initialisation de SDLMixer
        SDLMixer.openAudio(44100, SDLAudio.AUDIO_S16SYS, 2, 1024);
        // On passe le nombre de canaux de mixage à 32
        int result = SDLMixer.allocateChannels(32);
        if (result != 32) {
            throw new SDLException("Impossible d'allouer 32 canaux de mixage");
        }

        // Un objet de type MixChunk est utilisé pour charger un son
        setMonSon(SDLMixer.loadWAV("son/COM002.WAV"));
        // Un objet de type MixMusix est utilisé pour charger une musique11
        setMaMusique(SDLMixer.loadMUS("son/test.wav"));
    }

    @Override
    public void eventManager() throws SDLException {
        // Si la touche Shift de gauche n'est pas pressée, pas d'accélération
        if (SDLScreen.getEvent() instanceof SDLKeyboardEvent) {
            SDLKeyboardEvent keyEvent = (SDLKeyboardEvent ) SDLScreen.getEvent();

            if (keyEvent.getType() == SDLEvent.SDL_KEYDOWN) {
                switch (keyEvent.getSym()) {

                // Gestion du son
                    case SDLKey.SDLK_KP1:
                        SDLMixer.playChannel(-1, this.monSon, 0);
                        break;

                    case SDLKey.SDLK_KP2:
                        SDLMixer.playMusic(this.maMusique, 0);
                        break;

                    case SDLKey.SDLK_KP3:
                        SDLMixer.pauseMusic();
                        break;

                    case SDLKey.SDLK_KP4:
                        SDLMixer.resumeMusic();
                        break;

                    case SDLKey.SDLK_KP5:
                        SDLMixer.haltMusic();
                        break;

                }
            }
        }
    }

    @Override
    public void delete() throws SDLException {
        SDLMixer.freeChunk(monSon);
        SDLMixer.freeMusic(maMusique);
        SDLMixer.close();
    }

    public MixChunk getMonSon() {
        return monSon;
    }

    public void setMonSon(MixChunk monSon) {
        this.monSon = monSon;
    }

    public MixMusic getMaMusique() {
        return maMusique;
    }

    public void setMaMusique(MixMusic maMusique) {
        this.maMusique = maMusique;
    }

    @Override
    public void show() throws SDLException {
    }
}
