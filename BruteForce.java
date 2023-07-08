import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    public static void main(String[] args) {
        int distance = 140;
        int restInterval = 30;
        List<Integer> warungLocations = List.of(10, 25, 30, 40, 50, 75, 80, 110, 130);
        
        List<Integer> bestStops = findBestStops(distance, restInterval, warungLocations);
        
        System.out.println("Titik-titik warung untuk berhenti:");
        for (int stop : bestStops) {
            System.out.println(stop + " km");
        }
    }
    
    public static List<Integer> findBestStops(int distance, int restInterval, List<Integer> warungLocations) {
        List<Integer> bestStops = new ArrayList<>();
        int minStops = Integer.MAX_VALUE;
        
        // Menggunakan algoritma Brute Force untuk mencoba semua kombinasi
        int numCombinations = (int) Math.pow(2, warungLocations.size());
        for (int i = 0; i < numCombinations; i++) {
            List<Integer> stops = new ArrayList<>();
            for (int j = 0; j < warungLocations.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    stops.add(warungLocations.get(j));
                }
            }
            
            // Periksa apakah kombinasi saat ini memungkinkan Tono menempuh perjalanan
            if (isValidStops(distance, restInterval, stops)) {
                int numStops = stops.size();
                if (numStops < minStops) {
                    minStops = numStops;
                    bestStops = stops;
                }
            }
        }
        
        return bestStops;
    }
    
    public static boolean isValidStops(int distance, int restInterval, List<Integer> stops) {
        int prevStop = 0;
        for (int stop : stops) {
            if (stop - prevStop > restInterval) {
                return false;
            }
            prevStop = stop;
        }
        if (distance - prevStop > restInterval) {
            return false;
        }
        return true;
    }
}
