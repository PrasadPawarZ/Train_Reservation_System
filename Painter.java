package OnlineReservationSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JButton;
import javax.swing.JPanel;

class Painter extends JPanel implements ActionListener {
    JButton b1;
    private static final long serialVersionUID = 1L;
    private static final PropertyChangeSupport pcs = new PropertyChangeSupport(new Object());
    private static boolean userLoggedIn = false;

    String finalName, finalSrcStation, finalDesStation, finalTime, finalPrice;
    Long finalPrnNo, finalPhoneNo;


    Painter(){
        b1 = new JButton();
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        add(b1);
        b1.addActionListener(this);
        b1.setText("< Back");
        b1.setBounds(20, 50, 250, 50);
        setUserLoggedIn(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the main rectangle
        drawRectangleWithBorder(g, 200, 150, 650, 350);

        // Draw the smaller rectangles
        drawRectangleWithBorder(g, 200, 150, 275, 75);
        drawRectangleWithBorder(g, 550, 400, 275, 75);

        g.setFont(new Font("Courier", Font.PLAIN, 36));
        g.drawString("Ticket ", 475, 100);
        // Set font for text
        g.setFont(new Font("Courier", Font.PLAIN, 18));
        // Draw text
        g.drawString("Prn No " + finalPrnNo, 235, 190);
        g.drawString("Name " + finalName, 245, 270);
        g.drawString("Phone No  " + finalPhoneNo, 245, 310);
        g.drawString("From  " + finalSrcStation, 245, 350);
        g.drawString("To  " + finalDesStation, 405, 350);
        g.drawString("Time  " + finalTime, 245, 390);
        g.drawString("Price  " + finalPrice, 580, 440);

        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        setUserLoggedIn(true);
    }

    private void drawRectangleWithBorder(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.WHITE);

        for (int i = 0; i < 3; i++) {
            g.drawRect(x - i, y - i, width + 2 * i, height + 2 * i);
        }
    }

    public void getValues(String Name,String SrcStation,String DesStation,String Time,Long PrnNo,Long PhoneNo, String Price){
        finalName = Name;
        finalSrcStation = SrcStation;
        finalDesStation = DesStation;
        finalTime = Time;
        finalPrnNo = PrnNo;
        finalPhoneNo = PhoneNo;
        finalPrice = Price;
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

}
