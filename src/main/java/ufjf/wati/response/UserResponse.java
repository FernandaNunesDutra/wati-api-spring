package ufjf.wati.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("id")
    private long id;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("token")
    private String token;

    public UserResponse(long id, String email, String name, String token) {
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
