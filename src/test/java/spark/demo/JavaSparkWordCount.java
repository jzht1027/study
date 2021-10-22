package com.spark.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JavaSparkWordCount {
    public static void main(String[] args) {
        args = new String[]{"1"};
        //配置执行
        SparkConf conf = new SparkConf().setAppName("Java_WordCount");
        conf.setAppName("LG_CALCULATE");
        conf.setMaster("local");

        System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master/");
        // 创建SparkContext对象:  JavaSparkContext
        JavaSparkContext context = new JavaSparkContext(conf);

        //读入数据
        JavaRDD<String> lines = context.textFile(args[0]);

        //分词
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            //       @Override
            public Iterator<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" ")).iterator();
            }

        });

        //每个单词记一次数
        JavaPairRDD<String, Integer> wordOne = words.mapToPair(new PairFunction<String, String, Integer>() {

            //       @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String,Integer>(word,1);
            }
        });

        //执行reduceByKey的操作
        JavaPairRDD<String, Integer> count = wordOne.reduceByKey(
                new Function2<Integer,
                        Integer, Integer>() {

            //       @Override
            public Integer call(Integer i1, Integer i2) throws Exception {
                return i1 + i2;
            }
        });


        //执行计算，执行action操作: 把结果打印在屏幕上
        List<Tuple2<String, Integer>> result = count.collect();

        //输出
        for(Tuple2<String, Integer> tuple: result){
            System.out.println("计算结果："+tuple._1+"\t"+tuple._2);
        }

        //停止SparkContext对象
        context.stop();
    }
}
