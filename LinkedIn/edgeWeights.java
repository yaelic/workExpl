import java.util.ArrayList;
import java.util.HashMap;
import  java.lang.Integer;
import net.stixar.graph.Edge;
import net.stixar.graph.attr.EdgeSource;

@SuppressWarnings("hiding")
public class edgeWeights<Integer> implements EdgeSource<java.lang.Integer> {
	HashMap<String,java.lang.Integer> nodeDict;
	ArrayList<myEdge> eList;
	
	public edgeWeights(ArrayList<myEdge> e, HashMap<String,java.lang.Integer> nodeDict) {
		this.eList =e;
		this.nodeDict =nodeDict;
	}
	@Override
	public java.lang.Integer get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void grow(int cap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void shrink(int cap, int[] fillPerm) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public java.lang.Integer get(Edge e) {
		int sourceID = e.source().nodeId();
		int targetId = e.target().nodeId();
		boolean found = false;
		for (myEdge mEdge : eList){
			if (myHtmlParser.getKeyByValue(nodeDict,sourceID ).contains(mEdge.source) && myHtmlParser.getKeyByValue(nodeDict,targetId).contains(mEdge.target)){
				//System.out.println("found the edge");
				found=true;
				java.lang.Integer intObj= java.lang.Integer.valueOf(mEdge.weight);
				return intObj;
			}			
		}
		System.out.println("--------didnt fin edge somthings wrong------------");
		return 2000000;
	}

}
