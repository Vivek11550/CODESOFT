import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMApp extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JLabel messageLabel;
    private JButton withdrawButton, depositButton, balanceButton;

    public ATMApp() {
        account = new BankAccount(100.0);

        // Set up the main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();

        // Amount Label
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(amountLabel, gbc);

        // Amount Field
        amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        // Withdraw Button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 12));
        withdrawButton.setBackground(new Color(0, 123, 255));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(withdrawButton, gbc);

        // Deposit Button
        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 12));
        depositButton.setBackground(new Color(40, 167, 69));
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(this);
        gbc.gridx = 1;
        panel.add(depositButton, gbc);

        // Balance Button
        balanceButton = new JButton("Check Balance");
        balanceButton.setFont(new Font("Arial", Font.BOLD, 12));
        balanceButton.setBackground(new Color(255, 193, 7));
        balanceButton.setForeground(Color.WHITE);
        balanceButton.addActionListener(this);
        gbc.gridx = 2;
        panel.add(balanceButton, gbc);

        // Message Label
        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        messageLabel.setForeground(new Color(220, 53, 69));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, gbc);

        // Set up the frame
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("ATM App");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = 0;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid amount.");
            return;
        }

        if (e.getSource() == withdrawButton) {
            if (account.withdraw(amount)) {
                showMessage(String.format("Withdrew $%.2f. New balance: $%.2f", amount, account.getBalance()));
            } else {
                showMessage("Insufficient balance or invalid amount.");
            }
        } else if (e.getSource() == depositButton) {
            if (account.deposit(amount)) {
                showMessage(String.format("Deposited $%.2f. New balance: $%.2f", amount, account.getBalance()));
            } else {
                showMessage("Invalid deposit amount.");
            }
        } else if (e.getSource() == balanceButton) {
            showMessage(String.format("Your current balance is: $%.2f", account.getBalance()));
        }
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMApp::new);
    }
}
