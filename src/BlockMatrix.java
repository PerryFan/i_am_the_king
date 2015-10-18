/**
 * Created by perry on 10/14/15.
 */
public class BlockMatrix implements  Matrix
{
   Matrix A, B, C, D;
   BlockMatrix (Matrix A, Matrix B, Matrix C, Matrix D )
   {
       this.A=A;
       this.B=B;
       this.C=C;
       this.D=D;

   }

    BlockMatrix (Matrix M, int h, int w)
    {
        Vector [] A1 =new Vector [h];
        for (int i=0; i<h; i++)
        {
            A1[i]=M.get_row(i).part(0, w-1);
        }
        A=new Real_Matrix(A1) ;

        Vector [] C1 =new Vector [M.get_height()-h];
        for (int i=0; i<M.get_height()-h; i++)
        {
            C1[i]=M.get_row(i+h).part(0, w-1);
        }
        C=new Real_Matrix(C1) ;

        Vector [] B1 =new Vector [h];
        for (int i=0; i<h; i++)
        {
            B1[i]=M.get_row(i).part(w, M.get_width()-1);
        }
        B=new Real_Matrix(B1) ;

        Vector [] D1 =new Vector [M.get_height()-h];
        for (int i=0; i<M.get_height()-h; i++)
        {
            D1[i]=M.get_row(i+h).part(w, M.get_width()-1);
        }
        D=new Real_Matrix(D1) ;
    }

    public Matrix toMatrix()
    {
        Vector [] Y=new Vector[this.get_height()];
        for (int i=0; i<this.get_height(); i++)
            Y[i]=this.get_row(i);
        return new Real_Matrix (Y);
    }

    public int get_height()
    {
        return A.get_height()+C.get_height();
    }
    public int get_width()
    {
        return A.get_width()+B.get_width();
    }
    public double get (int i,  int j)
    {
        if (i>A.get_height())
        {
            if (j>A.get_width()) return D.get(i-A.get_height(), j-A.get_width());
            else return C.get(i-A.get_height(), j);        }
        else
        {
            if (j>A.get_width()) return B.get(i, j-A.get_width());
            else return A.get(i, j);
        }
    }

    public Vector get_row(int i)
    {
        double [] E =new double [A.get_width()+B.get_width()];
        if (i<A.get_height())
        {
            for  (int j=0; j<A.get_width(); j++)
                E[j]=A.get(i, j);
            for  (int j=A.get_width(); j<E.length; j++)
                E[j]=B.get(i, j-A.get_width());
        }


        if (i>=A.get_height())
        {
            for  (int j=0; j<A.get_width(); j++)
                E[j]=C.get(i-A.get_height(), j);
            for  (int j=A.get_width(); j<E.length; j++)
                E[j]=D.get(i-A.get_height(), j-A.get_width());

        }
        return new Real_Vector (E);


    }

    public BlockMatrix Transpose() {
        /*Matrix swap;
        swap=B;
        B=C;
        C=swap;
        A.Transpose();
        B.Transpose();
        C.Transpose();
        D.Transpose();
        B.print();*/
        return new BlockMatrix(A.Transpose(), C.Transpose(), B.Transpose(), D.Transpose());
    }

    public Matrix sum (Matrix M)
    {
        int height=A.get_height()+B.get_height();
        Vector [] row= new Vector [height];
        for (int i=0; i<height; i++)
            row[i]=this.get_row(i).sum(M.get_row(i));

        return new Real_Matrix (row);
    }
    public Vector multiply (Vector V)
    {
        double [] A=new double [this.get_height()];
        for (int i=0; i<V.get_size(); i++)
        {
            A[i]=this.get_row(i).multiply(V);
        }
        return new Real_Vector(A);
    }

    public void print()
    {
        for (int i=0; i<this.get_height(); i++)
            get_row(i).print();
    }
    public Matrix Slicemultiply (Matrix M)
    {
        return Slicemultiply(new BlockMatrix (M, A.get_width(), A.get_height()));
    }
    public Matrix OKmultiply (Matrix M)
    {
        return this.toMatrix().OKmultiply(M);
    }
    public boolean compareto (Matrix M)
    {
        if (this.get_height()!=M.get_height()||this.get_width()!=M.get_width())
        {
            return false;
        }
        for (int i=0; i<this.get_height(); i++)
            for(int j=0; j<get_width(); j++)
            {
                if (Math.abs(this.get(i, j)-M.get(i, j))>0.01)return false;
            }

        return true;
    }

    public BlockMatrix Slicemultiply( BlockMatrix M)
    {
        Matrix A1= A.Slicemultiply(M.A).sum(B.Slicemultiply(M.C));
        Matrix C1= C.Slicemultiply(M.A).sum(D.Slicemultiply(M.C));
        Matrix B1= A.Slicemultiply(M.B).sum(B.Slicemultiply(M.D));
        Matrix D1= C.Slicemultiply(M.B).sum(D.Slicemultiply(M.D));
        BlockMatrix T=new BlockMatrix(A1, B1, C1, D1);
        return  T;

    }
}