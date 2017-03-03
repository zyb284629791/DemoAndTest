package cn.Yb.Z.code.test.demo;

/**
 * Created by zyb on 2017/03/03.
 */
public class CeilSplit {
    /**
     * 10、一个细胞，从第三天开始每天都分裂出一个新细胞，新细胞成长到第三天后每天又分裂一个新细胞，
     * 问第n天时有多少细胞？请写一段代码，输入参数为n，计算出第n天的细胞数。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(split(6));
    }

    /**
     * 1,1,2(+1),3(+1),5(+2),8(+3),12(+4),
     * @param days
     * @return
     */

    public static int split(int days) {
        int num = 1;
        if(days < 0 ){
            throw new RuntimeException("天数小于0");
        }
        if (days > 2){
            num += split(days - 2) + split(days - 1) ;
        }
        return num;
    }
}
