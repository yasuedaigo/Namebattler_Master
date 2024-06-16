package masternamebattler.Chara;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import masternamebattler.GameConstants;
import masternamebattler.GameManager;
import masternamebattler.Magic.Heal;
import masternamebattler.Magic.Magic;
import masternamebattler.Magic.Paralysis;
import masternamebattler.Magic.Poison;
import masternamebattler.Tactics.Tactics;

/**
 *  職業のひとつ「僧侶」
 *     ヒールとポイズンとパライズの魔法を使える
 */
public class Priest extends Player {
    // 表示名
    public static final String DISPLAY_NAME = CharaConstants.Priest.DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param name プレイヤーの名前
     * @param team プレイヤーの所属するチーム
     * @param characterType 職業を表す列挙子
     * @param tactics 作戦を表す列挙子
     */
    public Priest(String name,GameConstants.Teams team,CharacterType characterType,Tactics tactics) {
        super(name,team,characterType,tactics);
        this.useAbleMagics = Arrays.asList(
            new Paralysis(),
            new Poison(),
            new Heal()
        );
    }

    /**
     * 名前からステータスを計算してセットする
     */
    @Override
    protected void setStatsu(String name){
        this.hp = calcStatus(this.name, CharaConstants.Priest.MAX_HP, CharaConstants.Priest.MIN_HP, CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Priest.MAX_MP, CharaConstants.Priest.MIN_MP, CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Priest.MAX_STR, CharaConstants.Priest.MIN_STR, CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Priest.MAX_DEF, CharaConstants.Priest.MIN_DEF, CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Priest.MAX_AGI, CharaConstants.Priest.MIN_AGI, CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Priest.MAX_LUCK, CharaConstants.Priest.MIN_LUCK, CharaConstants.LUCK_INDEX);
    }

    /**
     * @return 職業の表示名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }

    /**
     * 攻撃処理
     * @param enemy 攻撃対象のプレイヤー
     */
    @Override
    public void attack() {
        // 麻痺判定を行い、麻痺した場合は攻撃できない
        if(isIncapacitationForParalysis()){
            GameManager.consoleManager.addLogText(String.format(CharaConstants.PARALYSIS_MESSAGE, this.name));
            return;
        }

        Magic choicedMagic = null;
        // 使用できる魔法がある場合は、魔法を選択する
        if(canUseMagic()){
            choicedMagic = choiceMagic();
        }

        if(choicedMagic != null) {
            //魔法を使用する
            choicedMagic.cast(this, enemy);
            return;
        }

        //使用できる魔法がない場合は通常攻撃を行う
        super.normalAttack();
    }
}