package eu.leeuwis.adventofcode2021.day21;

class Die {
    private long rolled = 0L;

    int roll(){
        rolled++;

        int ret = (int)(rolled % 100);

        if (ret == 0){
            return 100;
        } else {
            return ret;
        }
    }

    long total() {
        return rolled;
    }
}
