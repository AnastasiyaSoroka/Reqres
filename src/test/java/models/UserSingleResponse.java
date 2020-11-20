package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserSingleResponse {
    @Expose
    private UserData data;
    @Expose
    private Support support;
    @Expose
    private Ad ad;
}
