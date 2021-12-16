package eu.leeuwis.adventofcode2021.day16;

import java.util.ArrayList;
import java.util.List;

record Packet(int version, int typeId, Content content, String binary) {

    static Packet fromBinary(String binary){
        int version = Integer.parseInt(binary.substring(0, 3), 2);
        int typeId = Integer.parseInt(binary.substring(3, 6), 2);
        int pos = 6;

        if (typeId == 4) {
            // literal value
            StringBuilder literal = new StringBuilder();
            while (binary.charAt(pos) != '0') {
                literal.append(binary, pos + 1, pos + 5);
                pos = pos + 5;
            }
            literal.append(binary, pos + 1, pos + 5);
            pos = pos + 5;

            long result = Long.parseLong(literal.toString(), 2);
            return new Packet(version, typeId, new Number(result), binary.substring(0, pos));
        } else {
            // operator
            if (binary.charAt(pos) == '0'){
                // next 15 bits are a number that represents the total length in bits of the sub-packets contained by this packet.
                String nr = binary.substring(pos+1, pos + 16);
                int result = Integer.parseInt(nr, 2);
                pos = pos + 16;

                List<Packet> packets = new ArrayList<>();
                String toParse = binary.substring(pos, pos+result);

                int i = 0;
                while (i < toParse.length()){
                    String current = toParse.substring(i);
                    Packet packet = fromBinary(current);
                    packets.add(packet);
                    i = i + packet.binary().length();
                }

                return new Packet(version, typeId, new SubPackets(packets), binary.substring(0, pos+i));
            } else {
                // next 11 bits are a number that represents the number of sub-packets immediately contained by this packet.
                String nr = binary.substring(pos+1, pos + 12);
                int result = Integer.parseInt(nr, 2);
                pos = pos + 12;

                String toParse = binary.substring(pos);
                List<Packet> packets = new ArrayList<>();

                int i = 0;
                while (i < result){
                    Packet packet = fromBinary(toParse);
                    packets.add(packet);
                    pos = pos + packet.binary().length();
                    toParse = binary.substring(pos);

                    i++;
                }

                return new Packet(version, typeId, new SubPackets(packets), binary.substring(0, pos));
            }
        }
    }

    private long value(Packet packet) {
        return packet.value();
    }

    long value() {
        if (content instanceof SubPackets) {
            if (typeId == 0) {
                return ((SubPackets) content).packets().stream()
                        .mapToLong(this::value).sum();
            } else if (typeId == 1) {
                return ((SubPackets) content).packets().stream()
                        .mapToLong(this::value).reduce(1, (a, b) -> a * b);
            } else if (typeId == 2) {
                return ((SubPackets) content).packets().stream()
                        .mapToLong(this::value).min().getAsLong();
            } else if (typeId == 3) {
                return ((SubPackets) content).packets().stream()
                        .mapToLong(this::value).max().getAsLong();
            } else if (typeId == 5) {
                List<Packet> packets = ((SubPackets) content).packets();
                return value(packets.get(0)) > value(packets.get(1)) ? 1 : 0;
            } else if (typeId == 6) {
                List<Packet> packets = ((SubPackets) content).packets();
                return value(packets.get(0)) < value(packets.get(1)) ? 1 : 0;
            } else if (typeId == 7) {
                List<Packet> packets = ((SubPackets) content).packets();
                return value(packets.get(0)) == value(packets.get(1)) ? 1 : 0;
            }
            return 0;
        } else {
            return ((Number) content).number();
        }
    }

}
