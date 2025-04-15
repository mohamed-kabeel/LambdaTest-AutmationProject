package utilities;

import com.google.gson.*;
import com.jayway.jsonpath.*;

import java.io.*;

public class JsonUtils {

    public static String getDataFromJson(File path, String field) {
        try {
            FileReader reader = new FileReader(path);
            JsonElement jsonElement = JsonParser.parseReader(reader);

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String[] keys = field.split("\\.");
            for (String key : keys) {
                if (jsonObject.has(key)) {
                    JsonElement tempElement = jsonObject.get(key);
                    if (tempElement.isJsonObject()) {
                        jsonObject = tempElement.getAsJsonObject();
                    } else {
                        return tempElement.getAsString();
                    }
                } else {
                    return "Field not found";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file not found");
            return "File not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing JSON";
        }
        return "No data found";
    }

    public static String getJsonValue(String jsonFilename, String field) {
        try {
            FileReader reader = new FileReader(jsonFilename);
            Object jsonData = new Gson().fromJson(reader, Object.class);
            Object value = JsonPath.read(jsonData, "$." + field);
            return value != null ? String.valueOf(value) : "";
        } catch (Exception e) {
            return "";
        }
    }

    public static void updateJsonValue(String jsonFilename, String jsonPath, String newValue) {
        try {
            FileReader reader = new FileReader(jsonFilename);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            reader.close();

            if (jsonElement == null || !jsonElement.isJsonObject()) return;

            JsonObject jsonData = jsonElement.getAsJsonObject();

            Object existingValue;
            try {
                existingValue = JsonPath.read(jsonData.toString(), "$." + jsonPath);
            } catch (PathNotFoundException e) {
                existingValue = null;
            }

            DocumentContext updatedJson = JsonPath.parse(jsonData.toString()).set("$." + jsonPath, newValue);
            String updatedJsonString = updatedJson.jsonString();
            if (updatedJsonString == null || updatedJsonString.isEmpty()) return;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(jsonFilename);
            gson.toJson(JsonParser.parseString(updatedJsonString), writer);
            writer.close();
        } catch (IOException | JsonSyntaxException e) {
        } catch (Exception e) {
        }
    }
}
