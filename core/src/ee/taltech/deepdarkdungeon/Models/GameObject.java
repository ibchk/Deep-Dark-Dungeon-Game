package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public abstract class GameObject {

    Rectangle bounds;
    Sprite object;

    public abstract Texture getPicture();

    public abstract CharacterType getCharacterType();

    public abstract double getX();

    public abstract double getY();

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract int getPower();

    public abstract int getHealth();

    public abstract String getName();


    public enum CharacterType {GOOD1, GOOD2, GOOD3, GOOD4, BAD1, BAD2, BAD3, BAD4}

    public GameObject createCharacter(Texture texture, String name, CharacterType characterType, int power, int health, double x, double y, double width, double height) {
        GameObject object;
        switch (characterType) {
            case BAD1:
            case BAD2:
            case BAD3:
            case BAD4:
                object = new BadCharacter(texture, name, characterType, power, health, x, y, width, height);
                break;
            case GOOD1:
            case GOOD2:
            case GOOD3:
            case GOOD4:
                object = new GoodCharacter(texture, name, characterType, power, health, x, y, width, height);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + characterType);
        }
        this.bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        this.object = new Sprite(getPicture());
        return object;
    }

    public void draw(SpriteBatch batch) {
        object.setBounds((float) bounds.getX(), (float) bounds.getY(), (float) bounds.getWidth(), (float) bounds.getHeight());
        object.draw(batch);
    }


}
