package by.alex.web.site.controller;

import by.alex.web.site.model.Request;
import by.alex.web.site.model.Review;
import by.alex.web.site.model.Service;
import by.alex.web.site.service.RequestService;
import by.alex.web.site.service.ReviewService;
import by.alex.web.site.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping(value = {"/home"}, method = RequestMethod.POST)
    public ModelAndView postHome(@Valid @ModelAttribute("request") Request request,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("home");
        if (request != null) {
            request = requestService.save(request);
            if (request != null) {
                redirectAttributes.addFlashAttribute("result", "Заявка отправлена");
            }
        }
        modelAndView.clear();
        modelAndView.setViewName("redirect:home");
        return modelAndView;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView getAbout() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public ModelAndView getContacts() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.setViewName("contacts");
        return modelAndView;
    }

    @RequestMapping(value = {"/contacts"}, method = RequestMethod.POST)
    public ModelAndView postContacts(@Valid @ModelAttribute("request") Request request,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("contacts");
        if (request != null) {
            request = requestService.save(request);
            if (request != null) {
                redirectAttributes.addFlashAttribute("result", "Заявка отправлена");
            }
        }
        modelAndView.clear();
        modelAndView.setViewName("redirect:contacts");
        return modelAndView;
    }

    @RequestMapping(value = {"/reviews"}, method = RequestMethod.GET)
    public ModelAndView getReviews() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reviews", reviewService.findAll());
        modelAndView.addObject("review", new Review());
        modelAndView.setViewName("reviews");
        return modelAndView;
    }

    @RequestMapping(value = {"/reviews"}, method = RequestMethod.POST)
    public ModelAndView postReviews(@Valid @ModelAttribute("review") Review review,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("/reviews");
        if (review != null) {
            review = reviewService.save(review);
            if (review != null) {
                redirectAttributes.addFlashAttribute("result", "Отзыв отправлен");
            }
        }
        modelAndView.clear();
        modelAndView.setViewName("redirect:reviews");
        return modelAndView;
    }

    @RequestMapping(value = {"/services"}, method = RequestMethod.GET)
    public ModelAndView getServices() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("services", serviceService.findAll());
        modelAndView.setViewName("services");
        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView getError() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.setViewName("error/403");
        return modelAndView;
    }

    private void addHeaderText(ModelAndView modelAndView) {
        modelAndView.addObject("request", new Request());
        modelAndView.addObject("home_image", "images/home.png");
    }

}
