package TSPComparison;

import TSPComparison.bruteforce.BruteForce;
import TSPComparison.neuralnet.Net;

import java.util.ArrayList;

/**
 * Created by Nick on 20.03.2017.
 */
public class Comparison {

    public final int MIN_CITIES = 8;
    public final int MAX_CITIES = 15;

    private final int DIM_IN = 2;

    private ArrayList<double[]> inputVectors;

    private Net net;
    private BruteForce bruteForce;

    private long[][] results;

    public Comparison() {
        results = new long[MAX_CITIES - MIN_CITIES][3];

        long time;
        for (int i = MIN_CITIES; i < MAX_CITIES; i++) {
            generateInputVectors(i);
            net = new Net(DIM_IN, inputVectors, i);
            bruteForce = new BruteForce(DIM_IN, inputVectors, i);

            // Uses the kohonen feature map
            time = System.nanoTime();
            net.start();
            results[i][0] = System.nanoTime() - time;

            // Uses the casual brute force algorithm
            time = System.nanoTime();
            bruteForce.startCasual();
            results[i][1] = System.nanoTime() - time;

            bruteForce.reInitArrays();
            // Uses the enhanced brute force algorithm
//            time = System.nanoTime();
//            bruteForce.startOptimized();
//            results[i][2] = System.nanoTime() - time;
        }

        for (int i = -1; i < 3; i++) {
            if (i == -1) {
                for (int j = 0; j < MAX_CITIES - MIN_CITIES; j++) {
                    System.out.print(j + "\t\t");
                }
            } else {
                for (int j = 0; j < MAX_CITIES - MIN_CITIES; j++) {
                    System.out.print((double) (results[j][i] / 1000000) + "\t");
                }
            }
            System.out.println();
        }


    }


    public void generateInputVectors(int dimension) {
        inputVectors = new ArrayList<>();
        double temp[];
        for (int i = 0; i < dimension; i++) {
            temp = new double[DIM_IN];
            for (int j = 0; j < DIM_IN; j++) {
                temp[j] = Math.random() * 20 - 10;
            }
            inputVectors.add(temp);
        }
    }
}
