package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NumbersDictionary {
    private static HashMap<String, Integer> numbersMap = new HashMap<>();
    private static String[] allowedOps = {"suma", "sumale", "resta", "restale", "multiplica", "multiplicalo", "divide", "dividelo"};

    public NumbersDictionary() {
        fillNumbersMap();
    }

    private static void fillNumbersMap() {
        numbersMap.put("cero", 0);
        numbersMap.put("uno", 1);
        numbersMap.put("dos", 2);
        numbersMap.put("tres", 3);
        numbersMap.put("cuatro", 4);
        numbersMap.put("cinco", 5);
        numbersMap.put("seis", 6);
        numbersMap.put("siete", 7);
        numbersMap.put("ocho", 8);
        numbersMap.put("nueve", 9);
    }

    public static Integer stringToInt(String text) throws Exception {
        String textNumber = text.toLowerCase();
        if(numbersMap.containsKey(textNumber))
            return numbersMap.get(textNumber);
        else
            throw new Exception("[ERROR]: El número \"" + text + "\" no existe en el diccionario. Sólo se permiten números del 0 al 9.");
    }

    public static String intToString(Integer number) throws Exception {
        for(Map.Entry<String, Integer> entry : numbersMap.entrySet()) {
            if(entry.getValue() == number)
                return entry.getKey();
        }
        throw new Exception("[ERROR]: El número \"" + number + "\" no existe en el diccionario. Sólo se permiten números del 0 al 9.");
    }

    public static Boolean opExists(String operation) {
        return Arrays.asList(allowedOps).contains(operation);
    }

    public static Boolean numberExists(String number) {
        try {
            Integer num = stringToInt(number);
            return num >= 0 && num <= 9;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Integer doOperation(String operator, Integer firstNum, Integer secondNum) {
        Integer result = 0;
        result = operator.startsWith("sum") ? (firstNum + secondNum) :
                (operator.startsWith("res") ? (firstNum - secondNum) :
                        (operator.startsWith("mul") ? (firstNum * secondNum) : firstNum / secondNum));
        return result;
    }
}