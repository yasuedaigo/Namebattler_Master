package masternamebattler.chara.Magic;

import masternamebattler.chara.Player;
import masternamebattler.chara.Conditions;

/**
 * 魔法のひとつ「パライズ」
 * 唱えると相手を麻痺状態にする
 * 麻痺の確率は20%
 */
public class Paralysis  extends Magic{
    public static final int PARALYSIS_RATE = 20;  // 麻痺の確率
    public String DISPLAY_NAME = "パライズ";
    public Conditions GRANT_CONDITION = Conditions.PARALYSIS;  // 付与する状態異常
    public int CONSUMPTION_MP = 10;

    /**
     * 敵に麻痺を付与する
     * @param user 味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        enemy.setCondisions(Conditions.PARALYSIS);
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
        return GRANT_CONDITION;
    }
}

