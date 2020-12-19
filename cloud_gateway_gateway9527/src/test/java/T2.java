import java.time.ZonedDateTime;

/**
 * Created by Miraclo Wei on 2020/12/18 11:02
 */
public class T2 {
    public static void main(String[] args) {
        ZonedDateTime z = ZonedDateTime.now();//默认时区
        System.out.println(z);
    }
    //2020-12-18T11:03:25.114+08:00[Asia/Shanghai]
}
