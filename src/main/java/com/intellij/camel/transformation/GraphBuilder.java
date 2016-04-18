package com.intellij.camel.transformation;

import com.intellij.camel.ui.Node;
import org.apache.camel.Route;
import org.apache.camel.model.FromDefinition;
import org.apache.camel.model.RouteDefinition;
import org.jboss.tools.fuse.transformation.camel.CamelConfigBuilder;
import org.jboss.tools.fuse.transformation.camel.CamelSpringBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    private File file;
    public static String SPRING_NS = "http://camel.apache.org/schema/spring";
    public static String BLUEPRINT_NS = "http://camel.apache.org/schema/blueprint";

    public GraphBuilder(File file){
        this.file = file;
    }

    public List<Node> toNodesList(){
        List<Node> nodeList = new ArrayList<Node>();
        //CamelSpringBuilder springBuilder = getCamelContext();
        /*
        RouteDefinition firstRoute = springBuilder.getCamelContext().getRoutes().get(0);
        int x = 50;
        int y = 50;
        for(FromDefinition fromDefinition : firstRoute.getInputs()){
            Node node = new Node(x,y,fromDefinition.getUri());
            nodeList.add(node);
            //System.out.println(fromDefinition.toString());
            //System.out.println(fromDefinition.getUri());
            y += 100;
        }
        */

        int x = 50;
        int y = 50;
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setIgnoringElementContentWhitespace(true);
            doc = dbf.newDocumentBuilder().parse(file);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Element camelEle = getChildElement(doc.getDocumentElement(), SPRING_NS, "camelContext");

        NodeList nl = camelEle.getChildNodes();

        for(int i=0; i< nl.getLength(); i++){
            //System.out.println(nl.item(i).getNodeName());
            //System.out.println(nl.item(i).getTextContent());
            if(nl.item(i).getNodeName().equals("route")){
                NodeList nl2 = nl.item(i).getChildNodes();
                int amountOfFrom = 0;

                for(int j=0; j< nl2.getLength(); j++){
                    //System.out.println(nl2.item(j).getNodeName());
                    if(nl2.item(j).getNodeName().equals("from")){
                        amountOfFrom++;
                        y += 100;
                        //System.out.println(((Element)nl2.item(j)).getAttribute("uri"));
                        Node node = new Node(x,y,((Element)nl2.item(j)).getAttribute("uri"));
                        nodeList.add(node);

                    }
                    else if(nl2.item(j).getNodeName().equals("setHeader")){
                        x += 150;
                        Node node = new Node(x,y,("setHeader("+((Element)nl2.item(j)).getAttribute("headerName")+")"));
                        nodeList.add(node);
                    }
                    else if(nl2.item(j).getNodeName().equals("to")){
                        x += 150;
                        Node node = new Node(x,y,((Element)nl2.item(j)).getAttribute("uri"));
                        nodeList.add(node);
                    }
                }
            }




        }




        return nodeList;

    }

    private CamelSpringBuilder getCamelContext(){
        /*
        CamelSpringBuilder springBuilder = null;
        try {
            springBuilder = (CamelSpringBuilder) CamelConfigBuilder.loadConfig(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(springBuilder.getCamelContext().getRoutes().toString());
        return springBuilder;
        */

        return null;
    }

    private static Element getChildElement(Element parent, String childNS, String childName) {
        Element child = null;
        NodeList children = parent.getElementsByTagNameNS(childNS, childName);
        if(children.getLength() > 0) {
            child = (Element)children.item(0);
        }

        return child;
    }


}
