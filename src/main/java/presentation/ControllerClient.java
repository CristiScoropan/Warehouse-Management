package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerClient {
        private JFrame frame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField ageField;
    private ClientDAO clientDAO;

    public ControllerClient() {
        frame = new JFrame("Insert Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter ID:");
        idField = new JTextField(10);

        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField(10);

        JLabel emailLabel = new JLabel("Enter Email:");
        emailField = new JTextField(10);

        JLabel addressLabel = new JLabel("Enter Address:");
        addressField = new JTextField(10);

        JLabel ageLabel = new JLabel("Enter Age:");
        ageField = new JTextField(10);

        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the entered values from the text fields
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Client instance with the entered values
                Client client = new Client(id, name, email, address, age);

                // Insert the client into the database
                clientDAO = new ClientDAO();
                clientDAO.insert(client);

                // Clear the text fields
                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                addressField.setText("");
                ageField.setText("");
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(insertButton);

        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }
}
