package ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Necromancer extends GameObject {

    public Necromancer (Texture picture, String name, int health, int power, int x, int y, double width, double height, BadCharacterClass badCharacterClass, CharacterType badCharacterType, int mana) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = picture;
        this.badCharacterClass = badCharacterClass;
        this.characterType = badCharacterType;
        this.mana = mana;
    }
}
