package io.tailrec.example;

import java.util.Optional;

/**
 * @author Hussachai Puripunpinyo
 */
public class IpPairSummary {

    private Optional<Double> distance;

    private IpInfo ip1Info;

    private IpInfo ip2Info;

    public IpPairSummary(){}

    public IpPairSummary(Optional<Double> distance, IpInfo ip1Info, IpInfo ip2Info) {
        this.distance = distance;
        this.ip1Info = ip1Info;
        this.ip2Info = ip2Info;
    }

    @Override
    public String toString() {
        return "IpPairSummary{" +
                "distance=" + distance +
                ", ip1Info=" + ip1Info +
                ", ip2Info=" + ip2Info +
                '}';
    }

    public Optional<Double> getDistance() {
        return distance;
    }

    public IpInfo getIp1Info() {
        return ip1Info;
    }

    public IpInfo getIp2Info() {
        return ip2Info;
    }
}
