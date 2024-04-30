package masternamebattler.chara.Magic;

import java.util.Random;
import masternamebattler.Constants;
import masternamebattler.chara.Conditions;
import masternamebattler.chara.Player;

/**
 * 魔法のひとつ「サンダー」
 * 唱えると35～15のダメージを与える
 */
public class Thunder extends Magic{
    public String DISPLAY_NAME = "サンダー";
    public int CONSUMPTION_MP = 20;
    public int MAX_DAMAGE = 35;
    public int MIN_DAMAGE = 15;

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
     * 与ダメージを計算する
     * @return MAX_DAMAGEとMIN_DAMAGEから計算された与ダメージ
     */
    private int  calcDamage() {
        Random random = new Random();
        return random.nextInt(MAX_DAMAGE - MIN_DAMAGE + Constants.RANGE_INCLUSIVE_OFFSET) + MIN_DAMAGE;
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