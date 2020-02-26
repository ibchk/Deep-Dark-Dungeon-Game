package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GoodCharacter4 extends GameObject {

    int health = 2100;
    int power = 23;
    Texture picture = new Texture(Gdx.files.internal("GoodCharacter4.png"));

    public GoodCharacter4(Texture texture, double x, double y, double width, double height) {
        super(texture, x, y, width, height);
    }

}
