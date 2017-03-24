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
    private double optimumLength;
    private double[][] adjacentMatrix;

    public BruteForce(int dim_in, ArrayList<double[]> inputVectors, int dim_out, double[][] adjacentMatrix) {
        this.inputVectors = inputVectors;
        this.dim_in = dim_in;
        this.dim_out = dim_out;
        this.adjacentMatrix = adjacentMatrix;

        reset();
    }

    public void reset() {
        optimal = new int[dim_out];
        currentOutput = new int[dim_out];
        optimumLength = Double.MAX_VALUE;
        initializeOutput();
    }

    public void startCasual() {
        permutation(0);
    }

    public void startOptimized() {
        permutationOptimized(0);
    }

    private void initializeOutput() {
        for (int i = 0; i < dim_out; i++) {
            currentOutput[i] = -1;
        }
    }

    public void isOptimal(int[] permutation) {
        if (VectorCalc.distance(permutation, adjacentMatrix, dim_out) < optimumLength) {
            for (int i = 0; i < dim_out; i++) {
                optimal[i] = permutation[i];
            }
            optimumLength = VectorCalc.distance(permutation, adjacentMatrix, dim_out);
        }
    }

    public int getFirstEmptyPosition(int[] permutation, int n) {
        for (int i = n; i < dim_out; i++) {
            if (permutation[i] == -1) {
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
            if (currentOutput[i] == -1 || currentOutput[(i + 1) % dim_out] == -1)
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
                permutation(level + 1);
                currentOutput[currentPosition] = -1;
            }
        }
    }

    public void permutationOptimized(int level) {
        int currentPosition = -1;
        if (level == dim_out) {
            isOptimal(currentOutput);
        } else {
            for (int i = 0; i < dim_out - level; i++) {
                if (minDistance() < optimumLength) {
                    currentPosition = getFirstEmptyPosition(currentOutput, ++currentPosition);
                    currentOutput[currentPosition] = level;
                    permutation(level + 1);
                    currentOutput[currentPosition] = -1;
                }
            }
        }
    }

    public double getLength() {
        return VectorCalc.distance(optimal, adjacentMatrix, dim_out);
    }

    public void printOutput() {
        System.out.print("\nBrute Force calculated order: ");
        for (int i = 0; i < dim_out; i++) {
            System.out.print(optimal[i] + "  ");
        }
        System.out.println("Total Length: " + getLength() + "\n");
    }


}
