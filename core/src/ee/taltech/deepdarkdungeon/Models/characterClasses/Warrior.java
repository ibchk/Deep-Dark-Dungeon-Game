package ee.taltech.deepdarkdungeon.Models.characterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Warrior extends GameObject {

    public Warrior(Texture picture, String name, int health, int power, double x, double y, double width, double height, CharacterClass characterClass, CharacterType characterType) {
        this.texture = picture;
        this.name = "Warrior";
        this.health = 100;
        this.power = 20;
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
