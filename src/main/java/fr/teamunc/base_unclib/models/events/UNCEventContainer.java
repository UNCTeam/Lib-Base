package fr.teamunc.base_unclib.models.events;

import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UNCEventContainer extends UNCEntitiesContainer {
    private List<UNCEvent> events = new ArrayList<>();
}
