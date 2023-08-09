package fr.teamunc.base_unclib.models.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Listener;

import java.util.Date;
import java.util.Optional;

public abstract class UNCEvent implements Listener {
    private double probabilite;
    private Date dateDebut;
    private Long dureeMax;  // en millisecondes
    private Long intervalle; // en millisecondes

    /**
     * Constructeur pour les probabilités et la durée
     * @param probabilite Probabilité de déclenchement de l'événement (entre 0 et 1) toutes les {@link intervalle} minutes
     * @param intervalle Intervalle de temps entre chaque déclenchement de l'événement (en minutes)
     * @param startTime Date sur laquelle l'intervalle de temps se base
     * @param dureeMax Durée maximale de l'événement (en minutes)
     */
    protected UNCEvent(double probabilite, Long intervalle, Date startTime, long dureeMax) {

        // clause guard pour les paramètres
        if (probabilite < 0 || probabilite > 1) {
            throw new IllegalArgumentException("La probabilité doit être comprise entre 0 et 1");
        }
        if (intervalle <= 0) {
            throw new IllegalArgumentException("L'intervalle doit être supérieur à 0");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("La date de début ne peut pas être nulle");
        }
        if (dureeMax <= 0) {
            throw new IllegalArgumentException("La durée maximale doit être supérieure à 0");
        }


        this.probabilite = probabilite;
        this.dateDebut = startTime;
        this.dureeMax = dureeMax * 60 * 1000; // Convertir en millisecondes
        this.intervalle = intervalle * 60 * 1000; // Convertir en millisecondes
    }

    protected UNCEvent(Date startTime, long dureeMax) {

        // clause guard pour les paramètres
        if (startTime == null) {
            throw new IllegalArgumentException("La date de début ne peut pas être nulle");
        }
        if (dureeMax <= 0) {
            throw new IllegalArgumentException("La durée maximale doit être supérieure à 0");
        }

        this.dateDebut = startTime;
        this.dureeMax = dureeMax * 60 * 1000;
    }

    /**
     * utile pour savoir si l'événement est un événement avec une probabilité ou un événement fixe (qui se déclenche à une date précise)
     * @return true si l'événement est un événement fixe, false sinon
     */
    protected boolean isAFixedEvent() {
        return intervalle == null;
    }

    public abstract void onTick();

    public void onStart() {
        // Méthode par défaut pour onStart, peut être redéfinie par les sous-classes
    }

    public void onEnd() {
        // Méthode par défaut pour onEnd, peut être redéfinie par les sous-classes
    }

    public final void stopEvent() {
        // Code pour mettre fin à l'événement
    }
}
