package io.github.kamilszewc.diskcooker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Random;

/**
 * RandomByteFileGenerator class
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RandomByteFileGenerator implements FileGenerator {

    @Builder.Default
    private Integer size = 1024;

    @Builder.Default
    private Long seed = null;

    @Override
    public void generate(Path path) throws IOException {
        Random random = seed == null ? new Random() : new Random(seed);
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        Files.write(path, bytes, StandardOpenOption.CREATE);
    }

    @Override
    public void generate(Collection<Path> paths) throws IOException {
        for (Path path : paths) {
            generate(path);
        }
    }

}
