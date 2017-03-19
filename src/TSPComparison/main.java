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
        int originX = 0;
        int originY = 0;
        ArrayList<double[]> list = new ArrayList<double[]>();
        double[] temp = {originX + -3, originY + 3};
        list.add(temp);
        double[] temp1 = {originX + 0, originY + 3};
        list.add(temp1);
        double[] temp2 = {originX + -3, originY + 0};
        list.add(temp2);
        double[] temp3 = {originX + 0, originY + -3};
        list.add(temp3);
        double[] temp4 = {originX + 3, originY + 0};
        list.add(temp4);
        double[] temp5 = {originX + 3, originY + 3};
        list.add(temp5);
        double[] temp6 = {originX + 3, originY + -3};
        list.add(temp6);
        double[] temp7 = {originX + (-3), originY + -3};
        list.add(temp7);


        Net net = new Net(2, list, list.size());


//        BruteForce bruteForce = new BruteForce(2, list, list.size());

        SimpleOutputFrame frame = new SimpleOutputFrame(net);
//        net.start();
        BruteForce bruteForce = new BruteForce(2, list, 8);
        bruteForce.start();

    }
}
