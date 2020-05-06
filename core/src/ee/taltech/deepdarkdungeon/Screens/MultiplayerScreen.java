package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;

import java.util.LinkedList;
import java.util.List;

public class MultiplayerScreen implements Screen {

    private SpriteBatch batch;
    BitmapFont font = new BitmapFont();
    private Texture background;
    private boolean write = true;
    private List<GameObject> myCharacters;
    private List<GameObject> enemyCharacters;
    private MPClient client;

    public MultiplayerScreen(List<GameObject> myChars) {
        this.myCharacters = myChars;
        List<String> heroNames = new LinkedList<>();
        for (GameObject hero : myChars) {
            heroNames.add(hero.name);
        }
        this.client = new MPClient(heroNames);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("backScreenChooseScreen.png"));
    }

    @Override
    public void render(float delta) {
        if (client.game) {
            if (write) {
                List<String> enemyCharactersString = client.enemy;
                System.out.println(enemyCharactersString);
                System.out.println(myCharacters);
                write = false;
                this.enemyCharacters = createEnemies(enemyCharactersString);
            }
            Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0);
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public List<GameObject> createEnemies(List<String> enemyList) {
        List<GameObject> enemyCharacterList = new LinkedList<>();
        int place = 1;
        for (String name : enemyList) {
            GameObject enemy = null;
            switch (name) {
                case "Warrior":
                    enemy = new Warrior(new Texture(Gdx.files.internal("GoodCharacter1.png")), "Warrior", 100, 100, 0, 400, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1, place);
                    enemyCharacterList.add(enemy);
                    break;
                case "Archer":
                    enemy = new Archer(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Archer", 100, 100, 200, 450, 200, 277, GameObject.CharacterClass.ARCHER, GameObject.CharacterType.GOOD2, place);
                    break;
                case "Wizard":
                    enemy = new Magic(new Texture(Gdx.files.internal("GoodCharacter3.png")), "Wizard", 200, 100, 400, 400, 200, 277, GameObject.CharacterClass.MAGIC, GameObject.CharacterType.GOOD3, place);
                    break;
                case "Paladin":
                    enemy = new Paladin(new Texture(Gdx.files.internal("GoodCharacter4.png")), "Paladin", 100, 100, 600, 450, 200, 277, GameObject.CharacterClass.PALADIN, GameObject.CharacterType.GOOD4, place);
                    break;
            }
            enemyCharacterList.add(enemy);
            place++;
        }
        return enemyCharacterList;
    }
}
