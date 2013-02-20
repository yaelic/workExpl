import java.util.HashMap;

import net.stixar.graph.Node;
import net.stixar.graph.attr.NodeMap;


public class myNodes<Integer> implements NodeMap<java.lang.Integer> {
	HashMap<java.lang.Integer,java.lang.Integer> nodeDict = new HashMap<java.lang.Integer,java.lang.Integer>();
	
	public myNodes() {
		nodeDict = new HashMap<java.lang.Integer,java.lang.Integer>();
	}

	@Override
	public java.lang.Integer set(Node n, java.lang.Integer v) {
		if (nodeDict.containsKey(n.nodeId())){
			nodeDict.remove(n.nodeId());
		}
		nodeDict.put(n.nodeId(), v);
		return v;
	}

	@Override
	public java.lang.Integer set(int i, java.lang.Integer v) {
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
		return java.lang.Integer.valueOf(0);
	}

	@Override
	public java.lang.Integer get(int i) {
		return java.lang.Integer.valueOf(0);
	}

}
