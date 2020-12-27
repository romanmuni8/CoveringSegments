import java.sql.SQLOutput;
import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        // first sort the array of segments.
        Arrays.sort(segments, Comparator.comparing(Segment::getStart));
        // if we have just one segment, return it
        if (segments.length == 1) {
            int[] arr = {segments[0].start, segments[0].end};
            return arr;
        }



        //Create lists of segments that have a common point
        // this is a list of lists
        List crossSegments = new ArrayList();
        int currentElement = 1; // we start from element 1
        int [] points;          // points of intersection, returned by this method
        while (currentElement<segments.length -1) {

            List<Segment> temp = new ArrayList<>(); // List of points that have common point
            temp.add(0, segments[0]);         // First element will always be in the list
            for (int i = currentElement; i < segments.length; i++) {
                   currentElement =i;
                if (segments[i].start <= segments[i - 1].end) {
                    //!! Keep track of the current intersection


                    temp.add(segments[currentElement]);
                    currentElement =i;
                    if(i == segments.length-1){
                        crossSegments.add(temp);
                        break;
                    }


                } else {
                    crossSegments.add(temp);
                    currentElement =i;
                    break;

                }
            }

        }
        System.out.println("number of segements" + crossSegments.size());

        points = new int [crossSegments.size()];

        return points;
    }

    private static class Segment {
        int start, end;
        public int getStart(){
            return start;
        }
        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }



    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Size =" + n);
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            System.out.println("Current i is " + i);
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
//        for (int point : points) {
//            System.out.print(point + " ");
//        }
    }
}


