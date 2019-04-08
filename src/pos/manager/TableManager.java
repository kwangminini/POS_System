package pos.manager;

public class TableManager {
	private Table[] table=new Table[6];
	//public int[] Table=new int[6];
	
//	String filePath="C:\\Users\\rhkd8\\eclipse-workspace\\POS\\src\\menulist.txt";
	public TableManager() {
		table[0]=new Table();
	}
//	FileManager filemanager=new FileManager();
	public void add(String menu,int n) {
		
		table[0].addTable(menu, n);
		}
	public void print() {
		this.table[0].show();
	}
}
