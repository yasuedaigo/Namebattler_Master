package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「ポイズン」
 * 唱えると相手を毒状態にする
 * 毒ダメージは10~30
 */
public class Poison extends Magic{
    public final static String DISPLAY_NAME = MagicConstants.Poison.DISPLAY_NAME;
    public final static int CONSUMPTION_MP = MagicConstants.Poison.CONSUMPTION_MP;
    public final static int POISON_MAX_DAMAGE = MagicConstants.Poison.POISON_MAX_DAMAGE;
    public final static int POISON_MIN_DAMAGE = MagicConstants.Poison.POISON_MIN_DAMAGE;
    public final static int MAX_DAMAGE = MagicConstants.Poison.MAX_DAMAGE;
    public final static int MIN_DAMAGE = MagicConstants.Poison.MIN_DAMAGE;

    private Conditions GRANT_CONDITION = Conditions.POISON;  // 付与する状態異常


    /**
     * 敵に毒を付与する
     * @param user 味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        enemy.setCondisions(Conditions.POISON);
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
