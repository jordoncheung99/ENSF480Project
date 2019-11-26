package GUI;

import Server.Criteria;
//import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CriteriaGUI {
    private JLabel furnishedEnableLabel;
    private JLabel furnishedLabel;
    private JLabel numberOfBathRoomsLabel;
    private JLabel numberOfBedRoomsLabel;
    private JLabel typeOfPropertyLabel;
    private JLabel areaLabel;
    private JLabel rentTermLabel;
    private JLabel rentAmountLabel;
    private JLabel cityQuadrantLabel;
    private JLabel propertyIDLabel;
    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel panel1;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JButton registerButton;
    private JButton backButton;
    private JTextField furnishedEnableField;
    private JTextField furnishedField;
    private JTextField numBathRoomsField;
    private JTextField numBedRoomsField;
    private JTextField typePropertyField;
    private JTextField areaField;
    private JTextField rentTermField;
    private JTextField rentAmountField;
    private JTextField cityQuadrantField;
    private JTextField propertyIDField;
    JFrame frame;

    public CriteriaGUI(ActionListener registerAL) {
        frame = new JFrame("Hell");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        registerButton.addActionListener(registerAL);
        backButton.addActionListener(new ALBack());
        frame.setVisible(true);
    }

    public Criteria pullCriteria() {
        boolean worked = true;

        boolean furnishedEnable;
        try{
            furnishedEnable = Boolean.parseBoolean(furnishedEnableField.getText());
        }catch(NumberFormatException e){
            System.out.println("Furnished Enable is not a boolean");
            worked = false;
        }
        String furnished = furnishedField.getText();
        String numBathRooms = numBathRoomsField.getText();
        String numBedRooms = numBedRoomsField.getText();
        String typeProperty = typePropertyField.getText();
        String area = areaField.getText();
        String rentTerm = rentTermField.getText();
        String rentAmount = rentAmountField.getText();
        String cityQuadrant = cityQuadrantField.getText();
        String propertyID = propertyIDField.getText();

        String[] propertyIDArray = propertyID.split(" ");
        int[] propertyIDIntegers = new int[propertyIDArray.length];

        try{
            for (int i = 0; i < propertyIDIntegers.length; i++) {
                propertyIDIntegers[i] = Integer.parseInt(propertyIDArray[i]);
            }
        }catch(NumberFormatException e){
            System.out.println("Property ID are not ints");
            worked = false;
        }

        if(worked){
            Criteria criteria = new Criteria(Boolean.parseBoolean(furnishedEnable), Boolean.parseBoolean(furnished),
                    Integer.parseInt(numBathRooms), Integer.parseInt(numBedRooms), typeProperty.split(" "), Float.parseFloat(area),
                    Float.parseFloat(rentTerm), Float.parseFloat(rentAmount), cityQuadrant.split(" "), propertyIDIntegers);
            frame.dispose();

            return criteria;
        }
        return null;
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
        panel1.setBackground(new Color(-11038572));
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout(0, 0));
        titlePanel.setBackground(new Color(-11038572));
        panel1.add(titlePanel, BorderLayout.NORTH);
        titleLabel = new JLabel();
        Font titleLabelFont = this.$$$getFont$$$("Bradley Hand ITC", Font.BOLD, 24, titleLabel.getFont());
        if (titleLabelFont != null) titleLabel.setFont(titleLabelFont);
        titleLabel.setForeground(new Color(-16579837));
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setText("Criteria Information");
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        leftPanel = new JPanel();
        leftPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPanel.setBackground(new Color(-11038572));
        panel1.add(leftPanel, BorderLayout.WEST);
        furnishedEnableLabel = new JLabel();
        furnishedEnableLabel.setForeground(new Color(-16579837));
        furnishedEnableLabel.setText("Furnished Enable");
        leftPanel.add(furnishedEnableLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        furnishedLabel = new JLabel();
        furnishedLabel.setForeground(new Color(-16579837));
        furnishedLabel.setText("Furnished");
        leftPanel.add(furnishedLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberOfBathRoomsLabel = new JLabel();
        numberOfBathRoomsLabel.setForeground(new Color(-16579837));
        numberOfBathRoomsLabel.setText("Number of Bath Rooms");
        leftPanel.add(numberOfBathRoomsLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberOfBedRoomsLabel = new JLabel();
        numberOfBedRoomsLabel.setForeground(new Color(-16579837));
        numberOfBedRoomsLabel.setText("Number of Bed Rooms");
        leftPanel.add(numberOfBedRoomsLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeOfPropertyLabel = new JLabel();
        typeOfPropertyLabel.setForeground(new Color(-16579837));
        typeOfPropertyLabel.setText("Type of Property");
        leftPanel.add(typeOfPropertyLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        areaLabel = new JLabel();
        areaLabel.setForeground(new Color(-16579837));
        areaLabel.setText("Area");
        leftPanel.add(areaLabel, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rentTermLabel = new JLabel();
        rentTermLabel.setForeground(new Color(-16579837));
        rentTermLabel.setText("Rent Term");
        leftPanel.add(rentTermLabel, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rentAmountLabel = new JLabel();
        rentAmountLabel.setForeground(new Color(-16579837));
        rentAmountLabel.setText("Rent Amount");
        leftPanel.add(rentAmountLabel, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityQuadrantLabel = new JLabel();
        cityQuadrantLabel.setForeground(new Color(-16579837));
        cityQuadrantLabel.setText("City Quadrant");
        leftPanel.add(cityQuadrantLabel, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        propertyIDLabel = new JLabel();
        propertyIDLabel.setForeground(new Color(-16579837));
        propertyIDLabel.setText("Property ID");
        leftPanel.add(propertyIDLabel, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightPanel = new JPanel();
        rightPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        rightPanel.setBackground(new Color(-11038572));
        panel1.add(rightPanel, BorderLayout.EAST);
        furnishedEnableField = new JTextField();
        furnishedEnableField.setBackground(new Color(-1114369));
        rightPanel.add(furnishedEnableField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        furnishedField = new JTextField();
        furnishedField.setBackground(new Color(-1114369));
        rightPanel.add(furnishedField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        numBathRoomsField = new JTextField();
        numBathRoomsField.setBackground(new Color(-1114369));
        rightPanel.add(numBathRoomsField, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        numBedRoomsField = new JTextField();
        numBedRoomsField.setBackground(new Color(-1114369));
        rightPanel.add(numBedRoomsField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        typePropertyField = new JTextField();
        typePropertyField.setBackground(new Color(-1114369));
        rightPanel.add(typePropertyField, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        areaField = new JTextField();
        areaField.setBackground(new Color(-1114369));
        rightPanel.add(areaField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        rentTermField = new JTextField();
        rentTermField.setBackground(new Color(-1114369));
        rightPanel.add(rentTermField, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        rentAmountField = new JTextField();
        rentAmountField.setBackground(new Color(-1114369));
        rightPanel.add(rentAmountField, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cityQuadrantField = new JTextField();
        cityQuadrantField.setBackground(new Color(-1114369));
        rightPanel.add(cityQuadrantField, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        propertyIDField = new JTextField();
        propertyIDField.setBackground(new Color(-1114369));
        rightPanel.add(propertyIDField, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        bottomPanel.setBackground(new Color(-11038572));
        panel1.add(bottomPanel, BorderLayout.SOUTH);
        registerButton = new JButton();
        registerButton.setText("OK");
        bottomPanel.add(registerButton);
        backButton = new JButton();
        backButton.setText("Back");
        bottomPanel.add(backButton);
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


    private class ALBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}
