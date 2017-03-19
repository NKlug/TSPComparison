package TSPComparison.neuralnet;

import TSPComparison.VectorCalc;

import java.util.ArrayList;

/**
 * Created by Nick on 14.03.2017.
 */
public class Net {

    private final int EPOCHS = 5000;
    private double radius = 1;
    private double epsilon = 0.6;
    private int time = 0;

    private int winner;
    private int dim_in;
    private int dim_out;

    private ArrayList<double[]> inputVectors;

    private int output[];
    private double weights[][];

    private TSPComparison.neuralnet.DrawPanel panel;

    public Net(int dim_in, ArrayList<double[]> inputVectors, int dim_out) {
        this.dim_in = dim_in;
        this.dim_out = dim_out;

        output = new int[dim_out];
        weights = new double[dim_out][dim_in];

        this.inputVectors = inputVectors;
    }

    private void initializeWeights() {
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_in; j++) {
                weights[i][j] = Math.random() - 0.5;
            }
        }
    }

    public void start() {
        do {
            System.out.println("Started Calculation.");
            time = 0;
            initializeWeights();
            for (int i = 0; i < EPOCHS; i++) {
                for (int j = 0; j < dim_out; j++) {
                    selectWinner(inputVectors.get(j));
                    modifyWeights(inputVectors.get(j));
                    refreshPanel();
                }
                time++;
            }
            calculateOrder();
        } while (!validateOutput());

        printOutput();
    }

    private void selectWinner(double[] input) {
        double currentWinnerMagnitude = Double.MAX_VALUE;
        double temp;
        for (int i = 0; i < dim_out; i++) {
            if ((temp = VectorCalc.magnitude(VectorCalc.subtract(input, weights[i], dim_in), dim_in)) < currentWinnerMagnitude) {
                currentWinnerMagnitude = temp;
                winner = i;
            }
        }
    }

    private void modifyWeights(double[] input) {

        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_in; j++) {
                weights[i][j] += n() * neighborhood(i) * (input[j] - weights[i][j]);
            }
        }
    }

    private double neighborhood(int neuron) {
        int distance = Math.abs(winner - neuron);
        if (distance > dim_out / 2) {
            distance = dim_out - distance;
        }
        return Math.exp(-Math.pow(distance, 2) / (2 * sigma()));
    }

    private double sigma() {
        return radius - (0.95 / EPOCHS) * time;
    }

    private double n() {
        return epsilon - (0.55 / EPOCHS) * time;
    }

    public void addInputVector(double[] vector) {
        inputVectors.add(vector);
        dim_out++;
        output = new int[dim_out];
        weights = new double[dim_out][dim_in];
        initializeWeights();
    }

    private void calculateOrder() {
        for (int i = 0; i < dim_out; i++) {
            selectWinner(inputVectors.get(i));
            output[winner] = i;
        }
    }

    private boolean validateOutput() {
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_out; j++) {
                if (i != j && output[i] == output[j]) {
                    return false;
                }
            }
        }
        System.out.println("Output validated.");
        return true;
    }

    public void printOutput() {
        System.out.print("\nCalculated Order: ");
        for (int i = 0; i < dim_out; i++) {
            System.out.print(output[i] + "  ");
        }
        System.out.println("\n");
    }

    public int[] getOutput() {
        return this.output;
    }

    public double[][] getWeights() {
        return this.weights;
    }

    public int getDim_in() {
        return this.dim_in;
    }

    public int getDim_out() {
        return this.dim_out;
    }

    public void refreshPanel() {
        panel.setWeights(this.weights);
    }

    public void setPanel(TSPComparison.neuralnet.DrawPanel panel) {
        this.panel = panel;
    }

    public ArrayList getInput() {
        return this.inputVectors;
    }

    public void setPanelWinner() {
        panel.setWinner(this.winner);
    }



}
