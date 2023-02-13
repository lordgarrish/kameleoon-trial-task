package lordgarrish.kameleoontrialtask.dto;

import lordgarrish.kameleoontrialtask.entity.User;

import java.time.Instant;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Instant createdAt;

    private UserDTO(Long id, String name, String email, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public static UserDTO toDto(User entity) {
        return new UserDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
