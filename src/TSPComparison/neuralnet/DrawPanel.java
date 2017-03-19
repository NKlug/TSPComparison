package TSPComparison.neuralnet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nick on 14.03.2017.
 */
public class DrawPanel extends JPanel {

    private ArrayList<double[]> input;
    private int[] output;
    private double[][] weights;
    private int winner = 0;
    private int dim_in;
    private int dim_out;

    private int centerX = 500;
    private int centerY = 500;

    private final int factor = 80;

    public DrawPanel() {

    }

    public DrawPanel(int[] output, double[][] weights, int dim_in, int dim_out, ArrayList<double[]> input) {
        this.output = output;
        this.weights = weights;
        this.dim_in = dim_in;
        this.dim_out = dim_out;
        this.input = input;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(0,0,1000,1000);
        g2.setColor(Color.black);
        g2.fillRect(centerX, centerY, 3, 3);

        g2.setColor(Color.red);
        for (int i = 0; i < dim_out; i++) {
            g2.drawString( i + "",centerX + (int) (weights[i][0] * factor), centerY - (int) (weights[i][1] * factor));

        }

        g2.setColor(Color.green);
        g2.fillRect(centerX + (int) (input.get(1)[0] * factor), centerY - (int) (input.get(1)[1] * factor), 3, 3);

        g2.setColor(Color.blue);
        g2.drawString(winner + "",centerX + (int) (weights[winner][0] * factor), centerY - (int) (weights[winner][1] * factor));
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
        this.repaint();
    }

    public void setWinner(int winner) {
        this.winner = winner;
        this.repaint();
    }
}
