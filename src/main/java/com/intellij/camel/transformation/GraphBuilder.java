package com.intellij.camel.transformation;

import com.intellij.camel.ui.Node;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    private File file;
    public GraphBuilder(File file){
        this.file = file;
    }

    public List<Node> toNodesList(){
/*
        CamelContext a = new DefaultCamelContext();
        try {
            RoutesDefinition routes = a.loadRoutesDefinition(new FileInputStream(file));
            List<RouteDefinition> routesDefinition = routes.getRoutes();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        List<Node> nodeList = new ArrayList<Node>();
        Node node1 = new Node(50,50,"direct:bar");
        Node node2 = new Node(150,50,"mock:bar");
        nodeList.add(node1);
        nodeList.add(node2);
        return nodeList;

    }
}
