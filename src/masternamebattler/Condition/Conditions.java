package masternamebattler.Condition;

/**
 * 状態異常の列挙型
 * 表示名を持つ
 */
public enum Conditions {
    PARALYSIS(ConditionConstants.PARALYSIS_DISPLAY_NAME),
    POISON(ConditionConstants.POISON_DISPLAY_NAME);

    private final String DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param DISPLAY_NAME 表示名
     */
    Conditions(String DISPLAY_NAME) {
        this.DISPLAY_NAME = DISPLAY_NAME;
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}