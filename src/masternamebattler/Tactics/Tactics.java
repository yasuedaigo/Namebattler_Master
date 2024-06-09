package masternamebattler.Tactics;

import masternamebattler.Chara.Player;

import java.util.List;
import java.util.stream.Collectors;

import masternamebattler.GameConstants.Teams;
import masternamebattler.Party;
import java.util.Random;
import masternamebattler.Chara.CharacterType;

public abstract class Tactics {
    public static final String DISPLAY_NAME = TacticsConstants.Tactics.DISPLAY_NAME;
    public abstract Player getTargetPlayer(Party party,Player attacker);
    public List<CharacterType> priorityList;

    public Player getRandomEnemy(Party party, Teams team) {
        Random random = new Random();
        Teams enemyTeam = team.getOpposite();
        List<Player> enemys = party.getMembers(enemyTeam).stream()
            .collect(Collectors.toList());

        return enemys.get(random.nextInt(enemys.size()));
    }

    public Player getPlayerLowestHP(Party party, Teams team) {
        List<Player> players =  party.getMembers(team).stream()
            .sorted((p1, p2) -> Integer.compare(p1.getHp(), p2.getHp()))
            .collect(Collectors.toList());

        return players.get(0);
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
