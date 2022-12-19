import java.util.ArrayList;
import java.util.List;

public class ShapeIdentifier {
    public static String identify(List<Coordinate> coordinates) {
        // Return "None" if the list is empty
        if (coordinates.isEmpty()) {
            return "None";
        }

        // Check for a single coordinate (Dot)
        else if (coordinates.size() == 1) {
            return "Dot";
        }

        // Check for two coordinates (Line)
        else if (coordinates.size() == 2) {
            return "Line";
        }

        // Check for three coordinates (Triangle)
        else if (coordinates.size() == 3) {
            return "Triangle";
        }

        // Check for four coordinates (Square, Rectangle, Parallelogram)
        else if (coordinates.size() == 4) {
            // Check if the coordinates form a square
            if (isSquare(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3) )) {
                return "Square";
            }

            // Check if the coordinates form a rectangle
            else if (isRectangle(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3))) {
                return "Rectangle";
            }

            // Check if the coordinates form a parallelogram
            else if (isParallelogram(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3))) {
                return "Parallelogram";
            }
            // Check for coordinates on the same plane (2D Shape)
           else if (coordinates.stream().map(c -> c.getX()).distinct().count() == 1 ||
                    coordinates.stream().map(c -> c.getY()).distinct().count() == 1 ||
                    coordinates.stream().map(c -> c.getZ()).distinct().count() == 1) {
                return "2D Shape";
            }
        }



        // Check for five coordinates (Pyramid)
        else if (coordinates.size() == 5) {
            // Print out the coordinates
            System.out.println("coordinates: " + coordinates);

            if (isPyramid(coordinates)) {
                return "Pyramid";
            }
        }

// Check for eight coordinates (Cube, Rectangular Prism)
        else if (coordinates.size() == 8) {

            if (isRectangularPrism3(coordinates)) {
                return "Rectangular Prism";
            }
             if (isCube(coordinates)) {
                  return "Cube";
              }


        }
        return "3D Shape";
    }

    public static int calcDist(Coordinate coord1, Coordinate coord2){
        double ac = Math.abs(coord2.getX() - coord1.getX());
        double cb = Math.abs(coord2.getY() - coord1.getY());
        return (int) Math.abs(ac+ cb);
    }
    public static double calcDistance(Coordinate p1, Coordinate p2) {
        // Calculate the distance between the two coordinates using the distance formula
        double xDist = p2.getX() - p1.getX();
        double yDist = p2.getY() - p1.getY();
        double zDist = p2.getZ() - p1.getZ();

        return Math.abs(xDist+ yDist +zDist);
    }
    public static double calcAngle(Coordinate p1, Coordinate p2, Coordinate p3) {
        // Calculate the vectors formed by the coordinates
        double[] vector1 = {p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ()};
        double[] vector2 = {p3.getX() - p2.getX(), p3.getY() - p2.getY(), p3.getZ() - p2.getZ()};

        // Calculate the dot product of the vectors
        double dotProduct = vector1[0] * vector2[0] + vector1[1] * vector2[1] + vector1[2] * vector2[2];

        // Calculate the magnitudes of the vectors
        double magnitude1 = Math.sqrt(vector1[0] * vector1[0] + vector1[1] * vector1[1] + vector1[2] * vector1[2]);
        double magnitude2 = Math.sqrt(vector2[0] * vector2[0] + vector2[1] * vector2[1] + vector2[2] * vector2[2]);

        // Calculate the angle between the vectors using the dot product and magnitudes
        double angle = Math.toDegrees(Math.acos(dotProduct / (magnitude1 * magnitude2)));

        return angle;
    }

    public static double calcAnglePrism(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
        // Calculate the vectors formed by the coordinates
        double[] vector1 = {p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ()};
        double[] vector2 = {p3.getX() - p2.getX(), p3.getY() - p2.getY(), p3.getZ() - p2.getZ()};
        double[] vector3 = {p4.getX() - p3.getX(), p4.getY() - p3.getY(), p4.getZ() - p3.getZ()};

        // Calculate the normal vector of the first square using the cross product of vectors 1 and 2
        double[] normal1 = {
                vector1[1] * vector2[2] - vector1[2] * vector2[1],
                vector1[2] * vector2[0] - vector1[0] * vector2[2],
                vector1[0] * vector2[1] - vector1[1] * vector2[0]
        };

        // Calculate the normal vector of the second square using the cross product of vectors 2 and 3
        double[] normal2 = {
                vector2[1] * vector3[2] - vector2[2] * vector3[1],
                vector2[2] * vector3[0] - vector2[0] * vector3[2],
                vector2[0] * vector3[1] - vector2[1] * vector3[0]
        };

        // Calculate the dot product of the two normal vectors
        double dotProduct = normal1[0] * normal2[0] + normal1[1] * normal2[1] + normal1[2] * normal2[2];

        // Calculate the magnitudes of the two normal vectors
        double magnitude1 = Math.sqrt(normal1[0] * normal1[0] + normal1[1] * normal1[1] + normal1[2] * normal1[2]);
        double magnitude2 = Math.sqrt(normal2[0] * normal2[0] + normal2[1] * normal2[1] + normal2[2] * normal2[2]);

        // Calculate the angle between the two normal vectors using the dot product and magnitudes
        double angle = Math.toDegrees(Math.acos(dotProduct / (magnitude1 * magnitude2)));

        return angle;
    }


    public static Boolean isSquare(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4)
    {
        double d2 = calcDistance(p1, p2); // from p1 to p2
        double d3 = calcDistance(p1, p3); // from p1 to p3
        double d4 = calcDistance(p1, p4); // from p1 to p4

        if (d2 == 0 || d3 == 0 || d4 == 0)
            return false;

        // If lengths if (p1, p2) and (p1, p3) are same, then
        // following conditions must met to form a square.
        // 1) Square of length of (p1, p4) is same as twice
        // the square of (p1, p2)
        // 2) Square of length of (p2, p3) is same
        // as twice the square of (p2, p4)

        if (d2 == d3 && 2 * d2 == d4
                && 2 * calcDist(p2, p4) == calcDist(p2, p3)) {
            return true;
        }

        // The below two cases are similar to above case
        if (d3 == d4 && 2 * d3 == d2
                && 2 * calcDist(p3, p2) == calcDist(p3, p4)) {
            return true;
        }
        if (d2 == d4 && 2 * d2 == d3
                && 2 * calcDist(p2, p3) == calcDist(p2, p4)) {
            return true;
        }

        return false;
    }

    public static boolean isRectangle(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
        // Calculate the distances between the coordinates
        double d1 = calcDistance(p1, p2);
        double d2 = calcDistance(p2, p3);
        double d3 = calcDistance(p3, p4);
        double d4 = calcDistance(p4, p1);
        System.out.println(d1+" "+d2);

        // Check if two pairs of opposite sides are equal in length
        if (d1 == d3 && d2 == d4) {
            // Calculate the angles between the pairs of opposite sides
            double angle1 = calcAngle(p1, p2, p3);
            double angle2 = calcAngle(p2, p3, p4);

            // Check if the angles between the pairs of opposite sides are right angles
            if (angle1 == 90.0 && angle2 == 90.0) {
                return true;
            }
        }

        return false;
    }



    public static boolean isParallelogram(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
        // Calculate the distances between the coordinates
        double d1 = calcDistance(p1, p2);
        double d2 = calcDistance(p2, p3);
        double d3 = calcDistance(p3, p4);
        double d4 = calcDistance(p4, p1);

        // Check if two pairs of opposite sides are equal in length within a certain tolerance
        if (d1 == d3 && d2 == d4){
            // Calculate the angles between the pairs of opposite sides
            double angle1 = calcAngle(p1, p2, p3);
            double angle2 = calcAngle(p1, p4, p3);

            double angle3 = calcAngle(p4, p1, p2);
            double angle4 = calcAngle(p2, p3, p4);


            // Check if the angles between the pairs of opposite sides are equal
            if (angle1 == angle2 && angle3 == angle4) {
                return true;
            }
        }

        return false;
    }
    private static boolean isPyramid(List<Coordinate> coordinates) {
        // Check if four of the coordinates form a square
        boolean square = isSquare(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3));
        if (!square) {

            return false;
        }

        // Check if the fifth coordinate is not collinear with the other four
        boolean collinear = isFourCoordinatesCollinear(coordinates);
        if (collinear) {
            System.out.println("test");
            return false;
        }


        // Check if the fifth coordinate is equidistant from the four square coordinates
        boolean equidistant = fifthCoordinateEquidistant(coordinates);
        System.out.println("fifthCoordinateEquidistant: " + equidistant);
        if (equidistant) {
            return false;
        }



        // If all the conditions are met, return true
        else {
            return true;
        }
    }

    public static boolean isCube(List<Coordinate> coordinates) {
        // Check if the first four coordinates form a square
        boolean square1 = isSquare(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3));

        // Check if the last four coordinates form a square
        boolean square2 = isSquare(coordinates.get(4), coordinates.get(5), coordinates.get(6), coordinates.get(7));

        // Check if the squares are adjacent to each other
        boolean squaresAdjacent = calcDistance(coordinates.get(0), coordinates.get(4)) == calcDistance(coordinates.get(1), coordinates.get(5)) &&
                calcDistance(coordinates.get(1), coordinates.get(5)) == calcDistance(coordinates.get(2), coordinates.get(6)) &&
                calcDistance(coordinates.get(2), coordinates.get(6)) == calcDistance(coordinates.get(3), coordinates.get(7));

        // Check if the sides of the squares are equal in length
        boolean sidesEqual = calcDistance(coordinates.get(0), coordinates.get(1)) == calcDistance(coordinates.get(4), coordinates.get(5)) &&
                calcDistance(coordinates.get(1), coordinates.get(2)) == calcDistance(coordinates.get(5), coordinates.get(6)) &&
                calcDistance(coordinates.get(2), coordinates.get(3)) == calcDistance(coordinates.get(6), coordinates.get(7));






        // Check if the squares are the width of a square apart
        boolean squaresApart = calcDistance(coordinates.get(0), coordinates.get(4)) == calcDistance(coordinates.get(1), coordinates.get(5)) &&
                calcDistance(coordinates.get(1), coordinates.get(5)) == calcDistance(coordinates.get(2), coordinates.get(6)) &&
                calcDistance(coordinates.get(2), coordinates.get(6)) == calcDistance(coordinates.get(3), coordinates.get(7));
        System.out.println("squaresApart: "+ squaresApart);

        // Return true if all conditions are met
        return square1 && square2 && squaresAdjacent && sidesEqual;
    }




    public static boolean isRectangularPrism3(List<Coordinate> coordinates) {
        // Check that the first four coordinates form a rectangle
        boolean rec1 = isRectangle(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3));

        // Check that the last four coordinates form a rectangle
        boolean rec2 = isRectangle(coordinates.get(4), coordinates.get(5), coordinates.get(6), coordinates.get(7));

        // Calculate the distances between the corresponding corners of the two rectangles
        double dist1 = calcDistance(coordinates.get(0), coordinates.get(4));
        double dist2 = calcDistance(coordinates.get(1), coordinates.get(5));
        double dist3 = calcDistance(coordinates.get(2), coordinates.get(6));
        double dist4 = calcDistance(coordinates.get(3), coordinates.get(7));

        // Check that the distances between the corresponding corners of the two rectangles are equal within a small tolerance
        if (Math.abs(dist1 - dist2) > 1e-6 || Math.abs(dist1 - dist3) > 1e-6 || Math.abs(dist1 - dist4) > 1e-6) {
            return false;
        }
        // Check that the side lengths of the rectangles are equal
        if (Math.abs(dist1 - dist2) > 1e-6 || Math.abs(dist1 - dist3) > 1e-6 || Math.abs(dist1 - dist4) > 1e-6) {
            return false;
        }
        // Return true if all conditions are met
        return rec1 && rec2;
    }






    private static boolean fifthCoordinateEquidistant(List<Coordinate> coordinates) {
        // Calculate the distance between the fifth coordinate and each of the four square coordinates
        double dist1 = calcDistance(coordinates.get(4), coordinates.get(0));
        double dist2 = calcDistance(coordinates.get(4), coordinates.get(1));
        double dist3 = calcDistance(coordinates.get(4), coordinates.get(2));
        double dist4 = calcDistance(coordinates.get(4), coordinates.get(3));
        System.out.println("dist1: " + dist1 + ", dist2: " + dist2 + ", dist3: " + dist3 + ", dist4: " + dist4);

        // Check if the distances are equal
        return Math.abs(dist1 - dist2) < 1e-6 && Math.abs(dist2 - dist3) < 1e-6 && Math.abs(dist3 - dist4) < 1e-6;
    }

    private static boolean isFourCoordinatesCollinear(List<Coordinate> coordinates) {
        boolean collinear = isCollinear(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(4)) ||
                isCollinear(coordinates.get(0), coordinates.get(1), coordinates.get(3), coordinates.get(4)) ||
                isCollinear(coordinates.get(0), coordinates.get(2), coordinates.get(3), coordinates.get(4)) ||
                isCollinear(coordinates.get(1), coordinates.get(2), coordinates.get(3), coordinates.get(4));
        return collinear;
    }

    private static boolean isCollinear(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate p4) {
        return calcAngle(p1, p2, p4) == calcAngle(p2, p3, p4);
    }
    public static boolean isCube2(List<Coordinate> coordinates) {
        // Check if the first four coordinates form a square
        boolean square1 = isSquare(coordinates.get(0), coordinates.get(1), coordinates.get(2), coordinates.get(3));

        // Check if the last four coordinates form a square
        boolean square2 = isSquare(coordinates.get(4), coordinates.get(5), coordinates.get(6), coordinates.get(7));

        // Check if the squares are adjacent to each other
        boolean squaresAdjacent = calcDistance(coordinates.get(0), coordinates.get(4)) == 0 &&
                calcDistance(coordinates.get(1), coordinates.get(5)) == 0 &&
                calcDistance(coordinates.get(2), coordinates.get(6)) == 0 &&
                calcDistance(coordinates.get(3), coordinates.get(7)) == 0;

        // Check if the sides of the squares are equal in length
        boolean sidesEqual = calcDistance(coordinates.get(0), coordinates.get(1)) == calcDistance(coordinates.get(4), coordinates.get(5)) &&
                calcDistance(coordinates.get(0), coordinates.get(3)) == calcDistance(coordinates.get(4), coordinates.get(7));

        // Return true if all conditions are met
        return square1 && square2 && squaresAdjacent && sidesEqual;
    }




}
