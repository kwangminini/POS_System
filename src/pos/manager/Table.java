package pos.manager;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {
	//private String ordermenu;
	private ArrayList<HashMap<String, Integer>> orderedMenu=new ArrayList<HashMap<String, Integer>>();
	private HashMap<String, Integer> map=new HashMap<String,Integer>();
	//ArrayList<HashMap<String, Integer>> orderedMenu=new ArrayList<HashMap<String,Integer>>();
	
	public void addTable(String ordermenu,int num) {
		
		map.put(ordermenu, map.containsKey(ordermenu)?map.get(ordermenu)+num:num);				
		orderedMenu.add(map);
		}
	public ArrayList<HashMap<String, Integer>> getOrdedmenu() {
		return this.orderedMenu;
	}
	public void show() {
		System.out.println("size:"+orderedMenu.size());
		for(String key : map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}
	}
	
//	HashMap<String, Integer> map = new HashMap<String,Integer>();
//	Table[] tableArray=new Table[6];
//	public void addMenu(String mname,int quant) {
//		map.put(mname, quant);
//		orderedMenu.add(map);
//	}
	
}	
