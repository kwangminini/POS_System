package pos.manager;

public class TableManager {
	private Table[] table=new Table[7];
	//public int[] Table=new int[6];
	
//	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	public TableManager() {
		table[1]=new Table();
		table[2]=new Table();
		table[3]=new Table();
		table[4]=new Table();
		table[5]=new Table();
		table[6]=new Table();
	}
//	FileManager filemanager=new FileManager();
	public void add(String menu,int n,int tablenum) {
		
		table[tablenum].addTable(menu, n);
		}
	public void print(int tablenum) {
		this.table[tablenum].show();
	}
}
