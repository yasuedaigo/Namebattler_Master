package masternamebattler.chara.Magic;

import masternamebattler.chara.Conditions;
import masternamebattler.chara.Player;

/**
 * 魔法のひとつ「ポイズン」
 * 唱えると相手を毒状態にする
 * 毒ダメージは10~30
 */
public class Poison extends Magic{
    public static final int POISON_MAX_DAMAGE = 30;
    public static final int POISON_MIN_DAMAGE = 10;

    public String DISPLAY_NAME = "ポイズン";
    public Conditions GRANT_CONDITION = Conditions.POISON;  // 付与する状態異常
    public int CONSUMPTION_MP = 10;


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
