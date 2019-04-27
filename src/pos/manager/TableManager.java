package pos.manager;

public class TableManager {
	public Table[] table=new Table[7]; //테이블 배열 생성

	public TableManager() { //1~6번의 테이블을 생성
		table[1]=new Table();
		table[2]=new Table();
		table[3]=new Table();
		table[4]=new Table();
		table[5]=new Table();
		table[6]=new Table();
	}

	public void add(String menu,int n,int tablenum) { //테이블에 메뉴를 추가
		table[tablenum].addTable(menu, n);
		}
	public void print(int tablenum) { //테이블 메뉴를 보여준다.
		this.table[tablenum].show();
	}

}
