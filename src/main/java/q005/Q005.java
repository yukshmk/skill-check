package q005;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {
    private static final String FILE_PATH = "C:\\pleiades\\workspace\\skill-check\\src\\main\\resources\\q005\\data.txt";
    private static final String SEPARATOR = ",";

    public static void main(String[] args) {
        List<WorkData> workDataList = getList();
        printPosSum(workDataList);
        printPCSum(workDataList);
        printNumSum(workDataList);
    }

    private static List<WorkData> getList(){
        List<WorkData> list = new ArrayList<>();
        String[] getdata;
        try (FileReader fr = new FileReader(FILE_PATH);
                BufferedReader br = new BufferedReader(fr)){
            String line;
            boolean firstline = true;
            while ((line = br.readLine()) != null) {
                if (firstline) {
                    firstline = false;
                } else {
                    getdata = line.split(SEPARATOR);
                    list.add(new WorkData(getdata[0],getdata[1],getdata[2],getdata[3],Integer.parseInt(getdata[4])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            System.out.println("読み取りに失敗しました。");
        }
        return list;
    }

    private static void printPosSum(List<WorkData> list) {
        Map<String, Integer> sumByPosition = new HashMap<>();
        for(WorkData data : list) {
            if (sumByPosition.containsKey(data.getPosition())) {
                int sumByPos = sumByPosition.get(data.getPosition()) + data.getWorkTime();
                sumByPosition.put(data.getPosition(), sumByPos);
            } else {
                sumByPosition.put(data.getPosition(), data.getWorkTime());
            }
        }
        int hour = 0;
        int minute = 0;
        for (Map.Entry<String, Integer> set : sumByPosition.entrySet()) {
            hour = set.getValue() / 60;
            minute = set.getValue() % 60;
            String printString = String.format("%4d時間%02d分", hour, minute);
            System.out.println(set.getKey() + ": " + printString);
        }
    }

    private static void printPCSum(List<WorkData> list) {
        Map<String, Integer> sumByPCode = new HashMap<>();
        for(WorkData data : list) {
            if (sumByPCode.containsKey(data.getPCode())) {
                int sumByPC = sumByPCode.get(data.getPCode()) + data.getWorkTime();
                sumByPCode.put(data.getPCode(), sumByPC);
            } else {
                sumByPCode.put(data.getPCode(), data.getWorkTime());
            }
        }
        int hour = 0;
        int minute = 0;
        for (Map.Entry<String, Integer> set : sumByPCode.entrySet()) {
            hour = set.getValue() / 60;
            minute = set.getValue() % 60;
            String printString = String.format("%4d時間%02d分", hour, minute);
            System.out.println(set.getKey() + ": " + printString);
        }
    }

    private static void printNumSum(List<WorkData> list) {
        Map<String, Integer> sumByNumber = new HashMap<>();
        for(WorkData data : list) {
            if (sumByNumber.containsKey(data.getNumber())) {
                int sumByNum = sumByNumber.get(data.getNumber()) + data.getWorkTime();
                sumByNumber.put(data.getNumber(), sumByNum);
            } else {
                sumByNumber.put(data.getNumber(), data.getWorkTime());
            }
        }
        int hour = 0;
        int minute = 0;
        for (Map.Entry<String, Integer> set : sumByNumber.entrySet()) {
            hour = set.getValue() / 60;
            minute = set.getValue() % 60;
            String printString = String.format("%4d時間%02d分", hour, minute);
            System.out.println(set.getKey() + ": " + printString);
        }
    }
}
// 完成までの時間: 2時間 40分