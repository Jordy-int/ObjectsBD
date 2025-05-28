package ApiRest.ApiRest.controllers;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ApiRest.ApiRest.model.ModelProduct;
import ApiRest.ApiRest.repository.CustomProductRepo;
import ApiRest.ApiRest.service.ProductService;


/*  Este es el controlador, el cual se encarga de las respuesta http y de llamar al método
 * que corresponda con la llamada.
*/

@RestController
@RequestMapping("/objects")
public class ProductsController{

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?>listOfAllObjects(){
        return productService.listOfAllObjects();
    }

    @GetMapping
    @RequestMapping(params = "id")
    public ResponseEntity<?> listOfObjectsById(@RequestParam List<String> id){
        return productService.listOfObjectsById(id);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?>singleObject(@PathVariable String idProduct){
        return productService.singleObject(idProduct);
    }

    @PostMapping
    public ResponseEntity<?> addObject(@RequestBody ModelProduct product){
        return productService.addObject(product);
    }


    // // No actualiza
    @PutMapping("/{idProduct}")
    public ResponseEntity<?> updateObject(@PathVariable String idProduct, @RequestBody ModelProduct product){
        return productService.updateObject(idProduct, product);
    }

    @PatchMapping("/{idProduct}")
    public ResponseEntity<?> partiallyUpdateObject(@PathVariable String idProduct, @RequestBody ModelProduct product){
        return productService.partiallyUpdateObject(idProduct, product);
    }

    // // falta agregar el idProduct
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> deteleObject(@PathVariable String idProduct){
        return productService.deteleObject(idProduct);
    }
}
