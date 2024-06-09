package masternamebattler.Chara;

import java.util.function.BiFunction;
import java.util.function.Function;

import masternamebattler.GameConstants;
import masternamebattler.Util.QuadFunction;
import masternamebattler.Tactics.Tactics;

/**
 * キャラクターの職業を表す列挙型
 * ID、表示名、プレイヤーのコンストラクタを持つ
 */
public enum CharacterType {
    FIGHTER(1, Fighter.DISPLAY_NAME, Fighter::new),
    PRIEST(2, Priest.DISPLAY_NAME, Priest::new),
    WIZARD(3, Wizard.DISPLAY_NAME, Wizard::new),
    NINJA(4, Ninja.DISPLAY_NAME, Ninja::new);

    private final int id;
    private final String displayName;
    private final QuadFunction<String, GameConstants.Teams,CharacterType,Tactics, Player> playerConstructor;

    /**
     * コンストラクタ
     * @param id 職業のID
     * @param displayName 表示名
     * @param playerConstructor プレイヤーのコンストラクタ
     */
    CharacterType(int id, String displayName, QuadFunction<String, GameConstants.Teams,CharacterType,Tactics, Player> playerConstructor) {
        this.id = id;
        this.displayName = displayName;
        this.playerConstructor = playerConstructor;
    }

    /**
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * プレイヤーのインスタンスを生成する
     * @param jobId 職業のID
     * @param name プレイヤーの名前
     * @param useTeam プレイヤーの所属するチーム
     * @param consoleManager コンソールマネージャー
     * @return プレイヤーのインスタンス
     */
    public static Player createPlayer(int jobId, String name, GameConstants.Teams useTeam,Tactics tactics) {
        for (CharacterType characterClass : values()) {
            if (characterClass.getId() == jobId) {
                return characterClass.playerConstructor.apply(name, useTeam, characterClass,tactics);
            }
        }
        return null;
    }

    /**
     * 受け取ったidが有効かどうかを判定する
     * @param id 職業のID
     * @return 有効かどうか
     */
    public static boolean isValidId(int id) {
        for (CharacterType characterClass : values()) {
            if (characterClass.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
