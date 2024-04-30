package masternamebattler.chara;

import java.util.Random;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import masternamebattler.Constants;
import masternamebattler.chara.Magic.Paralysis;
import masternamebattler.chara.Magic.Poison;

/**
 * プレイヤーの基底クラス
 */
public abstract class Player {
    public static final String ATTACK_MESSAGE = "%sの攻撃！";
    public static final String DAMAGE_MESSAGE = "%sに%dのダメージ！";
    public static final String DOWN_MESSAGE = "%sは力尽きた...！";
    public static final String CRITICAL_MESSAGE = "会心の一撃！";
    public static final String USE_MAGIC_MESSAGE = "%sは%sを使った！";
    public static final String PARALYSIS_MESSAGE = "%sは麻痺して動けなかった！";
    public static final String POISON_DAMAGE_MESSAGE = "%sは毒によりダメージを受けた！";
    public static final String DECIDE_NAME_MESSAGE = "名前を入力してください";
    public static final String INVALID_NAME_MESSAGE = "入力が正しくありません";
    public static final String SET_CONDITION_MESSAGE = "%sは%sにかかった！";
    public static final String SHOW_HP_MESSAGE = "%s HP:%d";

    public static final int MIN_DAMAGE = 0;
    public static final int MIN_HP = 0;
    public static final int HP_INDEX = 0;
    public static final int MP_INDEX = 1;
    public static final int STR_INDEX = 2;
    public static final int DEF_INDEX = 3;
    public static final int AGI_INDEX = 4;
    public static final int LUCK_INDEX = 5;

    public String name;
    public int hp;
    public int mp;
    public int str;
    public int def;
    public int luck;
    public int agi;
    public boolean isLive;
    public Constants.Teams team;
    public Conditions condition;
    
    /**
     * コンストラクタ
     * 名前を入力してもらい、ステータスを計算してセットする
     * @param useTeam プレイヤーの所属するチーム
     */
    public Player(Constants.Teams useTeam) {
        this.name = decideNameForInput();
        this.setStatsu();
        this.isLive = true;
        this.team = useTeam;
    }

    /**
     * 入力によって名前を決定する
     * 入力が空でなくなるまで繰り返す
     * @return 入力された名前
     */
    protected String decideNameForInput() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        System.out.println(DECIDE_NAME_MESSAGE);
        String input = null;
        do{
            input = scanner.nextLine();
        }while(!validateName(input));
        return input;
    }

    /**
     * @param value 入力された名前
     * @return 入力値が空かどうか
     */
    protected boolean validateName(String value) {
        if(value == null || value.length() == 0){
            System.out.println(INVALID_NAME_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * 受け取った名前を元にステータスを計算する
     * @param name 名前
     * @param max_status ステータスの最大値
     * @param min_status ステータスの最小値
     * @param index ステータスのインデックス
     * @return ステータス値
     */
    protected int calcStatus(String name,int max_status,int min_status,int index){
        int baseStatusRange = max_status - min_status;
        int baseStatus = baseStatusRange * HashDigest.generatePercentage(name,index) / Constants.PERCENTAGE_BASE;
        return baseStatus + min_status;
    }

    /**
     * プレイヤーの情報を表示する
     */
    public void showInfo(){
        System.out.println("職業:" + getDisplayJobName());
        System.out.println("名前:" + this.name);
        System.out.println("HP:" + this.hp);
        System.out.println("MP:" + this.mp);
        System.out.println("STR:" + this.str);
        System.out.println("DEF:" + this.def);
        System.out.println("AGI:" + this.agi);
        System.out.println("LUCK:" + this.luck);
    }

    /**
     * HPを表示する
     */
    public void showHp(){
        System.out.println(String.format(SHOW_HP_MESSAGE, this.name, this.hp));
    }

    /**
     * @param max 乱数の最大値
     * @return 0からmaxまでの乱数
     */
    public int randomIntForRange(int max){
        Random random = new Random();
        return random.nextInt(max + Constants.RANGE_INCLUSIVE_OFFSET);
    }

    /**
     * @return AGI
     */
    public int getAgi(){
        return this.agi;
    }

    /**
     * 攻撃処理
     * 麻痺判定を行い、麻痺しなければ通常攻撃を行う
     * @param enemy 敵プレイヤー
     */
    public void attack(Player enemy) {
        if(isIncapacitationForParalysis()){
            System.out.println(String.format(PARALYSIS_MESSAGE, this.name));
            return;
        }
        normalAttack(enemy);
    }

    /**
     * 通常攻撃処理
     * ダメージを計算し、ダメージを与える
     * @param enemy 敵プレイヤー
     */
    public void normalAttack(Player enemy){
        System.out.println(String.format(ATTACK_MESSAGE, this.name));
        int damage = calcDamage(enemy);
        if(damage < MIN_DAMAGE){
            damage = MIN_DAMAGE;
        }
        enemy.damage(damage);
    }

    /**
     * ダメージ計算
     * クリティカル判定を行い、クリティカルならSTR分のダメージを与える
     * クリティカルでない場合は、STRの乱数から敵のDEFの乱数を引いた値がダメージになる
     * @param enemy 敵プレイヤー
     * @return 与ダメージ
     */
    public int calcDamage(Player enemy){
        if(this.isCritical()){
            System.out.println(CRITICAL_MESSAGE);
            return this.str;
        }
        int damage = this.randomIntForRange(this.str) - enemy.randomIntForRange(def);
        if(damage < MIN_DAMAGE){
            damage = MIN_DAMAGE;
        }
        return damage;
    }

    /**
     * LUCKの値からクリティカル率を算出し、クリティカルならtrueを返す
     * @return クリティカルならtrue
     */
    public boolean isCritical(){
        Random random = new Random();
        int luckPercentage = (this.luck * Constants.PERCENTAGE_BASE) / Constants.MAX_LUCK;
        return random.nextInt(Constants.PERCENTAGE_BASE) < luckPercentage;
    }

    /**
     * ダメージ処理
     * ダメージを受け、HPが0以下になったらHPを0にしてisLiveをfalseにする
     * @param damage 与ダメージ
     */
    public void damage(int damage){
        System.out.println(String.format(DAMAGE_MESSAGE, this.name, damage));
        this.hp -= damage;
        if(this.hp <= MIN_HP){
            this.hp = MIN_HP;
            this.isLive = false;
            System.out.println(String.format(DOWN_MESSAGE, this.name));
        }
    }

    /**
     * @return 倒れていない場合はtrue
     */
    public boolean getIsLive(){
        return this.isLive;
    }

    /**
     * @return プレイヤーの名前
     */
    public String getName(){
        return this.name;
    }

    /**
     * 状態異常をセットする
     * @param condition 状態
     */
    public void setCondisions(Conditions condition){
        this.condition = condition;
        System.out.println(String.format(SET_CONDITION_MESSAGE, this.name, condition.getDisplayName()));
    }

    /**
     * @return プレイヤーの状態異常
     */
    public Conditions getCondition(){
        return this.condition;
    }

    /**
     * 麻痺率をもとに麻痺判定を行う
     * @return 麻痺した場合はtrue
     */
    public boolean isIncapacitationForParalysis(){
        if(this.condition != Conditions.PARALYSIS){
            return false;
        }
        Random random = new Random();
        if(random.nextInt(Constants.PERCENTAGE_BASE + Constants.RANGE_INCLUSIVE_OFFSET) < Paralysis.PARALYSIS_RATE){
            return true;
        }
        return false;
    }

    /**
     * 毒ダメージ処理
     * 毒状態なら毒ダメージを与える
     */
    public void poisonDamage(){
        if(this.isLive == true && this.condition == Conditions.POISON){
            System.out.println(String.format(POISON_DAMAGE_MESSAGE, this.name));
            Random random = new Random();
            int damage = random.nextInt(Poison.POISON_MAX_DAMAGE - Poison.POISON_MIN_DAMAGE + Constants.RANGE_INCLUSIVE_OFFSET) + Poison.POISON_MIN_DAMAGE;
            this.damage(damage);
        }
    }

    /**
     * @return 表示名
     */
    public abstract String getDisplayJobName();

    /**
     * ステータスをセットする
     */
    public abstract void setStatsu();
}