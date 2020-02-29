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

    public SkeletonArcher (Texture picture, String name, int health, int power, double x, double y, double width, double height, BadCharacterClass badCharacterClass, CharacterType badCharacterType) {
        this.name = "Skeleton Archer";
        this.health = 50;
        this.power = 20;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.picture = picture;
        this.badCharacterClass = badCharacterClass;
        this.characterType = badCharacterType;
    }

    @Override
    public Texture getPicture() {
        return picture;
    }

    @Override
    public CharacterType getCharacterType() {
        return characterType;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public String getName() {
        return name;
    }
}
