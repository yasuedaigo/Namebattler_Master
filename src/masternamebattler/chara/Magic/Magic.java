package masternamebattler.chara.Magic;

import masternamebattler.chara.Conditions;
import masternamebattler.chara.Player;

/**
 * 魔法の抽象クラス
 */
public abstract class Magic{
    public static final String CANNOTCASTMESSAGE = "%sは%sを唱えられなかった！";
    public static final String CASTMESSAGE = "%sは%sを唱えた！";

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
            System.out.println(String.format(CANNOTCASTMESSAGE,user.getName(),getDisplayName()));
            return;
        }
        System.out.println(String.format(CASTMESSAGE,user.getName(),getDisplayName()));
        user.mp -= getConsumptionMp();
    }

    /**
     * @return 表示名
     */
    public abstract String getDisplayName();

    /**
     * @return 消費MP
     */
    public abstract int getConsumptionMp();

    /**
     * @return 付与する状態異常
     */
    public abstract Conditions getGrantCondition();
}
