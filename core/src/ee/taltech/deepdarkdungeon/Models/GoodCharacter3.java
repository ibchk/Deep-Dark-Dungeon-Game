package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GoodCharacter3 extends GameObject {

    int health = 1500;
    int power = 96;
    Texture picture = new Texture(Gdx.files.internal("GoodCharacter3.png"));

    public GoodCharacter3(Texture texture, double x, double y, double width, double height) {
        super(texture, x, y, width, height);
    }
}
