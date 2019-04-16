package pos.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager { //메뉴판을 담당하는 클래스
	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	//String pathName="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src"; 
	//String fileName="menulist.txt"; 
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt"; //메뉴판 텍스트파일 경로 설정
	public void readFile(String filePath) { //해당 txt파일을 읽어오는 함수
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(filePath);
			br=new BufferedReader(fr);
			String data;
			while((data=br.readLine())!=null) {
				System.out.println(data+" "); //txt파일 안의 문자열을 출력
			}
			System.out.println();
			
		} catch (Exception e) { 
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			if(fr!=null) {
				try {
					fr.close();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

}
