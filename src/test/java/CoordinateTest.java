import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    ArrayList<Coordinate> xyzArrayList = new ArrayList<>();



    @Test
    public void test(){
        String result = Coordinate.getResult(xyzArrayList);
        assertEquals("None", result);
    }


    @Test
    public void testEmptyList() {
        List<Coordinate> coordinates = new ArrayList<>();
        String expectedOutput = "None";
        String actualOutput = ShapeIdentifier.identify(coordinates);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSingleCoordinate() {
        List<Coordinate> coordinates = List.of(new Coordinate(0, 0, 0));
        String expectedOutput = "Dot";
        String actualOutput = ShapeIdentifier.identify(coordinates);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTwoCoordinates() {
        List<Coordinate> coordinates = List.of(new Coordinate(0, 0, 0), new Coordinate(1, 1, 1));
        String expectedOutput = "Line";
        String actualOutput = ShapeIdentifier.identify(coordinates);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testThreeCoordinates() {
        List<Coordinate> coordinates = List.of(new Coordinate(0, 0, 0), new Coordinate(1, 1, 1), new Coordinate(2, 2, 2));
        String expectedOutput = "Triangle";
        String actualOutput = ShapeIdentifier.identify(coordinates);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testFourCoordinatesSquare() {
        // Test for square
        // Create a list of coordinates representing a square
        List<Coordinate> squareCoordinates = Arrays.asList(
                new Coordinate(0, 0, 0),
                new Coordinate(1, 0, 0),
                new Coordinate(0, 0, 1),
                new Coordinate(1, 0, 1)
        );
        System.out.println(squareCoordinates.size());

        // Assert that the shape identified by the square coordinates is a square
        assertEquals("Square", ShapeIdentifier.identify(squareCoordinates));

    }
    @Test
    public void testFourCoordinatesRectangle() {
        // Create a list of coordinates representing a rectangle
        List<Coordinate> rectangleCoordinates = Arrays.asList(
                new Coordinate(0, 0, 0),
                new Coordinate(3, 0, 0),
                new Coordinate(3, 0, 4),
                new Coordinate(0, 0, 4)
        );


        // Assert that the shape identified by the rectangle coordinates is a rectangle
        assertEquals("Rectangle", ShapeIdentifier.identify(rectangleCoordinates));

    }
    @Test
    public void testFourCoordinatesParallelogram() {
        List<Coordinate> parallelogramCoordinates = Arrays.asList(
                new Coordinate(4, 0, 6),
                new Coordinate(7, 0, 7),
                new Coordinate(10, 0, 10),
                new Coordinate(7, 0, 9)
        );


        // Assert that the shape identified by the parallelogram coordinates is a parallelogram
        assertEquals("Parallelogram", ShapeIdentifier.identify(parallelogramCoordinates));

    }
    @Test
    public void testFourCoordinatesMakes2DShapeXZ(){

        List<Coordinate> parallelogramCoordinates = Arrays.asList(
                new Coordinate(0,0,0),
                new Coordinate(10,0,0),
                new Coordinate(1,3,0),
                new Coordinate(5,3,0)
        );
        String expected = "2D Shape";
        String actual = ShapeIdentifier.identify(parallelogramCoordinates);
        assertEquals(expected,actual);
    }
    @Test
    public void testFourCoordinatesMakes2DShapeYZ(){

        List<Coordinate> parallelogramCoordinates = Arrays.asList(
                new Coordinate(0,0,0),
                new Coordinate(0,0,10),
                new Coordinate(0,3,1),
                new Coordinate(0,3,5)
        );
        String expected = "2D Shape";
        String actual = ShapeIdentifier.identify(parallelogramCoordinates);
        assertEquals(expected,actual);
    }
    @Test
    public void testFiveCoordinatesMakesPyramid(){

        List<Coordinate> parallelogramCoordinates = Arrays.asList(
                new Coordinate(0,0,0),
                new Coordinate(0,0,5),
                new Coordinate(5,0,5),
                new Coordinate(5,0,0),
                new Coordinate(2,5,2)
        );
        String expected = "Pyramid";
        String actual = ShapeIdentifier.identify(parallelogramCoordinates);
        assertEquals(expected,actual);
    }

    @Test
    public void testFiveCoordinatesMakesPyramid2(){

        List<Coordinate> pyramidCoordinates = Arrays.asList(
                new Coordinate(0, 0, 0),  // base point 1
                new Coordinate(5, 0, 0),  // base point 2
                new Coordinate(5, 0, 5),  // base point 3
                new Coordinate(0, 0, 5),  // base point 4
                new Coordinate(2, 5, 2)  // apex
        );
        String expected = "Pyramid";
        String actual = ShapeIdentifier.identify(pyramidCoordinates);
        assertEquals(expected,actual);
    }

    @Test
    public void checkIfEightCoordinatesFormsACube(){
        List<Coordinate> cubeCoordinates = Arrays.asList(
                new Coordinate(0, 0, 0), // base point 1
                new Coordinate(5, 0, 0), // base point 2
                new Coordinate(5, 0, 5), // base point 3
                new Coordinate(0, 0, 5), // base point 4
                new Coordinate(0, 5, 0), // top point 1
                new Coordinate(5, 5, 0), // top point 2
                new Coordinate(5, 5, 5), // top point 3
                new Coordinate(0, 5, 5) // top point 4
        );

        String expected = "Cube";
        String actual = ShapeIdentifier.identify(cubeCoordinates);
        assertEquals(expected,actual);

    }
    @Test
    public void checkIfEightCoordinatesFormsARectangularPrism(){
        List<Coordinate> rectangularPrismCoordinates = Arrays.asList(
                new Coordinate(0, 0, 0),  // base point 1
                new Coordinate(5, 0, 0),  // base point 2
                new Coordinate(5, 0, 5),  // base point 3
                new Coordinate(0, 0, 5),  // base point 4
                new Coordinate(0, 10, 0),  // top point 1
                new Coordinate(5, 10, 0),  // top point 2
                new Coordinate(5, 10, 5),  // top point 3
                new Coordinate(0, 10, 5)  // top point 4
        );

        String expected = "Rectangular Prism";
        String actual = ShapeIdentifier.identify(rectangularPrismCoordinates);
        assertEquals(expected,actual);
    }
    @Test
    public void checkIfEightCoordinatesFormsA3D(){
        List<Coordinate> rectangularPrismCoordinates = Arrays.asList(
                new Coordinate(0, 0, 0),  // base point 1
                new Coordinate(10, 0, 0),  // base point 2
                new Coordinate(10, 0, 5),  // base point 3
                new Coordinate(0, 0, 5),  // base point 4
                new Coordinate(20, 0, 0),  // top point 1
                new Coordinate(31, 0, 0),  // top point 2
                new Coordinate(30, 0, 5),  // top point 3
                new Coordinate(20, 0, 5)  // top point 4
        );

        String expected = "3D Shape";
        String actual = ShapeIdentifier.identify(rectangularPrismCoordinates);
        assertEquals(expected,actual);
    }
}