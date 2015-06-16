package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.HashSet;

import static com.twu.biblioteca.Messages.SUCCESSFUL_BOOK_RETURN;
import static com.twu.biblioteca.Messages.UNSUCCESSFUL_BOOK_RETURN;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReturnOptionTest {
    @Mock
    private ConsoleInputOutput consoleInputOutput;

    @Mock
    Library library;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void bookTitleShouldBeObtainedFromTheUser() {
        HashSet<Book> books = new HashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(library.returnBook("Kite Runner")).thenReturn(UNSUCCESSFUL_BOOK_RETURN);
        ReturnOption returnOption = new ReturnOption(consoleInputOutput, library);

        returnOption.obtainOptionResult();

        verify(consoleInputOutput).getUserInput();
    }

    @Test
    public void returnBookShouldPerformBookReturn() {
        HashSet<Book> books = new HashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        ReturnOption returnOption = new ReturnOption(consoleInputOutput, library);

        returnOption.obtainOptionResult();

        verify(library).returnBook("Kite Runner");
    }

    @Test
    public void successfulBookReturnShouldProduceSuccessMessage() {
        HashSet<Book> books = new HashSet<Book>();
        Book bookOne = new CheckedOutBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(library.returnBook("Kite Runner")).thenReturn(SUCCESSFUL_BOOK_RETURN);
        ReturnOption returnOption = new ReturnOption(consoleInputOutput, library);

        returnOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(SUCCESSFUL_BOOK_RETURN);
    }

    @Test
    public void unsuccessfulBookReturnShouldProduceFailureMessage() {
        HashSet<Book> books = new HashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(library.returnBook("Kite Runner")).thenReturn(UNSUCCESSFUL_BOOK_RETURN);
        ReturnOption returnOption = new ReturnOption(consoleInputOutput, library);

        returnOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(UNSUCCESSFUL_BOOK_RETURN);
    }

    @Test
    public void invalidBookNameShouldProduceFailureMessage() {
        HashSet<Book> books = new HashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runn");
        when(library.returnBook("Kite Runn")).thenReturn(UNSUCCESSFUL_BOOK_RETURN);
        ReturnOption returnOption = new ReturnOption(consoleInputOutput, library);

        returnOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(UNSUCCESSFUL_BOOK_RETURN);
    }
}
