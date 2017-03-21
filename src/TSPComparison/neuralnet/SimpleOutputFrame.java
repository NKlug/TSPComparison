package TSPComparison.neuralnet;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nick on 14.03.2017.
 */
public class SimpleOutputFrame {


    JFrame frame;
    TSPComparison.neuralnet.Net net;

    public SimpleOutputFrame(TSPComparison.neuralnet.Net net) {
        this.net = net;
        frame = new JFrame("Output Test");
        frame.setSize(new Dimension(1000,1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TSPComparison.neuralnet.DrawPanel panel = new TSPComparison.neuralnet.DrawPanel(net.getWeights(), net.getDim_out(), net.getInput());

        net.setPanel(panel);

        frame.setContentPane(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

}
