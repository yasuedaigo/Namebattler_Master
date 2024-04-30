package masternamebattler.chara;

import masternamebattler.Constants;

/**
 * 忍者クラス
 * 麻痺しない特徴を持つ
 */
public class Ninja extends Player {
    // 忍者のステータスの最大値、最小値
    public static final int NINJA_MIN_HP = 1;
    public static final int NINJA_MAX_HP = 255;
    public static final int NINJA_MIN_MP = 0;
    public static final int NINJA_MAX_MP = 0;
    public static final int NINJA_MIN_STR = 1;
    public static final int NINJA_MAX_STR = 255;
    public static final int NINJA_MIN_DEF = 1;
    public static final int NINJA_MAX_DEF = 255;
    public static final int NINJA_MIN_LUCK = 255;
    public static final int NINJA_MAX_LUCK = 255;
    public static final int NINJA_MIN_AGI = 255;
    public static final int NINJA_MAX_AGI = 255;
    public static final String DISPLAY_NAME = "忍者";

    /**
     * コンストラクタ
     * @param team プレイヤーの所属するチーム
     */
    public Ninja(Constants.Teams team) {
        super(team);
    }

    /**
     * ステータスを計算してセットする
     */
    @Override
    public void setStatsu(){
        this.hp = calcStatus(this.name, NINJA_MAX_HP, NINJA_MIN_HP, HP_INDEX);
        this.mp = calcStatus(this.name, NINJA_MAX_MP, NINJA_MIN_MP, MP_INDEX);
        this.str = calcStatus(this.name, NINJA_MAX_STR, NINJA_MIN_STR, STR_INDEX);
        this.def = calcStatus(this.name, NINJA_MAX_DEF, NINJA_MIN_DEF, DEF_INDEX);
        this.agi = calcStatus(this.name, NINJA_MAX_AGI, NINJA_MIN_AGI, AGI_INDEX);
        this.luck = calcStatus(this.name, NINJA_MAX_LUCK, NINJA_MIN_LUCK, LUCK_INDEX);
    }

    /**
     * 攻撃処理
     * 麻痺しないため、通常攻撃のみを行う
     */
    public void attack(Player enemy) {
        normalAttack(enemy);
    }

    /**
     * @return 表示名
     */
    @Override
    public String getDisplayJobName() {
        return DISPLAY_NAME;
    }

    /**
     * @return 麻痺しないため常にfalse
     */
    @Override
    public boolean isIncapacitationForParalysis(){
        return false;
    }
}
