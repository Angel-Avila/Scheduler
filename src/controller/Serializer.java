package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.Schedule;

public class Serializer {
	
	public void serialize(List<Schedule> schedules, String fileName) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(schedules);
		} catch (IOException e) {
			System.out.println("A problem ocurred during serialization");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Schedule> deserialize(String fileName) {
		List<Schedule> schedules = null;
		
		try (ObjectInputStream in =  new ObjectInputStream(new FileInputStream(fileName))) {
			schedules = (List<Schedule>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("A problem ocurred during unserialization");
			System.out.println(e.getMessage());
		}
		
		return schedules;
	}
	
}
