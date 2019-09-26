package org.sercho.masp.models.Context.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sercho.masp.models.Context.gui.pathRecording.ElementPath;
import org.sercho.masp.models.Context.gui.pathRecording.Path;
import org.sercho.masp.models.Context.gui.pathRecording.PathRecorder;

public class ElementPathTest {

    public final static String ELEMENT_ID = "testElementId ";

    public final static int TEST_PATH1_ID = 0;

    public final static int TEST_PATH2_ID = 1;

    public final static int TEST_PATH3_ID = 2;

    @Test
    public final void testAddPaths() {
        final PathRecorder recorder = new PathRecorder();
        final ElementPath elementPath = new ElementPath(recorder, ELEMENT_ID);
        final Path testPath1 = new Path(ELEMENT_ID, TEST_PATH1_ID, 0, 1, 10, 2, 20);
        final Path testPath2 = new Path(ELEMENT_ID, TEST_PATH2_ID, 1, -1, 8, 2, -21);

        elementPath.addPath(testPath1, false);
        assertEquals(1, elementPath.getPaths().size());
        for(final Path path : elementPath.getPaths()) {
            assertEquals(path, testPath1);
        }
        elementPath.addPath(testPath2, false);
        assertEquals(2, elementPath.getPaths().size());

        assertEquals(elementPath.getTheFirstPath(), testPath1);

        // When removing testPath1 and adding again the testPath2 but setting
        // auto the start time, this path must start before testPath2
        elementPath.removePath(testPath1);
        elementPath.addPath(testPath1, true);
        assertEquals(1, testPath2.getStartTime());
        assertEquals(2, testPath1.getStartTime());
    }

    @Test
    public final void testCalculatePoints() {

    }

    @Test
    public final void testFindNextPath() {

    }

    @Test
    public final void testRemovePath() {
        final PathRecorder recorder = new PathRecorder();
        final ElementPath elementPath = new ElementPath(recorder, ELEMENT_ID);
        Path testPath1 = new Path(ELEMENT_ID, TEST_PATH1_ID, 0, 1, 10, 2, 20);
        Path testPath2 = new Path(ELEMENT_ID, TEST_PATH2_ID, 1, -1, 8, 2, -21);
        Path testPath3 = new Path(ELEMENT_ID, TEST_PATH3_ID, 2, -1, -20, 150, -21);
        elementPath.addPath(testPath1, false);
        // The path is added correctly
        assertEquals(1, elementPath.getPaths().size());
        for(final Path path : elementPath.getPaths()) {
            assertEquals(path, testPath1);
        }
        assertTrue(elementPath.removePath(testPath1));
        // The path is removed correctly
        assertEquals(0, elementPath.getPaths().size());
        elementPath.addPath(testPath1, false);
        assertEquals(1, elementPath.getPaths().size());
        assertFalse(elementPath.removePath(new Path()));

        // No path is remove because the path does not exist
        assertEquals(1, elementPath.getPaths().size());

        // The same test but now the path is not empty
        assertFalse(elementPath.removePath(testPath2));
        assertEquals(1, elementPath.getPaths().size());

        // Added a new path and removed the first one
        elementPath.addPath(testPath2, false);
        assertTrue(elementPath.removePath(testPath1));
        assertEquals(1, elementPath.getPaths().size());
        assertEquals(testPath1.getInitialX(), testPath2.getInitialX());
        assertEquals(testPath1.getInitialY(), testPath2.getInitialY());
        assertEquals(elementPath.getTheFirstPath(), testPath2);

        elementPath.removePath(testPath2);
        assertEquals(0, elementPath.getPaths().size());

        // Now with three paths
        testPath1 = new Path(ELEMENT_ID, TEST_PATH1_ID, 0, 1, 10, 2, 20);
        testPath2 = new Path(ELEMENT_ID, TEST_PATH2_ID, 1, -1, 8, 2, -21);
        testPath3 = new Path(ELEMENT_ID, TEST_PATH3_ID, 2, -1, -20, 150, -21);

        elementPath.addPath(testPath1, false);
        elementPath.addPath(testPath2, false);
        elementPath.addPath(testPath3, false);
        assertEquals(3, elementPath.getPaths().size());
        // Removing the first one
        elementPath.removePath(testPath1);
        assertEquals(2, elementPath.getPaths().size());
        assertEquals(testPath1.getInitialX(), testPath2.getInitialX());
        assertEquals(testPath1.getInitialY(), testPath2.getInitialY());

        elementPath.removePath(testPath2);
        elementPath.removePath(testPath3);

        testPath1 = new Path(ELEMENT_ID, TEST_PATH1_ID, 0, 1, 10, 2, 20);
        testPath2 = new Path(ELEMENT_ID, TEST_PATH2_ID, 1, -1, 8, 2, -21);
        testPath3 = new Path(ELEMENT_ID, TEST_PATH3_ID, 2, -1, -20, 150, -21);

        elementPath.addPath(testPath1, false);
        elementPath.addPath(testPath2, false);
        elementPath.addPath(testPath3, false);

        // Removing the last one
        elementPath.removePath(testPath3);
        assertEquals(2, elementPath.getPaths().size());

        elementPath.removePath(testPath1);
        elementPath.removePath(testPath2);

        testPath1 = new Path(ELEMENT_ID, TEST_PATH1_ID, 0, 1, 10, 2, 20);
        testPath2 = new Path(ELEMENT_ID, TEST_PATH2_ID, 1, -1, 8, 2, -21);
        testPath3 = new Path(ELEMENT_ID, TEST_PATH3_ID, 2, -1, -20, 150, -21);

        elementPath.addPath(testPath1, false);
        elementPath.addPath(testPath2, false);
        elementPath.addPath(testPath3, false);

        // Removing the second one
        elementPath.removePath(testPath2);
        assertEquals(2, elementPath.getPaths().size());
        assertEquals(testPath2.getInitialX(), testPath3.getInitialX());
        assertEquals(testPath2.getInitialY(), testPath3.getInitialY());

    }

    @Test
    public final void testMovePath() {

    }
}
