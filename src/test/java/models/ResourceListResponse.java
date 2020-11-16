package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResourceListResponse {
    @Expose
    private Integer page;
    @Expose
    private Integer perPage;
    @Expose
    private Integer total;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @Expose
    private ArrayList<ResourceData> data;
    @Expose
    private Support support;
    @Expose
    private Ad ad;

}
