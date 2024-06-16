package masternamebattler.Tactics;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import masternamebattler.Party;
import masternamebattler.Chara.CharacterType;
import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;

/*
 * 作戦のひとつ「魔法対策」
 * 魔法を使うキャラクターを優先してターゲットする
 */
public class AntiMagicTactics extends Tactics{
    // 表示名
    public static final String DISPLAY_NAME = TacticsConstants.AntiMagicTactics.DISPLAY_NAME;
    // 優先するキャラクターのリスト
    private List<CharacterType> priorityList = List.of(CharacterType.PRIEST, CharacterType.WIZARD);
   
    /**
     * 攻撃対象を決定する
     * 魔法を使うキャラクターを優先してターゲットする
     * @param party パーティ
     * @param attacker 攻撃するプレイヤー
     * @return 攻撃対象のプレイヤー
     */
    @Override
    public Player getTargetPlayer(Party party, Player attacker) {
        // 敵チームのプレイヤーを取得
        List<Player> enemys = party.getMembers(attacker.getTeam().getOpposite());
                
        // 取得したプレイヤーの中で、優先リストに含まれている職業のプレイヤーのみ抽出する
        List<Player> priorityTargets = enemys.stream()
            .filter(player -> priorityList.contains(player.getCharacterType()))
            .collect(Collectors.toList());

        //抽出したプレイヤーがいる場合、その中からランダムで選択する
        if (!priorityTargets.isEmpty()) {
            return priorityTargets.get(new Random().nextInt(priorityTargets.size()));
        }

        // 優先リストに含まれるプレイヤーがいない場合、敵プレイヤーからランダムで選択する
        return enemys.get(new Random().nextInt(enemys.size()));
    }

    /**
     * @return 表示名
     */
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
