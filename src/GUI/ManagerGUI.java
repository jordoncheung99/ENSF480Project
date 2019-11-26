package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ManagerGUI {
    private JPanel panel1;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel managerLabel;
    private JButton viewPeopleButton;
    private JButton requestSummaryButton;
    private JButton searchButton;
    private JButton changeFeeButton;
    private JFrame frame;

    private PrintWriter outBuffer;
    private BufferedReader inBuffer;

    public ManagerGUI(PrintWriter outBuffer, BufferedReader inBuffer) {
        JFrame frame = new JFrame("Manger");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        this.outBuffer = outBuffer;
        this.inBuffer = inBuffer;
        requestSummaryButton.addActionListener(new ALReuestSummary());
        viewPeopleButton.addActionListener(new ALViewPeople());
        searchButton.addActionListener(new ALSearch(outBuffer, inBuffer));
        frame.setVisible(true);
    }


    private class ALViewPeople implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO hook it up to the client;
            String send = "VIEWPEOPLE";
            outBuffer.println(send);
            String list = "";
            try {
                list = Client.readServer(inBuffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            new ManagerViewPeopleGUI(list);
        }
    }

    private class ALReuestSummary implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ManagerRequestSummary summary = new ManagerRequestSummary(outBuffer, inBuffer);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        titlePanel = new JPanel();
        titlePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        titlePanel.setBackground(new Color(-11038572));
        panel1.add(titlePanel, BorderLayout.NORTH);
        managerLabel = new JLabel();
        Font managerLabelFont = this.$$$getFont$$$("Bradley Hand ITC", Font.BOLD, 24, managerLabel.getFont());
        if (managerLabelFont != null) managerLabel.setFont(managerLabelFont);
        managerLabel.setForeground(new Color(-16579837));
        managerLabel.setText("Manager Page");
        titlePanel.add(managerLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        buttonPanel.setBackground(new Color(-11038572));
        panel1.add(buttonPanel, BorderLayout.CENTER);
        viewPeopleButton = new JButton();
        viewPeopleButton.setText("View Users");
        buttonPanel.add(viewPeopleButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        requestSummaryButton = new JButton();
        requestSummaryButton.setText("Request Summary");
        buttonPanel.add(requestSummaryButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        buttonPanel.add(searchButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changeFeeButton = new JButton();
        changeFeeButton.setText("Change Fee");
        buttonPanel.add(changeFeeButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
