package masternamebattler;

/**
 * ゲーム全体に関する定数
 */
public class GameConstants {

    /**
     * プレイヤーのチームの列挙型
     */
    public enum Teams {
        PLAYER,
        ENEMY;

        // 相手のチームを取得するためのフィールド
        private Teams opposite;

        // 相手のチームを設定
        static {
            PLAYER.opposite = ENEMY;
            ENEMY.opposite = PLAYER;
        }

        /**
         * 相手のチームを取得
         * 
         * @return 相手のチーム
         */
        public Teams getOpposite() {
            return opposite;
        }
    }

    // 勝利メッセージ
    public static final String GAME_FINISH_MESSAGE = "%sの勝利です";
    // キャラクターの職業選択メッセージ
    public static final String SELECT_JOB_MESSAGE = "キャラクターの職業を選択してください:";
    // ターン表示メッセージ
    public static final String TURN_COUNT_MESSAGE = "ターン%d";
    // 名前決定メッセージ
    public static final String DECIDE_NAME_MESSAGE = "名前を入力してください";
    // 作戦選択メッセージ
    public static final String SELECT_TACTICS_MESSAGE = "作戦を選択してください";

    // 1チームのメンバー数
    public static final int NUMBER_OF_TEAM_MEMBERS = 3;

    // 麻痺率
    public static final int PARALYSIS_RATE = 20;
    // 毒の最大ダメージ
    public static final int POISON_MAX_DAMAGE = 30;
    // 毒の最小ダメージ
    public static final int POISON_MIN_DAMAGE = 10;
}
