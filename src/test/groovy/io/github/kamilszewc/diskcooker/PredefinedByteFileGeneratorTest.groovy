package io.github.kamilszewc.diskcooker

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class PredefinedByteFileGeneratorTest extends Specification {

    def "Default usage of generator generate empty byte file"() {
        given:
        def predefinedFileGenerator = new PredefinedByteFileGenerator()
        def path = Path.of("playground/testfile.txt")

        when:
        predefinedFileGenerator.generate(path)

        then:
        Files.exists(path)
        Files.size(path) == 0
    }

    def "Default usage of generator generate multiple empty byte files if using generate(collection) method"() {
        given:
        def predefinedFileGenerator = new PredefinedByteFileGenerator()
        def paths = [Path.of("playground/testfile1.txt"),
                                    Path.of("playground/testfile2.txt"),
                                    Path.of("playground/testfile3.txt"),
                                    Path.of("playground/testfile4.txt")]

        when:
        predefinedFileGenerator.generate(paths)

        then:
        paths.forEach {
            Files.size(it) == 0
        }
    }

    def "Object builder setting bytes correctly generate file with given byte data"() {
        given:
        def predefinedFileGenerator = PredefinedByteFileGenerator.builder().bytes("x".bytes).build()
        def path = Path.of("playground/testfile.txt")

        when:
        predefinedFileGenerator.generate(path)

        then:
        Files.exists(path)
        Files.size(path) == 1
    }
}
