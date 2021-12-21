package eu.leeuwis.adventofcode2021.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day19 {

    long puzzle1(List<String> input) {
        List<Scanner> scanners = parse(input);
        List<Beacon> beacons = new ArrayList<>();

        // add overlaps
        for (int i = 0; i < scanners.size(); i++) {
            for (int j = 0; j < i; j++) {
                Scanner scanner = scanners.get(i);
                Scanner scanner1 = scanners.get(j);
                if (!scanner.equals(scanner1) && scanner.possiblyOverlaps(scanner1)) {
                    addToResult(scanner, scanner1, scanner.translations(scanner1), beacons);
                }
            }
        }

        // add non-overlaps
        for (Scanner scanner : scanners) {
            addToResult(scanner, beacons);
        }

        return beacons.size();
    }

    private void addToResult(Scanner scanner, List<Beacon> beacons) {
        for (RelativeBeacon relativeBeacon : scanner.relativeBeacons()) {
            boolean contains = false;
            for (Beacon aBeacon : beacons) {
                if (aBeacon.relativePositions().containsKey(scanner) && aBeacon.relativePositions().get(scanner).equals(relativeBeacon)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                HashMap<Scanner, RelativeBeacon> theMap = new HashMap<>();
                theMap.put(scanner, relativeBeacon);
                beacons.add(new Beacon(theMap));
            }
        }
    }

    private void addToResult(Scanner scanner, Scanner scanner1,
            Map<RelativeBeacon, RelativeBeacon> translations, List<Beacon> beacons) {

        for (Map.Entry<RelativeBeacon, RelativeBeacon> entry : translations.entrySet()) {
            Beacon theBeacon = new Beacon(new HashMap<>());
            for (Beacon aBeacon : beacons) {
                if ((aBeacon.relativePositions().containsKey(scanner) &&
                        aBeacon.relativePositions().get(scanner).equals(entry.getKey()))
                        || (aBeacon.relativePositions().containsKey(scanner1)
                        && aBeacon.relativePositions().get(scanner1).equals(entry.getValue()))) {
                    theBeacon = aBeacon;
                    break;
                }
            }
            beacons.remove(theBeacon);

            theBeacon.relativePositions().put(scanner, entry.getKey());
            theBeacon.relativePositions().put(scanner1, entry.getValue());

            beacons.add(theBeacon);
        }
    }

    long puzzle2(List<String> input) {
        List<Scanner> scanners = parse(input);

        long maxDistance = 0;

        Map<FromTo, Transformer> transformers = new HashMap<>();

        // add overlaps
        for (Scanner scanner : scanners) {
            for (Scanner scanner1 : scanners) {
                if (!scanner.equals(scanner1) && scanner.possiblyOverlaps(scanner1)) {
                    var translations = scanner.translations(scanner1);
                    var transformer = findTransformer(translations);

                    transformers.put(new FromTo(scanner1.id(), scanner.id()), transformer);
                }
            }
        }

        List<String> scannerNames = scanners.stream().map(Scanner::id).toList();

        boolean added = true;
        while (added) {
            added = false;
            for (String nameFrom : scannerNames) {
                for (String nameTo : scannerNames) {
                    if (!nameFrom.equals(nameTo)
                            && !transformers.containsKey(new FromTo(nameFrom, nameTo))) {
                        for (String intermediateName : scannerNames) {
                            if (transformers.containsKey(new FromTo(intermediateName, nameTo))
                                    && transformers.containsKey(new FromTo(nameFrom, intermediateName))) {
                                Transformer transformer = (b) -> transformers.get(new FromTo(intermediateName, nameTo)).transform(
                                        transformers.get(new FromTo(nameFrom, intermediateName)).transform(b));
                                transformers.put(new FromTo(nameFrom, nameTo), transformer);
                                added = true;
                            }
                        }
                    }
                }
            }
        }

        List<RelativeBeacon> scannerPositions = new ArrayList<>();
        for (String scannerName : scannerNames) {
            if (scannerName.equals("0")) {
                scannerPositions.add(new RelativeBeacon(0, 0, 0));
            } else {
                scannerPositions.add(transformers.get(new FromTo(scannerName, "0")).transform(new RelativeBeacon(0, 0, 0)));
            }
        }

        for (RelativeBeacon posA : scannerPositions) {
            for (RelativeBeacon posB : scannerPositions) {
                long distance = Math.abs(posA.x() - posB.x()) + Math.abs(posA.y() - posB.y()) + Math.abs(posA.z() - posB.z());
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }

        return maxDistance;
    }

    private Transformer findTransformer(Map<RelativeBeacon, RelativeBeacon> translations) {
        long xDistance = 0;
        long yDistance = 0;
        long zDistance = 0;

        var referenceIterator = translations.keySet().iterator();
        RelativeBeacon referenceBeacon = referenceIterator.next();
        RelativeBeacon secondReference = referenceIterator.next();

        for (RelativeBeacon beacon : translations.keySet()) {
            xDistance += Math.abs(beacon.x() - referenceBeacon.x());
            yDistance += Math.abs(beacon.y() - referenceBeacon.y());
            zDistance += Math.abs(beacon.z() - referenceBeacon.z());
        }

        List<Transformer> axisTransformers = new ArrayList<>();
        axisTransformers.add((b) -> new RelativeBeacon(b.x(), b.y(), b.z()));
        axisTransformers.add((b) -> new RelativeBeacon(b.x(), b.z(), b.y()));
        axisTransformers.add((b) -> new RelativeBeacon(b.y(), b.x(), b.z()));
        axisTransformers.add((b) -> new RelativeBeacon(b.y(), b.z(), b.x()));
        axisTransformers.add((b) -> new RelativeBeacon(b.z(), b.x(), b.y()));
        axisTransformers.add((b) -> new RelativeBeacon(b.z(), b.y(), b.x()));

        Transformer rightTransformer = null;

        for (Transformer axisTransformer : axisTransformers) {
            long x1Distance = 0;
            long y1Distance = 0;
            long z1Distance = 0;
            RelativeBeacon transformedTranslatedReference = axisTransformer.transform(translations.get(referenceBeacon));

            for (RelativeBeacon translatedBeacon : translations.values()) {
                RelativeBeacon transformedTranslatedBeacon = axisTransformer.transform(translatedBeacon);
                x1Distance += Math.abs(transformedTranslatedBeacon.x() - transformedTranslatedReference.x());
                y1Distance += Math.abs(transformedTranslatedBeacon.y() - transformedTranslatedReference.y());
                z1Distance += Math.abs(transformedTranslatedBeacon.z() - transformedTranslatedReference.z());
            }

            if (x1Distance == xDistance && y1Distance == yDistance && z1Distance == zDistance) {
                rightTransformer = axisTransformer;
                break;
            }
        }

        final Transformer axisTransformer = rightTransformer;

        List<Transformer> signTransformers = new ArrayList<>();
        signTransformers.add((b) -> new RelativeBeacon(b.x(), b.y(), b.z()));
        signTransformers.add((b) -> new RelativeBeacon(-b.x(), b.y(), b.z()));
        signTransformers.add((b) -> new RelativeBeacon(b.x(), -b.y(), b.z()));
        signTransformers.add((b) -> new RelativeBeacon(b.x(), b.y(), -b.z()));
        signTransformers.add((b) -> new RelativeBeacon(-b.x(), -b.y(), b.z()));
        signTransformers.add((b) -> new RelativeBeacon(-b.x(), b.y(), -b.z()));
        signTransformers.add((b) -> new RelativeBeacon(b.x(), -b.y(), -b.z()));
        signTransformers.add((b) -> new RelativeBeacon(-b.x(), -b.y(), -b.z()));

        Transformer leftTranslator = (b) -> new RelativeBeacon(b.x() - referenceBeacon.x(),
                b.y() - referenceBeacon.y(),
                b.z() - referenceBeacon.z());
        Transformer inverseLeftTranslator = (b) -> new RelativeBeacon(b.x() + referenceBeacon.x(),
                b.y() + referenceBeacon.y(),
                b.z() + referenceBeacon.z());

        RelativeBeacon leftTransformedSecondReference = leftTranslator.transform(secondReference);
        RelativeBeacon axisModifiedFirstReference = axisTransformer.transform(translations.get(referenceBeacon));
        Transformer rightTranslator = (b) -> new RelativeBeacon(b.x() - axisModifiedFirstReference.x(),
                b.y() - axisModifiedFirstReference.y(),
                b.z() - axisModifiedFirstReference.z());

        int x = leftTransformedSecondReference.x();
        int y = leftTransformedSecondReference.y();
        int z = leftTransformedSecondReference.z();

        for (Transformer signTransformer : signTransformers) {
            Transformer totalTransformer = (b) -> signTransformer.transform(rightTranslator.transform(axisTransformer.transform(b)));

            var transformedTranslatedReference = totalTransformer.transform(translations.get(referenceBeacon));
            var secondTransformedTranslatedReference = totalTransformer.transform(translations.get(secondReference));

            int x1 = secondTransformedTranslatedReference.x() - transformedTranslatedReference.x();
            int y1 = secondTransformedTranslatedReference.y() - transformedTranslatedReference.y();
            int z1 = secondTransformedTranslatedReference.z() - transformedTranslatedReference.z();

            if (x == x1 && y == y1 && z == z1) {
                return (b) -> inverseLeftTranslator.transform(totalTransformer.transform(b));
            }
        }
        return null;
    }

    private List<Scanner> parse(List<String> input) {
        List<Scanner> result = new ArrayList<>();

        List<RelativeBeacon> beacons = new ArrayList<>();
        int scannerId = -1;

        for (String inputLine : input) {
            if (inputLine.contains("scanner")) {
                if (scannerId >= 0) {
                    result.add(new Scanner(Integer.toString(scannerId), beacons));
                }
                beacons = new ArrayList<>();
                scannerId = parseScannerId(inputLine);
            } else if (!inputLine.isBlank()) {
                beacons.add(parseBeacon(inputLine));
            }
        }

        // add final scanner
        result.add(new Scanner(Integer.toString(scannerId), beacons));

        return result;
    }

    private RelativeBeacon parseBeacon(String inputLine) {
        String[] x = inputLine.split(",");
        return new RelativeBeacon(parse(x[0]), parse(x[1]), parse(x[2]));
    }

    private int parseScannerId(String inputLine) {
        String[] x = inputLine.split("--- scanner ");
        String y = x[1].split(" ---")[0];
        return parse(y);
    }

    private int parse(String input) {
        return Integer.parseInt(input);
    }

}
