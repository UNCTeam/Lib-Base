package fr.teamunc.base_unclib.models.events;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class UNCEventSave implements Serializable {
    private boolean disabled = false;
    private List<UNCEventDatesSaves> dates;

    @Getter @Setter
    protected static class UNCEventDatesSaves implements Serializable {
        private Date date;
        private boolean disabled = false;
    }
}
