package io.tailrec.example;

/**
 * @author Hussachai Puripunpinyo
 */
public class IpPairSummaryRequest {

    private String ip1;

    private String ip2;

    @Override
    public String toString() {
        return "IpPairSummaryRequest{" +
                "ip1='" + ip1 + '\'' +
                ", ip2='" + ip2 + '\'' +
                '}';
    }

    public String getIp1() {
        return ip1;
    }

    public String getIp2() {
        return ip2;
    }
}
