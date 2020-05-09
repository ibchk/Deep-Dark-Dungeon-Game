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
    public int tcpC = 5221;
    String IPConnection = "localhost";

    public int myPlace;
    public boolean game = false;
    public List<String> enemy;
    public boolean myTurn;

    public int characterWhoAttacked;
    public int attachedCharacter;
    public boolean skillUsed;


    public Client client;

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
                    c.sendTCP(allowToStart);
                } else if (o instanceof Packets.AllowToStart) {
                    if (((Packets.AllowToStart) o).allow) {
                        game = true;
                        // игра начало
                        enemy = ((Packets.AllowToStart) o).anotherGamerCharacters;
                        Packets.AllowToAttack allowToSend = new Packets.AllowToAttack();
                        allowToSend.gamer = myPlace;
                        System.out.println("started");
                        c.sendTCP(allowToSend);
                    } else {
                        c.sendTCP(o);
                    }
                } else if (o instanceof Packets.GameInfo) {
                    System.out.println("I'm the first!!!!");
                    // тут мы получаем информацию об ударе от соперника и ее воспроизвоодим у себя в игре.
                    // ПРИ ПЕРВОМ ПОЛУЧЕНИИ Packets.GameInfo У НЕГО ПАРАМЕТРЫ НЕ ИНИЦИАЛЕЗИРОВАНЫ.
                    // после мы делаем свой удар и шлем Packets.GameInfo на сервер, в ответ мы ничего не получаем.
                    // далее начинаем посылать Packets.AllowToAttack пока не получим в ответ Packets.GameInfo чтобы походить
                    // самим
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
        client.sendTCP(ask);
    }

    public void sendGameInfo(int characterWhoAttacked, int attackedCharacter, boolean skill) {
        System.out.println("Game info sended");
        myTurn = false;
        Packets.GameInfo myInfo = new Packets.GameInfo();
        myInfo.gamer = myPlace;
        myInfo.characterWhoBeat = characterWhoAttacked;
        myInfo.damagedCharacter = attackedCharacter;
        myInfo.animation = skill;
        client.sendTCP(myInfo);
    }

//    public void askIfCanAttack() {
//        Packets. p1cords = new Packets.Packet01Message();
//        p1cords.message = message;
//        client.sendTCP(p1cords);
//    }

    private void registerPackets() {
        Kryo kryo = client.getKryo();
        kryo.register(Packets.ConnectToGame.class);
        kryo.register(Packets.AllowToStart.class);
        kryo.register(Packets.GameInfo.class);
        kryo.register(Packets.AllowToAttack.class);
        kryo.register(LinkedList.class);
    }
}
