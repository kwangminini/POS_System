package pos.manager;

public class TableManager {
	public Table[] table=new Table[7]; //���̺� �迭 ����

	public TableManager() { //1~6���� ���̺��� ����
		table[1]=new Table();
		table[2]=new Table();
		table[3]=new Table();
		table[4]=new Table();
		table[5]=new Table();
		table[6]=new Table();
	}

	public void add(String menu,int n,int tablenum) { //���̺� �޴��� �߰�
		table[tablenum].addTable(menu, n);
		}
	public void print(int tablenum) { //���̺� �޴��� �����ش�.
		this.table[tablenum].show();
	}

}
