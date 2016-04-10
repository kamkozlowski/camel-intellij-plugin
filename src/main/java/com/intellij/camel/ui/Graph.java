package com.intellij.camel.ui;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Graph {

    private mxGraph mxGraph = new mxGraph();

    public Graph(){
        mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();
        mxStylesheet stylesheet = mxGraph.getStylesheet();
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
        mxGraph.getStylesheet().putCellStyle("edgeStyle",edge);
        mxGraph.getModel().beginUpdate();
        try{
            Object a = mxGraph.insertVertex(parent, null, "Endpoint", 50, 50, 84, 50, "endpointStyle");
            Object b = mxGraph.insertVertex(parent, null, "Log",      250, 50, 84, 50, "logStyle");
            Object c = mxGraph.insertVertex(parent, null, "Mapping",  450, 50, 84, 50, "transformStyle");
            Object d = mxGraph.insertVertex(parent, null, "Outbound", 650, 150, 84, 50, "endpointQueueStyle");
            mxGraph.insertEdge(parent, null, null, a, b,"edgeStyle");
            mxGraph.insertEdge(parent, null, null, b, c,"edgeStyle");
            mxGraph.insertEdge(parent, null, null, c, d,"edgeStyle");
        }
        finally{
            mxGraph.getModel().endUpdate();
        }
    }

    public com.mxgraph.view.mxGraph getMxGraph() {
        return mxGraph;
    }

    public void setMxGraph(com.mxgraph.view.mxGraph mxGraph) {
        this.mxGraph = mxGraph;
    }
}

