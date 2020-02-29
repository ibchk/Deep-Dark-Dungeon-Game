package ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class SkeletonArcher extends GameObject {

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

    public SkeletonArcher (int health, int power, double x, double y, double width, double height, BadCharacterClass badCharacterClass, CharacterType characterType) {
        this.name = "Skeleton Archer";
        this.health = 50;
        this.power = 20;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.badCharacterClass = badCharacterClass;
        this.characterType = characterType;
    }

    @Override
    public Texture getPicture() {
        return null;
    }

    @Override
    public CharacterType getCharacterType() {
        return null;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public int getPower() {
        return 0;
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
