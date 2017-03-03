package cn.Yb.Z.code.test.zd.algorithm;

/**
 * 灰色模型算法
 * Created by zyb on 2017/03/03.
 */
public class GrayModel {

    /**
     * 1、存在初始序列（参数传递）
     * 2、初始化各企业算子
     * 3、计算相似度
     */

    private double[] array;

    public void setArray(double[] array) {
        this.array = array;
    }

    /**
     * 初始化算子
     * @param data
     * @return
     */
    public double[][] init(double[][] data) {
        if (data == null || data.length == 0) {
            throw new RuntimeException("传入参数为空!");
        }
        double[][] result = new double[data.length][];
        for (int i = 0; i < data.length; i++) {
            double[] nums = data[i];
            if (nums.length == 0) {
                result[i] = nums;
            }
            result[i] = new double[nums.length];
            double denominator = nums[0];
            if (denominator == 0) {
                throw new RuntimeException("第一个元素为0!");
            }
            for (int j = 0; j < nums.length; j++) {
                result[i][j] = nums[j] / denominator;
            }
        }
        return data;
    }

    /**
     * 计算相似度
     * @param data
     * @return
     */
    public double getSimilarityDegre(double[][] data) {
        return 0.0;
    }
}
