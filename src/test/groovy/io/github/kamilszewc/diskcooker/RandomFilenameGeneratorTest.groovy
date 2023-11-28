package io.github.kamilszewc.diskcooker

import spock.lang.Specification


class RandomFilenameGeneratorTest extends Specification {

    def "Default generated filename has 6 characters and .dat extension"() {
        given:
        def randomFilenameGenerator = new RandomFilenameGenerator()

        when:
        def filename = randomFilenameGenerator.generate()

        then:
        println filename
        filename.length() == 10
    }

    def "Builder allows to set 12 characters and .txt extension"() {
        given:
        def randomFilenameGenerator = RandomFilenameGenerator.builder()
                                .length(12)
                                .extension('txt')
                                .build()

        when:
        def filename = randomFilenameGenerator.generate()

        then:
        println filename
        filename.length() == 16
    }
}
