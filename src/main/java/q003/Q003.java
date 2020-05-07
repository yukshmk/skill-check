package q003;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    static String masterData;
    private static final String SEPARATOR = "(\\s+?|\\.|,|;|\\n)";

    public static void main(String[] args) {
        // テキストデータをStringに変換
        try {
            masterData = IOUtils.toString(openDataFile(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 指定のセパレータで分割。小文字に変換してmapに格納する。
        Map<String, Integer> map = new HashMap<>();
        String lowerWord;
        String[] words = masterData.split(SEPARATOR);
        for (String word : words) {
            if (!word.isEmpty()) {
                lowerWord = word.toLowerCase();
                if (map.containsKey(lowerWord)) {
                    int count = map.get(lowerWord) + 1;
                    map.put(lowerWord, count);
                } else {
                    map.put(lowerWord, 1);
                }
            }
        }
        // TreeMapでソートして出力
        TreeMap<String, Integer> sorted = new TreeMap<>(map);
        for (Map.Entry<String, Integer> set : sorted.entrySet()) {
            System.out.println(set.getKey()+ "=" +set.getValue());
        }
    }
}
// 完成までの時間: 3時間 10分