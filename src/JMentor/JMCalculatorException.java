package JMentor;

public class JMCalculatorException extends Exception {
    public JMCalculatorException(String message) {
        super(message);
    }

    public void alert(String message) {
        System.out.println(message);
    }
}
