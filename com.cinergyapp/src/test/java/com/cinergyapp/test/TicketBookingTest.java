package com.cinergyapp.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cinergyapp.page.TicketBookingPages;

public class TicketBookingTest {

    WebDriver driver;
    TicketBookingPages ticketBookingPage;

    @BeforeTest
    public void launch_url() {
        ticketBookingPage = new TicketBookingPages(driver);
        ticketBookingPage.initialize("chrome", "https://cinergyapp.thetunagroup.com/");
    }

    @Test
    public void movieTicketBooking() throws InterruptedException {
        ticketBookingPage.select_location();
        ticketBookingPage.movies_menu_selection();
        ticketBookingPage.movie_selection();
        ticketBookingPage.opt_showtime();
        ticketBookingPage.seat_selection();
        ticketBookingPage.apply_voucher_code();
        ticketBookingPage.select_senior_citizen();
        ticketBookingPage.continue_booking();
        ticketBookingPage.billing_details();
        ticketBookingPage.returnhome();
    }
    @AfterTest
    public void Close_browser()
    {
    	ticketBookingPage.tearDown();
    }
}