package com.ying.core.er;

import com.ying.core.data.tree.Tree;
import com.ying.core.data.tree.TreeMerger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成树
 *
 * @author 123456
 */
public class Treer {

    /**
     * 生成树
     * @param treeMergers treeMergers
     * @return list tree
     */
    public static List<Tree> genTree(List<TreeMerger> treeMergers) {
        List<TreeMerger> parents = getTreeParent(treeMergers);
        List<Tree> trees = new ArrayList<>();
        for (TreeMerger parent : parents) {
            trees.add(genTree(parent, treeMergers));
        }
        Collections.sort(trees);
        return trees;
    }

    private static Tree genTree(TreeMerger parent, List<TreeMerger> treeMergers) {
        Tree root = new Tree();
        root.setId(parent.getId());
        root.setLabel(parent.getLabel());
        root.setPath(parent.getPath());
        root.setOrderNum(parent.getOrderNum());
        List<Tree> children = new ArrayList<>();
        for (TreeMerger treeMerger : treeMergers) {
            if (treeMerger.getPid().equals(parent.getId())) {
                Tree tree = genTree(treeMerger, treeMergers);
                children.add(tree);
            }
        }
        Collections.sort(children);
        root.setChildren(children);
        return root;
    }

    private static List<TreeMerger> getTreeParent(List<TreeMerger> treeMerger) {
        return treeMerger.stream().filter(tm -> tm.getPid().equals(0)).collect(Collectors.toList());
    }

}
