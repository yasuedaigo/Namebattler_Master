package masternamebattler.Magic;

import java.util.concurrent.ThreadLocalRandom;

import masternamebattler.GameManager;
import masternamebattler.GlobalConstants;
import masternamebattler.Chara.Player;
import masternamebattler.Condition.Conditions;
import masternamebattler.Console.ConsoleManager;

/**
 * 魔法の抽象クラス
 */
public abstract class Magic{
    
    /**
     * 魔法を唱えられるかどうか
     * @param user 味方プレイヤー
     * @return MPが足りていればtrue
     */
    public boolean canCast(Player user){
        return user.mp >= getConsumptionMp();
    }

    /**
     * 魔法を唱えたときの処理
     * MPが足りなければ唱えられない
     * MPが足りるならメッセージを表示しMPを消費する
     * @param user 味方プレイヤー
     * @param enemy 敵プレイヤー
     */
    public void cast(Player user, Player enemy){
        if(user.mp < getConsumptionMp()) {
            GameManager.consoleManager.addLogText(String.format(MagicConstants.Magic.CANNOTCASTMESSAGE,user.getName(),getDisplayName()));
            return;
        }
        GameManager.consoleManager.addLogText(String.format(MagicConstants.Magic.CASTMESSAGE,user.getName(),getDisplayName()));
        user.mp -= getConsumptionMp();
    }

    /**
     * 与ダメージを計算する
     * @return MAX_DAMAGEとMIN_DAMAGEから計算された与ダメージ
     */
    protected int  calcDamage() {
        return ThreadLocalRandom.current().nextInt(getMinDamage(), getMaxDamage() + GlobalConstants.RANGE_INCLUSIVE_OFFSET);
    }

    public abstract int getMaxDamage();

    public abstract int getMinDamage();

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
