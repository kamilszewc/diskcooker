package io.github.kamilszewc.diskcooker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PredefinedByteFileGenerator implements FileGenerator {

    @Builder.Default
    private byte[] bytes = new byte[0];

    @Override
    public void generate(Path path) throws IOException {
        Files.write(path, bytes, StandardOpenOption.CREATE);
    }

    @Override
    public void generate(Collection<Path> paths) throws IOException {
        for (Path path : paths) {
            generate(path);
        }
    }

}
