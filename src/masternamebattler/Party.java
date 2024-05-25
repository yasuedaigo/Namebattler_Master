
package masternamebattler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;
import masternamebattler.Condition.Conditions;
import java.util.Set;


public class Party {
    private List<Player> beforMembers;
    private List<Player> members;
    private Teams team;

    public Party() {
        beforMembers = new ArrayList<>();
        members = new ArrayList<>();
    }

    public List<Player> getMembers() {
        return members;
    }

    /**
     * パーティーにプレイヤーを追加する
     * @param player 追加するプレイヤー
     */
    public void appendPlayer(Player player) {
        members.add(player);
    }

    /**
     * パーティーからプレイヤーを離脱させる
     * @param player 離脱させるプレイヤー
     */
    public void removePlayer(Player player) {
        members.remove(player);
    }

    public void setBeforMembers() {
        beforMembers = members;
    }

    public void sortPlayerListForAgi() {
        members.sort((player1, player2) -> player2.getAgi() - player1.getAgi());
    }

    public void showMembers() {
        System.out.println("------------------------------");
        System.out.println("現在の状況");
        for (Player player : members) {
            player.showInfo();
        }
        System.out.println("------------------------------");
    }

    /**
     * 次に攻撃するプレイヤーを取得する
     * @return 次に攻撃するプレイヤー
     */
    public Player getAttacker() {
        for (Player player : members) {
            if (!player.getIsAttacked()) {
                return player;
            }
        }
        return null;
    }

    /**
     * 次に防御するプレイヤーを取得する
     * @param attacker 攻撃するプレイヤー
     * @return 防御するプレイヤー
     */
    public Player getDefender(Player attacker) {
        List<Player> oppositeTeamMembers = members.stream()
                .filter(player -> player.getTeam() != attacker.getTeam() && player.getIsLive())
                .collect(Collectors.toList());
        Random random = new Random();
        return oppositeTeamMembers.get(random.nextInt(oppositeTeamMembers.size()));
    }

    /**
     * 毒ダメージを処理する
     */
    public void poisonDamage() {
        for (Player player : members) {
            if(player.getCondition() == Conditions.POISON){
                player.poisonDamage();
            }
            checkGameEnd();
        }
    }

    /**
     * ターンの終了をチェックする
     * @return ターンが終了している場合はtrue
     */
    public boolean checkTurnEnd() {
        for (Player player : members) {
            if (player.getIsLive() && !player.getIsAttacked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * ゲームの終了をチェックする
     * @return ゲームが終了している場合はtrue
     */
    public boolean checkGameEnd() {
        // 生きているプレイヤーのみを抽出
        List<Player> livePlayers = members.stream()
                .filter(Player::getIsLive)
                .collect(Collectors.toList());
        Set<GameConstants.Teams> uniqueTeams = livePlayers.stream()
                .map(Player::getTeam)
                .collect(Collectors.toSet());
    
        // チームの種類が1つ以下であればゲーム終了
        return uniqueTeams.size() <= 1;
        
    }

    /**
     * 勝者を取得する
     * @return 勝者のプレイヤー
     */
    public Teams getWinner() {
        for (Player player : members) {
            if (player.getIsLive()) {
                return player.getTeam(); // 生きているプレイヤーのチームを返す
            }
        }
        return null; // 生きているプレイヤーがいない場合は null を返す
    }
}