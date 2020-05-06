package ee.taltech.deepdarkdungeon.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Necromancer;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonArcher;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonWarrior;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Zombie;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;

import java.awt.*;

public abstract class GameObject {
    public int mana;
    public String skills;
    public BadCharacterClass badCharacterClass;
    public Rectangle bounds;
    public Sprite object;
    public Texture texture;
    public String name;
    public CharacterType characterType;
    public CharacterClass characterClass;
    public int power;
    public int health;
    public int x;
    public int y;
    public double width;
    public double height;
    public int place;

    public BadCharacterClass getBadCharacterClass() {
        return badCharacterClass;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public String getSkill() {
        return skills;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int needed) {
        this.mana = needed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getPlace() {
        return place;
    }

    public enum CharacterClass {PALADIN, WARIOR, MAGIC, ARCHER}

    public enum BadCharacterClass {ZOMBIE, SKELETON_WARRIOR, SKELETON_ARCHER, NECROMANCER}

    public enum CharacterType {GOOD1, GOOD2, GOOD3, GOOD4, BAD1, BAD2, BAD3, BAD4}
}
