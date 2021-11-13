package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static final String INVALID_FORMAT = "[ERROR]: La operación matemática no tiene el formato correcto. Ej: suma dos y dos y multiplícalo por seis";

    public static void main(String[] args) throws Exception {
        NumbersDictionary numDict = new NumbersDictionary();
        System.out.println("Escribe una operación matemática con suma, resta y/o división que incluya números del 0 al 9(Ej: suma dos y dos y multiplícalo por seis)");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        //String input = "suma dos y dos y multiplícalo por seis";
        //String input = "suma nueve y nueve y dividelo por seis";

        try {
            Integer result = parseInput(numDict, input);
            System.out.println("El resultado de la operación es: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Integer parseInput(NumbersDictionary numDict, String input) throws Exception {
        input = cleanString(input);
        if(!input.contains("y") && (!input.contains("suma") || !input.contains("resta") || !input.contains("multiplica") || !input.contains("divide"))) {
            throw new Exception(Main.INVALID_FORMAT);
        }

        String[] inputParts = input.split("y");
        if(inputParts.length == 2 || inputParts.length == 3) {
            String mainInputs[] = inputParts[0].split(" ");
            String secondInput = inputParts[1].split(" ")[1];
            String firstOp = cleanString(mainInputs[0]);

            if (!numDict.opExists(firstOp) || !numDict.numberExists(mainInputs[1]) || !numDict.numberExists(secondInput)) {
                throw new Exception(Main.INVALID_FORMAT);
            } else {
                Integer firstNum = numDict.stringToInt(mainInputs[1]);
                Integer secondNum = numDict.stringToInt(secondInput);
                Integer result = 0;

                result = numDict.doOperation(firstOp, firstNum, secondNum);

                if(inputParts.length == 3) {
                    String thirdInputs[] = inputParts[2].split(" ");
                    String secondOp = cleanString(thirdInputs[1]);
                    if(!numDict.opExists(secondOp))
                        throw new Exception(Main.INVALID_FORMAT);

                    Integer thirdNum = numDict.stringToInt(inputParts[2].contains("por") ? thirdInputs[3] : thirdInputs[2]);
                    result = numDict.doOperation(secondOp, result, thirdNum);
                }

                return result;
            }
        } else {
            throw  new Exception(Main.INVALID_FORMAT);
        }
    }

    public static String cleanString(String input) {
        String cleanedString = input.toLowerCase();
        cleanedString = cleanedString.replace("á", "a")
                .replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u");
        return cleanedString;
    }
}
