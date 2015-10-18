/**
 * Created by perry on 10/12/15.
 */
public class Real_Matrix implements Matrix
{
Vector [] row;
Vector [] column;
int height, width ;
int p, l, v, n; //право лево верх низ
public Real_Matrix( Vector [] row)
{
    this.row = row;
    this.height=row.length;
    this.width= row[0].get_size();
    column=new Vector[width];
    for(int i=0; i<this.width; i++)
    {
        double []C=new double[height];
        for (int j=0; j<this.height; j++)
        {
            C[j]=row[j].get(i);
        }
        column[i]=new Real_Vector(C);

    }
}
/* Real_Matrix (Vector [] row, Vector [] column, int p, int l, int v, int n)
{
    this.row=row;
    this.p=p;
    this.l=l;
    this.v=v;
    this.n=n;
    this.column=column;

}
*/
    public int get_height()
    {
        return height;
    }

    public int get_width()
    {
        return width;
    }

    public Vector get_row (int i)
    {
        return row[i];
    }

    public Vector get_column(int i)
    {
        return column[i];
    }

    public Matrix sum (Matrix M)
    {
        Vector [] row= new Vector [height];
        for (int i=0; i<height; i++)
            row[i]=this.get_row(i).sum(M.get_row(i));

        return new Real_Matrix (row);
    }

    public double get (int i, int j)
    {
        return this.get_row(i).get(j);
    }

    @Override
    public Matrix Transpose() {
        return new Real_Matrix(column);

    }



    public Vector multiply (Vector V)
    {
        double [] A=new double [this.get_height()];
        for (int i=0; i<get_height(); i++)
        {
            A[i]=this.get_row(i).multiply(V);
        }
        return new Real_Vector(A);
    }

    public Matrix OKmultiply (Matrix M)
    {
        Vector [] C=new Vector [M.get_width()];
        for (int i=0; i<M.get_width(); i++)
        {
            C[i]=this.multiply(M.Transpose().get_row(i));
        }
        return new Real_Matrix(C).Transpose();
    }


   public Matrix Slicemultiply (Matrix M)
    {
        if((this.height<6)||(this.width<6)||(M.get_height()<6)||(M.get_width()<6))
            return OKmultiply(M);
        else
            return (new BlockMatrix (this, height/2, width/2)).Slicemultiply(new BlockMatrix(M, M.get_height()/2, M.get_width()/2) ).toMatrix();
    }

    public void print()
    {
        for (int i=0; i<this.get_height(); i++)
            get_row(i).print();
    }
    public boolean compareto(Matrix M)
    {
       if (height!=M.get_height()||width!=M.get_width())
       {
           return false;
       }
       for (int i=0; i<height; i++)
           for(int j=0; j<width; j++)
           {
               if (Math.abs(this.get(i, j)-M.get(i, j))>0.01)return false;
           }

        return true;
    }

}
