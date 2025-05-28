package ApiRest.ApiRest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ApiRest.ApiRest.model.ModelProduct;

// Capa de persistencia, se encarga del acceso de base de datos

/* Cabe aclarar que en este caso tengo un query personalizado */

public interface CustomProductRepo extends MongoRepository<ModelProduct, String> {

    public ModelProduct findByName(String name);
}