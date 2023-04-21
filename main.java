
class DataSet {
    private float[] x1;
    private float[] x2;
    private float[] y;

    public DataSet() {
        x1 = new float[]{60,62,67,70,71,72,75,78};
        x2 = new float[]{22,25,24,20,15,14,14,11};
        y = new float[]{140,155,159,179,192,200,212,215};
    }
    public float[] getx1() {
        return x1;
    }

    public float[] getx2() {
        return x2;
    }

    public float[] getY() {
        return y;
    }
}

class DiscreteMaths {
    public DiscreteMaths() {}

    // funcion de media
    public float mean(float[] data) {
        float sum = 0;
    
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
    
        float mean = sum / data.length;
        return mean;
    }
    
    // sumatoria de x1
    public float sumx(float[] x) {
        float totalx = 0;

        for (int i = 0; i < x.length; i++) {
            totalx += x[i];
        }
        return totalx;
    }

    // sumatoria de y 
    public float sumY(float[] y) {
        float totalY = 0;

        for (int i = 0; i < y.length; i++) {
            totalY += y[i];
        }
        return totalY;
    }

    // sumatorias de x1 por y
    public float sumXY(float[] x,float[] y) {
        float totalXY = 0;

        for (int i = 0; i < y.length; i++) {
            totalXY += x[i]*y[i];
        }
        return totalXY;
    }

    // sumatorias de x1 por y
    public float sumx2Y(float[] x2,float[] y) {
        float totalx2Y = 0;

        for (int i = 0; i < y.length; i++) {
            totalx2Y += x2[i]*y[i];
        }
        return totalx2Y;
    }

    // x al cuadrado
    public float sum_xcuadrado(float[] x) {
        float totalx = 0;

        for (int i = 0; i < x.length; i++) {
            totalx += (x[i]*x[i]);
        }
        return totalx;
    }

    // sumatorias de x1 por x2
    public float sumX1X2(float[] x1,float[] x2) {
        float totalX1X2 = 0;
        for (int i = 0; i < x1.length; i++) {
            totalX1X2 += x1[i]*x2[i];
            
        }
        return totalX1X2;
    }

}

class SimpleLinear {
    public static void main(String[] args) {
        DataSet dataset = new DataSet();
		DiscreteMaths discretemaths= new DiscreteMaths();
        
        float n         = dataset.getx1().length;
        float sumx1     = discretemaths.sumx(dataset.getx1());
        float sumx2     = discretemaths.sumx(dataset.getx2());

        float sumY      = discretemaths.sumY(dataset.getY());
        // ΣX1y – (ΣX1Σy) / n
        float sumx1Y    = discretemaths.sumXY(dataset.getx1(),dataset.getY())-(sumx1*sumY)/n;
        // ΣX12 – (ΣX1)2 / n
        float sumX1_2    = discretemaths.sum_xcuadrado(dataset.getx1()) - (sumx1*sumx1)/n;
        

        // ΣX22 – (ΣX2)2 / n 
        float sumX2_2    = discretemaths.sum_xcuadrado(dataset.getx2()) - (sumx2*sumx2)/n;

        // ΣX2y – (ΣX2Σy) / n
        float sumX2Y     = discretemaths.sumXY(dataset.getx2(),dataset.getY())-(sumx2*sumY)/n;
        // ΣX1X2 – (ΣX1ΣX2) / n
        float sumX1X2    = discretemaths.sumX1X2(dataset.getx1(),dataset.getx2())-(sumx1*sumx2)/n;

        // Obtencion d beta 1 [(Σx22)(Σx1y)  – (Σx1x2)(Σx2y)]  / [(Σx12) (Σx22) – (Σx1x2)2]
        float b1 = (((sumX2_2)*(sumx1Y))-((sumX1X2)*(sumX2Y)))/(((sumX1_2)*(sumX2_2))-(sumX1X2*sumX1X2));
        // Obtencion de beta 2 [(Σx12)(Σx2y)  – (Σx1x2)(Σx1y)]  / [(Σx12) (Σx22) – (Σx1x2)2]
        float b2 = (((sumX1_2)*(sumX2Y))-((sumX1X2)*(sumx1Y)))/(((sumX1_2)*(sumX2_2))-(sumX1X2*sumX1X2));
        // Obtencion de beta 0  b0 is: y – b1X1 – b2X2
        float b0 = discretemaths.mean(dataset.getY()) - b1*discretemaths.mean(dataset.getx1()) - b2*discretemaths.mean(dataset.getx2());

        System.out.println("Entrada b0: "+b0+" Entrada b1: "+b1+" Entrada b2: "+b2);
        
        float yMean = discretemaths.mean(dataset.getY());
        float sst = 0;
        float sse = 0;
        
        for (int i = 0; i < dataset.getx1().length; i++) {
            // ŷ = b0 + b1*x1 + b2*x2
            float yPred = b0 + b1*dataset.getx1()[i] + b2*dataset.getx2()[i];
            System.out.println("Entrada x1: "+dataset.getx1()[i]+" Entrada x2: "+dataset.getx2()[i]+" Predeccion Y: "+yPred);
            float yActual = dataset.getY()[i];
            
            sst += (yActual - yMean) * (yActual - yMean);
            sse += (yActual - yPred) * (yActual - yPred);
        }
        
        float rSquared = sse / sst;
        
        System.out.println("R^2: " + rSquared);
        



    }
}