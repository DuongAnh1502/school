package csd;
public class Substring {
    public static void main(String[] args) {
        String s = "cawwdrrtabaawwqa";
        String ss = "abaa";
        for(int i = 0;i<s.length();i++) {
            if (i+ss.length() < s.length()) {
                String temp = s.substring(i, i+ss.length());
                if(ss.equals(temp)) {
                    System.out.println(i);
                }
            }
        }
    }
}
