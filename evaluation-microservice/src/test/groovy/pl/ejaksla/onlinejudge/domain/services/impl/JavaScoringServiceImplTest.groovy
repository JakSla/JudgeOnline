package pl.ejaksla.onlinejudge.domain.services.impl

import spock.lang.Specification

import javax.tools.JavaFileObject

class JavaScoringServiceImplTest extends Specification {

    def 'simpleTest'() {
        when:
        def instance =  new JavaScoringServiceImpl();

        then:
        JavaFileObject result = instance.compileJava("class HelloWorld {\n" +
                "    HelloWorld() {\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] var0) {\n" +
                "        System.out.println(\"This is another java file\");\n" +
                "    }\n" +
                "}")

        when:
        def runtest = new RunJUnit5TestsFromJava("class HelloWorld {\n" +
                "    HelloWorld() {\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] var0) {\n" +
                "        System.out.println(\"This is another java file\");\n" +
                "    }\n" +
                "}")
        then:
        runtest.runOne()

    }
}
