package TSPComparison;

/**
 * Created by Nick on 22.03.2017.
 */
public class Result {

    private long bruteForceTime;
    private long netTime;
    private double bruteForceLength;
    private double netLength;

    private int cityNum;

    public Result() {

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

}
