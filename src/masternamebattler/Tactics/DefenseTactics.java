package masternamebattler.Tactics;

import masternamebattler.Party;
import masternamebattler.Chara.Player;
import masternamebattler.Tactics.TacticsConstants;

/**
 * 作戦のひとつ「回復重視」
 * 攻撃するプレイヤーがヒールを使える場合は、HPが一番少ない味方をターゲットする
 */
public class DefenseTactics extends Tactics{
    // 表示名
    public static final String DISPLAY_NAME = TacticsConstants.DefenseTactics.DISPLAY_NAME;


    /**
     * 攻撃対象を決定する
     * 攻撃するプレイヤーがヒールを使える場合は、HPが一番少ない味方をターゲットする
     * 使えない場合は、ランダムな敵をターゲットする
     * @param party パーティ
     * @param player 攻撃するプレイヤー
     * @return 攻撃対象のプレイヤー
     */
    public Player getTargetPlayer(Party party, Player attacker) {
        // プレイヤーがヒールを使える場合は、HPが一番少ない味方をターゲットする。それ以外はランダムな敵をターゲットする
        if (attacker.canUseHeal()) {
            return getPlayerLowestHP(party, attacker.getTeam());
        } else {
            return getRandomEnemy(party, attacker);
        }
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}