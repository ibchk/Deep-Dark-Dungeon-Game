package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GoodCharacter3 extends GameObject {

    int health = 1500;
    int power = 96;
    double x;
    double y;
    double width;
    double height;
    private CharacterType characterType = CharacterType.GOOD3;
    Texture picture = new Texture(Gdx.files.internal("GoodCharacter3.png"));

    public GoodCharacter3(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Texture getPicture() {
        return picture;
    }

    @Override
    public CharacterType getCharacterType() {
        return characterType;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getHealth() {
        return health;
    }
}
