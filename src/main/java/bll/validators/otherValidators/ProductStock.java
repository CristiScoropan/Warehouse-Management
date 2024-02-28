package bll.validators.otherValidators;
import bll.validators.Validator;
import model.Product;
public class ProductStock implements Validator<Product> {
    private static final int MIN_STOCK = 1;
    private static final int MAX_STOCK = 10000;
    public void validate(Product t){
        if(t.getNumber()<MIN_STOCK || t.getNumber()>MAX_STOCK){
            throw new IllegalArgumentException("The Product stock limit is not respected!");

        }
    }
}
