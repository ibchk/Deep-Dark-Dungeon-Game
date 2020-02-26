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


    public enum CharacterType {GOOD1, GOOD2, GOOD3, GOOD4, BAD1, BAD2, BAD3, BAD4}

    public GameObject createCharacter(CharacterType characterType, double x, double y, double width, double height) {
        GameObject object;
        switch (characterType) {
            case BAD1:
                object = new BadCharacter1(x, y, width, height);
                break;
            case BAD2:
                object = new BadCharacter2(x, y, width, height);
                break;
            case BAD3:
                object = new BadCharacter3(x, y, width, height);
                break;
            case BAD4:
                object = new BadCharacter4(x, y, width, height);
                break;
            case GOOD1:
                object = new GoodCharacter1(x, y, width, height);
                break;
            case GOOD2:
                object = new GoodCharacter2(x, y, width, height);
                break;
            case GOOD3:
                object = new GoodCharacter3(x, y, width, height);
                break;
            case GOOD4:
                object = new GoodCharacter4(x, y, width, height);
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
