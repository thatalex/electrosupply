package by.alex.web.site.controller;

import by.alex.web.site.model.*;
import by.alex.web.site.service.RequestService;
import by.alex.web.site.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Properties;

@Controller
public class AdminController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value = {"/admin/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        addMessages(modelAndView, new HomeMessages());
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    private void addMessages(ModelAndView modelAndView, Messages messages) {
        Properties properties = Util.INSTANCE.getProperties();
        messages.read(properties);
        modelAndView.addObject("messages", messages);
    }

    private void saveMessages(Messages messages, RedirectAttributes redirectAttributes) {
        if (messages != null) {
            Properties properties = Util.INSTANCE.getProperties();
            messages.write(properties);
            Util.INSTANCE.storeProperties();
            redirectAttributes.addFlashAttribute("result", "Данные сохранены");
        }
    }


    @RequestMapping(value = {"/admin/home"}, method = RequestMethod.POST)
    public ModelAndView postHome(@Valid @ModelAttribute("messages") HomeMessages messages,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/home");
        saveMessages(messages, redirectAttributes);
        modelAndView.clear();
        modelAndView.setViewName("redirect:/admin/home");
        return modelAndView;
    }


    @RequestMapping(value = {"/admin/contacts"}, method = RequestMethod.GET)
    public ModelAndView getContacts() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        addMessages(modelAndView, new ContactsMessages());
        modelAndView.setViewName("admin/contacts");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/contacts"}, method = RequestMethod.POST)
    public ModelAndView postContacts(@Valid @ModelAttribute("messages") ContactsMessages messages,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/contacts");
        saveMessages(messages, redirectAttributes);
        modelAndView.clear();
        modelAndView.setViewName("redirect:/admin/contacts");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/services"}, method = RequestMethod.GET)
    public ModelAndView getServices() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.addObject("service", new Service());
        modelAndView.addObject("services", serviceService.findAll());
        modelAndView.setViewName("admin/services");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/services", params = "action", method = RequestMethod.POST)
    public ModelAndView postServices(@RequestParam(value = "action") String action,
                                     @RequestParam(value = "id", required = false) Integer id,
                                     @RequestParam(value = "file", required = false) MultipartFile file,
                                     @Valid @ModelAttribute("service") Service service,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/services");
        modelAndView.addObject("service", new Service());
        if (service != null && "save".equals(action)) {
            try {
                if (file != null && file.getSize() > 0){
                    service.setImage(file.getBytes());
                } else if (service.getId() != null && service.getId() > 0){
                    service.setImage(serviceService.getImage(service.getId()));
                }
                serviceService.save(service);
                modelAndView.addObject("result", "Сохранено");

            } catch (Exception e) {
                modelAndView.addObject("result", e.getMessage());
            }
            modelAndView.addObject("services", serviceService.findAll());
            return modelAndView;
        } else if (id != null && "delete".equals(action)) {
            try {
                serviceService.delete(id);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("result", e.getMessage());
            }
        }
        modelAndView.clear();
        modelAndView.setViewName("redirect:/admin/services");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/requests"}, method = RequestMethod.GET)
    public ModelAndView getRequests() {
        ModelAndView modelAndView = new ModelAndView();
        addHeaderText(modelAndView);
        modelAndView.addObject("request", new Request());
        modelAndView.addObject("requests", requestService.findAll());
        modelAndView.setViewName("admin/requests");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/requests", params = "action", method = RequestMethod.POST)
    public ModelAndView postRequests(@RequestParam(value = "action") String action,
                                     @RequestParam(value = "id", required = false) Integer id,
                                     @Valid @ModelAttribute("request") Request request,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/requests");
        modelAndView.addObject("request", new Request());
        if (request != null && "save".equals(action)) {
            try {
                requestService.save(request);
                modelAndView.addObject("result", "Сохранено");

            } catch (Exception e) {
                modelAndView.addObject("result", e.getMessage());
            }
            modelAndView.addObject("requests", requestService.findAll());
            return modelAndView;
        } else if (id != null && "delete".equals(action)) {
            try {
                requestService.delete(id);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("result", e.getMessage());
            }
        }
        modelAndView.clear();
        modelAndView.setViewName("redirect:/admin/requests");
        return modelAndView;
    }


    private void addHeaderText(ModelAndView modelAndView) {
        modelAndView.addObject("request", new Request());
    }
}
