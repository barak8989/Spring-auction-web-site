package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.model.AuctionUpdate;
import spring.model.SiteUser;
import spring.service.AuctionUpdateService;
import spring.service.UserService;



//This is a controller class to load mysells page
@Controller
public class ProfileController {
@Autowired
private AuctionUpdateService auctionUpdateService;

@Autowired
private UserService userService;


/**
 * Sets view "app.mysells" and adds to model information of user and his auctions
	 * @param modelAndView holder of model and view
 * @param auctionUpdate represents auction
 * @param pageNumber is page number to represent
 * @param user represents user
 * @return updated modelAndView
 */
@RequestMapping("/mysells")
ModelAndView showProfile(ModelAndView modelAndView,@ModelAttribute("auctionUpdate") AuctionUpdate auctionUpdate,@RequestParam(name = "p", defaultValue = "1") int pageNumber,SiteUser user) {
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	/* String username = auth.getName(); */
	Long id = userService.getUser().getId();
	Page<AuctionUpdate> page = auctionUpdateService.getPage(pageNumber);
	modelAndView.getModel().put("page", page);
	modelAndView.getModel().put("id", id);
	modelAndView.getModel().put("auctionUpdate", auctionUpdate);
	modelAndView.setViewName("app.mysells");
	
	return modelAndView;
}

}
