package masternamebattler.Tactics;

import java.util.List;
import java.util.stream.Collectors;

import masternamebattler.Party;
import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;

//体力が低い相手を狙う
public class AttackTactics extends Tactics{
    public static final String DISPLAY_NAME = TacticsConstants.AttackTactics.DISPLAY_NAME;

    public Player getTargetPlayer(Party party, Player attacker) {
        Teams enemyTeam = attacker.getTeam().getOpposite();
        List<Player> enemys = party.getMembers(enemyTeam).stream()
            .sorted((p1, p2) -> Integer.compare(p1.getHp(), p2.getHp()))
            .filter(player -> player.getIsLive())
            .collect(Collectors.toList());

        return enemys.get(0);
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
