package masternamebattler.Magic;

import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法のひとつ「サンダー」
 * 唱えると35～15のダメージを与える
 */
public class Thunder extends Magic{
    //表示名
    public final static String DISPLAY_NAME = MagicConstants.Thunder.DISPLAY_NAME;
    //消費MP
    public final static int CONSUMPTION_MP = MagicConstants.Thunder.CONSUMPTION_MP;
    //最大ダメージ
    public final static int MAX_DAMAGE = MagicConstants.Thunder.MAX_DAMAGE;
    //最小ダメージ
    public final static int MIN_DAMAGE = MagicConstants.Thunder.MIN_DAMAGE;

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
        enemy.applyDamage(damage);
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