package masternamebattler.Tactics;

import masternamebattler.Party;
import masternamebattler.Chara.Player;
import masternamebattler.Tactics.TacticsConstants;
import masternamebattler.Tactics.TacticsConstants;

public class RandomTactics extends Tactics{
    public static final String DISPLAY_NAME = TacticsConstants.RandomTactics.DISPLAY_NAME;
    
    public Player getTargetPlayer(Party party, Player player) {
        return getRandomEnemy(party, player.getTeam());
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
