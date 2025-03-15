package com.labs.Assessment2.camera;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CameraService {

    private final CameraRepository cameraRepository;

    public CameraService(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    public Optional<Camera> getCameraById(Long id) {
        return cameraRepository.findById(id);
    }

    public Camera addCamera(Camera camera) {
        try {
            return cameraRepository.save(camera);
        } catch (Exception e) {
            throw new RuntimeException("Error saving camera: " + e.getMessage());
        }
    }


    public Camera updateCamera(Long id, Camera newCameraData) {
        return cameraRepository.findById(id)
                .map(camera -> {
                    camera.setLocation(newCameraData.getLocation());
                    camera.setStatus(newCameraData.getStatus());
                    return cameraRepository.save(camera);
                })
                .orElse(null);
    }

    public boolean removeCamera(Long id) {
        if (cameraRepository.existsById(id)) {
            cameraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
