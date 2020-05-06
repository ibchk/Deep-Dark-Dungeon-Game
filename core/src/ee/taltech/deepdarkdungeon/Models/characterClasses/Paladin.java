package ee.taltech.deepdarkdungeon.Models.characterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Paladin extends GameObject {

    public Paladin(Texture picture, String name, int health, int power, int x, int y, double width, double height, CharacterClass characterClass, CharacterType characterType, int place) {
        this.name = name;
        this.texture = picture;
        this.health = health;
        this.power = power;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.characterClass = characterClass;
        this.characterType = characterType;
        this.skills = "purification";
        this.mana = 100;
        this.place = place;
    }
}
