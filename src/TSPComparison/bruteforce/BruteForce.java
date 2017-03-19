package TSPComparison.bruteforce;

import TSPComparison.VectorCalc;

import java.util.ArrayList;

/**
 * Created by Nick on 15.03.2017.
 */
public class BruteForce {

    private int dim_in;
    private int dim_out;

    private ArrayList<double[]> inputVectors;

    private int[] optimal;
    private int[] currentOutput;
    private double currentOutputLength;
    private double[][] adjacentMatrix;

    public BruteForce(int dim_in, ArrayList<double[]> inputVectors, int dim_out) {
        this.inputVectors = inputVectors;
        this.dim_in = dim_in;
        this.dim_out = dim_out;
        currentOutputLength = Double.MAX_VALUE;

        optimal = new int[dim_out];
        currentOutput = new int[dim_out];
        adjacentMatrix = new double[dim_out][dim_out];

        initializeOutput();
        calculateAdjacentMatrix();
    }

    public void start() {
        permutation(0);
        printOutput();
    }

    private void calculateAdjacentMatrix() {
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_out; j++) {
                adjacentMatrix[i][j] = VectorCalc.magnitude(VectorCalc.subtract(inputVectors.get(i), inputVectors.get(j), dim_in), dim_in);
            }
        }
    }

    private void initializeOutput() {
        for (int i = 0; i < dim_out; i++) {
            currentOutput[i] = -1;
        }
    }

    public double distance(int[] permutation) {
        int length = 0;
        for (int i = 0; i < dim_out; i++) {
            length += adjacentMatrix[permutation[i]][permutation[(i+1) % dim_out]];
        }
        return length;
    }

    public void isOptimal(int[] permutation) {
        if(distance(permutation) < currentOutputLength) {
            for (int i = 0; i < dim_out; i++) {
                optimal[i] = permutation[i];
            }
            currentOutputLength = distance(permutation);
        }
    }

    public int getFirstEmptyPosition(int[] permutation, int n) {
        for (int i = n; i < dim_out; i++) {
            if(permutation[i] == -1) {
                return i;
            }
        }
        return dim_out;
    }

    public double findMinDistance() {
        double currentMin = Double.MAX_VALUE;
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < i; j++) {
                if (adjacentMatrix[i][j] < currentMin)
                    currentMin = adjacentMatrix[i][j];
            }
        }
        return currentMin;
    }

    public double minDistance() {
        double distance = 0;
        double minDistance = findMinDistance();
        for (int i = 0; i < dim_out; i++) {
            if (currentOutput[i] == -1 || currentOutput[(i + 1) % dim_out] ==  -1)
                distance += minDistance;
            else
                distance += adjacentMatrix[currentOutput[i]][currentOutput[(i + 1) % dim_out]];
        }
        return distance;
    }

    public void permutation(int level) {
        int currentPosition = -1;
        if (level == dim_out) {
            isOptimal(currentOutput);
        } else {
            for (int i = 0; i < dim_out - level; i++) {
                currentPosition = getFirstEmptyPosition(currentOutput, ++currentPosition);
                currentOutput[currentPosition] = level;
                permutation(level+1);
                currentOutput[currentPosition] = -1;
            }
        }
    }

    public void permutationOptimized(int level) {
        int currentPosition = getFirstEmptyPosition(currentOutput, 0);
        if (level == dim_out) {
            isOptimal(currentOutput);
        }
        for (int i = 0; i < dim_out-level; i++) {
            if (minDistance() < currentOutputLength) {
                currentOutput[currentPosition] = level;
                permutation(++level);
                currentOutput[currentPosition] = -1;
                currentPosition = getFirstEmptyPosition(currentOutput, ++currentPosition);
            }

        }
    }

    public void printOutput() {
        System.out.print("\nCalculated Order: ");
        for (int i = 0; i < dim_out; i++) {
            System.out.print(optimal[i] + "  ");
        }
        System.out.println("\n");
    }


}
