package by.alex.web.site.controller;

import by.alex.web.site.model.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class FileUploadController {

    public static final String HOME_IMAGE_FILENAME = "." + File.separator + "home.image";

    @PostMapping("/admin/home_image")
    public String fileImportSubjects(@RequestParam("file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) {
        try(OutputStream out = new FileOutputStream(HOME_IMAGE_FILENAME)) {
            out.write(file.getBytes());
            out.flush();
            redirectAttributes.addFlashAttribute("result", "Файл сохранен");
            Util.INSTANCE.reloadMainImage();
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("result", e.toString());
        }
        return "redirect:/admin/home";
    }
}