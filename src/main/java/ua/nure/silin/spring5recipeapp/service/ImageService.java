package ua.nure.silin.spring5recipeapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImage(Long recipeId, MultipartFile image);
    byte[] getImageByRecipeIdOrDefault(Long recipeId);
}
