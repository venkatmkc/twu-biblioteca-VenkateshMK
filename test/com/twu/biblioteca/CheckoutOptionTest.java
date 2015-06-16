package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class CheckoutOptionTest {
    @Mock
    private ConsoleInputOutput consoleInputOutput;

    @Mock
    BookParser bookParser;

    @Mock
    Library library;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void bookTitleShouldBeObtainedFromTheUser() {

        ArrayList<Book> availableBooks = new ArrayList<Book>();
        ArrayList<Book> checkedoutBooks = new ArrayList<Book>();
        Book bookOne = new Book("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new Book("The Sky Is Falling", "Sidney Sheldon", "2001");
        availableBooks.add(bookOne);
        availableBooks.add(bookTwo);
        Library library = new Library(availableBooks, checkedoutBooks);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, bookParser, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).getUserInput();
    }

    @Test
    public void checkoutShouldParseUserInputToBook() {
        HashMap<Book, Boolean> bookList = new HashMap<Book, Boolean>();
        Book bookOne = new Book("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new Book("The Sky Is Falling", "Sidney Sheldon", "2001");
        bookList.put(bookOne, true);
        bookList.put(bookTwo, true);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, bookParser, library);

        checkoutOption.obtainOptionResult();

        verify(bookParser).parseUserInput("Kite Runner");
    }

    @Test
    public void checkoutShouldPerformBookCheckout() {
        HashMap<Book, Boolean> bookList = new HashMap<Book, Boolean>();
        Book bookOne = new Book("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new Book("The Sky Is Falling", "Sidney Sheldon", "2001");
        bookList.put(bookOne, true);
        bookList.put(bookTwo, true);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(bookParser.parseUserInput("Kite Runner")).thenReturn(bookOne);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, bookParser, library);

        checkoutOption.obtainOptionResult();

        verify(library).checkout(bookOne);
    }

    @Test
    public void successfulCheckoutShouldProduceSuccessMessage() {
        HashMap<Book, Boolean> bookList = new HashMap<Book, Boolean>();
        Book bookOne = new Book("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new Book("The Sky Is Falling", "Sidney Sheldon", "2001");
        bookList.put(bookOne, true);
        bookList.put(bookTwo, true);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(bookParser.parseUserInput("Kite Runner")).thenReturn(bookOne);
        when(library.checkout(bookOne)).thenReturn(true);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, bookParser, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(Messages.SUCCESSFUL_CHECKOUT);
    }

    @Test
    public void unsuccessfulCheckoutShouldProduceFailureMessage() {
        HashMap<Book, Boolean> bookList = new HashMap<Book, Boolean>();
        Book bookOne = new Book("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new Book("The Sky Is Falling", "Sidney Sheldon", "2001");
        bookList.put(bookOne, true);
        bookList.put(bookTwo, true);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(bookParser.parseUserInput("Kite Runner")).thenReturn(bookOne);
        when(library.checkout(bookOne)).thenReturn(false);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, bookParser, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(Messages.UNSUCCESSFUL_CHECKOUT);
    }

    @Test
    public void invalidBookNameShouldProduceFailureMessage() {
        HashMap<Book, Boolean> bookList = new HashMap<Book, Boolean>();
        Book bookOne = new Book("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new Book("The Sky Is Falling", "Sidney Sheldon", "2001");
        bookList.put(bookOne, true);
        bookList.put(bookTwo, true);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runn");
        when(bookParser.parseUserInput("Kite Runn")).thenReturn(null);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, bookParser, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(Messages.UNSUCCESSFUL_CHECKOUT);
    }


}
