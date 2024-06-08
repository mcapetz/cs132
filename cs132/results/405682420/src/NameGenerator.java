// package minijava.visitor;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


public class NameGenerator {
    
    int name_count;
    int name_count_w;
    int name_count_null;
    int name_count_program;
    int name_count_label;

    public NameGenerator() {
        this.name_count = 0;
        this.name_count_w = 0;
        this.name_count_null = 1;
        this.name_count_program = 0;
        this.name_count_label = 0;
    }

    public String generateName() {
        String name = "v" + String.valueOf(this.name_count);
        this.name_count ++;
        return name;
    }

    public String generateNameW() {
        String name = "w" + String.valueOf(this.name_count_w);
        this.name_count_w ++;
        return name;
    }

    public String generateNameNull() {
        String name = "null" + String.valueOf(this.name_count_null);
        this.name_count_null ++;
        return name;
    }

    public String generateNameProgram() {
        String name = "program" + String.valueOf(this.name_count_program);
        this.name_count_program ++;
        return name;
    }

    public String generateNameLabel() {
        String name = "label" + String.valueOf(this.name_count_label);
        this.name_count_label ++;
        return name;
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;  // Return the input string if it's null or empty
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    
}
