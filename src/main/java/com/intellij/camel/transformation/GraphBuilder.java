package com.intellij.camel.transformation;

import com.intellij.camel.ui.Node;
import org.apache.camel.Route;
import org.apache.camel.model.FromDefinition;
import org.apache.camel.model.RouteDefinition;
import org.jboss.tools.fuse.transformation.camel.CamelConfigBuilder;
import org.jboss.tools.fuse.transformation.camel.CamelSpringBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    private File file;

    public GraphBuilder(File file){
        this.file = file;
    }

    public List<Node> toNodesList(){
        List<Node> nodeList = new ArrayList<Node>();
        CamelSpringBuilder springBuilder = getCamelContext();
        RouteDefinition firstRoute = springBuilder.getCamelContext().getRoutes().get(0);
        int x = 50;
        int y = 50;
        for(FromDefinition fromDefinition : firstRoute.getInputs()){
            Node node = new Node(x,y,fromDefinition.getUri());
            nodeList.add(node);
            System.out.println(fromDefinition.toString());
            System.out.println(fromDefinition.getUri());
            y += 100;
        }
        return nodeList;
    }

    private CamelSpringBuilder getCamelContext(){
        CamelSpringBuilder springBuilder = null;
        try {
            springBuilder = (CamelSpringBuilder) CamelConfigBuilder.loadConfig(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(springBuilder.getCamelContext().getRoutes().toString());
        return springBuilder;
    }
}
