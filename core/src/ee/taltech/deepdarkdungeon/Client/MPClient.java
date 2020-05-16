package ee.taltech.deepdarkdungeon.Client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MPClient {
    int udpC = 5200;
    public int tcpC = 5201;
    String IPConnection = "193.40.255.16"; //193.40.255.16

    public List<Integer> myPlace;
    public boolean game = false;
    public List<String> enemy;
    public boolean myTurn;

    public int characterWhoAttacked;
    public int attachedCharacter;
    public boolean skillUsed;


    public Client client;

    public boolean gameOver = false;

    public MPClient(final List<String> chars) {

        client = new Client();
        new Thread(client).start();

        registerPackets();
        client.addListener(new Listener() {
            public void connected(Connection c) {
                System.out.println("[CLIENT] >> You have connected");

                //Prepare and send message to the server
                Packets.ConnectToGame firstMessage = new Packets.ConnectToGame();
                firstMessage.characters = new LinkedList<>(chars);
                c.sendTCP(firstMessage);
            }

            public void disconnected(Connection c) {
                System.out.println("[CLIENT] >> You have disconnected");
            }

            public void received(Connection c, Object o) {
                if (o instanceof Packets.ConnectToGame) {
                    Packets.AllowToStart allowToStart = new Packets.AllowToStart();
                    myPlace = ((Packets.ConnectToGame) o).place;
                    allowToStart.gamer = ((Packets.ConnectToGame) o).place;
                    allowToStart.allow = false;
                    c.sendTCP(allowToStart);
                } else if (o instanceof Packets.AllowToStart) {
                    if (((Packets.AllowToStart) o).allow) {
                        game = true;
                        // игра начало
                        enemy = ((Packets.AllowToStart) o).anotherGamerCharacters;
                        Packets.AllowToAttack allowToSend = new Packets.AllowToAttack();
                        allowToSend.gamer = myPlace;
                        c.sendTCP(allowToSend);
                    } else {
                        Packets.AllowToStart newO = new Packets.AllowToStart();
                        newO.allow = ((Packets.AllowToStart) o).allow;
                        newO.anotherGamerCharacters = ((Packets.AllowToStart) o).anotherGamerCharacters;
                        newO.gamer = ((Packets.AllowToStart) o).gamer;
                        c.sendTCP(newO);
                    }
                } else if (o instanceof Packets.GameInfo) {
                    // тут мы получаем информацию об ударе от соперника и ее воспроизвоодим у себя в игре.
                    // ПРИ ПЕРВОМ ПОЛУЧЕНИИ Packets.GameInfo У НЕГО ПАРАМЕТРЫ НЕ ИНИЦИАЛЕЗИРОВАНЫ.
                    // после мы делаем свой удар и шлем Packets.GameInfo на сервер, в ответ мы ничего не получаем.
                    // далее начинаем посылать Packets.AllowToAttack пока не получим в ответ Packets.GameInfo чтобы походить
                    // самим
                    gameOver = ((Packets.GameInfo) o).gameOver;
                    myTurn = true;
                    characterWhoAttacked = ((Packets.GameInfo) o).characterWhoBeat;
                    attachedCharacter = ((Packets.GameInfo) o).damagedCharacter;
                    skillUsed = ((Packets.GameInfo) o).animation;
                }
            }
        });

        try {
            client.connect(9999, IPConnection, tcpC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void canIAttack() {
        Packets.AllowToAttack ask = new Packets.AllowToAttack();
        ask.gamer = myPlace;
        myTurn = false;
        client.sendTCP(ask);
    }

    public void sendGameInfo(int characterWhoAttacked, int attackedCharacter, boolean skill, boolean gameOver) {
        this.gameOver = gameOver;
        System.out.println("Game info sended");
        myTurn = false;
        Packets.GameInfo myInfo = new Packets.GameInfo();
        myInfo.gamer = myPlace;
        myInfo.characterWhoBeat = characterWhoAttacked;
        myInfo.damagedCharacter = attackedCharacter;
        myInfo.animation = skill;
        myInfo.gameOver = gameOver;
        client.sendTCP(myInfo);
    }

//    public void askIfCanAttack() {
//        Packets. p1cords = new Packets.Packet01Message();
//        p1cords.message = message;
//        client.sendTCP(p1cords);
//    }

    private void registerPackets() {
        Kryo kryo = client.getKryo();
        kryo.register(LinkedList.class);
        kryo.register(Packets.ConnectToGame.class);
        kryo.register(Packets.AllowToStart.class);
        kryo.register(Packets.GameInfo.class);
        kryo.register(Packets.AllowToAttack.class);
    }
}
