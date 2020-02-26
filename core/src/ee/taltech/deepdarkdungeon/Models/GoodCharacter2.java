package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GoodCharacter2 extends GameObject {

    int health = 600;
    int power = 366;
    Texture picture = new Texture(Gdx.files.internal("GoodCharacter2.png"));

    public GoodCharacter2(Texture texture, double x, double y, double width, double height) {
        super(texture, x, y, width, height);
    }
}
