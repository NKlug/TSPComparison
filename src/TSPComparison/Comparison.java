package TSPComparison;

import TSPComparison.bruteforce.BruteForce;
import TSPComparison.neuralnet.Net;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Nick on 20.03.2017.
 */
public class Comparison {

    public final int MIN_CITIES = 8;
    public final int MAX_CITIES = 12;
    public final int REPETITIONS = 100;

    private final int DIM_IN = 2;

    private final String FILENAME = "C:\\Users\\Nick\\IdeaProjects\\TSPComparison\\results\\results_V1.csv";

    private File file;
    private ArrayList<double[]> inputVectors;
    private LinkedList<Result> results;

    private Net net;
    private BruteForce bruteForce;


    public Comparison() {
        double[][] adjacentMatrix;
        results = new LinkedList<>();
        file = new File(FILENAME);

        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


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

                bruteForce.reset();
                // Uses the enhanced brute force algorithm
                time = System.nanoTime();
                bruteForce.startOptimized();
                res.setBruteForceOptimizedTime(System.nanoTime() - time);
                res.setBruteForceOptimizedLength(bruteForce.getLength());

                try {
                    res.saveResult(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                results.add(res);

                assert (res.getBruteForceLength() == res.getBruteForceOptimizedLength());

                System.out.println("Net: \t\t\t\t\t" + (double) (res.getNetTime() / 1000) + " µs\t\t" + res.getNetLength() + "\t");
                System.out.println("BruteForce: \t\t\t" + (double) (res.getBruteForceTime() / 1000000) + " ms\t" + res.getBruteForceLength());
                System.out.println("BruteForce optimized: \t" + (double) (res.getBruteForceOptimizedTime() / 1000000) + " ms\t" + res.getBruteForceOptimizedLength());

                System.out.println();
            }
            System.out.println("\n\n");
        }

        int errors = 0;
        double netLength = 0, bruteForceLength = 0, netErrorLength = 0, bruteForceErrorLength = 0;
        long[] netTime = new long[MAX_CITIES - MIN_CITIES];
        long[] bruteForceTime = new long[MAX_CITIES - MIN_CITIES];
        long[] bruteForceOptimizedTime = new long[MAX_CITIES - MIN_CITIES];
        for (Result result : results) {
            if ((int) (result.getNetLength() * 100000) != (int) (result.getBruteForceLength() * 100000)) {
                errors++;
                bruteForceErrorLength += result.getBruteForceLength();
                netErrorLength += result.getNetLength();
            }
            netLength += result.getNetLength();
            bruteForceLength += result.getBruteForceLength();
            netTime[result.getCityNum() - MIN_CITIES] += result.getNetTime();
            bruteForceTime[result.getCityNum() - MIN_CITIES] += result.getBruteForceTime();
            bruteForceOptimizedTime[result.getCityNum() - MIN_CITIES] += result.getBruteForceOptimizedTime();
        }

        System.out.println("Error rate: " + (100 * errors) / results.size() + " % \t(absolute: " + errors + "/" + results.size() + ")");
        System.out.println("Net length on average " + ((netLength / bruteForceLength) - 1) * 100 + " % longer than actual Length");
        System.out.println("Net length at errors on average " + ((netErrorLength / bruteForceErrorLength) - 1) * 100 + " % longer than actual length");
        System.out.println("Average Net Time: " + arrayAverage(netTime) / 1000 + " µs");
        System.out.println("Average Brute Force Time: " + arrayAverage(bruteForceTime) / 1000 + " µs");
        System.out.println("Average Brute Force Optimized Time: " + arrayAverage(bruteForceOptimizedTime) / 1000 + " µs");


    }

    private long arrayAverage(long[] array) {
        long result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i] / REPETITIONS;
        }
        return result / array.length;
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
