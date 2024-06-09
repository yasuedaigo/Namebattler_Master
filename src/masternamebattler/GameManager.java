package masternamebattler;

import java.util.ArrayList;

import masternamebattler.Chara.CharacterType;
import masternamebattler.Chara.Player;
import masternamebattler.Console.ConsoleManager;
import masternamebattler.GameConstants.Teams;
import masternamebattler.Tactics.Tactics;
import masternamebattler.Tactics.TacticsType;
import masternamebattler.Util.UserInput.UserInput;

/**
 * ゲームの進行を管理するクラス
 */
public class GameManager {
    private final int START_TURN_COUNT = 1;

    public static ConsoleManager consoleManager = new ConsoleManager();
    public Player mainPlayer;
    public Player enemyPlayer;
    public Party party;
    public int turnCount;
    public boolean isGameEnd = false;    

    /**
     * ゲームを開始する
     * キャラクターを作成し、ゲームが終了するまでターンを進める
     */
    public void start() {
        party = new Party();
        /*
        makeParty(Teams.PLAYER);
        makeParty(Teams.ENEMY);
        */
        makeParty(Teams.PLAYER);
        makeParty(Teams.ENEMY);
        //makePartyenemy(Teams.ENEMY);
        party.sortPlayerListForAgi();
        
        turnCount = START_TURN_COUNT;
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
        consoleManager.addLogText(String.format(GameConstants.TURN_COUNT_MESSAGE, turnCount));
        consoleManager.printLog(party);
    }

    public void turnProcess(){
        while(!party.checkTurnEnd() && !party.checkGameEnd()){
            Player attacker = party.getAttacker();
            Player defender = attacker.getTactics().getTargetPlayer(party, attacker);
            UserInput.waitForEnter();
            attacker.attack(defender);
            attacker.afterAttackProcess();
            consoleManager.printLog(party);
        }
    }

    /**
     * 
     * プレイヤーを2名作成しリストに加える
     */
    
    private void makeParty(Teams team){
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            Player player = makePlayer(team);
            party.appendPlayer(player);
        }
    }
    

    private Player makePlayer(Teams team){
        int jobId = choiceJobForinput();
        String name = decideNameForInput();
        Tactics tactics = choiceTacticsForInput();

        return CharacterType.createPlayer(jobId, name, team, tactics);
    }

    private String decideNameForInput(){
        consoleManager.addLogText(GameConstants.DECIDE_NAME_MESSAGE);
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
        consoleManager.addLogText(GameConstants.SELECT_JOB_MESSAGE);
        for (CharacterType characterClass : CharacterType.values()) {
            consoleManager.addLogText(characterClass.getId() + ": " + characterClass.getDisplayName());
        }
    }

    private void showSelectTacticsMessage(){
        consoleManager.addLogText(GameConstants.SELECT_TACTICS_MESSAGE);
        for (TacticsType tacticsClass : TacticsType.values()) {
            consoleManager.addLogText(tacticsClass.getId() + ": " + tacticsClass.getDisplayName());
        }
    }

    /**
     * ゲーム終了時のメッセージを表示する
     */
    private void gameFinish(){
        Teams winTeam = party.getWinner();
        consoleManager.addLogText(String.format(GameConstants.GAME_FINISH_MESSAGE, winTeam));
        consoleManager.printLog(party);
    }

    /**
     * ターン終了時の処理
     * 状態異常のダメージ処理を行い、ターン数を進める
     */
    private void turnEndProcess(){
        party.poisonDamage();
        party.resetIsAttacked();
        turnCount++;
        consoleManager.addLogText(GlobalConstants.PARTITION);
    }

    //  デバック用
    /*
    private void makeParty(Teams team){
        Player player = CharacterType.createPlayer(1, "プレイヤー1", team,TacticsType.createTactics(1));
        party.appendPlayer(player);
        player = CharacterType.createPlayer(2, "プレイヤー2", team,TacticsType.createTactics(2));
        party.appendPlayer(player);
        player = CharacterType.createPlayer(3, "プレイヤー3", team,TacticsType.createTactics(3));
        party.appendPlayer(player);
    }

    private void makePartyenemy(Teams team){
        Player player = CharacterType.createPlayer(4, "エネミー1", team,TacticsType.createTactics(4));
        party.appendPlayer(player);
        player = CharacterType.createPlayer(3, "エネミー2", team,TacticsType.createTactics(5));
        party.appendPlayer(player);
        player = CharacterType.createPlayer(2, "エネミー3", team,TacticsType.createTactics(1));
        party.appendPlayer(player);
    }
    */

    private Tactics choiceTacticsForInput(){
        ArrayList<Integer> tacticsIntList = new ArrayList<Integer>();
        for (TacticsType tacticsClass : TacticsType.values()) {
            tacticsIntList.add(tacticsClass.getId());
        }
        
        int tacticsInt = -1;
        do{
            showSelectTacticsMessage();
            tacticsInt = UserInput.inputIntValidValues(tacticsIntList);
        }while(!TacticsType.isValidId(tacticsInt));

        return TacticsType.createTactics(tacticsInt);
    }
}