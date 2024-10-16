/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analytics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import model.User;

/**
 *
 * @author Bhavya Reddy
 */
public class UserMapComparator implements Comparator<User> {

    HashMap<Integer, Integer> userPostMap;

    public UserMapComparator(HashMap<Integer, Integer> userPostMap) {
        this.userPostMap = userPostMap;
    }

    @Override
    public int compare(User o1, User o2) {
        Integer postCount1 = userPostMap.getOrDefault(o1.getId(), 0);
        Integer postCount2 = userPostMap.getOrDefault(o2.getId(), 0);

        return Integer.compare(postCount1, postCount2);
    }
}
