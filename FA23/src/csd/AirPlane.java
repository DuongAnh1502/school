package csd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class AirPlane {
    static HashMap<Vertex,ArrayList<Edge>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static HashMap<Vertex,Float> dijkstra() {
        HashMap<Vertex,Float> rs = new HashMap<>();

        return rs;
    }
    static void run() {
        String[] inp = sc.nextLine().split(" ");
        int n = Integer.parseInt(inp[0]);
        int m = Integer.parseInt(inp[1]);
        for (int i = 0;i<m;i++) {
            inp = sc.nextLine().split(" ");
            Vertex v1 = new Vertex(inp[0]);
            Vertex v2 = new Vertex(inp[1]);
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
    private Vertex v2;
    private float value;
    public Edge(Vertex v2, float value) {
        this.v2 = v2;
        this.value = value;
    }
    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
class Vertex {
    private final String name;
    private String region;
    public Vertex(String name) {
        this.name = name;
        switch(name) {
            case "ha noi",
                    "tuyen quang",
                    "lao cai",
                    "yen bai",
                    "son la",
                    "hoa binh",
                    "dien bien",
                    "lang son",
                    "quang ninh",
                    "bac giang",
                    "phu tho",
                    "vinh phuc",
                    "bac ninh",
                    "hai duong",
                    "thai binh",
                    "nam dinh",
                    "ha nam",
                    "thai nguyen",
                    "hung yen" -> {this.region = "Mien Bac";}
            case "thanh hoa",
                    "nghe an",
                    "ha tinh",
                    "quang binh",
                    "quang tri",
                    "thua thien hue",
                    "da nang",
                    "quang nam",
                    "quang ngai",
                    "binh dinh",
                    "phu yen",
                    "khanh hoa",
                    "ninh thuan",
                    "binh thuan",
                    "kon tum",
                    "gia lai",
                    "dak lak",
                    "dak nong" -> {this.region = "Mien Trung";}
            case "can tho",
                    "dong nai",
                    "binh duong",
                    "tp ho chi minh",
                    "binh phuoc",
                    "tay ninh",
                    "an giang",
                    "kien giang",
                    "ca mau",
                    "bac lieu",
                    "soc trang",
                    "tra vinh",
                    "vinh long",
                    "ben tre",
                    "long an",
                    "tien giang",
                    "dong thap" -> {this.region = "Mien Nam";}
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