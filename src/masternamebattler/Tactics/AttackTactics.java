package masternamebattler.Tactics;

import java.util.List;
import java.util.stream.Collectors;

import masternamebattler.Party;
import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;


/*
 * 作戦のひとつ「ガンガンいこうぜ」
 * HPが一番少ないプレイヤーをターゲットする
 */
public class AttackTactics extends Tactics{
    // 表示名
    public static final String DISPLAY_NAME = TacticsConstants.AttackTactics.DISPLAY_NAME;

    /**
     * 攻撃対象を決定する
     * HPが一番少ないプレイヤーをターゲットする
     * @param party パーティ
     * @param attacker 攻撃するプレイヤー
     * @return 攻撃対象のプレイヤー
     */
    public Player getTargetPlayer(Party party, Player attacker) {
        // 敵チームのプレイヤーを取得
        List<Player> enemys = party.getMembers(attacker.getTeam().getOpposite());
        // 取得したプレイヤーをHPが降順になるように並び替える
        enemys.sort((p1, p2) -> Integer.compare(p1.getHp(), p2.getHp()));
        // 最初のプレイヤーを返す（HPが一番多い）
        return enemys.get(0);
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
