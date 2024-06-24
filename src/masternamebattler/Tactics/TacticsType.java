package masternamebattler.Tactics;

import java.util.function.Supplier;

/**
 * 作戦を表す列挙型
 */
public enum TacticsType {
    ANTI_MAGIC_TACTICS(1, () -> AntiMagicTactics.DISPLAY_NAME, AntiMagicTactics::new),
    ATTACK_TACTICS(2, () -> AttackTactics.DISPLAY_NAME, AttackTactics::new),
    BALANCE_TACTICS(3, () -> BalanceTactics.DISPLAY_NAME, BalanceTactics::new),
    DEFENSE_TACTICS(4, () -> DefenseTactics.DISPLAY_NAME, DefenseTactics::new),
    RANDOM_TACTICS(5, () -> RandomTactics.DISPLAY_NAME, RandomTactics::new);

    // 作戦のID
    private final int id;
    // 表示名のサプライヤー
    private final Supplier<String> displayNameSupplier;
    // 作戦のコンストラクタ
    private final Supplier<Tactics> tacticsConstructor;

    /**
     * コンストラクタ
     * 
     * @param id                  戦術のID
     * @param displayNameSupplier 表示名のサプライヤー
     * @param tacticsConstructor  作戦のコンストラクタ
     */
    TacticsType(int id, Supplier<String> displayNameSupplier, Supplier<Tactics> tacticsConstructor) {
        this.id = id;
        this.displayNameSupplier = displayNameSupplier;
        this.tacticsConstructor = tacticsConstructor;
    }

    /**
     * IDから作戦のインスタンスを生成する
     * 
     * @return 作戦のインスタンス
     */
    public static Tactics createTactics(int tacticsId) {
        // IDと一致する作戦のコンストラクタを呼び出す
        for (TacticsType tactics : values()) {

            if (tactics.getId() == tacticsId) {
                return tactics.tacticsConstructor.get();
            }
        }
        return null;
    }

    /**
     * 受け取ったidが有効かどうかを判定する
     * 
     * @param id 職業のID
     * @return 有効な値の場合true
     */
    public static boolean isValidId(int id) {
        for (TacticsType tacticsClass : values()) {

            if (tacticsClass.getId() == id) {
                return true;
            }
            
        }
        return false;
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

}
