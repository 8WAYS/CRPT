package converterYAML;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Convertor {
    public static void YamlWriter(String filename, String YamlFileName) {
        try {
           List<Map<String,String>> data = new ArrayList<>();
           BufferedReader reader = new BufferedReader(new FileReader(filename));
           String line;
           while ((line = reader.readLine()) != null){
               line = "key:" + line;
               String[] part = line.split(":");
               if(part.length == 2){
                   String key = part[0].trim();
                   String value = part[1].trim();
                   Map<String, String> entry = new LinkedHashMap<>();
                   entry.put(key, value);
                   data.add(entry);
               }
           }
           reader.close();

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

            Yaml yaml = new Yaml(options);
            String yamlData = yaml.dump(data);
            FileWriter writer = new FileWriter(YamlFileName);
            writer.write(yamlData);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object YamlReader(String packet, Yaml yaml){
        Object data = yaml.load(new ByteArrayInputStream(packet.getBytes()));
        return data;
    }
}
