package littlezhou233.diamond.Utils;

import java.text.DecimalFormat;

public class IntUtils {
    public static String toTrisection(double d){
        if ( d == 0 ){
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        String result = df.format(d).replace(".00", "");
        return result.endsWith(".0") ? result.replace(".0", "") : result;
    }
}
