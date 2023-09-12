package com.computinglaboratory

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class PredefinedTextFileGeneratorTest extends Specification {

    def "Default usage of generator generate empty text file"() {
        given:
        def predefinedTextFileGenerator = new PredefinedTextFileGenerator()
        def path = Path.of("playground/testfile.txt")

        when:
        predefinedTextFileGenerator.generate(path)

        then:
        Files.exists(path)
        Files.readString(path).length() == 0
    }

    def "Default usage of generator generate multiple empty text files if using generate(collection) method"() {
        given:
        def predefinedFileGenerator = new PredefinedTextFileGenerator()
        def paths = [Path.of("playground/testfile1.txt"),
                                    Path.of("playground/testfile2.txt"),
                                    Path.of("playground/testfile3.txt"),
                                    Path.of("playground/testfile4.txt")]

        when:
        predefinedFileGenerator.generate(paths)

        then:
        paths.forEach {
            Files.readString(it).length() == 0
        }
    }

    def "Object builder setting text correctly generate file with given text"() {
        given:
        def predefinedTextFileGenerator = PredefinedTextFileGenerator.builder().text("text").build()
        def path = Path.of("playground/testfile.txt")

        when:
        predefinedTextFileGenerator.generate(path)

        then:
        Files.exists(path)
        Files.readString(path) == "text"
    }
}
