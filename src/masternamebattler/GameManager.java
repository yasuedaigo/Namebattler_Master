package masternamebattler;

import java.util.ArrayList;

import masternamebattler.Chara.CharacterType;
import masternamebattler.Chara.Player;
import masternamebattler.Console.ConsoleManager;
import masternamebattler.GameConstants.Teams;
import masternamebattler.Util.UserInput.UserInput;

/**
 * ゲームの進行を管理するクラス
 */
public class GameManager {
    private final int START_TURN_COUNT = 1;

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
        makeParty(Teams.PLAYER);
        makeParty(Teams.ENEMY);
        party.sortPlayerListForAgi();
        
        turnCount = START_TURN_COUNT;
        System.out.println(GlobalConstants.BLANK_LINE);
        while(!party.checkGameEnd()){
            turnStartProcess();
            turnProcess();
            if(party.checkGameEnd()){
                break;
            }
            turnEndProcess();
        }
        gameFinish();
    }

    public void turnStartProcess(){
        System.out.println(String.format(GameConstants.TURN_COUNT_MESSAGE, turnCount));
        party.showMembers();
    }

    public void turnProcess(){
        while(!party.checkTurnEnd() && !party.checkGameEnd()){
            Player attacker = party.getAttacker();
            Player defender = party.getDefender(attacker);
            UserInput.waitForEnter();
            attacker.attack(defender);
            ConsoleManager.printParty(party);
        }
    }

    /**
     * 
     * プレイヤーを2名作成しリストに加える
     */
    /*
    private void makeParty(Teams team){
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            Player player = makePlayer(team);
            party.appendPlayer(player);
        }
    }
    */

    private Player makePlayer(Teams team){
        int jobId = choiceJobForinput();
        String name = decideNameForInput();
        return CharacterType.createPlayer(jobId, name, team);
    }

    private String decideNameForInput(){
        System.out.println(GameConstants.DECIDE_NAME_MESSAGE);
        return UserInput.inputString();
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
     * ゲーム終了時のメッセージを表示する
     */
    private void gameFinish(){
        Teams winTeam = party.getWinner();
        System.out.println(String.format(GameConstants.GAME_FINISH_MESSAGE, winTeam));
    }

    /**
     * ターン終了時の処理
     * 状態異常のダメージ処理を行い、ターン数を進める
     */
    private void turnEndProcess(){
        party.poisonDamage();
        turnCount++;
        System.out.println(GlobalConstants.PARTITION);
    }

    //  デバック用
    private void makeParty(Teams team){
        Player player = CharacterType.createPlayer(1, "プレイヤー1", team);
        party.appendPlayer(player);
        player = CharacterType.createPlayer(2, "プレイヤー2", team);
        party.appendPlayer(player);
        player = CharacterType.createPlayer(3, "プレイヤー3", team);
        party.appendPlayer(player);
    }
}