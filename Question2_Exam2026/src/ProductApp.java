import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {
    private JTextField txtCustomerName;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    public ProductApp() {
        setTitle("Product App");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> clearForm());

        editMenu.add(processItem);
        editMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Customer Name:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtCustomerName = new JTextField(15);
        panel.add(txtCustomerName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Select Product:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProduct = new JComboBox<>(products);
        panel.add(cmbProduct, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Enter Price:"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        txtPrice = new JTextField(15);
        panel.add(txtPrice, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnProcess = new JButton("PROCESS");
        btnProcess.setPreferredSize(new Dimension(180, 28));
        btnProcess.addActionListener(e -> processSale());
        panel.add(btnProcess, gbc);

        add(panel);
    }

    private void processSale() {
        String customerName = txtCustomerName.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();
        String priceText = txtPrice.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            ProductCalculations calc = new ProductCalculations(price);

            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            String message = "Customer Name: " + customerName + "\n"
                    + "Product: " + product + "\n"
                    + "VAT: R " + vat + "\n"
                    + "COMMISSION: R " + commission + "\n"
                    + "TOTAL: R " + total;

            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            saveToFile(customerName, product, vat, commission, total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(String customerName, String product, double vat, double commission, double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
            writer.println("DATA LOG");
            writer.println("************************************");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + product);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("************************************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving to data.txt: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

}
