import java.io.*;
import java.util.logging.Logger;

import com.android.manifmerger.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Playground...");

        Merger merger = new Merger();

        String[] mergerArgs = {
                "--main", "MyAndroidManifest1.xml",
                "--overlays", "MyAndroidManifest2.xml",
                "--out", "OutManifest.xml"};
        try {
            merger.process(mergerArgs);
        } catch ( FileNotFoundException e) {
            // TODO Logger
            e.printStackTrace();
        }

        System.out.println("...Playground Finished");
    }
}