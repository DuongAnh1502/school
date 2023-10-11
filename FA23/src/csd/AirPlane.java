package csd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class AirPlane {
    static HashMap<String,ArrayList<Edge>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static void run() {
        String[] inp = sc.nextLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);
        for (int i = 0;i<m;i++) {
            inp = sc.nextLine().split(" ");
            String v1 = inp[0];
            String v2 = inp[1];
            float value = Float.parseFloat(inp[2]);
            if(map.get(v1) != null) {
                map.get(v1).add(new Edge(v2,value));
            } else {
                map.put(v1,new ArrayList<>());
                map.get(v1).add(new Edge(v2,value));
            }
            if(map.get(v2) != null) {
                map.get(v2).add(new Edge(v1,value));
            } else {
                map.put(v2,new ArrayList<>());
                map.get(v2).add(new Edge(v1,value));
            }
        }
    }
    public static void main(String[] args) {
        run();
    }
}
class Edge {
    private String v2;
    private float value;

    public Edge(String v2, float value) {
        this.v2 = v2;
        this.value = value;
    }
    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
}