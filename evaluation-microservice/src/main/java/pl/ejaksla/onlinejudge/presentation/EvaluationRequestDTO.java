package pl.ejaksla.onlinejudge.presentation;

public class EvaluationRequestDTO {

    private String programmingLanguage;
    private String submittedCode;
    private String input;
    private String output;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getSubmittedCode() {
        return submittedCode;
    }

    public void setSubmittedCode(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "EvaluationRequestDTO{" +
                "programmingLanguage='" + programmingLanguage + '\'' +
                ", submittedCode='" + submittedCode + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
