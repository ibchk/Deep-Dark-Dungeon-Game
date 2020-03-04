package ee.taltech.deepdarkdungeon.Models;

import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;

import java.util.List;

public class CharacterCreating {
    public GameObject createCharacter(List<GameObject> characters, int neededCharacter, GameObject goodCharacter) {
        GameObject character;
        switch (characters.get(neededCharacter % 4).getCharacterClass()) {
            case WARIOR:
                character = new Warrior(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;
            case MAGIC:
                character = new Magic(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;

            case ARCHER:
                character = new Archer(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;

            case PALADIN:
                character = new Paladin(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;
            default:
                character = new Warrior(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
        }
        return character;
    }
}
