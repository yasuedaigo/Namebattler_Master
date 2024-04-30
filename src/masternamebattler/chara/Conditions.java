package masternamebattler.chara;

/**
 * 状態異常の列挙型
 * 表示名を持つ
 */
public enum Conditions {
    PARALYSIS("麻痺"),
    POISON("毒");

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