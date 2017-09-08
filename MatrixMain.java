package projectSec1A;

import java.io.IOException;

public class MatrixMain
{
	public static void main(String[] args) throws IOException
	{
		Matrix matrices = new Matrix();
		matrices.userInput();
		matrices.add();
		matrices.product();
		matrices.transpose();
		matrices.determinant();
		matrices.coFactor();
		matrices.inverse();
		matrices.diagonalA();
	}//end main
}//end matrixmain class
