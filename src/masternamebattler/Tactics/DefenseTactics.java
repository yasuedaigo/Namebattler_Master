package masternamebattler.Tactics;

import masternamebattler.Party;
import masternamebattler.Chara.Player;
import masternamebattler.Tactics.TacticsConstants;

//回復重視
public class DefenseTactics extends Tactics{
    public static final String DISPLAY_NAME = TacticsConstants.DefenseTactics.DISPLAY_NAME;


    public Player getTargetPlayer(Party party, Player player) {
        if (player.canUseHeal()) {
            return getPlayerLowestHP(party, player.getTeam());
        } else {
            return getRandomEnemy(party, player.getTeam().getOpposite());
        }
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}