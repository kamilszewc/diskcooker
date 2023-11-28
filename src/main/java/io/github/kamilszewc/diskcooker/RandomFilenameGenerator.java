package io.github.kamilszewc.diskcooker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RandomFilenameGenerator implements FilenameGenerator {

    @Builder.Default
    private Integer length = 6;

    @Builder.Default
    private String extension = "dat";

    @Builder.Default
    private Long seed = null;

    @Override
    public String generate() {
        String generatedString = seed == null ? RandomTextGenerator.generate(length) : RandomTextGenerator.generate(length, seed);
        return generatedString + "." + extension;
    }
}
