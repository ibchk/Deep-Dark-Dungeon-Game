package ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses;

import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Models.GameObject;

public class SkeletonArcher extends GameObject {
    public SkeletonArcher (Texture picture, String name, int health, int power, double x, double y, double width, double height, BadCharacterClass badCharacterClass, CharacterType badCharacterType) {
        this.name = "Skeleton Archer";
        this.health = 50;
        this.power = 20;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = picture;
        this.badCharacterClass = badCharacterClass;
        this.characterType = badCharacterType;
    }
}
