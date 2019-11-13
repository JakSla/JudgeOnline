package pl.ejaksla.onlinejudge.problem.domain.services.impl

import spock.lang.Specification

class JavaScoringServiceImplTest extends Specification {

    def 'test'() {
        given:
        String code = "import java.io.*;\n" +
                "import java.util.*;\n" +
                "import java.text.*;\n" +
                "import java.math.*;\n" +
                "import java.util.regex.*;\n" +
                "import java.util.stream.Stream;\n" +
                "import java.util.Scanner;\n" +
                "\n" +
                "\n" +
                "public class ProgramNew {\n" +
                "\n" +
                "    static int getPoints(int month1, int month2, int month3){\n" +
                "        // Complete this function\n" +
                "        int totalPoints = 0;\n" +
                "        if(month1 >= 10) {\n" +
                "            totalPoints = totalPoints + 100;\n" +
                "        } else {\n" +
                "            totalPoints = totalPoints + (month1 * 10);\n" +
                "        }\n" +
                "        if(month2 >= 10) {\n" +
                "            totalPoints = totalPoints + 100;\n" +
                "        } else {\n" +
                "            totalPoints = totalPoints + (month2 * 10);\n" +
                "        }\n" +
                "        if(month3 >= 10) {\n" +
                "            totalPoints = totalPoints +  100;\n" +
                "        } else {\n" +
                "            totalPoints = totalPoints + (month3 * 10);\n" +
                "        }\n" +
                "        return totalPoints;\n" +
                "    }\n" +
                "\n" +
                "    public static void main(String[] args) throws IOException {\n" +
                "\t\t        //System.out.println(String.format(\"Hello %s!!\", args[0]));\n" +
                "\t\tint month1 = Integer.parseInt(args[0]);\n" +
                "        int month2 = Integer.parseInt(args[1]);\n" +
                "        int month3 = Integer.parseInt(args[2]);\n" +
                "        int pointsEarned = getPoints(month1, month2, month3);\n" +
                "        System.out.println(pointsEarned);\n" +
                "    }\n" +
                "\t\n" +
                "}"
        def instance =  new JavaScoringServiceImpl();
        when:
        instance.testSubmission(code, "10 20 5", "250")

        then:
        1 == 1
    }
}
