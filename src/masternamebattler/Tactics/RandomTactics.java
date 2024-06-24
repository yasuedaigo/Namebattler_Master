package masternamebattler.Tactics;

import masternamebattler.Party;
import masternamebattler.Chara.Player;

/**
 * 作戦のひとつ「バッチリがんばれ」
 * ランダムな敵をターゲットする
 */
public class RandomTactics extends Tactics {
    public static final String DISPLAY_NAME = TacticsConstants.RandomTactics.DISPLAY_NAME;

    /**
     * 攻撃対象を決定する
     * ランダムな敵をターゲットする
     * 
     * @param party  パーティ
     * @param player 攻撃するプレイヤー
     * @return 攻撃対象のプレイヤー
     */
    public Player getTargetPlayer(Party party, Player attacker) {
        return getRandomEnemy(party, attacker);
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
