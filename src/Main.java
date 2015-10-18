/**
 * Created by perry on 10/12/15.
 */
public class Main {

    public static void main(String[] args)
    {
        Vector v1=new Real_Vector(new double[] {1, 2, 3, 4, 5, 6});
        Vector v2=new Real_Vector(new double[] {1, 1, 1, 4, 1, 6});
        Vector v3=new Real_Vector(new double[] {1, 1, 1, 4, 1, 6});
        Vector v4=new Real_Vector(new double[] {1, 1, 1, 4, 1, 6});
        Vector v5=new Real_Vector(new double[] {1, 1, 1, 4, 1, 6});
        Vector v6=new Real_Vector(new double[] {1, 1, 1, 4, 1, 6});
        Vector v7=new Real_Vector(new double[] {1, 1, 1, 4, 1, 6});
        Vector [] rows = {v1,v2,v3,v4,v5,v6, v7};
        Vector [] rw = {v1,v2,v3,v4,v5,v6, v7};
        Matrix M1 = new Real_Matrix(rw);
        M1.print();
        Matrix M = new Real_Matrix(rows);
        //M1.multiply(v).print();
        M1 = M1.Transpose();
        M1.print();
                M.print();

        BlockMatrix bk = new BlockMatrix(M, 1, 1);
        bk.print();
        BlockMatrix T =new BlockMatrix(M, 1, 1).Transpose();
        T.print();
        bk.Slicemultiply(T).print();
        M.Slicemultiply(M1).print();
        M.print();
        //genrandmat(7, 7).print();
        Matrix U=(new RandomMatrix(7,7)).toMatrix();
        Matrix S=(new RandomMatrix(7,7)).toMatrix();
        U.print();S.print();
        if (U.OKmultiply(S).compareto(U.Slicemultiply(S))) System.out.println("true");
        //bk.print();
/*        System.out.println(v.get_size());
        v.sum(w).print();
        System.out.println (v.multiply(w));
        v.multiply(-1).print();
        System.out.println(w.get(5));
        v.part(2,4).print();
*/
        //bk.print();
    }


}
