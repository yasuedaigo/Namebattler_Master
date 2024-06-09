package masternamebattler.Tactics;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import masternamebattler.Party;
import masternamebattler.Chara.CharacterType;
import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;

public class AntiMagicTactics extends Tactics{
    public static final String DISPLAY_NAME = TacticsConstants.AntiMagicTactics.DISPLAY_NAME;
    private List<CharacterType> priorityList = List.of(CharacterType.PRIEST, CharacterType.WIZARD);
        
    @Override
    public Player getTargetPlayer(Party party, Player attacker) {
        Teams  enemyTeam = attacker.getTeam().getOpposite();
        List<Player> enemys = party.getMembers(enemyTeam);
                

        List<Player> priorityTargets = enemys.stream()
            .filter(player -> priorityList.contains(player.getCharacterType()) && player.getIsLive())
            .collect(Collectors.toList());

        if (!priorityTargets.isEmpty()) {
            return priorityTargets.get(new Random().nextInt(priorityTargets.size()));
        }

        return enemys.get(new Random().nextInt(enemys.size()));
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
