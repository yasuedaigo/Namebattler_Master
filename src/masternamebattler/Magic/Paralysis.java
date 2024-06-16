package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;
import masternamebattler.GameConstants;

/**
 * 魔法のひとつ「パライズ」
 */
public class Paralysis  extends Magic{
    //表示名
    public final static String DISPLAY_NAME = MagicConstants.Paralysis.DISPLAY_NAME;
    //消費MP
    public final static int CONSUMPTION_MP = MagicConstants.Paralysis.CONSUMPTION_MP;
    //麻痺の確率
    public final static int PARALYSIS_RATE = GameConstants.PARALYSIS_RATE;
    //最大ダメージ
    public final static int MAX_DAMAGE = MagicConstants.Paralysis.MAX_DAMAGE;
    //最小ダメージ
    public final static int MIN_DAMAGE = MagicConstants.Paralysis.MIN_DAMAGE;

    // 付与する状態異常
    private Conditions GRANT_CONDITION = Conditions.PARALYSIS;


    /**
     * 魔法を唱えたときの処理
     * 麻痺を付与する
     * @param user 味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        enemy.setCondition(Conditions.PARALYSIS);
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

    protected int getMaxDamage() {
        return MAX_DAMAGE;
    }

    protected int getMinDamage() {
        return MIN_DAMAGE;
    }
}

