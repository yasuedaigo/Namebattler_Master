package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「パライズ」
 * 唱えると相手を麻痺状態にする
 * 麻痺の確率は20%
 */
public class Paralysis  extends Magic{
    
    public final static String DISPLAY_NAME = MagicConstants.Paralysis.DISPLAY_NAME;
    public final static int CONSUMPTION_MP = MagicConstants.Paralysis.CONSUMPTION_MP;
    public final static int PARALYSIS_RATE = MagicConstants.Paralysis.PARALYSIS_RATE;
    public final static int MAX_DAMAGE = MagicConstants.Paralysis.MAX_DAMAGE;
    public final static int MIN_DAMAGE = MagicConstants.Paralysis.MIN_DAMAGE;

    private Conditions GRANT_CONDITION = Conditions.PARALYSIS;  // 付与する状態異常


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

    public int getMaxDamage() {
        return MAX_DAMAGE;
    }

    public int getMinDamage() {
        return MIN_DAMAGE;
    }
}

