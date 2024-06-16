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
    // ターン数の初期値
    private final int START_TURN_COUNT = 1;

    // ConsoleManagerのインスタンス
    public static ConsoleManager consoleManager = new ConsoleManager();
    // パーティのインスタンス
    public Party party;
    // ターン数
    public int turnCount;
    // ゲーム終了のフラグ
    public boolean isGameEnd = false;    

    /**
     * ゲームを開始する
     * キャラクターを作成し、ゲームが終了するまでターンを進める
     */
    public void start() {
        party = new Party();

        //プレイヤーチームとエネミーチームを作成
        //makeDebugParty();※デバッグ用のパーティ作成
        makeParty(Teams.PLAYER);
        makeParty(Teams.ENEMY);
        

        //プレイヤーリストを素早さ順にソート
        party.sortPlayerListForAgi();

        consoleManager.setPlayerToPlayerComponent(party);
        
        turnCount = START_TURN_COUNT;

        // ゲームが終了するまでターン処理を繰り返す
        while(!party.checkGameEnd()){
            turnStartProcess();
            turnProcess();
            if(party.checkGameEnd()){
                break;
            }
            turnEndProcess();
        }
        consoleManager.printLog();

        // ゲーム終了の処理
        gameFinish();
    }

    /**
     * ターン開始時の処理
     * ターン数を表示し、コンソールログを更新する
     */
    public void turnStartProcess(){
        consoleManager.addLogText(String.format(GameConstants.TURN_COUNT_MESSAGE, turnCount));
        consoleManager.printLog();
    }

    /**
     * ターン処理
     * 全てのプレイヤーが1回ずつ攻撃する
     */
    public void turnProcess(){
        //全てのプレイヤーが攻撃するか、ゲーム終了条件を満たすまで繰り返す
        while(!party.checkTurnEnd() && !party.checkGameEnd()){
            //攻撃プレイヤーと防御プレイヤーを決定する
            Player attacker = party.getAttacker();
            Player defender = attacker.getTactics().getTargetPlayer(party, attacker);
            
            attacker.setEnemy(defender);
            UserInput.waitForEnter();
            //攻撃処理
            attacker.attack();
            //攻撃後の処理
            attacker.afterAttackProcess();
            //コンソールログを更新
            consoleManager.printLog();
        }
    }

    /**
     * Partyを作成する
     * チームを受け取り、チーム人数分のプレイヤーを作成し、Partyに追加する
     */
    
    private void makeParty(Teams team){
        for (int i = 0; i < GameConstants.NUMBER_OF_TEAM_MEMBERS; i++) {
            Player player = makePlayer(team);
            party.appendPlayer(player);
        }
    }
    

    /**
     * プレイヤーを作成する
     * @param team
     * @return 作成されたプレイヤー
     */
    private Player makePlayer(Teams team){
        //標準入力でジョブIDを決定する
        int jobId = choiceJobForinput();
        //標準入力で名前を決定する
        String name = decideNameForInput();
        //標準入力で作戦を決定する
        Tactics tactics = choiceTacticsForInput();

        //プレイヤーを作成して返す
        return CharacterType.createPlayer(jobId, name, team, tactics);
    }

    /**
     * 標準入力で名前を決定して返す
     * @return 入力された名前
     */
    private String decideNameForInput(){
        consoleManager.addLogText(GameConstants.DECIDE_NAME_MESSAGE);
        return UserInput.inputString();
    }
    
    /**
     * 標準入力でジョブIDを決定
     * @return 選択されたジョブのID
     */
    private int choiceJobForinput(){
        ArrayList<Integer> jobIntList = new ArrayList<Integer>();
        //ジョブIDのリストを作成
        for (CharacterType characterClass : CharacterType.values()) {
            jobIntList.add(characterClass.getId());
        }
        
        //jobIntを-1で初期化
        int jobInt = -1;
        //ジョブIDのリストの範囲内の値が入力されるまで繰り返す
        do{
            showSelectJobMessage();
            jobInt = UserInput.inputIntValidValues(jobIntList);
        }while(!CharacterType.isValidId(jobInt));

        return jobInt;
    }

    /**
     * 職業選択のメッセージを表示する
     */
    private void showSelectJobMessage(){
        consoleManager.addLogText(GameConstants.SELECT_JOB_MESSAGE);
        //CharacterTypeの各要素のIDと表示名を表示
        for (CharacterType characterClass : CharacterType.values()) {
            consoleManager.addLogText(characterClass.getId() + ": " + characterClass.getDisplayName());
        }
    }

    /**
     * 作戦選択のメッセージを表示する
     */
    private void showSelectTacticsMessage(){
        consoleManager.addLogText(GameConstants.SELECT_TACTICS_MESSAGE);
        //TacticsTypeの各要素のIDと表示名を表示
        for (TacticsType tacticsClass : TacticsType.values()) {
            consoleManager.addLogText(tacticsClass.getId() + ": " + tacticsClass.getDisplayName());
        }
    }

    /**
     * ゲーム終了時の処理
     */
    private void gameFinish(){
        Teams winTeam = party.getWinner();
        consoleManager.addLogText(String.format(GameConstants.GAME_FINISH_MESSAGE, winTeam));
        consoleManager.printLog();
    }

    /**
     * ターン終了時の処理
     */
    private void turnEndProcess(){
        //毒ダメージの処理
        party.poisonDamage();
        //攻撃済みフラグをリセット
        party.resetIsAttacked();
        //ターン数をインクリメント
        turnCount++;
        //コンソールログを更新
        consoleManager.addLogText(GlobalConstants.PARTITION);
    }

    /**
     * 標準入力で作戦を決定
     * @return 選択された作戦
     */
    private Tactics choiceTacticsForInput(){
        //作戦のIDのリストを作成
        ArrayList<Integer> tacticsIntList = new ArrayList<Integer>();
        for (TacticsType tacticsClass : TacticsType.values()) {
            tacticsIntList.add(tacticsClass.getId());
        }
        
        //tacticsIntを-1で初期化
        int tacticsInt = -1;
        //作戦IDのリストの範囲内の値が入力されるまで繰り返す
        do{
            showSelectTacticsMessage();
            tacticsInt = UserInput.inputIntValidValues(tacticsIntList);
        }while(!TacticsType.isValidId(tacticsInt));

        return TacticsType.createTactics(tacticsInt);
    }

    /**
     * デバッグ用のパーティを作成する
     */
    private void makeDebugParty(){
        //プレイヤーチームとエネミーチームを作成
        party.appendPlayer(CharacterType.createPlayer(2,"プレイヤー1",Teams.PLAYER,TacticsType.createTactics(4)));
        party.appendPlayer(CharacterType.createPlayer(2,"プレイヤー2",Teams.PLAYER,TacticsType.createTactics(2)));
        party.appendPlayer(CharacterType.createPlayer(3,"プレイヤー3",Teams.PLAYER,TacticsType.createTactics(2)));

        party.appendPlayer(CharacterType.createPlayer(3,"エネミー1",Teams.ENEMY,TacticsType.createTactics(4)));
        party.appendPlayer(CharacterType.createPlayer(2,"エネミー2",Teams.ENEMY,TacticsType.createTactics(1)));
        party.appendPlayer(CharacterType.createPlayer(3,"エネミー3",Teams.ENEMY,TacticsType.createTactics(3)));

    }
}