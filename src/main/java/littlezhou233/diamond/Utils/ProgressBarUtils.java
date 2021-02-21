package littlezhou233.diamond.Utils;

public class ProgressBarUtils {

    private final int length;
    private final String icon;
    private int location = 0;

    public ProgressBarUtils(int length, String icon) {
        this.length = length;
        this.icon = icon;
    }

    @Override
    public String toString() {
        String color;
        if (location <= 0) {
            color = "§7";
        } else if (location < 10) {
            color = "§c";
        } else if (location < 15) {
            color = "§e";
        } else {
            color = "§a";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < location) {
                sb.append(color).append(icon);
            } else {
                sb.append("§7").append(icon);
            }
        }
        return sb.toString();
    }

    public int getLength() {
        return length;
    }

    public String getIcon() {
        return icon;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

}
