package org.password;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {
    static StringBuilder charsForPassword;
    static int offset;

    @BeforeAll
    static void dataPreparation() {
        charsForPassword = new StringBuilder();
        offset = 0;
    }

    static Stream<Arguments> paramsProvider() {
        return Stream.of(
                Arguments.of(0, Enum.a.get(), Enum.z.get(), false),
                Arguments.of(5, Enum.a.get(), Enum.z.get(), false),
                Arguments.of(7, Enum.A.get(), Enum.Z.get(), false),
                Arguments.of(1, Enum.ZERO.get(), Enum.NINE.get(), false),
                Arguments.of(19, -1, -1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsProvider")
    void testAddCharsOnce(int countToAdd, int from, int to, boolean spec) {
        PasswordGenerator.addChars(charsForPassword, countToAdd, from, to, spec);

        int len = charsForPassword.length() - offset;
        assertEquals(len, countToAdd);
        for (int i = offset; i < len; ++i) {
            if (!spec) {
                assertTrue(charsForPassword.charAt(i) >= from && charsForPassword.charAt(i) <= to);
            } else {
                assertTrue(charsForPassword.charAt(i) >= Enum.MARK.get() && charsForPassword.charAt(i) <= Enum.SLASH.get() ||
                        charsForPassword.charAt(i) >= Enum.TWO_DOT.get() && charsForPassword.charAt(i) <= Enum.DOG.get() ||
                        charsForPassword.charAt(i) >= Enum.START_ARR.get() && charsForPassword.charAt(i) <= Enum.APOS.get() ||
                        charsForPassword.charAt(i) >= Enum.START_FIGURE.get() && charsForPassword.charAt(i) <= Enum.TILDA.get()
                );
            }
        }
        offset += len;
    }
}