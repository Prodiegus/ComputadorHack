import java.util.Arrays;
import java.util.Objects;

public class Memory {
    /**
     * this class is used to manage the memory of the computer
     */
    private final String[] slots;
    private final boolean verbose;

    private String M;
    public Memory(boolean verbose) {
        // constructor
        this.verbose = verbose;
        this.slots = new String[65536];
        this.M = "0000000000000000";
        // we need to initialize all the slots
        initSlots();
        if (verbose) {
            System.out.println("\nMemory was successfully initialized.");
        }
    }
    public void setSlot(int slot, String value) {
        // this method will set the value of a slot
        if (verbose) {
            System.out.println("\nSetting the value of memory slot " + slot + " to " + value);
        }
        this.slots[slot] = value;
    }
    public String getSlot(int slot) {
        // this method will return the value of a slot
        if (verbose) {
            System.out.println("\nGetting the value of memory slot " + slot);
        }
        return this.slots[slot];
    }

    public void setM(String value) {
        // this method will set the value of the A register
        if (verbose) {
            System.out.println("\nSetting the value of the memory M register to " + value);
        }
        this.M = value;
    }

    public String getM() {
        // this method will return the value of the A register
        if (verbose) {
            System.out.println("\nGetting the value of the memory M register");
        }
        return this.M;
    }

    private void seeSlots() {
        // this method will show all the slots of the memory
        for (int i = 0; i < this.slots.length; i++) {
            System.out.println("Slot " + i + ": " + this.slots[i]);
        }
    }

    public void checkMemory() {
        // this method will show all the slots of the memory except the one on 0
        for (int i = 0; i < this.slots.length; i++) {
            if (!Objects.equals(this.slots[i], "0000000000000000")) {
                System.out.println("Slot " + i + ": " + this.slots[i]);
            }
        }
    }

    private void initSlots() {
        // this method will initialize all the slots of the memory
        Arrays.fill(this.slots, "0000000000000000");
    }


    public static void main(String[] args) {
        // this is the main method
        // we will use it to test the methods of this class
        Memory memory = new Memory(true);
        memory.setSlot(0, "0000000000000000");
        memory.setSlot(1, "0000000000000001");
        memory.setSlot(2, "0000000000000010");
        memory.setSlot(3, "0000000000000011");
        memory.setSlot(4, "0000000000000100");
        System.out.println(memory.getSlot(0));
        System.out.println(memory.getSlot(1));
        System.out.println(memory.getSlot(2));
        System.out.println(memory.getSlot(3));
        System.out.println(memory.getSlot(4));
        memory.setM(memory.getSlot(1));
        System.out.println(memory.getM());
        memory.seeSlots();
    }

}
