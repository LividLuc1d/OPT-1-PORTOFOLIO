import java.util.ArrayList;
import java.util.Scanner;

class Oefening {
    private String naam;
    private String spiergroep;
    private int aantalSets;
    private int aantalHerhalingen;
    private int rusttijd;

    public Oefening(String naam, String spiergroep, int aantalSets, int aantalHerhalingen, int rusttijd) {
        this.naam = naam;
        this.spiergroep = spiergroep;
        this.aantalSets = aantalSets;
        this.aantalHerhalingen = aantalHerhalingen;
        this.rusttijd = rusttijd;
    }

    public void toonGegevens() {
        System.out.printf("Oefening voor %s: herhaal %d keer (rust tussendoor %d seconden) %d %s%n", spiergroep, aantalSets, rusttijd, aantalHerhalingen, naam);
    }

    public boolean isOefening(String naam) {
        return naam.equals(this.naam);
    }
}

class Trainingsschema {
    private String klant;
    private String trainer;
    private ArrayList<Oefening> oefeningen;

    public Trainingsschema(String klant) {
        this.klant = klant;
        this.oefeningen = new ArrayList<>();
    }

    public Trainingsschema(String klant, String trainer) {
        this.klant = klant;
        this.trainer = trainer;
        this.oefeningen = new ArrayList<>();
    }

    public void voegOefeningToe(Oefening oefening) {
        oefeningen.add(oefening);
    }

    public boolean isSchemaVoor(String klant) {
        return this.klant.equals(klant);
    }

    private String toonTrainer() {
        return this.trainer;
    }

    public void toonGegevens() {
        if (trainer != null) {
            System.out.println("Uw trainer: " + toonTrainer());
        }
        for (Oefening oefening : oefeningen) {
            oefening.toonGegevens();
        }
    }

    public String getKlant() {
        return klant;
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Wat is uw naam?");
        String naam = scanner.nextLine();

        Trainingsschema schema = zoekTrainingsschema(naam);
        if (schema != null) {
            schema.toonGegevens();
        } else {
            System.out.println("U komt niet voor in ons systeem.");
        }
    }

    public static Oefening getOefening(String naam) {
        ArrayList<Oefening> exercises = oefeningen();
        for (Oefening oefening : exercises) {
            if (oefening.isOefening(naam)) {
                return oefening;
            }
        }
        return null;
    }

    private static Trainingsschema zoekTrainingsschema(String naam) {
        Trainingsschema[] trainingsschemas = {
                new Trainingsschema("Kim Kardashian", "Daphne Jongejans"),
                new Trainingsschema("Arnold Schwarzenegger")
        };

        for (Trainingsschema schema : trainingsschemas) {
            if (schema.isSchemaVoor(naam)) {
                toevoegenOefeningen(schema, schema.getKlant());
                return schema;
            }
        }
        return null;
    }

    private static void toevoegenOefeningen(Trainingsschema schema, String klant) {
        if (klant.equals("Kim Kardashian")) {
            schema.voegOefeningToe(getOefening("Supermans"));
            schema.voegOefeningToe(getOefening("Sit-ups"));
        } else if (klant.equals("Arnold Schwarzenegger")) {
            schema.voegOefeningToe(getOefening("Push-ups"));
            schema.voegOefeningToe(getOefening("Sit-ups"));
            schema.voegOefeningToe(getOefening("Squats"));
            schema.voegOefeningToe(getOefening("Supermans"));
            schema.voegOefeningToe(getOefening("Chest-dips"));
        }
    }

    public static ArrayList<Oefening> oefeningen() {
        ArrayList<Oefening> exercises = new ArrayList<>();

        // Add all exercises to the list
        exercises.add(new Oefening("Push-ups", "Armen", 3, 3, 30));
        exercises.add(new Oefening("Sit-ups", "Buik", 3, 4, 30));
        exercises.add(new Oefening("Squats", "Benen", 2, 5, 30));
        exercises.add(new Oefening("Supermans", "Rug", 3, 3, 15));
        exercises.add(new Oefening("Chest-dips", "Borst", 3, 3, 30));

        return exercises;
    }
}
