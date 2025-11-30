import java.util.Scanner;

public class practice2 {
    public static void main(String[] args) {
        
        // 1. ユーザーからの入力を受け取る準備
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("逆三角形のサイズを入力してください: ");
        
        // 2. 入力された数値を「size」という名前の変数に保存
        int size = scanner.nextInt();
        
        // ▼▼▼ 0や負数が入力された際のエラー処理 ▼▼▼
        if (size <= 0) {
            System.out.println("エラー: 1以上の数値を入力してください。");
            scanner.close(); // スキャナーを閉じて
            return;          // プログラムを終了する
        }
        // ▲▲▲ 正の数が入力された際の処理 ▲▲▲
        
        System.out.println(size + "段の逆三角形を表示します。");

        // 3. 三角形を描画するループ処理
        // 外側のループ (行をコントロール。1行目からsize行目まで)
        for (int i = 1; i <= size; i++) {
            
            // 4. 内側のループ (1) - 空白の表示
            for (int j = 1; j <= i - 1; j++) {
                // System.out.print() は改行しない
                System.out.print(" "); // ※半角スペース
            }
            
            // 5. 内側のループ (2) - 「*」の表示
            for (int k = 1; k <= size - i + 1; k++) {
                System.out.print("*");
            }
            
            // 6. 1行分（空白と*）の表示が終わったら改行
            System.out.println();
        }
        
        // 7. スキャナーを閉じる
        scanner.close();
    }
}