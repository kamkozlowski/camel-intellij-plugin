package com.intellij.camel;

import com.intellij.camel.ui.Graph;
import com.intellij.camel.ui.PalettePane;
import com.intellij.camel.ui.PropertiesPane;
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
        mxGraphComponent graphComponent = new mxGraphComponent(new Graph().getMxGraph());
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(new Color(255, 249, 244));
        graphComponent.setBorder(BorderFactory.createEmptyBorder());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);//,
        splitPane.setLeftComponent(graphComponent);
        splitPane.setRightComponent(new PalettePane());
        splitPane.setResizeWeight(0.75);
        this.setTopComponent(splitPane);
        this.setBottomComponent(new PropertiesPane());
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.setResizeWeight(0.75);


    }

}
