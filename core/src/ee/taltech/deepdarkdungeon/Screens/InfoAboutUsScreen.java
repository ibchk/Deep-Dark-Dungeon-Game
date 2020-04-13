package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;

public class InfoAboutUsScreen implements Screen {

    Texture BACKGROUND;
    DeepDarkDungeonGame game;
    BitmapFont font = new BitmapFont();
    Texture MUSICBUTTON1;
    Texture MUSICBUTTON2;
    private static final int MUSICBUTTON_Y_START = 10;
    private static final int MUSICBUTTON_Y_END = 110;
    private static final int MUSICBUTTON_X_START = 1788;
    private static final int MUSICBUTTON_X_END = 1900;
    private static final int MUSICBUTTON_Y_FORBUTTONCHANGE = 940;
    private static final int MUSIC_BUTTON_WIDTH = 112;
    private static final int MUSIC_BUTTON_HEIGHT = 100;

    private static final int C_X = 0;
    private static int C_Y = 500;
    private static int C_Y2 = 500;
    private static int TEXTX = 100;
    private static double TEXTY = 1080;

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
    public InfoAboutUsScreen(DeepDarkDungeonGame game){
        this.game = game;
    }
    @Override
    public void show() {
        BACKGROUND = new Texture("GameBackground.png");
        font.setColor(new Color(Color.rgb888(0f, 0f, 85f)));
        font.getData().setScale(2);
        MUSICBUTTON1 = new Texture("musicButton1.png");
        MUSICBUTTON2 = new Texture("musicButton2.png");
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(BACKGROUND, 0, 0, DeepDarkDungeonGame.WIDTH, DeepDarkDungeonGame.WIDTH);
        font.draw(game.batch, text, TEXTX,(int)TEXTY);
        game.batch.draw(MUSICBUTTON1, C_X, C_Y, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
        if (Gdx.input.getX() < MUSIC_BUTTON_WIDTH && Gdx.input.getY() > C_Y2 - MUSIC_BUTTON_HEIGHT / 2 && Gdx.input.getY() < C_Y2 + MUSIC_BUTTON_HEIGHT / 2) {
            game.batch.draw(MUSICBUTTON2, C_X, C_Y, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
            if (Gdx.input.isTouched() && TEXTY < 1900){
                TEXTY = TEXTY + 2.5;
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
}
