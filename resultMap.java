import java.util.HashMap;

import net.stixar.graph.Node;
import net.stixar.graph.attr.NodeMap;


public class resultMap implements NodeMap<java.lang.Integer> {

	HashMap<java.lang.Integer,java.lang.Integer> nodeDict = new HashMap<java.lang.Integer,java.lang.Integer>();
	
	public resultMap() {
		nodeDict = new HashMap<java.lang.Integer,java.lang.Integer>();
	}

	@Override
	public java.lang.Integer set(Node n, java.lang.Integer v) {
		//System.out.println("settt: " +n.nodeId() + " "+ v);
		if (nodeDict.containsKey(n.nodeId())){
			nodeDict.remove(n.nodeId());
		}
		nodeDict.put(n.nodeId(), v);
		return v;
	}

	@Override
	public java.lang.Integer set(int i, java.lang.Integer v) {
		System.out.println("SSSSSSSEEEEEETTTT");
		if (nodeDict.containsKey(i)){
			nodeDict.remove(i);
		}
		nodeDict.put(i, v);
		return v;
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
//		for (java.lang.Integer b : nodeDict.keySet()){
//			nodeDict.remove(b);
//		}	
	}

	@Override
	public java.lang.Integer get(Node n) {
		if (!nodeDict.containsKey(n.nodeId()) || nodeDict.get(n.nodeId()) ==null ){
			//System.out.println("ckcjsklcjakljcaklj.");
			return 1000000;
		}
		//System.out.println("the dist is:" +nodeDict.get(n.nodeId()));
		return nodeDict.get(n.nodeId());
	}

	@Override
	public java.lang.Integer get(int i) {
		if (!nodeDict.containsKey(i) || nodeDict.get(i) ==null ){
			//System.out.println("ckcjsklcjtttakljcaklj.");
			return 1000000;
		}
		//System.out.println("---------");
		//System.out.println(nodeDict.get(i));
		return nodeDict.get(i);
	}

}


