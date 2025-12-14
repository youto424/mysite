package progate;

class Main {
    public static void main(String[] args) {
        // --- 既存のProgate用コード ---
        Person person1 = new Person("Kate", "Jones", 27, 1.6, 50.0);
        Person person2 = new Person("John", "Christopher", "Smith", 65, 1.75, 80.0);

        Car car = new Car("フェラーリ", "赤");
        Bicycle bicycle = new Bicycle("ビアンキ", "緑");

        person1.buy(car);
        person2.buy(bicycle);

        System.out.println("【車の情報】");
        car.printData();
        System.out.println("-----------------");
        System.out.println("【車の所有者の情報】");
        car.getOwner().printData();

        System.out.println("=================");
        System.out.println("【自転車の情報】");
        bicycle.printData();
        System.out.println("-----------------");
        System.out.println("【自転車の所有者の情報】");
        bicycle.getOwner().printData();

        // --- ここから課題用の追加テストコード ---
        System.out.println("\n\n#########################");
        System.out.println("###   ここから課題のテスト   ###");
        System.out.println("#########################");

        // 課題用にVehicleを直接生成 (速度を指定)
        Vehicle v1 = new Vehicle("課題用車A", "Black", 10);
        Vehicle v2 = new Vehicle("課題用車B", "White", 20);

        // goメソッドの確認
        v1.go(5); // 10 * 5 = 50
        v2.go(2); // 20 * 2 = 40

        // backメソッドの確認
        v1.back(1); // 50 - 10 = 40

        // diffメソッドの確認 (v1:40, v2:40 なので 差は 0 になるはず)
        Vehicle.diff(v1, v2);
    }
}