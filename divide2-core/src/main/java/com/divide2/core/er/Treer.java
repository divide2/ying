package com.divide2.core.er;

import com.divide2.core.data.tree.ITreeMerger;
import com.divide2.core.data.tree.Tree;
import com.divide2.core.data.tree.payload.Payload;

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
     *
     * @param treeMergers treeMergers
     * @return list tree
     */
    public static <P extends Payload> List<Tree<P>> genTree(List<ITreeMerger<P>> treeMergers) {
        List<ITreeMerger<P>> parents = getTreeParent(treeMergers);
        List<Tree<P>> trees = new ArrayList<>();
        for (ITreeMerger<P> parent : parents) {
            trees.add(genTree(parent, treeMergers));
        }
        Collections.sort(trees);
        return trees;
    }

    private static <P extends Payload> Tree<P> genTree(ITreeMerger<P> parent, List<ITreeMerger<P>> treeMergers) {
        Tree<P> root = new Tree<>();
        root.setId(parent.getId());
        root.setLabel(parent.getLabel());
        root.setPayload(parent.getPayload());
        root.setOrderNum(parent.getOrderNum());
        List<Tree<P>> children = new ArrayList<>();
        for (ITreeMerger<P> treeMerger : treeMergers) {
            if (treeMerger.getPid().equals(parent.getId())) {
                Tree<P> tree = genTree(treeMerger, treeMergers);
                children.add(tree);
            }
        }
        Collections.sort(children);
        root.setChildren(children);
        return root;
    }

    private static <P extends Payload> List<ITreeMerger<P>> getTreeParent(List<ITreeMerger<P>> treeMerger) {
        return treeMerger.stream().filter(tm -> tm.getPid().equals(0)).collect(Collectors.toList());
    }

}
