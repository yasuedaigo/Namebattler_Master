package masternamebattler.Chara;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

import masternamebattler.GameConstants;
import masternamebattler.GameManager;
import masternamebattler.GlobalConstants;
import masternamebattler.Condition.Conditions;
import masternamebattler.Tactics.Tactics;
import masternamebattler.Magic.*;
import masternamebattler.Util.UserInput.UserInput;


/**
 * プレイヤーの基底クラス
 */
public abstract class Player {

    // 名前
    protected String name;
    // ステータス
    protected int hp;
    protected int mp;
    protected int str;
    protected int def;
    protected int luck;
    protected int agi;
    // 生存判定
    protected boolean isLive;
    // 攻撃済みかどうか
    protected boolean isAtaccked;
    // 所属チーム
    protected GameConstants.Teams team;
    // 状態異常
    protected Conditions condition;
    // 作戦
    protected Tactics tactics;
    // 職業
    protected CharacterType characterType;

    // 使用可能な魔法のリスト
    protected List<Magic> useAbleMagics;

    //攻撃対象のプレイヤー
    protected Player enemy;
    
    /**
     * コンストラクタ
     * @param name プレイヤーの名前
     * @param team プレイヤーの所属するチーム
     * @param characterType 職業を表す列挙子
     * @param tactics 作戦を表す列挙子
     */
    public Player(String name, GameConstants.Teams useTeam, CharacterType characterType,Tactics tactics) {
        this.name = name;
        this.setStatsu(this.name);
        this.isLive = true;
        this.team = useTeam;
        this.characterType = characterType;
        this.tactics = tactics;
        this.useAbleMagics = new ArrayList<>();
    }

    /**
     * 受け取った名前を元にステータスを計算する
     * @param name 名前
     * @param max_status 最大値
     * @param min_status 最小値
     * @param index ステータスのインデックス
     * @return ステータスの値
     */
    protected int calcStatus(String name, int max_status, int min_status, int index) {
        //変動部分の範囲を求める
        int baseStatusRange = max_status - min_status;
        //変動部分の値をHashDigestのパーセンテージを使って決定する
        int baseStatus = baseStatusRange * HashDigest.generatePercentage(name, index) / GlobalConstants.PERCENTAGE_BASE;
        //最低値に変動部分を足す
        return baseStatus + min_status;
    }

    /**
     * コンソールにHPを表示する
     */
    public void showHp() {
        GameManager.consoleManager.addLogText(String.format(CharaConstants.SHOW_HP_MESSAGE, this.name, this.hp));
    }

    /**
     * 受け取った値までの乱数を返す
     * @param max 乱数の最大値
     * @return 0からmaxまでの乱数
     */
    private int randomIntForRange(int max) {
        Random random = new Random();
        return random.nextInt(max + GlobalConstants.RANGE_INCLUSIVE_OFFSET);
    }

    /**
     * @return AGIの値
     */
    public int getAgi() {
        return this.agi;
    }

    public abstract void attack();

    /**
     * 通常攻撃の処理
     * ダメージを計算し、ダメージを与える
     * @param enemy 敵プレイヤー
     */
    protected void normalAttack() {
        GameManager.consoleManager.addLogText(String.format(CharaConstants.ATTACK_MESSAGE, this.name));
        int damage = calcDamage();
        
        enemy.applyDamage(damage);
    }

    /**
     * 攻撃後の処理
     * 攻撃済みフラグを立てる
     */
    public void afterAttackProcess() {
        this.isAtaccked = true;
    }

    /**
     * ダメージ計算を行う（クリティカル判定も含む）
     * @param enemy 敵プレイヤー
     * @return ダメージ量
     */
    protected int calcDamage() {
        // クリティカルならSTRの値がそのままダメージになる
        if (this.isCritical()) {
            GameManager.consoleManager.addLogText(CharaConstants.CRITICAL_MESSAGE);
            return this.str;
        }
        // クリティカルでない場合はSTRからDEFを引いた値
        int damage = this.randomIntForRange(this.str) - enemy.randomIntForRange(def);
        // ダメージがマイナスの場合は0にする
        if (damage < CharaConstants.MIN_DAMAGE) {
            damage = CharaConstants.MIN_DAMAGE;
        }
        return damage;
    }

    /**
     * クリティカル判定
     * @return クリティカルならtrue
     */
    protected boolean isCritical() {
        Random random = new Random();
        //Luckの値は0～255のためパーセンテージに変換する
        int luckPercentage = (this.luck * GlobalConstants.PERCENTAGE_BASE) / CharaConstants.MAX_LUCK_STATUS;
        //0から100の乱数を生成し、その値がLUCKの値より小さい場合はクリティカル
        return random.nextInt(GlobalConstants.PERCENTAGE_BASE) < luckPercentage;
    }

    /**
     * ダメージ処理
     * @param damage 与ダメージ
     */
    public void applyDamage(int damage) {
        GameManager.consoleManager.addLogText(String.format(CharaConstants.DAMAGE_MESSAGE, this.name, damage));
        this.hp -= damage;
        //プレイログの差分を見るためEnterが押されるまでプレイログの更新を止める
        UserInput.waitForEnter();
        // HPが0以下になった場合はHPを0にして生存フラグをfalseにする
        if (this.hp <= CharaConstants.DOWN_HP) {
            this.hp = CharaConstants.DOWN_HP;
            this.isLive = false;
            GameManager.consoleManager.addLogText(String.format(CharaConstants.DOWN_MESSAGE, this.name));
        }
    }

    /**
     * HPを回復する
     * @param healHp 回復量
     */
    public void healHp(int healHp) {
        GameManager.consoleManager.addLogText(String.format(CharaConstants.HEAL_MESSAGE, this.name, healHp));
        this.hp += healHp;
        UserInput.waitForEnter();
    }

    /**
     * @param tactics 作戦
     */
    protected void setTactics(Tactics tactics) {
        this.tactics = tactics;
    }

    /**
     * @return 作戦
     */
    public Tactics getTactics() {
        return this.tactics;
    }


    /**
     * @param isLive 生きてるときはtrue
     */
    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    /**
     * @return 生きてるときはtrue
     */
    public boolean getIsLive() {
        return this.isLive;
    }

    /**
     * @param isAttacked 攻撃済みのときはtrue
     */
    public void setIsAttacked(boolean isAttacked) {
        this.isAtaccked = isAttacked;
    }

    /**
     * @return 攻撃済みのときはtrue
     */
    public boolean getIsAttacked() {
        return this.isAtaccked;
    }

    /**
     * @param team 所属チーム
     */
    public void setTeam(GameConstants.Teams team) {
        this.team = team;
    }

    /**
     * @return 所属チーム
     */
    public GameConstants.Teams getTeam() {
        return this.team;
    }

    /**
     * @return 名前
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param condition 状態異常
     */
    public void setCondition(Conditions condition) {
        this.condition = condition;
        GameManager.consoleManager.addLogText(String.format(CharaConstants.SET_CONDITION_MESSAGE, this.name, condition.getDisplayName()));
    }

    /**
     * @return 状態異常
     */
    public Conditions getCondition() {
        return this.condition;
    }

    /**
     * 麻痺判定
     * @return 麻痺した場合はtrue
     */
    protected boolean isIncapacitationForParalysis() {
        // 麻痺状態でない場合はfalse
        if (this.condition != Conditions.PARALYSIS) {
            return false;
        }
        Random random = new Random();
        // 0から100の乱数を生成し、その値が麻痺率以下の場合は麻痺
        if (random.nextInt(GlobalConstants.PERCENTAGE_BASE + GlobalConstants.RANGE_INCLUSIVE_OFFSET) < Paralysis.PARALYSIS_RATE) {
            return true;
        }
        return false;
    }

    /**
     * 毒ダメージ処理
     */
    public void poisonDamage() {
        // 毒状態で生存している場合は毒ダメージを受ける
        if (this.isLive == true && this.condition == Conditions.POISON) {
            GameManager.consoleManager.addLogText(String.format(CharaConstants.POISON_DAMAGE_MESSAGE, this.name));
            // 毒ダメージはランダムで決定
            int damage = ThreadLocalRandom.current().nextInt(Poison.POISON_MIN_DAMAGE, Poison.POISON_MAX_DAMAGE + GlobalConstants.RANGE_INCLUSIVE_OFFSET);
            this.applyDamage(damage);
        }
    }

    /**
     * @return 表示名
     */
    public abstract String getDisplayJobName();

    /**
     * ステータスをセットする
     */
    protected abstract void setStatsu(String name);

    /**
     * ヒールを使用可能かどうか
     * @param enemy 攻撃対象のプレイヤー
     * @return 使用可能ならtrue
     */
    public boolean canUseHeal(){
        //useableMagicsにHealが含まれている場合、MPがHealの消費MP以上の場合はtrue
        for(Magic magic : useAbleMagics){
            if(magic instanceof Heal){
                return this.mp >= Heal.CONSUMPTION_MP;
            }
        }
        //useableMagicsにHealが含まれていない場合はfalse
        return false;
    }

    /**
     * 魔法が使用できるかどうか
     * @return 使用できる魔法がある場合はtrue
     */
    public boolean canUseMagic() {
        //MPが足りている魔法がある場合はtrue
        for (Magic magic : useAbleMagics) {
            if (magic.getConsumptionMp() <= this.mp) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param name 名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp HP
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return MP
     */
    public int getMp() {
        return mp;
    }

    /**
     * @param mp MP
     */
    public void setMp(int mp) {
        this.mp = mp;
    }

    /**
     * @return STR
     */
    public int getStr() {
        return str;
    }

    /**
     * @param str STR
     */
    public void setStr(int str) {
        this.str = str;
    }

    /**
     * @return DEF
     */
    public int getDef() {
        return def;
    }

    /**
     * @param def DEF
     */
    public void setDef(int def) {
        this.def = def;
    }

    /**
     * @return LUCK
     */
    public int getLuck() {
        return luck;
    }

    /**
     * @param luck LUCK
     */
    public void setLuck(int luck) {
        this.luck = luck;
    }

    /**
     * @param agi AGI
     */
    public void setAgi(int agi) {
        this.agi = agi;
    }

    /**
     * @return 職業の列挙子
     */
    public CharacterType getCharacterType() {
        return characterType;
    }

    /**
     * 魔法が使用に適しているか判定する
     * @param magic 選択した魔法
     * @param enemy 攻撃対象のプレイヤー
     * @return 使用に適している場合はtrue
     */
    public boolean isAvailableMagic(Magic magic) {
        //MPが足りていない場合は使用不可
        if (magic.getConsumptionMp() > this.mp) {
            return false;
        }
        //魔法が状態異常を付与する魔法で、敵プレイヤーがすでに状態異常にかかっている場合は使用不可
        if (enemy.getCondition() != null && magic.getGrantCondition() != null) {
            return false;
        }
        //Healの場合は味方プレイヤーにしか使用できない
        if(magic instanceof Heal){
            return this.team == enemy.getTeam();
        }
        //Heal以外の場合は敵プレイヤーにしか使用できない
        if(!(magic instanceof Heal)){
            return this.team != enemy.getTeam();
        }
        return true;
    }

    /**
     * 使用する魔法を選択する
     * @param enemy 攻撃対象のプレイヤー
     * @return 魔法の列挙子
     */
    public Magic choiceMagic(){
        //useAbleMagicsの中から敵プレイヤーに対して使用に適している魔法だけのリストを作成する
        List<Magic> availableMagics = new ArrayList<>();
        for (Magic magic : useAbleMagics) {
            if (isAvailableMagic(magic)) {
                availableMagics.add(magic);
            }
        }

        //使用に適した魔法リストの中からランダムで魔法を選択する
        if (!availableMagics.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(availableMagics.size());
            return availableMagics.get(index);
        }
        return null;
    }

    /**
     * @param enemy 敵プレイヤー
     */
    public void setEnemy(Player enemy){
        this.enemy = enemy;
    }

    /**
     * @return 敵プレイヤー
     */
    public Player getEnemy(){
        return this.enemy;
    }
}