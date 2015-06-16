package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.LinkedHashSet;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class CheckoutOptionTest {
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
        LinkedHashSet<Book> books = new LinkedHashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        LinkedHashSet<Movie> movies = new LinkedHashSet<Movie>();
        Movie movieOne = new Movie("Inception", "2010", "Christopher Nolan", "9.0");
        Movie movieTwo = new Movie( "Following", "1998", "Christopher Nolan", "8");
        movies.add(movieOne);
        movies.add(movieTwo);
        Library library = new Library(books, movies);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).getUserInput();
    }


    @Test
    public void successfulCheckoutShouldProduceSuccessMessage() {
        LinkedHashSet<Book> books = new LinkedHashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");

        when(library.checkoutBook("Kite Runner")).thenReturn(Messages.SUCCESSFUL_CHECKOUT);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(Messages.SUCCESSFUL_CHECKOUT);
    }

    @Test
    public void unsuccessfulCheckoutShouldProduceFailureMessage() {
        LinkedHashSet<Book> books = new LinkedHashSet<Book>();
        Book bookOne = new AvailableBook("Kite Runner", "Khaled Hosseini", "2003");
        Book bookTwo = new AvailableBook("The Sky Is Falling", "Sidney Sheldon", "2001");
        books.add(bookOne);
        books.add(bookTwo);
        when(consoleInputOutput.getUserInput()).thenReturn("Kite Runner");
        when(library.checkoutBook("Kite Runner")).thenReturn(Messages.UNSUCCESSFUL_CHECKOUT);
        CheckoutOption checkoutOption = new CheckoutOption(consoleInputOutput, library);

        checkoutOption.obtainOptionResult();

        verify(consoleInputOutput).displayOutputToUser(Messages.UNSUCCESSFUL_CHECKOUT);
    }


}
