package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterPostResponse {
    @Expose
    private int id;
    @Expose
    private String token;
}
