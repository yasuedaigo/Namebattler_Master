package masternamebattler.Chara;

import masternamebattler.GameConstants;
import masternamebattler.Util.QuadFunction;
import masternamebattler.Tactics.Tactics;

/**
 * キャラクターの職業の列挙型
 */
public enum CharacterType {
    FIGHTER(1, Fighter.DISPLAY_NAME, Fighter::new),
    PRIEST(2, Priest.DISPLAY_NAME, Priest::new),
    WIZARD(3, Wizard.DISPLAY_NAME, Wizard::new),
    NINJA(4, Ninja.DISPLAY_NAME, Ninja::new);

    // 職業のID
    private final int id;
    // 職業の表示名
    private final String displayName;
    // 職業のコンストラクタ
    private final QuadFunction<String, GameConstants.Teams, CharacterType, Tactics, Player> playerConstructor;

    /**
     * コンストラクタ
     * 
     * @param id                職業のID
     * @param displayName       職業の表示名
     * @param playerConstructor 職業のコンストラクタ
     */
    CharacterType(int id, String displayName,
            QuadFunction<String, GameConstants.Teams, CharacterType, Tactics, Player> playerConstructor) {
        this.id = id;
        this.displayName = displayName;
        this.playerConstructor = playerConstructor;
    }

    /**
     * プレイヤーのインスタンスを作成する
     * 
     * @param jobId   職業のID
     * @param name    プレイヤーの名前
     * @param useTeam プレイヤーの所属するチーム
     * @param tactics プレイヤーの作戦
     * @return プレイヤーのインスタンス
     */
    public static Player createPlayer(int jobId, String name, GameConstants.Teams useTeam, Tactics tactics) {
        // CharacterTypeの各要素のIDとjobIDを照合し、一致した要素のplayerConstructorを呼び出す
        for (CharacterType characterClass : values()) {

            if (characterClass.getId() == jobId) {
                return characterClass.playerConstructor.apply(name, useTeam, characterClass, tactics);
            }

        }
        return null;
    }

    /**
     * 入力されたidが有効な値かどうかを判定する
     * 
     * @param id 入力されたID
     * @return 有効な値の場合true
     */
    public static boolean isValidId(int id) {
        for (CharacterType characterClass : values()) {

            if (characterClass.getId() == id) {
                return true;
            }
            
        }
        return false;
    }

    /**
     * @return 職業のID
     */
    public int getId() {
        return id;
    }

    /**
     * @return 職業の表示名
     */
    public String getDisplayName() {
        return displayName;
    }
}
