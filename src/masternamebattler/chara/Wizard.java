package masternamebattler.Chara;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import masternamebattler.GameConstants;
import masternamebattler.GlobalConstants;
import masternamebattler.Console.ConsoleManager;
import masternamebattler.Magic.Fire;
import masternamebattler.Magic.Magic;
import masternamebattler.Magic.Thunder;
import masternamebattler.Tactics.Tactics;

/**
 * 魔法使いのクラス
 * ファイアーとサンダーを使える
 */
public class Wizard extends Player {
    // 魔法使いのステータスの最大値、最小値
    public static final String DISPLAY_NAME = CharaConstants.Wizard.DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param team プレイヤーの所属するチーム
     */
    public Wizard(String name,GameConstants.Teams team,CharacterType characterType,Tactics tactics) {
        super(name,team,characterType,tactics);
    }

    /**
     * 魔法使いが使用可能な魔法をリストで持つ
     */
    public List<Magic> usableMagics = Arrays.asList(
        new Fire(),
        new Thunder()
    );

    /**
     * ステータスを計算してセットする
     */
    @Override
    public void setStatsu(String name){
        this.hp = calcStatus(this.name, CharaConstants.Wizard.MAX_HP, CharaConstants.Wizard.MIN_HP, CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Wizard.MAX_MP, CharaConstants.Wizard.MIN_MP, CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Wizard.MAX_STR, CharaConstants.Wizard.MIN_STR, CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Wizard.MAX_DEF, CharaConstants.Wizard.MIN_DEF, CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Wizard.MAX_AGI, CharaConstants.Wizard.MIN_AGI, CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Wizard.MAX_LUCK, CharaConstants.Wizard.MIN_LUCK, CharaConstants.LUCK_INDEX);
    }

    /**
     * @return 魔法使いの職業名
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
        if(canUseMagic()){
            Magic choicedMagic = choiceMagic(enemy);
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
        List<Magic> availableMagics = new ArrayList<>();
        for (Magic magic : usableMagics) {
            if (isAvailableMagic(magic, enemy)) {
                availableMagics.add(magic);
            }
        }

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
        if (magic.getGrantCondition() != null && magic.getGrantCondition() == enemy.getCondition()) {
            return false;
        }
        if (magic.getGrantCondition() == null) {
            return true;
        }
        return true;
    }
}