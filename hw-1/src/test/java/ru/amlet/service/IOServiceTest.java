package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Имплементация класса IOService")
public class IOServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("метод writeString выводит на console текст переданный в него")
    void writeStringShouldPutStringInConsole(){
        IOService ioService = new IOServiceImpl(System.in, System.out);
        String question = "questionsText";
        ioService.writeString(question);
        assertEquals("questionsText\n", outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("метод readString считывает с console текст")
    void readStringShouldGetStringFromConsole() {
        String answer = "answerText";
        InputStream in = new ByteArrayInputStream(answer.getBytes());
        IOService ioService = new IOServiceImpl(in, System.out);
        assertEquals(answer, ioService.readString());
    }

}
