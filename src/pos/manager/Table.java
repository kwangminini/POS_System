package pos.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
public class Table {

	public ArrayList<HashMap<String, Integer>> orderedMenu=new ArrayList<HashMap<String, Integer>>();//주문 메뉴의 리스트안에 메뉴명, 수량을 hashamp으로 입력
	public HashMap<String, Integer> map=new HashMap<String,Integer>(); //메뉴명과 개수를 hashmap처리
	
	public void addTable(String ordermenu,int num) { //해당 테이블에 메뉴와 수량을 추가를 해주는 함수
		
		map.put(ordermenu, map.containsKey(ordermenu)?map.get(ordermenu)+num:num); //메뉴명과 수량을 추가하는데 메뉴명이 동일한게 또 주문되었다면 수량만 +				
		orderedMenu.add(map); //주문 메뉴 리스트에 메뉴명, 수량 추가
		}
	public ArrayList<HashMap<String, Integer>> getOrdedmenu() { //주문메뉴 확인용 
		return this.orderedMenu;
	}
	public void show() { //주문한 메뉴리스트를 보여준다
//		unit_test System.out.println("size:"+orderedMenu.size());
//		unit_test for(String key : map.keySet()) {
//			System.out.println(key+" "+map.get(key));
//		}
		for(int i=0;i<orderedMenu.size();i++) { 
			for(Entry<String,Integer> map:orderedMenu.get(i).entrySet()) {
				System.out.println(map.getKey()+" "+map.getValue());
			}
			if(i==0)
				break;
		}
	}

	
}	
