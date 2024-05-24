package masternamebattler;
public class GameConstants {

    /**
     * プレイヤーのチームの列挙型
     */
    public static enum Teams {
        PLAYER,
        ENEMY
    };

    public static final String GAME_FINISH_MESSAGE = "%sの勝利です";
    public static final String SELECT_JOB_MESSAGE = "キャラクターの職業を選択してください:";
    public static final String INVALID_INPUT_MESSAGE = "入力が正しくありません";
    public static final String TURN_COUNT_MESSAGE = "ターン%d";

    public static final int NUMBER_OF_TEAM_MENBERS = 3;
}
