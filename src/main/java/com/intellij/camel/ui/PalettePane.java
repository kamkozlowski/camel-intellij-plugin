package com.intellij.camel.ui;

import com.intellij.camel.Images;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import java.awt.*;


public class PalettePane extends JXTaskPaneContainer {

    public PalettePane(){

        UIManager.put("TaskPane.titleBackgroundGradientStart", Color.LIGHT_GRAY);
        UIManager.put("TaskPane.titleBackgroundGradientEnd", Color.LIGHT_GRAY);
        UIManager.put("TaskPane.borderColor", Color.GRAY);

        final JXLabel activeMqButton = new JXLabel();
        activeMqButton.setText("ActiveMQ");
        activeMqButton.setIcon(Images.EndpointQueue.getIcon(15, 15));
        activeMqButton.setHorizontalAlignment(JXLabel.LEFT);

        final JXLabel beanButton = new JXLabel();
        beanButton.setText("Bean");
        beanButton.setIcon(Images.Bean.getIcon(15, 15));
        beanButton.setHorizontalAlignment(JXLabel.LEFT);

        setLayout(new GridBagLayout());

        JXTaskPane componentsSection = new JXTaskPane();
        componentsSection.setTitle("Components");
        componentsSection.setBackground(new Color(155,155,155));
        componentsSection.setIcon(Images.Directory.getIcon(24, 24));

        componentsSection.add(activeMqButton);
        componentsSection.add(beanButton);

        JXTaskPane routingSection = new JXTaskPane();
        routingSection.setTitle("Routing");
        routingSection.setBackground(new Color(155,155,155));
        routingSection.setIcon(Images.Directory.getIcon(24, 24));

        final JXLabel aggregateButton = new JXLabel();
        aggregateButton.setText("Bean");
        aggregateButton.setIcon(Images.Aggregate.getIcon(15, 15));
        aggregateButton.setHorizontalAlignment(JXLabel.LEFT);

        routingSection.add(aggregateButton);

        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(componentsSection,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        add(routingSection,c);
        setBorder(BorderFactory.createEmptyBorder());
    }


}
