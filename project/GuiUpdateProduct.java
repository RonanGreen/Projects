package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiUpdateProduct extends JFrame {
    private JTextField IDField;
    private JTextField DateField;
    private JTextField itemField;
    private JTextField costField;
    private JButton updateButton;

    public GuiUpdateProduct() {
        super("Update Product");
        setLayout(null);
        
        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;
        
        IDField = new JTextField();
        DateField = new JTextField();
        itemField = new JTextField();
        costField = new JTextField();
        
        add(new JLabel("ID:")).setBounds(labelX, startY, 100, 20);
        add(IDField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;
        
        add(new JLabel("Cost:")).setBounds(labelX, startY, 100, 20);
        add(costField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Item:")).setBounds(labelX, startY, 100, 20);
        add(itemField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;
   
        
        add(new JLabel("Date of Sale:")).setBounds(labelX, startY, 100, 20);
        add(DateField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        
        updateButton = new JButton("Update");
        updateButton.setBounds(180, 260, 100, 30);
        add(updateButton);

      ButtonHandler handler = new ButtonHandler();
        updateButton.addActionListener(handler);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == updateButton) {
                int ID = Integer.parseInt(IDField.getText());
                
                String cost = costField.getText();
                String item = itemField.getText();
                String Date = DateField.getText();
                

                try {
                    UpdateProduct.updateProduct(ID, cost, item, Date);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Customer information updated!");
            }
        }
    }
    public static void main(String[] args) {
        GuiUpdateProduct Guiupdateproduct = new GuiUpdateProduct();
        Guiupdateproduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Guiupdateproduct.setSize(400, 380);
        Guiupdateproduct.setVisible(true);
    }
}