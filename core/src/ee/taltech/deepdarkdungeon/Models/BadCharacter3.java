package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BadCharacter3 extends GameObject {

    int health = 1000;
    int power = 123;
    Texture picture = new Texture(Gdx.files.internal("BadCharacter3.png"));

    public BadCharacter3(Texture texture, double x, double y, double width, double height) {
        super(texture, x, y, width, height);
    }
}
