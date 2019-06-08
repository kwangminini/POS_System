package pos.scene;

import java.awt.CheckboxMenuItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

//import com.sun.tools.javac.util.List;

import pos.manager.FileManager;
import pos.manager.StringChecker;
import pos.manager.Table;
import pos.manager.TableManager;

public class MainScene {
	String tableNum=""; //���̺� ��ȣ
	String movetableNum=""; //�̵��� ���̺� ��ȣ ���輭�� �������� �߰� 
	String payNum=""; //������ �ݾ�
	int totalPrice=0; //�ֹ��� �޴����� �� ����
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	FileManager filemanager=new FileManager();
	TableManager tableManager=new TableManager();
	Table tableClass=new Table();
	Scanner scan=new Scanner(System.in);
	public void doProcess() { //���� ������ ����ȭ�� ���� ����� ���� �Լ�
		while(this.goMain()) {
		};
	}
	public boolean goMain() {//����ȭ�� UI
		System.out.println("1. �ֹ�\n"+"2. ���̺�\n"+"3. ���\n"+"4. �޴�����\n"+"5. ����");
		String input=this.scan.nextLine();
		if(StringChecker.checkMain(input)) {
		switch(Integer.parseInt(input)) {
		case 1:
			return goMenu1(); //�ֹ� ������� �̵�
			//unit_test System.out.println("�ֹ�");
			//unit_test filemanager.readFile(filePath);
		case 2:
			//unit_test System.out.println("���̺�");
			return goMenu2(); //���̺� ������� �̵�
		case 3:
			//unit_test System.out.println("���");
			return goMenu3(); //��� ������� �̵�
		case 4:
			//unit_test System.out.println("�޴�����");
			return goMenu4(); //�޴����� ������� �̵�
		case 5:
			System.out.println("����");
			return false;
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
			System.out.println("���� ���̺��Դϴ�.");
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
		if(StringChecker.checkExit(menu)) { //exit �Է¹޾Ҵ��� üũ
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
			System.out.println("���� ���̺��Դϴ�.");
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
		if(MoveOrSum(num)) {
			if(Integer.parseInt(num)==1) {
				moveTable();
			}
			else if(Integer.parseInt(num)==2) {
				sumTable();
			}
		}
		else {
			System.out.println("�ٽ� �Է��� �ּ���.");
			moveOrSum(tableNum);
		}
	}
	private void sumTable() {
		System.out.println("1-6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
		movetableNum=scan.nextLine();
		if(StringChecker.checkExit(movetableNum)) { //exit ���� ������ ��������
			goMain();
		}
		if(StringChecker.checkTableNum(movetableNum)) { 
			if(NotNullCheck()) { //����ִ� ���̺����� Ȯ��
				for(Map.Entry<String, Integer> elem:tableManager.table[Integer.parseInt(tableNum)].map.entrySet()) { //�������̺��� �ֹ��޴��� ������ �׷�ȭ ���̺��� hashmap�� �߰�
					String key=elem.getKey();
					int value=elem.getValue();
					tableManager.table[Integer.parseInt(movetableNum)].addTable(key, value);//���� ���̺��� �޴��� �׷�ȭ�� ���̺�� �̵���Ų��.
					tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear();//���� ���̺��� �ֹ������� �����Ѵ�.
					}
				System.out.println("���̺� �׷�ȭ�� �Ϸ�Ǿ����ϴ�.");
				//unit_test System.out.println("�ռ� ������ ���̺�");
			}
			else {
				System.out.println("�� ���̺��Դϴ�. �ٸ� ���̺� ��ȣ�� �Է��� �ּ���.");
				sumTable();
			}
		}
		else {
			System.out.println("���� ���̺��Դϴ�.");
			sumTable();
		}
	}
	public boolean goMenu2() { //2. ���̺� ���ý� ȣ��Ǵ� �Լ�
		System.out.println("1-6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
		tableNum=scan.nextLine();
		tableState(tableNum); 
		moveOrSum(tableNum); 
		return goMain();
	}
	private boolean NullCheck() { //�ش� ���̺��� ����ִ��� Ȯ�� ��������� true
		if(tableManager.table[Integer.parseInt(movetableNum)].getOrdedmenu().size()==0) { //�ش� ���̺��� �ֹ������� ������Ȯ��
			return true;
		}
		else {
			return false;
		}
	}
	private boolean NotNullCheck() { //�ش� ���̺��� �Ⱥ���ִ��� Ȯ�� ��������� false
		if(tableManager.table[Integer.parseInt(movetableNum)].getOrdedmenu().size()==0) { //�ش� ���̺��� �ֹ������� ������Ȯ��
			return false;
		}
		else {
			return true;
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
				for(Map.Entry<String, Integer> entry:tableManager.table[Integer.parseInt(tableNum)].map.entrySet()) {
					tableManager.table[Integer.parseInt(movetableNum)].map.put(entry.getKey(), entry.getValue());
				}//���� ���̺��� �ֹ������� �̵��� ���̺�� �̵���Ų��.
				tableManager.table[Integer.parseInt(movetableNum)].orderedMenu.add(tableManager.table[Integer.parseInt(movetableNum)].map);
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear();//�̵��� ���̺��� �ֹ������� �����Ѵ�.
				tableManager.table[Integer.parseInt(tableNum)].map.clear();//�̵��� ���̺��� �ֹ������� �����Ѵ�.
				System.out.println("�̵��Ͽ����ϴ�.");	
				goMain();
			}
			else
			{
				//unit_test System.out.println("�̵��Ұ���");
				System.out.println("������� ���̺��Դϴ�. �ٸ� ���̺� ��ȣ�� �Է����ּ���.");
				moveTable();
			}
		}
		else {	
			System.out.println("���� ���̺��Դϴ�.");
			moveTable();
		}
		
	}
	private void addMenu(String mname,String price) { //�޴��ǿ� �޴� �߰�
		//File file=new File(filePath);
		BufferedWriter file;
		try {
			file = new BufferedWriter(new FileWriter(filePath,true));
			file.write(mname+" "+price+"\n");
			file.close();
			goMain();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

		private void selMode(int modeNum) { //�޴��߰� �� ������ �ٷ�� �Լ�
		switch (modeNum) {
		case 1: //�޴� �߰�
			System.out.println("�޴��̸�, ������ ���� �������� �����Ͽ� �Է����ּ���.");
			String menuNamePrice=scan.nextLine();
			String[] mnameArray=menuNamePrice.split(" ");
			String mname="";
			String price="";
			//unit_test System.out.println(mnameArray.length);
			for(int i=0;i<mnameArray.length-1;i++) {
				if(mnameArray.length>2) {
					mname+=mnameArray[i]+" ";					
				}
				else {
					mname=mnameArray[0];
					}
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
					System.out.println("��ȿ���� ���� �����Դϴ�. 5�ڸ� �̳��� ���������� �Է��ϼ���.");
					selMode(1);
					
				}
			}
			else {
				System.out.println("false");
			}
		case 2: //�޴�����
			System.out.println("������ �޴����� �Է��ϼ���.");
			String del_mname=scan.nextLine();
			if(StringChecker.checkMenu(del_mname)) { //�߰��� �޴��� �̹� �����ϴ��� üũ
				delMenu(del_mname);
			}
			if(StringChecker.checkExit(del_mname)) {
				goMain();
			}
			else {
				System.out.println("���� �޴��Դϴ�. �ٽ��Է����ּ���.");
				selMode(2);
			}
			break;
//			unit_test	for(int j=0;j<s.length;j++) {
//				System.out.println(s[j]);
//			}

		default:
			System.out.println("�ٽ� �Է����ּ���."); 
			goMenu4();
		}
	}
	private void delMenu(String mname) { //�޴� �����ϴ� �Լ�
		File file=new File(filePath);
		BufferedReader inFile;
		String[] s=new String[100];
		try {
			inFile = new BufferedReader(new FileReader(file));
			String sLine=null;
			int ii=0;
			try {
				while((sLine=inFile.readLine())!=null) {
					s[ii]=sLine;
					ii++;
					//unit_test System.out.println("����"+sLine);
				}
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i=0;
		while(s[i]!=null) { 
			if(s[i].contains(mname)) {//������ �޴����� �� ������ �� �޴� ����
				s[i]=null;
				break;
			}
			i++;
		}
		//�޴� �����Ѱ� ���� �ٽþ���
		try {
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
			for(int z=0;z<s.length;z++) {
				if(s[z]!=null) {
					bufferedWriter.write(s[z]);
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
			goMain();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String pay(String tableNum) { //��� �Լ�
		
		if(StringChecker.checkExit(tableNum)) { //exit �Է����� üũ
			goMain();
		}
		if(StringChecker.checkTableNum(tableNum)) { //���̺� ��ȣ�� �ùٸ� �Է����� üũ
			if(tableManager.table[Integer.parseInt(tableNum)].getOrdedmenu().size()==0) { //�ش� ���̺��� �ֹ������� ������Ȯ��
				System.out.println("�����̺��Դϴ�. �ٸ� ���̺� ��ȣ�� �Է����ּ���.");			
			}
			else {
				HashMap<String, Integer> pay_list=new HashMap<String, Integer>(); //txt������ �޴��� �ֹ��� �޴��� ���ϱ����� �ֹ��� �޴����� hashmap���� ����
				for(Map.Entry<String, Integer> elem:tableManager.table[Integer.parseInt(tableNum)].map.entrySet()) { //�������̺��� �ֹ��޴��� ������ �׷�ȭ ���̺��� hashmap�� �߰�
					String key=elem.getKey();
					int value=elem.getValue();
					pay_list.put(key, value);
					}
//unit_test		for(Map.Entry<String, Integer>entry:pay_list.entrySet()) {
//					System.out.println("key:"+entry.getKey()+" "+"value:"+entry.getValue());
//				}
				String menulist="";
				try {
					File file = new File("C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt");
					FileReader filereader=new FileReader(file);
					BufferedReader bufReader=new BufferedReader(filereader);
					String line="";
					try {
						int i=0;
						while((line=bufReader.readLine())!=null) {
							menulist+=line+" ";
							i++;
						}
						//unit_test System.out.println(menulist);
						String[] menuName=menulist.split(" ");
						//unit_testSystem.out.println(menuName.length);
						for(int j=0;j<menuName.length;j=j+2) {
							for(Map.Entry<String, Integer>entry:pay_list.entrySet()) {
								if(menuName[j].equals(entry.getKey())) { //�޴����� �޴���� �ֹ��� �޴����� ��ġ�Ѵٸ�
									totalPrice+=Integer.parseInt(menuName[j+1])*entry.getValue();  // �ֹ��� �޴�
								}
//				     unit_test	System.out.println("key:"+entry.getKey()+" "+"value:"+entry.getValue());
							}
						}
						tableManager.print(Integer.parseInt(tableNum));		
						System.out.println("�� �ݾ�:"+totalPrice+"��");
						bufReader.close();
						System.out.println("����� �ݾ��� �Է��ϼ���.");
					    payNum=scan.nextLine();
						payNumCheck(payNum);
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(e);
					}
				} 
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			System.out.println("���� ���̺��Դϴ�. 1 - 6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
			pay(tableNum);
		}
		return "�ѱݾ�"+totalPrice;
	}
	private String payNumCheck(String payNum) { // ������ �ݾ��� ���ڷι޾� �����ϴ� �Լ�
		if(StringChecker.checkExit(payNum)) { //exit �Է����� üũ
			goMain();
		}
		if(StringChecker.checkPayPrice(payNum)) {
			if(totalPrice<Integer.parseInt(payNum)) {
				System.out.println("����� �Ϸ�Ǿ����ϴ�.");
				System.out.println("�Ž������� "+Integer.toString(Integer.parseInt(payNum)-totalPrice)+"�Դϴ�.");
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear(); //��� �Ϸ�� ���̺��� �ֹ� ������ �����Ѵ�.
			}
			else if(totalPrice==Integer.parseInt(payNum)) {
				System.out.println("����� �Ϸ�Ǿ����ϴ�.");
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear(); //��� �Ϸ�� ���̺��� �ֹ� ������ �����Ѵ�.
			}
			else if(totalPrice>Integer.parseInt(payNum)) {
				System.out.println("�ݾ��� ���ڶ��ϴ�. �ѱݾ�"+totalPrice+"�� �Է��ϼ���.");
			    payNum=scan.nextLine();
				payNumCheck(payNum);
			}
			
		}
		else {
			System.out.println("�ٽ� �Է����ּ���.");
			payNum=scan.nextLine();
			payNumCheck(payNum);
		}
		goMain();
		return "true";
			}
	private boolean goMenu3() { //3. ���
		System.out.println("1-6 ���� ���̺� ��ȣ�� �Է��� �ּ���.");
		tableNum=scan.nextLine();
		if(StringChecker.checkExit(tableNum)) {
			return goMain();
		}
		if(!StringChecker.checkTableNum(tableNum)) {
			System.out.println("���� ���̺��Դϴ�.");
			goMenu3();
		}
		pay(tableNum);
		return goMain();
	}
	private boolean goMenu4() { //4. �޴� ����
		filemanager.readFile(filePath);
		System.out.println("1. �޴� �߰�"+"\n"+"2. �޴� ����");
		String modeNum=scan.nextLine();
		if(StringChecker.checkExit(modeNum)) { //exit �Է½� �������� 
			return goMain();
		}
		if(modeNum.equals("1")||modeNum.equals("2")) { //1,2 ���� ����ó�� 
			selMode(Integer.parseInt(modeNum));			
		}else {
			System.out.println("�ٽ� �Է����ּ���.");
			goMenu4();
		}
		return goMain();
	}
}
