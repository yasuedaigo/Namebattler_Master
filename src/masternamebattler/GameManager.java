package masternamebattler;

import java.util.ArrayList;
import java.util.Scanner;

import masternamebattler.chara.Player;
import masternamebattler.Constants.Teams;
import masternamebattler.chara.Conditions;
import masternamebattler.chara.CharacterType;

/**
 * ゲームの進行を管理するクラス
 */
public class GameManager {
    
    public static final String GAME_FINISH_MESSAGE = "%sの勝利です";
    public static final String SELECT_JOB_MESSAGE = "キャラクターの職業を選択してください:";
    public static final String INVALID_INPUT_MESSAGE = "入力が正しくありません";
    public static final String TURN_COUNT_MESSAGE = "ターン%d";
    public static final int START_TURN_COUNT = 1;

    public ArrayList<Player> playerList = new ArrayList<Player>();
    public Player mainPlayer;
    public Player enemyPlayer;
    public int turnCount;
    
    /**
     * ゲームを開始する
     * キャラクターを作成し、ゲームが終了するまでターンを進める
     */
    public void start() {
        prepairPlayers();
        sortPlayerList();
        Player firstPlayer = playerList.get(0);
        Player secondPlayer = playerList.get(1); 

        turnCount = START_TURN_COUNT;
        System.out.println(Constants.BLANK_LINE);
        while(!checkGameEnd()){
            System.out.println(String.format(TURN_COUNT_MESSAGE, turnCount));
            firstPlayer.attack(secondPlayer);
            if(checkGameEnd()){
                break;
            }
            secondPlayer.attack(firstPlayer);
            turnEndProcess();
            System.out.println(Constants.PARTITION);
        }
        gameFinish();
    }

    /**
     * プレイヤーを2名作成しリストに加える
     */
    private void prepairPlayers(){
        mainPlayer = makePlayer(Teams.PLAYER);
        enemyPlayer = makePlayer(Teams.ENEMY);
        
        playerList.add(mainPlayer);
        playerList.add(enemyPlayer);
        for (Player player : playerList) {
            player.showInfo();
        }
    }

    /**
     * ジョブを入力してもらい、プレイヤーを作成する
     * @param team プレイヤーの所属するチーム
     * @return 作成したプレイヤー
     */
    private Player makePlayer(Teams team){
        int jobInt = inputJobChoice();
        CharacterType selectedClass = CharacterType.fromId(jobInt);
        return selectedClass.createPlayer(team);
    }

    /**
     * ジョブ選択を入力してもらう
     * 選択肢を表示し、入力値を検証し、正しい入力が行われるまで繰り返す
     * @return 選択されたジョブのID
     */
    private int inputJobChoice(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do{
            System.out.println(SELECT_JOB_MESSAGE);
            for (CharacterType characterClass : CharacterType.values()) {
                System.out.println(characterClass.getId() + ": " + characterClass.getDisplayName());
            }
            if(!scanner.hasNextInt()){
                System.out.println(INVALID_INPUT_MESSAGE);
                scanner.next();
                continue;
            }
            choice = scanner.nextInt();
            if(!CharacterType.isValidId(choice)){
                System.out.println(INVALID_INPUT_MESSAGE);
                continue;
            }
        }while(!CharacterType.isValidId(choice));

        return choice;
    }

    /**
     * プレイヤーリストを素早さの降順に並び替える
     */
    private void sortPlayerList(){
        playerList.sort((player1, player2) -> Integer.compare(player2.getAgi(), player1.getAgi()));
    }

    /**
     * ゲームが終了しているかを判定する
     * @return どちらかのプレイヤーが倒れている場合はtrue
     */
    private boolean checkGameEnd(){
        for (Player player : playerList) {
            if(!player.getIsLive()){
                return true;
            }
        }
        return false;
    }

    /**
     * ゲーム終了時のメッセージを表示する
     */
    private void gameFinish(){
        for (Player player : playerList) {
            if(player.getIsLive()){
                System.out.println(String.format(GAME_FINISH_MESSAGE, player.getName()));
            }
        }
    }

    /**
     * ターン終了時の処理
     * 状態異常のダメージ処理を行い、ターン数を進める
     */
    private void turnEndProcess(){
        if(checkGameEnd()){
            return;
        }
        for (Player player : playerList) {
            if(player.getCondition() == Conditions.POISON){
                player.poisonDamage();
            }
        }
        showHP();
        turnCount++;
    }

    /**
     * 全プレイヤーのHPを表示する
     */
    private void showHP(){
        for (Player player : playerList) {
            player.showHp();
        }
    }
}