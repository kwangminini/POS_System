package pos.manager;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {

	public ArrayList<HashMap<String, Integer>> orderedMenu=new ArrayList<HashMap<String, Integer>>();//�ֹ� �޴��� ����Ʈ�ȿ� �޴��� ������ hashamp���� �Է�
	public HashMap<String, Integer> map=new HashMap<String,Integer>(); //�޴���� ������ hashmapó��
	
	public void addTable(String ordermenu,int num) { //�ش� ���̺� �޴��� ������ �߰��� ���ִ� �Լ�
		
		map.put(ordermenu, map.containsKey(ordermenu)?map.get(ordermenu)+num:num); //�޴���� ������ �߰��ϴµ� �޴����� �����Ѱ� �� �ֹ��Ǿ��ٸ� ������ +				
		orderedMenu.add(map); //�ֹ� �޴� ����Ʈ�� �޴���, ���� �߰�
		}
	public ArrayList<HashMap<String, Integer>> getOrdedmenu() { //�ֹ��޴� Ȯ�ο� 
		return this.orderedMenu;
	}
	public void show() { //�ֹ��� �޴�����Ʈ�� �����ش�
		//unit_test System.out.println("size:"+orderedMenu.size());
		for(String key : map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}
	}

	
}	
