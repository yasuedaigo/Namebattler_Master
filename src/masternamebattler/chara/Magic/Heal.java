package masternamebattler.chara.Magic;

import masternamebattler.chara.Conditions;
import masternamebattler.chara.Player;

/**
 * 魔法のひとつ「ヒール」
 * 唱えるとHPをH50回復する
 */
public class Heal  extends Magic{
    public static final String HEAL_MESSAGE = "%sは%s回復した";
    public String DISPLAY_NAME = "ヒール";
    public int CONSUMPTION_MP = 20;
    public int HEAL_HP = 50;  // 回復量

    /**
     * @param user 味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        user.hp += HEAL_HP;
        System.out.println(String.format(HEAL_MESSAGE, user.name, HEAL_HP));
    }

    /**
     * @return 表示名
     */
    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    /**
     * @return 消費MP
     */
    @Override
    public int getConsumptionMp() {
        return CONSUMPTION_MP;
    }

    /**
     * @return 付与する状態異常
     */
    @Override
    public Conditions getGrantCondition() {
        return null;
    }
}
