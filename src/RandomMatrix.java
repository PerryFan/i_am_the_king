import java.util.Random;

public class RandomMatrix
{

    Random random=new Random();
    double [][]u;
    int h,w;

    public RandomMatrix (int h,int  w)
    {
        this.h = h;
        this.w = w;
        u =new double [h][w];
        for (int i=0; i<h; i++)
            for(int j=0; j<w; j++)
            {
                u[i][j]=random.nextDouble();
            }
    }

    public Matrix toMatrix()
    {
        Vector[] V=new Vector[h];
        for(int i=0; i<h; i++)
            V[i]=new Real_Vector(u[i]);
        return new Real_Matrix(V);
    }
}