package com.intellij.camel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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





            // create a label

            final JXLabel label = new JXLabel();







//            label.setFont(new Font("Segoe UI", Font.BOLD, 14));

            label.setText("task pane item 1 : a label");

           // label.setIcon(Images.NetworkDisconnected.getIcon(32, 32));

            label.setHorizontalAlignment(JXLabel.LEFT);

          //  label.setBackgroundPainter(this.getParent().getP);



// tweak with the UI defaults for the taskpane and taskpanecontainer

            //changeUIdefaults();



// create a taskpanecontainer

            JXTaskPaneContainer taskpanecontainer = new JXTaskPaneContainer();
     //   BoxLayout bl = new BoxLayout(taskpanecontainer, BoxLayout.PAGE_AXIS);
        taskpanecontainer.setLayout(new GridBagLayout());

      //  taskpanecontainer.ga

    //    taskpanecontainer.getLayout().

   //     JPanel container = new JPanel( gl = new GridLayout( 2, 1, 0, 0 ) );
   //     gl.setHgap(0);
   //     gl.setVgap(0);



// create a taskpane, and set it's title and icon

            JXTaskPane taskpane = new JXTaskPane();







            taskpane.setTitle("My Tasks");
        taskpane.setBackground(new Color(155,155,155));

            //taskpane.setIcon(Images.Quit.getIcon(24, 24));



// add various actions and components to the taskpane

            taskpane.add(label);

            taskpane.add(new AbstractAction() {

                {

                    putValue(Action.NAME, "task pane item 2 : an action");

                    putValue(Action.SHORT_DESCRIPTION, "perform an action");

                  //  putValue(Action.SMALL_ICON, Images.NetworkConnected.getIcon(32, 32));

                }

                public void actionPerformed(ActionEvent e) {

                    label.setText("an action performed");

                }

            });



// add the task pane to the taskpanecontainer

        JXTaskPane taskpane2 = new JXTaskPane();

        //taskpane2.setTitle("My Tasks2");
        //taskpane.


        //taskpane.setBorder(BorderFactory.createEmptyBorder());
        //taskpane2.setBorder(BorderFactory.createEmptyBorder());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        taskpanecontainer.add(taskpane,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        taskpanecontainer.add(taskpane2,c);
        //taskpanecontainer.
        taskpanecontainer.setBorder(BorderFactory.createEmptyBorder());

      //  taskpanecontainer.



        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(new Color(255, 249, 244));
        graphComponent.setBorder(BorderFactory.createEmptyBorder());
        JPanel palette = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//,
        splitPane.setLeftComponent(graphComponent);

        splitPane.setRightComponent(taskpanecontainer);
        //splitPane.setRightComponent(palette);

        splitPane.setResizeWeight(0.75);
        JPanel palette2 = new JPanel();
        this.setTopComponent(splitPane);
        this.setBottomComponent(palette2);
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.setResizeWeight(0.75);


    }

}
