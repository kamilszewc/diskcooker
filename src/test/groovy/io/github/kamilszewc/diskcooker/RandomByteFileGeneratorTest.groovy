package io.github.kamilszewc.diskcooker

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class RandomByteFileGeneratorTest extends Specification {

    def "Default usage of generator generate 1024 bytes file"() {
        given:
        def randomFileGenerator = new RandomByteFileGenerator()
        def path = Path.of("playground/testfile.txt")

        when:
        randomFileGenerator.generate(path)

        then:
        Files.exists(path)
        Files.size(path) == 1024
    }

    def "Default usage of generator generate multiple 1024 bytes files if using generate(collection) method"() {
        given:
        def randomFileGenerator = new RandomByteFileGenerator()
        def paths = [Path.of("playground/testfile1.txt"),
                                    Path.of("playground/testfile2.txt"),
                                    Path.of("playground/testfile3.txt"),
                                    Path.of("playground/testfile4.txt")]

        when:
        randomFileGenerator.generate(paths)

        then:
        paths.forEach {
            Files.size(it) == 1024
        }
    }

    def "Object builder setting size correctly generate filesize"() {
        given:
        def randomFileGenerator = RandomByteFileGenerator.builder().size(2000).build()
        def path = Path.of("playground/testfile.txt")

        when:
        randomFileGenerator.generate(path)

        then:
        Files.exists(path)
        Files.size(path) == 2000
    }
}
