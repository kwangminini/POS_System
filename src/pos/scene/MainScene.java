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
	String tableNum=""; //테이블 번호
	String movetableNum=""; //이동할 테이블 번호 설계서에 전역변수 추가 
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	FileManager filemanager=new FileManager();
	TableManager tableManager=new TableManager();
	Scanner scan=new Scanner(System.in);
	public void doProcess() { //종료 전까지 메인화면 무한 출력을 위한 함수
		while(this.goMain()) {};
	}
	public boolean goMain() {//메인화면 UI
		System.out.println("1. 주문\n"+"2. 테이블\n"+"3. 계산\n"+"4. 메뉴관리\n"+"5. 종료");
		String input=this.scan.nextLine();
		if(StringChecker.checkMain(input)) {
		switch(Integer.parseInt(input)) {
		case 1:
			return goMenu1();
			//unit_test System.out.println("주문");
			//unit_test filemanager.readFile(filePath);
		case 2:
			//unit_test System.out.println("테이블");
			return goMenu2();
		case 3:
			System.out.println("계산");
			break;
		case 4:
			//unit_test System.out.println("메뉴관리");
			return goMenu4();
		case 5:
			System.out.println("종료");
			break;
			default:
				System.out.println("디폴트");
		}
		}
		else {
			System.out.println("1-5 내의 숫자를 입력해주세요.");
		}
		return true;
	}
	public boolean goMenu1() { //1번 메뉴 주문
		System.out.println("1~6 사이의 테이블 번호를 입력하세요.");
		tableNum=this.scan.nextLine();
		if(StringChecker.checkExit(tableNum)) { //exit 입력인지 체크
			return goMain();
		}
		if(StringChecker.checkTableNum(tableNum)) { //테이블 번호가 올바른 입력인지 체크
			//unit_test System.out.println("menu1");
			
			return goOrder(Integer.parseInt(tableNum));
		}
		else {
			System.out.println("없는 테이블입니다. 1 - 6 내의 테이블 번호를 입력해 주세요.");
			return goMenu1();
		}
	}
	public boolean goOrder(int tableNum) {
		filemanager.readFile(filePath); //메뉴판 출력
		String menu="";
		//unit_testSystem.out.println(tablenum);
		int order_tablenum=tableNum;
		System.out.println("주문하실 메뉴와 수량을 입력하세요.");
		menu=this.scan.nextLine();
		if(StringChecker.checkExit(menu)) {
			return goMain();
		}
		String[] menu_array=menu.split(" ");
		for(int i=0;i<menu_array.length;i=i+2) { //입력받은 메뉴명들이 메뉴판에 있는지 체크
		if(!StringChecker.checkMenu(menu_array[i])) {
			System.out.println("없는 메뉴입니다. 다시 입력해주세요.");
			return goOrder(order_tablenum);
		}
		}
		for(int i=1;i<menu_array.length;i=i+2) { //입력받은 메뉴의 수량이 올바른 입력인지 체크
			if(!StringChecker.checkQuantity(menu_array[i])) {
				System.out.println("유효하지 않는 수량입니다. 50내의 수량을 입력해 주세요.");
				return goOrder(order_tablenum);
			}
		}
		for(int i=0;i<menu_array.length;i=i+2) { //테이블에 메뉴와 수량 추가
		tableManager.add(menu_array[i],Integer.parseInt(menu_array[i+1]),tableNum);
		}
		System.out.println("주문이 완료되었습니다.");
		tableManager.print(tableNum);
		return goMain();
		//unit_test System.out.println(StringChecker.checkMenu(menu));
		//unit_test return true;
	}
	public void tableState(String tableNum) { //테이블 번호 stringcheck 해주는 함수
		if(StringChecker.checkExit(tableNum)) {
			 goMain();
		}
		if(StringChecker.checkTableNum(tableNum)) {
			//unit_test System.out.println("tableNum");
			if(tableManager.table[Integer.parseInt(tableNum)].getOrdedmenu().size()==0) {
				System.out.println("테이블 기능을 사용하실 수 없습니다.");
				goMain();
			}
			System.out.println(tableNum+"의 주문내역");
			tableManager.print(Integer.parseInt(tableNum));
			}
		else {
			System.out.println("없는 테이블입니다. 1 - 6 내의 테이블 번호를 입력해 주세요.");
			goMenu2();
			}
	}
	private boolean MoveOrSum(String tableNum) { // 기능번호가 유효한지 stringcheck
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
	private void moveOrSum(String functionNum) { //테이블의 기능을 호출하는 함수
		System.out.println("1. 테이블 이동"+"\n"+"2. 테이블 그룹화");
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
			System.out.println("다시 입력해 주세요.");
			moveOrSum(tableNum);
		}
	}
	public boolean goMenu2() { //2. 테이블 선택시 호출되는 함수
	
		System.out.println("1-6 내의 테이블 번호를 입력해 주세요.");
		tableNum=scan.nextLine();
		tableState(tableNum); 
		moveOrSum(tableNum); 
		return goMain();
	}
	private boolean NullCheck() { //
		if(tableManager.table[Integer.parseInt(movetableNum)].getOrdedmenu().size()==0) { //해당 테이블의 주문내역이 없는지확인
			return true;
		}else {
			return false;
		}
	}
	private void moveTable() { //1. 테이블 이동 선택시 호출되는 함수
		System.out.println("1-6 내의 이동할 테이블 번호를 입력하세요.");
		movetableNum=scan.nextLine();
		if(StringChecker.checkExit(movetableNum)) {
			goMain();
		}
		if(StringChecker.checkTableNum(movetableNum)) {
			if(NullCheck()) { //이동하려는 테이블이 비어있는지 확인하는 함수
				//unit_test System.out.println("이동가능");
				tableManager.table[Integer.parseInt(movetableNum)].orderedMenu=(ArrayList<HashMap<String, Integer>>)tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clone();
				//기존 테이블의 주문내역을 이동한 테이블로 이동시킨다.
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear();
				//이동한 테이블의 주문내역을 삭제한다.
				System.out.println("이동하였습니다.");
				goMain();
			}else
				
			{
				//unit_test System.out.println("이동불가능");
				System.out.println("사용중인 테이블입니다. 다른 테이블 번호를 입력해주세요.");
				moveTable();
			}
		}else {	
			System.out.println("없는 테이블입니다. 1-6 내의 테이블 번호를 입력해 주세요.");
			moveTable();
		}
		
	}
	private void addMenu(String mname,String price) { //메뉴판에 메뉴 추가
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
			System.out.println("메뉴이름, 가격을 공백 기준으로 연속하여 입력해주세요.");
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
			if(StringChecker.checkMenu(mname)) { //추가할 메뉴가 이미 존재하는지 체크
				System.out.println("이미 존재하는 메뉴입니다.");
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
			System.out.println("다시 입력해주세요.");
			selMode(modeNum);
		}
	}
	private boolean goMenu4() { //4. 메뉴 관리
		filemanager.readFile(filePath);
		System.out.println("1. 메뉴 추가"+"\n"+"2. 메뉴 삭제");
		String modeNum=scan.nextLine();
		selMode(Integer.parseInt(modeNum));
		return goMain();
	}
}
