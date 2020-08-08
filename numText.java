package numtext;

/**
 *
 * @author gionu
 */

public class numText {
    
    static String llones_digitos[] =
    {
        "",
        "m",
        "b",
        "tr",
        "cuatr",
        "quint",
        "sext",
        "sept",
        "oct",
        "non"
    };
    
    static String llones_prefijos[] =
    {
        "",
        "un",
        "duo",
        "tre",
        "cuator",
        "quin",
        "sex",
        "sept",
        "octo",
        "noven"
    };
    
    static String llones_diez[] = 
    {
        "",
        "dec",
        "vigint",
        "trigint",
        "quadragint",
        "quinquagint",
        "sexagint",
        "septuagint",
        "octogint",
        "nonagint"
    };
    
    static String llones_cien[] =
    {
        "",
        "cen" 
    };
    
    static String digitos[] = 
    {
        "",
        "uno",
        "dos",
        "tres",
        "cuatro",
        "cinco",
        "seis",
        "siete",
        "ocho" ,
        "nueve"
    };
        
    static String dieci[] = 
    {
        "",
        "once",
        "doce",
        "trece",
        "catorce",
        "quince"
    };
    
    static String multiplos_diez[] = 
    {
        "",
        "diez",
        "veinte",
        "treinta",
        "cuarenta",
        "cincuenta",
        "sesenta",
        "setenta",
        "ochenta",
        "noventa"
    };
        
    static String multiplos_cien[] = 
    {
        "",
        "ciento",
        "doscientos",
        "trescientos",
        "cuatrocientos",
        "quinientos",
        "seiscientos",
        "setecientos",
        "ochocientos",
        "novecientos"
    };
    
    public String hasta999(int x)
    {   
        int u =  x     %10;
        int d = (x/10 )%10;
        int c = (x/100)%10;
        
        String res = "";

        if(c > 0)
        {
            if(c == 1 && d == 0 && u == 0)
                res += "cien";
            else
                res += multiplos_cien[c];
        }

        if(u != 0 || d != 0)
        {
            if(c > 0)
                res += " ";
            if(d == 0)
                res += digitos[u];
            else
            {
                if(u == 0)
                    res += multiplos_diez[d]; 
                else
                { 
                    switch(d)
                    {
                        case 1:
                            if(1<= u && u <= 5)
                                res += dieci[u];
                            else
                                res += "dieci"+digitos[u];
                            break;
                        case 2:
                            res += "veinti"+digitos[u];
                            break;
                        default:
                            res += multiplos_diez[d] + " y " + digitos[u];
                            break;
                    }
                    
                }
            }
        }
        return res;
    }
    
    public int aEntero(String a,int s,int e)
    {
        return Integer.parseInt(a.substring(s,e), 10);
    }
    
    public String hasta999999(int x,int l)
    {   
        int u  =  x%1000;
        int m  = (x/1000)%1000;
        
        String res = "";        
        
        if(m == 1)
            res += "mil";
        else if(m > 0)
            res += hasta999(m)+" mil";
        
        if(m > 0 && u > 0)
        {   
            if(u <= 20)
                res += " y ";
            else
                res += " ";
        }
        if(u > 0)
            res += hasta999(u);
        
        if(l > 0)
        {
            String llon;
            int ul =  l     %10;
            int dl = (l/10) %10;
            int cl = (l/100)%10;
            
            if(ul > 0 && dl == 0 && cl == 0)
                llon = llones_digitos[ul];
            else
            {
                llon = llones_prefijos[ul];
                if(dl > 0)
                    llon += llones_diez[dl];
                if(cl > 0)
                    llon += (dl > 0 ? "i" : "")+llones_cien[cl];
	    }
                       
            if(x > 1)
                res += " "+llon+"illones";
            else if(x == 1)
                res = "un "+llon+"illón";
        }
        
        return res;
    }
    
    public String nombra(String s)
    {
        String a = s;
        if(a.length()%6 > 0)
            for(int i=a.length()%6;i<6;i++)
                a = '0' + a;

        String res = "";
        int L = a.length()/6;
        for(int l=0;l<L;l++)
        {
            int asub = Integer.parseInt(a.substring(6*l,6*l+6), 10);
            if(asub == 0) continue;
            
            if(l > 0)
            {
                if(l == L-1)
                  res += (asub > 20 && asub != 100 ? " " : " y ");
                else
                  res += " ";
            }
            
            res += hasta999999(asub,L-1-l);
        }
        return res;
    }
};
