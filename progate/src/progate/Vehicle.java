package progate;

// 課題で new Vehicle() するために abstract を外しました
public class Vehicle {
    // --- 既存のフィールド ---
    private String name;
    private String color;
    protected int distance = 0;
    private Person owner;

    // --- 課題2：追加フィールド ---
    private int x = 0;          // 現在地
    private int velocity = 0;   // 速度

    // --- コンストラクタ（修正） ---
    // 既存のコードを壊さないよう、velocityには初期値を与えるか、引数に追加します。
    // 今回は課題に合わせて引数に velocity を追加しました。
    Vehicle(String name, String color, int velocity) {
        this.name = name;
        this.color = color;
        this.velocity = velocity;
        this.x = 0;
    }

    // --- 既存のメソッド ---
    public String getName() { return this.name; }
    public String getColor() { return this.color; }
    public int getDistance() { return this.distance; }
    public Person getOwner() { return this.owner; }
    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setOwner(Person person) { this.owner = person; }

    public void printData() {
        System.out.println("名前：" + this.name);
        System.out.println("色：" + this.color);
        System.out.println("走行距離：" + this.distance + "km");
        // 追加情報の表示（必要であれば）
        System.out.println("現在地：" + this.x);
    }

    // abstract を外して、共通の処理として記述（削除せず残す）
    public void run(int distance) {
        System.out.println(distance + "km走ります");
        this.distance += distance;
        System.out.println("走行距離：" + this.distance + "km");
    }

    // --- 課題2：追加メソッド ---

    public int getX() { return this.x; }
    public int getVelocity() { return this.velocity; }

    // 1. goメソッド
    public void go(int time) {
        int dist = this.velocity * time;
        this.x += dist;
        // distance(走行距離)もついでに増やしておくと整合性が取れますが、
        // 今回は課題の要件である x の管理のみ行います。
        System.out.println(this.name + "は " + time + "時間進んだ。現在地: " + this.x);
    }

    // 2. backメソッド
    public void back(int time) {
        int dist = this.velocity * time;
        this.x -= dist;
        System.out.println(this.name + "は " + time + "時間戻った。現在地: " + this.x);
    }

    // 3. diffメソッド (static)
    public static void diff(Vehicle v1, Vehicle v2) {
        int difference = v1.getX() - v2.getX();
        System.out.println(v1.getName() + "と" + v2.getName() + "の現在地の差: " + difference);
    }
}