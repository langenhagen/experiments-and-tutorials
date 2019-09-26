package org.sercho.masp.models.Context.gui.pathRecording;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.sercho.masp.models.MASPDirectory;

/**
 * This class represents a complete path recording. Provides a method for load a
 * recording from file.
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
@XmlRootElement
public final class PathRecording {

    public PathRecording() {
        this.paths = new HashSet<Path>();
    }

    public static PathRecording getRecordFromMASPDirectory(final String fileName) throws JAXBException, FileNotFoundException {
        final FileInputStream in = new FileInputStream(MASPDirectory.getMASPDirectoryPath() + "/" + fileName);
        return (PathRecording)getUnmarshaller().unmarshal(in);
    }

    public static synchronized Unmarshaller getUnmarshaller() {
        try {
            return JAXBContext.newInstance(PathRecording.class, Path.class).createUnmarshaller();
        }
        catch(final JAXBException e) {
            throw new IllegalStateException("Failed to create unmarshaller", e);
        }
    }

    @XmlElement
    private final Set<Path> paths;

    public Set<Path> getPaths() {
        return this.paths;
    }

    public void addPaths(final List<Path> paths) {
        this.paths.addAll(paths);

    }

    public void addPath(final Path path) {
        this.paths.add(path);
    }
}
