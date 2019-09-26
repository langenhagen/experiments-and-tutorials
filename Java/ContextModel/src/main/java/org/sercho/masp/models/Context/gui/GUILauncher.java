package org.sercho.masp.models.Context.gui;

import java.io.IOException;

import org.sercho.masp.models.Context.Environment;

/**
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class GUILauncher {

    public static void main(final String[] args) throws IOException {
        Environment environment = null;

        if(args.length > 0) {
            environment = ContextModelLoader.loadEnvironmentFromFile(args[0]);
        }

        new VisualizerManager(environment, VisualizerManager.DisplayType.STANDALONE);
    }
}
