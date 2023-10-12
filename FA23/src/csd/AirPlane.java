package csd;
import java.util.*;
public class AirPlane {
    static HashMap<String,ArrayList<Vertex>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static String getRegion(String v) {
        final String[] north = {"Mien Bac","hanoi", "tuyenquang", "laocai", "yenbai", "sonla", "hoabinh", "dienbien", "langson", "quangninh", "bacgiang", "phuthuoc", "vinhphuc", "bacninh", "haiduong", "thaibinh", "namdinh", "hanam", "thainguyen", "hungyen", "vinhphuc"};
        final String[] cen = {"Mien Trung","thanhhoa", "nghean", "hatinh", "quangbinh", "quangtri", "thuathienhue", "danang", "quangnam", "quangnai", "binhdinh", "phuyen", "khanhhoa", "ninhthuan", "binhthuan", "kontum", "gialai", "daklak", "daknong"};
        final String[] south = {"Mien Nam","saigon","cantho", "dongnai", "binhduong", "tphochiminh", "binhphuoc", "tayninh", "angiang", "kiengiang", "camau", "baclieu", "soctrang", "travinh", "vinhlong", "bentre", "longan", "tiengiang", "dongthap"};
        String[][] regions = {north, cen, south};
        for (String[] reg : regions) {
            for (int i = 1;i<reg.length;i++) {
                if (reg[i].equalsIgnoreCase(v)) {
                    return reg[0];
                }
            }
        }
        return "Invalid";
    }
    static void dijkstra(String s,String r) {
        PriorityQueue<Vertex> PQ = new PriorityQueue<>(Comparator.comparingInt(Vertex::getValue));
        HashMap<String,Integer> d = new HashMap<>();
        map.forEach((key,value) -> d.put(key,Integer.MAX_VALUE));
        d.put(s,0);
        PQ.add(new Vertex(s,0));
        while (!PQ.isEmpty()) {
            int distance = PQ.peek().getValue();
            String v = PQ.peek().getV();
            PQ.remove();
            if (distance > d.get(v)) continue;
            for(Vertex e : map.get(v)) {
                String vertex = e.getV();
                int value = e.getValue();
                if(d.get(vertex) > d.get(v) + value) {
                    d.put(vertex,d.get(v)+value);
                    PQ.add(new Vertex(vertex,d.get(v)));
                }
            }
        }
        d.forEach((key,value) -> {
            if(!key.equalsIgnoreCase(s) && getRegion(key).equalsIgnoreCase(r)) System.out.println(s+ " to "+key+" : "+value);
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
                map.get(v1).add(new Vertex(v2,value));
            } else {
                map.put(v1,new ArrayList<>());
                map.get(v1).add(new Vertex(v2,value));
            }
            if(map.get(v2) != null) {
                map.get(v2).add(new Vertex(v1,value));
            } else {
                map.put(v2,new ArrayList<>());
                map.get(v2).add(new Vertex(v1,value));
            }
        }
        dijkstra("danang","Mien Nam");
    }
    public static void main(String[] args) {
        run();
    }
}
class Vertex {
    private String v;
    private int value;
    public Vertex(String v, int value) {
        this.v = v;
        this.value = value;
    }
    public String getV() {
        return v;
    }
    public int getValue() {
        return value;
    }
}