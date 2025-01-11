package ro.ase.csie.designPatterns.MihneaBuilder;

import ro.ase.csie.models.Room;

public class RoomBuilder implements IBuilder {

    private Room room;

    public RoomBuilder() {
        room = new Room(0, "Unknown", -10, "Unknown", 0,
                "Unknown", true, false, false, 0 );
    }
    @Override
    public Room build() {
        return room;
    }

    public RoomBuilder setIdRoom(int id){
        this.room.setIdRoom(id);
        return this;
    }

    public RoomBuilder setName(String name){
        this.room.setName(name);
        return this;
    }

    public RoomBuilder setFloor(int floor){
        this.room.setFloor(floor);
        return this;
    }
    public RoomBuilder setLocation(String location){
        this.room.setLocation(location);
        return this;
    }
    public RoomBuilder setCapacity(int capacity){
        this.room.setCapacity(capacity);
        return this;
    }
    public RoomBuilder setType(String type){
        this.room.setType(type);
        return this;
    }
    public RoomBuilder setHasProjector(boolean hasProjector){
        this.room.setHasProjector(hasProjector);
        return this;
    }
    public RoomBuilder setHasSmartBoard(boolean hasSmartBoard){
        this.room.setHasSmartBoard(hasSmartBoard);
        return this;
    }

    public RoomBuilder setPricePerDay(double pricePerDay){
        this.room.setPricePerDay(pricePerDay);
        return this;
    }


}
