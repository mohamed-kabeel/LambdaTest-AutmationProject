package utilities;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.monte.media.VideoFormatKeys.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

/*public class ScreenRecorderUtils {
    static String path = "test-outputs/screen-records/";
    public static ScreenRecorder start(WebDriver driver, String folderPath) {
        ScreenRecorder recorder = new ScreenRecorder(driver, path + folderPath);
        Thread thread = new Thread(recorder);
        thread.setDaemon(true);
        thread.start();
        recorder.setThread(thread);
        return recorder;
    }

    public static void stop(ScreenRecorder recorder) {
        if (recorder != null) {
            recorder.stop();
            try {
                recorder.getThread().join();
            } catch (InterruptedException e) {
                System.out.println("Failed to stop recorder thread: " + e.getMessage());
            }
        }
    }

    public static class ScreenRecorder implements Runnable {
        private final WebDriver driver;
        private final String folderPath;
        private volatile boolean running = true;
        private int counter = 0;
        private Thread thread;

        public ScreenRecorder(WebDriver driver, String folderPath) {
            this.driver = driver;
            this.folderPath = folderPath;
            new File(folderPath).mkdirs();
        }
        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Thread getThread() {
            return thread;
        }

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    File dest = new File(folderPath + "/pic" + String.format("%04d", counter++) + ".png");
                    Files.copy(screenshot.toPath(), dest.toPath());
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println("Screenshot error: " + e.getMessage());
                }
            }
        }
    }
}
*/

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScreenRecorderUtils {
    static String path = "test-outputs/screen-records/";

    public static ScreenRecorder start(WebDriver driver, String folderPath) {
        ScreenRecorder recorder = new ScreenRecorder(driver, path + folderPath);
        Thread thread = new Thread(recorder);
        thread.setDaemon(true);
        thread.start();
        recorder.setThread(thread);
        return recorder;
    }

    public static void stop(ScreenRecorder recorder) {
        if (recorder != null) {
            recorder.stop();
            try {
                recorder.getThread().join();
                recorder.saveScreenshotsToDisk();  // Save screenshots after stopping
            } catch (InterruptedException | IOException e) {
                System.out.println("Failed to stop recorder thread: " + e.getMessage());
            }
        }
    }

    public static class ScreenRecorder implements Runnable {
        private final WebDriver driver;
        private final String folderPath;
        private volatile boolean running = true;
        private int counter = 0;
        private Thread thread;
        private final List<byte[]> screenshots = new ArrayList<>();

        public ScreenRecorder(WebDriver driver, String folderPath) {
            this.driver = driver;
            this.folderPath = folderPath;
            new File(folderPath).mkdirs();
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Thread getThread() {
            return thread;
        }

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    screenshots.add(screenshotBytes);
                    Thread.sleep(200);  // Capture every 200ms
                } catch (Exception e) {
                    System.out.println("Screenshot error: " + e.getMessage());
                }
            }
        }

        public void saveScreenshotsToDisk() throws IOException {
            int index = 0;
            for (byte[] screenshot : screenshots) {
                File dest = new File(folderPath + "/pic" + String.format("%04d", index++) + ".png");
                try (FileOutputStream fos = new FileOutputStream(dest)) {
                    fos.write(screenshot);
                }
            }
        }
    }
}
