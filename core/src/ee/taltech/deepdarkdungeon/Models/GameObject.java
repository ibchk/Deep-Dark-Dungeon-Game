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

    public BadCharacterClass badCharacterClass;
    public Rectangle bounds;
    public Sprite object;
    public Texture texture;
    public String name;
    public CharacterType characterType;
    public CharacterClass characterClass;
    public int power;
    public int health;
    public double x;
    public double y;
    public double width;
    public double height;

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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


    public enum CharacterClass {PALADIN, WARIOR, MAGIC, ARCHER}
    public enum BadCharacterClass {ZOMBIE, SKELETON_WARRIOR, SKELETON_ARCHER, NECROMANCER}
    public enum CharacterType {GOOD1, GOOD2, GOOD3, GOOD4, BAD1, BAD2, BAD3, BAD4}

    public GameObject createCharacter(Texture texture, String name, CharacterType characterType, CharacterClass characterClass, int power, int health, double x, double y, double width, double height) {
        GameObject object = null;
        switch (characterType) {
            case GOOD1:
            case GOOD2:
            case GOOD3:
            case GOOD4:
                if (characterClass == CharacterClass.WARIOR) {
                    object = new Warrior(texture, name, health, power, x, y, width, height, characterClass, characterType);
                }
                if (characterClass == CharacterClass.PALADIN) {
                    object = new Paladin(texture, name, health, power, x, y, width, height, characterClass, characterType);
                }
                if (characterClass == CharacterClass.MAGIC) {
                    object = new Magic(texture, name, health, power, x, y, width, height, characterClass, characterType);
                }
                if (characterClass == CharacterClass.ARCHER) {
                    object = new Archer(texture, name, health, power, x, y, width, height, characterClass, characterType);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + characterType);
        }
        this.bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        this.object = new Sprite(getTexture());
        return object;
    }

    public GameObject createBadCharacter(Texture texture, String name, CharacterType badCharacterType, BadCharacterClass badCharacterClass, int power, int health, double x, double y, double width, double height) {
        GameObject object = null;
        switch (badCharacterType) {
            case BAD1:
            case BAD2:
            case BAD3:
            case BAD4:
                if (badCharacterClass == BadCharacterClass.ZOMBIE) {
                    object = new Zombie(texture, name, health, power, x, y, width, height, badCharacterClass, badCharacterType);
                }
                if (badCharacterClass == BadCharacterClass.SKELETON_WARRIOR) {
                    object = new SkeletonWarrior(texture, name, health, power, x, y, width, height, badCharacterClass, badCharacterType);
                }
                if (badCharacterClass == BadCharacterClass.SKELETON_ARCHER) {
                    object = new SkeletonArcher(texture, name, health, power, x, y, width, height, badCharacterClass, badCharacterType);
                }
                if (badCharacterClass == BadCharacterClass.NECROMANCER) {
                    object = new Necromancer(texture, name, health, power, x, y, width, height, badCharacterClass, badCharacterType);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + badCharacterType);
        }
        this.bounds = new Rectangle((int) x, (int) y, (int) width, (int) height);
        this.object = new Sprite(getTexture());
        return object;
    }

    public void draw(SpriteBatch batch) {
        object.setBounds((float) bounds.getX(), (float) bounds.getY(), (float) bounds.getWidth(), (float) bounds.getHeight());
        object.draw(batch);
    }
}
