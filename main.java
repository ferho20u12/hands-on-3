
class DataSet {
    private double[] x;
    private double[] y;
    public DataSet() {
        // (−3,7.5),(−2,3),(−1,0.5),(0,1),(1,3),(2,6),(3,14)
        x = new double[]{-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
        y = new double[]{7.5,3.0,7.5,1.0,3.0,6.0,14.0};
        // x= new double[]{2,4,6};
        // y = new double[]{3,6,4};
    }
    public double[] getX() {
        return x;
    }


    public double[] getY() {
        return y;
    }
}

class DiscreteMaths {
    public DiscreteMaths() {}

    // funcion de media
    public double mean(double[] data) {
        double sum = 0;
    
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
    
        double mean = sum / data.length;
        return mean;
    }
    
    // sumatoria de x1
    public double sum_X(double[] x) {
        double totalx = 0;

        for (int i = 0; i < x.length; i++) {
            totalx += x[i];
        }
        return totalx;
    }

    // sumatoria de y 
    public double sum_Y(double[] y) {
        double totalY = 0;

        for (int i = 0; i < y.length; i++) {
            totalY += y[i];
        }
        return totalY;
    }
    // sumXY
    public double sum_XY(double[] x,double[] y) {
        double totalXY = 0;

        for (int i = 0; i < y.length; i++) {
            totalXY += (x[i]*y[i]);
        }
        return totalXY;
    }
    // sumX2Y
    public double sum_X2Y(double[] x,double[]y) {
        double totalX2Y = 0;

        for (int i = 0; i < x.length; i++) {
            totalX2Y += ((x[i]*x[i])*y[i]);
        }
        return totalX2Y;
    }
    // sumXX 
    public double sum_XX(double[] x) {
        double totalXX = 0;

        for (int i = 0; i < x.length; i++) {
            totalXX += (x[i]*x[i]);
        }
        return totalXX;
    }
    // sumXX2 
    public double sum_XX2(double[] x) {
        double totalXX2 = 0;

        for (int i = 0; i < x.length; i++) {
            totalXX2 += (x[i]*(x[i]*x[i]));
        }
        return totalXX2;
    }
    // sumx2x2 
    public double sum_X2X2(double[] x) {
        double totalX2X2 = 0;

        for (int i = 0; i < x.length; i++) {
            totalX2X2 += ((x[i]*x[i])*(x[i]*x[i]));
        }
        return totalX2X2;
    }

    // sumx2x2 
    public double sum_X2(double[] x) {
        double totalX2 = 0;

        for (int i = 0; i < x.length; i++) {
            totalX2 += (x[i]*x[i]);
        }
        return totalX2;
    }
    // Σ x 3  	
    public double sum_X3(double[] x) {
        double totalX3 = 0;

        for (int i = 0; i < x.length; i++) {
            totalX3 += (x[i]*x[i]*x[i]);
        }
        return totalX3;
    }
    // Σ x 4
    public double sum_X4(double[] x) {
        double totalX4 = 0;

        for (int i = 0; i < x.length; i++) {
            totalX4 += (x[i]*x[i]*x[i]*x[i]);
        }
        return totalX4;
    }

}

class SimpleLinear {
    public static void main(String[] args) {
        DataSet dataset = new DataSet();
		DiscreteMaths discretemaths= new DiscreteMaths();
        double n       = dataset.getX().length;
        double sumX    = discretemaths.sum_X(dataset.getX());
        double sumY    = discretemaths.sum_Y(dataset.getY());
        double sumX2   = discretemaths.sum_X2(dataset.getX());
        double sumX3   = discretemaths.sum_X3(dataset.getX());
        double sumX4   = discretemaths.sum_X4(dataset.getX());
        // Σ x2  x2  = [ Σ x 4 ] - [ ( Σ x 2 )2 / n ]
        double sumX2X2 = (sumX4)-(((sumX2*sumX2)/n));
        // Σ x x2 = [ Σ x 3 ] - [ ( Σ x 2 * Σ x ) / n ]
        double sumXX2  = (sumX3)-((sumX2*sumX)/n);
        // Σ x x = [ Σ x 2 ] - [ ( Σ x )2 / n ]
        double sumXX   = (sumX2)-((sumX*sumX)/n);
        // Σ x2 y  = [ Σ x 2 y] - [ ( Σ x 2 * Σ y ) / n ]
        double sumX2Y  = (discretemaths.sum_X2Y(dataset.getX(), dataset.getY())-((sumX2*sumY)/n));
        // Σ x y = [ Σ x y ] - [ ( Σ x  *  Σ y ) / n ]
        double sumXY   = (discretemaths.sum_XY(dataset.getX(),dataset.getY()))-((sumX*sumY)/n);
        // a = { [ Σ x2 y * Σ xx ] - [Σ xy * Σ xx2 ] } /  { [ Σ xx * Σ x2x 2] - [Σ xx2 ]2 }
        double a = ((sumX2Y*sumXX)-(sumXY*sumXX2))/((sumXX*sumX2X2)-(sumXX2*sumXX2));
        // b = { [ Σ xy * Σ x2x2 ] - [Σ x2y * Σ xx2 ] } /  { [ Σ xx * Σ x2x 2] - [Σ xx2 ]2 }
        double b = (((sumXY*sumX2X2)-(sumX2Y*sumXX2))/((sumXX*sumX2X2)-(sumXX2*sumXX2)));
        // c =  [ Σ y / n ] - { b *  [ Σ x / n ] } -  { a * [ Σ x 2  / n ]  }
        double c = (sumY/n)-(b*(sumX/n))-(a*(sumX2/n));
        
        System.out.println("c: "+c);
        System.out.println("b: "+b);
        System.out.println("a: "+a);
 
        // SSE=∑(yi−axi2−bxi−c)2 
        //  SST=∑(yi−y¯)2
        double meanY = discretemaths.mean(dataset.getY());
        double sse = 0;
        double sst = 0;
        for (int i = 0; i < dataset.getX().length; i++) {
            // Quadratic Regression Equation y = a x^2 + b x + c
            double yPred = a*(dataset.getX()[i]*dataset.getX()[i])+ b *dataset.getX()[i]+c;
            sse += (dataset.getY()[i] - yPred)*(dataset.getY()[i] - yPred);
            sst += (dataset.getY()[i] - meanY)*(dataset.getY()[i] - meanY);
            System.out.println("Entrada X: "+dataset.getX()[i]+" Predeccion Y: "+yPred);
        }

        double r2 = 1 - sse/sst;
        System.out.println("R2: " + r2);
        // R2=1−SSE/SST
    }
}