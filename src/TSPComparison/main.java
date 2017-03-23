package TSPComparison;

import TSPComparison.bruteforce.BruteForce;
import TSPComparison.neuralnet.Net;
import TSPComparison.neuralnet.SimpleOutputFrame;

import java.util.ArrayList;

/**
 * Created by Nick on 14.03.2017.
 */
public class main {

    public static void main(String... args) {
/*        Net net;
        BruteForce bruteForce;
        SimpleOutputFrame frame;
        ArrayList<double[]> list;
        double[][] adjacentMatrix;

        do {

            list = new ArrayList<>();
            double temp[];
            for (int i = 0; i < 8; i++) {
                temp = new double[2];
                for (int j = 0; j < 2; j++) {
                    temp[j] = Math.random() * 10 - 5;
                }
                list.add(temp);
            }
//        double[] temp0 = {4.23, -0.08};
//        list.add(temp0);
//        double[] temp1 = {2.35, 3.13};
//        list.add(temp1);
//        double[] temp2 = {1.04, 1.28};
//        list.add(temp2);
//        double[] temp3 = {-1.27, -2.07};
//        list.add(temp3);
//        double[] temp4 = {3.18, 1.88};
//        list.add(temp4);
//        double[] temp5 = {1.77, -1.09};
//        list.add(temp5);
//        double[] temp6 = {1.17, -0.40};
//        list.add(temp6);
//        double[] temp7 = {1.77, -4.62};
//        list.add(temp7);


            adjacentMatrix = VectorCalc.calculateAdjacentMatrix(list, list.size(), 2);

            net = new Net(2, list, list.size(), adjacentMatrix);
            bruteForce = new BruteForce(2, list, 8, adjacentMatrix);

            frame = new SimpleOutputFrame(net);

            net.start();
            net.printOutput();

            bruteForce.startCasual();
            bruteForce.printOutput();
        } while ( !((int) (net.getLength()) < (int) (bruteForce.getLength())));

        frame.show();*/

//        for (int i = 0; i < adjacentMatrix.length; i++) {
//            for (int j = 0; j < adjacentMatrix.length; j++) {
//                System.out.print(adjacentMatrix[i][j] + "\t");
//            }
//            System.out.println();
//        }

        new Comparison();

    }
}
