package pos.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileManager { //�޴����� ����ϴ� Ŭ����
	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt"; //�޴��� �ؽ�Ʈ���� ��� ����(���� ��ǻ�Ϳ� �°� ����)
	public void readFile(String filePath) { //�ش� txt������ �о���� �Լ�
		File file = new File(filePath);
		try {
			Scanner scan=new Scanner(file);
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine()); //txt������ ���پ� �о ����Ѵ�.
			}
		
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
