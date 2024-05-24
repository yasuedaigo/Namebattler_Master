package masternamebattler.Chara;

import java.util.function.Function;

import masternamebattler.GlobalConstants;
import masternamebattler.GameConstants;
    
    /**
     * キャラクターの職業を表す列挙型
     * ID、表示名、プレイヤーのコンストラクタを持つ
     */
    public enum CharacterType {
        FIGHTER(1,Fighter.DISPLAY_NAME, Fighter::new),
        PRIEST(2,Priest.DISPLAY_NAME, Priest::new),
        WIZARD(3,Wizard.DISPLAY_NAME, Wizard::new),
        NINJA(4,Ninja.DISPLAY_NAME, Ninja::new);

            private final int id;
            private final String displayName;
            private final Function<GameConstants.Teams, Player> playerConstructor;

            /**
             * コンストラクタ
             * @param id 職業のID
             * @param displayName 表示名
             * @param playerConstructor プレイヤーのコンストラクタ
             */
            CharacterType(int id, String displayName, Function<GameConstants.Teams, Player> playerConstructor) {
                this.id = id;
                this.displayName = displayName;
                this.playerConstructor = playerConstructor;
            }

            /**
             * @return ID
             */
            public int getId() {
                return id;
            }

            /**
             * @return 表示名
             */
            public String getDisplayName() {
                return displayName;
            }

            /**
             * プレイヤーのインスタンスを生成する
             * @param useTeam プレイヤーの所属するチーム
             * @return プレイヤーのインスタンス
             */
            public Player createPlayer(GameConstants.Teams useTeam) {
                return playerConstructor.apply(useTeam);
            }

            /**
             * IDから職業を取得する
             * @param id 職業のID
             * @return 職業
             */
            public static CharacterType fromId(int id) {
                for (CharacterType characterClass : CharacterType.values()) {
                    if (characterClass.getId() == id) {
                        return characterClass;
                    }
                }
                return null;
            }

            /**
             * 受け取ったidが有効かどうかを判定する
             * @param id 職業のID
             * @return 有効かどうか
             */
            public static boolean isValidId(int id) {
                for (CharacterType characterClass : values()) {
                    if (characterClass.getId() == id) {
                        return true;
                    }
                }
                return false;
            }
    }