package ApiRest.ApiRest.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import ApiRest.ApiRest.dto.ProductDto;
import ApiRest.ApiRest.model.ModelProduct;
import ApiRest.ApiRest.repository.CustomProductRepo;

/*La capa de servicio se hace cargo de la lógica en cada uno de los códigos http */

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private CustomProductRepo repository;

    //Funciona
    @Override
    public ResponseEntity<?> listOfAllObjects() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @Override
    public ResponseEntity<?> listOfObjectsById(List<String> productsId) {
        List<ModelProduct> verifyProducts = repository.findAllById(productsId);

        if (verifyProducts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(verifyProducts);
        }
    }
    
    // Funciona
    @Override
    public ResponseEntity<?> singleObject(String productId) {
        return repository.findById(productId) != null ? ResponseEntity.ok(repository.findById(productId)) : ResponseEntity.notFound().build();
    }
    
    //Funciona
    @Override
    public ResponseEntity<?> addObject(ModelProduct product){
        Random random = new Random();

        product.setId(Integer.toString(random.nextInt(1000) + 1));
        repository.save(product);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }


    // Funciona
    @Override
    public ResponseEntity<?> updateObject(String idProduct, ModelProduct product){
        
        ModelProduct verifyProduct = repository.findById(idProduct).orElse(null);

        if (verifyProduct != null) {

            verifyProduct.setName(product.getName());
            verifyProduct.setData(product.getData());

            repository.save(verifyProduct);
            
            return ResponseEntity.ok(createDto(verifyProduct, 1));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> partiallyUpdateObject(String idProduct, ModelProduct product){
        ModelProduct verifyProduct = repository.findById(idProduct).orElse(null);
        
        if (verifyProduct != null) {
            if (product.getName() != null) {
                verifyProduct.setName(product.getName());
            }
            if (product.getData() != null) {

                if (verifyProduct.getData() == null) {
                    Map<String,Object> data = new HashMap<>();
                    verifyProduct.setData(data);
                }

                for(Map.Entry<String,Object> entry : product.getData().entrySet()){
                    verifyProduct.getData().put(entry.getKey(), entry.getValue());
                }
            }

            repository.save(verifyProduct);
            return ResponseEntity.ok(createDto(verifyProduct, 1));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> deteleObject(String idProduct){
        ModelProduct verifyProduct = repository.findById(idProduct).orElse(null);

        if (verifyProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: Object with id = " + idProduct + ", is null or not found.");
        }else{
            repository.deleteById(idProduct);
            return ResponseEntity.ok("message: Object with id = " + idProduct + ", has been deleted.");     
        }
    }


    // Funciones que Operan.
    private ProductDto createDto(ModelProduct product, int option){
    
        LocalDateTime date = LocalDateTime.now();

        ProductDto dto = new ProductDto(
            product.getId(),    
            product.getName(),
            product.getData()
        );
        if (option > 1 || option <0) {
            throw new IllegalArgumentException("La opción tiene que ser 1 o 0 ");
        }
        if (option == 0) {
            dto.setCreatedAt(date);
        }else{
            dto.setupdatedAt(date);
        }
        return dto;
    }
}
