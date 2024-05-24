package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「ファイア」
 * 10～30のダメージを与える
 */
public class Fire extends Magic{
    public static final String DISPLAY_NAME = MagicConstants.Fire.DISPLAY_NAME;
    public static final int CONSUMPTION_MP = MagicConstants.Fire.CONSUMPTION_MP;
    public static final int MAX_DAMAGE = MagicConstants.Fire.MAX_DAMAGE;
    public static final int MIN_DAMAGE = MagicConstants.Fire.MIN_DAMAGE;

    /**
     * 唱えたときの処理
     * ダメージを計算し、敵プレイヤーに与える
     * MPを消費する
     * @param user 魔法を使用するプレイヤー
     * @param enemy 魔法の対象となる敵プレイヤー
     */
    @Override
    public void cast(Player user, Player enemy) {
        super.cast(user, enemy);
        int damage = calcDamage();
        enemy.damage(damage);
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
        return MAX_DAMAGE;
    }

    public int getMinDamage() {
        return MIN_DAMAGE;
    }
}