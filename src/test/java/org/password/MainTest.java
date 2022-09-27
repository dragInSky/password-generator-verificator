/*
package org.password;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @ParameterizedTest
    @ValueSource(strings = {"yes", "no\naA46&h2be87", "no\naaa\nno\naA46&h2be87"})
    void testMain(String str) {
        byte[] arr = str.getBytes();
        String consoleOutput = null;
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(arr);
            System.setIn(stream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);

            Main.main(null);

            capture.flush();
            consoleOutput = outputStream.toString();

            System.setIn(System.in);
            System.setOut(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(consoleOutput);
        assertEquals("\nВаш пароль - ", consoleOutput.substring(0, 15));
    }
}*/
