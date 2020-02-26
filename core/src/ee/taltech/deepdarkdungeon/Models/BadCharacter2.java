package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BadCharacter2 extends GameObject {

    int health = 1000;
    int power = 123;
    double x;
    double y;
    double width;
    double height;
    private CharacterType characterType = CharacterType.BAD2;
    Texture picture = new Texture(Gdx.files.internal("BadCharacter2.png"));

    public BadCharacter2(double x, double y, double width, double height) {
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
