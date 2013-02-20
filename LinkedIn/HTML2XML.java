import org.jsoup.nodes.Element;

/*import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import core.src.java.org.jdom2.S
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import java.io.IOException;


public class HTML2XML{
public static void main(String args[]) throws JDOMException
{
	InputStream isInHtml =null;
	URL url  = null;
	URLConnection connection =null;
	DataInputStream disInHtml =null;
	FileOutputStream fosOutHtml =null;
	FileWriter fwOutXml =null;
	FileReader frInHtml=null;
	 BufferedWriter bwOutXml =null;
	 BufferedReader brInHtml=null;
try {
    // url  = new URL("www.climb.co.jp");
   //  connection = url.openConnection();       
   //  isInHtml = connection.getInputStream();
     
     frInHtml = new FileReader("D:\\Second.html");
     brInHtml = new BufferedReader(frInHtml);
     SAXBuilder saxBuilder = new SAXBuilder("org.ccil.cowan.tagsoup.Parser", false);
     org.jdom.Document jdomDocument = saxBuilder.build(brInHtml);
     XMLOutputter outputter = new XMLOutputter();
     try {
          outputter.output(jdomDocument, System.out);
          fwOutXml = new FileWriter("D:\\Second.xml");
          bwOutXml = new BufferedWriter(fwOutXml);
          outputter.output(jdomDocument, bwOutXml);
          System.out.flush();
      }
      catch (IOException e)  {  }
            
}
catch (IOException e) {  }  
finally {
     System.out.flush();
     try{
     isInHtml.close();
     disInHtml.close();                      
     fosOutHtml.flush();
     fosOutHtml.getFD().sync();
     fosOutHtml.close();
     fwOutXml.flush();
     fwOutXml.close();
     bwOutXml.close();
     }
     catch(Exception w)
     {
    	 
     }
}
}
}
*/

		
//		Elements content11 = content.getElementsByClass("section subsection-reorder");
//		System.out.println(content1.html());
//		for (Element e : content1){
//			//System.out.println("c");
//			Elements content2 = e.getElementsByClass("content vcalendar");
//			for (Element e1: content2){
//				System.out.println("c1");
//				Elements content3 = e1.getElementsByTag("div");
//				for (Element e2 :content3){
//					Elements content4 = e2.getElementsByTag("div");
//					for (Element e3 : content4){
//						Elements content5 = e3.getElementsByClass("position   experience vevent vcard summary-past");
//						for(Element e4: content5){
//							Elements content6 = e4.getElementsByClass("postitle");
//							for (Element e5:content6){
//								Elements content7 = e5.getElementsByTag("h3");
//								for (Element e6:content7){
//									Elements content8 = e6.getElementsByClass("title");
//									for (Element f : content8) {
//										  String text = f.text();
//										  System.out.println(text);
//										}
//									
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
		
		//System.out.println(body.html());
		
//		
//		//Log in
//		
////		Elements txtArea = doc.select("#quickpost");
////		txtArea.text(yourText);
//
//		Response res = Jsoup.connect("http://www.linkedin.com/")
//		    .data("session_key", "yaeli.j.cohen@gmail.com", "session_password", "thbhnhbhnhhbhnu")
//		    .method(Method.POST)
//		    .execute();
//		    //.post();
//		
//
//		//Document doc = res.parse();
//		String sessionId = res.cookie("visit"); // you will need to check what the right cookie name is
//		System.out.println(sessionId);
//		Document doc = Jsoup.connect(urlString)
//			    .cookie("visit", sessionId)
//			    .get();
//		
////		Document doc = res.parse();
//		Element body = doc.getElementById("body");
//		Element content = body.getElementById("content");
//		System.out.println(content.html());
//
//
//		//Keep logged in
//		//Map<String, String> cookies = res.cookies();
//		

//		Document doc = Jsoup
//		    .connect(urlString)
//		    .cookies(cookies)
//		    .get();
		
//		String username = "yaeli.j.cohen@gmail.com";
//		String password = "thbhnhbhnhhbhnu";
//		String login = username + ":" + password;
//		String base64login = new String(Base64.encodeBase64(login.getBytes()));
//
//		Document doc = Jsoup
//		    .connect(urlString)
//		    .header("Authorization", "Basic " + base64login)
//		    .get();
//		//Document doc = Jsoup.connect(urlString).get();
//		//String title = doc.title();
//		String s= doc.html();
//		//System.out.println(s);
//		//Document parsed = Jsoup.parse(doc) 
//		//Element content = doc.getElementById("position");
//		Element body = doc.getElementById("body");
//		Element content = body.getElementById("content");
//		//System.out.println(content.data());
//		System.out.println(body.html());
//		for (Element pos : content){
//			System.out.println("help3");
//			  String p = pos.text();
//			  System.out.println(p);
//			Elements links = pos.getElementsByTag("div");
//			for (Element link : links) {
//				  //String linkHref = link.attr("href");
//				  String linkText = link.text();
//				  System.out.println(linkText);
//				}
//		}
//
//
//
//			for (Element e : positions){
//				//System.out.println("111111111"+e.text());
//				//System.out.println("222222222"+e.html());
//				if(e.getElementsByClass("title").isEmpty()){
//					System.out.println("bad e");
//					continue;
//				}
//				String title= e.getElementsByClass("title").first().text();
//				String periodToParse = e.getElementsByClass("period").first().text();
//				System.out.println("The title: "+ title);
//				System.out.println("The period: "+ periodToParse);
//				
// 				if (!nodeDict.containsValue(title)){
//					nodeDict.put(index, title);
//					index ++;
//				}
//				else {
//					System.out.println(title+" node existst ");
//				}
// 				String month = getRegFromString(periodToParse, regMonth);
//				String year = getRegFromString(periodToParse, regYear);
//				month = month==null? "0" : month;
//				year = year==null? "0" : year;
//				int weight = Integer.parseInt(month) +Integer.parseInt(year)*12;
//				
//				
//			}


//boolean suc =false;
//for (myEdge ee: myEdges){
//	if (ee.source==nodeDictById.get(s) && ee.target==nodeDictById.get(t)){
//		suc = true;
//		System.out.println("daaaaaaaaaaaaaaaa");
//	}
//	if (ee.source==e.source && ee.target==e.target){
//		System.out.println("SUPERdaaaaaaaaaaaaaaaa");
//	}
//	if (ee.source==dictTest.get(s) && ee.target==dictTest.get(t)){
//		System.out.println("Yabadabaedaaaaaaaaaaaaaaaa");
//	}
//	if (getKeyByValue(nodeDict,s).contains(ee.source) && getKeyByValue(nodeDict,t).contains(ee.target)){
//		suc = true;
//		System.out.println("PPpPPPPPPPPPPPPPPPPP");
//	}
//	
//}
//if (!suc){
//	Set<String> skeys =getKeyByValue(nodeDict, s);
//	Set<String> tkeys =getKeyByValue(nodeDict, t);
//	System.out.println("sss " + skeys.toString());
//	System.out.println("ttt" + tkeys.toString());
//}