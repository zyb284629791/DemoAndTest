package cn.Yb.Z.code.test.zd;

import cn.Yb.Z.code.test.zd.algorithm.GrayModel;

/**
 * Created by zyb on 2017/03/03.
 */
public class Main {

    public static void main(String[] args) {
        GrayModel gm = new GrayModel();
        Double[][] array = new Double[1][5];
        for (Integer i = 0; i < 5;i++) {
            array[0][i] = Double.parseDouble(i + 3 + "");
            System.out.print(array[0][i] + "\t");
        }
        System.out.println();
        // set initialArray
        Double[] initialArray = new Double[]{1.0,3.0,5.0,7.0,9.0};
        gm.setInitialArray(initialArray);
        Double[] similarityDegres = gm.getSimilarityDegres(array);
        for (Double similarityDegre : similarityDegres) {
            System.out.print(similarityDegre + "\t");
        }
        System.out.println(gm.getSimilarityDegre(array[0]));
    }

}
