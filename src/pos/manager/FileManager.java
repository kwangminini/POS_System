package pos.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileManager { //메뉴판을 담당하는 클래스
	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt"; //메뉴판 텍스트파일 경로 설정(각자 컴퓨터에 맞게 세팅)
	public void readFile(String filePath) { //해당 txt파일을 읽어오는 함수
		File file = new File(filePath);
		try {
			Scanner scan=new Scanner(file);
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine()); //txt파일을 한줄씩 읽어서 출력한다.
			}
		
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
