import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    private static class Partition {
        public double begin;
        public double end;
        public double itv;

        public Partition(double itv, double begin, double end) {
            this.begin = begin;
            this.end = end;
            this.itv = itv;
        }
    }

    public static void main(String[] args) {
        List<Partition> partitions = new ArrayList<>();
        double nDivs = 1000000000;
        double nParts = 100;

        long start = System.currentTimeMillis();

        for (int i = 0; i < nParts; ++i) {
            partitions.add(new Partition(nDivs, i * (nDivs/nParts) + 1, i * (nDivs/nParts) + (nDivs/nParts)));
        }


        double pi = partitions
                .parallelStream()
                .mapToDouble(p -> {
                    double result = 0.0;
                    for (double i = p.begin; i <= p.end; ++i)
                        result += 1.0/p.itv*(Math.sqrt(1.0-(Math.pow(i/p.itv, 2))));
                    return result;
                })
                .reduce((d, d2) -> d + d2)
                .getAsDouble() * 4;

        long end = System.currentTimeMillis();

        System.out.println(pi);
        System.out.println(end - start);
    }

}
