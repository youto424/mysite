package progate;

class Bicycle extends Vehicle {
    Bicycle(String name, String color) {
        // 自転車なので速度は遅めに 15 と設定
        super(name, color, 15);
    }

    @Override
    public void run(int distance) {
        System.out.println(distance + "km走ります");
        this.distance += distance;
        System.out.println("走行距離：" + this.distance + "km");
    }
}