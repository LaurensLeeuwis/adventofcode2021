package eu.leeuwis.adventofcode2021.day16;

import java.math.BigInteger;
import java.util.List;

class Day16 {

    public long puzzle1(List<String> readFile) {
        String input = readFile.get(0);
        String binary = hexToBinary(input);
        Packet packet = Packet.fromBinary(binary);
        return calcSum(packet);
    }

    public long puzzle2(List<String> readFile) {
        String input = readFile.get(0);
        String binary = hexToBinary(input);
        Packet packet = Packet.fromBinary(binary);
        return packet.value();
    }

    private String hexToBinary(String hex) {
        String result = new BigInteger(hex, 16).toString(2);
        int leadingZeroes = hex.length() * 4 - result.length();

        return "0".repeat(leadingZeroes) + result;
    }

    int calcSum(Packet packet){
        Content c = packet.content();
        if (c instanceof SubPackets){
            return ((SubPackets) c).packets().stream()
                    .mapToInt(this::calcSum).sum() + packet.version();
        } else {
            return packet.version();
        }
    }
}
