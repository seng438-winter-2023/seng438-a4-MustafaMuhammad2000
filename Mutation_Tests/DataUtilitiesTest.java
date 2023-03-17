package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest {
	private Mockery mockingContext;
	private Values2D values;
	private KeyedValues keyedValues;
	private KeyedValues keyedResult;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	mockingContext = new Mockery();
    	values = mockingContext.mock(Values2D.class);
    	keyedValues = mockingContext.mock(KeyedValues.class);
    }
	 
//      CalculateColumnTotal tests START
	 @Test
    // CalculateColumnTotal tests START
    //(MUTATION) Mocking a Values2D object and calculating column total of the first column
	 public void testCalculateColumnTotal_ColumnIndexInLowerBoundary(){
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 10.0, .000000001d);
	 }
	 
	 
	 @Test
	 //(MUTATION)Mocking a Values2D object and calculating column total of the matrix size-1 index 
	 public void testCalculateColumnTotal_ColumnIndexInUpperBoundary(){
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	             one(values).getValue(0, 1);
	             will(returnValue(5));
	             one(values).getValue(1, 1);
	             will(returnValue(5));

	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 1);
	     assertEquals(result, 10.0, .000000001d);
	 }
	 

	 @Test
	 //Mocking a Values2D object and calculating column total for an empty values2D object
	 public void testCalculateColumnTotal_EmptyDataTable() {
	     mockingContext.checking(new Expectations() {
	         {
	        	 one(values).getRowCount();
	             will(returnValue(0));
	             one(values).getColumnCount();
	             will(returnValue(0));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             
	             
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 0, 0);
	 }
	 
	 @Test
	 //Mocking a Values2D object and calculating column total for a column that has some null values
	 public void testCalculateColumnTotal_NullValuesInColumn() {
		//Mocking a Values2D object and calculating column total for a column which contains null values.
	     mockingContext.checking(new Expectations() {
	         {
	        	 allowing(values).getRowCount();
	             will(returnValue(2));
	             will(returnValue(2));
	             allowing(values).getValue(0,0);
	             will(returnValue(null));
	             allowing(values).getValue(1,0);
	             will(returnValue(null));
	             allowing(values).getValue(0,1);
	             will(returnValue(null));
	             allowing(values).getValue(1,1);
	             will(returnValue(null)); 
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 0, 0);
	 }
	 
	 @Test
	 public void testCalculateColumnTotal_NegativeRowCount() {
			//Mocking a Values2D object and calculating column total for values2DObject with negative rows. 
	     mockingContext.checking(new Expectations() {
	         {
	        	 one(values).getRowCount();
	             will(returnValue(-1));  
	             one(values).getValue(0,-1);
	             will(returnValue(null));
	             one(values).getValue(1,-1);
	             will(returnValue(null));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, -1);
	     assertEquals(result, 0, 0);
	 }
	 
	 @Test(expected = IllegalStateException.class)
	 //Mocking a Values2D object and calculating column total for a non-numeric Values2D object.
	 public void testCalculateColumnTotal_NonNumericInput(){
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue("A"));
	             one(values).getValue(1, 0);
	             will(returnValue("B"));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 0, 0);
	 }
	 
    // CalculateColumnTotal tests END

	// CalculateColumnTotal Valid ROWS Test START

    @Test
    //For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with an empty validRows array, should return 0
    public void testCalculateColumnTotal_ValidRowsEmpty() {
        final int[] validRows = {};
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(5));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    //(MUTATION) For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with a validRows array that's out of bounds of data matrix, should return 0
    public void testCalculateColumnTotal_ValidRowsOutOfBounds() {
        final int[] validRows = {5, 6, 7};
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(5));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    //(MUTATION) For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with one index in validRows, should return column total in that row
    public void testCalculateColumnTotal_ValidRowsOne() {
        final int[] validRows = {2};
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(5));
                one(values).getValue(2, 0);
                will(returnValue(2.0));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    //For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with multiple valid indices in validRows, 
    //should return column total in those rows
    public void testCalculateColumnTotal_ValidRowsMultiple() {
        final int[] validRows = {0, 2, 4};
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(5));
                one(values).getValue(0, 0);
                will(returnValue(1.0));
                one(values).getValue(2, 0);
                will(returnValue(2.0));
                one(values).getValue(4, 0);
                will(returnValue(3.0));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals(6.0, result, 0.0001);
    }

    @Test
    //(MUTATION) For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with valid indices, however all values in the rows contain only null,
    //should return 0
    public void testCalculateColumnTotal_NullData() {
        final int[] validRows = {0, 1, 2, 3, 4};
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(5));
                one(values).getValue(0, 0);
                will(returnValue(null));
                one(values).getValue(1, 0);
                will(returnValue(null));
                one(values).getValue(2, 0);
                will(returnValue(null));
                one(values).getValue(3, 0);
                will(returnValue(null));
                one(values).getValue(4, 0);
                will(returnValue(null));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
        assertEquals(0.0, result, 0.0001);
    }
    
    @Test
    //For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with negative indices in validRows
    //should return 0
    public void testCalculateColumnTotal_NegativeIndexValidRows() {
        int[] validRows = {-1, -3, -5};
        
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(2)); 
                one(values).getValue(-1, 0);
                will(returnValue(null));
                one(values).getValue(-3, 0);
                will(returnValue(null));
                one(values).getValue(-5, 0);
                will(returnValue(null));
            }
        });

        double actualTotal = DataUtilities.calculateColumnTotal(values, 0, validRows);

        assertEquals(0.0, actualTotal, 0.0001);
    }

    @Test
    //For the function CalculateColumnTotal() with argument validRows
    //Tries to call the function with more rows then the data matrix has
    //should return the total of the rows which do exist
    public void testCalculateColumnTotal_MoreValidRowsThenAvailableRows() {
        int[] validRows = {0, 1, 2, 3, 4};
        
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(1.0));
                one(values).getValue(1, 0);
                will(returnValue(2.0));
                one(values).getValue(2, 0);
                will(returnValue(3.0));
            }
        });

        double actualTotal = DataUtilities.calculateColumnTotal(values, 0, validRows);

        assertEquals(6.0, actualTotal, 0.0001);
    }
    
	
	// CalculsteColumnTotal Valid Rows Test END

    // CalculateRowTotal tests START

	//(MUTATION) Testing original method calculateRowTotal()
    //This test will mock 4 columns for the first row and calculate the total number of values we pass in using will() and getValue() for the columns
    //Lastly we will use assertEquals() to determine the expected and actual results along with a small delta value for discrepancy allowance
    //This test will only test positive integers
    @Test
	public void testCalculateRowTotal_PositiveNumbers() {
		
    	mockingContext.checking(new Expectations() {
			{
			one(values).getColumnCount();
			will(returnValue(4));
			one(values).getValue(0, 0);
			will(returnValue(5));
			one(values).getValue(0, 1);
			will(returnValue(1));
			one(values).getValue(0, 2);
			will(returnValue(5));
			one(values).getValue(0, 3);
			will(returnValue(9));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals(result, 20, .000000001d);
	}
    
    //(MUTATION) Testing original method calculateRowTotal()
    //This test will mock 4 columns for the first row and calculate the total number of values we pass in using will() and getValue() for the columns
    //Lastly we will use assertEquals() to determine the expected and actual results along with a small delta value for discrepancy allowance
    //This test will only test negative integers
    @Test
	public void testCalculateRowTotal_NegativeNumbers() {
		mockingContext.checking(new Expectations() {
			{
			oneOf(values).getColumnCount();
			will(returnValue(4));
			oneOf(values).getValue(0, 0);
			will(returnValue(-5));
			oneOf(values).getValue(0, 1);
			will(returnValue(-1));
			oneOf(values).getValue(0, 2);
			will(returnValue(-5));
			oneOf(values).getValue(0, 3);
			will(returnValue(-9));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals(result, -20, .000000001d);
	}
    
    //Testing original method calculateRowTotal()
    //This test will mock 4 columns for the first row and calculate the total number of values we pass in using will() and getValue() for the columns
    //Lastly we will use assertEquals() to determine the expected and actual results along with a small delta value for discrepancy allowance
    //This test will contain a mix of positive and negative integers
    @Test
	public void testCalculateRowTotal_MixNumbers() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(4));
				oneOf(values).getValue(0, 0);
				will(returnValue(5));
				oneOf(values).getValue(0, 1);
				will(returnValue(-1));
				oneOf(values).getValue(0, 2);
				will(returnValue(-5));
				oneOf(values).getValue(0, 3);
				will(returnValue(9));
				}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals(result, 8, .000000001d);
	}
    
    //Testing original method calculateRowTotal()
    //This test will mock 1 column for the first row and calculate the total number of values we pass in using will() and getValue() for the columns
    //Lastly we will use assertEquals() to determine the expected and actual results along with a small delta value for discrepancy allowance
    //This test will check that the maximum possible value of an integer is accepted
    @Test
	public void testCalculateRowTotal_MaximumInteger() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(1));
				oneOf(values).getValue(0, 0);
				will(returnValue(Integer.MAX_VALUE));
				}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(result, Integer.MAX_VALUE, .000000001d);
	}
    
    //Testing original method calculateRowTotal()
    //This test will mock 1 column for the first row and calculate the total number of values we pass in using will() and getValue() for the columns
    //Lastly we will use assertEquals() to determine the expected and actual results along with a small delta value for discrepancy allowance
    //This test will check that the smallest possible value of an integer is accepted
    @Test
	public void testCalculateRowTotal_MinimumInteger() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(1));
				oneOf(values).getValue(0, 0);
				will(returnValue(Integer.MIN_VALUE));
				}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(result, Integer.MIN_VALUE, .000000001d);
	}
    
	//Testing original method calculateRowTotal()
    //This test will mock 2 columns for the first row and calculate the total number of values we pass in using will() and getValue() for the columns
    //Lastly we will use assertEquals() to determine the expected and actual results along with a small delta value for discrepancy allowance
    //This test will check that the method can return 0 if the rows only have 0's in them
    @Test
	public void testCalculateRowTotal_Return0() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(2));
				oneOf(values).getValue(0, 0);
				will(returnValue(0));
				oneOf(values).getValue(0, 1);
				will(returnValue(0));
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(result, 0, .000000001d);
	}
    
    //(MUTATION) Testing original method calculateRowTotal()
    //This test will mock 0 total columns which should be illegal
    //Therefore this test is determining that a null pointer exception is thrown
    @Test
    public void testCalculateRowTotal_WithoutValues() {
    	mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(0));
				oneOf(values).getValue(0, 0);
				will(throwException(new NullPointerException("null not permitted")));
				
			}
		});
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(result, 0, .000000001d);
	}
    
    //Testing original method calculateRowTotal()
    //This test will simply replace the calculation with a null value which should throw an exception
    @Test
    (expected = Exception.class)
	public void testCalculateRowTotal_InvalidInput_NULL() {
    	
		DataUtilities.calculateRowTotal(null, 0);
	}
    
    //Testing original method calculateRowTotal()
    //This test will mock 1 total column with a negative index which should be illegal
    //Therefore this test is determining that an InvalidParameterException exception is thrown
    @Test
    public void testCalculateRowTotal_NegativeIndex() {
    	mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(1));
				oneOf(values).getValue(-1, 0);
				will(returnValue(null));
				
			}
		});
		double result = DataUtilities.calculateRowTotal(values, -1);
		assertEquals(result, 0, .000000001d);
	}
    
    // CalculateRowTotal tests END

	// CalculateRowTotal Valid COLUMNS Test START
    
    //For the function CalculateRowTotal() with argument validCols
    //Tries to call the function with valid indices in validCols
    //should return the total of the columns 
	@Test
	public void testCalculateRowTotal_ValidValues() {
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(3.0));
				one(values).getValue(0, 1);
				will(returnValue(4.0));
			}
		});
		int[] validCols = {0, 1};
		assertEquals(7.0, DataUtilities.calculateRowTotal(values, 0, validCols), 0.000000001d);
	}

	
	@Test
    //(MUTATION) For the function CalculateRowTotal() with argument validCols
    //Tries to call the function with invalid indices in validCols
    //should return 0
	public void testCalculateRowTotal_InvalidColumn() {
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(3.0));
				one(values).getValue(0, 1);
				will(returnValue(null));
			}
		});
		int[] validCols = {3, 4};
		assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0, validCols), 0.000000001d);
	}
	
	@Test
    //(MUTATION) For the function CalculateRowTotal() with argument validCols
    //Tries to call the function with no indices in validCols
    //should return 0
	public void testCalculateRowTotal_EmptyData() {
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(0));
			}
		});
		int[] validCols = {};
		assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0, validCols), 0.000000001d);
	}
	
	@Test
    //For the function CalculateRowTotal() with argument validCols
    //Tries to call the function with one valid index in validCols
    //should return row total of that column
	public void testCalculateRowTotal_SingleColumn() {
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(3.0));
			}
		});
		int[] validCols = {0};
		assertEquals(3.0, DataUtilities.calculateRowTotal(values, 0, validCols), 0.000000001d);
	}
	
   @Test
   //For the function CalculateRowTotal() with argument validCols
   //Tries to call the function with valid indices in validCols, but the rows only contain null values
   //should return 0
    public void testCalculateRowTotal_NullValueInColumn() {
        // Tests every statement in the method at least once
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(2));
                one(values).getValue(1, 1);
                will(returnValue(null));
                one(values).getValue(1, 2);
                will(returnValue(null));

            }
        });

        int[] validCols = {1, 2};
        double total = DataUtilities.calculateRowTotal(values, 1, validCols);
        assertEquals(0, total, 0.000001);
    }
	   
    @Test
    //For the function CalculateRowTotal() with argument validCols
    //Tries to call the function with valid indices in validCols but mocking negative columns
    //should return 0
    public void testCalculateRowTotal_ColCountLessThanZero() {
        int row = 0;
        int[] validCols = new int[] { 0, 1 };
        
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); 
            will(returnValue(-1));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, row, validCols);
        
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    //(MUTATION) For the function CalculateRowTotal() with argument validCols
    //Tries to call the function with negative indices in validCols
    //should return 0
    public void testCalculateRowTotal_NegativeIndexValidCols() {
        int[] validCols = {-1, -3, -5};
        
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(2)); 
                one(values).getValue(0, -1);
                will(returnValue(null));
                one(values).getValue(0, -3);
                will(returnValue(null));
                one(values).getValue(0, -5);
                will(returnValue(null));
            }
        });

        double actualTotal = DataUtilities.calculateRowTotal(values, 0, validCols);

        assertEquals(0.0, actualTotal, 0.0001);
    }


	// CalculateRowTotal Valid COLUMNS Test END 
    
    // CreateNumberArray tests START

	@Test
	//Testing a 3-element array input, should output a length of 3
	public void testCreateNumberArray_ThreeElementArrayMatchingElementCount() {
		double[] data = {1.0, 2.5, 3.5};
		int result = DataUtilities.createNumberArray(data).length;
		assertEquals(3, result, .000000001d);
	}
	
	@Test
	//Testing a 1-element array, should output a length of 1
	public void testCreateNumberArray_OneElementArrayMatchingElementCount() {
		double[] data = {1.0};
		int result = DataUtilities.createNumberArray(data).length;
		assertEquals(1, result, .000000001d);
	}
	
	@Test
	//(MUTATION) Testing a 0-element array, should output a length of 0
	public void testCreateNumberArray_ZeroElementArrayMatchingElementCount() {
		double[] data = new double[0];
		int result = DataUtilities.createNumberArray(data).length;
		assertEquals(0, result, .000000001d);
	}
	
	@Test
	//Testing a positive 3-element array, should output a Number array with
	//the same values
	public void testCreateNumberArray_ThreePositiveElementArrayMatchingNumberValue() {
		double[] data = {1.0, 2.5, 3.5};
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals(1.0, result[0]);
		assertEquals(2.5, result[1]);
		assertEquals(3.5, result[2]);
		assertEquals(Double.class, result[0].getClass());
		assertEquals(Double.class, result[1].getClass());
		assertEquals(Double.class, result[2].getClass());
	}
	
	@Test
	//Testing a positive 1-element array, should output a Number array with
	//the same value
	public void testCreateNumberArray_OnePositiveElementArrayMatchingNumberValue() {
		double[] data = {2.5};
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals(2.5, result[0]);
		assertEquals(Double.class, result[0].getClass());
	}
	
	@Test
	//Testing a negative 3-element array, should output a Number array with
	//the same values
	public void testCreateNumberArray_ThreeNegativeElementArrayMatchingNumberValue() {
		double[] data = {-1.5, -2.6, -3.7};
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals(-1.5, result[0]);
		assertEquals(-2.6, result[1]);
		assertEquals(-3.7, result[2]);
		assertEquals(Double.class, result[0].getClass());
		assertEquals(Double.class, result[1].getClass());
		assertEquals(Double.class, result[2].getClass());
	}
	
	@Test
	//Testing a negative 1-element array, should output a Number array with
	//the same value
	public void testCreateNumberArray_OneNegativeElementArrayMatchingNumberValue() {
		double[] data = {-2.5};
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals(-2.5, result[0]);
		assertEquals(Double.class, result[0].getClass());
	}
	
	@Test
	//Testing a 0-element array, should throw InvalidParameterException
	public void testCreateNumberArray_ZeroElementArrayMatchingNumberValue() {
		double[] data = new double[0];
		Number [] result = DataUtilities.createNumberArray(data);
		assertEquals(0, result.length);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	//(MUTATION) Testing a null input, should throw InvalidParameterException
	public void testCreateNumberArray_NullParameter() {
		double[] data = null;
		DataUtilities.createNumberArray(data);
		
	}
	
    // CreateNumberArray tests END


    // CreateNumberArray2D tests Start
	
    //For the function createNumberArray2D()
    //Tries to call the function with null data
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_InputWithNullData() {
        DataUtilities.createNumberArray2D(null);
    }
    
    //For the function createNumberArray2D()
    //Tries to call the function with an empty 2D array
    @Test
    public void testCreateNumberArray2D_ArrayIsEmpty() {
        double[][] test_data = new double[0][0];
        Number[][] result = DataUtilities.createNumberArray2D(test_data);
        
        assertEquals(0, result.length);
    }
    
    //(MUTATION) For the function createNumberArray2D()
    //Tries to call the function with a 1x1 array
    @Test
    public void testCreateNumberArray2D_ArrayHasOnlyOneElement() {
        double[][] test_data = new double [1][1];
        test_data[0][0] = 100;
        Number[][] result = DataUtilities.createNumberArray2D(test_data);
        
        assertEquals(1, result.length);
        assertEquals(1, result[0].length);
        assertEquals(100.0, result[0][0]);
    }
    
    //For the function createNumberArray2D()
    //Tries to call the function with a 3x3 array
    @Test
    public void testCreateNumberArray2D_ArrayHasMultipleElements() {
        double[][] input = {{0.0, 2.5, 5.6}, {0.2, 4.1, 3.5}};
        Number[][] expected = {{0.0, 2.5, 5.6}, {0.2, 4.1, 3.5}};
        Number[][] actual =  DataUtilities.createNumberArray2D(input);
        for(int i = 0; i<input.length; i++) {
        	for (int j = 0; j<input[i].length; j++){
        		System.out.println(expected[i][j]+" "+actual[i][j]);
        	}
        }
        assertTrue(Arrays.deepEquals(expected, actual));
    }
    
    //For the function createNumberArray2D()
    //Tries to call the function with a 2d array with different lengths for each row
    @Test
    public void testCreateNumberArray2D_ArrayHasRowsOfDifferentLengths() {
        double[][] input = {{5.6}, {4.1, 3.5}};
        Number[][] expected = {{5.6}, {4.1, 3.5}};
        Number[][] actual = DataUtilities.createNumberArray2D(input);
        
        assertTrue(Arrays.deepEquals(expected, actual));
    }
    
    //For the function createNumberArray2D()
    //Tries to call the function with a 2d array containing negative elements
    @Test
    public void testCreateNumberArray2D_ArrayHasNegativeElements() {
        double[][] input = {{1.1, -3.3, -9.2}, {2.6, -3.3, -4.8}};
        Number[][] expected = {{1.1, -3.3, -9.2}, {2.6, -3.3, -4.8}};
        Number[][] actual = DataUtilities.createNumberArray2D(input);

        assertTrue(Arrays.deepEquals(expected, actual));
    }
    
    //For the function createNumberArray2D()
    //Tries to call the function with a 2d array containing the minimum and maximum double size
    @Test
    public void testCreateNumberArray2D_ArrayHasExtremelyLargeOrSmallElements() {
        double[][] input = {{Double.MAX_VALUE, Double.MIN_VALUE}, {Double.MIN_VALUE, Double.MAX_VALUE}};
        Number[][] expected = {{Double.MAX_VALUE, Double.MIN_VALUE}, {Double.MIN_VALUE, Double.MAX_VALUE}};
        Number[][] actual = DataUtilities.createNumberArray2D(input);

        assertTrue(Arrays.deepEquals(expected, actual));
    }

    // CreateNumberArray2D tests END
    
    // GetCumulativePercentages tests START
    
    //(MUTATION) For the function getCumulativePercentages()
    // Checks the output for a single row that will have 100% of the values in that row
    @Test
    public void getCumulativePercentages_SimpleValidInputOneRow() {
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyedValues).getItemCount();
    			will(returnValue(1));
    			allowing(keyedValues).getValue(0);
    			will(returnValue(1));
    			allowing(keyedValues).getKey(0);
				will(returnValue(0));
    			
    		}
    	});
    	
    	
    	KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);

    	assertEquals(1.0,res.getValue(0));
    	
    }
    
    //For the function getCumulativePercentages()
    // Checks the output for two rows that will have 0% of the values in the first row and 
    // 100% of the values in the last row
    @Test
    public void getCumulativePercentages_SimpleValidInputTwoRows() {
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyedValues).getItemCount();
    			will(returnValue(2));
    			allowing(keyedValues).getValue(0);
    			will(returnValue(0));
    			allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
    			will(returnValue(1));
    			allowing(keyedValues).getKey(1);
				will(returnValue(1));
    			
    		}
    	});
    	
    	
    	KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);

    	assertEquals(0.0,res.getValue(0));
    	assertEquals(1.0,res.getValue(1));
    	
    }
    
    //For the function getCumulativePercentages()
    // Checks the result of two rows that have zero as values in both rows
    @Test
    public void getCumulativePercentages_ZeroInputInputTwoRows() {
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyedValues).getItemCount();
    			will(returnValue(2));
    			allowing(keyedValues).getValue(0);
    			will(returnValue(0));
    			allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
    			will(returnValue(0));
    			allowing(keyedValues).getKey(1);
				will(returnValue(1));
    			
    		}
    	});
    	
    	
    	KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);

    	assertEquals(0.0 / 0.0,res.getValue(0));
    	assertEquals(0.0 / 0.0,res.getValue(1));
    	
    }
    
    //For the function getCumulativePercentages()
    //Checks the percentages of the cumulative values for an input of 4 rows with a value of 1 in each
    @Test
    public void getCumulativePercentages_SinpleInputFourRows() {
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyedValues).getItemCount();
    			will(returnValue(4));
    			allowing(keyedValues).getValue(0);
    			will(returnValue(1));
    			allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
    			will(returnValue(1));
    			allowing(keyedValues).getKey(1);
				will(returnValue(1));
				allowing(keyedValues).getValue(2);
    			will(returnValue(1));
    			allowing(keyedValues).getKey(2);
				will(returnValue(2));
				allowing(keyedValues).getValue(3);
    			will(returnValue(1));
    			allowing(keyedValues).getKey(3);
				will(returnValue(3));
    			
    		}
    	});
    	
    	
    	KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);

    	assertEquals(0.25,res.getValue(0));
    	assertEquals(0.5,res.getValue(1));
    	assertEquals(0.75,res.getValue(2));
    	assertEquals(1.0,res.getValue(3));
    	
    }
    
    //For the function getCumulativePercentages()
    //Checks the cumulative percentages of the values for a negative input and a positive input
    @Test
    public void getCumulativePercentages_InvalidNegativeInputTwoRows() {
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyedValues).getItemCount();
    			will(returnValue(2));
    			allowing(keyedValues).getValue(0);
    			will(returnValue(-1));
    			allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
    			will(returnValue(2));
    			allowing(keyedValues).getKey(1);
				will(returnValue(1));
    			
    		}
    	});
    	
    	
    	KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);

    	assertEquals(-1.0,res.getValue(0));
    	assertEquals(1.0,res.getValue(1));
    	
    }
    
    //For the function getCumulativePercentages()
    //Checks the cumulative percentages of the values for two very large values
    @Test
    public void getCumulativePercentages_LargeInputTwoRows() {
    	mockingContext.checking(new Expectations() {
    		{
    			allowing(keyedValues).getItemCount();
    			will(returnValue(2));
    			allowing(keyedValues).getValue(0);
    			will(returnValue(Double.MAX_VALUE));
    			allowing(keyedValues).getKey(0);
				will(returnValue(0));
				allowing(keyedValues).getValue(1);
    			will(returnValue(Double.MAX_VALUE));
    			allowing(keyedValues).getKey(1);
				will(returnValue(1));
    			
    		}
    	});
    	
    	
    	KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);
    	System.out.println(keyedValues.getItemCount());
    	assertEquals(0.0,res.getValue(0));
    	assertEquals(Double.NaN,res.getValue(1));
    	
    }
    
  //For the function getCumulativePercentages()
    //Checks the cumulative percentages of the values for null inputs
    @Test
    public void getCumulativePercentages_Null() {
        mockingContext.checking(new Expectations() {
            {
                allowing(keyedValues).getItemCount();
                will(returnValue(2));
                allowing(keyedValues).getValue(0);
                will(returnValue(null));
                allowing(keyedValues).getKey(0);
                will(returnValue(0));
                allowing(keyedValues).getValue(1);
                will(returnValue(null));
                allowing(keyedValues).getKey(1);
                will(returnValue(1));

            }
        });

        KeyedValues res = DataUtilities.getCumulativePercentages(keyedValues);
        System.out.println(keyedValues.getItemCount());
        assertEquals(Double.NaN,res.getValue(0));
        assertEquals(Double.NaN,res.getValue(1));
    }
    
    // GetCumulativePercentages tests END
    
    @Test
    public void clone_simpleInput() {
    	double[][] input = new double[1][1];
    	double[][] output = DataUtilities.clone(input);
    	assertEquals(input[0][0],output[0][0],0);
    	
    }
    //since double is a primitive type, you can't assign a null value to get 100% branch coverage
	//clone_1x2Input tests the simple base case if a 1x2 matrix using simple data values
    @Test
    public void clone_1x2Input() {
    	double[][] input = new double[1][2];
    	input[0][0]= 1;
    	input[0][1] = 1.5;
    	double[][] output = DataUtilities.clone(input);
    	assertEquals(input[0][0],output[0][0],0);
    	assertEquals(input[0][1],output[0][1],0);
    	
    }
    
	//clone_2x1Input tests the simple base case if a 2x1 matrix using simple data values
    @Test
    public void clone_2x1Input() {
    	double[][] input = new double[2][1];
    	input[0][0]= 1;
    	input[1][0] = 1.5;
    	double[][] output = DataUtilities.clone(input);
    	assertEquals(input[0][0],output[0][0],0);
    	assertEquals(input[1][0],output[1][0],0);
    	
    }
    
	//clone_2x2Input tests the simple base case if a 2x2 matrix using simple data values
    @Test
    public void clone_2x2Input() {
    	double[][] input = new double[2][2];
    	input[0][0]= 1;
    	input[0][1] = 1.5;
    	input[1][0]= 0;
    	input[1][1] = 2.5;
    	double[][] output = DataUtilities.clone(input);
    	assertEquals(input[0][0],output[0][0],0);
    	assertEquals(input[0][1],output[0][1],0);
    	assertEquals(input[1][0],output[1][0],0);
    	assertEquals(input[1][1],output[1][1],0);
    	
    }
    
	//clone_2x2NegativeInput tests the case if a 2x2 matrix using negative data values
    @Test
    public void clone_2x2NegativeInput() {
    	double[][] input = new double[2][2];
    	input[0][0]= -1;
    	input[0][1] = -1.5;
    	input[1][0]= 0;
    	input[1][1] = -2.5;
    	double[][] output = DataUtilities.clone(input);
    	assertEquals(input[0][0],output[0][0],0);
    	assertEquals(input[0][1],output[0][1],0);
    	assertEquals(input[1][0],output[1][0],0);
    	assertEquals(input[1][1],output[1][1],0);
    	
    }
    
    
    //equal_simpleInput tests the base case of a simple 1x1 2D array with default values
    @Test
    public void equal_simpleInput() {
    	double[][] input = new double[1][1];
    	double[][] output = new double[1][1];
    	assertEquals(true,DataUtilities.equal(input, output));
    	
    }
    
	//equal_NullInputs tests wether equal can handle null inputs
    @Test
    public void equal_NullInputs() {
    	assertEquals(true,DataUtilities.equal(null, null));
    	
    }
    
	//equal_BNullInput tests wether equal can handle a null input against a 2D array
    @Test
    public void equal_BNullInput() {
    	double[][] input = new double[1][1];
    	assertEquals(false,DataUtilities.equal(input, null));
    	
    }
    
	//equal_DifferentLengthInputs tests wether equal can differentiate 2D arrays of different lengths
    @Test
    public void equal_DifferentLengthInputs() {
    	double[][] input = new double[1][1];
    	double[][] output = new double[2][1];
    	assertEquals(false,DataUtilities.equal(input, output));
    	
    }
    
	//equal_DifferentInputs tests wether equal can differentiate 2D arrays of the same length containing different values
    @Test
    public void equal_DifferentInputs() {
    	double[][] input = new double[1][1];
    	double[][] output = new double[1][1];
    	input[0][0] = 0;
    	output[0][0] = 1;
    	assertEquals(false,DataUtilities.equal(input, output));
    	
    }

}
