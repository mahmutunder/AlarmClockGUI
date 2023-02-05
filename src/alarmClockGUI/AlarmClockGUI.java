package alarmClockGUI;


import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class AlarmClockGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Alarm Clock");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel alarmLabel = new JLabel("Alarm Time 24hr (HH:MM)");
        alarmLabel.setBounds(10, 10, 200, 25);
        panel.add(alarmLabel);

        JTextField alarmField = new JTextField(20);
        alarmField.setBounds(10, 40, 165, 25);
        panel.add(alarmField);

        JButton setAlarmButton = new JButton("Set Alarm");
        setAlarmButton.setBounds(180, 40, 100, 25);
        panel.add(setAlarmButton);

        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setBounds(10, 70, 270, 25);
        panel.add(statusLabel);

        // set alarm
        setAlarmButton.addActionListener(e -> {
            String alarmTime = alarmField.getText();
            String[] alarmTimeArray = alarmTime.split(":");
            int hour = Integer.parseInt(alarmTimeArray[0]);
            int minute = Integer.parseInt(alarmTimeArray[1]);

            Calendar now = Calendar.getInstance();
            Calendar alarm = Calendar.getInstance();
            alarm.set(Calendar.HOUR_OF_DAY, hour);
            alarm.set(Calendar.MINUTE, minute);
            alarm.set(Calendar.SECOND, 0);


            if (alarm.before(now)) {
                alarm.add(Calendar.DATE, 1);

            }

            long timeUntilAlarm = alarm.getTimeInMillis() - now.getTimeInMillis();

            // set timer

            Timer timer = new Timer((int) timeUntilAlarm, e1 -> {
                statusLabel.setText("Wake Up!!!!!!!!!!!!");
                ((Timer) e1.getSource()).stop();
            });
            timer.start();

            statusLabel.setText("Alarm set for " + alarmTime);

        });
    }
}