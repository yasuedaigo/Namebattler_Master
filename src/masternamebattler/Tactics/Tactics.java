package masternamebattler.Tactics;

import masternamebattler.Chara.Player;

import java.util.List;
import java.util.stream.Collectors;

import masternamebattler.GameConstants.Teams;
import masternamebattler.Party;
import java.util.Random;
import masternamebattler.Chara.CharacterType;

/**
 * 作戦の抽象クラス
 */
public abstract class Tactics {
    // 表示名
    public static final String DISPLAY_NAME = TacticsConstants.Tactics.DISPLAY_NAME;

    // 攻撃対象を決定する
    public abstract Player getTargetPlayer(Party party, Player attacker);

    // 優先する職業のリスト
    public List<CharacterType> priorityList;

    /**
     * 敵プレイヤーからランダムにプレイヤーを取得する
     * 
     * @param party
     * @param team
     * @return ランダムな敵プレイヤー
     */
    public Player getRandomEnemy(Party party, Player attacker) {
        Random random = new Random();
        List<Player> enemys = party.getMembers(attacker.getTeam().getOpposite());

        return enemys.get(random.nextInt(enemys.size()));
    }

    /**
     * パーティのプレイヤーの中でHPが一番少ない自チームのプレイヤーを取得する
     * 
     * @param party
     * @param team
     * @return HPが一番少ない自チームのプレイヤー
     */
    public Player getPlayerLowestHP(Party party, Teams team) {
        // 自チームのプレイヤーを取得し、HPが昇順になるように並び替える
        List<Player> players = party.getMembers(team).stream()
                .sorted((p1, p2) -> Integer.compare(p1.getHp(), p2.getHp()))
                .collect(Collectors.toList());

        // 最初のプレイヤーを返す（HPが一番少ない）
        return players.get(0);
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
