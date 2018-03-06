import java.util.HashMap;
import java.util.Map;

public class Hashmatique {
    public static void main(String[] args) {
        Hashmatique.hashMap();
    }
    public static void hashMap() {
        HashMap<String, String> trackList = new HashMap<>();
        trackList.put("6 Inch", "She walks in da club like nobody's bidness");
        trackList.put("Sorry", "I ain't sorry");
        trackList.put("All Night", "Trade you broken wings for mine");
        trackList.put("Sandcastles", "We build sandcastles");
        trackList.put("Hold Up", "They don't love me like a love you");

        String lyrics = trackList.get("6 Inch");
        System.out.println(lyrics);

        // for(String track : trackList.keySet()) {
        //     System.out.println(track);
        // }
        // for(String lyrs : trackList.values()) {
        //     System.out.println(lyrs);
        // }
        for (Map.Entry<String, String> song : trackList.entrySet()){
            System.out.println(song.getKey() + ": " + song.getValue());           
        }
    }
}