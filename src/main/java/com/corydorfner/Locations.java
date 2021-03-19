package com.corydorfner;

import java.io.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static InputStream inputStream = Locations.class.getClassLoader().getResourceAsStream("locations.dat");

    public static void main(String[] args) throws IOException {
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src\\main\\resources\\locations.dat")))){
            for(Location location : locations.values()){
                locFile.writeObject(location);
            }
        }
    }

    static {
        try (ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(inputStream))){
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) reader.readObject();
                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        }catch (InvalidClassException e){
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException io){
            System.out.println("IO Exception " + io.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
