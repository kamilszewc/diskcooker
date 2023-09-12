package com.computinglaboratory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface FileGenerator {

    void generate(Path path) throws IOException;
    void generate(Collection<Path> paths) throws IOException;
}
