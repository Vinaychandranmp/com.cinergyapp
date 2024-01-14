package com.cinergyapp.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cinergyapp.page.TicketBookingPages;

public class TicketBookingTest 
{	
	TicketBookingPages ticketBookingPage=new TicketBookingPages();
	@BeforeTest
	public void launch_url()
	{
		ticketBookingPage.initialize("chrome", "https://cinergyapp.thetunagroup.com/");
	}
	@Test
	public void movieTicketBooking()
	{
		ticketBookingPage.select_location();
	}
}

