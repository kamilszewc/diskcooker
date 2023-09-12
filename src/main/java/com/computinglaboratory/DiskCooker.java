package com.computinglaboratory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

/**
 * DiskCooker class
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiskCooker {

    @Builder.Default
    private Integer delay = 0;

    @Builder.Default
    private FilenameGenerator filenameGenerator = new RandomFilenameGenerator();

    @Builder.Default
    private Integer numberOfFilesLimit = null;

    @Builder.Default
    private Long timeLimit = null;

    @Builder.Default
    private FileGenerator fileGenerator = new RandomTextFileGenerator();

    /**
     * Method starts files generation in specified folder
     * @param path path to folder where files will be generated
     * @throws IOException
     * @throws InterruptedException
     */
    public void cook(Path path) throws IOException, InterruptedException {
        cook(path, null);
    }

    /**
     * Method starts files generation in specified folder
     * @param path path to folder where files will be generated
     * @param generatedFilesPaths collection that stores generated file paths
     * @throws IOException
     * @throws InterruptedException
     */
    public void cook(Path path, Collection<Path> generatedFilesPaths) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        long generatedNumberOfFiles = 0;
        while(true) {
            // Delay
            Thread.sleep(delay);

            // Generate filename
            String filename = filenameGenerator.generate();

            // Construct path
            Path generatedFilePath = path.resolve(filename);

            // Generate file
            fileGenerator.generate(generatedFilePath);

            if (generatedFilesPaths != null) {
                generatedFilesPaths.add(generatedFilePath);
            }

            generatedNumberOfFiles += 1;

            // Check the number of files limit
            if (numberOfFilesLimit != null) {
                if (generatedNumberOfFiles >= numberOfFilesLimit) break;
            }

            // Check the time limit
            if (timeLimit != null) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= timeLimit) break;
            }
        }
    }
}