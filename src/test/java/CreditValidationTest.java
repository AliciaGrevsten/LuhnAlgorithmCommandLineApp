import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CreditValidationTest {
    private final int length = 16;

    @Test
    void checkValidCheckDigit () {
        assertTrue(Main.validateCheckDigit("1234567891234563", 3));
        assertTrue(Main.validateCheckDigit("4242424242424242", 2));
    }

    @Test
    void checkInvalidCheckDigit () {
        assertFalse(Main.validateCheckDigit("1234567891234567", 7));
        assertFalse(Main.validateCheckDigit("4242424242424245", 5));
    }

    @Test
    void checkIsNumeric () {
        assertTrue(Main.isNumeric("12345678912345"));
    }

    @Test
    void checkIsNotNumeric () {
        assertFalse(Main.isNumeric("123abc123"));
        assertFalse(Main.isNumeric("abc123"));
        assertFalse(Main.isNumeric("123abc"));
        assertFalse(Main.isNumeric("@£$€"));
    }

    @Test
    void checkLongEnough () {
        assertTrue(Main.isValidLength("4242424242424242", length));
    }

    @Test
    void checkIfInvalidLength() {
        assertFalse(Main.isValidLength("12345", length));
        assertFalse(Main.isValidLength("123456789123456789", length));
    }

}