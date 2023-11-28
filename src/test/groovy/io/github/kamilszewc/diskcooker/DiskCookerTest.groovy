package io.github.kamilszewc.diskcooker

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class DiskCookerTest extends Specification {

    def "Basic test"() {
        given:
        def path = Path.of("playground")
        def diskCooker = DiskCooker.builder()
                .delay(100)
                .numberOfFilesLimit(10)
                .timeLimit(200)
                .filenameGenerator(RandomFilenameGenerator.builder()
                        .length(10)
                        .extension("txt")
                        .build())
                .logging(true)
                .build()

        when:
        diskCooker.cook(path)

        then:
        Files.exists(path)
    }

    def "Basic test with collecting generated filenmaes"() {
        given:
        def path = Path.of("playground")
        def diskCooker = DiskCooker.builder()
                .delay(100)
                .numberOfFilesLimit(10)
                .timeLimit(200)
                .filenameGenerator(RandomFilenameGenerator.builder()
                        .length(10)
                        .extension("txt")
                        .build())
                .build()
        def generatedFilesPaths = []

        when:
        diskCooker.cook(path, generatedFilesPaths)

        then:
        println generatedFilesPaths
    }

    def "Basic test with bid data and with collecting generated filenames"() {
        given:
        def path = Path.of("playground")
        def diskCooker = DiskCooker.builder()
                .delay(100)
                .numberOfFilesLimit(10)
                .timeLimit(200)
                .fileGenerator(RandomTextFileGenerator.builder()
                    .size(10000000)
                    .build())
                .filenameGenerator(RandomFilenameGenerator.builder()
                        .length(10)
                        .extension("txt")
                        .build())
                .build()
        def generatedFilesPaths = []

        when:
        diskCooker.cook(path, generatedFilesPaths)

        then:
        println generatedFilesPaths
    }

}
