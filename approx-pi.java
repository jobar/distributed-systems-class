/**
 * Created by pascalluttgens on 04/10/16.
 */
public class Pi implements Runnable {

    private final int itv;
    private final int nDivs;
    private final int begin;
    private int result = 0;
    private boolean done = false;

    public Pi(int itv, int nDivs, int begin) {
        this.nDivs = nDivs;
        this.begin = begin;
        this.itv = itv;
    }

    @Override
    public void run() {
        for (int i = begin; i < this.nDivs; ++i)
            result += 1/nDivs*(Math.sqrt(1-(Math.pow(i/nDivs, 2))));
        this.done = true;
    }

    public boolean isDone() { return this.done; }

}
