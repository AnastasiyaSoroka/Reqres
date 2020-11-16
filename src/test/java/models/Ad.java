package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Ad {
    @Expose
    private String company;
    @Expose
    private String description;
    @Expose
    private String url;
}
