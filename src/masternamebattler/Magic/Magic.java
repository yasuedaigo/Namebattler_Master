package masternamebattler.Magic;

import java.util.concurrent.ThreadLocalRandom;

import masternamebattler.GameManager;
import masternamebattler.GlobalConstants;
import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;

/**
 * 魔法の抽象クラス
 */
public abstract class Magic {

    // 最大ダメージ
    public static final int MAX_DAMAGE = MagicConstants.Magic.MAX_DAMAGE;
    // 最小ダメージ
    public static final int MIN_DAMAGE = MagicConstants.Magic.MIN_DAMAGE;

    /**
     * 魔法を唱えられるかどうか
     * 
     * @param user 魔法を使用するプレイヤー
     * @return MPが足りていればtrue
     */
    public boolean canCast(Player user) {
        return user.getMp() >= getConsumptionMp();
    }

    /**
     * 魔法を唱えたときの処理
     * 
     * @param user  味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    public void cast(Player user, Player enemy) {
        // MPが足りない場合は魔法を唱えられない
        if (user.getMp() < getConsumptionMp()) {
            GameManager.consoleManager.addLogText(
                    String.format(MagicConstants.Magic.CANNOTCASTMESSAGE, user.getName(), getDisplayName()));
            return;
        }
        
        // MPが足りていればログを出力し、MPを消費する
        GameManager.consoleManager
                .addLogText(String.format(MagicConstants.Magic.CASTMESSAGE, user.getName(), getDisplayName()));
        user.setMp(user.getMp() - getConsumptionMp());
    }

    /*
     * @return 最大ダメージ
     */
    protected int getMaxDamage() {
        return MAX_DAMAGE;
    }

    /*
     * @return 最小ダメージ
     */
    protected int getMinDamage() {
        return MIN_DAMAGE;
    }

    /**
     * 与ダメージを計算する
     * 
     * @return MAX_DAMAGEとMIN_DAMAGEの範囲内の乱数
     */
    protected int calcDamage() {
        return ThreadLocalRandom.current().nextInt(getMinDamage(),
                getMaxDamage() + GlobalConstants.RANGE_INCLUSIVE_OFFSET);
    }

    /**
     * @return 消費MP
     */
    public abstract int getConsumptionMp();

    /**
     * @return 表示名
     */
    public abstract String getDisplayName();

    /**
     * @return 付与する状態異常
     */
    public abstract Conditions getGrantCondition();
}
