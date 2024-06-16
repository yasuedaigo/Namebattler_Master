
package masternamebattler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;
import masternamebattler.Condition.Conditions;

/**
 * パーティーを管理するクラス
 */
public class Party {
    // パーティーのメンバーリスト
    private List<Player> members;

    /**
     * コンストラクタ
     * メンバーリストを初期化する
     */
    public Party() {
        members = new ArrayList<>();
    }

    /**
     * @return メンバーリスト
     */
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
     * パーティーのメンバーリストをAGIの降順でソートする
     */
    public void sortPlayerListForAgi() {
        members.sort((player1, player2) -> player2.getAgi() - player1.getAgi());
    }
    
    /**
     * 攻撃するプレイヤーを取得する
     * @return 攻撃するプレイヤー
     */
    public Player getAttacker() {
        //membersの前から撃済みでないプレイヤーを取得する
        for (Player player : members) {
            if (!player.getIsAttacked()) {
                return player;
            }
        }
        return null;
    }

    /**
     * 防御するプレイヤーを取得する
     * @param attacker 攻撃するプレイヤー
     * @return 防御するプレイヤー
     */
    public Player getDefender(Player attacker) {
        List<Player> oppositeTeamMembers = getMembers(attacker.getTeam().getOpposite());
        Random random = new Random();
        return oppositeTeamMembers.get(random.nextInt(oppositeTeamMembers.size()));
    }

    /**
     * 毒ダメージ処理
     */
    public void poisonDamage() {
        // 毒状態のプレイヤーに毒ダメージを与える
        for (Player player : members) {
            if(player.getCondition() == Conditions.POISON){
                player.poisonDamage();
            }
            //ゲーム終了判定を行う
            checkGameEnd();
        }
    }

    /**
     * ターンの終了をチェックする
     * @return ターンが終了している場合はtrue
     */
    public boolean checkTurnEnd() {
        //生存状態のすべてのプレイヤーが攻撃済みであればターン終了
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
        //プレイヤーチームとエネミーチームのどちらかが全滅していればゲーム終了
        List<Player> playerTeamList = getMembers(Teams.PLAYER);
        List<Player> enemyTeamList = getMembers(Teams.ENEMY);
        if(playerTeamList.size() < 1 || enemyTeamList.size() < 1){
            return true;
        }
        return false;
    }

    /**
     * 勝者を取得する
     * @return 勝者のプレイヤー
     */
    public Teams getWinner() {
        //生存しているプレイヤーのチームを取得する
        for (Player player : members) {
            if (player.getIsLive()) {
                return player.getTeam();
            }
        }
        return null;
    }

    /**
     * 受け取ったチームのメンバーリストを取得する
     */
    public List<Player> getMembers(Teams team) {
        return members.stream()
                .filter(player -> player.getTeam() == team)
                .filter(Player::getIsLive)
                .collect(Collectors.toList());
    }
    
    /**
     * チームのメンバーを取得する
     * @param team チーム
     * @param index メンバーのインデックス
     * @return チームのメンバー
     */
    public Player getPlayer(Teams team, int index){
        List<Player> teamMembers = getMembers(team);
        if (index < teamMembers.size()) {
            return teamMembers.get(index);
        }
        return null;
    }

    /**
     * 攻撃済みフラグをリセットする
     */
    public void resetIsAttacked(){
        for (Player player : members) {
            player.setIsAttacked(false);
        }
    }
}