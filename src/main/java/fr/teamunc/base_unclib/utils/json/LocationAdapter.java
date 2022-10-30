package fr.teamunc.base_unclib.utils.json;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;
import java.lang.reflect.Type;


public class LocationAdapter implements JsonSerializer<Location>, JsonDeserializer<Location> {
    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String world = object.get("world").getAsString();
        double x = object.get("x").getAsDouble();
        double y = object.get("y").getAsDouble();
        double z = object.get("z").getAsDouble();
        float yaw = object.get("yaw").getAsFloat();
        float pitch = object.get("pitch").getAsFloat();
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    @Override
    public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("world", src.getWorld().getName());
        object.addProperty("x", src.getX());
        object.addProperty("y", src.getY());
        object.addProperty("z", src.getZ());
        object.addProperty("yaw", src.getYaw());
        object.addProperty("pitch", src.getPitch());
        return object;
    }
}

