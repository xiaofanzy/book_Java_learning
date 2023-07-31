import java.io.File;
import java.io.FileFilter;

public class FirstFunction {

    public static void main(String[] args) {
        //以前的方法
        File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        //现在
        files = new File(".").listFiles(File::isHidden);



    }
}
