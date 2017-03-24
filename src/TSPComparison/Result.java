package TSPComparison;

import java.io.*;

/**
 * Created by Nick on 22.03.2017.
 */
public class Result {

    private int cityNum;
    private long bruteForceTime;
    private long bruteForceOptimizedTime;
    private long netTime;
    private double bruteForceLength;
    private double netLength;
    private double bruteForceOptimizedLength;



    public Result() {

    }



    public void saveResult(File file) throws IOException {
        String net = (netLength + "").replace('.', ',');
        String bruteForce = (bruteForceLength + "").replace('.', ',');
        String bruteForceOptimized = (bruteForceOptimizedLength + "").replace('.', ',');

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        writer.println(cityNum + ";" + (((int) netLength*100000 == (int) bruteForceLength*100000) ? 1 : 0) + ";" + netTime + ";" + net + ";" + bruteForceTime + ";" + bruteForce + ";" + bruteForceOptimizedTime + ";" + bruteForceOptimized + ";");
        writer.flush();
        writer.close();
    }


    public long getBruteForceTime() {
        return bruteForceTime;
    }

    public void setBruteForceTime(long bruteForceTime) {
        this.bruteForceTime = bruteForceTime;
    }

    public long getNetTime() {
        return netTime;
    }

    public void setNetTime(long netTime) {
        this.netTime = netTime;
    }

    public double getBruteForceLength() {
        return bruteForceLength;
    }

    public void setBruteForceLength(double bruteForceLength) {
        this.bruteForceLength = bruteForceLength;
    }

    public double getNetLength() {
        return netLength;
    }

    public void setNetLength(double netLength) {
        this.netLength = netLength;
    }

    public int getCityNum() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }

    public long getBruteForceOptimizedTime() {
        return bruteForceOptimizedTime;
    }

    public void setBruteForceOptimizedTime(long bruteForceOptimizedTime) {
        this.bruteForceOptimizedTime = bruteForceOptimizedTime;
    }

    public double getBruteForceOptimizedLength() {
        return bruteForceOptimizedLength;
    }

    public void setBruteForceOptimizedLength(double bruteForceOptimizedLength) {
        this.bruteForceOptimizedLength = bruteForceOptimizedLength;
    }


}
