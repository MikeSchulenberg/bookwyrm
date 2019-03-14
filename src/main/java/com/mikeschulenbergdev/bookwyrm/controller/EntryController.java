/*******************************************************************************
 * Copyright (C) 2019 Mike Schulenberg
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package com.mikeschulenbergdev.bookwyrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle requests for the Welcome, Register, and Login pages.
 * 
 * @author Mike Schulenberg
 * @version 0.0.1-SNAPSHOT
 *
 */
@Controller
public class EntryController {

	@RequestMapping("/")
	public String showWelcomePage() {
		return "/entry/welcome.html";
	}
	
	@RequestMapping("/register")
	public String showRegistrationPage() {
		return "/entry/register.html";
	}
	
	@RequestMapping("/login")
	public String showLoginPage() {
		return "/entry/login.html";
	}
	
	@RequestMapping("/home")
	public String showIndexPage() {
		return "/home.html";
	}
	
}
