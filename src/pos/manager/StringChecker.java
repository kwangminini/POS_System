package pos.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



import pos.scene.MainScene;

public class StringChecker {
	
	public static boolean checkMain(String main) {//1~5 check
	      if(!main.matches("^[1-5]{1}$")) {
	         
	         return false;
	      }
	      return true;
	   }
	public static boolean checkTableNum(String tablenum) {//1~6 check && exit check
	      if(!tablenum.matches("^[1-6]{1}$")&&!tablenum.matches("/exit/")) {
	         
	         return false;
	      }
	      
	      return true;
	   }
	public static boolean checkExit(String exit) {//exit check
	      if(!exit.matches("exit")) {
	       
	         return false;
	      }
	      
	      return true;
	   }
	@SuppressWarnings("unused")

	public static boolean checkMenu(String menu) {//메뉴판에 있는 메뉴인지 확인
		//String[] menulist= new String[100];
		 if(menu.length()>20) { //메뉴명이 20글자 이상인지 체크
        	 System.out.println("메뉴명은 20글자 이내의 한글을 입력해야합니다.");
        	 return false;
         }
		String menulist="";
		try {
			File file = new File("C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt");
			FileReader filereader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(filereader);
			String line="";
			try {
				int i=0;
				while((line=bufReader.readLine())!=null) { //메뉴리스트 String에 txt파일의 내용을 저장
					menulist+=line+" ";
					i++;
				}
				//unit_test System.out.println(menulist);
				String[] menuName=menulist.split(" ");
				//unit_testSystem.out.println(menuName.length);
				for(int j=0;j<menuName.length;j=j+2) {
					//unit_test System.out.println(menuName[j]+" "+menu);
					if(menuName[j].equals(menu)) { //메뉴판에 있는 메뉴라면 true를 반환한다.
						bufReader.close();
						return true;
					}
				}
				bufReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static boolean checkQuantity(String menu) {//입력받은 메뉴 수량이 올바른지 체크 하는 함수
		//String[] menulist= new String[100];
		if(menu.length()>1) { //02, 03 이런 수량 앞에 0 붙는걸 방지
			if(Integer.parseInt(menu.substring(0,1))==0) {
				return false;
			}
		}
		if(Integer.parseInt(menu)>0&&Integer.parseInt(menu)<=50) {
			return true;
		}else
			return false;
	}
	public static boolean checkMname(String mname) { //메뉴 추가의 메뉴명 조건을 체크하는 함수
		MainScene main=new MainScene();
		for(int i=0;i<mname.length();i++) { //mname문자열에 공백이 있는지 체크
			if(mname.charAt(i)==' ') {
				return false;
			}
		}
		 if(!mname.matches("exit")) { //문구가 exit 인지 체크하는 함수
	         if(mname.length()>20) { //메뉴명이 20글자 이상인지 체크
	        	 System.out.println("메뉴명은 20글자 이내의 한글을 입력해야합니다.");
	        	 return false;
	         }
	         else if(!mname.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) { //한글로 이루어졌는지 치크
	        	 System.out.println("메뉴명은 20글자 이내의 한글을 입력해야합니다.");
	        	 return false;
	         }
	         else
	        	 return true;
	      }  
		 else {
			 main.goMain();
		 }
		return true;
	   
	}
	public static boolean checkPrice(String price) { //가격의 입력 조건이 맞는지 체크하는 함수
		MainScene main=new MainScene();
		if(price.matches("exit")) { //입력받은 문구가 exit 인지 확인
			main.goMain();
		}
		if(!price.matches("^[0-9]*$")) { //숫자로 이루어졌는지 체크
//			System.out.println("유효하지 않은 가격입니다. 5자리 이내의 양의정수를 입력하세요.");
			return false;
		}
		if(price.length()>5) { //5자리 이내인지 체크
//			System.out.println("유효하지 않은 가격입니다. 5자리 이내의 양의정수를 입력하세요.");
			return false;
		}
		if(price.charAt(0)=='0') { //가격의 맨앞이 0 으로시작하면 안되는것을 체크
//			System.out.println("유효하지 않은 가격입니다. 5자리 이내의 양의정수를 입력하세요.");
			return false;
		}
		return true;
	}
	public static boolean checkPayPrice(String payNum) { // 계산에 필요한 금액의 형태가 올바른지 체크
		if(!payNum.matches("^[0-9]*$")) { //숫자로 이루어졌는지 체크
			System.out.println("7자리 이하의 양의 정수를 입력하세요.");
			return false;
		}
		if(payNum.charAt(0)=='0') { //가격의 맨앞이 0 으로시작하면 안되는것을 체크
			System.out.println("7자리 이하의 양의 정수를 입력하세요.");
			return false;
		}
		if(payNum.length()>7) { //메뉴명이 20글자 이상인지 체크
       	 System.out.println("7자리 이하의 양의 정수를 입력하세요.");
       	 return false;
        }
		if(Integer.parseInt(payNum)>0&&Integer.parseInt(payNum)<=10000000) {
			return true;
		}
        return true;
	}

}
