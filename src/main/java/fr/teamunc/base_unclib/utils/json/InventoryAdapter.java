package fr.teamunc.base_unclib.utils.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

// TODO : Add support for inventory content
public class InventoryAdapter  extends TypeAdapter<Inventory> {
    @Override
    public void write(JsonWriter out, Inventory value) throws IOException {

    }

    @Override
    public Inventory read(JsonReader in) throws IOException {
        return null;
    }
}
