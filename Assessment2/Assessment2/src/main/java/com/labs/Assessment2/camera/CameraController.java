package com.labs.Assessment2.camera;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @PostMapping
    public ResponseEntity<Camera> addCamera(@RequestBody Camera camera) {
        Camera savedCamera = cameraService.addCamera(camera);
        return ResponseEntity.ok(savedCamera);
    }

    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        return ResponseEntity.ok(cameraService.getAllCameras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCameraById(@PathVariable Long id) {
        return cameraService.getCameraById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Camera not found."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCamera(@PathVariable Long id, @RequestBody Camera updatedCamera) {
        Camera updated = cameraService.updateCamera(id, updatedCamera);
        return (updated != null)
                ? ResponseEntity.ok(updated)
                : ResponseEntity.status(404).body("Camera not found.");
    }

    @DeleteMapping("/{id}")
    public boolean removeCamera(@PathVariable Long id) {
        return cameraService.removeCamera(id);
    }
}
