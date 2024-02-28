package bll.validators.otherValidators;

import model.Product;
import bll.validators.Validator;

public class ProductPrice implements Validator<Product>{
    private static final int MIN_PRICE = 1;
    private static final int MAX_PRICE = 10000;
    public void validate(Product t){
        if(t.getPrice() < MIN_PRICE || t.getPrice() > MAX_PRICE){
            throw new IllegalArgumentException("The Product price limit is not respected!");

        }
    }
}
