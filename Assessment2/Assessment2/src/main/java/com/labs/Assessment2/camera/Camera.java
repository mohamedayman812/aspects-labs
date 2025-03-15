package com.labs.Assessment2.camera;
import jakarta.persistence.*;

@Entity
@Table(name = "cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "status", nullable = false) // ACTIVE, INACTIVE, FAULTY
    private String status;

    public Camera() {}

    public Camera(String location, String status) {
        this.location = location;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "{ id: " + id + ", location: '" + location + "', status: '" + status + "' }";
    }
}
