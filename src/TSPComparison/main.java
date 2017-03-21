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
        ArrayList<double[]> list;
        list = new ArrayList<>();
//        double temp[];
//        for (int i = 0; i < 8; i++) {
//            temp = new double[2];
//            for (int j = 0; j < 2; j++) {
//                temp[j] = Math.random() * 10 - 5;
//                System.out.print(temp[j] + "\t");
//            }
//            list.add(temp);
//            System.out.println();
//        }

        double[] temp0 = {2.8, 2.3};
        list.add(temp0);
        double[] temp1 = {3.5, 0.8};
        list.add(temp1);
        double[] temp2 = {2.6, 2.0};
        list.add(temp2);
        double[] temp3 = {-1.4, -4.5};
        list.add(temp3);
        double[] temp4 = {-1.4, 4.3};
        list.add(temp4);
        double[] temp5 = {5.0, 1.0};
        list.add(temp5);
        double[] temp6 = {-0.7, 2.3};
        list.add(temp6);
        double[] temp7 = {1.9, 0.9};
        list.add(temp7);



        Net net = new Net(2, list, list.size());

        SimpleOutputFrame frame = new SimpleOutputFrame(net);
        frame.show();
        net.start();
        BruteForce bruteForce = new BruteForce(2, list, 8);
        bruteForce.startCasual();

//        new Comparison();

    }
}
