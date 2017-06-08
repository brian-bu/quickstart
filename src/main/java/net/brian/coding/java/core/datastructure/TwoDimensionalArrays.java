package net.brian.coding.java.core.datastructure;

/**
 * ��ά���������
 * 
 * �����ڳ�ʼ��һ�������С��2ά���顣��Ϊ�򻯷�������ʹ�ö�ά�������ǽ������ֲ�ͬ��ʽ����ͬһ�����飬�������
 * ���ֵ�����ʽ�����ܲ��ܴ�
 * @author mohit
 *
 */
public class TwoDimensionalArrays {
    //�����С������Խ�����ܲ��Խ����
    private static final int ARR_SIZE=999;
    public static void main(String[] args) {
        //������
        int arr[][]=new int[ARR_SIZE][ARR_SIZE];
        long currTime=System.currentTimeMillis();
        colMajor(arr); 
        System.out.println("Total time in colMajor : "+(System.currentTimeMillis()-currTime)+" ms");
        //�����飬��arr��ȫ��ͬ
        int arr1[][]=new int[ARR_SIZE][ARR_SIZE];
        currTime=System.currentTimeMillis();
        rowMajor(arr1); // this is the only difference in above
        System.out.println("Total time in col : "+(System.currentTimeMillis()-currTime) +" ms");
    }

    /**
     * ����Ĵ��밴�����ȱ�������
     * ����ɨ����һ��֮ǰ��ɨ���걾��
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
     * �������ת������ѭ��
     * �������������˳���������
     * ����ɨ����һ��֮ǰ��ɨ���걾��
     * ����ζ�����Ƿ�������ʱÿ�ζ��ڷ��ʲ�ͬ���У����Ҳ�ڷ��ʲ�ͬ��ҳ��
     * ����΢С�ĸı佫����������򻨷Ѹ����ʱ����ɱ���
     */
    private static void rowMajor(int arr[][]) {
        for(int i=0;i<ARR_SIZE;i++){
            for (int j=0;j<ARR_SIZE;j++){
            /*������������ȱ���j��Ȼ�����i�����Ƕ��ڷ���Ԫ����˵
             * �����ڸ�Զ��λ�ã�������Ҫ���ѵĸ���
             */
            arr[j][i]=i+j;
            }
        }
    }
}