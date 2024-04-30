package masternamebattler;
public class Constants {

    /**
     * プレイヤーのチームの列挙型
     */
    public static enum Teams {
        PLAYER,
        ENEMY
    };

    public static final String PARTITION = "--------------------------------------";
    public static final String BLANK_LINE = "\n";

    public static final int PERCENTAGE_BASE = 100;
    public static final int RANGE_INCLUSIVE_OFFSET = 1;
    public static final int MAX_LUCK = 255;
}