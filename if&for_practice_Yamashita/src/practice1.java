import java.util.Scanner;

public class practice1 {
    public static void main(String[] args) {
        
        // 1. ユーザーからの入力を受け取る準備
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("三角形のサイズを入力してください: ");
        
        // 2. 入力された数値を「size」という名前の変数に保存
        int size = scanner.nextInt();
        
        // ▼▼▼ 0や負数が入力された際のエラー処理 ▼▼▼
        if (size <= 0) {
            System.out.println("エラー: 1以上の数値を入力してください。");
            scanner.close(); // スキャナーを閉じて
            return;          // プログラムを終了する
        }
        // ▲▲▲ 正の数が入力された際の処理 ▲▲▲

        // 3. 三角形を描画するループ処理
        // 外側のループ (行をコントロール。1行目からsize行目まで)
        for (int i = 1; i <= size; i++) {
            
            // 内側のループ (各行で表示する「$」の数をコントロール)
            for (int j = 1; j <= i; j++) {
                // System.out.print() は改行しない
                System.out.print("$");
            }
            
            // 内側のループが終わったら（1行分の $ を表示し終えたら）
            // System.out.println() で改行して、次の行に移る
            System.out.println();
        }
        
        // 4. スキャナーを閉じる (お作法)
        scanner.close();
    }
}