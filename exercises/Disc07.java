public class Disc07 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static boolean isBST(TreeNode T) {
        return isBSTHelper(T, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static boolean isBSTHelper(TreeNode T, int max, int min) {
        if (T == null) {
            return true;
        } else if (T.val >= max || T.val <= min) {
            return false;
        }
        return isBSTHelper(T.left, T.val, min)
                && isBSTHelper(T.right, max, T.val);
    }
}
