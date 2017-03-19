package TSPComparison;

/**
 * Created by Nick on 16.03.2017.
 */
public class VectorCalc {

    public static double[] subtract(double[] minuend, double[] subtrahend, int dim) {
        double[] result = new double[dim];
        for (int i = 0; i < dim; i++) {
            result[i] = minuend[i] - subtrahend[i];
        }
        return result;
    }

    public static double magnitude(double[] vector, int dim) {
        double result = 0;
        for (int i = 0; i < dim; i++) {
            result += Math.pow(vector[i], 2);
        }
        return Math.sqrt(result);
    }
}
