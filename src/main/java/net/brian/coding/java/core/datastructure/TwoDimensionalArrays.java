package net.brian.coding.java.core.datastructure;

/**
 * 二维数组的问题
 * 
 * 我们在初始化一个任意大小的2维数组。（为简化分析我们使用二维方阵）我们将用两种不同方式迭代同一个数组，分析结果
 * 两种迭代方式的性能差距很大
 * @author mohit
 *
 */
public class TwoDimensionalArrays {
    //数组大小：数组越大，性能差距越明显
    private static final int ARR_SIZE=999;
    public static void main(String[] args) {
        //新数组
        int arr[][]=new int[ARR_SIZE][ARR_SIZE];
        long currTime=System.currentTimeMillis();
        colMajor(arr); 
        System.out.println("Total time in colMajor : "+(System.currentTimeMillis()-currTime)+" ms");
        //新数组，与arr完全相同
        int arr1[][]=new int[ARR_SIZE][ARR_SIZE];
        currTime=System.currentTimeMillis();
        rowMajor(arr1); // this is the only difference in above
        System.out.println("Total time in col : "+(System.currentTimeMillis()-currTime) +" ms");
    }

    /**
     * 下面的代码按列优先遍历数组
     * 即在扫描下一列之前先扫描完本列
     * 
     */
    private static void colMajor(int arr[][]) {
        for(int i=0;i<ARR_SIZE;i++){
            for (int j=0;j<ARR_SIZE;j++){
                //See this, we are traversing j first and then i
                arr[i][j]=i+j;
            }
        }
    }

    /**
     * 如果我们转换内外循环
     * 程序就以行优先顺序遍历数组
     * 即在扫描下一行之前先扫描完本行
     * 这意味着我们访问数组时每次都在访问不同的列（因此也在访问不同的页）
     * 代码微小的改变将导致这个程序花费更多的时间完成遍历
     */
    private static void rowMajor(int arr[][]) {
        for(int i=0;i<ARR_SIZE;i++){
            for (int j=0;j<ARR_SIZE;j++){
            /*看这个，我们先遍历j，然后遍历i，但是对于访问元素来说
             * 它们在更远的位置，所以需要花费的更多
             */
            arr[j][i]=i+j;
            }
        }
    }
}