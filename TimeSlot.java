import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeSlot {
   private final DayOfWeek day;
   private final LocalTime startTime;
   private final LocalTime endTime;

   // Constructor to initialize the time slot
   public TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
      this.day = day;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   // Getters for the fields
   public DayOfWeek getDay() {
      return day;
   }

   public LocalTime getStartTime() {
      return startTime;
   }

   public LocalTime getEndTime() {
      return endTime;
   }

   // toString method
   @Override
   public String toString() {
      return day.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "-" + startTime + "-" + endTime;
   }

   // isEqual method to compare two TimeSlot objects (you could use equals() instead)
   public boolean isEqual(TimeSlot other) {
      if (other == null) {
         return false;
      }
      return this.day.equals(other.day) &&
            this.startTime.equals(other.startTime) &&
            this.endTime.equals(other.endTime);
   }

   // Overriding equals to allow comparison with other TimeSlot objects
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      TimeSlot other = (TimeSlot) obj;
      return day == other.day &&
            startTime.equals(other.startTime) &&
            endTime.equals(other.endTime);
   }

   // Overriding hashCode to ensure consistency when using in hash-based collections
   @Override
   public int hashCode() {
      int result = day.hashCode();
      result = 31 * result + startTime.hashCode();
      result = 31 * result + endTime.hashCode();
      return result;
   }
}