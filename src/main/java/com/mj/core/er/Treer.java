package com.mj.core.er;

import com.mj.core.data.tree.Tree;
import com.mj.core.data.tree.TreeMerger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成树
 */
public class Treer {

    public static  List<Tree> genTree(List<TreeMerger> treeMergers) {
        List<TreeMerger> parents = getTreeParent(treeMergers);
        List<Tree> trees = new ArrayList<>();
        for (TreeMerger parent : parents) {
            trees.add(genTree(parent, treeMergers));
        }
        return trees;
    }

    private static  Tree genTree(TreeMerger parent, List<TreeMerger> treeMergers) {
        Tree root = new Tree();
        root.setId(parent.getId());
        root.setTitle(parent.getTitle());
        List<Tree> children = new ArrayList<>();
        for (TreeMerger treeMerger : treeMergers) {
            if (treeMerger.getPid().equals(parent.getId())) {
                Tree tree = genTree(treeMerger, treeMergers);
                children.add(tree);
            }
        }
        root.setChildren(children);
        return root;
    }

    private static  List<TreeMerger> getTreeParent(List<TreeMerger> treeMerger) {
        return treeMerger.stream().filter(tm -> tm.getPid().equals(0)).collect(Collectors.toList());
    }

}
