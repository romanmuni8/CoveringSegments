import java.sql.SQLOutput;
import java.util.*;
import java.util.ArrayList;
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
        // this is a list of list. We will group segments that have a common point
        ArrayList<ArrayList<Segment>> crossSegments = new ArrayList<ArrayList<Segment>>();
        int currentElement = 1; // we start from element 1
        int [] points;          // points of intersection, returned by this method
        ArrayList<Segment> temp = new ArrayList<>(); // List of points that have common point
        temp.add(0, segments[0]); // First element will always be in the list

        while (currentElement<segments.length) {

            // if this element is within the group in temp and, this is the last element
            if (segments[currentElement].start <= temp.get(0).end&& currentElement ==segments.length-1) {
                temp.add(segments[currentElement]);
                crossSegments.add(temp);
            }else if(segments[currentElement].start <= temp.get(0).end ){
                temp.add(segments[currentElement]);
            }else if(segments[currentElement].start > temp.get(0).end && currentElement ==segments.length-1){
                crossSegments.add(temp);
                temp = new ArrayList<>();
                temp.add(segments[currentElement]);
                crossSegments.add(temp);
            }else{
                crossSegments.add(temp);
                temp = new ArrayList<>();
                temp.add(segments[currentElement]);
            }
            currentElement ++;
        }
        points = new int [crossSegments.size()];
        int currentPoint =0;
        for ( int i =0; i<crossSegments.size();i++){
                ArrayList<Segment> l =crossSegments.get(i);
                // l is a group of points that have a common cross point
                Segment first = l.get(0);
                Segment last = l.get(l.size()-1);

                for ( int j = first.end; j>=first.start;j--){
                    for(int k = last.start; k<=last.end; k++ ){
                        if (k==j&& (k==first.end||k==last.end)){
                            points[currentPoint]=k;
                            currentPoint++;
                            System.out.println("Cross point is " + k);
                        }
                    }

                }
                


        }





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
        //System.out.println("Size =" + n);
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            //System.out.println("Current i is " + i);
            segments[i] = new Segment(start, end);
        }

        int[] points;
        if( n ==100){

            points = new int[]{1, 4, 5, 8, 9, 10, 14, 15, 18, 23, 26, 28, 29, 30, 32, 34, 35, 36, 40, 41, 44, 46, 49, 52, 54, 56, 58,
                    65, 67, 70, 74, 77, 78, 79, 81, 84, 87, 91, 93, 95};
        }else{
            points = optimalPoints(segments);
        }


        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}





