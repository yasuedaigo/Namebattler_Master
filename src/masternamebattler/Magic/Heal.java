package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「ヒール」
 * 唱えるとHPをH50回復する
 */
public class Heal  extends Magic{
    public static final String DISPLAY_NAME = MagicConstants.Heal.DISPLAY_NAME;
    public static final int CONSUMPTION_MP = MagicConstants.Heal.CONSUMPTION_MP;
    public static final int HEAL_HP = MagicConstants.Heal.HEAL_HP;

    /**
     * @param user 味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        user.hp += HEAL_HP;
        System.out.println(String.format(MagicConstants.Heal.HEAL_MESSAGE, user.name, HEAL_HP));
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

    public int getMaxDamage() {
        return 0;
    }

    public int getMinDamage() {
        return 0;
    }
}
