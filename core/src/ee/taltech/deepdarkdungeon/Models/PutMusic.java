package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class PutMusic {
    private Music music;
    boolean playing = true;

    public PutMusic(String music) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal(music));
        this.music.setLooping(true);
        this.music.setVolume(0.2f);
        this.music.play();
    }

    public void changePlayOrStop() {
        if (playing) {
            stopMusic();
        } else {
            playMusic();
        }
    }

    public void stopMusic() {
        music.pause();
        playing = false;
    }

    public void playMusic() {
        playing = true;
        music.play();
    }

    public void setMusic(String music) {
        this.music.stop();
        this.music = Gdx.audio.newMusic(Gdx.files.internal(music));
        if (playing) {
            this.music.play();
        }
    }
}
