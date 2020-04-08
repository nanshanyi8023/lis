import java.io.FileOutputStream;
import java.io.IOException;

public class FormatDatabaseStatements {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        String s = sb.toString();
        String[] sarr = s.split(";");
        int length = sarr.length;
        for (int i = 0; i < length ; i++) {
            String[] split = sarr[i].split(",");
            sarr[i] = "update check_item_group set group_name = " + split[3] + "  where hosnum = '10000' and group_id = " + split[0] + ";";
       }
        String result = "";
        int flag = 0;
        while (flag < length) {
            result += sarr[flag];
            flag++;
        }

        FileOutputStream fos = new FileOutputStream("E:\\equipment_channel.txt");
        fos.write(result.getBytes());
        fos.close();
    }

}
