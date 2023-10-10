package csd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class AriPlane {
    static HashMap<String,ArrayList<Edge>> map = new HashMap();
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
            Edge e = new Edge(v1,v2,value);
            if(map.get(v1) != null) {
                map.get(v1).add(e);
            } else {
                map.put(v1,new ArrayList<>());
                map.get(v1).add(e);
            }
            if(map.get(v2) != null) {
                map.get(v2).add(e);
            } else {
                map.put(v2,new ArrayList<>());
                map.get(v2).add(e);
            }
        }  
    }
    public static void main(String[] args) {
        run();
        
    }
}
class Vertice {
    private String vertice;
    private String dir;

    public Vertice(String vertice, String dir) {
        this.vertice = vertice;
        this.dir = dir;
    }

    public String getVertice() {
        return vertice;
    }

    public void setVertice(String vertice) {
        this.vertice = vertice;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
}
class Edge {
    private String v1;
    private String v2;
    private float value;

    public Edge(String v1, String v2, float value) {
        this.v1 = v1;
        this.v2 = v2;
        this.value = value;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
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