/**
 * Created by perry on 10/12/15.
 */
public class Real_Vector implements Vector {
    double [] elements ;
    int left, right, size;

    //конструктор, хз, зачем он нужен
    public Real_Vector(double[] elements){
        this.elements=elements;
        this.size=elements.length;
        right=size-1;
        left=0;
    }
    //это какая-то тавтология, не?
    // штука, чтобы вырезать куски
    public Real_Vector(double[] elements,int left,int right){
        if(left>right) throw new IllegalArgumentException("left is bigger than right");
        this.elements=elements;
        this.left=left;
        this.right=right;
        size=right-left+1;
    }
    //функции!
    public int get_size ()
    {
        return size;
    }
    public double get (int i)
    {
        if ((i+left<right+1)&&(i>-1))
            return elements[i+left];
        throw new ArrayIndexOutOfBoundsException("Этого элемента вектора не существует!");
    }

    public Vector sum (Vector v) //u.sum(v)
    {
        double[] elements=new double[size];
        for(int i=0;i<size;i++) elements[i]=this.get(i)+v.get(i);
        return new Real_Vector(elements);
    }

    @Override
    public double multiply (Vector v)
    {
        double a=0;
        for(int i=0;i<size;i++) a=a+(this.get(i)*v.get(i));
        return a;
    }

    public Vector multiply ( double a ) //u.sum(v)
    {
        double[] elements=new double[size];
        for(int i=0;i<size;i++) elements[i]=this.get(i)*a;
        return new Real_Vector(elements);
    }

    public boolean is_empty ()
    {
        if (size==0) return true;
        else return false;
    }
    public Vector part(int i,int j){
        return new Real_Vector(elements,left+i,left+j);
    }
    public boolean equal (Vector v)
    {
        if (this.get_size()!=v.get_size()) return false;
        for (int i=0; i<this.get_size(); i++)
            if( this.get(i)!=v.get(i) )return false;
        return true;
    }
    public void print()
    {
        for (int i=0; i<this.get_size(); i++)
            System.out.print( this.get(i)+" ");
        System.out.println();
    }

}
