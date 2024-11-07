import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlots {
   private final ArrayList<TimeSlot> timeSlotList; // List to store all time slots

   // Constructor
   public TimeSlots() {
      this.timeSlotList = new ArrayList<>();
   }

   // Add a time slot to the list
   public boolean addTimeSlot(TimeSlot timeSlot) {
      // Check for conflicts before adding
      if (isConflict(timeSlot)) {
         return false; // Conflict found, not added
      }
      timeSlotList.add(timeSlot);
      return true; // Successfully added
   }

   // Check for conflicts with the given time slot
   private boolean isConflict(TimeSlot timeSlot) {
      for (TimeSlot ts : timeSlotList) {
         if (timeSlot.isEqual(ts)) {
            return true; // Conflict found
         }
      }
      return false; // No conflict
   }

   // Remove a time slot from the list
   public boolean removeTimeSlot(TimeSlot timeSlot) {
      if (timeSlot == null) {
         return false; // Prevent removing a null value
      }
      return timeSlotList.remove(timeSlot); // Returns true if removed, false if not found
   }

   // Find a time slot by day and time
   public TimeSlot findTimeSlot(DayOfWeek day, LocalTime startTime) {
      for (TimeSlot ts : timeSlotList) {
         if (ts.getDay().equals(day) && ts.getStartTime().equals(startTime)) {
            return ts; // Return the time slot if found
         }
      }
      return null; // Return null if the time slot is not found
   }

   // Output all time slots in a string format
   @Override
   public String toString() {
      StringBuilder output = new StringBuilder();
      for (TimeSlot ts : timeSlotList) {
         output.append(ts.toString()).append(" "); // Appending with StringBuilder
      }
      return output.toString().trim(); // Remove trailing space
   }

   // Get all time slots as a new list to avoid external modification
   public List<TimeSlot> getAllTimeSlots() {
      return new ArrayList<>(timeSlotList); // Returning a copy to protect original list
   }
}