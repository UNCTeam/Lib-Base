package fr.teamunc.base_unclib.models.events;

import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UNCEventContainer extends UNCEntitiesContainer {
    private Map<String, UNCEventSave> events = new HashMap<>();
}
