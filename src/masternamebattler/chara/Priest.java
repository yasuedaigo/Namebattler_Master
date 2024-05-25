package masternamebattler.Chara;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import masternamebattler.GameConstants;
import masternamebattler.GlobalConstants;
import masternamebattler.Magic.Heal;
import masternamebattler.Magic.Magic;
import masternamebattler.Magic.Paralysis;
import masternamebattler.Magic.Poison;

/**
 * 僧侶のクラス
 * ヒールとパライズとポイズンを使える
 */
public class Priest extends Player {
    public static final String DISPLAY_NAME = CharaConstants.Priest.DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param team プレイヤーの所属するチーム
     */
    public Priest(String name,GameConstants.Teams team) {
        super(name,team);
    }

    /**
     * 僧侶が使用可能な魔法をリストで持つ
     */
    public List<Magic> usableMagics = Arrays.asList(
        new Paralysis(),
        new Poison(),
        new Heal()
    );

    /**
     * ステータスを計算してセットする
     */
    @Override
    public void setStatsu(String name){
        this.hp = calcStatus(this.name, CharaConstants.Priest.MAX_HP, CharaConstants.Priest.MIN_HP, CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Priest.MAX_MP, CharaConstants.Priest.MIN_MP, CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Priest.MAX_STR, CharaConstants.Priest.MIN_STR, CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Priest.MAX_DEF, CharaConstants.Priest.MIN_DEF, CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Priest.MAX_AGI, CharaConstants.Priest.MIN_AGI, CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Priest.MAX_LUCK, CharaConstants.Priest.MIN_LUCK, CharaConstants.LUCK_INDEX);
    }

    /**
     * @return 僧侶の職業名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }

    /**
     * 攻撃処理
     * 麻痺判定を行い、麻痺した場合は攻撃できない
     * 魔法を使用可能な場合は、使用可能な魔法を使うもしくは通常攻撃をする
     * 使用できる魔法がない場合は通常攻撃を行う
     * @param enemy 攻撃対象のプレイヤー
     */
    @Override
    public void attack(Player enemy) {
        if(isIncapacitationForParalysis()){
            System.out.println(String.format(CharaConstants.PARALYSIS_MESSAGE, this.name));
            return;
        }
        Magic choicedMagic = null;
        if(canUseMagic()){
            choicedMagic = choiceMagic(enemy);
        }

        // 使用できる魔法がある場合は、通常攻撃を行うか魔法を使用する。通常攻撃を行う確率は50%
        if(choicedMagic != null) {
            Random random = new Random();
            if (random.nextInt(2) == 1) {
                super.normalAttack(enemy);
                return;
            }
            choicedMagic.cast(this, enemy);
            return;
        }
        super.normalAttack(enemy);
    }

    /**
     * 使用する魔法を選択する
     * @param enemy 攻撃対象のプレイヤー
     * @return 使用可能な魔法の中からランダムで魔法を返す
     */
    public Magic choiceMagic(Player enemy){

        //使用可能な魔法をリストに加える
        List<Magic> availableMagics = new ArrayList<>();
        for (Magic magic : usableMagics) {
            if (isAvailableMagic(magic, enemy)) {
                availableMagics.add(magic);
            }
        }

        //使用可能な魔法がある場合は、その中からランダムで魔法を選択する
        if (!availableMagics.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(availableMagics.size());
            return availableMagics.get(index);
        }
        return null;
    }

    /**
     * 魔法が使用できるかどうか
     * @return 現在のMPで使用できる魔法がある場合はtrue
     */
    public boolean canUseMagic() {
        for (Magic magic : usableMagics) {
            if (magic.getConsumptionMp() <= this.mp) {
                return true;
            }
        }
        return false;
    }

    /**
     * 魔法が使用に適しているか判定する
     * MPが足りていて、状態異常を付与する魔法の場合、同じ状態異常に敵がかかっていない場合にtrueを返す
     * @param magic 選択した魔法
     * @param enemy 攻撃対象のプレイヤー
     * @return 使用に適している場合はtrue
     */
    public boolean isAvailableMagic(Magic magic, Player enemy) {
        if (magic.getConsumptionMp() > this.mp) {
            return false;
        }
        if (enemy.getCondition() != null && magic.getGrantCondition() != null) {
            return false;
        }
        return true;
    }
}