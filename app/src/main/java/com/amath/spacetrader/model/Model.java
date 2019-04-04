package com.amath.spacetrader.model;

import android.util.Log;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Universe;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class Model {

    /** the data repository */
    private Repository myRepository;
    private Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  Model instance = new Model();

    public static Model getInstance() { return instance; }

    /**
     * Make a new Model instance
     */
    private Model() {
        Log.d("initialization", "Create model");
        synchronized (this) {
            initializeAvailablePlanetNames();
        };

        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
        Log.d("initialization", String.valueOf(myRepository));
    }

    /** end Singleton Pattern */

    public static void setCurrentPlanet(Planet planet) {
        instance.myRepository.getGame().setCurrentPlanet(planet);
    }

    public void loadPlayer(Player player) {
        instance.myRepository.getGame().loadPlayer(player);
        instance.myRepository.update();
    }

    public Player getPlayer() {
        return instance.myRepository.getGame().getPlayer();
    }

    public static void loadDifficulty(GameDifficulty difficulty) {
        instance.myRepository.getGame().changeDifficulty(difficulty);
    }

    public Game getGame() {
        return instance.myRepository.getGame();
    }

    public static void loadUniverse(Universe universe) {
        instance.myRepository.getGame().loadUniverse(universe);
    }


    /**
     * Create a set of interactors to be used by the application
     */
    private void registerInteractors() {
        interactorMap.put("Game", new ConfigurationInteractor(myRepository));
        interactorMap.put("Universe", new UniverseInteractor(myRepository));
        interactorMap.put("SolarSystem", new SolarSystemInteractor(myRepository));
        interactorMap.put("Planet", new PlanetInteractor(myRepository));
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Market", new MarketInteractor(myRepository));
        interactorMap.put("Main", new MainInteractor(myRepository));
        interactorMap.put("Flight", new FlightInteractor(myRepository));
        interactorMap.put("RandomEvent", new RandomEventInteractor(myRepository));
    }

    public ConfigurationInteractor getConfigurationInteractor() {
        return (ConfigurationInteractor) interactorMap.get("Game");
    }

    public UniverseInteractor getUniverseInteractor() {
        return (UniverseInteractor) interactorMap.get("Universe");
    }

    public MarketInteractor getMarketInteractor() {
        return (MarketInteractor) interactorMap.get("Market");
    }

    public SolarSystemInteractor getSolarSystemInteractor() {
        return (SolarSystemInteractor) interactorMap.get("SolarSystem");
    }

    public PlanetInteractor getPlanetInteractor() {
        return (PlanetInteractor) interactorMap.get("Planet");
    }

    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    public FlightInteractor getFlightInteractor() {
        return (FlightInteractor) interactorMap.get("Flight");
    }

    public MainInteractor getMainInteractor() {
        return (MainInteractor) interactorMap.get("Main");
    }

    public RandomEventInteractor getRandomEventInteractor() {
        return (RandomEventInteractor) interactorMap.get("RandomEvent");
    }

//    public boolean save_locally(File file) {
//        return myRepository.serialize(file);
//    }

    private void initializeAvailablePlanetNames() {
        String[] names = {
                "Acamar",
                "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
                "Aldea",
                "Andevian",
                "Antedi",
                "Balosnee",
                "Baratas",
                "Brax",			// One of the heroes in Master of Magic
                "Bretel",		// This is a Dutch device for keeping your pants up.
                "Calondia",
                "Campor",
                "Capelle",		// The city I lived in while programming this game
                "Carzon",
                "Castor",		// A Greek demi-god
                "Cestus",
                "Cheron",
                "Courteney",	// After Courteney Cox…
                "Daled",
                "Damast",
                "Davlos",
                "Deneb",
                "Deneva",
                "Devidia",
                "Draylon",
                "Drema",
                "Endor",
                "Esmee",		// One of the witches in Pratchett's Discworld
                "Exo",
                "Ferris",		// Iron
                "Festen",		// A great Scandinavian movie
                "Fourmi",		// An ant, in French
                "Frolix",		// A solar system in one of Philip K. Dick's novels
                "Gemulon",
                "Guinifer",		// One way of writing the name of king Arthur's wife
                "Hades",		// The underworld
                "Hamlet",		// From Shakespeare
                "Helena",		// Of Troy
                "Hulst",		// A Dutch plant
                "Iodine",		// An element
                "Iralius",
                "Janus",		// A seldom encountered Dutch boy's name
                "Japori",
                "Jarada",
                "Jason",		// A Greek hero
                "Kaylon",
                "Khefka",
                "Kira",			// My dog's name
                "Klaatu",		// From a classic SF movie
                "Klaestron",
                "Korma",		// An Indian sauce
                "Kravat",		// Interesting spelling of the French word for "tie"
                "Krios",
                "Laertes",		// A king in a Greek tragedy
                "Largo",
                "Lave",			// The starting system in Elite
                "Ligon",
                "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
                "Magrat",		// The second of the witches in Pratchett's Discworld
                "Malcoria",
                "Melina",
                "Mentar",		// The Psilon home system in Master of Orion
                "Merik",
                "Mintaka",
                "Montor",		// A city in Ultima III and Ultima VII part 2
                "Mordan",
                "Myrthe",		// The name of my daughter
                "Nelvana",
                "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
                "Nyle",			// An interesting spelling of the great river
                "Odet",
                "Og",			// The last of the witches in Pratchett's Discworld
                "Omega",		// The end of it all
                "Omphalos",		// Greek for navel
                "Orias",
                "Othello",		// From Shakespeare
                "Parade",		// This word means the same in Dutch and in English
                "Penthara",
                "Picard",		// The enigmatic captain from ST:TNG
                "Pollux",		// Brother of Castor
                "Quator",
                "Rakhar",
                "Ran",			// A film by Akira Kurosawa
                "Regulas",
                "Relva",
                "Rhymus",
                "Rochani",
                "Rubicum",		// The river Ceasar crossed to get into Rome
                "Rutia",
                "Sarpeidon",
                "Sefalla",
                "Seltrice",
                "Sigma",
                "Sol",			// That's our own solar system
                "Somari",
                "Stakoron",
                "Styris",
                "Talani",
                "Tamus",
                "Tantalos",		// A king from a Greek tragedy
                "Tanuga",
                "Tarchannen",
                "Terosa",
                "Thera",		// A seldom encountered Dutch girl's name
                "Titan",		// The largest moon of Jupiter
                "Torin",		// A hero from Master of Magic
                "Triacus",
                "Turkana",
                "Tyrus",
                "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
                "Utopia",		// The ultimate goal
                "Vadera",
                "Vagra",
                "Vandor",
                "Ventax",
                "Xenon",
                "Xerxes",		// A Greek hero
                "Yew",			// A city which is in almost all of the Ultima games
                "Yojimbo",		// A film by Akira Kurosawa
                "Zalkon",
                "Zuul"			// From the first Ghostbusters movie
        };

        List<String> namesAsList = new LinkedList<>();
        for (String name: names) {
            ((LinkedList<String>) namesAsList).addLast(name);
        }
        java.util.Collections.shuffle(namesAsList);
        Planet.setAvailablePlanetNames(namesAsList);


    }


}
