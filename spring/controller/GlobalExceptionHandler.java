package spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//This is a controller class  to handle exceptions
@ControllerAdvice
public class GlobalExceptionHandler {

@Value("${message.error.exception}")
private String exceptionMessage;

@Value("${message.error.duplicate.user}")
private String duplicateUserMessage;


/**
 * Default handler for any type of exceptions
 * @param req is request information for HTTP servlets.
 * @param e represents exception
 * @return updated modelAndView
 */
@ExceptionHandler(value=Exception.class)
public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
	return  handler(exceptionMessage, req, e);
}


/**
 * Handler for DataIntegrityViolationException
 * @param req is request information for HTTP servlets.
 * @param e represents exception
 * @return updated modelAndView
 */
@ExceptionHandler(value=DataIntegrityViolationException.class)
public ModelAndView duplicateUserHandler(HttpServletRequest req, Exception e) {

	return  handler(duplicateUserMessage, req, e);

}

/**
 * Private handler for any type of exceptions
 * @param msg is message to put to model
 * @param req is request information for HTTP servlets.
 * @param e represents exception
 * @return updated modelAndView
 */
private ModelAndView handler(String msg, HttpServletRequest req, Exception e){
	ModelAndView modelAndView = new ModelAndView();

	modelAndView.getModel().put("message", msg);

	modelAndView.getModel().put("url", req.getRequestURL());

	modelAndView.getModel().put("exception", e);

	modelAndView.setViewName("app.exception");

	return modelAndView;
}
}
