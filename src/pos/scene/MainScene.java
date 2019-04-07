package pos.scene;

import java.util.Scanner;

import pos.manager.FileManager;
import pos.manager.StringChecker;
import pos.manager.TableManager;

public class MainScene {
	String tablenum="";
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	FileManager filemanager=new FileManager();
	TableManager tablemanager=new TableManager();
	Scanner scan=new Scanner(System.in);
	public void doProcess() {
		while(this.goMainMenu()) {};
	}
	public boolean goMainMenu() {
		System.out.println("1. �ֹ�\n"+"2. ���̺�\n"+"3. ���\n"+"4. �޴�����\n"+"5. ����");
		String input=scan.nextLine();
		if(StringChecker.checkOneFour(input)) {
		switch(Integer.parseInt(input)) {
		case 1:
			return goMenu1();
			//unit_test System.out.println("�ֹ�");
			//unit_test filemanager.readFile(filePath);
		case 2:
			System.out.println("���̺�");
			break;
		case 3:
			System.out.println("���");
			break;
		case 4:
			System.out.println("�޴�����");
			break;
		case 5:
			System.out.println("����");
			break;
			default:
				System.out.println("����Ʈ");
		}}else {
			System.out.println("1-5 ���� ���ڸ� �Է����ּ���.");
		}
		return true;
	}
	public boolean goMenu1() {
		System.out.println("1~6 ������ ���̺� ��ȣ�� �Է��ϼ���.");
		tablenum=scan.nextLine();
		if(StringChecker.checkTableNum(tablenum)) {
			//unit_test System.out.println("menu1");
			filemanager.readFile(filePath);
			return goOrder(Integer.parseInt(tablenum));
		}else {
			System.out.println("���� ���̺��Դϴ�. 1 - 6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
			return goMenu1();
		}
	}
	public boolean goOrder(int tablenum) {
		String menu="";
		//unit_testSystem.out.println(tablenum);
		int order_tablenum=tablenum;
		System.out.println("�ֹ��Ͻ� �޴��� ������ �Է��ϼ���.");
		menu=this.scan.nextLine();
		String[] menu_array=menu.split(" ");
		for(int i=0;i<menu_array.length;i=i+2) {
		if(!StringChecker.checkMenu(menu_array[i])) {
			System.out.println("���� �޴��Դϴ�. �ٽ� �Է����ּ���.");
			return goOrder(order_tablenum);
		}}
		for(int i=1;i<menu_array.length;i=i+2) {
			if(!StringChecker.checkQuantity(menu_array[i])) {
				System.out.println("��ȿ���� �ʴ� �����Դϴ�. 50���� ������ �Է��� �ּ���.");
				return goOrder(order_tablenum);
			}
		}
		System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�.");
		return goMainMenu();
		//unit_test System.out.println(StringChecker.checkMenu(menu));
		//return true;
	}
}
