package ee.taltech.deepdarkdungeon.Models.characterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Archer extends GameObject {

    public Archer(Texture picture, String name, int health, int power, double x, double y, double width, double height, CharacterClass characterClass, CharacterType characterType) {
        this.name = "Archer";
        this.health = 100;
        this.mana = 100;
        this.power = 5;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.characterClass = characterClass;
        this.characterType = characterType;
        this.texture = picture;
        this.skills = "powershot";
    }
}
