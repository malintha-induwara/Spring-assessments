package lk.ijse.gdse68.imagehandler.util;


import lk.ijse.gdse68.imagehandler.exception.ImageDeleteException;
import lk.ijse.gdse68.imagehandler.exception.InvalidImageTypeException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;


@Component
public class ImageUtil {

    public static Path IMAGE_DIRECTORY = Paths.get(System.getProperty("user.home"), "Desktop", "LocalS3Bucket", "upload").toAbsolutePath().normalize();
    public static Path DEFAULT_PROFILE_IMAGE = Paths.get(System.getProperty("user.home"), "Desktop", "LocalS3Bucket", "upload", "defaultProfile.jpg").toAbsolutePath().normalize();
    public static Path DEFAULT_BANNER_IMAGE = Paths.get(System.getProperty("user.home"), "Desktop", "LocalS3Bucket", "upload", "defaultBanner.jpg").toAbsolutePath().normalize();


    public ImageUtil() {
        if (!Files.exists(IMAGE_DIRECTORY)) {
            try {
                Files.createDirectories(IMAGE_DIRECTORY);
                System.out.println("Directory Created");
            } catch (IOException e) {
                System.out.println("Failed to Create directory " + e.getMessage());
                throw new RuntimeException(e);
            }
        }

        if (!Files.exists(DEFAULT_PROFILE_IMAGE)) {
            System.out.println("Default Profile Image Does Not Exists");
        }

        if (!Files.exists(DEFAULT_BANNER_IMAGE)) {
            System.out.println("Default Banner Image Does Not Exists");
        }
    }

    public static Resource getImage(String type, Long userId) {
        try {
            Optional<Path> resource = searchImage(type, userId);
            if (resource.isPresent()) {
                return new UrlResource(resource.get().toUri());
            }
            return new UrlResource(type.equals("profile") ? DEFAULT_PROFILE_IMAGE.toUri() : DEFAULT_BANNER_IMAGE.toUri());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteImage(String type, Long userId) {
        try {
            Optional<Path> resource = searchImage(type, userId);
            if (resource.isPresent()) {
                Files.delete(resource.get());
            }
        } catch (IOException e) {
            System.err.println("Error Deleting file: " + e.getMessage());
            throw new ImageDeleteException("An unexpected error occurred while deleting the image.", e);
        }
    }


    private static Optional<Path> searchImage(String type, Long userId) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(IMAGE_DIRECTORY, userId + "-" + type + ".*")) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    return Optional.of(entry);
                }
            }
        } catch (IOException e) {
            System.err.println("Error searching for file: " + e.getMessage());
        }
        return Optional.empty();
    }

    public static void saveImage(String type, Long userId, MultipartFile file) {
        try {

            // Check if the file is empty
            if (file==null || file.isEmpty()) {
                return;
            }

            if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith("jpg") &&
                    !Objects.requireNonNull(file.getOriginalFilename()).endsWith("png") &&
                    !Objects.requireNonNull(file.getOriginalFilename()).endsWith("jpeg")
            ) {
                throw new InvalidImageTypeException("Invalid file type. Only JPG and PNG files are allowed.");
            }

            Optional<Path> resource = searchImage(type, userId);
            if (resource.isPresent()) {
                Files.delete(resource.get());
            }

            Files.copy(file.getInputStream(), IMAGE_DIRECTORY.resolve(userId + "-" + type + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1]));
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

