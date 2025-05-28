package ApiRest.ApiRest.service;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

import ApiRest.ApiRest.model.ModelProduct;

public interface ProductService {
    // Definir la firma del metodo
    public ResponseEntity<?> listOfAllObjects();
    public ResponseEntity<?> listOfObjectsById(List<String> productsId);
    public ResponseEntity<?> singleObject(String productId);
    public ResponseEntity<?> addObject(ModelProduct product);
    public ResponseEntity<?> updateObject(String idProduct, ModelProduct product);
    public ResponseEntity<?> partiallyUpdateObject(String idProduct, ModelProduct product);
    public ResponseEntity<?> deteleObject(String idProduct);
}
