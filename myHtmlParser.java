import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.stixar.graph.attr.ArrayNodeMap;
import net.stixar.graph.attr.NativeEdgeMap;
import net.stixar.graph.attr.NodeMap;
import net.stixar.graph.BasicDigraph;
import net.stixar.graph.BasicNode;
import net.stixar.graph.Edge;
import net.stixar.graph.Graph;
import net.stixar.graph.Node;
import net.stixar.graph.attr.NativeNodeMap;
import net.stixar.util.BinaryPQ;
import net.stixar.util.Cell;
import net.stixar.util.NumAdaptor;
import org.jsoup.*;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.*;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.lobobrowser.util.io.Files;
import org.apache.commons.codec.binary.Base64;
import flex.messaging.util.FileUtils;
import java.util.regex.*;



public class myHtmlParser {
	public static final String regYear = "\\(?(\\d+) year?\\)?";
	public static final String regMonth = "\\(?(\\d+) months?\\)?";
	public static final String ROOT = "/Users/yaelcohen/Documents/cvs/";
	HashMap<String,Integer> nodeDict = new HashMap<String,Integer>();
	//HashMap<Integer,String> nodeDictById = new HashMap<Integer,String>();
	ArrayList<myEdge> myEdges = new ArrayList<myEdge>();
	//ArrayList<String> dictTest = new ArrayList<String>();
	int index;
	
	public myHtmlParser(){
		index=0;
		nodeDict = new HashMap<String,Integer>();
		myEdges = new ArrayList<myEdge>();
	}
	
	public static String getRegFromString(String per, String regexp){
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(per);
		String returnString = null;
		if(m.find()){
			returnString = m.group(1);
		}
		return returnString;
	}
	
	public void goOverCvs() throws Exception{
		File folder = new File(ROOT);
        File[] listOfFiles = folder.listFiles();
        for (File f: listOfFiles){
        	System.out.println(f.getCanonicalPath());
        	getData(f);	
        } 
	}
	
	public static Set<String> getKeyByValue(HashMap<String, Integer> nodeDict2, Integer value) {
		Set<String> keys = new HashSet<String>();
		for (Entry<String, Integer> entry : nodeDict2.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
		//System.out.println("WWTF?");
	    return keys;
	}
	
	public static String getKeyByValueOne(HashMap<String, Integer> nodeDict2, Integer value) {
		for (Entry<String, java.lang.Integer> entry : nodeDict2.entrySet()) {
			if (value.equals(entry.getValue())) {
	            //return keys.add(entry.getKey());
	            return entry.getKey();
	        }
	    }
		//System.out.println("WWTF?");
	    return null;
	}
	
	public BasicDigraph craeteGraphs(){
		 BasicDigraph graph = new BasicDigraph();
		 BasicNode[] allNodes = new BasicNode[index];
		for (int i=0 ; i<index; i++){
			//i+1
			BasicNode b = new BasicNode(graph,i);
			allNodes[i] = b;
		}
		System.out.println("PDPDPDPDPDP");
		BasicDigraph graphWithNodes = new BasicDigraph(allNodes);
		for (myEdge e: myEdges){
			if (e.source==null || e.target==null){
				continue;
			}
			System.out.println(e.source + " \t" + e.target);
			int s = nodeDict.get(e.source);
			int t = nodeDict.get(e.target);
			System.out.println("  "+s + "  "+ t);
			graphWithNodes.genEdge(graphWithNodes.node(s),graphWithNodes.node(t));
//			System.out.println(graphWithNodes.toString());
			//graphWithNodes.
		}
		//System.out.println(graphWithNodes.toString());
		return graphWithNodes;
	}
	
	public void getData(File input) throws Exception{		
		try{
			Document doc = Jsoup.parse(input, "UTF-8");
			Element body = doc.getElementById("body");
			Element content = body.getElementById("content");
			Elements positions = doc.select(".position");

			myEdge currentE =new myEdge();
 			for (Element e : positions){
 				if(e.getElementsByClass("title").isEmpty()){
					System.out.println("bad e");
					continue;
				}
				String title= e.getElementsByClass("title").first().text();
				String periodToParse = e.getElementsByClass("period").first().text();
				
 				if (!nodeDict.containsKey(title)){
					nodeDict.put(title, index);
					//nodeDictById.put(index, title);
					//dictTest.add(title);
					index ++;
					System.out.println(title+" added to nodes");
				}
				else {
					System.out.println(title+" node existst ");
				}
				String month = getRegFromString(periodToParse, regMonth);
				String year = getRegFromString(periodToParse, regYear);
				month = month==null? "0" : month;
				year = year==null? "0" : year;
				int weight = Integer.parseInt(month) + Integer.parseInt(year)*12;
				currentE.setSource(title);
				if (currentE.target != null){
					myEdges.add(currentE);
					System.out.println("just added: " + currentE.source+ " " + currentE.target + " " + currentE.weight );
				}
				currentE =new myEdge(title, weight);
			}
 			myEdges.add(currentE);
		}
		catch(Exception e){
			throw(e);
			
		}
	}
	
	public void WriteGraphToFile(File output) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(output);
		String text ="";
		for (myEdge e: myEdges){
			text += e.source +"^"+e.target+"^" + e.weight + "\n";
		}
		out.println(text);
		out.close();

	}
	
	
	@SuppressWarnings("unchecked")
	public void search(String source, String dest,BasicDigraph g, edgeWeights weights, myNodes huristic,resultMap res, shortestPath sp, compSaveMe a){
		for (String s :nodeDict.keySet()){
			if (s.contains(source)){
				System.out.println("source: " + s);
				for (String t :nodeDict.keySet()){
					if (t.contains(dest)){
						System.out.println("taregt: " + t);
						BasicNode sourceNode = g.node(nodeDict.get(s)); 
						BasicNode targetNode = g.node(nodeDict.get(t));
						if (sourceNode.nodeId()>406 || targetNode.nodeId() >406){
							System.out.println("-------------------------------------");
							continue;
						}
						int ansz = g.nodeAttrSize();
						NodeMap<Edge> parents = new ArrayNodeMap<Edge>(new Edge[ansz]);
						NodeMap<Cell<Node>> pqItems = new ArrayNodeMap<Cell<Node>>(new Cell[ansz]);
						BinaryPQ<Node> pQueue = new BinaryPQ<Node>(getComparator(res, a), g.nodeSize());
						// initializing the result map
						for (Node n : g.nodes()){
							if (n == sourceNode) {
								res.set(n, java.lang.Integer.valueOf(0));
							}
				            else res.set(n, java.lang.Integer.valueOf(7000000));
						}
						pqItems.set(sourceNode, pQueue.insert(sourceNode));
				        while(!pQueue.isEmpty()) {
				        	//System.out.println("not empty!");
				            Node s1 = pQueue.extractMin();
				            if (s1 == targetNode) {
				                pQueue.clear();
				                break;
				            }
				            java.lang.Integer sDist = res.get(s1);
				            //System.out.println("the c dist is" + sDist);
				            for (Edge e = s1.out(); e != null; e = e.next()){
				            	//System.out.println("-----------------------------");
				            	//System.out.println("NODE SOURCE "+ e.source().nodeId() + " NODE TARGET: "+ e.target().nodeId());
				            	java.lang.Integer eWeight = weights.get(e);
				            	//System.out.println("wwwww" + eWeight);
				                Node t1 = e.target();
				                java.lang.Integer tDist = a.add(sDist, eWeight);
				               // System.out.println("the t dist is" + tDist);
				                if (pqItems.get(t1) == null) {
				                	//System.out.println("cwehoiwhcpiwheio");
				                	//System.out.println(t1.nodeId());
				                    res.set(t1, tDist);
				                    //System.out.println(res.get(t1));
				                    parents.set(t1, e);
				                    pqItems.set(t1, pQueue.insert(t1));
				                } else {
				                    if (a.compare(tDist, res.get(t1)) < 0) {
				                    	//System.out.println("the other one");
				                        res.set(t1, tDist);
				                        parents.set(t1, e);
				                        pQueue.requeue(pqItems.get(t1));
				                    }
				                }
				            }
				        }
						//System.out.println(sourceNode + " " + targetNode);
						//net.stixar.graph.paths.SSSP.dijkstra((Graph)g,(Node)sourceNode,(NativeNodeMap)res,(NativeEdgeMap)weights);
						//net.stixar.graph.paths.SSSP.dijkstra((Graph)g,(Node)sourceNode,res,weights,a);
						//System.out.println("pppp" + res.get(targetNode));
						//net.stixar.graph.search.AStar.search(g, sourceNode, targetNode, weights, res,huristic, sp, a);
						System.out.println("the shortest path is: " + res.get(targetNode)+ " months long");
						if (res.get(targetNode) < 70000){
							System.out.println("the path is :");
							System.out.print(t+ "<-");
							Integer currentNodeID = parents.get(targetNode).source().nodeId();
							while(currentNodeID != sourceNode.nodeId()){
								System.out.print(getKeyByValueOne(nodeDict,parents.get(targetNode).source().nodeId())+"<-");
								//System.out.println(parents.get(targetNode).source().nodeId()+"->");
								currentNodeID = parents.get(currentNodeID).source().nodeId();
							}
							System.out.println(s );
						}
						else {
							System.out.println("no path");
						}
					}
				}
			}
		}
		
	}
	public static Comparator<Node> getComparator(final NodeMap<java.lang.Integer> dMap, 
			final NumAdaptor<java.lang.Integer> adaptor)
			{
		return new Comparator<Node>()
				{
			public int compare(Node n1, Node n2)
			{
				return adaptor.compare(n1.get(dMap), n2.get(dMap));
			}
				};
			}
	
	public static void main (String argv[]) throws Exception{
		myHtmlParser hp = new myHtmlParser();
		//File f= new File("/Uxsers/yaelcohen/Documents/cvs/Dan Aloni   LinkedIn.html");
		//hp.getData(f);
		hp.goOverCvs();
		BasicDigraph g = hp.craeteGraphs();
		edgeWeights weights = new edgeWeights(hp.myEdges,hp.nodeDict);
		myNodes huristic = new myNodes();
		resultMap res = new resultMap();
		shortestPath sp = new shortestPath();
		compSaveMe a = new compSaveMe();
		System.out.println("the size is " + g.nodeAttrSize());
		//hp.search("Commander in a military technological course","Co-Founder & VP Product", g,weights,huristic,res, sp,a);
		hp.search("dev","Co-Founder", g,weights,huristic,res, sp,a);
		//hp.search("Prog","CEO", g,weights,huristic,res, sp,a);
		//hp.search("rogrammer","CTO", g,weights,huristic,res, sp,a);
		//File graphOut = new File("/Users/yaelcohen/Documents/cvGraph2.txt"); 
		//hp.WriteGraphToFile(graphOut);
		System.out.println("done");
	}
}
