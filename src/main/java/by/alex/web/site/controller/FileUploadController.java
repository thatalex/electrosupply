package by.alex.web.site.controller;

import by.alex.web.site.service.JSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

@Controller
public class FileUploadController {

    public static final String HOME_IMAGE_FILENAME = "/static/images/home.image";

    @PostMapping("/admin/home_image")
    public String fileImportSubjects(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) {
        String path = getClass().getResource(HOME_IMAGE_FILENAME).getFile();
        try(OutputStream out = new FileOutputStream(path)) {
            out.write(file.getBytes());
            out.flush();
            redirectAttributes.addFlashAttribute("result", "Файл сохранен");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("result", e.toString());
        }
        return "redirect:admin/home";
    }
}