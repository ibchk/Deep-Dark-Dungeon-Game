package ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Necromancer extends GameObject {

    String name;
    int health;
    int power;
    double x;
    double y;
    double width;
    double height;
    BadCharacterClass badCharacterClass;
    CharacterType characterType;
    Texture picture;

    public Necromancer (Texture picture, String name, int health, int power, double x, double y, double width, double height, BadCharacterClass badCharacterClass, CharacterType badCharacterType) {
        this.name = "Necromancer";
        this.health = 150;
        this.power = 40;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.picture = picture;
        this.badCharacterClass = badCharacterClass;
        this.characterType = badCharacterType;
    }
}
