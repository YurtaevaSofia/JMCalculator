package JMentor;

public class Interpreter {

    Translator translator = new Translator();
    int translationFlag = 0;

    public int interpretNumber(String symbol) throws JMCalculatorException {

        int result;
        if (isDigit(symbol)) {
            int i = Integer.parseInt(symbol);
            if (i > 0 && i < 11) {
                result = i;
            } else throw new JMCalculatorException("Число " + symbol + " меньше 1 или больше 10!");
        } else {
            int i = Integer.parseInt(translator.romanToArabicTranslator(symbol));
            if (i > 0 && i < 11) {
                result = i;
                translationFlag++;
            } else throw new JMCalculatorException("Число " + symbol + " меньше 1 или больше 10!");
        }
        return result;
    }

    public int interpretSymbol(String symbol) throws JMCalculatorException {
        int result = 0;
        String logicSymbols = "+ - * /";
        if (logicSymbols.contains(symbol)) {
            switch (symbol) {
                case "+":
                    result = 1;
                    break;
                case "-":
                    result = 2;
                    break;
                case "*":
                    result = 3;
                    break;
                case "/":
                    result = 4;
                    break;
                default:
                    break;
            }
        } else throw new JMCalculatorException("На втором месте должен стоять допустимый логический символ!");

        return result;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}














