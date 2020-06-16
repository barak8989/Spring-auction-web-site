package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.model.AuctionUpdate;
import spring.model.Category;
import spring.service.AuctionUpdateService;
import spring.service.CategoryService;

//This is a controller class to load homepage
@Controller
public class PageController {

@Autowired
private AuctionUpdateService auctionUpdateService;

@Value("${message.error.forbidden}")
private String accessDeniedMessage;

@Value("${message.error.not.found}")
private String fileNotMessage;


/**
 * Sets view "app.homepage" and add to model categories and latestAuctionUpdate
 * @param modelAndView holder of model and view
 * @param auctionUpdate represents auction
 * @param pageNumber is page number to represent
 * @return updated modelAndView
 */
@RequestMapping("/")
ModelAndView home (ModelAndView modelAndView,@ModelAttribute("auctionUpdate") AuctionUpdate auctionUpdate ,@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
	 List<Category> categories = CategoryService.getAllCategories();
	 Page<AuctionUpdate> page = auctionUpdateService.getPage(pageNumber);
	 modelAndView.getModel().put("page", page);
	 modelAndView.getModel().put("categories", categories);
	 modelAndView.setViewName("app.homepage"); 
	 return modelAndView;
 }


/**
 * Sets model and view if HTTP 403 occurred
 * @param modelAndView holder of model and view
 * @return updated modelAndView
 */
@RequestMapping("/403")
ModelAndView accessDenied(ModelAndView modelAndView)
{
	return  requestFail(modelAndView, accessDeniedMessage);
}

/**
 * Sets model and view if HTTP 404 occurred
 * @param modelAndView holder of model and view
 * @return updated modelAndView
 */
@RequestMapping("/404")
ModelAndView notFound(ModelAndView modelAndView) {
	return  requestFail(modelAndView, fileNotMessage);
}

/**
 * Private method. Sets model and view if HTTP error occurred
 * @param modelAndView holder of model and view
 * @return updated modelAndView
 */
private ModelAndView requestFail(ModelAndView modelAndView, String msg){
	modelAndView.getModel().put("message", msg);
	modelAndView.setViewName("app.message");
	return modelAndView;
}
}
