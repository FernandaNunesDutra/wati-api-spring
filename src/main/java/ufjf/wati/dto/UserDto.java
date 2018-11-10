package ufjf.wati.dto;

public class UserDto {

    private long id;

    private String email;

    private String name;

    private String token;

    public UserDto(long id, String email, String name, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
