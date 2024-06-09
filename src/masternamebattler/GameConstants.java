package masternamebattler;
public class GameConstants {

    /**
     * プレイヤーのチームの列挙型
     */
    public enum Teams {
        PLAYER,
        ENEMY;
    
        private Teams opposite;
    
        static {
            PLAYER.opposite = ENEMY;
            ENEMY.opposite = PLAYER;
        }
    
        public Teams getOpposite() {
            return opposite;
        }
    }

    public static final String GAME_FINISH_MESSAGE = "%sの勝利です";
    public static final String SELECT_JOB_MESSAGE = "キャラクターの職業を選択してください:";
    public static final String INVALID_INPUT_MESSAGE = "入力が正しくありません";
    public static final String TURN_COUNT_MESSAGE = "ターン%d";
    public static final String DECIDE_NAME_MESSAGE = "名前を入力してください";
    public static final String SELECT_TACTICS_MESSAGE = "作戦を選択してください";

    public static final int NUMBER_OF_TEAM_MEMBERS = 3;
}
