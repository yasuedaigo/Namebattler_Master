package masternamebattler.Util;

/**
 * 4つの引数を受け取り、戻り値を返す関数型インターフェース
 * CharacterTypeクラスで使用
 * 
 * @param <T> 第1引数の型
 * @param <U> 第2引数の型
 * @param <V> 第3引数の型
 * @param <W> 第4引数の型
 * @param <R> 戻り値の型
 */
@FunctionalInterface
public interface QuadFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}