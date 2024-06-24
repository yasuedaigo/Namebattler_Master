package masternamebattler.Chara;

import java.math.BigInteger;
import java.security.MessageDigest;

class HashDigest {

    /**
     * ハッシュダイジェストから数値を取り出す
     * 
     * @param name  名前
     * @param index 何番目の数値を取り出すか
     * @return 数値(0 ～ 255)
     */
    private static int generateNumber(String name, int index) {
        try {
            String digest = getHashDigest(name);
            String hex = digest.substring(
                    index * 2, index * 2 + 2);
            return Integer.parseInt(hex, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * ハッシュ値を取得する
     * 
     * @param name
     * @return
     */
    private static String getHashDigest(String name) {
        try {
            // ハッシュ値を取得する
            byte[] result = MessageDigest.getInstance("SHA-1")
                    .digest(name.getBytes());
            return String.format(
                    "%040x",
                    new BigInteger(1, result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ハッシュダイジェストからパーセンテージを取り出す
     * 
     * @param name  名前
     * @param index 何番目の数値を取り出すか
     * @return パーセンテージ(0 ～ 100)
     */
    protected static int generatePercentage(String name, int index) {
        int baseNumber = generateNumber(name, index);
        return baseNumber * 100 / 255;
    }
}