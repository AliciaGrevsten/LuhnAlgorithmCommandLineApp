import java.io.BufferedReader;
import java.io.InputStreamReader;

/** @author Alicia Grevsten
**  Last edited 2020-07-31
**  A program that takes an input of numbers (any length) and uses the Luhn Algorithm to perform a simple checksum on the last digit of the user input.
**  It then display the following:
**      The full user input, with the check digit separated
**      A comparison of the provided check digit compared to the expected (calculated) check digit
**      A clear output of Valid or Invalid for the user input
**      It must indicate if the solution is long enough to be a credit card number (16 digits)
 **/
public class Main {

    private static int expectedDigit;

    public static void main(String[] args) {
        System.out.print("Enter card number: ");
        String input = readUserInput();
        System.out.println();

        if (input != null) {
            if (isNumeric(input) && isValidLength(input, 16)) {
                int inputCheckDigit = Integer.parseInt(input.substring(input.length()-1));
                boolean isValid = validateCheckDigit(input, inputCheckDigit);

                // Output
                System.out.println("Input: " + input.substring(0, input.length()-1) + " " + inputCheckDigit);
                System.out.println("Provided: " + inputCheckDigit);
                System.out.println("Expected: " + expectedDigit);
                System.out.println("Checksum: " + isValid);
                if (isValid)
                    System.out.println("Digits: " + input.length() + " (credit card)");
                else
                    System.out.println("Digits: " + input.length() + " (not a credit card)");
            } else {
                System.out.println("A credit card number consists of 16 numbers you silly goose. Try again.");
            }
        } else {
            System.out.println("You can't just leave it blank!");
        }
    }

    // Returns true if given card number is valid
    static boolean validateCheckDigit(String cardNo, int inputCheckDigit) {
        // Convert input to int
        int[] creditCardInt = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            creditCardInt[i] = Integer.parseInt(cardNo.substring(i, i + 1));
        }

        //  Starting from the right (skipping check digit), double each other digit, if greater than 9 mod 10 and + 1 to the reminder
        for (int i = creditCardInt.length - 2; i >= 0; i = i - 2) {
            int tempValue = creditCardInt[i];
            tempValue = tempValue * 2;
            if (tempValue > 9)
                tempValue = tempValue % 10 + 1;
            creditCardInt[i] = tempValue;
        }

        //  Add upp all the digits except the check digit
        int total = 0;
        for (int i = 0; i < creditCardInt.length-1; i++) {
            total += creditCardInt[i];
        }

        expectedDigit = (total * 9) % 10;

        // check if input digit is same as % of total
        return inputCheckDigit == expectedDigit;
    }

    //  returns true if the input is the specific length
    public static boolean isValidLength(String input, int length) {
        return input.length() == length;
    }

    // returns true if all the chars in a string is numbers
    public static boolean isNumeric(String input) {
        char[] chars = input.toCharArray();
        boolean isNumeric = false;

        for (char c: chars) {
            if (Character.isDigit(c))
            {
                isNumeric = true;
            } else {
                return false;
            }
        }
        return isNumeric;
    }

    public static String readUserInput() {
        try {
            String input;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            input = bufferedReader.readLine();

            return input;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
