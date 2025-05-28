package ApiRest.ApiRest.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private String id;
    private String name;
    private Map<String, Object> data;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    public ProductDto(String id, String name, Map<String, Object> data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setupdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public Map<String, Object> getData() {
        return data;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getupdatedAt() {
        return updatedAt;
    }
    
}

