package masternamebattler.Tactics;

import java.util.List;
import java.util.stream.Collectors;

import masternamebattler.Party;
import masternamebattler.Chara.Player;
import masternamebattler.Tactics.TacticsConstants;


//体力が高い相手を狙う
public class BalanceTactics extends Tactics{
    public static final String DISPLAY_NAME = TacticsConstants.BalanceTactics.DISPLAY_NAME;

    public Player getTargetPlayer(Party party, Player attacker) {
        List<Player> enemys = party.getMembers().stream()
            .filter(player -> player.getTeam() != attacker.getTeam() && player.getIsLive())
            .sorted((p1, p2) -> Integer.compare(p2.getHp(), p1.getHp()))
            .collect(Collectors.toList());

        return enemys.get(0);
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
