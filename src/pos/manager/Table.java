package pos.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
public class Table {

	public ArrayList<HashMap<String, Integer>> orderedMenu=new ArrayList<HashMap<String, Integer>>();//�ֹ� �޴��� ����Ʈ�ȿ� �޴���, ������ hashamp���� �Է�
	public HashMap<String, Integer> map=new HashMap<String,Integer>(); //�޴���� ������ hashmapó��
	
	public void addTable(String ordermenu,int num) { //�ش� ���̺� �޴��� ������ �߰��� ���ִ� �Լ�
		
		map.put(ordermenu, map.containsKey(ordermenu)?map.get(ordermenu)+num:num); //�޴���� ������ �߰��ϴµ� �޴����� �����Ѱ� �� �ֹ��Ǿ��ٸ� ������ +				
		orderedMenu.add(map); //�ֹ� �޴� ����Ʈ�� �޴���, ���� �߰�
		}
	public ArrayList<HashMap<String, Integer>> getOrdedmenu() { //�ֹ��޴� Ȯ�ο� 
		return this.orderedMenu;
	}
	public void show() { //�ֹ��� �޴�����Ʈ�� �����ش�
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
