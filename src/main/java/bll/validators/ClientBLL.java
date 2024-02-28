package bll.validators;

import bll.validators.otherValidators.ClientAge;
import bll.validators.otherValidators.EmailValidator;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBLL {
   private List<Validator<Client>> validators;
   private ClientDAO clientDAO;
   public ClientBLL(){
       this.clientDAO = new ClientDAO();
       validators = new ArrayList<>();
       validators.add(new ClientAge());
       validators.add(new EmailValidator());
   }
   public Client findById(int id){
       return clientDAO.findById(id);
   }
   public List<Client> findAll(){
       return clientDAO.findAll();
   }
   public Client findByName(String name){
       return clientDAO.findByName(name);
   }

   public Client insertClient(Client client){
       try{
           for(Validator<Client> validator: validators){
               validator.validate(client);
           }
       } catch (IllegalArgumentException e){
           throw new IllegalArgumentException(e.getMessage());
       }
       return clientDAO.insert(client);
   }

   public Client updateClient(Client client, int id){
       try{
           for(Validator<Client> validator: validators){
               validator.validate(client);
           }
       } catch (IllegalArgumentException e){
           throw new IllegalArgumentException(e.getMessage());
       }
       if(findById(id)== null){
           return null;
       }
       return clientDAO.update(client,id);
   }

   public boolean deleteId(int id){
       if(findById(id)==null){
           return false;
       }
       return clientDAO.deleteById(id);
   }
}
