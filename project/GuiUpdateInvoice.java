package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiUpdateInvoice extends JFrame {
    private JTextField IDField;
    private JTextField LastNameField;
    private JTextField AddressField;
    private JTextField itemField;
    private JTextField costField;
    private JButton updateButton;

    public GuiUpdateInvoice() {
        super("Update Invoice");
        setLayout(null);
        
        int labelX = 30;
        int fieldX = 130;
        int rowHeight = 30;
        int startY = 30;
        
        IDField = new JTextField();
        LastNameField = new JTextField();
        AddressField = new JTextField();
        itemField = new JTextField();
        costField = new JTextField();

        add(new JLabel("ID:")).setBounds(labelX, startY, 100, 20);
        add(IDField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Last Name:")).setBounds(labelX, startY, 100, 20);
        add(LastNameField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Address:")).setBounds(labelX, startY, 100, 20);
        add(AddressField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Item:")).setBounds(labelX, startY, 100, 20);
        add(itemField).setBounds(fieldX, startY, 200, 20);
        startY += rowHeight;

        add(new JLabel("Cost:")).setBounds(labelX, startY, 100, 20);
        add(costField).setBounds(fieldX, startY, 200, 20);
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
                String lastName = LastNameField.getText();
                String address = AddressField.getText();
                String item = itemField.getText();
                String cost = costField.getText();

                

                try {
                    UpdateInvoice.updateInvoice(ID, lastName, address, item, cost);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Customer information updated!");
            }
        }
    }
    public static void main(String[] args) {
        GuiUpdateInvoice Guiupdateinvoice = new GuiUpdateInvoice();
        Guiupdateinvoice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Guiupdateinvoice.setSize(400, 380);
        Guiupdateinvoice.setVisible(true);
    }
}