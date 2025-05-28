package ApiRest.ApiRest.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

// La capa modelo es la "tabla" que estará en la BD

@JsonPropertyOrder({ "id", "name", "data" })
@Getter
@Setter
public class ModelProduct {
    @Id
    private String id;

    private String name;
    private Map<String, Object> data;

    /*sin el constructor habría que poner 
     * cada atributo por set
     */
    public ModelProduct(
    String name,
    Map<String,Object>data
    ){
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString(){

        return String.format("Customer[id='%s', name='%s', data='%s']",
        id, name, data);
        
    }

}
