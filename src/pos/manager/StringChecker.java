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

	public static boolean checkMenu(String menu) {//¸Þ´ºÆÇ¿¡ ÀÖ´Â ¸Þ´ºÀÎÁö È®ÀÎ
		//String[] menulist= new String[100];
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
					//unit_test System.out.println(menuName[j]+" "+menu);
					if(menuName[j].equals(menu)) {
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
	
	
	public static boolean checkQuantity(String menu) {//¸Þ´ºÆÇ¿¡ ÀÖ´Â ¸Þ´ºÀÎÁö È®ÀÎ
		//String[] menulist= new String[100];
		if(menu.length()>1) { //02, 03 ÀÌ·± ¼ö·® ¾Õ¿¡ 0 ºÙ´Â°É ¹æÁö
			if(Integer.parseInt(menu.substring(0,1))==0) {
				return false;
			}
		}
		if(Integer.parseInt(menu)>0&&Integer.parseInt(menu)<=50) {
			return true;
		}else
			return false;
	}
	public static boolean checkMname(String mname) {
		MainScene main=new MainScene();
		for(int i=0;i<mname.length();i++) { //mname¹®ÀÚ¿­¿¡ °ø¹éÀÌ ÀÖ´ÂÁö Ã¼Å©
			if(mname.charAt(i)==' ') {
				return false;
			}
		}
		 if(!mname.matches("exit")) {   
	         if(mname.length()>20) {
	        	 return false;
	         }
	         else if(!mname.matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR]+.*")) { //ÇÑ±Û·Î ÀÌ·ç¾îÁ³´ÂÁö
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

}
