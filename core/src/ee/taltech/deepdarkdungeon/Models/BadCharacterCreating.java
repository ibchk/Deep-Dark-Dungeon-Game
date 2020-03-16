package ee.taltech.deepdarkdungeon.Models;

import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Necromancer;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonArcher;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.SkeletonWarrior;
import ee.taltech.deepdarkdungeon.Models.BadCgaracterClasses.Zombie;

import java.util.List;

public class BadCharacterCreating {
    public GameObject createCharacter(List<GameObject> characters, int neededCharacter, GameObject goodCharacter) {
        GameObject character;
        switch (characters.get(neededCharacter % 4).getBadCharacterClass()) {
            case NECROMANCER:
                character = new Necromancer(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getBadCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;
            case SKELETON_ARCHER:
                character = new SkeletonArcher(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getBadCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;

            case SKELETON_WARRIOR:
                character = new SkeletonWarrior(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getBadCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;

            case ZOMBIE:
                character = new Zombie(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getBadCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
                break;
            default:
                character = new Necromancer(characters.get(neededCharacter % 4).getTexture(), characters.get(neededCharacter % 4).getName(), characters.get(neededCharacter % 4).health, characters.get(neededCharacter % 4).getPower(), goodCharacter.getX(), goodCharacter.getY(), characters.get(neededCharacter % 4).getWidth(), characters.get(neededCharacter % 4).getHeight(), characters.get(neededCharacter % 4).getBadCharacterClass(), characters.get(neededCharacter % 4).getCharacterType());
        }
        return character;
    }
}
