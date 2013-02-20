import java.util.HashMap;

import net.stixar.graph.Edge;
import net.stixar.graph.Node;
import net.stixar.graph.attr.NodeMap;


public class shortestPath implements NodeMap<Edge> {
	HashMap<java.lang.Integer,Edge> shortPathDict = new HashMap<java.lang.Integer,Edge>();
	public shortestPath() {
		shortPathDict = new HashMap<java.lang.Integer,Edge>();
	}

	@Override
	public Edge set(Node n, Edge v) {
		if (shortPathDict.containsKey(java.lang.Integer.valueOf(n.nodeId()))){
			shortPathDict.remove(java.lang.Integer.valueOf(n.nodeId()));
		}
		shortPathDict.put(java.lang.Integer.valueOf(n.nodeId()), v);
		return v;
	}

	@Override
	public Edge set(int i, Edge v) {
		if (shortPathDict.containsKey(java.lang.Integer.valueOf(i))){
			shortPathDict.remove(java.lang.Integer.valueOf(i));
		}
		shortPathDict.put(java.lang.Integer.valueOf(i), v);
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
//		for (java.lang.Integer b : shortPathDict.keySet()){
//			shortPathDict.remove(b);
//		}	

	}

	@Override
	public Edge get(Node n) {
		if (shortPathDict.containsKey(java.lang.Integer.valueOf(n.nodeId()))){
			return shortPathDict.get(java.lang.Integer.valueOf(n.nodeId()));
		}
		return null;
	}

	@Override
	public Edge get(int i) {
		if (shortPathDict.containsKey(java.lang.Integer.valueOf(i))){
			return shortPathDict.get(java.lang.Integer.valueOf(i));
		}
		return null;
	}

}
