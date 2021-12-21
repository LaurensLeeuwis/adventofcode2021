package eu.leeuwis.adventofcode2021.day19;

@FunctionalInterface
interface Transformer {
    RelativeBeacon transform(RelativeBeacon beacon);
}
