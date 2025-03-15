package com.labs.Assessment2.aspects;
import com.labs.Assessment2.camera.Camera;
import com.labs.Assessment2.camera.CameraService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Aspect
@Component
class CameraLoggingAspect {

    private final CameraService cameraService;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CameraLoggingAspect(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    // Logs GET request for all cameras
    @Before("execution(* com.labs.Assessment2.camera.CameraController.getAllCameras(..))")
    public void logBeforeGet() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("[" + getTimestamp() + "] [GET] Retrieving all security cameras.");
    }

    // Logs GET request for a specific camera by ID
    @Before("execution(* com.labs.Assessment2.camera.CameraController.getCameraById(..)) && args(cameraId)")
    public void logBeforeGetById(Long cameraId) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("[" + getTimestamp() + "] [GET] Retrieving security camera with ID: " + cameraId);

        Optional<Camera> camera = cameraService.getCameraById(cameraId);
        if (camera.isPresent()) {
            System.out.println("Camera Details: " + formatCamera(camera.get()));
        } else {
            System.out.println("Camera not found.");
        }
    }

    // Logs POST request when adding a camera
    @Before("execution(* com.labs.Assessment2.camera.CameraController.addCamera(..)) && args(camera)")
    public void logBeforePost(Camera camera) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("[" + getTimestamp() + "] [POST] Adding a new security camera.");
        System.out.println("Camera Details: " + formatCamera(camera));
    }

    @AfterReturning(pointcut = "execution(* com.labs.Assessment2.camera.CameraController.addCamera(..))", returning = "savedCamera")
    public void logAfterPost(Camera savedCamera) {
        System.out.println("[" + getTimestamp() + "] [POST] Camera successfully added: " + formatCamera(savedCamera));
    }

    // Logs PUT request when updating a camera
    @Before(value = "execution(* com.labs.Assessment2.camera.CameraController.updateCamera(..)) && args(cameraId, updatedCamera)", argNames = "cameraId,updatedCamera")
    public void logBeforePut(Long cameraId, Camera updatedCamera) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("[" + getTimestamp() + "] [PUT] Updating security camera ID: " + cameraId);
        System.out.println("Updated Camera Data (Before Save): " + formatCamera(updatedCamera));
    }

    @AfterReturning(pointcut = "execution(* com.labs.Assessment2.camera.CameraController.updateCamera(..))", returning = "updatedCamera")
    public void logAfterPut(Camera updatedCamera) {
        if (updatedCamera != null) {
            System.out.println("[" + getTimestamp() + "] [PUT] Camera successfully updated: " + formatCamera(updatedCamera));
        } else {
            System.out.println("[" + getTimestamp() + "] [PUT] Camera update failed: Camera not found.");
        }
    }

    // Logs DELETE request when removing a camera
    @Before("execution(* com.labs.Assessment2.camera.CameraController.removeCamera(..)) && args(cameraId)")
    public void logBeforeDelete(Long cameraId) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("[" + getTimestamp() + "] [DELETE] Removing security camera ID: " + cameraId);
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(formatter);
    }

    private String formatCamera(Camera camera) {
        return "{ location: '" + camera.getLocation() + "', status: '" + camera.getStatus() + "' }";
    }
}
