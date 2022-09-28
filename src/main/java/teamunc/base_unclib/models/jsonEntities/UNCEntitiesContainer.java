package teamunc.base_unclib.models.jsonEntities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import org.bukkit.Location;
import teamunc.base_unclib.models.utils.json.LocationAdapter;

import java.io.*;

/**
 * Workflow:
 * 1. Create a new class A that extends UNCEntitySerializable
 * 2. Create a new class B that extends UNCEntitiesContainer
 * 3. Add the class A to the class B with registerEntity()
 *
 * Example with UNCTeam Lib :
 *      UNCTeam A = UNCTeam.builder("testTeam").build();
 *      UNCTeamContainer B = new UNCTeamContainer();
 *      B.registerEntity(A);
 *      B.save("teams");
 *      ***************
 *      UNCTeamContainer reloadedB = UNCTeamContainer.loadContainer("teams", UNCTeamContainer.class);
 *      Arrays.stream(reloadedB.getEntities()).forEach(System.out::println);
 */
public abstract class UNCEntitiesContainer implements Serializable {
    private static final long serialVersionUID = -1681012206529286330L;

    // * JSON SAVING AND LOADING * //
    private static File pluginDataFile;
    public static void init(File dataFolder) {
        pluginDataFile = dataFolder;
    }

    /**
     * the Gson instance used to serialize and deserialize the entities
     * @return the Gson instance
     */
    public static Gson getGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Location.class, new LocationAdapter())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping()
                .enableComplexMapKeySerialization()
                .create();
    }

    /**
     * save the entities in json format
     * using the Gson library
     * at the specified path
     * @param path the path to save the entities
     */
    public void save(String fileName) {
        String json = getGson().toJson(this);
        try {
            File file = new File(pluginDataFile + "/" + fileName + ".json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load the entities from json format
     * using the Gson library
     * at the specified path
     * @param fileName the name of the file to load the entities
     */
    public static UNCEntitiesContainer loadContainer(String fileName) throws FileNotFoundException {
        return loadContainer(fileName, UNCEntitiesContainer.class);
    }

    /**
     * load the entities from json format
     * using the Gson library
     * at the specified path
     * @param fileName the name of the file to load the entities
     */
    public static <T extends UNCEntitiesContainer> T loadContainer(String fileName, Class<T> classOfT) throws FileNotFoundException {
        File file = new File(pluginDataFile + "/" + fileName + ".json");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return getGson().fromJson(reader, classOfT);
    }
}
