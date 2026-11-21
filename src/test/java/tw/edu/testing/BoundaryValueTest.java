package tw.edu.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

public class BoundaryValueTest {

    private final PasswordValidator validator = new PasswordValidator();

    @ParameterizedTest(name = "{0} | 長度={1} | input=\"{2}\" -> expected={3}")
    @CsvSource({
        "BV0_空字串(0筆),          0, '',      false",
        "BV1_下界減1(min-1),       3, a1b,     false",
        "BV2_下界(min),            4, ab12,    true",
        "BV3_下界加1與上界減1,      5, ab123,   true",
        "BV4_上界(max),            6, ab1234,  true",
        "BV5_上界加1(max+1),       7, ab12345, false"
    })
    void testLengthBoundaryValues(String caseName, int expectedLength, String password, boolean expectedValid) {
        assertEquals(expectedLength, password.length(), "輸入字串長度必須符合邊界設計");
        assertEquals(expectedValid, validator.isValid(password));
    }

    @ParameterizedTest(name = "BV6_Null輸入 | input=null -> expected=false")
    @NullSource
    void testNullBoundary(String password) {
        assertEquals(false, validator.isValid(password));
    }
}