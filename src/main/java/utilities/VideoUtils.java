package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoUtils {

    public static void convertImagesToVideo(String name) {
        String folderPath = "G:\\restAssured\\lambda3\\test-outputs\\screen-records\\";
        try {
            // Ensure videos folder exists
            File videosFolder = new File(folderPath );
            videosFolder.mkdirs();

            // Build ffmpeg command
            ProcessBuilder builder = new ProcessBuilder(
                    "ffmpeg",
                    "-r", "5",
                    "-f", "image2",
                    "-i", folderPath+ name+"\\"+"pic%04d.png",
                    "-vf", "scale=trunc(iw/2)*2:trunc(ih/2)*2",
                    "-vcodec", "libx264",
                    "-crf", "25",
                    "-pix_fmt", "yuv420p",
                    folderPath + name+".mp4"
            );

            builder.directory(new File(folderPath)); // Set working directory
            builder.redirectErrorStream(true); // Merge stderr with stdout

            Process process = builder.start();

            // Optional: Print ffmpeg output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("FFmpeg finished with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error running ffmpeg: " + e.getMessage());
        }
    }
}
