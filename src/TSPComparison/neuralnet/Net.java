package TSPComparison.neuralnet;

import TSPComparison.VectorCalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 14.03.2017.
 */
public class Net {

    public final double RADIUS = 1;
    public final double EPSILON = 0.7;

    private int epochs;
    private double radius;
    private double epsilon;
    private int time;

    private int winner;
    private int dim_in;
    private int dim_out;

    private ArrayList<double[]> inputVectors;

    private int output[];
    private double weights[][];
    private double[][] adjacentMatrix;

    private TSPComparison.neuralnet.DrawPanel panel;

    public Net(int dim_in, ArrayList<double[]> inputVectors, int dim_out, double[][] adjacentMatrix) {
        this.dim_in = dim_in;
        this.dim_out = dim_out;
        this.epochs = 5 * dim_out;
        this.adjacentMatrix = adjacentMatrix;

        weights = new double[dim_out][dim_in];

        this.inputVectors = inputVectors;

        initializeOutput();
    }


    private void initializeWeights() {
        radius = RADIUS;
        epsilon = EPSILON;
        time = 0;
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_in; j++) {
                weights[i][j] = Math.random() - 0.5;
            }
        }
    }

    private void initializeOutput() {
        output = new int[dim_out];
        for (int i = 0; i < dim_out; i++) {
            output[i] = -1;
        }
    }

    public void start() {
        initializeWeights();
        calculation();
        getRoute();
    }

    private void calculation() {
        for (int i = 0; i < epochs; i++) {
            for (int j = 0; j < inputVectors.size(); j++) {
                selectWinner(inputVectors.get(j));
                modifyWeights(inputVectors.get(j));
                if (panel != null)
                    refreshPanel();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {

                }
            }
            time++;
        }
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
        return radius - (0.95 / epochs) * time;
    }

    private double n() {
        return epsilon - (0.55 / epochs) * time;
    }

    public void addInputVector(double[] vector) {
        inputVectors.add(vector);
        dim_out++;
        output = new int[dim_out];
        weights = new double[dim_out][dim_in];
        initializeWeights();
    }

    public void calculateOrder() {
        for (int i = 0; i < inputVectors.size(); i++) {
            selectWinner(inputVectors.get(i));
//            System.out.println("Output for " + i + ": " + winner);
            output[winner] = i;
        }
    }

    public LinkedList<Integer> listFromOutput() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < dim_out; i++) {
            if (output[i] != -1) {
                list.add(output[i]);
            }
        }
        return list;
    }


    private void getRoute() {

        calculateOrder();

        LinkedList<Integer> list = listFromOutput();

        int k = 0, size, currentIndex;
        while ((size = list.size()) < inputVectors.size()) {
            while (list.contains(k)) {
                k++;
            }
            selectWinner(inputVectors.get(k));
            currentIndex = list.indexOf(output[winner]);

            // Check where to add the missing city
            list.add(currentIndex, k);
            int[] temp = list.stream().mapToInt(i -> i).toArray();
            double length = VectorCalc.distance(temp, adjacentMatrix, list.size());
            list.remove((Integer) k);
            list.add((size + currentIndex + 1) % size, k);
            temp = list.stream().mapToInt(i -> i).toArray();

            if (length < VectorCalc.distance(temp, adjacentMatrix, list.size())) {
                list.remove((Integer) k);
                list.add(currentIndex, k);
            }
        }

        initializeOutput();
        for (int i = 0; i < dim_out; i++) {
            output[i] = list.get(i);
        }



/*        LinkedList<Integer> list = listFromOutput();

        if (list.size() < dim_out) {
            System.out.println("Size of List: " + list.size());
            this.dim_out = list.size();
            initializeWeights();
            initializeOutput();
            calculation();
            calculateOrder();
            LinkedList<Integer> templist = listFromOutput();

            int k = 0, size, currentIndex;
            while ((size = templist.size()) < inputVectors.size()) {
                System.out.println("Entered loop " + templist.size());
                while (templist.contains(k)) {
                    k++;
                }
                selectWinner(inputVectors.get(k));
                currentIndex = templist.indexOf(output[winner]);

                if (VectorCalc.magnitude(VectorCalc.subtract(inputVectors.get(templist.get((size + currentIndex - 1) % size)), inputVectors.get(k), dim_in), dim_in) <
                        VectorCalc.magnitude(VectorCalc.subtract(inputVectors.get(templist.get((size + currentIndex + 1) % size)), inputVectors.get(k), dim_in), dim_in)) {
                    templist.add(currentIndex, k);

                } else {
                    templist.add((size + currentIndex + 1) % size, k);
                }

            }


            dim_out = inputVectors.size();
            initializeOutput();
            for (int i = 0; i < dim_out; i++) {
                output[i] = templist.get(i);
            }
        }*/

    }

    public boolean validateOutput() {
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_out; j++) {
                if (i != j && output[i] == output[j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public void printOutput() {
        System.out.print("\nSOM calculated order: ");
        for (int i = 0; i < dim_out; i++) {
            System.out.print(output[i] + "  ");
        }
        System.out.println("Total Length: " + getLength());
    }

    public double getLength() {
        return VectorCalc.distance(output, adjacentMatrix, dim_out);
    }

    public double[][] getWeights() {
        return this.weights;
    }

    public int getDim_out() {
        return this.dim_out;
    }

    public void refreshPanel() {
        panel.setWeights(this.weights, this.dim_out);
    }

    public void setPanel(TSPComparison.neuralnet.DrawPanel panel) {
        this.panel = panel;
    }

    public ArrayList getInput() {
        return this.inputVectors;
    }


/*    public void printWeights() {
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_in; j++) {
                System.out.print(weights[i][j] + "\t");
            }
            System.out.println();
        }
    }*/


}
