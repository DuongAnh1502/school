package csd;
import java.io.*;
import java.util.*;
public class AirPlane {
    //Chuyen input danh sach canh sang danh sach ke
    static HashMap<String,ArrayList<Vertex>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static HashMap<String,String> parrent = new HashMap<>();
    static HashMap<String,Boolean> visited = new HashMap<>();
    static ArrayList<String> ls = new ArrayList<>();
    //Lay vung mien cua tinh,thanh pho
    static String getRegion(String v) {
        final String[] north = {"Mien Bac","hanoi", "tuyenquang", "laocai", "yenbai", "sonla", "hoabinh", "dienbien", "langson", "quangninh", "bacgiang", "phuthuoc", "vinhphuc", "bacninh", "haiduong", "thaibinh", "namdinh", "hanam", "thainguyen", "hungyen", "vinhphuc"};
        final String[] cen = {"Mien Trung","thanhhoa", "nghean", "hatinh", "quangbinh", "quangtri", "thuathienhue", "danang", "quangnam", "quangnai", "binhdinh", "phuyen", "khanhhoa", "ninhthuan", "binhthuan", "kontum", "gialai", "daklak", "daknong"};
        final String[] south = {"Mien Nam","saigon","cantho", "dongnai", "binhduong", "hochiminh", "binhphuoc", "tayninh", "angiang", "kiengiang", "camau", "baclieu", "soctrang", "travinh", "vinhlong", "bentre", "longan", "tiengiang", "dongthap","phuquoc"};
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
    static void dfs(String p) {
        visited.put(p, true);
        ls.add(p);
        for(Vertex v:map.get(p)) {
            if(!visited.get(v.getV())) {
                dfs(v.getV());
            }
        }
    }
    //Find cutting point
    static void findCuttingPoint() {
        ArrayList<String> rs = new ArrayList<>();
        for(String v:map.keySet()) {
            visited.replaceAll((key,value)->false);
            visited.put(v, true);
            ls.clear();
            dfs("danang");
            if(ls.size() != map.size()-1) {
                rs.add(v);
            }
        }
        System.out.println("Cutting point : ");
        for(String v:rs) {
            System.out.print(v+ " ");
        }
        System.out.println("");
    }
    //Thuat toan tim duong di ngan nhat
    static void dijkstra(String s,String t,String r) {
        //Tao priority queue theo khoang cach cua 2 dinh
        PriorityQueue<Vertex> PQ = new PriorityQueue<>(Comparator.comparingInt(Vertex::getValue));
        HashMap<String,Integer> d = new HashMap<>();
        ArrayList<String> rs = new ArrayList<>();
        //Dat khoang cach cua cac dinh thanh gia tri lon nhat
        map.forEach((key,value) -> d.put(key,Integer.MAX_VALUE));
        d.put(s,0);
        PQ.add(new Vertex(s,0));
        while (!PQ.isEmpty()) {
            int distance = PQ.peek().getValue();
            String v = PQ.peek().getV();
            PQ.remove();
            if (distance > d.get(v)) continue;
            for(Vertex e : map.get(v)) {
                String name = e.getV();
                int value = e.getValue();
                if(d.get(name) > d.get(v) + value) {
                    d.put(name,d.get(v)+value);
                    parrent.put(name,v);
                    PQ.add(new Vertex(name,d.get(name)));
                }
            }
        }
        dfs("danang");
        d.forEach((key,value) -> {
            if(!key.equalsIgnoreCase(s)) System.out.println(s+ " to "+key+" : "+value);
        });
        while(t!=s) {
            rs.add(t);
            t = parrent.get(t);
        }
        rs.add(s);
        Collections.reverse(rs);
        for(String n:rs) {
            System.out.print(n+" ");
        }
        System.out.println("");
    }
    static void run() {
        File currentDir = new File("");
        try(BufferedReader br = new BufferedReader(new FileReader(currentDir.getAbsolutePath()+"/src/data/flight.txt"))) {
            String line;
            while((line = br.readLine())!=null) {
                String[] inp = line.split(" ");
                String v1 = inp[0];
                String v2 = inp[1];
                int value = Integer.parseInt(inp[2]);
                visited.put(v1, false);
                visited.put(v2,false);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dijkstra("danang","condao","Mien Nam");
    }
    public static void main(String[] args) {
        run();
        findCuttingPoint();
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