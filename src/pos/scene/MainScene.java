package pos.scene;

import java.awt.CheckboxMenuItem;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import pos.manager.FileManager;
import pos.manager.StringChecker;
import pos.manager.Table;
import pos.manager.TableManager;

public class MainScene {
	String tableNum=""; //���̺� ��ȣ
	String movetableNum=""; //�̵��� ���̺� ��ȣ ���輭�� �������� �߰� 
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	FileManager filemanager=new FileManager();
	TableManager tableManager=new TableManager();
	Scanner scan=new Scanner(System.in);
	public void doProcess() { //���� ������ ����ȭ�� ���� ����� ���� �Լ�
		while(this.goMain()) {};
	}
	public boolean goMain() {//����ȭ�� UI
		System.out.println("1. �ֹ�\n"+"2. ���̺�\n"+"3. ���\n"+"4. �޴�����\n"+"5. ����");
		String input=this.scan.nextLine();
		if(StringChecker.checkMain(input)) {
		switch(Integer.parseInt(input)) {
		case 1:
			return goMenu1();
			//unit_test System.out.println("�ֹ�");
			//unit_test filemanager.readFile(filePath);
		case 2:
			//unit_test System.out.println("���̺�");
			return goMenu2();
		case 3:
			System.out.println("���");
			break;
		case 4:
			//unit_test System.out.println("�޴�����");
			return goMenu4();
		case 5:
			System.out.println("����");
			break;
			default:
				System.out.println("����Ʈ");
		}
		}
		else {
			System.out.println("1-5 ���� ���ڸ� �Է����ּ���.");
		}
		return true;
	}
	public boolean goMenu1() { //1�� �޴� �ֹ�
		System.out.println("1~6 ������ ���̺� ��ȣ�� �Է��ϼ���.");
		tableNum=this.scan.nextLine();
		if(StringChecker.checkExit(tableNum)) { //exit �Է����� üũ
			return goMain();
		}
		if(StringChecker.checkTableNum(tableNum)) { //���̺� ��ȣ�� �ùٸ� �Է����� üũ
			//unit_test System.out.println("menu1");
			
			return goOrder(Integer.parseInt(tableNum));
		}
		else {
			System.out.println("���� ���̺��Դϴ�. 1 - 6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
			return goMenu1();
		}
	}
	public boolean goOrder(int tableNum) {
		filemanager.readFile(filePath); //�޴��� ���
		String menu="";
		//unit_testSystem.out.println(tablenum);
		int order_tablenum=tableNum;
		System.out.println("�ֹ��Ͻ� �޴��� ������ �Է��ϼ���.");
		menu=this.scan.nextLine();
		if(StringChecker.checkExit(menu)) {
			return goMain();
		}
		String[] menu_array=menu.split(" ");
		for(int i=0;i<menu_array.length;i=i+2) { //�Է¹��� �޴������ �޴��ǿ� �ִ��� üũ
		if(!StringChecker.checkMenu(menu_array[i])) {
			System.out.println("���� �޴��Դϴ�. �ٽ� �Է����ּ���.");
			return goOrder(order_tablenum);
		}
		}
		for(int i=1;i<menu_array.length;i=i+2) { //�Է¹��� �޴��� ������ �ùٸ� �Է����� üũ
			if(!StringChecker.checkQuantity(menu_array[i])) {
				System.out.println("��ȿ���� �ʴ� �����Դϴ�. 50���� ������ �Է��� �ּ���.");
				return goOrder(order_tablenum);
			}
		}
		for(int i=0;i<menu_array.length;i=i+2) { //���̺� �޴��� ���� �߰�
		tableManager.add(menu_array[i],Integer.parseInt(menu_array[i+1]),tableNum);
		}
		System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�.");
		tableManager.print(tableNum);
		return goMain();
		//unit_test System.out.println(StringChecker.checkMenu(menu));
		//unit_test return true;
	}
	public void tableState(String tableNum) { //���̺� ��ȣ stringcheck ���ִ� �Լ�
		if(StringChecker.checkExit(tableNum)) {
			 goMain();
		}
		if(StringChecker.checkTableNum(tableNum)) {
			//unit_test System.out.println("tableNum");
			if(tableManager.table[Integer.parseInt(tableNum)].getOrdedmenu().size()==0) {
				System.out.println("���̺� ����� ����Ͻ� �� �����ϴ�.");
				goMain();
			}
			System.out.println(tableNum+"�� �ֹ�����");
			tableManager.print(Integer.parseInt(tableNum));
			}
		else {
			System.out.println("���� ���̺��Դϴ�. 1 - 6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
			goMenu2();
			}
	}
	private boolean MoveOrSum(String tableNum) { // ��ɹ�ȣ�� ��ȿ���� stringcheck
		if(StringChecker.checkExit(tableNum)) { 
			return goMain();
		}
		switch(tableNum) {
		case "1":
			return true;
		case "2":
			return true;
			default:
				return false;
		}
	}
	private void moveOrSum(String functionNum) { //���̺��� ����� ȣ���ϴ� �Լ�
		System.out.println("1. ���̺� �̵�"+"\n"+"2. ���̺� �׷�ȭ");
		String num=scan.nextLine();
//		int funcNum=Integer.parseInt(num);
		if(MoveOrSum(num)) {
			if(Integer.parseInt(tableNum)==1) {
				moveTable();
			}
			else if(Integer.parseInt(tableNum)==2) {
				
			}
		}
		else {
			System.out.println("�ٽ� �Է��� �ּ���.");
			moveOrSum(tableNum);
		}
	}
	public boolean goMenu2() { //2. ���̺� ���ý� ȣ��Ǵ� �Լ�
	
		System.out.println("1-6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
		tableNum=scan.nextLine();
		tableState(tableNum); 
		moveOrSum(tableNum); 
		return goMain();
	}
	private boolean NullCheck() { //
		if(tableManager.table[Integer.parseInt(movetableNum)].getOrdedmenu().size()==0) { //�ش� ���̺��� �ֹ������� ������Ȯ��
			return true;
		}else {
			return false;
		}
	}
	private void moveTable() { //1. ���̺� �̵� ���ý� ȣ��Ǵ� �Լ�
		System.out.println("1-6 ���� �̵��� ���̺� ��ȣ�� �Է��ϼ���.");
		movetableNum=scan.nextLine();
		if(StringChecker.checkExit(movetableNum)) {
			goMain();
		}
		if(StringChecker.checkTableNum(movetableNum)) {
			if(NullCheck()) { //�̵��Ϸ��� ���̺��� ����ִ��� Ȯ���ϴ� �Լ�
				//unit_test System.out.println("�̵�����");
				tableManager.table[Integer.parseInt(movetableNum)].orderedMenu=(ArrayList<HashMap<String, Integer>>)tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clone();
				//���� ���̺��� �ֹ������� �̵��� ���̺�� �̵���Ų��.
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear();
				//�̵��� ���̺��� �ֹ������� �����Ѵ�.
				System.out.println("�̵��Ͽ����ϴ�.");
				goMain();
			}else
				
			{
				//unit_test System.out.println("�̵��Ұ���");
				System.out.println("������� ���̺��Դϴ�. �ٸ� ���̺� ��ȣ�� �Է����ּ���.");
				moveTable();
			}
		}else {	
			System.out.println("���� ���̺��Դϴ�. 1-6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
			moveTable();
		}
		
	}
	private void addMenu(String mname,String price) { //�޴��ǿ� �޴� �߰�
		File file=new File(filePath);
		try {
			FileWriter fw=new FileWriter(file, true);
			fw.write(mname+" "+price+"\n");
			fw.close();
			goMain();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	String mname="";
		private void selMode(int modeNum) {
		switch (modeNum) {
		case 1:
			System.out.println("�޴��̸�, ������ ���� �������� �����Ͽ� �Է����ּ���.");
			String menuNamePrice=scan.nextLine();
			String[] mnameArray=menuNamePrice.split(" ");
			String mname="";
			String price="";
			//unit_test System.out.println(mnameArray.length);
			for(int i=0;i<mnameArray.length-1;i++) {
				if(mnameArray.length>2) {
					mname+=mnameArray[i]+" ";					
				}else
					mname=mnameArray[0];
			}
			if(mnameArray.length==1)mname=menuNamePrice; 
			price=mnameArray[mnameArray.length-1];
			//unit_test System.out.println(mname);
			//unit_test System.out.println(price);
			if(StringChecker.checkMenu(mname)) { //�߰��� �޴��� �̹� �����ϴ��� üũ
				System.out.println("�̹� �����ϴ� �޴��Դϴ�.");
				selMode(modeNum);
			}
			if(StringChecker.checkMname(mname)) {
				//unit_test System.out.println("price:"+price+" "+"price size:"+" "+price.length());
				if(StringChecker.checkPrice(price)) {
					addMenu(mname, price);
					//unit_test System.out.println("true");
				}
				if(!StringChecker.checkPrice(price)) {
					//unit_test System.out.println("false");
				}
			}else {
				System.out.println("false");
			}
		case 2:
			
		default:
			System.out.println("�ٽ� �Է����ּ���.");
			selMode(modeNum);
		}
	}
	private boolean goMenu4() { //4. �޴� ����
		filemanager.readFile(filePath);
		System.out.println("1. �޴� �߰�"+"\n"+"2. �޴� ����");
		String modeNum=scan.nextLine();
		selMode(Integer.parseInt(modeNum));
		return goMain();
	}
}
