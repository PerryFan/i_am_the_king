/**
 * Created by perry on 10/14/15.
 */
public interface Matrix {

        public int get_height();
        public int get_width();
        public Vector get_row (int i);
   //     public Vector get_column(int i);
        public Vector multiply (Vector V);
        public Matrix OKmultiply (Matrix M);
        public Matrix Slicemultiply (Matrix M);
    public boolean compareto (Matrix M);
  //      public Matrix part (int i, int j, int k, int l );
        public Matrix sum (Matrix M);
        public double get (int i, int j);
        public Matrix Transpose();
        public void print();


}
