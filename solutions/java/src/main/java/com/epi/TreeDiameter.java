package com.epi;

import java.util.ArrayList;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TreeDiameter {
  // @include
  public static class TreeNode {
    ArrayList<Pair<TreeNode, Double>> edges = new ArrayList<Pair<TreeNode, Double>>();
  }

  public static double computeDiameter(TreeNode T) {
    return T != null ? computeHeightAndDiameter(T).getSecond() : 0.0;
  }

  // Return (height, diameter) pair.
  private static Pair<Double, Double> computeHeightAndDiameter(TreeNode r) {
    double diameter = Double.MIN_VALUE;
    double[] height = { 0.0, 0.0 }; // store the max two heights.
    for (Pair<TreeNode, Double> e : r.edges) {
      Pair<Double, Double> heightDiameter = computeHeightAndDiameter(e
          .getFirst());
      if (heightDiameter.getFirst() + e.getSecond() > height[0]) {
        height[1] = height[0];
        height[0] = heightDiameter.getFirst() + e.getSecond();
      } else if (heightDiameter.getFirst() + e.getSecond() > height[1]) {
        height[1] = heightDiameter.getFirst() + e.getSecond();
      }
      diameter = Math.max(diameter, heightDiameter.getSecond());
    }
    return new Pair<Double, Double>(height[0], Math.max(diameter, height[0]
        + height[1]));
  }

  // @exclude

  public static void main(String[] args) {
    TreeNode r = null;
    assert (0.0 == computeDiameter(r));
    r = new TreeNode();
    r.edges.add(new Pair<TreeNode, Double>(new TreeNode(), 10.0));
    r.edges.get(0).getFirst().edges.add(new Pair<TreeNode, Double>(
        new TreeNode(), 50.0));
    r.edges.add(new Pair<TreeNode, Double>(new TreeNode(), 20.0));
    assert (80 == computeDiameter(r));
    System.out.println(computeDiameter(r));
    r.edges.get(0).getFirst().edges.add(new Pair<TreeNode, Double>(
        new TreeNode(), 100.0));
    assert (150 == computeDiameter(r));
    System.out.println(computeDiameter(r));
  }
}
