package masternamebattler.Magic;

import masternamebattler.GameConstants;
import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「ポイズン」
 */
public class Poison extends Magic {
    // 表示名
    public final static String DISPLAY_NAME = MagicConstants.Poison.DISPLAY_NAME;
    // 消費MP
    public final static int CONSUMPTION_MP = MagicConstants.Poison.CONSUMPTION_MP;
    // 最大ダメージ
    public final static int POISON_MAX_DAMAGE = GameConstants.POISON_MAX_DAMAGE;
    // 最小ダメージ
    public final static int POISON_MIN_DAMAGE = GameConstants.POISON_MIN_DAMAGE;
    // 最大ダメージ
    public final static int MAX_DAMAGE = MagicConstants.Poison.MAX_DAMAGE;
    // 最小ダメージ
    public final static int MIN_DAMAGE = MagicConstants.Poison.MIN_DAMAGE;

    // 付与する状態異常
    private Conditions GRANT_CONDITION = Conditions.POISON;

    /**
     * 魔法を唱えたときの処理
     * 毒を付与する
     * 
     * @param user  味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        enemy.setCondition(Conditions.POISON);
    }

    /**
     * @return 消費MP
     */
    @Override
    public int getConsumptionMp() {
        return CONSUMPTION_MP;
    }

    /**
     * @return 表示名
     */
    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    /**
     * @return 付与する状態異常
     */
    @Override
    public Conditions getGrantCondition() {
        return GRANT_CONDITION;
    }

    /**
     * @return 最大ダメージ
     */
    protected int getMaxDamage() {
        return MAX_DAMAGE;
    }

    /**
     * @return 最小ダメージ
     */
    protected int getMinDamage() {
        return MIN_DAMAGE;
    }
}
