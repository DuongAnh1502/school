package j.s0052;
public class EastAsiaCountries extends Country{
    private String countryTerrain;
    public EastAsiaCountries() {
    }
    public EastAsiaCountries(String countryCode, String countryName, float totalArea,String countryTerrain) {
        super(countryCode, countryName, totalArea);
        this.countryTerrain = countryTerrain;
    } 
    @Override
    void display() {
        System.out.println(countryCode+"\t\t"+countryName+"\t\t"+totalArea+"\t\t"+countryTerrain);
    }
}