package Virus_Attack.src;

public class Line
{
    private int virusx;
    private int liney;
    private int virusy;
    private int linex;
    private AntiVirus av;
    public Line (int a, int b, int c, int d, AntiVirus v)
    {
        virusx = a;
        virusy = b;
        linex = c;
        liney = d;
        av = v;

    }
    public int virusx()
    {
        return virusx;
    }
    public int liney()
    {
        return liney;
    }
    public int virusy()
    {
        return virusy;
    }
    public int linex()
    {
        return linex;
    }
    public AntiVirus av()
    {
        return av;
    }



}