package bll.validators;

import model.Product;
import dao.ProductDAO;
import bll.validators.otherValidators.ProductPrice;
import bll.validators.otherValidators.ProductStock;
import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    public ProductBLL(){
        productDAO = new ProductDAO();
        validators = new ArrayList<>();
        validators.add(new ProductPrice());
        validators.add(new ProductStock());
    }
    public Product findById(int id){
        return productDAO.findById(id);
    }
    public List<Product> findAll(){
        return productDAO.findAll();
    }
    public Product findByName(String name){
        return productDAO.findByName(name);
    }

    public Product insertProduct(Product product){
        try{
            for(Validator<Product> validator: validators){
                validator.validate(product);
            }
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return productDAO.insert(product);
    }

    public Product updateProduct(Product product, int id){
        if(findById(id) == null){
            return null;
        }
        return productDAO.update(product, id);
    }

    public  boolean deleteById(int id){
        if(findById(id)==null){
            return false;
        }
        return productDAO.deleteById(id);
    }
}
