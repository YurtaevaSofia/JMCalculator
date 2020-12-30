package JMentor;

import java.util.Scanner;

public class Calculator {

    Interpreter interpreter = new Interpreter();

    public void calculate() throws JMCalculatorException {

        System.out.println("Пожалуйста, введите операцию согласно ТЗ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine().trim();

        String[] parts = expression.split(" ");

        if (parts.length != 3) {
            throw new JMCalculatorException("Выражение не соответствует ТЗ! Запишите другое выражение!");
        }

        int firstNumber = interpreter.interpretNumber(parts[0]);
        int logicSymbol = interpreter.interpretSymbol(parts[1]);
        int secondNumber = interpreter.interpretNumber(parts[2]);
        int result = 0;

        if (logicSymbol == 0) {
            throw new JMCalculatorException("На втором месте должен стоять логический символ!");
        }
        switch (logicSymbol) {
            case 1:
                result = firstNumber + secondNumber;
                displayTheResult(expression, result);
                break;
            case 2:
                result = firstNumber - secondNumber;
                displayTheResult(expression, result);
                break;
            case 3:
                result = firstNumber * secondNumber;
                displayTheResult(expression, result);
                break;
            case 4:
                if (firstNumber % secondNumber != 0) {
                    float resultOfDivision = (float) firstNumber / (float) secondNumber;
                    if (interpreter.translationFlag == 0) {
                        System.out.println(expression + " = " + resultOfDivision);
                    } else if (interpreter.translationFlag == 1) {
                        throw new JMCalculatorException("По условиям ТЗ в одном выражении не могут быть римское и арабское число одновременно!");
                    } else {
                        System.out.println("Римские числа не бывают нецелыми! Поэтому результат - деление нацело");
                        result = firstNumber / secondNumber;
                        displayTheResult(expression, result);
                    }
                } else {
                    result = firstNumber / secondNumber;
                    displayTheResult(expression, result);
                }
                break;
        }
    }

    public void displayTheResult(String expression, int result) throws JMCalculatorException {
        if (interpreter.translationFlag == 0) {
            System.out.println(expression + " = " + result);
        }
        if (interpreter.translationFlag == 2) {
            System.out.println(expression + " = " + interpreter.translator.arabicToRomanTranslator(result));
        }
        if (interpreter.translationFlag == 1) {
            throw new JMCalculatorException("По условиям ТЗ в одном выражении не могут быть римское и арабское число одновременно!");
        }
    }
}
