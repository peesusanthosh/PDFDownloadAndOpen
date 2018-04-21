package srp.com.pdfdownloadandopen;

import android.os.Environment;
/**
 * Created by Santhosh on 04/21/2018.
 */
public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
