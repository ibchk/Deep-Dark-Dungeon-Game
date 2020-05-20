import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Necromancer;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonArcher;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonWarrior;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Zombie;
import ee.taltech.deepdarkdungeon.Models.BadCharacterCreating;
import ee.taltech.deepdarkdungeon.Models.CharacterCreating;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.PutMusic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class charactersTest {
    Texture pic;
    List<GameObject> characters = new ArrayList<>();
    List<GameObject> rightBadCharacters = new ArrayList<>();
    PutMusic putMusic;

    public void show() {
        pic = new Texture(Gdx.files.internal(""));
        putMusic = new PutMusic("startMelody.mp3");
    }

    @Test
    void charactersCreation() {
        GameObject warrior = new Warrior(pic, "Warrior", 200, 15, 0, 400, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1, 1);
        warrior.setMana(211);
        assertEquals(211, warrior.getMana());
        warrior.setHealth(23);
        assertEquals(23, warrior.getHealth());
        assertEquals(1, warrior.getPlace());
        warrior.setPower(122);
        assertEquals(122, warrior.getPower());
        assertEquals("berserk call", warrior.getSkill());
        warrior.setTexture(pic);
        assertEquals(pic, warrior.getTexture());
        warrior.setCharacterType(GameObject.CharacterType.GOOD3);
        assertEquals(GameObject.CharacterType.GOOD3, warrior.getCharacterType());
        warrior.setName("WAR");
        assertEquals("WAR", warrior.getName());
        warrior.setCharacterClass(GameObject.CharacterClass.MAGIC);
        assertEquals(GameObject.CharacterClass.MAGIC, warrior.getCharacterClass());
        warrior.setX(900);
        assertEquals(900, warrior.getX());
        warrior.setY(344);
        assertEquals(344, warrior.getY());
        warrior.setWidth(300);
        warrior.setHeight(500);
        assertEquals(300, warrior.getWidth());
        assertEquals(500, warrior.getHeight());
    }

    @Test
    void testGoodCharacterCreating() {
        GameObject goodCharacter1 = new Warrior(pic, "Warrior", 200, 15, 0, 400, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1, 1);
        GameObject goodCharacter2 = new Archer(pic, "Archer", 120, 20, 200, 450, 200, 277, GameObject.CharacterClass.ARCHER, GameObject.CharacterType.GOOD2, 2);
        GameObject goodCharacter3 = new Magic(pic, "Wizard", 100, 30, 400, 400, 200, 277, GameObject.CharacterClass.MAGIC, GameObject.CharacterType.GOOD3, 3);
        GameObject goodCharacter4 = new Paladin(pic, "Paladin", 75, 25, 600, 450, 200, 277, GameObject.CharacterClass.PALADIN, GameObject.CharacterType.GOOD4, 4);
        characters.add(goodCharacter1);
        characters.add(goodCharacter2);
        characters.add(goodCharacter3);
        characters.add(goodCharacter4);
        GameObject newChar = new CharacterCreating().createCharacter(characters, 3, goodCharacter4, 1);
        assertEquals("Paladin", newChar.getName());
    }

    @Test
    void testBadCharacterCreating() {
        GameObject badCharacter1 = new Necromancer(pic, "Necromancer", 200, 20, 1100, 450, 200, 277, GameObject.BadCharacterClass.NECROMANCER, GameObject.CharacterType.BAD1, 100);
        GameObject badCharacter2 = new Zombie(pic, "Zombie", 150, 15, 1300, 400, 200, 277, GameObject.BadCharacterClass.ZOMBIE, GameObject.CharacterType.BAD2);
        GameObject badCharacter3 = new SkeletonWarrior(pic, "Skeleton Warrior", 75, 15, 1500, 450, 200, 277, GameObject.BadCharacterClass.SKELETON_WARRIOR, GameObject.CharacterType.BAD3);
        GameObject badCharacter4 = new SkeletonArcher(pic, "Skeleton Archer", 50, 25, 1700, 400, 200, 277, GameObject.BadCharacterClass.SKELETON_ARCHER, GameObject.CharacterType.BAD4);
        rightBadCharacters.add(badCharacter1);
        rightBadCharacters.add(badCharacter2);
        rightBadCharacters.add(badCharacter3);
        rightBadCharacters.add(badCharacter4);
        GameObject newBadChar = new BadCharacterCreating().createCharacter(rightBadCharacters, 1, badCharacter1);
        assertEquals("Zombie", newBadChar.getName());
    }

    @Test
    void musicTest() {
        MPClient client = new MPClient(new LinkedList<>(Arrays.asList("a", "b", "c", "d")));
        MPClient client2 = new MPClient(new LinkedList<>(Arrays.asList("a", "b", "c", "d")));
    }
}
