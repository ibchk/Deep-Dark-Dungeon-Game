package ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Zombie extends GameObject {

    public Zombie (Texture picture, String name, int health, int power, double x, double y, double width, double height, BadCharacterClass badCharacterClass, CharacterType badCharacterType) {
        this.name = "Zombie";
        this.health = 100;
        this.power = 15;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = picture;
        this.badCharacterClass = badCharacterClass;
        this.characterType = badCharacterType;
    }
}
