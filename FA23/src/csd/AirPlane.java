package csd;
import java.lang.reflect.Array;
import java.util.*;

public class AirPlane {
    static HashMap<String,ArrayList<Edge>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static void dijkstra(String s) {
        PriorityQueue<Edge> PQ = new PriorityQueue<>(Comparator.comparingInt(Edge::getValue));
        HashMap<String,Integer> d = new HashMap<>();
        map.forEach((key,value) -> d.put(key,Integer.MAX_VALUE));
        d.put(s,0);
        PQ.add(new Edge(s,0));
        while (!PQ.isEmpty()) {
            int distance = PQ.peek().getValue();
            assert PQ.peek() != null;
            String v = PQ.peek().getV();
            PQ.remove();
            if (distance > d.get(v)) continue;
            for(Edge edge : map.get(v)) {
                String vertex = edge.getV();
                int value = edge.getValue();
                if(d.get(vertex) > d.get(v) + value) {
                    d.put(vertex,d.get(v)+value);
                    PQ.add(new Edge(vertex,d.get(v)));
                }
            }
        }
        d.forEach((key,value) -> {
            if(!key.equalsIgnoreCase(s)) System.out.println(s+ " to "+key+" : "+value);
        });
    }
    static void run() {
        String[] inp = sc.nextLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);
        for (int i = 0;i<m;i++) {
            inp = sc.nextLine().split(" ");
            String v1 = inp[0];
            String v2 = inp[1];
            int value = Integer.parseInt(inp[2]);
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
        dijkstra("danang");
    }
    public static void main(String[] args) {
        run();
    }
}
class Edge {
    private String v;
    private int value;
    public Edge(String v, int value) {
        this.v = v;
        this.value = value;
    }
    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
class Vertex {
    private String name;
    private String region;
    private final String[] north = {"Mien Bac","hanoi", "tuyenquang", "laocai", "yenbai", "sonla", "hoabinh", "dienbien", "langson", "quangninh", "bacgiang", "phuthuoc", "vinhphuc", "bacninh", "haiduong", "thaibinh", "namdinh", "hanam", "thainguyen", "hungyen", "vinhphuc"};
    private final String[] cen = {"Mien Trung","thanhhoa", "nghean", "hatinh", "quangbinh", "quangtri", "thuathienhue", "danang", "quangnam", "quangnai", "binhdinh", "phuyen", "khanhhoa", "ninhthuan", "binhthuan", "kontum", "gialai", "daklak", "daknong"};
    private final String[] south = {"Mien Nam","cantho", "dongnai", "binhduong", "tphochiminh", "binhphuoc", "tayninh", "angiang", "kiengiang", "camau", "baclieu", "soctrang", "travinh", "vinhlong", "bentre", "longan", "tiengiang", "dongthap"};
    private String[][] regions = {north,cen,south};
    public Vertex(String name) {
        this.name = name;
        for (String[] reg : regions) {
            for (int i = 1;i<reg.length;i++) {
                if(reg[i].equalsIgnoreCase(name)) {
                    this.region = reg[0];
                }
            }
        }
    }
    public String getName() {
        return name;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
}