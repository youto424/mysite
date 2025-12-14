package progate;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	// goメソッドのテスト
	@Test
	public void testGo() {
		// 準備: 名前"TestCar", 色"Red", 速度10 の車を作成
		Vehicle v = new Vehicle("TestCar", "Red", 10);
		
		// 実行: 5時間進む (10 * 5 = 50 になるはず)
		v.go(5);
		
		// 検証: 現在地(x)が期待値(50)と一致するか確認
		// assertEquals(期待する値, 実際の値);
		assertEquals(50, v.getX());
	}

	// backメソッドのテスト
	@Test
	public void testBack() {
		// 準備: 速度20 の車を作成
		Vehicle v = new Vehicle("TestCar2", "Blue", 20);
		
		// 実行: まずgoで100まで進める (20 * 5 = 100)
		v.go(5);
		
		// 実行: 2時間戻る (20 * 2 = 40 戻るので、100 - 40 = 60 になるはず)
		v.back(2);
		
		// 検証: 現在地(x)が60であるか
		assertEquals(60, v.getX());
	}
	
	// 初期値のテスト
	@Test
	public void testInitialState() {
		Vehicle v = new Vehicle("InitTest", "Green", 50);
		
		// 作成直後の現在地は0であるはず
		assertEquals(0, v.getX());
		
		// 設定した速度が正しく入っているか
		assertEquals(50, v.getVelocity());
	}
	
	// diffの計算ロジックのテスト
	// (diffメソッド自体はvoidでコンソール出力するものなので、
	// ここでは計算ロジックが合っているか、getX()を使って擬似的にテストします)
	@Test
	public void testDiffLogic() {
		Vehicle v1 = new Vehicle("V1", "Red", 10);
		Vehicle v2 = new Vehicle("V2", "Blue", 20);
		
		v1.go(2); // 10 * 2 = 20
		v2.go(2); // 20 * 2 = 40
		
		// 期待される差は 20 - 40 = -20
		int expectedDiff = -20;
		int actualDiff = v1.getX() - v2.getX();
		
		assertEquals(expectedDiff, actualDiff);
	}

}