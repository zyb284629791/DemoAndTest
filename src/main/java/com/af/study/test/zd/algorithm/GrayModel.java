package com.af.study.test.zd.algorithm;

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

    /**
     * 初始序列
     */
    private Double[] initialArray;

    /**
     * 初始序列的算子
     */
    private Double[] operatorArray;

    public void setInitialArray(Double[] initialArray) {
        this.initialArray = initialArray;
        operatorArray = initOperator(initialArray);
    }

    /**
     * 初始化算子
     * @param array 原始数组
     * @return
     */
    public Double[][] initOperators(Double[][] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("传入参数为空!");
        }
        Double[][] result = new Double[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = initOperator(array[i]);
        }
        return result;
    }

    /**
     * 初始化算子
     * @param array 原始数组
     * @return
     */
    public Double[] initOperator(Double[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("传入参数为空!");
        }
        Double[] nums ;
        if (array.length == 0) {
            nums = array;
        } else {
            nums = new Double[array.length];
            double denominator = array[0];
            if (denominator == 0) {
                throw new RuntimeException("第一个元素为0!");
            }
            for (int j = 0; j < array.length; j++) {
                nums[j] = array[j] / denominator;
            }
        }
        return nums;
    }

    /**
     * 计算所有企业的相似度
     * @param array 原始数组
     * @return
     */
    public Double[] getSimilarityDegres(Double[][] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("参数为空!");
        }
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            Double[] doubles = array[i];
            result[i] = getSimilarityDegre(doubles);
        }
        return result;
    }

    /**
     * 计算单个企业的相似度
     * @param array 原始数组
     * @return
     */
    public Double getSimilarityDegre(Double[] array) {
        Double result;
        if (array == null || array.length == 0) {
            throw new RuntimeException("参数为空!");
        } else {
            // 初始化算子
            Double[] operator = initOperator(array);

            // 计算相对关联度
            Double rcd = getRelativeCorrelationDegree(array,operator);
            Double acd = getAbsoluteCorrelationDegree(operator);
            result = 0.5 * rcd + 0.5 * acd;
        }
        return result;
    }

    /**
     * 计算绝对关联度
     * @param operator 算子数组
     * @return
     */
    private Double getAbsoluteCorrelationDegree(Double[] operator) {
        if (operator == null || operator.length == 0) {
            throw new RuntimeException("参数为空!");
        }
        // 计算分子
        Double s0 = getDefiniteIntegration(operatorArray);
        Double s1 = getDefiniteIntegration(operator);
        Double numerator = 1 + Math.abs(s0) + Math.abs(s1);
        // 计算分母
        Double[] s0s1 = getVectorArray(operator);
        Double definiteIntegration = getDefiniteIntegration(s0s1);
        Double denominator = numerator + Math.abs(definiteIntegration);
        Double acd = numerator / denominator;
        return acd;
    }

    // 计算定积分
    private Double getDefiniteIntegration(Double[] operator) {
        if (operator == null || operator.length == 0) {
            throw new RuntimeException("参数为空!");
        }
        Integ integ = new Integ();
        // 计算向量的值
        Double vectorValue = getVectorValue(operator);
        double value = integ.getValueTrapezia(1, vectorValue, 0.00001);
        return value;
    }

    /**
     * 计算相对关联度
     * @param array 原始数组
     * @param operator 算子数组
     * @return
     */
    public Double getRelativeCorrelationDegree(Double[] array,Double[] operator){
        Double rcd;
        if (array != null && array.length > 0 && operator != null && operator.length > 0) {
            //计算分子
            Double num1 = getMinValue(initialArray) - getMinValue(array);
            Double num2 = getMaxValue(initialArray) - getMaxValue(array);
            num1 = Math.abs(num1);
            num2 = Math.abs(num2);
            Double numerator = num1 + 0.5 * num2;

            //计算分母
            Double vectorValue = getMinusVectorValue(operator);
            Double denominator = vectorValue + 0.5 * num2;
            rcd = numerator / denominator;
        } else {
            throw new RuntimeException("参数为空!");
        }

        return rcd;
    }

    /**
     * 计算向量的值
     * @param operator 算子数组
     * @return
     */
    public Double getMinusVectorValue(Double[] operator){
        if (operator == null || operator.length == 0) {
            throw new RuntimeException("参数为空!");
        }
        Double[] vectorArray = getVectorArray(operator);
        Double vectorValue = getVectorValue(vectorArray);
        return vectorValue;
    }

    private Double getVectorValue(Double[] vectorArray) {
        if (vectorArray == null || vectorArray.length == 0) {
            throw new RuntimeException("参数为空!");
        }
        Double value = 0.0;
        for ( int i = 0; i < vectorArray.length; i ++) {
            value += vectorArray[i] * vectorArray[i];
        }
        value = Math.sqrt(value);
        return value;
    }

    /**
     * 计算与初始序列算子的向量差
     * @param operator
     * @return
     */
    private Double[] getVectorArray(Double[] operator) {
        return getVectorArray(operatorArray,operator);
    }

    /**
     * 计算两个向量的差
     * @param operator1 被减数
     * @param operator2 减数
     * @return
     */
    private Double[] getVectorArray(Double[] operator1,Double[] operator2) {
        if (operator1 == null || operator1.length == 0 || operator2 == null || operator2.length == 0) {
            throw new RuntimeException("参数为空!");
        }
        if (operator1.length != operator2.length) {
            throw new RuntimeException("向量长度不同！");
        }
        Double[] vectorArray = new Double[operator1.length];
        for (int i = 0 ; i < operator1.length; i ++) {
            vectorArray[i] = operator1[i] - operator2[i];
        }
        return vectorArray;
    }

    /**
     * 求数组中最小值
     * @param array
     * @return
     */
    public Double getMinValue(Double[] array){
        if (array == null || array.length == 0) {
            throw new RuntimeException("参数为空");
        }
        Double min = array[0];
        for (Double num : array) {
            min = min < num ? min : num;
        }
        return min;
    }

    /**
     * 求数组中最大值
     * @param array
     * @return
     */
    public Double getMaxValue(Double[] array){
        if (array == null || array.length == 0) {
            throw new RuntimeException("参数为空");
        }
        Double max = array[0];
        for (Double num : array) {
            max = max > num ? max : num;
        } 
        return max;
    }

    /**
     * 计算积分的类
     */
    class Integ extends Integral
    {
        public double func(double x)
        {
            return Math.exp(-x*x);
        }
    }
}
