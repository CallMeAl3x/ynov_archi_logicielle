# Design Pattern : State Pattern

## Pourquoi ce pattern ?

Le cycle de vie d'une reservation suit un automate a etats finis : une reservation est **CONFIRMED** a la creation, puis peut etre **CANCELLED** ou **COMPLETED**. Une fois annulee ou terminee, aucune autre transition n'est possible.

Sans pattern, on se retrouverait avec des blocs `if/else` ou `switch` repetes dans chaque methode du service :

```java
// Sans State Pattern - code fragile et repetitif
public void cancel(Reservation r) {
    if (r.getStatus() == CONFIRMED) {
        r.setStatus(CANCELLED);
    } else if (r.getStatus() == CANCELLED) {
        throw new IllegalStateException("Deja annulee");
    } else if (r.getStatus() == COMPLETED) {
        throw new IllegalStateException("Impossible d'annuler une reservation terminee");
    }
}
```

Ce code viole le principe ouvert/ferme (OCP) : ajouter un nouvel etat (ex: `PENDING`, `NO_SHOW`) necessite de modifier toutes les methodes existantes. Le risque d'oubli ou d'erreur augmente avec chaque etat ajoute.

## Le State Pattern (GoF - Behavioral)

Le **State Pattern** resout ce probleme en encapsulant le comportement de chaque etat dans sa propre classe. L'objet delegue les actions a son etat courant, qui decide si la transition est autorisee ou non.

### Diagramme de classes

```
        +--------------------+
        | ReservationState   |  <-- Interface
        |--------------------|
        | + confirm(context) |
        | + cancel(context)  |
        | + complete(context)|
        +--------------------+
           ^       ^       ^
           |       |       |
  +--------+--+ +--+------+--+ +--+-----------+
  |ConfirmedSt| |CancelledSt | |CompletedSt   |
  |-----------|  |------------|  |--------------|
  |confirm: X |  |confirm: X |  |confirm: X   |
  |cancel: OK |  |cancel: X  |  |cancel: X    |
  |complete:OK|  |complete: X|  |complete: X  |
  +-----------+  +-----------+  +--------------+

        +---------------------+
        | ReservationContext   |
        |---------------------|
        | - reservation       |
        | - state             |
        |---------------------|
        | + confirm()         |  --> delegue a state.confirm(this)
        | + cancel()          |  --> delegue a state.cancel(this)
        | + complete()        |  --> delegue a state.complete(this)
        +---------------------+
```

### Diagramme de transitions

```
             cancel()
  CONFIRMED ──────────> CANCELLED
      │                    (etat final)
      │ complete()
      v
  COMPLETED
  (etat final)
```

### Implementation

**Interface** (`ReservationState.java`) :

```java
public interface ReservationState {
    void confirm(ReservationContext context);
    void cancel(ReservationContext context);
    void complete(ReservationContext context);
}
```

**Etat CONFIRMED** (`ConfirmedState.java`) - seul etat autorisant des transitions :

```java
public class ConfirmedState implements ReservationState {
    public void confirm(ReservationContext context) {
        throw new IllegalStateException("Reservation is already confirmed");
    }
    public void cancel(ReservationContext context) {
        context.getReservation().setStatus(ReservationStatus.CANCELLED);
        context.setState(new CancelledState());
    }
    public void complete(ReservationContext context) {
        context.getReservation().setStatus(ReservationStatus.COMPLETED);
        context.setState(new CompletedState());
    }
}
```

**Etats finaux** (`CancelledState.java`, `CompletedState.java`) - refusent toute transition :

```java
public class CancelledState implements ReservationState {
    public void confirm(ReservationContext ctx)  { throw new IllegalStateException("Cannot confirm a cancelled reservation"); }
    public void cancel(ReservationContext ctx)   { throw new IllegalStateException("Reservation is already cancelled"); }
    public void complete(ReservationContext ctx) { throw new IllegalStateException("Cannot complete a cancelled reservation"); }
}
```

**Contexte** (`ReservationContext.java`) - encapsule la reservation et delegue :

```java
public class ReservationContext {
    private Reservation reservation;
    private ReservationState state;

    public ReservationContext(Reservation reservation) {
        this.reservation = reservation;
        this.state = resolveState(reservation.getStatus()); // determine l'etat initial
    }

    public void cancel()   { state.cancel(this); }
    public void complete() { state.complete(this); }
}
```

### Utilisation dans le service

```java
public Reservation cancelReservation(Long id) {
    Reservation reservation = getReservationById(id);
    ReservationContext context = new ReservationContext(reservation);
    context.cancel(); // delegue au state courant
    return reservationRepository.save(context.getReservation());
}
```

### Fichiers

| Fichier | Role |
|---------|------|
| `pattern/ReservationState.java` | Interface des transitions |
| `pattern/ConfirmedState.java` | Autorise cancel et complete |
| `pattern/CancelledState.java` | Etat final, refuse tout |
| `pattern/CompletedState.java` | Etat final, refuse tout |
| `pattern/ReservationContext.java` | Enveloppe la reservation et delegue |
| `service/ReservationService.java` | Utilise le contexte dans cancel/complete |

## Pourquoi pas un autre pattern ?

| Pattern envisage | Raison du rejet |
|-----------------|-----------------|
| **Builder** | La construction d'une reservation est simple (4 champs), pas besoin de construction par etapes |
| **Strategy** | Adapte quand on choisit un algorithme au runtime, ici c'est l'etat qui dicte le comportement |
| **Observer** | Kafka remplit deja ce role pour la communication inter-services |

Le **State Pattern** est le choix le plus naturel car le probleme est intrinsequement lie a un automate a etats avec des regles de transition strictes.

## Principes SOLID respectes

- **SRP** : chaque classe d'etat a une seule responsabilite (gerer ses transitions)
- **OCP** : ajouter un etat (ex: `PendingState`) = creer une nouvelle classe sans toucher aux existantes
- **LSP** : tous les etats implementent la meme interface et sont interchangeables
- **DIP** : le service depend de l'interface `ReservationState`, pas des implementations concretes
