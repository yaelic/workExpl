
public class myEdge {
	public int weight;
	public String source;
	public String target;
	
	public myEdge(){
		source = null;
		target = null;
		weight = 0;
	}
	public myEdge(String s, String t, int w){
		source = s;
		target = t;
		weight =w;
	}
	public myEdge(String t,int w){
		source = null;
		target = t;
		weight =w;
	}
	
	public void setTarget(String t){
		target=t;
	}
	
	public void setSource(String s){
		source=s;
	}
	public void setWeight(int w){
		weight=w;
	}
	
	
}
