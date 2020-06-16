// File: AuctionController.java
// AuctionController class contains methods relates to auctions
package spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.model.AuctionUpdate;
import spring.model.Category;
import spring.service.AuctionUpdateService;
import spring.service.CategoryService;
import spring.service.UserService;

//  This is a controller class to access or update auctions
@Controller
public class AuctionController {
	
	// Access to users
	@Autowired
	private UserService userService;
	
	// Access to auctions
	@Autowired
	private AuctionUpdateService auctionUpdateService;

	/**
	 * Sets view "app.addauction" and add to model categories and latestAuctionUpdate
	 * @param modelAndView holder of model and view
	 * @param auctionUpdate represents auction
	 * @return updated modelAndView
	 */
	@RequestMapping(value = "/addauction", method = RequestMethod.GET)
	ModelAndView addstatus(ModelAndView modelAndView, @ModelAttribute("auctionUpdate") AuctionUpdate auctionUpdate) {

		// Sets view
		modelAndView.setViewName("app.addauction");
		AuctionUpdate latestAuctionUpdate = auctionUpdateService.getLatests();
		List<Category> categories = CategoryService.getAllCategories();

		//Updates model
		modelAndView.getModel().put("categories", categories);
		modelAndView.getModel().put("latestAuctionUpdate", latestAuctionUpdate);
		return modelAndView;
	}

	/**
	 * Saves new auction. If everything goes right sets view to home.
	 * @param modelAndView holder of model and view
	 * @param auctionUpdate represents auction
	 * @return updated modelAndView
	 */
	@RequestMapping(value = "/addauction", method = RequestMethod.POST)
	ModelAndView addstatus(ModelAndView modelAndView, @Valid AuctionUpdate auctionUpdate, BindingResult result) {
		modelAndView.setViewName("app.addauction");
		// if ok
		 if(!result.hasErrors()) {
		 	// saves
			 auctionUpdateService.save(auctionUpdate);
			 //sets view to home
			 modelAndView.setViewName("redirect:/"); 
		  }
		 // Updates model with relevant auctions
		 AuctionUpdate latestAuctionUpdate = auctionUpdateService.getLatests();
		 modelAndView.getModel().put("latestAuctionUpdate", latestAuctionUpdate);

		return modelAndView;
	}


	/**
	 * Sets view "app.viewauction" and add to model categories and latestAuctionUpdate
	 * @param modelAndView holder of model and view
	 * @param id  represents id of auction
	 * @return updated modelAndView
	 */
	@RequestMapping(value = "/viewauction", method = RequestMethod.GET)
	ModelAndView viewAuction(ModelAndView modelAndView, @RequestParam(name="id") Long id ) {
		//Gets and puts information about auction
		AuctionUpdate auctionUpdate = auctionUpdateService.get(id);
		Long authId = userService.getUser().getId();
		modelAndView.getModel().put("authId", authId);
		modelAndView.getModel().put("auctionUpdate", auctionUpdate);
		// Sets next view
		modelAndView.setViewName("app.viewauction");
		return modelAndView;
	}

	/**
	 * Saves new bid. If everything goes right sets view to home.
	 * @param modelAndView holder of model and view
	 * @param auctionUpdate represents auction
	 * @return updated modelAndView
	 */
	@RequestMapping(value="/viewauction", method=RequestMethod.POST)
	ModelAndView viewAuction(ModelAndView modelAndView, AuctionUpdate auctionUpdate, BindingResult result) {
		if(!result.hasErrors()) {

			auctionUpdateService.updateBid(auctionUpdate);
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	
}
