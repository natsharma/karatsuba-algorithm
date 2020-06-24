package karatsuba;

import java.util.Scanner;

public class KaratsubaAlgorithm {
	
	@SuppressWarnings("resource")
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
        int degree = sc.nextInt()+1;
        int poly1[] = new int[degree];
        int poly2[] = new int[degree];
        for (int i=0; i<degree; i++){poly1[i] = sc.nextInt();}
        for (int i=0; i<degree; i++){poly2[i] = sc.nextInt();}
	}
	
	//karatsuba's algorithm
	public static int[] recurse(int[] p1, int[] p2, int deg) {
		int[] poly = new int[2*deg-1];
		if(deg < 5) {
			for(int i=0; i<deg; i++) {
				for(int j=0; j<deg; j++) {
					poly[i+j] += p1[i] * p2[j];
				}
			}
			return poly;
		}
		int[] p1l = new int[deg/2];
		int[] p2l = new int[deg/2];
        int[] p1m = new int[deg/2];
        int[] p2m = new int[deg/2];
        int[] p1h = new int[deg/2];
        int[] p2h = new int[deg/2];
        
        for(int i = 0; i < deg; i++){
            if(i<deg/2){
                p1l[i] = p1[i];
                p2l[i] = p2[i];
            }
            else{
                p1m[i-deg/2] = p1[i];
                p2m[i-deg/2] = p2[i];
            }
        }

        for(int i = 0; i < deg/2; i++){
            p1h[i] = p1l[i]+p1m[i];
            p2h[i] = p2l[i]+p2m[i];
        }

        int[] sum = recurse(p1h, p2h, deg/2);
        int[] front = recurse(p1l, p2l, deg/2);
        int[] back = recurse(p1m, p2m, deg/2);

        for(int i = 0; i < deg-1; i++){poly[i] = front[i];}

        for(int i = 0; i < deg-1; i++){poly[deg+i] = back[i];}

        for(int i = 0; i < deg-1; i++){poly[deg/2 + i] += (sum[i] - front[i] - back[i]);}
        return poly;
	}
}
