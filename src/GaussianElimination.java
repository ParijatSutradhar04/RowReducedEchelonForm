import java.util.*;

public class GaussianElimination {
    double [][]matrix;

    GaussianElimination(int row, int column){
        this.matrix = new double[row][column];
    }

    void rowEchelonForm(){
        int row=matrix.length;
        int column=matrix[0].length;

        for (int k=0; k<Math.min(row,column)-1; k++){
            for (int i=k+1; i<row; i++){
                double factor = -(matrix[i][k]/matrix[k][k]);
                for (int j=k; j<column; j++){
                    matrix[i][j]=matrix[i][j]+matrix[k][j]*factor;
                }
            }
        }

        for (int i=0; i<row; i++){
            double x = matrix[i][i];
            if (x!=0){
                for (int j=i; j<column; j++){
                    matrix[i][j]/=x;
                }
            }
        }

        System.out.println("Row Echelon Form:\n"+Arrays.deepToString(matrix));
    }

    boolean matrixCorrection(){
        double []list;
        for (int i=0; i<matrix.length; i++){
            if (matrix[i][i]==0){
                list=matrix[i];
                for (int j=i+1; j<matrix.length;j++){
                    if (matrix[j][i]!=0){
                        matrix[i]=matrix[j];
                        matrix[j]=list;
                        break;
                    }
                }
            }
        }
        for (int i=0; i<matrix.length;i++){
            if (matrix[i][i]==0){
                return false;
            }
        }
        return true;
    }

    public static void main (String []args){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter the size of the matrix: ");
        String size=sc.nextLine();
        int row=Integer.parseInt(size.substring(0, size.indexOf('*')).strip());
        int column=Integer.parseInt(size.substring(size.indexOf('*')+1).strip());

        GaussianElimination obj = new GaussianElimination(row,column);

        MatrixInputGUI gui = new MatrixInputGUI(row,column);
        // Wait for the user to input the matrix data
        while (gui.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Get the matrix data from the GUI
        obj.matrix= gui.getMatrix();

        if (obj.matrixCorrection()){
            System.out.println(Arrays.deepToString(obj.matrix));
            obj.rowEchelonForm();
        }
        else{
            System.out.println("CANNOT BE REDUCED TO RREF");
        }
    }
}