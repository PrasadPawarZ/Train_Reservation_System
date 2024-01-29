package OnlineReservationSystem;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisualForm extends JFrame implements ActionListener {

    JFrame frame;
    JPanel headingName, displayPanel;
    JLabel title, l1, l2, l3, l4, l5, l6;
    JButton b1, b2, b3, b4, b5, b6;
    JTextField t1, t2, t3, t4, t5, t6;
    JPasswordField passwordField;
    DefaultComboBoxModel<String> comboBoxModel1, comboBoxModel2, timeModel;
    JComboBox<String> trains1, trains2, timeSelection;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;
    Objecct obj;

    String currentUser, fromStation, toStation, time, price;
    Long prnNo, phoneNo;
    long prnNum;

    String finalName, finalSrcStation, finalDesStation, finalTime, finalPrice;
    Long finalPrnNo, finalPhoneNo;
    Boolean isTicked = false;

    String columns[] = { "" };
    String values[][] = { { "" } };
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    private static final String DATABASE_URL_Users = "jdbc:ucanaccess://D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/Users.accdb";
    private static final String DATABASE_URL_Trains = "jdbc:ucanaccess://D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/Train.accdb";
    private static final String INSERT_QUERY_Users = "INSERT INTO Users (Name, Username, Email, Password) VALUES (?, ?, ?, ?)";
    private static final String INSERT_QUERY_Ticket = "INSERT INTO Ticket (nameHolder, srcStation, desStation, Time, PrnNo, PhoneNo, Price) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY_Users = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
    private static final long serialVersionUID = 1L;

    private static final PropertyChangeSupport pcs = new PropertyChangeSupport(new Object());
    private static boolean userLoggedIn = false;

    VisualForm() {
        // Main frame
        frame = new JFrame("Online Reservation System");
        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Heading (ONLINE RESERVATION WITH BLACK BACKGROUND)
        headingName = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingName.setBackground(Color.BLACK);

        title = new JLabel("ONLINE    RESERVATION    SYSTEM");
        title.setFont(new Font("Courier", Font.PLAIN, 40));
        title.setForeground(Color.WHITE);

        headingName.add(title);
        // Heading Finished

        frame.add(headingName, BorderLayout.NORTH);

        displayPanel = new JPanel(null);
        displayPanel.setBackground(Color.DARK_GRAY);
        displayPanel.setPreferredSize(new Dimension(1080, 720));
        displayPanel.setVisible(true);

        frame.add(displayPanel, BorderLayout.CENTER);
        frame.setState(Frame.ICONIFIED);
        frame.setState(Frame.NORMAL);

        obj = new Objecct();

        this.initlizeAndAddElements();
    }

    public void createLoginPage() {

        b1.setText("Login");
        b2.setText("New User, Sign Up?");

        l1.setText("Enter Name");
        l2.setText("Enter Password");

        b1.setVisible(true);
        b2.setVisible(true);
        l1.setVisible(true);
        l2.setVisible(true);
        t1.setVisible(true);
        passwordField.setVisible(true);

        b1.setBounds(450, 350, 150, 50);
        b2.setBounds(450, 450, 150, 50);
        t1.setBounds(550, 100, 170, 35);
        passwordField.setBounds(550, 200, 170, 35);
        l1.setBounds(300, 100, 250, 35);
        l2.setBounds(300, 200, 250, 35);

    }

    private void initlizeAndAddElements() {

        passwordField = new JPasswordField();

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();

        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);

        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();
        l4 = new JLabel();
        l5 = new JLabel();
        l6 = new JLabel();

        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Courier", Font.PLAIN, 20));
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Courier", Font.PLAIN, 20));
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("Courier", Font.PLAIN, 20));
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("Courier", Font.PLAIN, 20));
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("Courier", Font.PLAIN, 20));
        l6.setForeground(Color.WHITE);
        l6.setFont(new Font("Courier", Font.PLAIN, 20));

        comboBoxModel1 = new DefaultComboBoxModel<>();
        comboBoxModel1.addElement("------------Select------------");
        comboBoxModel1.addElement("Mumbai");
        comboBoxModel1.addElement("Pune");
        comboBoxModel1.addElement("Nashik");
        comboBoxModel2 = new DefaultComboBoxModel<>();
        comboBoxModel2.addElement("------------Select------------");
        comboBoxModel2.addElement("Mumbai");
        comboBoxModel2.addElement("Pune");
        comboBoxModel2.addElement("Nashik");
        timeModel = new DefaultComboBoxModel<>();
        timeModel.addElement("----Select----");
        trains1 = new JComboBox<>(comboBoxModel1);
        trains2 = new JComboBox<>(comboBoxModel2);
        timeSelection = new JComboBox<>(timeModel);

        trains1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fromStation = trains1.getSelectedItem().toString();
            }
        });
        trains2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toStation = trains2.getSelectedItem().toString();
            }
        });
        timeSelection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time = timeSelection.getSelectedItem().toString();
            }
        });
        model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        displayPanel.add(scrollPane);

        displayPanel.add(passwordField);

        displayPanel.add(b1);
        displayPanel.add(b2);
        displayPanel.add(b3);
        displayPanel.add(b4);
        displayPanel.add(b5);
        displayPanel.add(b6);

        displayPanel.add(t1);
        displayPanel.add(t2);
        displayPanel.add(t3);
        displayPanel.add(t4);
        displayPanel.add(t5);
        displayPanel.add(t6);

        displayPanel.add(l1);
        displayPanel.add(l2);
        displayPanel.add(l3);
        displayPanel.add(l4);
        displayPanel.add(l5);
        displayPanel.add(l6);

        displayPanel.add(trains1);
        displayPanel.add(trains2);
        displayPanel.add(timeSelection);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        System.out.println(value);
        switch (value) {
            case "Login":
                if (!t1.getText().isEmpty()) {
                    if (checkLogin()) {
                        currentUser = t1.getText().toString();
                        setUserLoggedIn(true);
                        this.setInvisible();
                        this.Homepage();
                    } else if (t1.getText().equals("Admin") && t2.getText().equals("yes")) {
                        this.AdminPage();
                    }
                }
                break;

            case "New User, Sign Up?":
                this.setInvisible();
                this.signupPage();
                break;
            case "Sign Up":
                if (!signUpValidate()) {
                    System.out.println("Fail");
                    break;
                }
                this.addUser();
            case "Cancel return Login Page":
                this.setInvisible();
                this.createLoginPage();
                break;
            case "Search Train":
                this.setInvisible();
                this.searchTrainPage();
                break;
            case "Go":
                this.searchTrain(fromStation, toStation);
                break;
            case "Cancel":
                this.cancelReseerv();
                break;
            case "Make a Reservation":
                if (!isTicked) {
                    this.setInvisible();
                    this.makeReservationPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Already resserved!", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "View all Trains":
                this.setInvisible();
                this.allTrainsPage();
                break;
            case "< Back":
                // case "No dont cancel reservation":
                this.setInvisible();
                this.Homepage();
                break;
            case "get time":
                this.getTimeforReservation();
                l6.setText("Price = " + price);
                break;
            case "Logout":
                this.setInvisible();
                setUserLoggedIn(false);
                this.createLoginPage();
                break;
            case "Reserve":
                this.getTimeforReservation();
                finalName = t1.getText().toString();
                isTicked = true;
                finalSrcStation = fromStation;
                finalDesStation = toStation;
                finalTime = time;
                prnNum = generateUniquePRN();
                finalPrnNo = prnNum;
                finalPhoneNo = Long.valueOf(t2.getText().toString());
                finalPrice = price;
                this.saveTicketToDataBase();
                obj.setValues(finalName, finalSrcStation, finalDesStation, finalTime, finalPrnNo, finalPhoneNo,
                        finalPrice);
                saveVisualFormToFile(obj,
                        "D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/UsersData/User" + currentUser + ".ser");
                    break;
            case "Cancel Reservation":
                this.cancelReseerv();
                break;
            case "View Reserved Train Ticket":
                if (isTicked) {
                    this.setInvisible();
                    this.ticketPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Ticket not Booked yet.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Ticket not Booked yet");
                }
                break;
            default:
                break;
        }
    }

    private void cancelReseerv() {
        if (isTicked) {
            Object[] options = { "Yes", "No" };
            int result = JOptionPane.showOptionDialog(null, "Do really want to cancel?", "Cancel Reservation",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            // Handle the result of the option dialog
            if (result == JOptionPane.CLOSED_OPTION) {
                System.out.println("Dialog closed without selection.");
            } else {
                if (options[result].equals("Yes")) {
                    JOptionPane.showMessageDialog(null, "Ticket cancelled.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    isTicked = false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ticket not Booked yet.", "Information",
                            JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void saveTicketToDataBase() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL_Trains);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_Ticket);

            preparedStatement.setString(1, finalName);
            preparedStatement.setString(2, finalSrcStation);
            preparedStatement.setString(3, finalDesStation);
            preparedStatement.setString(4, finalTime);
            preparedStatement.setString(5, String.valueOf(finalPrnNo));
            preparedStatement.setString(6, String.valueOf(finalPhoneNo));
            preparedStatement.setString(7, finalPrice);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getTimeforReservation() {
        if (!fromStation.equals("") && !toStation.equals("")) {
            try {
                Connection connection = DriverManager.getConnection(DATABASE_URL_Trains);

                String query = "SELECT Price, Time1, Time2, Time3 " +
                        "FROM TrainTable " +
                        "WHERE SourceStation = ? AND DestinationStation = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, fromStation);
                    preparedStatement.setString(2, toStation);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                            if (resultSet.getMetaData().getColumnType(i) == Types.TIME ||
                                    resultSet.getMetaData().getColumnType(i) == Types.TIMESTAMP) {
                                Time timeValue = resultSet.getTime(i);
                                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                                timeModel.addElement(timeFormat.format(timeValue));
                            } else {
                                price = resultSet.getObject(i).toString();
                            }
                        }
                    }

                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ticketPage() {
        Painter p = new Painter();
        frame.add(p, BorderLayout.CENTER);
        p.setVisible(true);
        p.getValues(finalName, finalSrcStation, finalDesStation, finalTime, finalPrnNo, finalPhoneNo, finalPrice);
        Painter.addLoginChangeListener(evt -> {
            if ("userLoggedIn".equals(evt.getPropertyName()) && (boolean) evt.getNewValue()) {
                p.removeAll();
                p.repaint();
                p.revalidate();

                frame.remove(p);
                frame.repaint();
                frame.revalidate();

                this.Homepage();
            }
        });

    }

    private void makeReservationPage() {

        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        l4.setVisible(true);
        l5.setVisible(true);
        l6.setVisible(true);

        t1.setVisible(true);
        t2.setVisible(true);

        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);

        trains1.setVisible(true);
        trains2.setVisible(true);
        timeSelection.setVisible(true);

        b1.setText("Reserve");
        b2.setText("< Back");
        b3.setText("get time");

        l1.setText("Name");
        l2.setText("Phone No");
        l3.setText("From");
        l4.setText("To");
        l5.setText("Time");
        l6.setText("Price (Auto reveal after selection of train and time)");

        l1.setBounds(300, 100, 250, 35);
        l2.setBounds(300, 150, 250, 35);
        l3.setBounds(300, 200, 250, 35);
        l4.setBounds(300, 250, 250, 35);
        l5.setBounds(300, 300, 250, 35);
        l6.setBounds(300, 350, 550, 35);

        trains1.setBounds(500, 200, 150, 35);
        trains2.setBounds(500, 250, 150, 35);
        timeSelection.setBounds(625, 300, 125, 35);

        t1.setBounds(500, 100, 250, 35);
        t2.setBounds(500, 150, 250, 35);

        b1.setBounds(350, 450, 250, 50);
        b2.setBounds(20, 50, 250, 50);
        b3.setBounds(500, 300, 100, 35);

    }

    private void allTrainsPage() {
        b2.setVisible(true);
        b2.setText("< Back");

        b2.setBounds(20, 50, 250, 50);
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL_Trains);

            fetchTrainData(connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void searchTrain(String fromStation2, String toStation2) {
        if (!fromStation2.equals(toStation2)) {
            l3.setVisible(false);
            try {
                Connection connection = DriverManager.getConnection(DATABASE_URL_Trains);

                fetchTrainData(connection, fromStation2, toStation2);

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            l3.setText("Same Soucre and Destination");
            l3.setVisible(true);
            l3.setBounds(350, 375, 250, 50);
        }
    }

    private void searchTrainPage() {
        l1.setVisible(true);
        l2.setVisible(true);
        trains1.setVisible(true);
        trains2.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        l1.setText("From : ");
        l2.setText("To : ");
        b1.setText("Go");
        b2.setText("< Back");

        trains1.setModel(comboBoxModel1);
        trains2.setModel(comboBoxModel2);

        l1.setBounds(300, 150, 250, 50);
        trains1.setBounds(450, 150, 250, 50);
        l2.setBounds(300, 225, 250, 50);
        trains2.setBounds(450, 225, 250, 50);
        b1.setBounds(350, 300, 250, 50);
        b2.setBounds(20, 50, 250, 50);

    }

    private boolean checkLogin() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL_Users);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_Users);
            preparedStatement.setString(1, t1.getText());
            preparedStatement.setString(2, new String(passwordField.getPassword()));

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isValidLogin = resultSet.next();

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return isValidLogin;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addUser() {
        String name = t1.getText();
        String username = t2.getText();
        String email = t3.getText();
        String password = t4.getText();

        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL_Users);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_Users);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean signUpValidate() {
        if (!t1.getText().isEmpty()) {
            if (!t2.getText().isEmpty()) {
                if (!t3.getText().isEmpty() && emailValidate()) {
                    if (!t4.getText().isEmpty() && !t5.getText().isEmpty() && t4.getText().equals(t5.getText())) {
                        return true;
                    } else {
                        System.out.println("Password does not match");
                        return false;
                    }
                } else {
                    System.out.println("Email wrong");
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean emailValidate() {
        Matcher matcher = pattern.matcher(t3.getText());
        return matcher.matches();
    }

    private void setInvisible() {
        scrollPane.setVisible(false);
        passwordField.setVisible(false);
        passwordField.setText("");

        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);

        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);

        t1.setVisible(false);
        t2.setVisible(false);
        t3.setVisible(false);
        t4.setVisible(false);
        t5.setVisible(false);
        t6.setVisible(false);

        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");

        trains1.setVisible(false);
        trains2.setVisible(false);
        timeSelection.setVisible(false);
    }

    private void signupPage() {

        l1.setBounds(300, 50, 250, 35);
        l2.setBounds(300, 100, 250, 35);
        l3.setBounds(300, 150, 250, 35);
        l4.setBounds(300, 200, 250, 35);
        l5.setBounds(300, 250, 250, 35);

        t1.setBounds(500, 50, 250, 35);
        t2.setBounds(500, 100, 250, 35);
        t3.setBounds(500, 150, 250, 35);
        t4.setBounds(500, 200, 250, 35);
        t5.setBounds(500, 250, 250, 35);

        b1.setBounds(400, 350, 250, 50);
        b2.setBounds(400, 450, 250, 50);
        b1.setVisible(true);
        b2.setVisible(true);

        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        l4.setVisible(true);
        l5.setVisible(true);

        t1.setVisible(true);
        t2.setVisible(true);
        t3.setVisible(true);
        t4.setVisible(true);
        t5.setVisible(true);

        l1.setText("Enter Name : ");
        l2.setText("Enter Username : ");
        l3.setText("Enter Email : ");
        l4.setText("Enter Password : ");
        l5.setText("Comfirm Password : ");

        b1.setText("Sign Up");
        b2.setText("Cancel return Login Page");

    }

    private void AdminPage() {
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);
        b5.setVisible(true);

        b1.setText("Add Train");
        b2.setText("Delete Train");
        b3.setText("Update Train");
        b4.setText("Search Train");
        b5.setText("Logout");

        b1.setBounds(400, 100, 250, 50);
        b2.setBounds(400, 200, 250, 50);
        b3.setBounds(400, 300, 250, 50);
        b4.setBounds(400, 400, 250, 50);
        b5.setBounds(400, 500, 250, 50);
    }

    public void Homepage() {
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);
        b5.setVisible(true);
        b6.setVisible(true);

        b1.setText("Search Train");
        b2.setText("Make a Reservation");
        b3.setText("Cancel Reservation");
        b4.setText("View Reserved Train Ticket");
        b5.setText("View all Trains");
        b6.setText("Logout");

        b1.setBounds(400, 50, 250, 50);
        b2.setBounds(400, 125, 250, 50);
        b3.setBounds(400, 200, 250, 50);
        b4.setBounds(400, 275, 250, 50);
        b5.setBounds(400, 350, 250, 50);
        b6.setBounds(400, 425, 250, 50);
    }

    private void fetchTrainData(Connection connection, String sourceStation, String destinationStation)
            throws SQLException {

        model.setColumnCount(0);
        model.setRowCount(0);
        String query = "SELECT TrainID, TrainName, SourceStation, DestinationStation, Price, Time1, Time2, Time3 " +
                "FROM TrainTable " +
                "WHERE SourceStation = ? AND DestinationStation = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sourceStation);
            preparedStatement.setString(2, destinationStation);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                l3.setVisible(true);
                l3.setText("No trains found for the given route.");
                l3.setBounds(350, 375, 450, 50);
            } else {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    model.addColumn(resultSet.getMetaData().getColumnName(i));
                }
                while (resultSet.next()) {
                    Object[] rowData = new Object[resultSet.getMetaData().getColumnCount()];
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        if (resultSet.getMetaData().getColumnType(i) == Types.TIME ||
                                resultSet.getMetaData().getColumnType(i) == Types.TIMESTAMP) {
                            Time timeValue = resultSet.getTime(i);
                            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                            rowData[i - 1] = timeFormat.format(timeValue);
                        } else {
                            rowData[i - 1] = resultSet.getObject(i);
                        }
                    }
                    model.addRow(rowData);
                }

                scrollPane.setVisible(true);
                scrollPane.setBounds(150, 375, 650, 150);

            }
        }
    }

    private void fetchTrainData(Connection connection) throws SQLException {
        model.setRowCount(0);
        model.setColumnCount(0);
        String query = "SELECT * FROM TrainTable ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                model.addColumn(resultSet.getMetaData().getColumnName(i));
            }
            while (resultSet.next()) {
                Object[] rowData = new Object[resultSet.getMetaData().getColumnCount()];
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    if (resultSet.getMetaData().getColumnType(i) == Types.TIME ||
                            resultSet.getMetaData().getColumnType(i) == Types.TIMESTAMP) {
                        Time timeValue = resultSet.getTime(i);
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        rowData[i - 1] = timeFormat.format(timeValue);
                    } else {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                }
                model.addRow(rowData);
            }

            scrollPane.setVisible(true);
            scrollPane.setBounds(150, 150, 650, 350);

        }
    }

    public static void setUserLoggedIn(boolean loggedIn) {
        boolean oldValue = userLoggedIn;
        userLoggedIn = loggedIn;
        pcs.firePropertyChange("userLoggedIn", oldValue, userLoggedIn);
    }

    public static boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public static void addLoginChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public static void removeLoginChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public static void saveVisualFormToFile(Objecct visualForm, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(visualForm);
            System.out.println("VisualForm saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Objecct loadVisualFormFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

            Objecct visualForm = (Objecct) ois.readObject();
            System.out.println("VisualForm loaded from file: " + fileName);
            if (visualForm != null) {
                // Proceed with using the visualForm object
                // ...
                return visualForm;
            } else {
                // Handle the case when the object is null
                System.out.println("Loaded VisualForm object is null.");
                return null;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            System.out.println("Failed to load VisualForm from file: " + fileName);
        }

        // Return null if loading fails
        return null;
    }

    public void copyDataFrom(Objecct otherForm) {
        if (otherForm != null) {
            this.finalName = otherForm.finalName;
            this.finalSrcStation = otherForm.finalSrcStation;
            this.finalDesStation = otherForm.finalDesStation;
            this.finalTime = otherForm.finalTime;
            this.finalPrnNo = otherForm.finalPrnNo;
            this.finalPhoneNo = otherForm.finalPhoneNo;
            this.finalPrice = otherForm.finalPrice;
            this.isTicked = otherForm.isTicked;
            System.out.println("Done");
        } else {
            System.out.println("Attempted to copy data from null VisualForm.");
        }
    }

    private static long generateUniquePRN() {
        Set<Long> existingPRNs = readExistingPRNs();

        Random random = new Random();
        long newPRN;

        do {
            newPRN = (long) (random.nextDouble() * 900_000_000_000L) + 100_000_000_000L; // Generate a 12-digit PRN
        } while (existingPRNs.contains(newPRN));

        savePRNToFile(newPRN);

        return newPRN;
    }

    private static Set<Long> readExistingPRNs() {
        Set<Long> existingPRNs = new HashSet<>();

        try {
            if (Files.exists(Paths.get("D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/prn_no.txt"))) {
                Files.lines(Paths.get("D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/prn_no.txt"))
                        .map(String::trim)
                        .map(Long::parseLong)
                        .forEach(existingPRNs::add);
            }
        } catch (IOException e) {
            System.out.println("Heres problem");
            e.printStackTrace();
        }

        return existingPRNs;
    }

    private static void savePRNToFile(long newPRN) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("D:/GitHub/REpos/OIBSIP/OnlineReservationSystem/prn_no.txt", true))) {
            writer.write(Long.toString(newPRN));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
