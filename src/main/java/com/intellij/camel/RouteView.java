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

/**
 * Created by kozik on 03.04.16.
 */
public class RouteView extends JPanel {

    public RouteView(){
        setLayout(new GridBagLayout());
    }

    /**
     * @see java.applet.Applet#init().
     */
    public void init(  ) {

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();


        // get graph stylesheet
        mxStylesheet stylesheet = graph.getStylesheet();

        // define image style name

        String EIP_icons[] = { "endpoint", "log", "transform", "endpointQueue" };

        for(String icon : EIP_icons) {
            String myStyleName = icon + "Style";
            Hashtable<String, Object> style = new Hashtable<String, Object>();
            style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
            style.put(mxConstants.STYLE_IMAGE, "/icons/"+icon+".png");
            style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
            stylesheet.putCellStyle(myStyleName, style);
        }
// SHAPE_LINE
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


        mxStylesheet edgeStyle = new mxStylesheet();
        //edgeStyle.setDefaultEdgeStyle(edge);
        graph.getStylesheet().putCellStyle("edgeStyle",edge);


        //Hashtable<String, Object> style2 = new Hashtable<String, Object>();
        //style2.put( mxConstants.STYLE_FILLCOLOR, mxConstants.Co)

        graph.getModel().beginUpdate();
        try
        {
        //    Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
       //             30);
          //  Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
        //            80, 30);

            Object a = graph.insertVertex(parent, null, "Endpoint", 50, 50, 84, 50, "endpointStyle");
            Object b = graph.insertVertex(parent, null, "Log",      250, 50, 84, 50, "logStyle");
            Object c = graph.insertVertex(parent, null, "Mapping",  450, 50, 84, 50, "transformStyle");
            Object d = graph.insertVertex(parent, null, "Outbound", 650, 150, 84, 50, "endpointQueueStyle");


            graph.insertEdge(parent, null, null, a, b,"edgeStyle");
            graph.insertEdge(parent, null, null, b, c,"edgeStyle");
            graph.insertEdge(parent, null, null, c, d,"edgeStyle");
            //graph.getV
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        //graphComponent.setViewportView(new ImageViewport());
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(new Color(255, 249, 244));
        graphComponent.setViewport(graphComponent.getViewport());

       // getContentPane().add(graphComponent);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;

        this.add(graphComponent,gbc);
        //this.setSize(1024,768);
        //this.setM




    }

}
