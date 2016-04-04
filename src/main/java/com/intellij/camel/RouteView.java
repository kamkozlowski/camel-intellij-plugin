package com.intellij.camel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class RouteView extends JSplitPane {

    public void init(){
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        mxStylesheet stylesheet = graph.getStylesheet();
        String EIP_icons[] = { "endpoint", "log", "transform", "endpointQueue" };
        for(String icon : EIP_icons){
            String myStyleName = icon + "Style";
            Hashtable<String, Object> style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
            style.put(mxConstants.STYLE_IMAGE, "/icons/"+icon+".png");
            style.put(mxConstants.STYLE_IMAGE_BORDER, 1);
            style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
            stylesheet.putCellStyle(myStyleName, style);
        }
        Map<String, Object> edge = new HashMap<String, Object>();
        edge.put(mxConstants.STYLE_ROUNDED, true);
        edge.put(mxConstants.STYLE_ORTHOGONAL, false);
        edge.put(mxConstants.STYLE_EDGE, "elbowEdgeStyle");
        edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edge.put(mxConstants.STYLE_DASHED, 3);
        edge.put(mxConstants.STYLE_STROKEWIDTH, 2f);
        edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
        edge.put(mxConstants.STYLE_FONTCOLOR, "#ff0000");
        graph.getStylesheet().putCellStyle("edgeStyle",edge);
        graph.getModel().beginUpdate();
        try{
            Object a = graph.insertVertex(parent, null, "Endpoint", 50, 50, 84, 50, "endpointStyle");
            Object b = graph.insertVertex(parent, null, "Log",      250, 50, 84, 50, "logStyle");
            Object c = graph.insertVertex(parent, null, "Mapping",  450, 50, 84, 50, "transformStyle");
            Object d = graph.insertVertex(parent, null, "Outbound", 650, 150, 84, 50, "endpointQueueStyle");
            graph.insertEdge(parent, null, null, a, b,"edgeStyle");
            graph.insertEdge(parent, null, null, b, c,"edgeStyle");
            graph.insertEdge(parent, null, null, c, d,"edgeStyle");
        }
        finally{
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(new Color(255, 249, 244));
        graphComponent.setBorder(BorderFactory.createEmptyBorder());
        JPanel palette = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//,
        splitPane.setLeftComponent(graphComponent);
        splitPane.setRightComponent(palette);
        splitPane.setResizeWeight(0.75);
        JPanel palette2 = new JPanel();
        this.setTopComponent(splitPane);
        this.setBottomComponent(palette2);
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.setResizeWeight(0.75);
    }

}
