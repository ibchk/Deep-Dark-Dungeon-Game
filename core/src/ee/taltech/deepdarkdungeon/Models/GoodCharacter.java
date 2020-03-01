package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GoodCharacter extends GameObject {

    String name;
    int health;
    int power;
    double x;
    double y;
    double width;
    double height;
    CharacterType characterType;
    Texture picture;


    public GoodCharacter(Texture texture, String name, CharacterType characterType, int power, int health, double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.characterType = characterType;
        this.power = power;
        this.health = health;
        this.name = name;
        picture = texture;
    }
}
