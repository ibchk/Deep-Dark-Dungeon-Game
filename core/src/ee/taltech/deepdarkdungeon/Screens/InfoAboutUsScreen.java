package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.PutMusic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoAboutUsScreen implements Screen, InputProcessor {

    DeepDarkDungeonGame game;
    BitmapFont font = new BitmapFont();
    Texture MUSICBUTTON1;
    Texture MUSICBUTTON2;
    Texture BACKGROUND;
    Texture BACKGROUND2;
    Texture UPBUTTON;
    Texture DOWNBUTTON;
    Texture BACKBUTTON;
    Texture BACKBUTTON2;
    int openLevelNumber;
    PutMusic music;
    int wheelMoved = 0;

    String text = "Kommunistlik ühiskonnakorraldus\n" +
            "Teoorias\n" +
            "Kommunism või kommunistlik ühiskonnakorraldus kui teoreetiline sotsiaalne ja majanduslik süsteem on egalitaarse ühiskonna tüüp, kus ei ole eraomandit ega sotsiaalseid klasse, riiki ega perekonda. Kommunismis on kõik varad ja tootmisvahendid ühiskondlikus omandis ja kõik inimesed on võrdsed nii sotsiaalselt kui majanduslikult. Kuulsaim kommunistliku ühiskonna põhimõte on: Igaühelt vastavalt tema võimalustele ja igaühele vastavalt tema vajadustele. Kommunistliku ühiskonna teoreetilised alused esinevad näiteks Friedrich Engelsi teoses \"Perekonna, riigi ja eraomandi tekkimine\".\n" +
            "\n" +
            "Praktikas\n" +
            "\"Kommunism\", \"kommunistlik riik\" ja \"kommunistlik režiim\" on läänemaailmas laialdaselt kasutatavad terminid kommunistliku partei võimu all olevate riikide ja neis valitseva korra kohta.\n" +
            "\n" +
            "Oma korra nimetamisel kasutati \"kommunismi\" asemel väljendeid \"sotsialism\" ja \"sotsialistlik riik\", sest vastavalt teooriale pidi sotsialism olema alles üleminekuaste kommunismile.\n" +
            "\n" +
            "Kommunistlik ideoloogia\n" +
            "Kommunism kui ideoloogia või teooria on vaadete, seisukohtade, ideede süsteem või maailmavaade, mis toetab ja põhjendab kommunistiku ühiskonnakorralduse loomist ning arendab selle loomise ideid. Kommunist on inimene, kes toetab kommunismi ideoloogiat. Selles tähenduses kasutas seda mõistet näiteks Lenin, rõhutades \"kommunismi õppimise\" vajadust.\n" +
            "\n" +
            "Kommunistlik liikumine\n" +
            "Kommunism kui poliitiline liikumine on osa laiemast, sotsialistlikust liikumisest. Selles tähenduses kasutas seda mõistet näiteks Lenin oma teoses \"\"Pahempoolsuse\" lastehaigus kommunismis\". Kommunistid erinevad sotsialistidest näiteks selle poolest, et nad pooldavad kapitalistlikult ühiskonnalt kommunistlikusse ideaalühiskonda üleminekut revolutsioonilisel teel. 21. sajandi sotsiaaldemokraatial ei ole enam kommunistidega kuigi palju ühiseid vaateid, ehkki Karl Marx, Friedrich Engels jt on mõlema liikumise jaoks autoriteedid.\n" +
            "\n" +
            "Sõna ajalugu\n" +
            "Sõnad \"kommunism\" ja \"kommunist\" tulid kasutusele Prantsusmaal pärast 1830. aasta revolutsiooni. Tavakeelde jõudsid nad 1840ndatel. Aastal 1840 korraldati Pariisis esimene communist banquet. Terminit kasutati ka utoopilise sotsialisti Étienne Cabet' toetajate nimetamisel. Sõna 'kommunism' tuleb prantsuse keelest, kus seda võib tõlkida nii kommuun, omavalitsuslik küla ehk kogukond kui ka communauté, ühisomand. Hiljem marksistid kasutasid seda sõna nii, et selles sisaldusid mõlemad elemendid. Sõna 'kommunism' tuli kasutusele Inglismaal prantsuse kommunistidest maapagulaste kaudu ja sel oli võitluslik tähendus vastandina rahumeelsele mõistele 'sotsialism'. Seepärast kasutasid Karl Marx ja Friedrich Engels seda sõna 'kommunism' Kommunistliku partei manifestis.\n" +
            "\n" +
            "Kommunismi kuriteod\n" +
            "Lähemalt artiklis Kommunismi kuriteod\n" +
            "\n" +
            "Kõige tuntumaks on sõna 'kommunism' teinud Nõukogude Liidus 1917–1991 valitsenud režiim, mis hukutas kümneid miljoneid inimesi.\n" +
            "\n" +
            "1917–1991 Nõukogude Liidus toimunu ja tema poolt naaberriikides kordasaadetu on 20. sajandi üks suuremaid inimsusvastaseid kuritegusid. Ka ükski teine kommunistlik režiim ei ole võimul püsinud ilma ulatuslike inimsusvastaste kuritegude toimepanekuta ning demokraatlikke vabadusi ja opositsioonilisi poliitilisi organisatsioone lämmatamata. Kommunism on üks totalitaarseid ideoloogiaid koos fašismi, äärmusteokraatia ja teistega. Prantsuse ajaloolaste \"Kommunismi musta raamatu\" andmeil on kommunistlikud režiimid hävitanud üle 100 miljoni inimese.\n" +
            "\n" +
            "Kommunismi voolud\n" +
            "Kommunism on aja jooksul omandanud eri vorme, mis konkretiseerivad algset õpetust, aga sageli ka hälbivad teatud määral sellest. Näiteks on mitmel pool tekkinud rahvuskommunism, mis teoorias peaks olema kommunismi internatsionalistlikule põhimõttele risti vastupidine.\n" +
            "\n" +
            "Suuremad kommunismivoolud on:\n" +
            "\n" +
            "eurokommunism\n" +
            "leninism\n" +
            "maoism\n" +
            "stalinism\n" +
            "trotskism";

    private static final int TEXTHEIGHESTPLACE = 950;
    private static int TEXTLOWESTPLACE = 0;
    private static int TEXTX = 200;
    private static double TEXTY = TEXTHEIGHESTPLACE;

    private static final int UP_BUTTON_WIDTH = 112;
    private static final int UP_BUTTON_HEIGHT = 100;
    private static final int UP_X = 1765;
    private static final int UP_Y = 900;

    private static final int DOWN_BUTTON_WIDTH = 112;
    private static final int DOWN_BUTTON_HEIGHT = 100;
    private static final int DOWN_X = 1765;
    private static final int DOWN_Y = 130;

    private static final int BACK_BUTTON_WIDTH = 150;
    private static final int BACK_BUTTON_HEIGHT = 58;
    private static final int BACKBUTTON_Y_START = 50;
    private static final int BACKBUTTON_Y_END = 108;
    private static final int BACKBUTTON_X_START = 30;
    private static final int BACKBUTTON_X_END = 180;

    public InfoAboutUsScreen(DeepDarkDungeonGame game, int openLevelNumber, PutMusic musicToStop) {
        this.openLevelNumber = openLevelNumber;
        this.game = game;
        this.music = musicToStop;
        Pattern pattern = Pattern.compile("\n");
        Matcher matcher = pattern.matcher(text);
        TEXTLOWESTPLACE = (int) (matcher.results().count() + 1) * 40;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        font.setColor(new Color(Color.rgb888(0f, 0f, 85f)));
        font.getData().setScale(2);
        MUSICBUTTON1 = new Texture("musicButton1.png");
        MUSICBUTTON2 = new Texture("musicButton2.png");
        BACKGROUND = new Texture("infoPageBackground.png");
        BACKGROUND2 = new Texture("infoPageBackground2.png");
        UPBUTTON = new Texture("scrollUp.png");
        DOWNBUTTON = new Texture("scrollDown.png");
        BACKBUTTON = new Texture("backbutton.png");
        BACKBUTTON2 = new Texture("backbutton2.png");
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(BACKGROUND, 0, 0, DeepDarkDungeonGame.WIDTH, DeepDarkDungeonGame.HEIGHT);
        font.draw(game.batch, text, TEXTX, (int) TEXTY);
        game.batch.draw(UPBUTTON, UP_X, UP_Y, UP_BUTTON_WIDTH, UP_BUTTON_HEIGHT);
        game.batch.draw(BACKGROUND2, 0, 0, DeepDarkDungeonGame.WIDTH, DeepDarkDungeonGame.HEIGHT);
        game.batch.draw(UPBUTTON, UP_X, UP_Y, UP_BUTTON_WIDTH, UP_BUTTON_HEIGHT);
        if (Gdx.input.getX() < UP_X + UP_BUTTON_WIDTH && Gdx.input.getX() > UP_X && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - UP_Y - 27 && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - UP_Y - 27 - UP_BUTTON_HEIGHT) {
            game.batch.draw(MUSICBUTTON2, UP_X, UP_Y, UP_BUTTON_WIDTH, UP_BUTTON_HEIGHT);
            if (Gdx.input.isTouched() && TEXTY > TEXTHEIGHESTPLACE) {
                TEXTY = TEXTY - 2.5;
            }
        }
        game.batch.draw(DOWNBUTTON, DOWN_X, DOWN_Y, DOWN_BUTTON_WIDTH, DOWN_BUTTON_HEIGHT);
        if (Gdx.input.getX() < DOWN_X + DOWN_BUTTON_WIDTH && Gdx.input.getX() > DOWN_X && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - DOWN_Y - 27 && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - DOWN_Y - 27 - DOWN_BUTTON_HEIGHT) {
            game.batch.draw(MUSICBUTTON2, DOWN_X, DOWN_Y, DOWN_BUTTON_WIDTH, DOWN_BUTTON_HEIGHT);
            if (Gdx.input.isTouched() && TEXTY < TEXTLOWESTPLACE) {
                TEXTY = TEXTY + 2.5;
            }
        }
        game.batch.draw(BACKBUTTON, BACKBUTTON_X_START, BACKBUTTON_Y_START, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        if (Gdx.input.getX() > BACKBUTTON_X_START && Gdx.input.getX() < BACKBUTTON_X_END && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - BACKBUTTON_Y_END - 27 && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - BACKBUTTON_Y_START - 27) {
            game.batch.draw(BACKBUTTON2, BACKBUTTON_X_START, BACKBUTTON_Y_START, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new MainMenuScreen(game, openLevelNumber));
            }
        }
        game.batch.end();
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

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount == 1 && TEXTY < TEXTLOWESTPLACE) {
            TEXTY = TEXTY + 30;
        } else if (amount == -1 && TEXTY > TEXTHEIGHESTPLACE) {
            TEXTY = TEXTY - 30;
        }
        return false;
    }
}
