// @author Ivan Sharov

package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class NearestRepetition {
  private static String randString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char) (rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  // @include
  public static int findNearestRepetition(List<String> s) {
    HashMap<String, Integer> stringToLocation = new HashMap<String, Integer>();
    int closestDis = Integer.MAX_VALUE;
    for (int i = 0; i < s.size(); ++i) {
      if (stringToLocation.containsKey(s.get(i))) {
        closestDis = Math.min(closestDis, i - stringToLocation.get(s.get(i)));
      }
      stringToLocation.put(s.get(i), i);
    }
    return closestDis;
  }

  // @exclude

  // O(n^2) checking
  private static int checkAnswer(List<String> s) {
    int closestDis = Integer.MAX_VALUE;
    for (int i = 0; i < s.size(); ++i) {
      for (int j = i + 1; j < s.size(); ++j) {
        if (s.get(i).equals(s.get(j))) {
          closestDis = Math.min(closestDis, j - i);
        }
      }
    }
    return closestDis;
  }

  public static void main(String[] args) {
    List<String> A = Arrays.asList("foo", "bar", "widget", "foo", "widget",
        "widget", "adnan");
    assert (checkAnswer(A) == findNearestRepetition(A));
    A = Arrays.asList("foo", "bar", "widget", "foo", "xyz", "widget", "bar",
        "adnan");
    assert (checkAnswer(A) == findNearestRepetition(A));
    A = Arrays.asList("foo", "bar", "widget", "adnan");
    assert (checkAnswer(A) == findNearestRepetition(A));
    A = Arrays.asList();
    assert (checkAnswer(A) == findNearestRepetition(A));
    A = Arrays.asList("foo", "foo", "foo");
    assert (checkAnswer(A) == findNearestRepetition(A));
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n = 0;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rnd.nextInt(10000) + 1;
      }
      List<String> s = new ArrayList<String>();
      for (int i = 0; i < n; ++i) {
        s.add(randString(rnd.nextInt(10) + 1));
      }
      assert (checkAnswer(s) == findNearestRepetition(s));
    }
  }
}
