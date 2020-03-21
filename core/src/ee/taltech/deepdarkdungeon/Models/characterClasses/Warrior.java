package ee.taltech.deepdarkdungeon.Models.characterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Warrior extends GameObject {

    public Warrior(Texture picture, String name, int health, int power, int x, int y, double width, double height, CharacterClass characterClass, CharacterType characterType) {
        this.texture = picture;
        this.name = name;
        this.health = health;
        this.power = power;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.characterClass = characterClass;
        this.characterType = characterType;
        this.skills = "berserk call";
        this.mana = 100;
    }
}
