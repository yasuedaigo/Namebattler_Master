package masternamebattler.Tactics;

import java.util.function.Supplier;

import masternamebattler.Chara.CharacterType;

/**
 * 戦術のタイプを表す列挙型
 * ID、表示名、戦術のコンストラクタを持つ
 */
public enum TacticsType {
    ANTI_MAGIC_TACTICS(1, () -> AntiMagicTactics.DISPLAY_NAME, AntiMagicTactics::new),
    ATTACK_TACTICS(2, () -> AttackTactics.DISPLAY_NAME, AttackTactics::new),
    BALANCE_TACTICS(3, () -> BalanceTactics.DISPLAY_NAME, BalanceTactics::new),
    DEFENSE_TACTICS(4, () -> DefenseTactics.DISPLAY_NAME, DefenseTactics::new),
    RANDOM_TACTICS(5, () -> RandomTactics.DISPLAY_NAME, RandomTactics::new);

    private final int id;
    private final Supplier<String> displayNameSupplier;
    private final Supplier<Tactics> tacticsConstructor;

    /**
     * コンストラクタ
     * @param id 戦術のID
     * @param displayNameSupplier 表示名のサプライヤー
     * @param tacticsConstructor 戦術のコンストラクタ
     */
    TacticsType(int id, Supplier<String> displayNameSupplier, Supplier<Tactics> tacticsConstructor) {
        this.id = id;
        this.displayNameSupplier = displayNameSupplier;
        this.tacticsConstructor = tacticsConstructor;
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
        return displayNameSupplier.get();
    }

    /**
     * 戦術のインスタンスを生成する
     * @return 戦術のインスタンス
     */
    public static Tactics createTactics(int tacticsId) {
        for (TacticsType tactics : values()) {
            if (tactics.getId() == tacticsId) {
                return tactics.tacticsConstructor.get();
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
        for (TacticsType tacticsClass : values()) {
            if (tacticsClass.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
