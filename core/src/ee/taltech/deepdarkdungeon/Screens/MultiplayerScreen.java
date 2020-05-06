package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.PutMusic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;

import java.util.LinkedList;
import java.util.List;

public class MultiplayerScreen implements Screen {
    private static final int VBOI_X = 300;
    private static final int VBOI_Y = 300;
    private static final int VBOI_HEIGTH = 50;
    private static final int VBOI_WIDTH = 50;
    private SpriteBatch batch;
    BitmapFont font = new BitmapFont();
    private Texture background;
    private boolean write = true;
    private List<GameObject> myCharacters;
    private List<String> heroNames;
    private MPClient client;
    private int WHOWILLATTACK = 0;
    private static final int WIN_SCREEN_X = 650;
    private static final int WIN_SCREEN_Y = 350;
    private static final int WIN_SCREEN_WIDTH = 592;
    private static final int WIN_SCREEN_HEIGHT = 341;
    private static final int MAIN_MENU_X_START = 690;
    private static final int MAIN_MENU_Y_START = 580;
    private static final int MAIN_MENU_X_END = 900;
    private static final int MAIN_MENU_Y_END = 675;
    private static final int NEXT_X_START = 970;
    private static final int NEXT_Y_START = 580;
    private static final int NEXT_X_END = 1185;
    private static final int NEXT_Y_END = 675;
    private static final int LOST_SCREEN_X = 650;
    private static final int LOST_SCREEN_Y = 350;
    private static final int LOST_SCREEN_WIDTH = 592;
    private static final int LOST_SCREEN_HEIGHT = 341;
    private static final int MAIN_MENU2_X_START = 835;
    private static final int MAIN_MENU2_Y_START = 580;
    private static final int MAIN_MENU2_X_END = 1060;
    private static final int MAIN_MENU2_Y_END = 675;
    private static final int FRAME_COLS = 10;
    private static final int FRAME_ROWS = 5;
    private static final int HERO_FRAME_COLS = 5;
    private static final int HERO_FRAME_ROWS = 3;
    private static final int FRAME_COLS_POWERSHOT = 4;
    private static final int FRAME_ROWS_POWERSHOT = 4;
    private static final int FRAME_COLS_HERO_HEAL = 5;
    private static final int FRAME_ROWS_HERO_HEAL = 5;
    private static final int FRAME_COLS_AGR = 5;
    private static final int FRAME_ROWS_AGR = 5;
    private int myPlace; // TODO
    List<GameObject> enemyCharacters;
    DeepDarkDungeonGame game;
    boolean gameOver = false;
    boolean canbeattacked = false;
    boolean skillIsPressed = false;
    String message;
    String messageForMonsters;
    private long stepCount = 1;
    private Texture monstersWinScreen;
    private Texture mainMenuButton2;
    private Texture nextLevelButton;
    private Texture mainMenuButton;
    private Texture heroesWinScreen;
    private Texture attackbutton;
    private Texture attackbuttonacitve;
    private Texture defenceButton;
    private Texture aciveDefenceButton;
    private GameObject goodCharacter1;
    private GameObject goodCharacter2;
    private GameObject goodCharacter3;
    private GameObject goodCharacter4;
    private GameObject badCharacter1;
    private GameObject badCharacter2;
    private GameObject badCharacter3;
    private GameObject badCharacter4;
    private GameObject attacker;
    PutMusic music;
    int openLevelNumber;
    int lvlPlaying;
    boolean agr = false;
    boolean sunstrikeAnimationStarted = false;
    boolean heroAttackAnimationStarted = false;
    int flag = 0;

    private String monsterDamage = "";

    private GameObject attackedMonster;

    private GameObject attackedHero;

    private boolean addManaMonsters = false;

    private boolean powershotStarted = false;

    private boolean heroHealStarted = false;

    private boolean agrAnimationStarted = false;

    Animation heroAttack;
    Texture heroAttackSheet;
    TextureRegion[] heroAttackFrames;
    TextureRegion currentHeroAttackFrames;

    float heroAttackStateTime;

    Animation sunstrikeAnimation; // анимация
    Texture sunstrikeSheet; // текстура анимации
    TextureRegion[] sunstrikeFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentSunstrikeFrame; // текуший кадр анимации

    float stateTime; // количество секунд прошедших с начала анимации

    Animation powershotAnimation; // анимация
    Texture powershotSheet; // текстура анимации
    TextureRegion[] powershotFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentPowershotFrame;
    float stateTimePowershot;

    Animation heroHealAnimation; // анимация
    Texture heroHealSheet; // текстура анимации
    TextureRegion[] heroHealFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentHeroHealFrame;
    float stateTimeHeroHeal;

    Animation agrAnimation; // анимация
    Texture agrSheet; // текстура анимации
    TextureRegion[] agrFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentAgrFrame;
    float stateTimeAgr;

    public MultiplayerScreen(List<GameObject> myChars) {
        this.myCharacters = myChars;
        heroNames = new LinkedList<>();
        for (GameObject hero : myChars) {
            heroNames.add(hero.name);
        }
        this.client = new MPClient(heroNames);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("GameBackground.png"));
        attackbutton = new Texture(Gdx.files.internal("atackButton1.png"));
        attackbuttonacitve = new Texture(Gdx.files.internal("atackButton2.png"));
        defenceButton = new Texture(Gdx.files.internal("defenceButton1.png"));
        aciveDefenceButton = new Texture(Gdx.files.internal("defenceButton2.png"));
        powershotSheet = new Texture(Gdx.files.internal("powershot.png"));
        heroesWinScreen = new Texture(Gdx.files.internal("You_Win_Screen.png"));
        mainMenuButton = new Texture(Gdx.files.internal("MainMenuSelected.png"));
        nextLevelButton = new Texture(Gdx.files.internal("NextLevelSelected.png"));
        monstersWinScreen = new Texture(Gdx.files.internal("YouLostScreen.png"));
        mainMenuButton2 = new Texture(Gdx.files.internal("MainMenuSelected2.png"));
        sunstrikeSheet = new Texture(Gdx.files.internal("explosionAttack.png"));
        heroAttackSheet = new Texture(Gdx.files.internal("atacka.png"));
        heroHealSheet = new Texture(Gdx.files.internal("heroHealAnimation.png"));
        agrSheet = new Texture(Gdx.files.internal("agrAnimation.png"));

        TextureRegion[][] tmp4 = TextureRegion.split(heroAttackSheet, heroAttackSheet.getWidth() / HERO_FRAME_COLS, heroAttackSheet.getHeight() / HERO_FRAME_ROWS);
        heroAttackFrames = new TextureRegion[HERO_FRAME_ROWS * HERO_FRAME_COLS];
        int index4 = 0;
        for (int i = 0; i < HERO_FRAME_ROWS; i++) {
            for (int j = 0; j < HERO_FRAME_COLS; j++) {
                heroAttackFrames[index4++] = tmp4[i][j];
            }
        }
        heroAttack = new Animation(0.04f, heroAttackFrames);
        heroAttack.setPlayMode(Animation.PlayMode.NORMAL);
        heroAttackStateTime = 0f;
        // Дальше идет конструктор анимации: (это анимация санстрайка здесь менять ничего не нужно)
        TextureRegion[][] tmp = TextureRegion.split(sunstrikeSheet, sunstrikeSheet.getWidth() / FRAME_COLS, sunstrikeSheet.getHeight() / FRAME_ROWS);
        sunstrikeFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                sunstrikeFrames[index++] = tmp[i][j];
            }
        }
        sunstrikeAnimation = new Animation(0.02f, sunstrikeFrames);
        sunstrikeAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTime = 0f;

        // Powershot animation:

        TextureRegion[][] tmp5 = TextureRegion.split(powershotSheet, powershotSheet.getWidth() / FRAME_COLS_POWERSHOT, powershotSheet.getHeight() / FRAME_ROWS_POWERSHOT);
        powershotFrames = new TextureRegion[FRAME_COLS_POWERSHOT * FRAME_ROWS_POWERSHOT];
        int index5 = 0;
        for (int i = 0; i < FRAME_ROWS_POWERSHOT; i++) {
            for (int j = 0; j < FRAME_COLS_POWERSHOT; j++) {
                powershotFrames[index5++] = tmp5[i][j];
            }
        }
        powershotAnimation = new Animation(0.04f, powershotFrames);
        powershotAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTimePowershot = 0f;

        // Hero Heal animation:

        TextureRegion[][] tmp6 = TextureRegion.split(heroHealSheet, heroHealSheet.getWidth() / FRAME_COLS_HERO_HEAL, heroHealSheet.getHeight() / FRAME_ROWS_HERO_HEAL);
        heroHealFrames = new TextureRegion[FRAME_COLS_HERO_HEAL * FRAME_ROWS_HERO_HEAL];
        int index6 = 0;
        for (int i = 0; i < FRAME_ROWS_HERO_HEAL; i++) {
            for (int j = 0; j < FRAME_COLS_HERO_HEAL; j++) {
                heroHealFrames[index6++] = tmp6[i][j];
            }
        }
        heroHealAnimation = new Animation(0.04f, heroHealFrames);
        heroHealAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTimeHeroHeal = 0f;

        // Agr animation:

        TextureRegion[][] tmp7 = TextureRegion.split(agrSheet, agrSheet.getWidth() / FRAME_COLS_AGR, agrSheet.getHeight() / FRAME_ROWS_AGR);
        agrFrames = new TextureRegion[FRAME_COLS_AGR * FRAME_ROWS_AGR];
        int index7 = 0;
        for (int i = 0; i < FRAME_ROWS_AGR; i++) {
            for (int j = 0; j < FRAME_COLS_AGR; j++) {
                agrFrames[index7++] = tmp7[i][j];
            }
        }
        agrAnimation = new Animation(0.04f, agrFrames);
        agrAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTimeAgr = 0f;
    }

    @Override
    public void render(float delta) {
        if (client.game) {
            if (write) {
                List<String> enemyCharactersString = client.enemy;
                System.out.println(heroNames);
                System.out.println(enemyCharactersString); // TODO
                write = false;
                myPlace = client.myPlace;
                this.enemyCharacters = createEnemies(enemyCharactersString);
                badCharacter1 = enemyCharacters.get(0);
                badCharacter2 = enemyCharacters.get(1);
                badCharacter3 = enemyCharacters.get(2);
                badCharacter4 = enemyCharacters.get(3);
                goodCharacter1 = myCharacters.get(0);
                goodCharacter2 = myCharacters.get(1);
                goodCharacter3 = myCharacters.get(2);
                goodCharacter4 = myCharacters.get(3);

            }
            Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0);
            batch.draw(attackbutton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
            batch.draw(defenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
            if (myPlace == client.whoAttack) {
                font.draw(batch, "Your turn!", 300, 700);
            }
            for (GameObject hero : myCharacters) {
                batch.draw(hero.getTexture(), hero.getX(), hero.getY(), 200, 220);
                font.draw(batch, "Hp: " + hero.getHealth(), hero.getX() + 30, hero.getY() - 10);
                font.draw(batch, "Mn: " + hero.getMana(), hero.getX() + 100, hero.getY() - 10);
            }
            for (GameObject monster :enemyCharacters) {
                batch.draw(monster.getTexture(), monster.getX(), monster.getY(), 200, 220);
                font.draw(batch, "Hp: " + monster.getHealth(), monster.getX() + 30, monster.getY() - 10);
                font.draw(batch, "Mn: " + monster.getMana(), monster.getX() + 100, monster.getY() - 10);
            }

            if (badCharacter1.getHealth() == 0 && badCharacter2.getHealth() == 0 && badCharacter3.getHealth() == 0 && badCharacter4.getHealth() == 0) {
                if (lvlPlaying == openLevelNumber) {
                    openLevelNumber++;
                }
                gameOver = true;
                batch.draw(heroesWinScreen, WIN_SCREEN_X, WIN_SCREEN_Y, WIN_SCREEN_WIDTH, WIN_SCREEN_HEIGHT);
                if (Gdx.input.getX() > MAIN_MENU_X_START && Gdx.input.getX() < MAIN_MENU_X_END && Gdx.input.getY() > MAIN_MENU_Y_START && Gdx.input.getY() < MAIN_MENU_Y_END) {
                    batch.draw(mainMenuButton, 685, 380, 220, 95);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                        game.setScreen(new MainMenuScreen(game, openLevelNumber, music, false));
                    }
                }
                // Ильюша, сладкий, этот код для тебя ;*
                if (Gdx.input.getX() > NEXT_X_START && Gdx.input.getX() < NEXT_X_END && Gdx.input.getY() > NEXT_Y_START && Gdx.input.getY() < NEXT_Y_END) {
                    batch.draw(nextLevelButton, 970, 380, 220, 95);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                        boolean isMusicPlaying = music.isPlaying();
                        music.stopMusic();
                        music = new PutMusic("startMelody.mp3");
                        if (isMusicPlaying) {
                            music.playMusic();
                        } else {
                            music.stopMusic();
                        }
                        game.setScreen(new SingleGameChooseScreen(game, openLevelNumber, music, true));
                    }
                }
            }
            if (goodCharacter1.getHealth() == 0 && goodCharacter2.getHealth() == 0 && goodCharacter3.getHealth() == 0 && goodCharacter4.getHealth() == 0 ) {
                gameOver = true;
                batch.draw(monstersWinScreen, LOST_SCREEN_X, LOST_SCREEN_Y, LOST_SCREEN_WIDTH, LOST_SCREEN_HEIGHT);
                if (Gdx.input.getX() > MAIN_MENU2_X_START && Gdx.input.getX() < MAIN_MENU2_X_END && Gdx.input.getY() > MAIN_MENU2_Y_START && Gdx.input.getY() < MAIN_MENU2_Y_END) {
                    batch.draw(mainMenuButton2, 835, 385, 228, 95);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                        game.setScreen(new MainMenuScreen(game, openLevelNumber, music, false));
                    }
                }
            }
            if ((myPlace == client.whoAttack) && !gameOver && !sunstrikeAnimationStarted && !heroAttackAnimationStarted && !powershotStarted && !heroHealStarted && !agrAnimationStarted) {
                if (addManaMonsters) { //TODO
                    for (GameObject monster :enemyCharacters) {
                        if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER) && monster.getHealth() > 0 && monster.getMana() < 100) {
                            monster.setMana(monster.getMana() + 10);
                            if (monster.getMana() > 100) {
                                monster.setMana(100);
                            }
                        }
                    }
                    addManaMonsters = false;
                }

                if (WHOWILLATTACK >= 4) {
                    WHOWILLATTACK = 0;
                }
                while (myCharacters.get(WHOWILLATTACK).getHealth() == 0) {
                    WHOWILLATTACK++;
                    if (WHOWILLATTACK >= 4) {
                        WHOWILLATTACK = 0;
                    }
                }
                attacker = myCharacters.get(WHOWILLATTACK);
                font.draw(batch, "Health: ", 300, 200);
                font.draw(batch, attacker.getHealth() + "", 360, 200);
                font.draw(batch, "Mana: ", 300, 150);
                font.draw(batch, attacker.getMana() + "", 360, 150);
                font.draw(batch, "Your turn! " + stepCount, 100, 1000); // Вызывает текст, тут например power персанажа
                batch.draw(attacker.getTexture(), 40, 130, 200, 220);
                monsterDamage = "-" + attacker.getPower() + " HP";
                if (stepCount > 1) {
                    font.draw(batch, "In last step " + message, 100, 900);
                }
                if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y + VBOI_HEIGTH + 30 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y + 25) {
                    batch.draw(attackbuttonacitve, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        canbeattacked = true;
                        if (skillIsPressed) {
                            skillIsPressed = false;
                        }
                    }
                }
                if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y - 70 + VBOI_HEIGTH + 30 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y - 70 + 25) {
                    if (attacker.getSkill().equals("powershot") && attacker.getMana() >= 100 || attacker.getSkill().equals("sunstrike") && attacker.getMana() >= 50 || attacker.getSkill().equals("purification") && attacker.getMana() >= 40 || attacker.getSkill().equals("berserk call") && attacker.getMana() >= 40) {
                        batch.draw(aciveDefenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                            skillIsPressed = true;
                            if (canbeattacked) {
                                canbeattacked = false;
                            }
                        }
                    }
                }
                if (skillIsPressed && attacker.getSkill().equals("purification")) {
                    GameObject needed = myCharacters.get(0);
                    for (GameObject hero : myCharacters) {
                        if (needed.getHealth() > hero.getHealth()) {
                            needed = hero;
                        }
                    }
                    needed.setHealth(needed.getHealth() + 30);
                    attacker.setMana(attacker.getMana() - 40);
                    skillIsPressed = false;
                    messageForMonsters = "You healed " + needed.getName();
                    monsterDamage = "+30 HP";
                    attackedMonster = needed;
                    heroHealStarted = true;
                }
                if (skillIsPressed && attacker.getSkill().equals("berserk call")) {
                    skillIsPressed = false;
                    attacker.setMana(attacker.getMana() - 40);
                    attackedHero = attacker;
                    messageForMonsters = attacker.getName() + " used berserk call";
                    monsterDamage = "";
                    attackedMonster = attacker;
                    agr = true;
                    agrAnimationStarted = true;
                }
                for (GameObject monster : enemyCharacters) {
                    if (Gdx.input.getX() > monster.getX() && Gdx.input.getX() < monster.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > monster.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < monster.getY() + 300) {
                        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && monster.getHealth() > 0) {
                            defAttack(monster);
                        } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && monster.getHealth() > 0) {
                            if (attacker.getSkill().equals("powershot")) {
                                powerShot(monster);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 0) {
                                sunstrike2(badCharacter1, badCharacter2);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 3) {
                                sunstrike2(badCharacter3, badCharacter4);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 2) {
                                sunstrike(badCharacter2, badCharacter3, badCharacter4);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 1) {
                                sunstrike(badCharacter1, badCharacter2, badCharacter3);
                            }
                        }
                    }
                }
            }
        }
        batch.end();
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

    private void powerShot(GameObject gameObject) {
        messageForMonsters = "You powershoted and killed" + gameObject.getName();
        attackedMonster = gameObject; // ошибка!!!!
        monsterDamage = "-100 HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - 100, 0));
        attacker.setMana(attacker.getMana() - 100);
        skillIsPressed = false;
        powershotStarted = true;
    }

    private void defAttack(GameObject gameObject) {
        messageForMonsters = "You attacked " + gameObject.getName();
        attackedMonster = gameObject;
        monsterDamage = "-" + attacker.getPower() + " HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - attacker.getPower(), 0));
        canbeattacked = false;
        heroAttackAnimationStarted = true;
    }

    private void sunstrike(GameObject gameObject1, GameObject gameObject2, GameObject gameObject3) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        gameObject3.setHealth(Math.max(gameObject3.getHealth() - 30, 0));
        attackedMonster = gameObject2;
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        sunstrikeAnimationStarted = true;
        skillIsPressed = false;
    }

    private void sunstrike2(GameObject gameObject1, GameObject gameObject2) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        if (gameObject1 == badCharacter1) {
            attackedMonster = gameObject1;
        } else {
            attackedMonster = gameObject2;
        }
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        sunstrikeAnimationStarted = true;
        skillIsPressed = false;
    }

    public List<GameObject> createEnemies(List<String> enemyList) {
        List<GameObject> enemyCharacterList = new LinkedList<>();
        int place = 1;
        int x = 900; // +200
        int y; // 450 ili 400
        for (String name : enemyList) {
            x += 200;
            if (place % 2 == 1) {
                y = 450;
            } else {
                y = 400;
            }
            GameObject enemy = null;
            switch (name) {
                case "Warrior":
                    enemy = new Warrior(new Texture(Gdx.files.internal("GoodCharacter1.png")), "Warrior", 100, 100, x, y, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1, place);
                    break;
                case "Archer":
                    enemy = new Archer(new Texture(Gdx.files.internal("GoodCharacter2.png")), "Archer", 100, 100, x, y, 200, 277, GameObject.CharacterClass.ARCHER, GameObject.CharacterType.GOOD2, place);
                    break;
                case "Wizard":
                    enemy = new Magic(new Texture(Gdx.files.internal("GoodCharacter3.png")), "Wizard", 200, 100, x, y, 200, 277, GameObject.CharacterClass.MAGIC, GameObject.CharacterType.GOOD3, place);
                    break;
                case "Paladin":
                    enemy = new Paladin(new Texture(Gdx.files.internal("GoodCharacter4.png")), "Paladin", 100, 100, x, y, 200, 277, GameObject.CharacterClass.PALADIN, GameObject.CharacterType.GOOD4, place);
                    break;
            }
            enemyCharacterList.add(enemy);
            place++;
        }
        return enemyCharacterList;
    }
}
