# Design Pattern : State Pattern

## Contexte

Dans le reservation-service, une reservation passe par differents etats au cours de son cycle de vie :
- **CONFIRMED** : la reservation est confirmee et active
- **CANCELLED** : la reservation a ete annulee
- **COMPLETED** : la reservation est terminee

## Probleme

Sans pattern, la gestion des transitions d'etat se ferait avec des blocs `if/else` ou `switch` dans le service, rendant le code difficile a maintenir et a etendre. Chaque ajout d'etat necessiterait de modifier toutes les methodes de transition.

## Solution : State Pattern

Le **State Pattern** encapsule le comportement lie a chaque etat dans des classes separees, permettant a l'objet de modifier son comportement lorsque son etat interne change.

### Structure

```
ReservationState (interface)
    |-- confirm(ReservationContext)
    |-- cancel(ReservationContext)
    |-- complete(ReservationContext)

ConfirmedState (implements ReservationState)
    |-- confirm() -> IllegalStateException (deja confirme)
    |-- cancel() -> passe a CancelledState
    |-- complete() -> passe a CompletedState

CancelledState (implements ReservationState)
    |-- confirm() -> IllegalStateException
    |-- cancel() -> IllegalStateException
    |-- complete() -> IllegalStateException

CompletedState (implements ReservationState)
    |-- confirm() -> IllegalStateException
    |-- cancel() -> IllegalStateException
    |-- complete() -> IllegalStateException

ReservationContext
    |-- reservation: Reservation
    |-- state: ReservationState
    |-- confirm(), cancel(), complete() -> delegue au state courant
```

### Fichiers concernes

- `com.ynov.reservationservice.pattern.ReservationState` - Interface definissant les transitions
- `com.ynov.reservationservice.pattern.ConfirmedState` - Etat confirme (transitions autorisees : cancel, complete)
- `com.ynov.reservationservice.pattern.CancelledState` - Etat annule (aucune transition autorisee)
- `com.ynov.reservationservice.pattern.CompletedState` - Etat complete (aucune transition autorisee)
- `com.ynov.reservationservice.pattern.ReservationContext` - Contexte qui encapsule la reservation et delegue au state

### Utilisation dans ReservationService

```java
public Reservation cancelReservation(Long id) {
    Reservation reservation = getReservationById(id);
    ReservationContext context = new ReservationContext(reservation);
    context.cancel(); // Delegue au state courant
    return reservationRepository.save(context.getReservation());
}
```

### Avantages

1. **Principe ouvert/ferme (OCP)** : ajout d'un nouvel etat sans modifier le code existant
2. **Responsabilite unique (SRP)** : chaque etat gere ses propres regles de transition
3. **Elimination des conditionnels** : plus de `if/switch` sur le statut
4. **Securite des transitions** : les transitions invalides sont rejetees par l'etat lui-meme

### Diagramme de transitions

```
CONFIRMED --cancel()--> CANCELLED
CONFIRMED --complete()--> COMPLETED
CANCELLED --X (aucune transition)
COMPLETED --X (aucune transition)
```
