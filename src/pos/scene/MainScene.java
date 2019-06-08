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
	String tableNum=""; //테이블 번호
	String movetableNum=""; //이동할 테이블 번호 설계서에 전역변수 추가 
	String payNum=""; //지불할 금액
	int totalPrice=0; //주문한 메뉴들의 총 가격
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	FileManager filemanager=new FileManager();
	TableManager tableManager=new TableManager();
	Table tableClass=new Table();
	Scanner scan=new Scanner(System.in);
	public void doProcess() { //종료 전까지 메인화면 무한 출력을 위한 함수
		while(this.goMain()) {
		};
	}
	public boolean goMain() {//메인화면 UI
		System.out.println("1. 주문\n"+"2. 테이블\n"+"3. 계산\n"+"4. 메뉴관리\n"+"5. 종료");
		String input=this.scan.nextLine();
		if(StringChecker.checkMain(input)) {
		switch(Integer.parseInt(input)) {
		case 1:
			return goMenu1(); //주문 기능으로 이동
			//unit_test System.out.println("주문");
			//unit_test filemanager.readFile(filePath);
		case 2:
			//unit_test System.out.println("테이블");
			return goMenu2(); //테이블 기능으로 이동
		case 3:
			//unit_test System.out.println("계산");
			return goMenu3(); //계산 기능으로 이동
		case 4:
			//unit_test System.out.println("메뉴관리");
			return goMenu4(); //메뉴관리 기능으로 이동
		case 5:
			System.out.println("종료");
			return false;
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
			System.out.println("없는 테이블입니다.");
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
		if(StringChecker.checkExit(menu)) { //exit 입력받았는지 체크
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
			System.out.println("없는 테이블입니다.");
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
		if(MoveOrSum(num)) {
			if(Integer.parseInt(num)==1) {
				moveTable();
			}
			else if(Integer.parseInt(num)==2) {
				sumTable();
			}
		}
		else {
			System.out.println("다시 입력해 주세요.");
			moveOrSum(tableNum);
		}
	}
	private void sumTable() {
		System.out.println("1-6 내의 테이블 번호를 입력해 주세요.");
		movetableNum=scan.nextLine();
		if(StringChecker.checkExit(movetableNum)) { //exit 문구 받으면 메인으로
			goMain();
		}
		if(StringChecker.checkTableNum(movetableNum)) { 
			if(NotNullCheck()) { //비어있는 테이블인지 확인
				for(Map.Entry<String, Integer> elem:tableManager.table[Integer.parseInt(tableNum)].map.entrySet()) { //기존테이블의 주문메뉴와 수량을 그룹화 테이블의 hashmap에 추가
					String key=elem.getKey();
					int value=elem.getValue();
					tableManager.table[Integer.parseInt(movetableNum)].addTable(key, value);//기존 테이블의 메뉴를 그룹화할 테이블로 이동시킨다.
					tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear();//기존 테이블의 주문내역을 삭제한다.
					}
				System.out.println("테이블 그룹화가 완료되었습니다.");
				//unit_test System.out.println("합석 가능한 테이블");
			}
			else {
				System.out.println("빈 테이블입니다. 다른 테이블 번호를 입력해 주세요.");
				sumTable();
			}
		}
		else {
			System.out.println("없는 테이블입니다.");
			sumTable();
		}
	}
	public boolean goMenu2() { //2. 테이블 선택시 호출되는 함수
		System.out.println("1-6 내의 테이블 번호를 입력해 주세요.");
		tableNum=scan.nextLine();
		tableState(tableNum); 
		moveOrSum(tableNum); 
		return goMain();
	}
	private boolean NullCheck() { //해당 테이블이 비어있는지 확인 비어있으면 true
		if(tableManager.table[Integer.parseInt(movetableNum)].getOrdedmenu().size()==0) { //해당 테이블의 주문내역이 없는지확인
			return true;
		}
		else {
			return false;
		}
	}
	private boolean NotNullCheck() { //해당 테이블이 안비어있는지 확인 비어있으면 false
		if(tableManager.table[Integer.parseInt(movetableNum)].getOrdedmenu().size()==0) { //해당 테이블의 주문내역이 없는지확인
			return false;
		}
		else {
			return true;
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
				for(Map.Entry<String, Integer> entry:tableManager.table[Integer.parseInt(tableNum)].map.entrySet()) {
					tableManager.table[Integer.parseInt(movetableNum)].map.put(entry.getKey(), entry.getValue());
				}//기존 테이블의 주문내역을 이동한 테이블로 이동시킨다.
				tableManager.table[Integer.parseInt(movetableNum)].orderedMenu.add(tableManager.table[Integer.parseInt(movetableNum)].map);
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear();//이동한 테이블의 주문내역을 삭제한다.
				tableManager.table[Integer.parseInt(tableNum)].map.clear();//이동한 테이블의 주문내역을 삭제한다.
				System.out.println("이동하였습니다.");	
				goMain();
			}
			else
			{
				//unit_test System.out.println("이동불가능");
				System.out.println("사용중인 테이블입니다. 다른 테이블 번호를 입력해주세요.");
				moveTable();
			}
		}
		else {	
			System.out.println("없는 테이블입니다.");
			moveTable();
		}
		
	}
	private void addMenu(String mname,String price) { //메뉴판에 메뉴 추가
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

		private void selMode(int modeNum) { //메뉴추가 및 삭제를 다루는 함수
		switch (modeNum) {
		case 1: //메뉴 추가
			System.out.println("메뉴이름, 가격을 공백 기준으로 연속하여 입력해주세요.");
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
					System.out.println("유효하지 않은 가격입니다. 5자리 이내의 양의정수를 입력하세요.");
					selMode(1);
					
				}
			}
			else {
				System.out.println("false");
			}
		case 2: //메뉴삭제
			System.out.println("삭제할 메뉴명을 입력하세요.");
			String del_mname=scan.nextLine();
			if(StringChecker.checkMenu(del_mname)) { //추가할 메뉴가 이미 존재하는지 체크
				delMenu(del_mname);
			}
			if(StringChecker.checkExit(del_mname)) {
				goMain();
			}
			else {
				System.out.println("없는 메뉴입니다. 다시입력해주세요.");
				selMode(2);
			}
			break;
//			unit_test	for(int j=0;j<s.length;j++) {
//				System.out.println(s[j]);
//			}

		default:
			System.out.println("다시 입력해주세요."); 
			goMenu4();
		}
	}
	private void delMenu(String mname) { //메뉴 삭제하는 함수
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
					//unit_test System.out.println("한줄"+sLine);
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
			if(s[i].contains(mname)) {//삭제할 메뉴명이 들어가 있으면 그 메뉴 삭제
				s[i]=null;
				break;
			}
			i++;
		}
		//메뉴 수정한것 파일 다시쓰기
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
	private String pay(String tableNum) { //계산 함수
		
		if(StringChecker.checkExit(tableNum)) { //exit 입력인지 체크
			goMain();
		}
		if(StringChecker.checkTableNum(tableNum)) { //테이블 번호가 올바른 입력인지 체크
			if(tableManager.table[Integer.parseInt(tableNum)].getOrdedmenu().size()==0) { //해당 테이블의 주문내역이 없는지확인
				System.out.println("빈테이블입니다. 다른 테이블 번호를 입력해주세요.");			
			}
			else {
				HashMap<String, Integer> pay_list=new HashMap<String, Integer>(); //txt파일의 메뉴와 주문한 메뉴를 비교하기위해 주문한 메뉴들을 hashmap으로 생성
				for(Map.Entry<String, Integer> elem:tableManager.table[Integer.parseInt(tableNum)].map.entrySet()) { //기존테이블의 주문메뉴와 수량을 그룹화 테이블의 hashmap에 추가
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
								if(menuName[j].equals(entry.getKey())) { //메뉴판의 메뉴명과 주문한 메뉴명이 일치한다면
									totalPrice+=Integer.parseInt(menuName[j+1])*entry.getValue();  // 주문한 메뉴
								}
//				     unit_test	System.out.println("key:"+entry.getKey()+" "+"value:"+entry.getValue());
							}
						}
						tableManager.print(Integer.parseInt(tableNum));		
						System.out.println("총 금액:"+totalPrice+"원");
						bufReader.close();
						System.out.println("계산할 금액을 입력하세요.");
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
			System.out.println("없는 테이블입니다. 1 - 6 내의 테이블 번호를 입력해 주세요.");
			pay(tableNum);
		}
		return "총금액"+totalPrice;
	}
	private String payNumCheck(String payNum) { // 지불할 금액을 인자로받아 실행하는 함수
		if(StringChecker.checkExit(payNum)) { //exit 입력인지 체크
			goMain();
		}
		if(StringChecker.checkPayPrice(payNum)) {
			if(totalPrice<Integer.parseInt(payNum)) {
				System.out.println("계산이 완료되었습니다.");
				System.out.println("거스름돈은 "+Integer.toString(Integer.parseInt(payNum)-totalPrice)+"입니다.");
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear(); //계산 완료된 테이블의 주문 내역을 삭제한다.
			}
			else if(totalPrice==Integer.parseInt(payNum)) {
				System.out.println("계산이 완료되었습니다.");
				tableManager.table[Integer.parseInt(tableNum)].orderedMenu.clear(); //계산 완료된 테이블의 주문 내역을 삭제한다.
			}
			else if(totalPrice>Integer.parseInt(payNum)) {
				System.out.println("금액이 모자랍니다. 총금액"+totalPrice+"를 입력하세요.");
			    payNum=scan.nextLine();
				payNumCheck(payNum);
			}
			
		}
		else {
			System.out.println("다시 입력해주세요.");
			payNum=scan.nextLine();
			payNumCheck(payNum);
		}
		goMain();
		return "true";
			}
	private boolean goMenu3() { //3. 계산
		System.out.println("1-6 내의 테이블 번호를 입력해 주세요.");
		tableNum=scan.nextLine();
		if(StringChecker.checkExit(tableNum)) {
			return goMain();
		}
		if(!StringChecker.checkTableNum(tableNum)) {
			System.out.println("없는 테이블입니다.");
			goMenu3();
		}
		pay(tableNum);
		return goMain();
	}
	private boolean goMenu4() { //4. 메뉴 관리
		filemanager.readFile(filePath);
		System.out.println("1. 메뉴 추가"+"\n"+"2. 메뉴 삭제");
		String modeNum=scan.nextLine();
		if(StringChecker.checkExit(modeNum)) { //exit 입력시 메인으로 
			return goMain();
		}
		if(modeNum.equals("1")||modeNum.equals("2")) { //1,2 외의 예외처리 
			selMode(Integer.parseInt(modeNum));			
		}else {
			System.out.println("다시 입력해주세요.");
			goMenu4();
		}
		return goMain();
	}
}
