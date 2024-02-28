package bll.validators;

import model.Client;
import model.Order;
import dao.OrderDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
public class OrderBLL {
    private OrderDAO orderDAO;
    private ProductBLL productBLL;
    private ClientBLL clientBLL;
    public OrderBLL(){
        orderDAO = new OrderDAO();
        productBLL = new ProductBLL();
        clientBLL = new ClientBLL();
    }
    public Order findById(int id){
        return orderDAO.findById(id);
    }
    public List<Order> findAll(){
        return orderDAO.findAll();
    }

    public Order insertOrder(Order order){
        Client client = clientBLL.findById(order.getClientId());
        Product product = productBLL.findById(order.getProductId());

        if(client == null){
            throw new IllegalArgumentException("This client doesn't exist");
        }
        if(product==null){
            throw new IllegalArgumentException("This product doesn't exist");
        }
        if(product.getNumber() - order.getQuantity() <0){
            throw new IllegalArgumentException("Not enough stock");
        }
        Order inserted;
        try{
            inserted = orderDAO.insert(order);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        product.setNumber(product.getNumber()-order.getQuantity());
        if(product.getNumber()!=0){
            productBLL.updateProduct(product, product.getId());
        } else {
            productBLL.deleteById(product.getId());
        }
        return inserted;
    }
}
