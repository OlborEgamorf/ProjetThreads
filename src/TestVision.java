package src;
public class TestVision {
    public static void main(String[] args) throws InterruptedException {
        Plage plage = new Plage(10,10,10,0,0,8,3);

        Personne[] thr = plage.getThreads();
        int[] pos1 = {1,1};
        int[] pos2 = {1,4};
        int[] pos3 = {1,8};
        thr[0].setObjPosition(pos1);
        thr[1].setObjPosition(pos2);
        thr[2].setObjPosition(pos3);

        thr[0].setPosition(pos3);
        thr[1].setPosition(pos1);
        thr[2].setPosition(pos2);

        while(true){
            plage.turn();
            Thread.sleep(3000);
        }

    }
}
