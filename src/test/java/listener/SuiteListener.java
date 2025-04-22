package listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import static utilities.FileUtilsCustom.cleanAllureResults;
import static utilities.FileUtilsCustom.cleanFolderContents;

public class SuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");
    }
    @Override
    public void onFinish(ISuite suite) {
    }
}
