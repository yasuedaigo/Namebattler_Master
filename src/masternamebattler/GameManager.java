package masternamebattler;

import java.util.ArrayList;

import masternamebattler.Chara.CharacterType;
import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;
import masternamebattler.GameConstants.Teams;
import masternamebattler.Util.UserInput.UserInput;

/**
 * ゲームの進行を管理するクラス
 */
public class GameManager {
    private final int START_TURN_COUNT = 1;

    public ArrayList<Player> playerList = new ArrayList<Player>();
    public Player mainPlayer;
    public Player enemyPlayer;
    public Party party;
    public int turnCount;
    public boolean isGameEnd = false;    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * ゲームを開始する
     * キャラクターを作成し、ゲームが終了するまでターンを進める
     */
    public void start() {
        party = new Party();
        prepairPlayers();
        party.appendPlayer(mainPlayer);
        party.appendPlayer(enemyPlayer);
        party.sortPlayerListForAgi();
        Player firstPlayer = playerList.get(0);
        Player secondPlayer = playerList.get(1);

        turnCount = START_TURN_COUNT;
        System.out.println(GlobalConstants.BLANK_LINE);
        while(!checkGameEnd()){
            turnStartProcess(party);
            UserInput.waitForEnter();
            turnProxess();
            if(checkGameEnd()){
                break;
            }
            UserInput.waitForEnter();
            turnEndProcess();
        }
        

        turnCount = START_TURN_COUNT;
        System.out.println(GlobalConstants.BLANK_LINE);
        while(!checkGameEnd()){
            
            System.out.println(String.format(GameConstants.TURN_COUNT_MESSAGE, turnCount));
            firstPlayer.attack(secondPlayer);
            if(checkGameEnd()){
                break;
            }
            secondPlayer.attack(firstPlayer);
            turnEndProcess();
            System.out.println(GlobalConstants.PARTITION);
        }
        gameFinish();
    }

    public void turmStartProcess(){
        System.out.println(String.format(GameConstants.TURN_COUNT_MESSAGE, turnCount));
        party.showMembers();
    }

    public void turnProxess(){
        while(!party.checkTurnEnd() && !party.checkGameEnd()){
            Player attacker = party.getAttacker();
            Player defender = party.getDefender();
            UserInput.waitForEnter();
            attacker.attack(defender);
            UserInput.waitForEnter();
        }
    }

    /**
     * プレイヤーを2名作成しリストに加える
     */
    private void prepairPlayers(){
        for(int i = 0; i < GameConstants.NUMBER_OF_TEAM_MENBERS; i++){
            Player player = makePlayer(Teams.PLAYER);
            playerList.add(player);
        }
        for(int i = 0; i < GameConstants.NUMBER_OF_TEAM_MENBERS; i++){
            Player player = makePlayer(Teams.ENEMY);
            playerList.add(player);
        }
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
        int jobInt = choiceJobForinput();
        CharacterType selectedClass = CharacterType.fromId(jobInt);
        return selectedClass.createPlayer(team);
    }

    /**
     * ジョブ選択を入力してもらう
     * 選択肢を表示し、入力値を検証し、正しい入力が行われるまで繰り返す
     * @return 選択されたジョブのID
     */
    private int choiceJobForinput(){
        ArrayList<Integer> jobIntList = new ArrayList<Integer>();
        for (CharacterType characterClass : CharacterType.values()) {
            jobIntList.add(characterClass.getId());
        }
        
        int jobInt = -1;
        do{
            showSelectJobMessage();
            jobInt = UserInput.inputIntValidValues(jobIntList);
        }while(!CharacterType.isValidId(jobInt));

        return jobInt;
    }

    private void showSelectJobMessage(){
        System.out.println(GameConstants.SELECT_JOB_MESSAGE);
        for (CharacterType characterClass : CharacterType.values()) {
            System.out.println(characterClass.getId() + ": " + characterClass.getDisplayName());
        }
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
                System.out.println(String.format(GameConstants.GAME_FINISH_MESSAGE, player.getName()));
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