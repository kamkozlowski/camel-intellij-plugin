package com.intellij.camel.ui;

public class Node {
    private int x;
    private int y;
    private boolean multipleOutputs = false;
    private String name;
    private String imageName = "bean";

    public Node(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;

        if(name.startsWith("activemq")){
            imageName = "endpointQueue";
        }



    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMultipleOutputs() {
        return multipleOutputs;
    }

    public void setMultipleOutputs(boolean multipleOutputs) {
        this.multipleOutputs = multipleOutputs;
    }
}
