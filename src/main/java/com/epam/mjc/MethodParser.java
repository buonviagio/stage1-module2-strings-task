package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer stringTokenizer = new StringTokenizer(signatureString);
        MethodSignature methodSignature;

        String firstPartOfMethod = stringTokenizer.nextToken("(");
        String[] array = firstPartOfMethod.trim().split(" ");
        String accessModifier = null;
        String returnValue, nameOfMethod;

        if (array.length == 3) {
            switch (array[0]) {
                case "private":
                    accessModifier = "private";
                    break;
                case "public":
                    accessModifier = "public";
                    break;
                case "protected":
                    accessModifier = "protected";
            }
            returnValue = array[1];
            nameOfMethod = array[2];
        } else {
            returnValue = array[0];
            nameOfMethod = array[1];
        }

        String result = stringTokenizer.nextToken("(");
        result = result.substring(0, result.length() - 1);
        String[] arguments = result.split(",");

        List<MethodSignature.Argument> list = new ArrayList<>();

        String[] arrayTmp;
        if (arguments.length > 1) {
            for (int i = 0; i < arguments.length; i++) {
                arrayTmp = arguments[i].trim().split(" ");
                MethodSignature.Argument argument = new MethodSignature.Argument(arrayTmp[0].trim(), arrayTmp[1].trim());
                list.add(argument);
            }
        }
        methodSignature = new MethodSignature(nameOfMethod, list);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnValue);
        return methodSignature;
    }
}
