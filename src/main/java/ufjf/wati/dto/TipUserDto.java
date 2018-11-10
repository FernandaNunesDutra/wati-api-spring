package ufjf.wati.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TipUserDto {

    @JsonProperty("id_tip")
    private long tipId;

    @JsonProperty("id_user")
    private long userId;

    @JsonProperty("like")
    private boolean like;

    public TipUserDto() {
    }

    public TipUserDto(long tipId, long userId) {
        this.tipId = tipId;
        this.userId = userId;
    }

    public long getTipId() {
        return tipId;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isLike() {
        return like;
    }
}
