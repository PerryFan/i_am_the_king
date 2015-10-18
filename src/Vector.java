/**
 * Created by perry on 10/14/15.
 */
public interface Vector {
    public  int get_size();
    public double get (int i);
    public Vector sum (Vector v);
    public  double multiply (Vector v);
    public  Vector multiply (double a);
    public  Vector part (int i, int j);
    public boolean is_empty ();
    public boolean equal (Vector v);
    public void print();
}
