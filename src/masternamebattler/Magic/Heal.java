package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「ヒール」
 * 唱えるとHPをH50回復する
 */
public class Heal extends Magic {
    // 表示名
    public static final String DISPLAY_NAME = MagicConstants.Heal.DISPLAY_NAME;
    // 消費MP
    public static final int CONSUMPTION_MP = MagicConstants.Heal.CONSUMPTION_MP;
    // 回復量
    public static final int HEAL_HP = MagicConstants.Heal.HEAL_HP;

    /**
     * @param user  味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        enemy.healHp(HEAL_HP);
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
        return null;
    }

    /**
     * @return 最大ダメージ
     */
    public int getMaxDamage() {
        return MAX_DAMAGE;
    }

    /**
     * @return 最小ダメージ
     */
    public int getMinDamage() {
        return MIN_DAMAGE;
    }
}
