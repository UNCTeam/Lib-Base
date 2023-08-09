package fr.teamunc.base_unclib.models.events;

import fr.teamunc.base_unclib.utils.helpers.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Listener;

import java.util.Date;
import java.util.List;

public abstract class UNCEvent implements Listener {
    @Getter
    protected String uniqueName = this.getClass().getSimpleName();
    protected double probabilite;
    protected Date dateDebut;
    protected Long dureeMax;  // en millisecondes
    protected Long intervalle; // en millisecondes
    protected Long tickPassed = 0L;


    @Getter @Setter
    protected List<Integer> activesPhasesNumber;

    /**
     * Constructeur pour les probabilités et la durée
     * @param probabilite Probabilité de déclenchement de l'événement (entre 0 et 1) toutes les {@link #intervalle} minutes
     * @param intervalle Intervalle de temps entre chaque déclenchement de l'événement (en millisecondes)
     * @param startTime Date sur laquelle l'intervalle de temps se base
     * @param dureeMax Durée maximale de l'événement (en millisecondes)
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
        this.dureeMax = dureeMax;
        this.intervalle = intervalle;
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
        this.dureeMax = dureeMax;
    }

    /**
     * utile pour savoir si l'événement est un événement avec une probabilité ou un événement fixe (qui se déclenche à une date précise)
     * @return true si l'événement est un événement fixe, false sinon
     */
    protected boolean isAFixedEvent() {
        return intervalle == null;
    }

    /**
     * METHODE A NE PAS OVERRIDE
     * Exécute {@link #onTick()}
     */
    public void tick() {
        tickPassed += 1;
        onTick();
    }
    /**
     * Executé à chaque tick durant l'événement
     * tickPassed est à 0 au premier call de cette méthode
     */
    protected abstract void onTick();


    /**
     * METHODE A NE PAS OVERRIDE
     * Démarre l'événement et exécute {@link #onStart()}
     */
    public void startEvent() {
        Message.Get().broadcastMessageToConsole(this.getClass().getSimpleName() + " (Starting event)");
        tickPassed = 0L;
        onStart();
    }
    /**
     * Executé au début de l'événement (juste avant le premier tick)
     * IL EST IMPORTANT D INITIALISER LES VARIABLES DANS CETTE METHODE CAR L INSTANCE DE L EVENT EST REUTILISEE
     * tickPassed est remis à 0 à chaque fois que l'événement est démarré
     */
    protected void onStart() {
        // Méthode par défaut pour onStart, peut être redéfinie par les sous-classes
        Message.Get().broadcastMessageToConsole(this.getClass().getSimpleName() + " (Nothing defined on onStart)");
    }

    /**
     * METHODE A NE PAS OVERRIDE
     * Termine l'événement et exécute {@link #onEnd()}
     */
    public void endEvent() {
        Message.Get().broadcastMessageToConsole(this.getClass().getSimpleName() + " (Ending event)");
        tickPassed = 0L;
        onEnd();
    }
    /**
     * Executé à la fin de l'événement
     * Cette méthode est appelée après le dernier tick
     */
    protected void onEnd() {
        // Méthode par défaut pour onEnd, peut être redéfinie par les sous-classes
        Message.Get().broadcastMessageToConsole(this.getClass().getSimpleName() + " (Nothing defined on onEnd)");
    }

    /**
     * Arrête l'événement et exécute {@link #onEnd()}
     */
    public final void stopEvent() {
        Message.Get().broadcastMessageToConsole(this.getClass().getSimpleName() + " (Stopping event)");
        endEvent();
    }

    public boolean isItTimeToStartEvent(Date date) {
        if (isAFixedEvent()) {
            // si l'événement est fixe, on vérifie si la date est après la date de début
            return date.after(dateDebut);
        } else {
            // Calculer le temps écoulé depuis dateDebut en millisecondes
            long elapsedMillis = date.getTime() - dateDebut.getTime();

            // Vérifier si elapsedMillis est un multiple de l'intervalle
            double ratio = (double) elapsedMillis / intervalle;

            // Si ratio est proche d'un nombre entier, cela signifie que c'est le bon moment
            // pour considérer le déclenchement de l'événement
            if (Math.abs(ratio - Math.round(ratio)) < 0.000001) {  // Pour gérer des imprécisions numériques
                return Math.random() < probabilite;
            }
        }
        return false;
    }

    public boolean isItTimeToEndEvent(Date now) {
        // Calculer le temps écoulé depuis dateDebut en millisecondes
        long elapsedMillis = now.getTime() - dateDebut.getTime();

        // Vérifier si elapsedMillis est supérieur à la durée maximale
        return elapsedMillis > dureeMax;
    }
}
