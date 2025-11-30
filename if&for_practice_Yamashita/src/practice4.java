import java.util.Scanner;

public class practice4 {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int size; // 生成する個数

        // 1. 入力チェック (0や負の数を弾く)
        while (true) {
            System.out.print("生成する数値の個数を入力してください (1以上): ");
            size = scanner.nextInt();
            
            if (size > 0) {
                break; // 1以上の数が入力されたのでループを抜ける
            } else {
                System.out.println("エラー: 1以上の数値を入力してください。");
            }
        }

        // 2. 指定された個数 (size) の配列を作成
        int[] numbers = new int[size];

        // 3. 配列に 1〜30 のランダムな正数を格納
        System.out.print("生成された数字は、");
        for (int i = 0; i < size; i++) {
            // (int)(Math.random() * 30) で 0〜29 の整数が
            // +1 することで 1〜30 の整数が得られる
            numbers[i] = (int)(Math.random() * 30) + 1;
            
            // 出力 (例: 1、9、14)
            System.out.print(numbers[i]);
            if (i < size - 1) {
                System.out.print("、"); 
            }
        }
        System.out.println("です。");


        // 4. ソート処理 (選択ソート: 大きい順)
        
        // i は「今、何番目に大きい数字を決めるか」の目印 (0番目から size-2 番目まで)
        for (int i = 0; i < size - 1; i++) {
            
            // 「i」番目以降で、一番大きい数字が入っている場所 (index) を探す
            int maxIndex = i; // とりあえず i 番目を「最大」と仮定しておく
            
            // j は「探す人」 (i+1 番目から最後まで)
            for (int j = i + 1; j < size; j++) {
                
                if (numbers[j] > numbers[maxIndex]) {
                    maxIndex = j; // 最大値の場所 (index) を更新
                }
            }

            int temp = numbers[i];
            numbers[i] = numbers[maxIndex];
            numbers[maxIndex] = temp;
        }

        // 5. ソート後の配列を出力
        System.out.print("大きい順に並べると、");
        for (int i = 0; i < size; i++) {
            System.out.print(numbers[i]);
            if (i < size - 1) {
                System.out.print("、");
            }
        }
        System.out.println("です。");

        scanner.close();
    }
}