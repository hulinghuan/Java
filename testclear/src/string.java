import java.util.ArrayList;


public class string {
	private ArrayList<String> name = new ArrayList<String>();
	public void clearName(){
		name.clear();
	}
	
	public void insert(){
		name.add("Alice");
		name.add("Bob");
	}
	public ArrayList<String> getName() {
		return name;
	}
	public void setName(ArrayList<String> name) {
		this.name = name;
	}
	
}
