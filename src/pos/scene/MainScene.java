package pos.scene;

import java.util.Scanner;

import pos.manager.FileManager;
import pos.manager.StringChecker;
import pos.manager.Table;
import pos.manager.TableManager;

public class MainScene {
	String tableNum="";
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	FileManager filemanager=new FileManager();
	TableManager tableManager=new TableManager();
	Scanner scan=new Scanner(System.in);
	public void doProcess() {
		while(this.goMainMenu()) {};
	}
	public boolean goMainMenu() {
		System.out.println("1. 주문\n"+"2. 테이블\n"+"3. 계산\n"+"4. 메뉴관리\n"+"5. 종료");
		String input=this.scan.nextLine();
		if(StringChecker.checkOneFour(input)) {
		switch(Integer.parseInt(input)) {
		case 1:
			return goMenu1();
			//unit_test System.out.println("주문");
			//unit_test filemanager.readFile(filePath);
		case 2:
			System.out.println("테이블");
			break;
		case 3:
			System.out.println("계산");
			break;
		case 4:
			System.out.println("메뉴관리");
			break;
		case 5:
			System.out.println("종료");
			break;
			default:
				System.out.println("디폴트");
		}}else {
			System.out.println("1-5 내의 숫자를 입력해주세요.");
		}
		return true;
	}
	public boolean goMenu1() {
		System.out.println("1~6 사이의 테이블 번호를 입력하세요.");
		tableNum=this.scan.nextLine();
		if(StringChecker.checkExit(tableNum)) {
			return goMainMenu();
		}
		if(StringChecker.checkTableNum(tableNum)) {
			//unit_test System.out.println("menu1");
			filemanager.readFile(filePath);
			return goOrder(Integer.parseInt(tableNum));
		}else {
			System.out.println("없는 테이블입니다. 1 - 6 내의 테이블 번호를 입력해 주세요.");
			return goMenu1();
		}
	}
	public boolean goOrder(int tablenum) {
		String menu="";
		//unit_testSystem.out.println(tablenum);
		int order_tablenum=tablenum;
		System.out.println("주문하실 메뉴와 수량을 입력하세요.");
		menu=this.scan.nextLine();
		if(StringChecker.checkExit(menu)) {
			return goMainMenu();
		}
		String[] menu_array=menu.split(" ");
		for(int i=0;i<menu_array.length;i=i+2) {
		if(!StringChecker.checkMenu(menu_array[i])) {
			System.out.println("없는 메뉴입니다. 다시 입력해주세요.");
			return goOrder(order_tablenum);
		}}
		for(int i=1;i<menu_array.length;i=i+2) {
			if(!StringChecker.checkQuantity(menu_array[i])) {
				System.out.println("유효하지 않는 수량입니다. 50내의 수량을 입력해 주세요.");
				return goOrder(order_tablenum);
			}
		}
		for(int i=0;i<menu_array.length;i=i+2) {
		tableManager.add(menu_array[i],Integer.parseInt(menu_array[i+1]),tablenum);
		}
		System.out.println("주문이 완료되었습니다.");
		tableManager.print(tablenum);
		return goMainMenu();
		//unit_test System.out.println(StringChecker.checkMenu(menu));
		//return true;
	}
}
