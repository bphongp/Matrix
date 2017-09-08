package projectSec1A;

import java.io.*;
/*This program will do calculations for matrices
 *from matrix multiplication, transpose, addition, and inverse
 */
public class Matrix
{
	private int row,column;
	private double matrixA[][], matrixB[][], matrixC[][];
	private String rowString, colString;
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	private String file2 = "matrix2.txt", file1 = "matrix1.txt",
		file3 = "matrix3.txt",fileCofactor="fileCofactor.txt";
	private File matrixCofactor = new File(fileCofactor);
	private File matrixFile3 = new File(file3);
	private File matrixFile1 = new File(file1);
	private File matrixFile2 = new File(file2);
	private RandomAccessFile rFile1, rFile2;
	public Matrix()
	{
		
	}//default constructor
	public Matrix(double matrixA[][], double matrixB[][])
	{
		this.matrixA = matrixA;
		this.matrixB = matrixB;
	}//constructor takes in argument
	public void userInput() throws IOException
	{
		rFile1 = new RandomAccessFile(matrixFile1, "rw");//write to file
		rFile2 = new RandomAccessFile(matrixFile2, "rw");//write to file
		if(!matrixFile1.exists())//check to see existence of files
			matrixFile1.createNewFile();//create file if not exist
		if(!matrixFile2.exists())
			matrixFile2.createNewFile();
		
		inputRandC();
		matrixA = new double[row][column];
		matrixACreation();
		inputRandC();
		matrixB = new double[row][column];
		matrixBCreation();
		matrixC = new double[row][column];
	}//end user input method
	private void inputRandC() throws IOException
	{
		System.out.println("Please enter the amount of rows for the matrix");
		rowString =br.readLine();
		row = Integer.valueOf(rowString).intValue();
		System.out.println("Please enter the amount of columns for the matrix");
		colString =br.readLine();
		column = Integer.valueOf(colString).intValue();
	}//end inputRandC method
	private void matrixACreation() throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(matrixFile1, "rw");//read file
		System.out.println("Please enter numbers for first Matrix");
		String matrixEleString;
		double matrixElement, matrix1;
		for(int h = 0; h<matrixA.length; h++)
		{
			for(int w = 0; w<matrixA[h].length; w++)
			{
				matrixEleString = br.readLine();//read as string
				matrixElement = Double.parseDouble(matrixEleString);//convert to int
				rFile1.writeDouble(matrixElement);//write to file
				matrixA[h][w] = matrixElement;//write to double array
			}//end inner loop
		}//for loop reads each entrance to array
		for(int h= 0; h<matrixA.length;h++)
		{
			for(int w = 0; w<matrixA[h].length; w++)
			{
				matrix1 = raf.readDouble();//read from file
				System.out.print(matrix1+ " ");//output file
			}//end inner for loop
			System.out.println();
		}//end four loop output file
		raf.close();
		rFile1.close();
	}//end matrixACreation
	private void matrixBCreation() throws IOException
	{
		RandomAccessFile raf2 = new RandomAccessFile(matrixFile2, "rw");//read file
		System.out.println("Please enter numbers for second Matrix");
		String matrixEleString;
		double matrixElement, matrix2;
		for(int h = 0; h<matrixB.length; h++)
		{
			for(int w = 0; w<matrixB[h].length; w++)
			{
				matrixEleString = br.readLine();//read as string
				matrixElement = Double.parseDouble(matrixEleString);//convert to int
				rFile2.writeDouble(matrixElement);//write to file
				matrixB[h][w] = matrixElement;//write to double array
			}//end inner loop
		}//for loop reads each entrance to array
		for(int h= 0; h<matrixB.length;h++)
		{
			for(int w = 0; w<matrixB[h].length; w++)
			{
				matrix2 = raf2.readDouble();//read from file
				System.out.print(matrix2+ " ");//output file
			}//end inner for loop
			System.out.println();
		}//end for loop output file
		raf2.close();
		rFile2.close();
	}//end matrixBCreation
	public void add() throws IOException
	{
		for(int i =0; i<row;i++)
		{
			for(int j=0; j<column; j++)
			{
				matrixC[i][j]= matrixA[i][j]+matrixB[i][j];
			}//end inner for loop
		}//end for loop that
		System.out.println("Sum Matrix");
		outputResult();
	}//end add method
	public void product() throws IOException
	{
		for(int i =0; i<row;i++)
		{
			for(int k=0; k<row; k++)
			{
				double product=0;
				for(int j=0; j<row;j++)
				{
					product+= matrixA[i][j]*matrixB[j][k];
					matrixC[i][k]=product;
					if(product==0)
					{
						product=-product;
					}//end if statement
				}//end for loop j
			}//end for loop k
		}//end for loop i
		System.out.println("Product Matrix");
		outputResult();
	}//end method
	public void transpose() throws IOException
	{
		String fileTrans="fileTranspose.txt";
		File matrixTranspose = new File(fileTrans);
		RandomAccessFile raf = new RandomAccessFile(matrixTranspose, "rw");//read file
		RandomAccessFile write = new RandomAccessFile(matrixTranspose, "rw");//write to new file
		double matrixProduct;
		double matrixT[][]=new double[row][column];
		for(int i =0; i<row;i++)
		{
			for(int j=0; j<column; j++)
			{
				matrixT[i][j]= matrixC[j][i];
			}//end inner for loop
		}//end for loop that
		System.out.println("Transpose Matrix");
		for(int i =0; i<matrixC.length;i++)
		{
			for(int j=0;j<matrixC[i].length;j++)
			{
				write.writeDouble(matrixT[i][j]);//write to file
				matrixProduct =raf.readDouble();//read from file
				System.out.print(matrixProduct+" ");
			}//end for loop j
			System.out.println();
		}//end for loop i
		raf.close();
		write.close();
	}//end transpose method
	public void determinant() throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(matrixCofactor, "rw");//read file
		double determinant=0,cofactor;
		for(int i = 0; i<row;i++)
		{
			for(int j=0;j<row;j++)
			{
				cofactor=raf.readDouble();
				if(i==0)//choose a random column
					determinant+=matrixC[0][j]*cofactor;
			}//end for loop j
		}//end for loop i
		System.out.println("Determinant "+determinant);
		raf.close();
	}//end determinant method
	public void coFactor() throws IOException
	{
		System.out.println("Matrix C");
		outputResult();
		RandomAccessFile raf = new RandomAccessFile(matrixCofactor, "rw");//read file
		RandomAccessFile write = new RandomAccessFile(matrixCofactor, "rw");//write to new file
		double cofactor[][]=new double[row][column];
		double first,second, matrixP;
		for (int i= 0; i < row; i++)
		{
			for (int j = 0; j< column; j++)
			{
				first = matrixC[(i+1)%row][(j+1)%column]*
						matrixC[(i+2)%row][(j+2)%column];
				second =matrixC[(i+1)%row][(j+2)%column]*
						matrixC[(i+2)%row][(j+1)%column];
				cofactor[i][j]= (first-second);
			}//end for loop for column
		}//end for loop for row
		System.out.println("Cofactor Matrix");
		for(int i =0; i<row;i++)
		{
			for(int j=0; j<column; j++)
			{
				write.writeDouble(cofactor[i][j]);//write to file
				matrixP=raf.readDouble();//read from file
				System.out.print(matrixP+" ");
			}//end inner for loop
			System.out.println();
		}//end for loop that
		raf.close();
		write.close();
	}//end cofactor method
	public void inverse() throws IOException
	{
		String matrixM = "matrixMult.txt";
		File matrixmult = new File(matrixM);
		RandomAccessFile raf = new RandomAccessFile(matrixFile1, "rw");//write file
		RandomAccessFile raf1 = new RandomAccessFile(matrixFile1, "rw");//read file
		RandomAccessFile rafpr = new RandomAccessFile(matrixmult, "rw");//write file
		RandomAccessFile rafpr1 = new RandomAccessFile(matrixmult, "rw");//read file
		double determinantA=0,first, second, inverse;
		double cofactorA[][] = new double[row][column];
		double matrixcoT[][]= new double[row][column];
		double inverseA[][] = new double[row][column];
		double productAB[][]= new double[row][1];
		for(int i = 0; i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				first = matrixA[(i+1)%row][(j+1)%column]*
						matrixA[(i+2)%row][(j+2)%column];
				second =matrixA[(i+1)%row][(j+2)%column]*
						matrixA[(i+2)%row][(j+1)%column];
				cofactorA[i][j]= (first-second);//cofactor of A
				if(i==0)//choose a random column
					determinantA+=matrixA[0][j]*cofactorA[i][j];
			}//end for loop j
		}//end for loop i
		System.out.println("determinant "+determinantA);
		System.out.println("Inverse of A");
		for(int i =0; i<row;i++)
		{
			for(int j=0; j<column;j++)
			{
				matrixcoT[i][j]= cofactorA[j][i];//transpose the cofactor of A
				inverseA[i][j]= (1/determinantA)*matrixcoT[i][j];
				raf.writeDouble(inverseA[i][j]);
				inverse = raf1.readDouble();
				if(inverse==0)
				{
					inverse=-inverse;
				}//end if statement
				System.out.print(inverse+" ");
			}//end for loop j
			System.out.println();
		}//end for loop i 
		System.out.println("Multiply by first row of B");
		for(int i =0; i<row;i++)
		{
			for(int k=0; k<1; k++)
			{
				double product1=0;
				for(int j=0; j<row;j++)
				{
					product1+= inverseA[i][j]*matrixB[j][k];
					productAB[i][k]=product1;
				}//end for loop j
			}//end for loop k
		}//end for loop i
		for(int i =0; i<row;i++)
		{
			for(int k=0; k<1; k++)
			{
				rafpr.writeDouble(productAB[i][k]);
				System.out.print(rafpr1.readDouble()+" ");
			}//end for loop k
			System.out.println();
		}//end for loop i
		raf.close();
		raf1.close();
		rafpr.close();
		rafpr1.close();
	}//end inverse method
	public void diagonalA() throws IOException
	{
		String diagonalA = "diagonalA.txt";
		File diagonalAFile = new File(diagonalA);
		RandomAccessFile raf = new RandomAccessFile(diagonalAFile, "rw");//write file
		RandomAccessFile raf1 = new RandomAccessFile(diagonalAFile, "rw");//read file
		System.out.println("Diagonal Element of A");
		double diagonal[]= new double[row];
		double sumA=0, sumB=0,eleA=0, eleB=0,sd,diff=0,diffB=0;
		double diagonalB[]= new double[row];
		for(int i =0; i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				if(i==j)
				{
					diagonal[i]= matrixA[i][j];
					diagonalB[i]= matrixB[i][j];
				}//end if statement
			}//end for loop j
			sumA+=diagonal[i]+diagonalB[i];
			raf.writeDouble(diagonal[i]);
			System.out.print(raf1.readDouble()+" \t");
		}//end for loop i
		System.out.println();
		System.out.println("sum"+sumA);
		System.out.println("Diagonal Element of B");
		for(int i =0; i<row;i++)
		{ 
			raf.writeDouble(diagonalB[i]);
			System.out.print(raf1.readDouble()+ " \t");
		}//end for loop i 
		System.out.println();
		double ave = (sumB+sumA)/(row+row);
		System.out.println("average: "+ave);
		for(int i=0;i<row;i++)
		{
			eleA=diagonal[i]-ave;
			eleB= diagonalB[i]-ave;
			diff+=Math.pow(eleA, 2);
			diffB+=Math.pow(eleB, 2);
		}//end for loop i
		sd=(diff+diffB)/(row+row);//calculate standard dev
		System.out.println("Standard Deviation " +sd);
		raf.close();
		raf1.close();
	}//end diagonal
	private void outputResult() throws IOException
	{
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
			}//end for loop j
			System.out.println();
		}//end for loop i
		raf3.close();
		writeC.close();
	}//end for loop outputResults
}