package TSPComparison;

import java.util.ArrayList;

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

    public static double distance(int[] permutation, double[][] adjacentMatrix, int dim_out) {
        double length = 0;
        for (int i = 0; i < dim_out; i++) {
            length += adjacentMatrix[permutation[i]][permutation[(i + 1) % dim_out]];
        }
        return length;
    }

    public static double[][] calculateAdjacentMatrix(ArrayList<double[]> inputVectors, int dim_out, int dim_in) {
        double[][] adjacentMatrix = new double[dim_out][dim_out];
        for (int i = 0; i < dim_out; i++) {
            for (int j = 0; j < dim_out; j++) {
                adjacentMatrix[i][j] = VectorCalc.magnitude(VectorCalc.subtract(inputVectors.get(i), inputVectors.get(j), dim_in), dim_in);
            }
        }
        return adjacentMatrix;
    }

    public static boolean compare(int[] permutation1, int[] permutation2, int dim_out) {
        if (true) {
            // vorwärts
            for (int i = 0; i < dim_out; i++) {

            }
        } else {
            // rückwärts
        }

        return true;
    }
}
