package masternamebattler.Tactics;

import java.util.List;

import masternamebattler.Party;
import masternamebattler.Chara.Player;

/**
 * 作戦のひとつ「バランスよく」
 * HPが一番多いプレイヤーをターゲットする
 */
public class BalanceTactics extends Tactics {
    // 表示名
    public static final String DISPLAY_NAME = TacticsConstants.BalanceTactics.DISPLAY_NAME;

    /**
     * 攻撃対象を決定する
     * HPが一番多いプレイヤーをターゲットする
     * 
     * @param party    パーティ
     * @param attacker 攻撃するプレイヤー
     * @return 攻撃対象のプレイヤー
     */
    public Player getTargetPlayer(Party party, Player attacker) {
        // 敵チームのプレイヤーを取得
        List<Player> enemys = party.getMembers(attacker.getTeam().getOpposite());

        // 取得したプレイヤーをHPが昇順になるように並び替える
        enemys.sort((p1, p2) -> Integer.compare(p2.getHp(), p1.getHp()));
        
        // 最初のプレイヤーを返す（HPが一番少ない）
        return enemys.get(0);
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
