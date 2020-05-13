package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.PutMusic;
import ee.taltech.deepdarkdungeon.animation.AnimationClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen {
    private static int WHOWILLATTACK = 0;
    private static final int VBOI_X = 300;
    private static final int VBOI_Y = 300;
    private static final int VBOI_HEIGTH = 50;
    private static final int VBOI_WIDTH = 50;
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
    private static final int FRAME_COLS_MONSTERS_ATTACK = 5;
    private static final int FRAME_ROWS_MONSTERS_ATTACK = 3;
    private static final int FRAME_COLS_MONSTERS_HEAL = 5;
    private static final int FRAME_ROWS_MONSTERS_HEAL = 4;
    private static final int FRAME_COLS_POWERSHOT = 4;
    private static final int FRAME_ROWS_POWERSHOT = 4;
    private static final int FRAME_COLS_HERO_HEAL = 5;
    private static final int FRAME_ROWS_HERO_HEAL = 5;
    private static final int FRAME_COLS_AGR = 5;
    private static final int FRAME_ROWS_AGR = 5;


    List<GameObject> heroes;
    List<GameObject> monsters;
    DeepDarkDungeonGame game;
    boolean gameOver = false;
    boolean canbeattacked = false;
    boolean skillIsPressed = false;
    String message;
    String messageForMonsters;
    BitmapFont font = new BitmapFont();
    private long stepCount = 1;
    private Texture monstersWinScreen;
    private Texture mainMenuButton2;
    private Texture nextLevelButton;
    private Texture mainMenuButton;
    private Texture heroesWinScreen;
    private Texture attackbutton;
    private Texture attackbuttonacitve;
    private Texture healButton;
    private Texture healButtonActive;
    private Texture powershotButton;
    private Texture powershotButtonActive;
    private Texture sunstrikeButton;
    private Texture sunstrikeButtonActive;
    private Texture background;
    private Texture defenceButton;
    private Texture aciveDefenceButton;
    private SpriteBatch batch;
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
    private GameObject attackedHero;
    int flag = 0;
    int checkRandom = 0;

    private String monsterDamage = "";

    private GameObject attackedMonster;
    List<GameObject> monsterAttackedLast = new LinkedList<>();
    Random random = new Random();


    private boolean addManaMonsters = false;

    Texture heroAttackSheet;
    AnimationClass heroAttackAnimation;

    Texture sunstrikeSheet;
    AnimationClass sunstrikeAnimation;

    Texture monsterAttackSheet;
    AnimationClass monsterAttackAnimation;

    Texture monsterHealSheet;
    AnimationClass monsterHealAnimation;

    Texture powershotSheet;
    AnimationClass powershotAnimation;

    Texture heroHealSheet;
    AnimationClass heroHealAnimation;

    Texture agrSheet;
    AnimationClass agrAnimation;

    AnimationClass currentAnimation;
    boolean animationStarted = false;

    public GameScreen(List<GameObject> goodCharacters, List<GameObject> badCharacters, DeepDarkDungeonGame game, PutMusic music, int openLevelNumber, int lvlPlaying) {
        this.lvlPlaying = lvlPlaying;
        this.openLevelNumber = openLevelNumber;
        this.music = music;
        this.game = game;
        heroes = goodCharacters;
        monsters = badCharacters;
        boolean playing = music.isPlaying();
        goodCharacter1 = goodCharacters.get(0);
        goodCharacter2 = goodCharacters.get(1);
        goodCharacter3 = goodCharacters.get(2);
        goodCharacter4 = goodCharacters.get(3);
        badCharacter1 = badCharacters.get(0);
        badCharacter2 = badCharacters.get(1);
        badCharacter3 = badCharacters.get(2);
        badCharacter4 = badCharacters.get(3);
        this.music.setMusic("gameMelody.mp3");
        if (playing) {
            this.music.playMusic();
        } else {
            this.music.stopMusic();
        }
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
        monsterAttackSheet = new Texture(Gdx.files.internal("newMonsterAttackAnimation.png"));
        monsterHealSheet = new Texture(Gdx.files.internal("MonsterHealAnimation.png"));
        heroAttackSheet = new Texture(Gdx.files.internal("atacka.png"));
        heroHealSheet = new Texture(Gdx.files.internal("heroHealAnimation.png"));
        agrSheet = new Texture(Gdx.files.internal("agrAnimation.png"));
        healButton = new Texture(Gdx.files.internal("healButton2.png"));
        healButtonActive = new Texture(Gdx.files.internal("healButton1.png"));
        powershotButton = new Texture(Gdx.files.internal("powershotButton2.png"));
        powershotButtonActive = new Texture(Gdx.files.internal("powershotButton1.png"));
        sunstrikeButton = new Texture(Gdx.files.internal("sunstrikeButton2.png"));
        sunstrikeButtonActive = new Texture(Gdx.files.internal("sunstrikeButton1.png"));

        heroAttackAnimation =  new AnimationClass(heroAttackSheet, HERO_FRAME_ROWS, HERO_FRAME_COLS);

        sunstrikeAnimation = new AnimationClass(sunstrikeSheet, FRAME_ROWS, FRAME_COLS);

        monsterAttackAnimation = new AnimationClass(monsterAttackSheet, FRAME_ROWS_MONSTERS_ATTACK, FRAME_COLS_MONSTERS_ATTACK);

        monsterHealAnimation = new AnimationClass(monsterHealSheet, FRAME_ROWS_MONSTERS_HEAL, FRAME_COLS_MONSTERS_HEAL);

        powershotAnimation = new AnimationClass(powershotSheet, FRAME_ROWS_POWERSHOT, FRAME_COLS_POWERSHOT);

        heroHealAnimation = new AnimationClass(heroHealSheet, FRAME_ROWS_HERO_HEAL, FRAME_COLS_HERO_HEAL);

        agrAnimation = new AnimationClass(agrSheet, FRAME_ROWS_AGR, FRAME_COLS_AGR);
    }

    @Override
    public void render(float delta) {
        if (WHOWILLATTACK >= 4) {
            WHOWILLATTACK = 0;
        }
        while (heroes.get(WHOWILLATTACK).getHealth() == 0) {
            WHOWILLATTACK++;
            if (WHOWILLATTACK >= 4) {
                WHOWILLATTACK = 0;
            }
        }
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(attackbutton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
        GameObject myHero = heroes.get(WHOWILLATTACK);
        switch (myHero.getName()) {
            case "Warrior":
                batch.draw(defenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                break;
            case "Archer":
                batch.draw(powershotButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                break;
            case "Wizard":
                batch.draw(sunstrikeButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                break;
            case "Paladin":
                batch.draw(healButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                break;
        }
        for (GameObject hero : heroes) {
            batch.draw(hero.getTexture(), hero.getX(), hero.getY(), 200, 220);
            font.draw(batch, "Hp: " + hero.getHealth(), hero.getX() + 30, hero.getY() - 10);
            font.draw(batch, "Mn: " + hero.getMana(), hero.getX() + 100, hero.getY() - 10);
        }
        for (GameObject monster: monsters) {
            batch.draw(monster.getTexture(), monster.getX(), monster.getY(), 200, 220);
            font.draw(batch, "Hp: " + monster.getHealth(), monster.getX() + 30, monster.getY() - 10);
            if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER)) {
                font.draw(batch, "Mn: " + monster.getMana(), monster.getX() + 100, monster.getY() - 10);
            }
        }
        if (stepCount % 2 != 0 && !gameOver && !animationStarted) {
            if (addManaMonsters) {
                for (GameObject monster : monsters) {
                    if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER) && monster.getHealth() > 0 && monster.getMana() < 100) {
                        monster.setMana(monster.getMana() + 10);
                        if (monster.getMana() > 100) {
                            monster.setMana(100);
                        }
                    }
                }
                addManaMonsters = false;
            }
            attacker = heroes.get(WHOWILLATTACK);
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
                if (attacker.getSkill().equals("powershot") && attacker.getMana() >= 100) {
                    batch.draw(powershotButtonActive, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                } else if (attacker.getSkill().equals("sunstrike") && attacker.getMana() >= 50) {
                    batch.draw(sunstrikeButtonActive, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                } else if (attacker.getSkill().equals("purification") && attacker.getMana() >= 40) {
                    batch.draw(healButtonActive, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                } else if (attacker.getSkill().equals("berserk call") && attacker.getMana() >= 40) {
                    batch.draw(aciveDefenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                }
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    skillIsPressed = true;
                    if (canbeattacked) {
                        canbeattacked = false;
                    }
                }
            }
            if (skillIsPressed && attacker.getSkill().equals("purification")) {
                GameObject needed = heroes.get(0);
                for (GameObject hero : heroes) {
                    if (needed.getHealth() > hero.getHealth()) {
                        needed = hero;
                    }
                }
                needed.setHealth(needed.getHealth() + 30);
                attacker.setMana(attacker.getMana() - 40);
                skillIsPressed = false;
                messageForMonsters = attacker.getName() + " healed " + needed.getName();
                monsterDamage = "+30 HP";
                attackedMonster = needed;
                currentAnimation = heroHealAnimation;
                animationStarted = true;
            }
            if (skillIsPressed && attacker.getSkill().equals("berserk call")) {
                skillIsPressed = false;
                attacker.setMana(attacker.getMana() - 40);
                attackedHero = attacker;
                messageForMonsters = attacker.getName() + " used berserk call";
                monsterDamage = "";
                attackedMonster = attacker;
                agr = true;
                currentAnimation = agrAnimation;
                animationStarted = true;
            }
            for (GameObject monster : monsters) {
                if (Gdx.input.getX() > monster.getX() && Gdx.input.getX() < monster.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > monster.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < monster.getY() + 300) {
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && monster.getHealth() > 0) {
                        defAttack(monster);
                    } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && monster.getHealth() > 0) {
                        if (attacker.getSkill().equals("powershot")) {
                            powerShot(monster);
                        } else if (attacker.getSkill().equals("sunstrike") && monsters.indexOf(monster) == 0) {
                            sunstrike2(badCharacter1, badCharacter2);
                        } else if (attacker.getSkill().equals("sunstrike") && monsters.indexOf(monster) == 3) {
                            sunstrike2(badCharacter3, badCharacter4);
                        } else if (attacker.getSkill().equals("sunstrike") && monsters.indexOf(monster) == 2) {
                            sunstrike(badCharacter2, badCharacter3, badCharacter4);
                        } else if (attacker.getSkill().equals("sunstrike") && monsters.indexOf(monster) == 1) {
                            sunstrike(badCharacter1, badCharacter2, badCharacter3);
                        }
                    }
                }
            }

        } else if (stepCount % 2 == 0 && !gameOver && !animationStarted) {
            boolean clear = true;
            addManaMonsters = true;
            for (GameObject monster : monsters) {
                if (monster.getHealth() > 0 && !monsterAttackedLast.contains(monster)) {
                    clear = false;
                    break;
                }
            }
            if (clear) {
                monsterAttackedLast.clear();
            }
            int count = 0;
            GameObject hero = heroes.get(0);
            if (checkRandom % 2 == 1) {
                for (GameObject heros : heroes) {
                    if (count >= 4) {
                        break;
                    }
                    if (heros.getCharacterClass() == GameObject.CharacterClass.PALADIN && heros.getHealth() > 0) {
                        hero = heros;
                        break;
                    } else if (heros.getCharacterClass() == GameObject.CharacterClass.MAGIC && heros.getHealth() > 0) {
                        hero = heros;
                    } else if (heros.getCharacterClass() == GameObject.CharacterClass.ARCHER && hero.getCharacterClass()
                            != GameObject.CharacterClass.MAGIC && heros.getHealth() > 0) {
                        hero = heros;
                    } else if (heros.getCharacterClass() == GameObject.CharacterClass.WARIOR && hero.getCharacterClass()
                            != GameObject.CharacterClass.MAGIC && hero.getCharacterClass() != GameObject.CharacterClass.ARCHER
                            && heros.getHealth() > 0) {
                        hero = heros;
                    }
                    count++;
                }
                checkRandom++;
            } else {
                do {
                    hero = heroes.get(random.nextInt(heroes.size()));
                } while (hero.getHealth() <= 0);
                checkRandom++;
            }
            for (GameObject weak : heroes) {
                if (weak.getHealth() <= 20 && weak.getHealth() > 0) {
                    hero = weak;
                    break;
                }
            }
            if (agr) {
                hero = attackedHero;
                agr = false;
            }
            for (GameObject manaUpdate : heroes) { //добовляет ману
                if (manaUpdate.getMana() < 100 && manaUpdate.getHealth() > 0) {
                    manaUpdate.setMana(manaUpdate.getMana() + 10);
                    if (manaUpdate.getMana() > 100) {
                        manaUpdate.setMana(100);
                    }
                }
            }
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            for (GameObject monster : monsters) {
                boolean attackFlag = true;
                if (monster.getHealth() > 0 && !monsterAttackedLast.contains(monster)) {
                    if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER) && monster.getMana() >= 30) {
                        for (GameObject monsterToHeal : monsters) {
                            if (monsterToHeal.getHealth() <= 30) {
                                currentAnimation = monsterHealAnimation;
                                animationStarted = true;
                                monsterToHeal.setHealth(monsterToHeal.getHealth() + 50);
                                message = monster.getName() + " cured " + monsterToHeal.getName();
                                attackFlag = false;
                                monsterDamage = "+50 HP";
                                attackedMonster = monsterToHeal;
                                monster.setMana(monster.getMana() - 30);
                                break;
                            }
                        }
                    }
                    if (attackFlag) {
                        currentAnimation = monsterAttackAnimation;
                        animationStarted = true;
                        message = monster.getName() + " attached " + hero.getName();
                        monsterDamage = "-" + monster.getPower() + " HP";
                        hero.setHealth(Math.max(hero.getHealth() - monster.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                    }
                    attackedMonster = hero;
                    monsterAttackedLast.add(monster);
                    break;
                }
            }
        }
        if (animationStarted) {
            font.draw(batch, "Monsters turn! " + stepCount, 100, 1000);
            font.draw(batch, messageForMonsters, 100, 950);
            if (currentAnimation.equals(sunstrikeAnimation)) {
                if (attackedMonster.equals(monsters.get(0))) {
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 200);
                } else if (attackedMonster.equals(monsters.get(1))) {
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 300);
                    font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 300);
                } else if (attackedMonster.equals(monsters.get(2))) {
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 200);
                    font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 200);
                } else if (attackedMonster.equals(monsters.get(3))) {
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                    font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 300);
                }
            }
            flag++;
            if (flag > 100) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                if (currentAnimation.equals(monsterAttackAnimation) || currentAnimation.equals(monsterHealAnimation)) {
                    font.draw(batch, message, 100, 900);
                }
                currentAnimation.startAnimation();
                if (currentAnimation.equals(sunstrikeAnimation)) {
                    if (attackedMonster.equals(monsters.get(0))) {
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter1.getX() - 30, badCharacter1.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter2.getX()- 30, badCharacter2.getY(), 300, 320);
                    } else if (attackedMonster.equals(monsters.get(1))) {
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter1.getX()- 30, badCharacter1.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter2.getX()- 30, badCharacter2.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter3.getX()- 30, badCharacter3.getY(), 300, 320);
                    } else if (attackedMonster.equals(monsters.get(2))) {
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter2.getX()- 30, badCharacter2.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter3.getX()- 30, badCharacter3.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter4.getX()- 30, badCharacter4.getY(), 300, 320);
                    } else if (attackedMonster.equals(monsters.get(3))) {
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter3.getX() - 30, badCharacter3.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, badCharacter4.getX() - 30, badCharacter4.getY(), 300, 320);
                    }
                } else {
                    batch.draw(currentAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY() - 50, 300, 320);
                }
            }
            if ((currentAnimation.equals(agrAnimation) && currentAnimation.animation.isAnimationFinished(currentAnimation.stateTime)) || (currentAnimation.animation.isAnimationFinished(currentAnimation.stateTime) && flag > 200)) {
                flag = 0;
                animationStarted = false;
                currentAnimation.stateTime = 0f;
                stepCount++;
                if (!currentAnimation.equals(monsterAttackAnimation) && !currentAnimation.equals(monsterHealAnimation)) {
                    WHOWILLATTACK++;
                }
            }
        }

        if (badCharacter1.getHealth() == 0 && badCharacter2.getHealth() == 0 && badCharacter3.getHealth() == 0 && badCharacter4.getHealth() == 0 && !animationStarted) {
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
        if (goodCharacter1.getHealth() == 0 && goodCharacter2.getHealth() == 0 && goodCharacter3.getHealth() == 0 && goodCharacter4.getHealth() == 0 && !animationStarted) {
            gameOver = true;
            batch.draw(monstersWinScreen, LOST_SCREEN_X, LOST_SCREEN_Y, LOST_SCREEN_WIDTH, LOST_SCREEN_HEIGHT);
            if (Gdx.input.getX() > MAIN_MENU2_X_START && Gdx.input.getX() < MAIN_MENU2_X_END && Gdx.input.getY() > MAIN_MENU2_Y_START && Gdx.input.getY() < MAIN_MENU2_Y_END) {
                batch.draw(mainMenuButton2, 835, 385, 228, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    game.setScreen(new MainMenuScreen(game, openLevelNumber, music, false));
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
        attackedMonster = gameObject;
        monsterDamage = "-100 HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - 100, 0));
        attacker.setMana(attacker.getMana() - 100);
        skillIsPressed = false;
        currentAnimation = powershotAnimation;
        animationStarted = true;
    }

    private void defAttack(GameObject gameObject) {
        messageForMonsters = "You attacked " + gameObject.getName();
        attackedMonster = gameObject;
        monsterDamage = "-" + attacker.getPower() + " HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - attacker.getPower(), 0));
        canbeattacked = false;
        currentAnimation = heroAttackAnimation;
        animationStarted = true;
    }

    private void sunstrike(GameObject gameObject1, GameObject gameObject2, GameObject gameObject3) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        gameObject3.setHealth(Math.max(gameObject3.getHealth() - 30, 0));
        attackedMonster = gameObject2;
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        currentAnimation = sunstrikeAnimation;
        animationStarted = true;
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
        currentAnimation = sunstrikeAnimation;
        animationStarted = true;
        skillIsPressed = false;
    }
}