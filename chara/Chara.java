package namebattler.chara;

import java.util.Random;

import namebattler.GameManager;

public class Chara {
    private final int MAX_GENERATE_NUMBER = 255;
    private final int INDEX_FOR_HP = 0;
    private final int INDEX_FOR_ATK = 1;
    private final int INDEX_FOR_DEF = 2;
    private final int INDEX_FOR_LUCK = 3;
    private final int MAX_CRITICAL = 101;
    private static final String ATTACK_MESSAGE = "%sの攻撃";
    private static final String CRITICAL_MESSAGE = "会心の一撃！";
    private static final String DAMAGE_MESSAGE = "%sに%dのダメージ。(HP %d → %d)";
    private static final String DOWN_MESSAGE = "%sは倒れた";
    private static final String STATSU_MESSAGE = "Name:%d HP:%d ATK:%d DEF:%d LUCK:%d";
    private String name;
    private int hp;
    private int atk;
    private int def;
    private int luck;
    private boolean isLive;
    private GameManager.Teams team;

    public Chara(String useName, GameManager.Teams useTeam) {
        HashDigest hashDigest = new HashDigest();
        this.name = useName;
        this.hp = hashDigest.generateNumber(useName, INDEX_FOR_HP);
        this.atk = hashDigest.generateNumber(useName, INDEX_FOR_ATK);
        this.def = hashDigest.generateNumber(useName, INDEX_FOR_DEF);
        this.luck = calcLuck(hashDigest, useName);
        this.isLive = true;
        this.team = useTeam;
    }

    private int calcLuck(HashDigest hashDigest, String usename) {
        int originLuck = hashDigest.generateNumber(usename, INDEX_FOR_LUCK);
        return this.convertPercentasion(originLuck);
    }

    private int convertPercentasion(int origin) {
        return origin * 100 / MAX_GENERATE_NUMBER;
    }

    public void attack(Chara defender) {
        this.showAttackMessage();
        int damage = calcDamage(defender);
        defender.damage(damage);
    }

    public void showDamageMessage(int damage, int beforHp, int afterHp) {
        System.out.println(String.format(DAMAGE_MESSAGE, this.name, damage, beforHp, afterHp));
    }

    public void showAttackMessage() {
        System.out.println(String.format(ATTACK_MESSAGE, this.name));
    }

    public void showCriticalMessage() {
        System.out.println(CRITICAL_MESSAGE);
    }

    public void showDownMessage() {
        System.out.println(String.format(DOWN_MESSAGE, this.name));
    }

    private int calcDamage(Chara defender) {
        int damage;
        damage = this.atk - defender.getDef();
        if (isCritical()) {
            this.showCriticalMessage();
            damage = this.atk;
        }
        if (damage < 0) {
            damage = 0;
        }
        return damage;
    }

    public void damage(int damage) {
        int beforHp = this.hp;
        this.hp = (this.hp - damage);
        if (this.isDown()) {
            this.isLive = false;
            this.hp = 0;
            this.showDamageMessage(damage, beforHp, this.hp);
            showDownMessage();
            return;
        }
        this.showDamageMessage(damage, beforHp, this.hp);
    }

    private boolean isCritical() {
        Random rnd = new Random();
        if (this.luck < rnd.nextInt(MAX_CRITICAL)) {
            return true;
        }
        return false;
    }

    public boolean isDown() {
        return this.hp <= 0;
    }

    public void showStatsu() {
        System.out.println(String.format(
                STATSU_MESSAGE, this.name, this.hp, this.atk, this.def, this.luck));
    }

    public void setName(String useName) {
        this.name = useName;
    }

    public String getName() {
        return this.name;
    }

    public void setHp(int useHp) {
        this.hp = useHp;
    }

    public int getHp() {
        return this.hp;
    }

    public void setAtk(int useAtk) {
        this.atk = useAtk;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setDef(int useDef) {
        this.def = useDef;
    }

    public int getDef() {
        return this.def;
    }

    public void setLuck(int useLuck) {
        this.luck = useLuck;
    }

    public int getLuck() {
        return this.luck;
    }

    public void setIsLive(boolean useIsLive) {
        this.isLive = useIsLive;
    }

    public boolean getIsLive() {
        return this.isLive;
    }

    public void setTeam(GameManager.Teams useteam) {
        this.team = useteam;
    }

    public GameManager.Teams getTeam() {
        return this.team;
    }
}