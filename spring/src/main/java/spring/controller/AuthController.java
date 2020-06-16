// File: AuthController.java
// AuthController class allows to user add himself and login
package spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.model.SiteUser;
import spring.service.UserService;

//  This is a controller class to add user to system and perform login.
@Controller
public class AuthController {

	// Access to users
	@Autowired
	private UserService userService;


	@RequestMapping("/login")
	String admin() {
		return "app.login";
	}

	/**
	 * Sets view "app.register" and adds user to model
	 * @param modelAndView holder of model and view
	 * @return updated modelAndView
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView) {

		SiteUser user = new SiteUser();

		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("app.register");
		return modelAndView;
	}


	/**
	 * Saves new user. If everything goes right sets view to success page.
	 * @param modelAndView holder of model and view
	 * @param user represents user
	 * @return updated modelAndView
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	ModelAndView register(ModelAndView modelAndView, @ModelAttribute(value="user") @Valid SiteUser user, BindingResult result) {
		modelAndView.setViewName("app.register");
		if(!result.hasErrors()) {
			//registers new user
			userService.register(user);
			modelAndView.setViewName("app.success");
		}
		return modelAndView;
	}
}
























