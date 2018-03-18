package ua.nure.silin.spring5recipeapp.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.silin.spring5recipeapp.domain.Recipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.String.format;

@Service
@Slf4j
public class DefaultImageService implements ImageService {

    private static final String DEFAULT_IMAGE_LOCATION = "src/main/resources/static/images/no-image.jpg";

    private RecipeService recipeService;

    public DefaultImageService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public void saveImage(Long recipeId, MultipartFile image) {
        try {
            Recipe recipe = recipeService.getRecipeById(recipeId);
            Byte[] imageBytesBoxed = ArrayUtils.toObject(image.getBytes());
            recipe.setImage(imageBytesBoxed);
            recipeService.save(recipe);
        } catch (IOException e) {
            throw new RuntimeException(format("Error saving image for recipe with id %d", recipeId));
        }
    }

    @Override
    public byte[] getImageByRecipeIdOrDefault(Long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        Byte[] imageBytesBoxed = recipe.getImage();
        if (imageBytesBoxed != null) {
            return ArrayUtils.toPrimitive(imageBytesBoxed);
        }
        else {
            return getDefaultImageBytes();
        }
    }

    private byte[] getDefaultImageBytes() {
        byte[] defaultImageBytes = null;

        try {
            Path path = Paths.get(DEFAULT_IMAGE_LOCATION);
            defaultImageBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            log.error("Error preparing default image from " + DEFAULT_IMAGE_LOCATION, e);
        }
        return defaultImageBytes;
    }
}
