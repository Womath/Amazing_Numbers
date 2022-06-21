package numbers;

import numbers.enumerator.Properties;
import numbers.numbers.MagicNumber;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat("#,###");


        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        while (true) {

            System.out.println("\nEnter a request:");
            String input = scanner.nextLine();
            String[] list = input.split(" ");
            long number = 0;
            long process = Long.MIN_VALUE;
            String property1 = null;
            String property2 = null;
            ArrayList<String> wrongProperties = new ArrayList<>();
            if (list.length == 1) {
                if (isNumeric(list[0])){
                    number = Long.parseLong(list[0]);
                    if (number == 0) {
                        exit();
                    } else if (number < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else {
                        MagicNumber number1 = new MagicNumber(number);
                        oneProperty(number1);
                    }
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            } else if (list.length == 2) {
                if (isNumeric(list[0])){
                    number = Long.parseLong(list[0]);
                    if (number == 0) {
                        exit();
                    } else if (number < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }

                if (isNumeric(list[1])) {
                    process = Long.parseLong(list[1]);
                    if (process <= 0) {
                        System.out.println("The second parameter should be a natural number.");
                        continue;
                    }
                } else {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }
                MagicNumber[] magicNumbers = new MagicNumber[(int) process];
                for (int i = 0; i < process; i++) {
                    magicNumbers[i] = new MagicNumber(number + i);
                    twoProperties(magicNumbers[i]);

                }

            } else {
                if (isNumeric(list[0])){
                    number = Long.parseLong(list[0]);
                    if (number == 0) {
                        exit();
                    } else if (number < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }

                if (isNumeric(list[1])) {
                    process = Long.parseLong(list[1]);
                    if (process <= 0) {
                        System.out.println("The second parameter should be a natural number.");
                        continue;
                    }
                } else {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }

                String[] properties = new String[list.length - 2];
                for (int i = 2; i < list.length; i++) {
                    properties[i - 2] = list[i].toUpperCase();
                    if (!isValid(list[i])) {
                        wrongProperties.add(list[i].toUpperCase());
                    }

                }

                if (wrongProperties.size() > 0) {
                    if (wrongProperties.size() == 1) {
                        System.out.println("The property " + wrongProperties + " is wrong.\n" +
                                "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
                    } else {
                        System.out.println("The properties " + wrongProperties + " are wrong.\n" +
                                "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
                    }
                    continue;
                }

                if (isMutuallyExclusive(properties)) {
                    continue;
                }

                long counter = 0;
                for (int i = 0; counter < process; i++) {
                    if (propFinder(new MagicNumber(number + i), properties)) {
                        counter++;
                    }

                }
            }
        }
    }

    public static void exit() {
        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    public static void oneProperty(MagicNumber number) {

            boolean buzz = number.getBuzz() != null;
            boolean duck = number.getDuck() != null;
            boolean palindromic = number.getPalindromic() != null;
            boolean gapful = number.getGapful() != null;
            boolean even = number.getEven() != null;
            boolean odd = number.getOdd() != null;
            boolean spy = number.getSpy() != null;
            boolean square = number.getSquare() != null;
            boolean sunny = number.getSunny() != null;
            boolean jumping = number.getJumping() != null;
            boolean happy = number.getHappy() != null;
            boolean sad = number.getSad() != null;

            System.out.printf("%nProperties of %,d%n", number.getNumber());
            System.out.printf("        buzz: %b" +
                    "%n        duck: %b" +
                    "%n palindromic: %b" +
                    "%n      gapful: %b" +
                    "%n         spy: %b" +
                    "%n      square: %b" +
                    "%n       sunny: %b" +
                    "%n     jumping: %b" +
                    "%n       happy: %b" +
                    "%n         sad: %b" +
                    "%n        even: %b" +
                    "%n         odd: %b%n",
                    buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, even, odd);

    }

    public static void twoProperties(MagicNumber number) {
        System.out.println(number.getNumber() + " is" +
                (number.getBuzz() == Properties.BUZZ ? " buzz," : "") +
                (number.getDuck() == Properties.DUCK ? " duck," : "") +
                (number.getPalindromic() == Properties.PALINDROMIC ? " palindromic," : "") +
                (number.getGapful() == Properties.GAPFUL ? " gapful," : "") +
                (number.getSpy() == Properties.SPY ? " spy," : "") +
                (number.getSquare() == Properties.SQUARE ? " square," : "") +
                (number.getSunny() == Properties.SUNNY ? " sunny," : "") +
                (number.getJumping() == Properties.JUMPING ? " jumping," : "") +
                (number.getHappy() == Properties.HAPPY ? " happy," : "") +
                (number.getSad() == Properties.SAD ? " sad," : "") +
                (number.getEven() == Properties.EVEN ? " even" : "") +
                (number.getOdd() == Properties.ODD ? " odd" : ""));

    }

    public static boolean isValid(String property) {
        if (property.startsWith("-")) {
            property = property.substring(1);
        }
        return switch (property.toUpperCase()) {
            case "BUZZ", "PALINDROMIC", "GAPFUL", "DUCK", "SPY", "SQUARE", "EVEN", "SUNNY", "JUMPING", "HAPPY", "SAD", "ODD" -> true;
            default -> false;
        };
    }

    public static boolean isMutuallyExclusive (String[] properties) {
        for (String property : properties) {
            for (String s : properties) {
                if (property.toUpperCase().equals("EVEN") && s.toUpperCase().equals("ODD")) {
                    System.out.println("The request contains mutually exclusive properties: [EVEN, ODD]" +
                            "\nThere are no numbers with these properties.");
                    return true;

                } else if (property.toUpperCase().equals("DUCK") && s.toUpperCase().equals("SPY")) {
                    System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]" +
                            "\nThere are no numbers with these properties.");
                    return true;

                } else if (property.toUpperCase().equals("SUNNY") && s.toUpperCase().equals("SQUARE")) {
                    System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]" +
                            "\nThere are no numbers with these properties.");
                    return true;

                } else if (property.toUpperCase().equals("HAPPY") && s.toUpperCase().equals("SAD")) {
                    System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]" +
                            "\nThere are no numbers with these properties.");
                    return true;
                } else if (property.toUpperCase().equals("-" + s.toUpperCase())) {
                    System.out.println(("The request contains mutually exclusive properties: [" + property.toUpperCase() + ", " +
                            s.toUpperCase() + "]\nThere are no numbers with these properties."));
                    return true;
                } else if (property.toUpperCase().equals("-EVEN") && s.toUpperCase().equals("-ODD")) {
                    System.out.println("The request contains mutually exclusive properties: [-EVEN, -ODD]" +
                            "\nThere are no numbers with these properties.");
                    return true;

                }  else if (property.toUpperCase().equals("-HAPPY") && s.toUpperCase().equals("-SAD")) {
                    System.out.println("The request contains mutually exclusive properties: [-HAPPY, -SAD]" +
                            "\nThere are no numbers with these properties.");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean propFinder(MagicNumber number, String[] properties) {

        int counter = 0;

        for (String property : properties) {
            if (property.equals(String.valueOf(number.getEven())) ||
                    property.equals(String.valueOf(number.getOdd())) ||
                    property.equals(String.valueOf(number.getBuzz())) ||
                    property.equals(String.valueOf(number.getDuck())) ||
                    property.equals(String.valueOf(number.getPalindromic())) ||
                    property.equals(String.valueOf(number.getGapful())) ||
                    property.equals(String.valueOf(number.getSpy())) ||
                    property.equals(String.valueOf(number.getSquare())) ||
                    property.equals(String.valueOf(number.getSunny())) ||
                    property.equals(String.valueOf(number.getJumping())) ||
                    property.equals(String.valueOf(number.getHappy())) ||
                    property.equals(String.valueOf(number.getSad()))
            ){
                counter++;
            }
        }

        for (String property : properties) {
            if (property.startsWith("-")) {
                property = property.substring(1);
                if (property.equals(String.valueOf(number.getEven())) ||
                        property.equals(String.valueOf(number.getOdd())) ||
                        property.equals(String.valueOf(number.getBuzz())) ||
                        property.equals(String.valueOf(number.getDuck())) ||
                        property.equals(String.valueOf(number.getPalindromic())) ||
                        property.equals(String.valueOf(number.getGapful())) ||
                        property.equals(String.valueOf(number.getSpy())) ||
                        property.equals(String.valueOf(number.getSquare())) ||
                        property.equals(String.valueOf(number.getSunny())) ||
                        property.equals(String.valueOf(number.getJumping())) ||
                        property.equals(String.valueOf(number.getHappy())) ||
                        property.equals(String.valueOf(number.getSad()))
                ) {

                } else {
                    counter++;
                }
            }
        }

        if (counter == properties.length) {
            twoProperties(number);
            return true;
        }
        return false;
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            long l = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
