/**
 * Created by perry on 10/19/15.
 */
import org.junit.Assert;

public class MatrixTest {
    @org.junit.Test
    public void testSlicemultiply() throws Exception {
        for( int i=0; i<1; i++)
        {
        long startTime = System.nanoTime();
        Matrix U=(new RandomMatrix(1000,1000)).toMatrix();
        Matrix S=(new RandomMatrix(1000,1000)).toMatrix();
        //U.print();S.print();
        long genTime = System.nanoTime() - startTime;
        System.out.println(genTime);
        Assert.assertTrue (U.OKmultiply(S).compareto(U.Slicemultiply(S))); //System.out.println("true");
        //else System.out.println("false");
        long finTime = System.nanoTime() - startTime;
        System.out.println(finTime);
        }

    }
}
