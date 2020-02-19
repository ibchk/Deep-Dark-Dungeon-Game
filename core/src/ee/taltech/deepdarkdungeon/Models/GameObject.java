package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public abstract class GameObject {

    Rectangle bounds;
    Sprite object;
    public GameObject(Texture texture, double x, double y, double width, double height) {
        bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        object = new Sprite(texture);
    }

    public void draw(SpriteBatch batch) {
        object.setBounds((float) bounds.getX(), (float) bounds.getY(), (float) bounds.getWidth(), (float) bounds.getHeight());
        object.draw(batch);
    }
}
