package GUI;

import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ALSearch implements ActionListener {
    private PrintWriter outBuffer;
    private BufferedReader inBuffer;

    ALSearch(PrintWriter outBuffer, BufferedReader inBuffer){
        this.outBuffer = outBuffer;
        this.inBuffer = inBuffer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ViewProperties(outBuffer, inBuffer);
    }
}
