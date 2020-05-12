package q007;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.util.ArrayDeque;

/**
 * q007 最短経路探索
 *
 * 壁を 'X' 通路を ' ' 開始を 'S' ゴールを 'E' で表現された迷路で、最短経路を通った場合に
 * 何歩でゴールまでたどり着くかを出力するプログラムを実装してください。
 * もし、ゴールまで辿り着くルートが無かった場合は -1 を出力してください。
 * なお、1歩は上下左右のいずれかにしか動くことはできません（斜めはNG）。
 *
 * 迷路データは MazeInputStream から取得してください。
 * 迷路の横幅と高さは毎回異なりますが、必ず長方形（あるいは正方形）となっており、外壁は全て'X'で埋まっています。
 * 空行が迷路データの終了です。
 *

[迷路の例]
XXXXXXXXX
XSX    EX
X XXX X X
X   X X X
X X XXX X
X X     X
XXXXXXXXX

[答え]
14
 */
public class Q007 {

    public static void main(String[] args) {

        // 指定の座標の四方の座標の差を表す定数
        Point[] D4 = {new Point(0,-1), new Point(1,0), new Point(0,1), new Point(-1,0)};
        // 迷路の高さ(y軸)幅(x軸)、startとexitの座標を格納する変数
        int h = 0, w = 0, sy = 0, sx = 0, ey = 0, ex = 0;

        // byte型で迷路データを受け取り、文字列に変換する。ついでにコンソールに出力
        ByteArrayInputStream maze = new MazeInputStream();
        String str = new String(maze.readAllBytes());
        System.out.println(str);

        // 迷路を改行で分割
        String[] mazes = str.split("\r\n");

        h = mazes.length;
        w = mazes[0].length();

        // 迷路を切り分けてchar型2次元配列に格納し、startとexitの座標を取得する。
        char[][] map = new char[h][w];

        for (int loop = 0; loop < h; loop++) {
            String toChar = mazes[loop];
            char[] array = new char[w];

            for(int index = 0; index < toChar.length(); index++) {
                array[index] = toChar.charAt(index);
            }

            for(int i = 0; i < array.length; i++) {
                if (array[i] == 'S') {
                    sy = loop;
                    sx = i;
                    map[loop][i] = ' ';
                } else if (array[i] == 'E') {
                    ey = loop;
                    ex = i;
                    map[loop][i] = ' ';
                } else {
                    map[loop][i] = array[i];
                }
            }
        }

        //start, exitをPoint型に
        Point start = new Point(sx, sy), exit = new Point(ex, ey);

        //キューにスタート地点を入れる
        ArrayDeque<Point> dq = new ArrayDeque<>();
        dq.add(start);

        //各地点のスタート地点からの距離を記録する二次元配列
        int[][] moves = new int[h][w];

        //スタート地点を"X"で埋めて、探索済みにする
        map[(int) start.getY()][(int) start.getX()] = 'X';

        //幅優先探索を実行
        while (dq.size() > 0) {

            //Queueの先頭の座標を取り出し＆削除して、その四方を探索する。
            Point p = dq.removeFirst();

            for (int i=0; i<4; i++) {

                int x = (int) (p.getX()+D4[i].getX());
                int y = (int) (p.getY()+D4[i].getY());

                if (map[y][x] == ' ') {

                    //" "であれば探索可能なので、Queueの末尾に追加する
                    dq.addLast(new Point(x, y));

                    //探索が終わった座標を"X"で埋めて、次回以降探索しないようにする
                    map[y][x] = 'X';

                    //座標pからpの四方の座標への移動距離は、pまでの移動距離+1
                    moves[y][x] = moves[(int) p.getY()][(int) p.getX()] + 1;

                }

            }

        }

        //ゴール地点に相当するmovesの値が答えとなる。answerが0の場合ゴールできないので-1を表示
        int answer = moves[(int) exit.getY()][(int) exit.getX()];

        if (answer == 0) {
            answer = -1;
        }

        System.out.println(answer);

    }

}
// 完成までの時間: 6時間 15分