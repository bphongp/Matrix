package projectSec1AA;

import java.io.*;

import projectSec1A.Matrix;

public class MatrixMain1
{
	private static Matrix1 matrixA = new Matrix1();
	private static Matrix1 matrixB = new Matrix1();
	private static double a[][],b[][],matrix1[][]=new double[3][3],
			matrix2[][]=new double[3][3], matrix3[][];
	private static double matrixC[][] = new double[3][3];
	private static double cofactor[][]=new double[3][3];
	private static double matrixT[][]=new double[3][3];
	private static double determinant=0;
	private static double inverseA[][] = new double[3][3];
	private static int t;
	private static String fileCofactor="fileCofactor.txt";
	private static File matrixCofactor = new File(fileCofactor);
	private static String mA="matrixA.txt";
	private static File matA = new File(mA);
	private static String mB="matrixB.txt";
	private static File matB = new File(mB);
	public static void main(String[] args) throws IOException
	{
		matrixA.userInput1();
		matrixA.getFile();
		matA = matrixA.getFile();
		RandomAccessFile rFile1 = new RandomAccessFile(matA, "rw");//write to file
		a=new double[matrixA.getRow()][matrixA.getColumn()];
		double matrix1read, matrix2read;
		for(int h= 0; h<matrixA.getRow();h++)
		{
			for(int w = 0; w<matrixA.getColumn(); w++)
			{
				matrix1read =rFile1.readDouble();
				System.out.print(matrix1read+ " ");//output file
				a[h][w]=matrix1read;
			}//end inner for loop
			System.out.println();
		}//end four loop output file
		rFile1.close();
		matrixB.userInput1();
		matrixB.getFile();
		matB = matrixB.getFile();
		RandomAccessFile rFile2 = new RandomAccessFile(matB, "rw");
		b = new double[matrixB.getRow()][matrixB.getColumn()];
		for(int h= 0; h<matrixB.getRow();h++)
		{
			for(int w = 0; w<matrixB.getColumn(); w++)
			{
				matrix2read = rFile2.readDouble();
				System.out.print(matrix2read+ " ");//output file
				b[h][w]=matrix2read;
				matrix1[h][w]=a[h][w];
				matrix2[h][w]=b[h][w];
			}//end inner for loop
			System.out.println();
		}//end four loop output file
		rFile2.close();
		add();
		t= matrixA.getRow();
		product();
		transpose();
		coFactor();
		determinant();
		for(int i=0; i<matrixA.getRow();i++)
		{
			for(int j=0; j<matrixA.getColumn();j++)
			{
				matrixC[i][j]=a[i][j];
				System.out.print(matrixC[i][j]+" ");
			}
			System.out.println();
		}
		coFactor();
		determinant();
		inverse();
		for(int i=0; i<matrixA.getRow();i++)
		{
			for(int j=0; j<matrixA.getColumn();j++)
			{
				matrix1[i][j]=inverseA[i][j];
				System.out.print(matrix1[i][j]+" ");
			}
			System.out.println();
		}
		t=1;
		product();
	}
	private static void add() throws IOException
	{
		for(int i =0; i<matrixA.getRow();i++)
		{
			for(int j=0; j<matrixA.getColumn(); j++)
			{
				matrixC[i][j]= matrix1[i][j]+matrix2[i][j];
			}//end inner for loop
		}//end for loop that
		System.out.println("Sum Matrix");
		outputResult();
	}
	private static void product() throws IOException
	{
		for(int i =0; i<3;i++)
		{
			for(int k=0; k<t; k++)
			{
				double product=0;
				for(int j=0; j<3;j++)
				{
					product+= matrix1[i][j]*matrix2[j][k];
					matrixC[i][k]=product;
				}
			}
		}
		System.out.println("Product Matrix");
		outputResult();
	}//end method
	private static void transpose() throws IOException
	{
		for(int i =0; i<3;i++)
		{
			for(int j=0; j<3; j++)
			{
				matrixT[i][j]= matrixC[j][i];
			}//end inner for loop
		}//end for loop that
		System.out.println("Transpose Matrix");
		String fileTrans="fileTranspose.txt";
		File matrixTranspose = new File(fileTrans);
		RandomAccessFile raf = new RandomAccessFile(matrixTranspose, "rw");//read file
		RandomAccessFile write = new RandomAccessFile(matrixTranspose, "rw");//write to new file
		double matrixProduct;
		for(int i =0; i<matrixC.length;i++)
		{
			for(int j=0;j<matrixC[i].length;j++)
			{
				write.writeDouble(matrixT[i][j]);//write to file
				matrixProduct =raf.readDouble();//read from file
				System.out.print(matrixProduct+" ");
			}
			System.out.println();
		}
		raf.close();
		write.close();
	}
	private static void determinant() throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(matrixCofactor, "rw");//read file
		double cofactor;
		for(int i = 0; i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				cofactor=raf.readDouble();
				if(i==0)//choose a random column
					determinant+=matrixC[0][j]*cofactor;
			}
		}
		System.out.println("Determinant "+determinant);
		raf.close();
	}
	private static void coFactor() throws IOException
	{
		System.out.println("Matrix C");
		outputResult();
		RandomAccessFile raf = new RandomAccessFile(matrixCofactor, "rw");//read file
		RandomAccessFile write = new RandomAccessFile(matrixCofactor, "rw");//write to new file
		double first,second;
		for (int i= 0; i < 3; i++)
		{
			for (int j = 0; j< 3; j++)
			{
				first = matrixC[(i+1)%matrixA.getRow()][(j+1)%matrixA.getColumn()]*
						matrixC[(i+2)%matrixA.getRow()][(j+2)%matrixA.getColumn()];
				second =matrixC[(i+1)%matrixA.getRow()][(j+2)%matrixA.getColumn()]*
						matrixC[(i+2)%matrixA.getRow()][(j+1)%matrixA.getColumn()];
				cofactor[i][j]= (first-second);
			}//end for loop for column
		}//end for loop for row
		System.out.println("Cofactor Matrix");
		double matrixP;
		for(int i =0; i<3;i++)
		{
			for(int j=0; j<3; j++)
			{
				write.writeDouble(cofactor[i][j]);//write to file
				matrixP=raf.readDouble();//read from file
				System.out.print(matrixP+" ");
			}//end inner for loop
			System.out.println();
		}//end for loop that
		raf.close();
		write.close();
	}
	private static void inverse() throws IOException
	{
		File matrixInverse= new File("matrixInverse.txt");
		RandomAccessFile raf = new RandomAccessFile(matrixInverse, "rw");//write file
		RandomAccessFile raf1 = new RandomAccessFile(matrixInverse, "rw");//read file
		System.out.println("Inverse of A");
		for(int i =0; i<3;i++)
		{
			for(int j=0; j<3;j++)
			{
				matrixT[i][j]= cofactor[j][i];//transpose the cofactor of A
				inverseA[i][j]= (1/determinant)*matrixT[i][j];
				raf.writeDouble(inverseA[i][j]);
				System.out.print(raf1.readDouble()+" ");
			}
			System.out.println();
		}
		raf.close();
		raf1.close();
	}
	private static void outputResult() throws IOException
	{
		String file3 = "matrixC.txt";
		File matrixFile3 = new File(file3);
		RandomAccessFile raf3 = new RandomAccessFile(matrixFile3, "rw");//read file
		RandomAccessFile writeC = new RandomAccessFile(matrixFile3, "rw");//write to new file
		double matrixProduct;
		for(int i =0; i<matrixC.length;i++)
		{
			for(int j=0;j<matrixC[i].length;j++)
			{
				writeC.writeDouble(matrixC[i][j]);//write to file
				matrixProduct =raf3.readDouble();
				System.out.print(matrixProduct+" ");
			}
			System.out.println();
		}
		raf3.close();
		writeC.close();
	}
	private static void diagonalA()
	{
		System.out.println("Diagonal Element of A");
		double diagonal[]= new double[3];
		double sumA=0;
		double diagonalB[]= new double[3];
		double sumB=0;
		for(int i =0; i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(i==j)
				{
					diagonal[i]= matrix1[i][j];
					diagonalB[i]= b[i][j];
				}
			}
		}
		for(int i =0; i<3;i++)
		{ 
			sumA+=diagonal[i]+diagonalB[i];
			System.out.println(diagonal[i]);
		}
		System.out.println("sum"+sumA);
		System.out.println("Diagonal Element of B");
		for(int i =0; i<3;i++)
		{ 
			System.out.println(diagonalB[i]);
		}
		double ave = (sumB+sumA)/(6);
		System.out.println("ave"+ave);
		double eleA=0, eleB=0,sd,diff=0,diffB=0;
		for(int i=0;i<3;i++)
		{
			eleA=diagonal[i]-ave;
			eleB= diagonalB[i]-ave;
			diff+=Math.pow(eleA, 2);
			diffB+=Math.pow(eleB, 2);
		}
		sd=(diff+diffB)/(6);
		System.out.println(sd);
	}
}
