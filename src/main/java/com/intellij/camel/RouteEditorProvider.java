package com.intellij.camel;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jboss.tools.fuse.transformation.camel.CamelConfigBuilder;
import org.jboss.tools.fuse.transformation.camel.CamelSpringBuilder;
import org.jdom.Element;
import com.intellij.openapi.diagnostic.Logger;


import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Route editor provider
 */
public class RouteEditorProvider implements ApplicationComponent, FileEditorProvider {

    /** The editor component name. */
    private static final String NAME = "RouteEditorProvider";
    /** The editor type id. */
    private static final String EDITOR_TYPE_ID = "CamelRoutes";
    /** Logger instance */
    private static final Logger LOG = Logger.getInstance(RouteEditorProvider.class.getName());

    @Override
    public void initComponent() {
        LOG.info("initComponent()");
    }

    @Override
    public void disposeComponent() {
        LOG.info("disposeComponent()");
    }

    /**
     * Checks if for the specified file an editor of this type can be created.
     * @param project the project
     * @param file    the file to be tested
     * @return <code>true</code> if this provider can create an editor for this file
     */
    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        LOG.info("accept()");
        boolean accept;
        if(file.getName().equals(CamelPluginConstants.DEFAULT_CAMEL_CONTEXT_NAME)) {
            LOG.info("Camel context found");


            CamelSpringBuilder springBuilder = null;
            try {
                springBuilder = (CamelSpringBuilder) CamelConfigBuilder.loadConfig(new File(file.getCanonicalPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(springBuilder.getCamelContext().getRoutes().toString());





            accept = true;
        }
        else {
            accept = false;
        }
        return accept;
        }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new RouteEditor();
    }

    @Override
    public void disposeEditor(@NotNull FileEditor fileEditor) {

    }


    @NotNull
    @Override
    public FileEditorState readState(@NotNull Element element, @NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new FileEditorState() {
            public boolean canBeMergedWith(FileEditorState otherState, FileEditorStateLevel level) {
                return false;
            }
        };
    }

    @Override
    public void writeState(@NotNull FileEditorState fileEditorState, @NotNull Project project, @NotNull Element element) {

    }

    /**
     * Returns the editor type id.
     * @return the editor type id
     */
    public
    @NotNull
    String getEditorTypeId() {
        return EDITOR_TYPE_ID;
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_BEFORE_DEFAULT_EDITOR;
    }

    /**
     * Returns the editor component name.
     * @return the editor component name
     */
    public
    @NotNull
    String getComponentName() {
        return NAME;
    }
}
