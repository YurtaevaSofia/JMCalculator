package JMentor;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {

    public String romanToArabicTranslator(String romanNumber) throws JMCalculatorException {

        romanNumber = romanNumber.toUpperCase();
        String[] symbols = romanNumber.split("");
        Pattern patternForSymbols = Pattern.compile("[IVXLCDM]");
        for (String symbol : symbols) {
            Matcher matcherForSymbols = patternForSymbols.matcher(symbol);
            if (!matcherForSymbols.matches()) {
                throw new JMCalculatorException("Выражение " + romanNumber + " не является ни римским, ни арабским числом, либо это число не круглое!");
            }
        }

        int resultArabicNumber = 0;

        if (romanNumber.contains("XLX") || romanNumber.contains("IIX") || romanNumber.contains("IIV") || romanNumber.contains("IL") || romanNumber.contains("VLV") || romanNumber.contains("LL") || romanNumber.contains("VV") || romanNumber.contains("IIII") || romanNumber.indexOf("L") > 1) {
            throw new JMCalculatorException("Римское число " + romanNumber + " записано неверно!");
        } else {
            LinkedHashMap<String, Integer> book = new LinkedHashMap<>();

            book.put("M", 1000);
            book.put("DM", 900);
            book.put("CD", 400);
            book.put("C", 100);
            book.put("L", 50);
            book.put("XL", 40);
            book.put("X", 10);
            book.put("IX", 9);
            book.put("V", 5);
            book.put("IV", 4);
            book.put("I", 1);

            while (romanNumber.length() != 0) {
                for (String key : book.keySet()) {
                    if (romanNumber.indexOf(key) == 0) {
                        romanNumber = romanNumber.substring(key.length());
                        resultArabicNumber += book.get(key);
                    }
                }
            }
            return Integer.toString(resultArabicNumber);
        }

    }

    public String arabicToRomanTranslator(int arabicNumber) throws JMCalculatorException {

        String romanNumeral = "";

        LinkedHashMap<Integer, String> book = new LinkedHashMap<>();

        book.put(1000, "M");
        book.put(900, "СM");
        book.put(500, "D");
        book.put(400, "CD");
        book.put(100, "C");
        book.put(90, "XC");
        book.put(50, "L");
        book.put(40, "XL");
        book.put(10, "X");
        book.put(9, "IX");
        book.put(5, "V");
        book.put(4, "IV");
        book.put(1, "I");


        // цикл для для отрицательных римских чисел или 0
        if (arabicNumber > 0) {
            while (arabicNumber > 0) {
                for (int key : book.keySet()) {
                    int i = (arabicNumber - (arabicNumber % key)) / key;
                    if (i > 0) {
                        for (int j = 0; j < i; j++) {
                            romanNumeral += book.get(key);
                        }
                        arabicNumber -= key * i;
                    }
                }
            }
        } else throw new JMCalculatorException("В римских числах нет нуля и отрицательных!");
        return romanNumeral;
    }
}
