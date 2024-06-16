package masternamebattler.Chara;

import java.util.Arrays;
import java.util.List;

import masternamebattler.GameConstants;
import masternamebattler.Magic.Fire;
import masternamebattler.Magic.Magic;
import masternamebattler.Magic.Thunder;
import masternamebattler.Tactics.Tactics;

/**
 * 職業のひとつ「魔法使い」
 *    ファイアとサンダーを使える
 */
public class Wizard extends Player {
    // 表示名
    public static final String DISPLAY_NAME = CharaConstants.Wizard.DISPLAY_NAME;

    /**
     * コンストラクタ
     * @param name プレイヤーの名前
     * @param team プレイヤーの所属するチーム
     * @param characterType 職業を表す列挙子
     * @param tactics 作戦を表す列挙子
     */
    public Wizard(String name,GameConstants.Teams team,CharacterType characterType,Tactics tactics) {
        super(name,team,characterType,tactics);
        this.useAbleMagics = Arrays.asList(
            new Fire(),
            new Thunder()
        );
    }

    /**
     * 名前からステータスを計算してセットする
     */
    @Override
    protected void setStatsu(String name){
        this.hp = calcStatus(this.name, CharaConstants.Wizard.MAX_HP, CharaConstants.Wizard.MIN_HP, CharaConstants.HP_INDEX);
        this.mp = calcStatus(this.name, CharaConstants.Wizard.MAX_MP, CharaConstants.Wizard.MIN_MP, CharaConstants.MP_INDEX);
        this.str = calcStatus(this.name, CharaConstants.Wizard.MAX_STR, CharaConstants.Wizard.MIN_STR, CharaConstants.STR_INDEX);
        this.def = calcStatus(this.name, CharaConstants.Wizard.MAX_DEF, CharaConstants.Wizard.MIN_DEF, CharaConstants.DEF_INDEX);
        this.agi = calcStatus(this.name, CharaConstants.Wizard.MAX_AGI, CharaConstants.Wizard.MIN_AGI, CharaConstants.AGI_INDEX);
        this.luck = calcStatus(this.name, CharaConstants.Wizard.MAX_LUCK, CharaConstants.Wizard.MIN_LUCK, CharaConstants.LUCK_INDEX);
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
            System.out.println(String.format(CharaConstants.PARALYSIS_MESSAGE, this.name));
            return;
        }
        // 使用できる魔法がある場合は、魔法を使用する
        if(canUseMagic()){
            Magic choicedMagic = choiceMagic();
            choicedMagic.cast(this, enemy);
            return;
        }
        // 魔法を使えない場合は通常攻撃を行う
        super.normalAttack();
    }
}