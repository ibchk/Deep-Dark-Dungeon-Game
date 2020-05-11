package ee.taltech.deepdarkdungeon.Models.characterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class Archer extends GameObject {

    public Archer(Texture picture, String name, int health, int power, int x, int y, double width, double height, CharacterClass characterClass, CharacterType characterType, int place) {
        this.name = name;
        this.health = health;
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
        this.place = place;
        this.description = "Archer worship a nature god by\n" +
                "protecting nature and slaying foul\n" +
                "creatures. Good ranger will often\n" +
                "act as the guardian of others by\n" +
                "repelling \"evil\" forces and protecting\n" +
                "the weak. Enemies experience fear\n" +
                "at the sight of archers, as their\n" +
                "deadly arrows kill immediately.";
    }
}
