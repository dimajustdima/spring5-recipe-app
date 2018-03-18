package ua.nure.silin.spring5recipeapp.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.silin.spring5recipeapp.service.ImageService;
import ua.nure.silin.spring5recipeapp.service.RecipeService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Long.valueOf;

@Controller
@RequestMapping("/recipe/{id}/image")
public class ImageController {

    private ImageService imageService;
    private RecipeService recipeService;

    public ImageController(ImageService imageService,
                           RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("upload")
    public String showUploadImage(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeCommandById(valueOf(id)));
        return "/recipe/imageUploadForm";
    }

    @PostMapping
    public String uploadImage(@PathVariable String id,
                              @RequestParam MultipartFile imageFile) {
        imageService.saveImage(valueOf(id), imageFile);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping
    public void getImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        byte[] imageBytes = imageService.getImageByRecipeIdOrDefault(valueOf(id));
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        response.setContentType("image/jpeg");
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
