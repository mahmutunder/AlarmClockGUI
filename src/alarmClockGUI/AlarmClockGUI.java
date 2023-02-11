package alarmClockGUI;


import java.util.Calendar;

import javax.swing.*;


public class AlarmClockGUI {



    public static void main(String[] args) {
        // instantiate the frame and add  title
        JFrame frame = new JFrame("Alarm Clock");
        // frame size
        frame.setSize(300, 150);
        // to close the frame after click X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // instantiate panel
        JPanel panel = new JPanel();
        // add panel to the frame
        frame.add(panel);

        //build the panel in the method
        placeComponents(panel);

        frame.setVisible(true);
    }

    /**
     * this method build panel and
     * label, textField and button
     * add
     * @param panel to show information
     */
    private static void placeComponents(JPanel panel) {

        // panel layout
        panel.setLayout(null);

        // instantiate label to give info
        JLabel alarmLabel = new JLabel("Alarm Time 24hr (HH:MM)");
        // label position
        alarmLabel.setBounds(10, 10, 200, 25);
        // add label to panel
        panel.add(alarmLabel);


        // input text field
        JTextField alarmField = new JTextField(20);
        alarmField.setBounds(10, 40, 165, 25);
        panel.add(alarmField);

        // button to set alarm
        JButton setAlarmButton = new JButton("Set Alarm");
        setAlarmButton.setBounds(180, 40, 100, 25);
        panel.add(setAlarmButton);

        // label to show status of alarm
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(10, 70, 270, 25);
        panel.add(statusLabel);

        // set alarm with setButton
        setAlarmButton.addActionListener(e -> {
            // get text from textField
            String alarmTime = alarmField.getText();
            // string array to keep values
            String[] alarmTimeArray=null;
            // for hour
            int hour=0;
            // for minute
            int minute=0;
            try {
                // get text and split to array of string
                alarmTimeArray = alarmTime.split(":");
                            // convert  string to integer
                 hour = Integer.parseInt(alarmTimeArray[0]);
                 minute = Integer.parseInt(alarmTimeArray[1]);
                 // wild card for any exception
            }catch (RuntimeException ex){
                // show when exception occurs
                JOptionPane.showMessageDialog(null,"Enter EX: \"HH:MM\"");
            }



            // instantiate calendar object for now
            Calendar now = Calendar.getInstance();

            // instantiate calendar object for alarm
            Calendar alarm = Calendar.getInstance();
            alarm.set(Calendar.HOUR_OF_DAY, hour);
            alarm.set(Calendar.MINUTE, minute);
            alarm.set(Calendar.SECOND, 0);


            // check alarm status
            if (alarm.before(now)) {
                alarm.add(Calendar.DATE, 1);

            }

            // find the time of millisecond
            long timeUntilAlarm = alarm.getTimeInMillis() - now.getTimeInMillis();

            // set timer for schedule
            Timer timer = new Timer((int) timeUntilAlarm, e1 -> {
                statusLabel.setText("Wake Up!!!!!!!!!!!!");
                ((Timer) e1.getSource()).stop();
            });
            timer.start();

            statusLabel.setText("Alarm set for " + alarmTime);

        });
    }
}