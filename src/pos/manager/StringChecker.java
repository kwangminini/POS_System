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

	public static boolean checkMenu(String menu) {//¸Ş´ºÆÇ¿¡ ÀÖ´Â ¸Ş´ºÀÎÁö È®ÀÎ
		//String[] menulist= new String[100];
		 if(menu.length()>20) { //¸Ş´º¸íÀÌ 20±ÛÀÚ ÀÌ»óÀÎÁö Ã¼Å©
        	 System.out.println("¸Ş´º¸íÀº 20±ÛÀÚ ÀÌ³»ÀÇ ÇÑ±ÛÀ» ÀÔ·ÂÇØ¾ßÇÕ´Ï´Ù.");
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
				while((line=bufReader.readLine())!=null) { //¸Ş´º¸®½ºÆ® String¿¡ txtÆÄÀÏÀÇ ³»¿ëÀ» ÀúÀå
					menulist+=line+" ";
					i++;
				}
				//unit_test System.out.println(menulist);
				String[] menuName=menulist.split(" ");
				//unit_testSystem.out.println(menuName.length);
				for(int j=0;j<menuName.length;j=j+2) {
					//unit_test System.out.println(menuName[j]+" "+menu);
					if(menuName[j].equals(menu)) { //¸Ş´ºÆÇ¿¡ ÀÖ´Â ¸Ş´º¶ó¸é true¸¦ ¹İÈ¯ÇÑ´Ù.
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
	
	
	public static boolean checkQuantity(String menu) {//ÀÔ·Â¹ŞÀº ¸Ş´º ¼ö·®ÀÌ ¿Ã¹Ù¸¥Áö Ã¼Å© ÇÏ´Â ÇÔ¼ö
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
	public static boolean checkMname(String mname) { //¸Ş´º Ãß°¡ÀÇ ¸Ş´º¸í Á¶°ÇÀ» Ã¼Å©ÇÏ´Â ÇÔ¼ö
		MainScene main=new MainScene();
		for(int i=0;i<mname.length();i++) { //mname¹®ÀÚ¿­¿¡ °ø¹éÀÌ ÀÖ´ÂÁö Ã¼Å©
			if(mname.charAt(i)==' ') {
				return false;
			}
		}
		 if(!mname.matches("exit")) { //¹®±¸°¡ exit ÀÎÁö Ã¼Å©ÇÏ´Â ÇÔ¼ö
	         if(mname.length()>20) { //¸Ş´º¸íÀÌ 20±ÛÀÚ ÀÌ»óÀÎÁö Ã¼Å©
	        	 System.out.println("¸Ş´º¸íÀº 20±ÛÀÚ ÀÌ³»ÀÇ ÇÑ±ÛÀ» ÀÔ·ÂÇØ¾ßÇÕ´Ï´Ù.");
	        	 return false;
	         }
	         else if(!mname.matches(".*[¤¡-¤¾¤¿-¤Ó°¡-ÆR]+.*")) { //ÇÑ±Û·Î ÀÌ·ç¾îÁ³´ÂÁö Ä¡Å©
	        	 System.out.println("¸Ş´º¸íÀº 20±ÛÀÚ ÀÌ³»ÀÇ ÇÑ±ÛÀ» ÀÔ·ÂÇØ¾ßÇÕ´Ï´Ù.");
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
	public static boolean checkPrice(String price) { //°¡°İÀÇ ÀÔ·Â Á¶°ÇÀÌ ¸Â´ÂÁö Ã¼Å©ÇÏ´Â ÇÔ¼ö
		MainScene main=new MainScene();
		if(price.matches("exit")) { //ÀÔ·Â¹ŞÀº ¹®±¸°¡ exit ÀÎÁö È®ÀÎ
			main.goMain();
		}
		if(!price.matches("^[0-9]*$")) { //¼ıÀÚ·Î ÀÌ·ç¾îÁ³´ÂÁö Ã¼Å©
//			System.out.println("À¯È¿ÇÏÁö ¾ÊÀº °¡°İÀÔ´Ï´Ù. 5ÀÚ¸® ÀÌ³»ÀÇ ¾çÀÇÁ¤¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return false;
		}
		if(price.length()>5) { //5ÀÚ¸® ÀÌ³»ÀÎÁö Ã¼Å©
//			System.out.println("À¯È¿ÇÏÁö ¾ÊÀº °¡°İÀÔ´Ï´Ù. 5ÀÚ¸® ÀÌ³»ÀÇ ¾çÀÇÁ¤¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return false;
		}
		if(price.charAt(0)=='0') { //°¡°İÀÇ ¸Ç¾ÕÀÌ 0 À¸·Î½ÃÀÛÇÏ¸é ¾ÈµÇ´Â°ÍÀ» Ã¼Å©
//			System.out.println("À¯È¿ÇÏÁö ¾ÊÀº °¡°İÀÔ´Ï´Ù. 5ÀÚ¸® ÀÌ³»ÀÇ ¾çÀÇÁ¤¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return false;
		}
		return true;
	}
	public static boolean checkPayPrice(String payNum) { // °è»ê¿¡ ÇÊ¿äÇÑ ±İ¾×ÀÇ ÇüÅÂ°¡ ¿Ã¹Ù¸¥Áö Ã¼Å©
		if(!payNum.matches("^[0-9]*$")) { //¼ıÀÚ·Î ÀÌ·ç¾îÁ³´ÂÁö Ã¼Å©
			System.out.println("7ÀÚ¸® ÀÌÇÏÀÇ ¾çÀÇ Á¤¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return false;
		}
		if(payNum.charAt(0)=='0') { //°¡°İÀÇ ¸Ç¾ÕÀÌ 0 À¸·Î½ÃÀÛÇÏ¸é ¾ÈµÇ´Â°ÍÀ» Ã¼Å©
			System.out.println("7ÀÚ¸® ÀÌÇÏÀÇ ¾çÀÇ Á¤¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			return false;
		}
		if(payNum.length()>7) { //¸Ş´º¸íÀÌ 20±ÛÀÚ ÀÌ»óÀÎÁö Ã¼Å©
       	 System.out.println("7ÀÚ¸® ÀÌÇÏÀÇ ¾çÀÇ Á¤¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
       	 return false;
        }
		if(Integer.parseInt(payNum)>0&&Integer.parseInt(payNum)<=10000000) {
			return true;
		}
        return true;
	}

}
