package masternamebattler;

import java.util.ArrayList;
import java.util.List;

import masternamebattler.Chara.Player;
import masternamebattler.GameConstants.Teams;

public class Party {
    private List<Player> beforMembers;
    private List<Player> members;
    private Teams team;

    public Party(Teams team) {
        this.team = team;
        beforMembers = new ArrayList<>();
        members = new ArrayList<>();
    }

    public Party() {
        beforMembers = new ArrayList<>();
        members = new ArrayList<>();
    }

    List<Player> getMembers() {
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
}