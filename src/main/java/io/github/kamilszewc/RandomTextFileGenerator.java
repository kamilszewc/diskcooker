package io.github.kamilszewc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

/**
 * RandomTextFileGenerator class
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RandomTextFileGenerator implements FileGenerator {

    @Builder.Default
    private Integer size = 1024;

    @Builder.Default
    private Long seed = null;

    @Override
    public void generate(Path path) throws IOException {
        String generatedString = seed == null ? RandomTextGenerator.generate(size) : RandomTextGenerator.generate(size, seed);
        Files.writeString(path, generatedString, StandardOpenOption.CREATE);
    }

    @Override
    public void generate(Collection<Path> paths) throws IOException {
        for (Path path : paths) {
            generate(path);
        }
    }

}
