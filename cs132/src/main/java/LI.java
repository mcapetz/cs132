public class LI {
    private int start = Integer.MAX_VALUE;
    private int end = Integer.MIN_VALUE;

    public void startInstructionTime(int index) {
        start = Math.min(start, index);
    }

    public void endInstructionTime(int index) {
        end = Math.max(end, index);
    }

    public void setEndTime(int index) {
        end = index;
    }

    public int getStartTime() {
        return start;
    }

    public int getEndTime() {
        return end;
    }

    public boolean containsTime(int time) {
        return time <= end && time >= start;
    }

    public boolean overlapsWith(LI other) {
        // Two intervals [start1, end1] and [start2, end2] overlap if:
        // start1 <= end2 && end1 >= start2
        return this.start <= other.getEndTime() && this.end >= other.getStartTime();
    }
}