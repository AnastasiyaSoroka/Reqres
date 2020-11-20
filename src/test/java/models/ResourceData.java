package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResourceData {
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer year;
    @Expose
    private String color;
    @SerializedName("pantone_value")
    @Expose
    private String pantoneValue;
}
