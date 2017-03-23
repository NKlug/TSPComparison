package TSPComparison;

import TSPComparison.bruteforce.BruteForce;
import TSPComparison.neuralnet.Net;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Nick on 20.03.2017.
 */
public class Comparison {

    public final int MIN_CITIES = 8;
    public final int MAX_CITIES = 15;
    public final int REPETITIONS = 10;

    private final int DIM_IN = 2;

    private ArrayList<double[]> inputVectors;
    private LinkedList<Result> results;

    private Net net;
    private BruteForce bruteForce;


    public Comparison() {
        double[][] adjacentMatrix;
        results = new LinkedList<>();

        long time;
        for (int i = MIN_CITIES; i < MAX_CITIES; i++) {
            System.out.println("##########################\nNumber of Cities " + i + "\n##########################");
            for (int j = 0; j < REPETITIONS; j++) {

                Result res = new Result();
                res.setCityNum(i);

                generateInputVectors(i);
                adjacentMatrix = VectorCalc.calculateAdjacentMatrix(inputVectors, i, DIM_IN);
                net = new Net(DIM_IN, inputVectors, i, adjacentMatrix);
                bruteForce = new BruteForce(DIM_IN, inputVectors, i, adjacentMatrix);

                // Uses the kohonen feature map
                time = System.nanoTime();
                net.start();
                res.setNetTime(System.nanoTime() - time);
                res.setNetLength(net.getLength());

                // Uses the casual brute force algorithm
                time = System.nanoTime();
                bruteForce.startCasual();
                res.setBruteForceTime(System.nanoTime() - time);
                res.setBruteForceLength(bruteForce.getLength());

                bruteForce.reInitArrays();
                // Uses the enhanced brute force algorithm
//            time = System.nanoTime();
//            bruteForce.startOptimized();
//            results[i][2] = System.nanoTime() - time;

                System.out.println("Net:\t\t" + (double) (res.getNetTime() / 1000) + " µs\t" + res.getNetLength() + "\t" + net.validateOutput());
                System.out.println("BruteForce:\t" + (double) (res.getBruteForceTime() / 1000) + " µs\t" + res.getBruteForceLength());
                System.out.println();
            }
            System.out.println("\n\n");

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
