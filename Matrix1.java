package projectSec1AA;

import java.io.*;
public class Matrix1
{
	//change everything to doulbe
	private int row,column;
	private double matrixA[][];
	private String rowString, colString;
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	private String file2 = "matrix2.txt";
	private String file1 = "matrix1.txt";
	private File matrixFile1 = new File(file1);
	private File matrixFile2 = new File(file2);
	private RandomAccessFile rFile1;
	public Matrix1()
	{
		
	}//default constructor
	public Matrix1(double matrixA[][])
	{
		this.matrixA = matrixA;
	}//constructor takes in argument
	public File getFile()
	{
		return matrixFile1;
	}
	private void setRow(int row)
	{
		this.row=row;
	}
	public int getRow()
	{
		return row;
	}
	private void setColumn(int column)
	{
		this.column=column;
	}
	public int getColumn()
	{
		return column;
	}
	public void userInput1() throws IOException
	{
		rFile1 = new RandomAccessFile(matrixFile1, "rw");//write to file
		if(!matrixFile1.exists())//check to see existence of files
			matrixFile1.createNewFile();//create file if not exist
		if(!matrixFile2.exists())
			matrixFile2.createNewFile();
		
		inputRandC();
		matrixA = new double[row][column];
		matrixACreation();
	}//end user input method
	private void inputRandC() throws IOException
	{
		System.out.println("Please enter the amount of rows for the matrix");
		rowString =br.readLine();
		row = Integer.valueOf(rowString).intValue();
		setRow(row);
		System.out.println("Please enter the amount of columns for the matrix");
		colString =br.readLine();
		column = Integer.valueOf(colString).intValue();
		setColumn(column);
	}
	private void matrixACreation() throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(matrixFile1, "rw");//read file
		System.out.println("Please enter numbers for first Matrix");
		String matrixEleString;
		double matrixElement;
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
		raf.close();
		rFile1.close();
	}
}