package org.password;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class PasswordDataTest {
    PasswordData passwordData;
    int symbolsSum;

    @BeforeEach
    void dataPreparation() {
        passwordData = new PasswordData();
        symbolsSum = passwordData.alphaLowerCaseToGenerate +
                passwordData.alphaUpperCaseToGenerate +
                passwordData.numToGenerate +
                passwordData.specToGenerate;
    }

    @RepeatedTest(10)
    void testPasswordGeneratedDataCorrect() {
        assertTrue(passwordData.alphaLowerCaseToGenerate >= Req.MIN_ALPHA_LOWER.get());
        assertTrue(passwordData.alphaUpperCaseToGenerate >= Req.MIN_ALPHA_UPPER.get());
        assertTrue(passwordData.numToGenerate >= Req.MIN_NUM.get());
        assertTrue(passwordData.specToGenerate >= Req.MIN_SPEC.get());
        assertTrue(symbolsSum >= Req.MIN_SYMBOLS.get());
        assertTrue(symbolsSum <= Req.MAX_SYMBOLS.get());
    }
}