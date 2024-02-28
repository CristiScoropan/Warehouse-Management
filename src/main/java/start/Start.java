import bll.validators.ClientBLL;
import bll.validators.OrderBLL;
import bll.validators.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class Start {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Order Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JButton productButton = new JButton("Product");
		JButton clientButton = new JButton("Client");
		JButton orderButton = new JButton("Order");

		productButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productWindow("Product");
			}
		});

		clientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientWindow("Client");
			}
		});

		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderWindow("Order");
			}
		});

		panel.add(productButton);
		panel.add(clientButton);
		panel.add(orderButton);
		frame.add(panel);
		frame.setVisible(true);
	}


	private static void clientWindow(String windowTitle) {

		JFrame windowFrame = new JFrame(windowTitle);
		windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowFrame.setSize(300, 200);


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());


		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createClientWindow();
			}
		});
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editClientWindow();
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteClientWindow();
			}
		});
		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewClientWindow();
			}
		});

		buttonPanel.add(insertButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(viewButton);


		windowFrame.add(buttonPanel);


		windowFrame.setVisible(true);
	}

	private static void productWindow(String windowTitle) {

		JFrame windowFrame = new JFrame(windowTitle);
		windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowFrame.setSize(300, 200);


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());


		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createProductWindow();
			}
		});
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editProductWindow();
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteProductWindow();
			}
		});
		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewProductWindow();
			}
		});


		buttonPanel.add(insertButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(viewButton);


		windowFrame.add(buttonPanel);


		windowFrame.setVisible(true);
	}
	private static void orderWindow(String windowTitle) {

		JFrame windowFrame = new JFrame(windowTitle);
		windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowFrame.setSize(300, 200);


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());


		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createOrderWindow();
			}
		});
		JButton viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewOrderWindow();
			}
		});


		buttonPanel.add(insertButton);
		buttonPanel.add(viewButton);


		windowFrame.add(buttonPanel);


		windowFrame.setVisible(true);
	}
	private static void createClientWindow() {
		JFrame clientFrame = new JFrame("Client Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);

		JLabel nameLabel = new JLabel("Enter Name:");
		JTextField nameField = new JTextField(10);

		JLabel emailLabel = new JLabel("Enter Email:");
		JTextField emailField = new JTextField(10);

		JLabel addressLabel = new JLabel("Enter Address:");
		JTextField addressField = new JTextField(10);

		JLabel ageLabel = new JLabel("Enter Age:");
		JTextField ageField = new JTextField(10);

		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				String email = emailField.getText();
				String address = addressField.getText();
				int age = Integer.parseInt(ageField.getText());
				ClientBLL clientBLL = new ClientBLL();

				Client client = new Client(id, name, email, address, age);
				clientBLL.insertClient(client);


				idField.setText("");
				nameField.setText("");
				emailField.setText("");
				addressField.setText("");
				ageField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(nameLabel);
		clientPanel.add(nameField);
		clientPanel.add(emailLabel);
		clientPanel.add(emailField);
		clientPanel.add(addressLabel);
		clientPanel.add(addressField);
		clientPanel.add(ageLabel);
		clientPanel.add(ageField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void editClientWindow() {
		JFrame clientFrame = new JFrame("Edit Client Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);

		JLabel nameLabel = new JLabel("Enter Name:");
		JTextField nameField = new JTextField(10);

		JLabel emailLabel = new JLabel("Enter Email:");
		JTextField emailField = new JTextField(10);

		JLabel addressLabel = new JLabel("Enter Address:");
		JTextField addressField = new JTextField(10);

		JLabel ageLabel = new JLabel("Enter Age:");
		JTextField ageField = new JTextField(10);

		JButton insertButton = new JButton("Edit");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				String email = emailField.getText();
				String address = addressField.getText();
				int age = Integer.parseInt(ageField.getText());
				ClientBLL clientBLL = new ClientBLL();

				Client client = new Client(id, name, email, address, age);
				clientBLL.updateClient(client, id);


				idField.setText("");
				nameField.setText("");
				emailField.setText("");
				addressField.setText("");
				ageField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(nameLabel);
		clientPanel.add(nameField);
		clientPanel.add(emailLabel);
		clientPanel.add(emailField);
		clientPanel.add(addressLabel);
		clientPanel.add(addressField);
		clientPanel.add(ageLabel);
		clientPanel.add(ageField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void deleteClientWindow() {
		JFrame clientFrame = new JFrame("Delete Client Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);


		JButton insertButton = new JButton("Delete");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				ClientBLL clientBLL = new ClientBLL();
				clientBLL.deleteId(id);

				// Clear the text fields
				idField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void viewClientWindow() {
		ClientBLL clientBLL = new ClientBLL();
		createAndShowTable(clientBLL.findAll());
	}

	private static void createProductWindow() {
		JFrame clientFrame = new JFrame("Create Product Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);

		JLabel nameLabel = new JLabel("Enter Name:");
		JTextField nameField = new JTextField(10);

		JLabel numberLabel = new JLabel("Enter Number:");
		JTextField numberField = new JTextField(10);

		JLabel priceLabel = new JLabel("Enter Price:");
		JTextField priceField = new JTextField(10);

		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				int number = Integer.parseInt(numberField.getText());
				int price = Integer.parseInt(priceField.getText());
				ProductBLL productBLL = new ProductBLL();

				Product product = new Product(id, name, number, price);
				productBLL.insertProduct(product);

				idField.setText("");
				nameField.setText("");
				numberField.setText("");
				priceField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(nameLabel);
		clientPanel.add(nameField);
		clientPanel.add(numberLabel);
		clientPanel.add(numberField);
		clientPanel.add(priceLabel);
		clientPanel.add(priceField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void editProductWindow() {
		JFrame clientFrame = new JFrame("Edit Product Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);

		JLabel nameLabel = new JLabel("Enter Name:");
		JTextField nameField = new JTextField(10);

		JLabel numberLabel = new JLabel("Enter Number:");
		JTextField numberField = new JTextField(10);

		JLabel priceLabel = new JLabel("Enter Price:");
		JTextField priceField = new JTextField(10);

		JButton insertButton = new JButton("Edit");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				int number = Integer.parseInt(numberField.getText());
				int price = Integer.parseInt(priceField.getText());
				ProductBLL productBLL = new ProductBLL();

				Product product = new Product(id, name, number, price);
				productBLL.updateProduct(product, id);

				idField.setText("");
				nameField.setText("");
				numberField.setText("");
				priceField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(nameLabel);
		clientPanel.add(nameField);
		clientPanel.add(numberLabel);
		clientPanel.add(numberField);
		clientPanel.add(priceLabel);
		clientPanel.add(priceField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void deleteProductWindow() {
		JFrame clientFrame = new JFrame("Delete Product Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);

		JButton insertButton = new JButton("Delete");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				ProductBLL productBLL = new ProductBLL();
				productBLL.deleteById(id);


				idField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void viewProductWindow() { // de facut
		ProductBLL productBLL = new ProductBLL();
		createAndShowTable(productBLL.findAll());
	}

	private static void createOrderWindow() {
		JFrame clientFrame = new JFrame("Create Order Window");
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setSize(300, 200);


		JPanel clientPanel = new JPanel();
		clientPanel.setLayout(new FlowLayout());


		JLabel idLabel = new JLabel("Enter ID:");
		JTextField idField = new JTextField(10);

		JLabel productIdLabel = new JLabel("Enter Product ID:");
		JTextField productIdField = new JTextField(10);

		JLabel clientIdLabel = new JLabel("Enter Client ID:");
		JTextField clientIdField = new JTextField(10);

		JLabel quantityLabel = new JLabel("Enter Quantity:");
		JTextField quantityField = new JTextField(10);

		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(idField.getText());
				int clientId = Integer.parseInt(clientIdField.getText());
				int productId = Integer.parseInt(productIdField.getText());
				int quantity = Integer.parseInt(quantityField.getText());

				OrderBLL orderBLL = new OrderBLL();
				try{
					Order order = new Order(id, productId, clientId, quantity);
					orderBLL.insertOrder(order);
				} catch (IllegalArgumentException ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}


				idField.setText("");
				clientIdField.setText("");
				productIdField.setText("");
				quantityField.setText("");
			}
		});


		clientPanel.add(idLabel);
		clientPanel.add(idField);
		clientPanel.add(productIdLabel);
		clientPanel.add(productIdField);
		clientPanel.add(clientIdLabel);
		clientPanel.add(clientIdField);
		clientPanel.add(quantityLabel);
		clientPanel.add(quantityField);
		clientPanel.add(insertButton);


		clientFrame.add(clientPanel);


		clientFrame.setVisible(true);
	}

	private static void viewOrderWindow() { // de facut
		OrderBLL orderBLL = new OrderBLL();
		createAndShowTable(orderBLL.findAll());
	}

	private static void createAndShowTable(List<?> objects) {
		JFrame frame = new JFrame("Table Example");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Class<?> objectClass = objects.get(0).getClass();


		Field[] fields = objectClass.getDeclaredFields();


		Object[][] tableData = new Object[objects.size()][fields.length];


		for (int i = 0; i < objects.size(); i++) {
			Object object = objects.get(i);
			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				try {
					tableData[i][j] = fields[j].get(object);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}


		String[] columnNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			columnNames[i] = fields[i].getName();
		}

		JTable table = new JTable(new DefaultTableModel(tableData, columnNames));
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane);

		frame.pack();
		frame.setVisible(true);
	}

}
